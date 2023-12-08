package commands

import collections.Collection
import organization.Organization
import workModuls.Answer
import workModuls.DatabaseHandler
import workModuls.Task
import java.lang.RuntimeException
import java.util.stream.Collectors

class CommandRemoveLast(
    private var workCollection: Collection,
    private var workDatabaseHandler: DatabaseHandler,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        var flag = false
        var maxValue = -1L;
        var org: String = "-1";
        return try {
            workCollection.collection.keys.stream().collect(Collectors.toList())
                .filter {workCollection.collection[it]?.getOwner() == task.login
                }.forEach {
                    if (workCollection.collection[it]!!.getId() > maxValue) {
                        maxValue = workCollection.collection[it]!!.getId()
                        org = it
                    }
                    flag = true
                }
            if (!flag) {
                answer.result = "Failed to delete the organization"
            } else {
                workCollection.remove(org, workDatabaseHandler)
            }
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}