package application.commands

import application.commands

class CommandHelp : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            for (command in commands.values) {
                println(command.toString())
            }
            println("Attention: {element} is entered from a new line.")
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- help : get help."
    }
}