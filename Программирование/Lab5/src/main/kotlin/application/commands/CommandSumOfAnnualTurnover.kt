package application.commands

import application.collection

class CommandSumOfAnnualTurnover : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            if (collection.size != 0) {
                var sum = 0F
                for (org in collection) {
                    sum += org.getAnnualTurnover()
                }
                println("The sum of the annual turnover is equal to $sum.")
                return 0
            }
            println("The collection is empty.")
            return 1
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- sum_of_annual_turnover : output the sum of the values of the annualTurnover field \n" +
                "  for all elements of the collection."
    }
}