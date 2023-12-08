package application.commands

import application.collection

class CommandRemoveByID : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 2) {
            val id = args[1].toLong()

            for (org in collection) {
                if (org.getID() == id) {
                    collection.remove(org)
                    println("The organization was successfully removed from the collection.")
                    return 0
                }
            }
            println("WARNING: The organization with this id is not in the collection.")
            return 2
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- remove_by_id id : remove an item from the collection by its id."
    }
}