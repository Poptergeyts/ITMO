package server

import module.ClientHandler
import module.Launcher
import org.slf4j.LoggerFactory
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import kotlin.system.exitProcess

class Server(path: String) {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    private var port = 8888
    private lateinit var server: ServerSocket
    private val launcher = Launcher(path)

    constructor(_port: Int, path: String) : this(path) {
        port = _port
    }

    fun start() {
        logger.info("asd")
        try {
            server = ServerSocket(port, 3)
            println("The server is listening on the port $port")
            launch()
        } catch (e: Exception) {
            println(e.message)
            println("ERROR: Server failed.")
            exitProcess(1)
        }
    }

    private fun launch() {
        while (true) {
            try {
                val s = server.accept()
                val writer = ObjectOutputStream(s.getOutputStream())
                val reader = ObjectInputStream(s.getInputStream())
                val thread = Thread(ClientHandler(s, writer, reader, launcher))
                thread.start()
            } catch (e: Exception) {
                println(e.message)
            }
        }


//        val br = BufferedReader(InputStreamReader(System.`in`))
//        while (true) {
//            if (br.ready()) {
//                val serverCommand = br.readLine()
//                if (serverCommand.equals("exit")) {
//                    exitProcess(0)
//                }
//                if (serverCommand.equals("save")){
//                    launcher.save()
//                    continue
//                }
//            }
//            try {
//                val socket = server.accept()
//                val writer = ObjectOutputStream(socket.getOutputStream())
//                val reader = ObjectInputStream(socket.getInputStream())
//                val packet = reader.readObject() as Packet
//                if (packet.command == "exit") {
//                    println("Client has disconnected.")
//                    continue
//                } else if (packet.command == "connect") {
//                    println("New client has connected.")
//                    continue
//                }
//                writer.writeObject(launcher.execute(packet))
//            } catch (_: Exception) {
//                println("WARNING: Broken packet.")
//                continue
//            }
//        }
    }
}