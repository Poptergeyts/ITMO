package commands

import collections.Collection
import workModuls.Answer
import workModuls.DatabaseHandler
import workModuls.Task
import java.lang.RuntimeException

import java.util.stream.Collectors

class CommandRemoveByID(
    private var workCollection: Collection,
    private var workDatabaseHandler: DatabaseHandler,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        var flag = false
        return try {
            if (workCollection.collection.keys.contains(key)) {
                workCollection.collection.keys.stream().collect(Collectors.toList())
                    .filter { it.hashCode() == key.uppercase().hashCode()
                            && workCollection.collection[it]?.getOwner() == task.login
                    }.forEach {
                        workCollection.remove(it, workDatabaseHandler)
                        flag = true
                    }
            }
            if (!flag) {
                answer.result = "Failed to delete the organization"
            }
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}