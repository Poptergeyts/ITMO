package application.commands

import application.collection

class CommandInfo : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            println(collection.toString())
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- info : display information about the collection on the screen."
    }
}