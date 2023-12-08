package workModuls

import collections.Collection
import java.sql.Connection
import java.util.logging.Level
import java.util.logging.Logger

class ExecutorOfCommands(
    collection: Collection,
    databaseHandler: DatabaseHandler,
    connection: Connection,
    workTokenManager: TokenManager
){
    private val logger = Logger.getLogger("logger")
    private val tokens = CommandComponentsManager()
    private val tokenManager= workTokenManager
    private val commandHandler = CommandHandler(collection, databaseHandler, connection, tokenManager)

    fun reader(
        command: MutableList<String>,
        task: Task,
        listOfOldCommand: MutableList<String>
    ): Answer {
        logger.log(Level.INFO, "Reading the command")
        return if (tokenManager.getToken(task.token)!=null
            || task.describe[0] == "registration" || task.describe[0] == "log_in") {
            val commandComponents = tokens.returnCommandCommand(command)
            val answer = commandHandler.chooseCommand(commandComponents, listOfOldCommand, task)
            logger.log(Level.INFO, "Redirecting the answer")
            answer
        } else {
            println(command)
            Answer("You need to log in.\n" +
                    "P.S. If you don't have an account, write \"registration\" and follow the instructions\n" +
                    "or if you have an account, write \"log_in\"")
        }
    }
}