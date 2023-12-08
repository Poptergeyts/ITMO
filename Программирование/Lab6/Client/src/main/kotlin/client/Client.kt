package client

import authorization.User
import module.FileExecutor
import module.Launcher
import module.Packet
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.net.InetAddress
import java.net.Socket
import kotlin.system.exitProcess

class Client() {
    private val user = User()
    private val launcher = Launcher()

    private lateinit var client: Socket
    private lateinit var writer:ObjectOutputStream
    private lateinit var reader: ObjectInputStream
    private var port = 8888

    fun connect() {
        var enter: String
        println("Enter a port.")
        while (true) {
            print("-> ")
            enter = readLine()?: " "
            if (enter == "exit") {
                println("The program is completed.")
                exitProcess(0)
            }
            try {
                client = Socket(InetAddress.getLocalHost(), enter.toInt())
                writer = ObjectOutputStream(client.getOutputStream())
                reader = ObjectInputStream(client.getInputStream())
                port = enter.toInt()
                println("Connection successful.")

                var answer = Packet(user)
                while (true) {
                    if (answer.message == "Success\n" +
                        "----------\n" +
                        "Authorization successful") {
                        break
                    }
                    writer.writeObject(user.logIn())
                    answer = reader.readObject() as Packet
                    println(answer.message)
                }
                execute(user)
            } catch (e: IOException) {
                println("WARNING: The server is currently unavailable, please try again later.")
            } catch (e: Exception) {
                println("WARNING: The port number is incorrect. Please repeat the input.")
                println(e.message)
            }
        }
    }

    private fun execute(user: User) {
        var enter: String
        println("Enter \"help\" to get help.")

        while (true) {
            try {
                print("-> ")
                enter = readLine() ?: ""
                enter = enter.replace("\\s+".toRegex(), " ")
                val enterList = enter.split(" ")

                if (launcher.checkCommand(enterList)) {
                    if (enter == "exit") {
                        val packet = Packet(user)
                        packet.command = "exit"
                        writer.writeObject(packet)
                        println("The program is completed.")
                        exitProcess(0)
                    } else if (enterList[0] == "execute_script") {
                        val packetList = FileExecutor.execute(enterList[1], launcher, user)
                        for (packet in packetList) {
                            writer.writeObject(packet)
                            val answer = reader.readObject() as Packet
                            println(answer.message)
                        }
                    }

                    val packet = launcher.packageCommand(enterList, user)
                    print(1)
                    writer.writeObject(packet)
                    print(2)
                    val answer = reader.readObject() as Packet
                    println(answer.message)
                }
            } catch (e: IOException) {
                println("WARNING: The server is currently unavailable, please try again later.")
            } catch (e: Exception) {
                println(e.message)
                println("ERROR: I do not know what happened. Sorry...")
                exitProcess(1)
            }
        }
    }
}