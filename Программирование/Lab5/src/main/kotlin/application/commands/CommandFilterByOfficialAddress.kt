package application.commands

import application.collection
import application.enterAddress

class CommandFilterByOfficialAddress : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 2) {
            var wasFound = false
            val address = enterAddress()

            for (org in collection) {
                if (org.getAddress().toString() == address.toString()) {
                    wasFound = true
                    println(org.toString())
                }
            }
            if (!wasFound) {
                println("WARNING: There are no organizations with this address in the collection.")
                return 2
            }
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- filter_by_official_address officialAddress : output elements\n" +
                "  whose officialAddress field value is equal to the specified one."
    }
}