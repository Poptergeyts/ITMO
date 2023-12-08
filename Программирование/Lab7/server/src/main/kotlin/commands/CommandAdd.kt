package commands

import collections.Collection
import workModuls.*
import java.lang.RuntimeException

class CommandAdd(
    private var workCollection: Collection,
    private val workDatabaseHandler: DatabaseHandler,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            task.organization?.let { workCollection.add(it, workDatabaseHandler) }
            workCollection.collection = workDatabaseHandler.getAllOrganizations()
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}