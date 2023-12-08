package application.commands

import application.collection
import application.enterOrganization

class CommandAddIfMax : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            val organization = enterOrganization()
            val annualTurnover = organization.getAnnualTurnover()
            for (org in collection) {
                if (annualTurnover > org.getAnnualTurnover()) {
                    collection.add(organization)
                    println(
                        "The value of the element is greater than the value of the largest element in this collection.\n" +
                                "The new item has been successfully added to the collection.")
                    return 0
                }
            }
            println("The value of the element does not exceed the value of the largest element of this collection.\n" +
                    "The new item has not been added to the collection.")
            return 2
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- add_if_max {element} : add a new element to the collection if \n" +
                "  its value exceeds the value of the largest element in this collection."
    }
}