import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess

private fun fail(message: String): Nothing {
    System.err.println("lab0: $message")
    exitProcess(65)
}

fun main(args: Array<String>) {
   /* val path = args.firstOrNull() ?: fail("expected one source-file path")

    val source = try {
        Files.readString(Path.of(path), StandardCharsets.UTF_8)
    } catch (error: Exception) {
        fail("cannot read '$path': ${error.message}")
    }

    print(source)
    */

    val src = "var limit = 10 * 5 / 2;\n" +
            "if (limit <= 25)\n" +
            "    print true;\n" +
            "else\n" +
            "    print nil;\n" +
            "while (limit < 50)\n" +
            "    limit = limit + 1 - 0;while2 = limit<=25;"

    val scanner = TokenScanner(src)
    val tokens = scanner.scanTokens()

    tokens.forEach{println(it)}
    if(scanner.errorOccured){
        exitProcess(65)
    }
    println("Symbol Table: ${scanner.symbolTable}")
}

