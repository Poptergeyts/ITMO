package workModuls

import commands.Command
import java.sql.Connection

interface CreateCommand {
    fun createCommands(
        collection: collections.Collection,
        databaseHandler: DatabaseHandler, connection: Connection,
        tokenManager: TokenManager
    ): Map<String, Command>
}