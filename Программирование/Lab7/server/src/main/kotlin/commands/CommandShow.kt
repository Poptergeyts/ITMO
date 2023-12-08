package commands

import collections.Collection
import workModuls.Answer
import workModuls.Task

class CommandShow(private var workCollection: Collection) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        var flag = true
        return try {
            val listOfOrganization = workCollection.collection.values
            listOfOrganization.stream()
                .forEach {
                    flag = false
                    answer.result += """
                        id:${it.getId()}
                        Organization: ${it.getName()}
                        Annual Turnover: ${it.getAnnualTurnover()}
                        x: ${it.getCoordinateX()}, y: ${it.getCoordinateY()} 
                        Type: ${it.getType()}
                        Address: ${it.getStreet()} ${it.getZipCode()}
                        ----------
                    """.trimIndent()
                    answer.result += "\n"
                }
            if (flag) {
                answer.result += "The collection is empty"
            }
            answer
        } catch (e: Exception) {
            answer.result = "Command exception"
            answer
        }
    }
}