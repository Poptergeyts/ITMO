package authorization

import module.Packet
import java.util.*

class User {
    private var name = "Undefined"
    private var password = "Undefined"

    fun logIn(): Packet {
        val packet = Packet(this)
        println("If you want to register, enter 0.")
        print("If you want to log in, enter 1.\n-> ")
        val enter = readLine()?: " "
        return if (enter == "0") {
            packet.command = "registration"
            name = enterName()
            packet.userName = name
            password = createPassword()
            packet.userPassword = password
            packet
        } else if (enter == "1") {
            packet.command = "authorization"
            name = enterName()
            packet.userName = name
            password = enterPassword()
            packet.userPassword = password
            packet
        } else {
            println("WARNING: Try again.")
            logIn()
        }
    }

    private fun enterName(): String {
        print("Enter name: ")
        val enter = readLine()?: " "
        if (enter.length >= 24) {
            println("WARNING: The name must not exceed 23 characters.")
            return enterName()
        }
        return enter
    }

    private fun enterPassword(): String {
        print("Enter your password: ")
        val enter = readLine()?: " "
        return encryptWithSHA(enter)
    }

    private fun createPassword(): String {
        print("Enter your password: ")
        val password1 = readLine()?: " "
        print("Repeat the password: ")
        val password2 = readLine()?: " "
        if (password1 == password2) {
            return encryptWithSHA(password1)
        }
        println("WARNING: Passwords don't match, try again.")
        return createPassword()
    }

    fun getName(): String {
        return name
    }

    fun getPassword(): String {
        return password
    }
}