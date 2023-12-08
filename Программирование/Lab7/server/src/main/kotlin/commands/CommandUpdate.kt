package commands

import collections.Collection
import workModuls.Answer
import workModuls.DatabaseHandler
import workModuls.Task
import java.lang.RuntimeException

class CommandUpdate(
    private var workCollection: Collection,
    private val workDatabaseHandler: DatabaseHandler,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        var flag = true
        return try {
            workCollection.collection.values.stream().filter { it -> it.getId() == task.organization!!.getId() }
                .forEach {
                    flag = false
                    task.organization?.let { workCollection.update(it, workDatabaseHandler) }
                    workCollection.collection = workDatabaseHandler.getAllOrganizations()
                }
            if (flag) {
                answer.result = "Failed to update the organization"
            }
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}