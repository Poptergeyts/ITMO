package workModuls

import java.util.stream.Collectors

class CommandComponentsManager {
    fun returnCommandCommand(command: String): MutableList<String> {
        val commandComponent1 = command.trim().split(" ").toMutableList()
        val commandComponent2 = commandComponent1.stream().filter{it!=""}.collect(Collectors.toList())
        if (commandComponent2.size == 3) {
            commandComponent2[0] = commandComponent2[0] + " " + commandComponent2[1]
            commandComponent2[1] = commandComponent2[2]
            commandComponent2.removeAt(2)
        }
        return commandComponent2
    }
}
