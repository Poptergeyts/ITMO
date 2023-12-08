package application.commands

import application.collection

class CommandShuffle : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            if (collection.size != 0) {
                collection.shuffle()
                println("The collection is mixed.")
                return 0
            }
            println("The collection is empty.")
            return 2
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- shuffle: shuffle the collection items in random order."
    }
}