import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess

private fun fail(message: String): Nothing {
    System.err.println("lab1: $message")
    exitProcess(65)
}

fun main(args: Array<String>) {
    val lex = args.contains("--lex")
    val path = args.firstOrNull { !it.startsWith("-") }
        ?: fail("expected one source-file path")

    val source = try {
        Files.readString(Path.of(path), StandardCharsets.UTF_8)
    } catch (error: Exception) {
        fail("cannot read '$path': ${error.message}")
    }

    val scanner = TokenScanner(source)
    val tokens = scanner.scanTokens()

    if (scanner.errorOccured) {
        val errorOutput = tokens.filter { it.type != TokenType.EOF }

        System.err.println("Invalid ")

        errorOutput.forEach { errorToken ->
            when(errorToken.type) {
                // TokenType.ERROR -> System.err.println(errorToken.lexeme) 
                else -> System.err.println(errorToken)
            }
        }

        System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX")

        exitProcess(65)
    }

    if (lex) {
        for (token in tokens) {
            println("Token(type=${token.type}, lexeme=${token.lexeme}, literal=${token.literal}, line=${token.line})")}
    }
}