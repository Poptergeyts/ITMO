package authorization

import java.security.MessageDigest
import java.math.BigInteger


fun encryptWithSHA (input: String): String{
    val md = MessageDigest.getInstance("SHA-224")
    val bytes = md.digest(input.encodeToByteArray())
    var hash = BigInteger(1, bytes).toString(16)

    while (hash.length < 32) {
        hash = "0$hash"
    }

    return hash
}