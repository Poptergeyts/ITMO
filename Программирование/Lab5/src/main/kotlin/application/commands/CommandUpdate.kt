package application.commands

import application.collection
import application.enterOrganization

class CommandUpdate : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 2) {
            val id = args[1].toLong()

            for (org in collection) {
                if (org.getID() == id) {
                    org.update(enterOrganization())
                    println("The organization has been successfully updated.")
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
        return "- update id {element} : update the value of the collection element, \n" +
                "  whose id is equal to the specified one."
    }
}