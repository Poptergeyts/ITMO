import network.Server
import collections.Collection
import commands.CommandSave
import workModuls.DatabaseHandler
import workModuls.Task
import java.sql.Connection
import kotlin.system.exitProcess

fun main() {
    Class.forName("org.postgresql.Driver");
    val collection = Collection()

    print("Enter user login: ")
    val user = readln()
    print("Enter password: ")
    val password = readln()
    val url = "jdbc:postgresql://localhost:5432/studs"

    val databaseHandler = DatabaseHandler(user, password, url)
    val connection = databaseHandler.connect()

    collection.collection = databaseHandler.getAllOrganizations()
    executeStartServer(collection, databaseHandler, connection, "8888")
}

fun executeStartServer(collection: Collection, databaseHandler: DatabaseHandler, connection: Connection, port: String) {
    val server = Server(port, collection, databaseHandler, connection)
    val commandSave = CommandSave(collection, databaseHandler)
    val emptyList = "".split("").toMutableList()
    Thread { server.startSever() }.start()
    while (true) {
        val task = readLine()
        if (task == "exit") {
            databaseHandler.DELETE_NOTSAVE_ORGANIZATIONS.execute()
            exitProcess(0)
        } else if (task == "save") {
            commandSave.commandDo("nothing", Task(emptyList, listOfCommands = emptyList))
        }
    }
}
