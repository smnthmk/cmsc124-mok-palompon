class TokenScanner(val input: String){
    var current = 0
    var col = 1
    var line = 1
    val symbolTable = mutableMapOf<String, String>()
    var c: Char = ' '
    var symbolID = 1
    var errorOccured = false

    val keywords = mapOf(
        "var" to TokenType.VAR,
        "print" to TokenType.PRINT,
        "if" to TokenType.IF,
        "else" to TokenType.ELSE,
        "while" to TokenType.WHILE,
        "true" to TokenType.TRUE,
        "false" to TokenType.FALSE,
        "nil" to TokenType.NIL
    )


    fun isAtEnd(): Boolean{
        return current >= input.length
    }

    fun peek(): Char{
        return if(!isAtEnd()){
            input[current]
        }else{
            '\u0000'
        }
    }

    fun advance(): Char{
        c = input[current++]
        col++
        return c
    }
    fun scanTokens(): List<Token>{
        val tokens = mutableListOf<Token>()

        while(!isAtEnd()){
            var startCol = col
            val c = advance()

            when {
                c.isWhitespace()->{
                    if(c=='\n'){
                        line++
                        col = 1
                    }
                }
                c.isLetter() || c=='_' ->{
                    var lexemestr = ""
                    lexemestr += c

                    while(peek().isLetter() || peek().isDigit() || peek() == '_'){
                        lexemestr += advance()
                    }
                    //val type = if(lexemestr == "while") TokenType.WHILE else TokenType.IDENTIFIER

                    val type = keywords[lexemestr]?:TokenType.IDENTIFIER

                    if(type == TokenType.IDENTIFIER && !symbolTable.containsKey(lexemestr)){
                        symbolTable[lexemestr] = "S${symbolID++}"
                    }

                    tokens.add(Token(type, lexemestr,null, startCol, line))
                }

                c.isDigit() ->{
                    var lexemestr = ""
                    lexemestr += c
                    while(peek().isDigit()){
                        lexemestr += advance()
                    }
                    tokens.add(Token(TokenType.NUMBER, lexemestr,lexemestr.toDouble(), startCol, line))
                }

                c == '<' ->{
                    if(peek() == '='){
                        advance()
                        tokens.add(Token(TokenType.LESS_EQUAL, "<=", null, startCol, line))
                    }else{
                        tokens.add(Token(TokenType.LESS, "<", null, startCol, line))
                    }
                }

                //equals and bangs
                c == '=' ->{
                    if(peek() == '='){
                        advance()
                        tokens.add(Token(TokenType.EQUAL_EQUAL, "==", null, startCol, line))
                    }else{
                        tokens.add(Token(TokenType.EQUAL, "=", null, startCol, line))
                    }
                }

                c == '!' ->{
                    if(peek() == '='){
                        advance()
                        tokens.add(Token(TokenType.BANG_EQUAL, "!=", null, startCol, line))
                    }else{
                        tokens.add(Token(TokenType.BANG, "!", null, startCol, line))
                    }
                }

                // string handling
                c == '"' -> {
                    val startLine = line
                    var lexemestr = ""
                    while (peek() != '"' && !isAtEnd()) {
                        if (peek() == '\n') {
                            line++
                        }

                        val ch = advance()
                        lexemestr += ch
                        if (ch == '\n') {  
                            col = 1
                        }
                    }

                    if (isAtEnd()) {
                        System.err.println("Unterminated string at line: $startLine")
                        errorOccured = true
                    } else {
                        advance() 
                        tokens.add(Token(TokenType.STRING, "\"$lexemestr\"", lexemestr, startCol, startLine))
                    }
                }

                c == ';' -> tokens.add(Token(TokenType.SEMICOLON, ";",null, startCol, line))
                c == '(' -> tokens.add(Token(TokenType.LEFT_PAREN, "(",null, startCol, line))
                c == ')' -> tokens.add(Token(TokenType.RIGHT_PAREN, ")", null, startCol, line))
                c == '+' -> tokens.add(Token(TokenType.PLUS, "+", null, startCol, line))
                c == '-' -> tokens.add(Token(TokenType.MINUS, "-", null, startCol, line))
                c == '*' -> tokens.add(Token(TokenType.STAR, "*", null, startCol, line))
                c == '/' -> tokens.add(Token(TokenType.SLASH, "/", null, startCol, line))
                c == '{' -> tokens.add(Token(TokenType.LEFT_BRACE, "{", null, startCol, line))
                c == '}' -> tokens.add(Token(TokenType.RIGHT_BRACE, "}", null, startCol, line))

                else -> {
                    System.err.println("Invalid Char: $c at line: $line")
                    errorOccured = true
                }
            }
        }

        tokens.add(Token(TokenType.EOF, "empty", null, col, line))
        return tokens

    }
}
