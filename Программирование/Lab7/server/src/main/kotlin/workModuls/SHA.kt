package workModuls

import java.security.MessageDigest

fun sha224(input: String): String {
    val bytes = input.toByteArray()
    val md = MessageDigest.getInstance("SHA-224")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}