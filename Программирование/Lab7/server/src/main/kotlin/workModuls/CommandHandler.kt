package workModuls

import collections.Collection
import commands.*
import java.sql.Connection
import java.util.logging.Level
import java.util.logging.Logger
import java.util.stream.Collectors

class CommandHandler(
    collection: Collection,
    databaseHandler: DatabaseHandler,
    connection: Connection,
    tokenManager: TokenManager
) : CreateCommand {
    private var listOfCommand = createCommands(collection, databaseHandler, connection, tokenManager)
    private val logger = Logger.getLogger("logger")

    fun chooseCommand(commandComponent: MutableList<String>, listOfOldCommands: MutableList<String>, task: Task): Answer {
        logger.log(Level.INFO, "Command selection")
        commandComponent[0].lowercase()
        listOfCommand[commandComponent[0]]?.let {
            val answer = it.commandDo(commandComponent[1], task)
            answer.setNewCommands(
                listOfCommand.keys.stream().collect(Collectors.toList()).filter { !listOfOldCommands.contains(it) }
            )
            return answer
        }
        return Answer(commandComponent[0])
    }

    override fun createCommands(
        collection: Collection,
        databaseHandler: DatabaseHandler,
        connection: Connection,
        tokenManager: TokenManager
    ): Map<String, Command> {
        return mapOf(
            "show" to CommandShow(collection),
            "help" to CommandHelp(),
            "info" to CommandInfo(collection),
            "clear" to CommandClear(collection),
            "remove" to CommandRemoveByID(collection, databaseHandler),
            "add" to CommandAdd(collection, databaseHandler),
            "registration" to CommandRegistration(databaseHandler, tokenManager),
            "log_in" to CommandLogIn(databaseHandler, tokenManager),
            "log_out" to CommandLogOut(tokenManager),
            "remove_last" to CommandRemoveLast(collection, databaseHandler),
            "add_if_max" to CommandAddIfMax(collection, databaseHandler),
            "filter_by_address" to CommandFilterByAddress(collection),
            "sum_of_AT" to CommandSumOfAT(collection),
            "update" to CommandUpdate(collection, databaseHandler)
        )
    }
}