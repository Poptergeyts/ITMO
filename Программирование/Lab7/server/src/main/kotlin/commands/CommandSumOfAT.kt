package commands

import collections.Collection
import workModuls.Answer
import workModuls.Task

class CommandSumOfAT (private var workCollection: Collection) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            var AT = 0F
            workCollection.collection.values.stream().forEach {
                AT += it.getAnnualTurnover()
            }
            answer.result += "sum of annual turnover = $AT"
            answer
        } catch (e: Exception) {
            answer.result = "Command exception"
            answer
        }
    }
}