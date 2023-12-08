package commands

import collections.Collection
import workModuls.Answer
import workModuls.DatabaseHandler
import workModuls.Task

class CommandSave(
    private var workCollection: Collection,
    private var workDatabaseHandler: DatabaseHandler,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            workCollection.collection.values.stream().forEach {
                it.isSave = true
                workDatabaseHandler.doOrganizationSave(it.getId())}
            workDatabaseHandler.deleteNotSaveOrganization()
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}