package module

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import java.sql.PreparedStatement

class ClientHandler(private val socket: Socket,
                    private val writer: ObjectOutputStream,
                    private val reader: ObjectInputStream,
                    private val launcher: Launcher   ): Runnable {

    override fun run() {
        println("New thread " + Thread.currentThread().name + " is started.");

        while (true) {
            try {
                val packet = reader.readObject() as Packet
                if (packet.command == "exit") {
                    println("Client has disconnected.")
                    return
                }
                print(packet.command)
                writer.writeObject(launcher.execute(packet))
            } catch (e: Exception) {
                e.message
            }
        }
    }

}
