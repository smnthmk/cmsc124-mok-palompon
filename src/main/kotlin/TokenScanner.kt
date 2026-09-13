class TokenScanner(val input: String){
    var current = 0
    var col = 1
    var line = 1
    val symbolTable = mutableMapOf<String, String>()
    var c: Char = ' '
    var symbolID = 1


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
                    val type = if(lexemestr == "while") TokenType.WHILE else TokenType.IDENTIFIER

                    if(type == TokenType.IDENTIFIER && !symbolTable.containsKey(lexemestr)){
                        symbolTable[lexemestr] = "S${symbolID++}"
                    }

                    tokens.add(Token(type, lexemestr, col-1))
                }

                c.isDigit() ->{
                    var lexemestr = ""
                    lexemestr += c
                    while(peek().isDigit()){
                        lexemestr += advance()
                    }
                    tokens.add(Token(TokenType.NUMBER, lexemestr, col-1))
                }

                c == '<' ->{
                    if(peek() == '='){
                        advance()
                        tokens.add(Token(TokenType.LESS_EQUAL, "<=", col-1))
                    }else{
                        tokens.add(Token(TokenType.LESS, "<", col-1))
                    }
                }

                c == '=' -> tokens.add(Token(TokenType.EQUAL, "=", col-1))
                c == ';' -> tokens.add(Token(TokenType.SEMICOLON, ";", col - 1))
                c == '(' -> tokens.add(Token(TokenType.LEFT_PAREN, "(", col - 1))
                c == ')' -> tokens.add(Token(TokenType.RIGHT_PAREN, ")", col - 1))

            }
        }

        tokens.add(Token(TokenType.EOF, "empty", col))
        return tokens

    }
}
