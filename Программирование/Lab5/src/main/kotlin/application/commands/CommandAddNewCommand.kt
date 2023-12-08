package application.commands

import application.commands

class CommandAddNewCommand : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 3) {
            if (args[2].toInt() < 0) {
                println("The number of arguments cannot be less than zero.")
                return 2
            }
            commands.put(args[1], CustomCommand(args[1], args[2].toInt()))
            println("Custom command successfully added.")
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- add_new_command name args_number : add new command."
    }
}