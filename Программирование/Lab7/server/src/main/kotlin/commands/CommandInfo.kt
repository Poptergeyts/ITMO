package commands

import collections.Collection
import workModuls.Answer
import workModuls.Task
import java.lang.RuntimeException

class CommandInfo(private var workCollection: Collection) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            answer.result =
                ("Collection: HashTable\nSize ${workCollection.collection.size}\n ${java.time.LocalTime.now()}")
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}