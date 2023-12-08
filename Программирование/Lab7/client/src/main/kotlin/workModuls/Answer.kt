package workModuls

import java.io.Serializable

data class Answer(var result: String = "Success\n----------\n") : Serializable {
    val listOfNewCommand = mutableListOf<String>()

    fun setNewCommands(list: List<String>) {
        listOfNewCommand.addAll(list)
    }
}