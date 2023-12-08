package application.commands

class CustomCommand (private val name: String, private val args_number: Int) : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == args_number + 1) {
            println("All is ok.")
            return 0
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- $name : it's your custom command."
    }
}