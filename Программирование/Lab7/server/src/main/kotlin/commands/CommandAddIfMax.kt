package commands

import collections.Collection
import workModuls.Answer
import workModuls.DatabaseHandler
import workModuls.Task
import java.lang.RuntimeException
import java.util.stream.Collectors

class CommandAddIfMax(
    private var workCollection: Collection,
    private val workDatabaseHandler: DatabaseHandler,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        var flag = true
        return try {
            val AT = task.organization!!.getAnnualTurnover()
            workCollection.collection.keys.stream().collect(Collectors.toList())
                .forEach {
                    if (workCollection.collection[it]?.getAnnualTurnover()!! > AT) {
                        flag = false
                    }
                }
            if (flag) {
                task.organization?.let { workCollection.add(it, workDatabaseHandler) }
                workCollection.collection = workDatabaseHandler.getAllOrganizations()
            } else {
                answer.result = "Failed to add the organization"
            }
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}