package client

import workModuls.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel

class Client {
    private val listOfNewCommands = mutableListOf<String>()
    var token = ""
    var login = ""

    private fun connection(): SocketChannel {
        return try {
            val clientSocket = SocketChannel.open()
            clientSocket.socket().connect(InetSocketAddress("localhost", 8888))
            clientSocket
        } catch (e: RuntimeException) {
            println("Bad connection")
            SocketChannel.open(InetSocketAddress("localhost", 8888))
            throw e
        }
    }

    fun sendTask(task: Task) {
        try {
            val clientSocket = connection()
            if (clientSocket.isConnected) {
                val objectOutputStream = ObjectOutputStream(clientSocket.socket().getOutputStream())
                if (task.describe[0] == "registration" || task.describe[0] == "log_in") {
                    putLoginAndPassword(task)
                }
                if (task.describe[0]=="log_out") {
                    task.describe.add(token)
                }
                task.login = login
                task.token = token
                objectOutputStream.writeObject(task)
                getAnswer(clientSocket)
            }
        } catch (e: Exception) {
            println("Bad output")
        }
    }

    private fun getAnswer(clientSocket: SocketChannel) {
        val objectInputStream = ObjectInputStream(clientSocket.socket().getInputStream())
        val answer = objectInputStream.readObject() as Answer
        listOfNewCommands.addAll(answer.listOfNewCommand)
        if (answer.result.split(" ").contains("token")) {
            token = answer.result.split(" ").last()
        }
        println(answer.result)
        clientSocket.close()
    }

    fun returnNewCommands(): MutableList<String> {
        return listOfNewCommands
    }

    fun clearNewCommands() {
        listOfNewCommands.clear()
    }

    private fun putLoginAndPassword(task: Task) {
        val components = task.describe[1].trim().split(" ")
        login = components[0]
        token = components[1]
    }
}