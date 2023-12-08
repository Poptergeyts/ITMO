package application.commands

import application.collection

class CommandShow : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            if (collection.size != 0) {
                for (org in collection) {
                    println(org.toString())
                }
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
        return "- show : display all the elements of the collection on the screen."
    }
}