package commands

import collections.Collection
import workModuls.Answer
import workModuls.Task
import java.lang.RuntimeException
import java.util.stream.Collectors

class CommandClear(private var workCollection: Collection) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            workCollection.collection.keys.stream().collect(Collectors.toList())
                .filter { workCollection.collection[it]?.getOwner() == task.login }
                .forEach(workCollection.collection::remove)
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}