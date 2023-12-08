package application.commands

import application.executeScript

class CommandExecuteScript : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 2) {
            executeScript(args[1])
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- execute_script file_name : read and execute the script from the specified file."
    }
}