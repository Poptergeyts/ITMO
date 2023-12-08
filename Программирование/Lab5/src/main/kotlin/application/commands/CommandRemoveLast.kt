package application.commands

import application.collection

class CommandRemoveLast : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            if (collection.size != 0) {
                collection.removeLast()
                println("The last item in the collection has been deleted.")
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
        return "- remove_last : remove the last item from the collection."
    }
}