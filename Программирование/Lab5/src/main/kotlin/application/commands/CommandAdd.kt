package application.commands

import application.collection
import application.enterOrganization

class CommandAdd : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            collection.add(enterOrganization())
            println("The new item has been successfully added to the collection.")
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- add {element} : add a new element to the collection."
    }
}