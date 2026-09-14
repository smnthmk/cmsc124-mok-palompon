data class Token(
    val type: TokenType,
    val lexeme: String,
    val literal: Any?,
    val col: Int,
    val line: Int
)
