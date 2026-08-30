import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess

private fun fail(message: String): Nothing {
    System.err.println("lab0: $message")
    exitProcess(65)
}

fun main(args: Array<String>) {
    val path = args.firstOrNull() ?: fail("expected one source-file path")

    val source = try {
        Files.readString(Path.of(path), StandardCharsets.UTF_8)
    } catch (error: Exception) {
        fail("cannot read '$path': ${error.message}")
    }

    print(source)
}