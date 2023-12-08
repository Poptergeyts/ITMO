import module.Launcher
import module.database.DB
import server.Server

fun main() {
    val path = System.getenv("LAB")?: "NotFound"
    val server = Server(path)
    server.start()
}