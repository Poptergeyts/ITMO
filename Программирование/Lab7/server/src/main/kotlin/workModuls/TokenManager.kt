package workModuls

import java.util.*
import java.util.concurrent.TimeUnit

class TokenManager {
    private val tokens = mutableMapOf<String, Token>()

    fun createToken(tokenValue: String): Token {
        removeExpiredTokens()
        val expirationDate = Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10))
        val token = Token(tokenValue, expirationDate)
        tokens[tokenValue] = token
        return token
    }

    fun getToken(tokenValue: String): Token? {
        try{
            val token = tokens[tokenValue]
            if (token != null && token.isValid()) {
                token.expirationDate =
                    Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10))
                return token
            }
            removeExpiredTokens()
            return null
        }
        catch (e: RuntimeException){
            return null
        }
    }

    fun removeToken(tokenValue: String) {
        tokens.remove(tokenValue)
    }

    private fun removeExpiredTokens() {
        val iterator = tokens.iterator()
        while (iterator.hasNext()) {
            val token = iterator.next().value
            if (!token.isValid()) {
                iterator.remove()
            }
        }
    }
}