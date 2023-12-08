package workModuls

class CommandComponentsManager {
    fun returnCommandCommand(command: MutableList<String>): MutableList<String> {
        val commandComponent: MutableList<String> = listOf<String>().toMutableList()

        for (i in command) {
            if (i != "") commandComponent.add(i)
        }

        if (commandComponent.size == 3 && commandComponent[0] != "update id") {
            commandComponent[0] = commandComponent[0] + " " + commandComponent[1]
            commandComponent[1] = commandComponent[2]
            commandComponent.removeAt(2)
        }
        if (commandComponent[0] == "update id" && commandComponent.size == 3) {
            commandComponent[1] = commandComponent[1] + " " + commandComponent[2]
            commandComponent.removeAt(2)
        }
        if (commandComponent.size == 1) {
            commandComponent.add("")
        }
        return commandComponent
    }
}
