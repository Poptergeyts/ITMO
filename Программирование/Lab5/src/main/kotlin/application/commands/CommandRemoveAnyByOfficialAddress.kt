package application.commands

import application.collection
import application.enterAddress

class CommandRemoveAnyByOfficialAddress : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 2) {
            val address = enterAddress()

            for (org in collection) {
                if (org.getAddress().toString() == address.toString())
                {
                    collection.remove(org)
                    println("The organization was successfully deleted.")
                    return 0
                }
            }
            println("WARNING: The organization with this address is not in the collection.")
            return 2
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- remove_any_by_official_address officialAddress : remove one item from the collection\n" +
                "  whose officialAddress field value is equivalent to the specified one."
    }
}