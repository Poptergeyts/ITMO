package network

import collections.Collection
import workModuls.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.sql.Connection
import java.util.logging.Level
import java.util.logging.Logger

class Server(workPort: String, workCollection: Collection, workDatabaseHandler: DatabaseHandler, workConnection: Connection) {

    private val logger = Logger.getLogger("logger")
    private val tokenManager= TokenManager()
    private val port: String = workPort
    private val collection= workCollection
    private val databaseHandler= workDatabaseHandler
    private val connection= workConnection
    private val executorOfCommands = ExecutorOfCommands(collection, databaseHandler, connection, tokenManager)

    init {
        logger.log(Level.INFO, "Server start")
    }

    fun startSever() {
        logger.log(Level.INFO, "Waiting for connection")
        try {
            val serverSocketChannel = ServerSocketChannel.open()
            serverSocketChannel.bind(InetSocketAddress("localhost", port.toInt()))
            while (serverSocketChannel != null) {
                val clientSocketChannel = serverSocketChannel.accept()
                Thread { getTask(clientSocketChannel) }.start()
            }
            serverSocketChannel?.close()
        } catch (e: RuntimeException) {
            logger.log(Level.SEVERE, "Connection exception")
        }
    }

    private fun getTask(clientSocketChannel: SocketChannel) {
        logger.log(Level.INFO, "Getting task")
        try {
            val objectInputStream = ObjectInputStream(clientSocketChannel.socket().getInputStream())
            val task = objectInputStream.readObject() as Task
            processTask(task, clientSocketChannel)
        } catch (e: RuntimeException) {
            logger.log(Level.SEVERE, "Getting task exception")
        }
    }

    private fun processTask(task: Task, clientSocketChannel: SocketChannel) {
        logger.log(Level.INFO, "Task processing")
        try {
            val answer = executorOfCommands.reader(task.describe, task, task.listOfCommands)
            returnAnswer(answer, clientSocketChannel)
        } catch (e: RuntimeException) {
            logger.log(Level.SEVERE, "Task processing exception")
        }
    }
    private fun returnAnswer(answer: Answer, clientSocketChannel: SocketChannel) {
        logger.log(Level.INFO, "Transmission of task")
        try {
            val objectOutputStream = ObjectOutputStream(clientSocketChannel.socket().getOutputStream())
            objectOutputStream.writeObject(answer)
        } catch (e: RuntimeException) {
            logger.log(Level.SEVERE, "Transmission of task exception")
        }
    }
}