package commands

class CommandAddNewCommand(_name: String, _info: String, private val _num: Int) : Command(_name, _info, _num) {
    override fun execute(args: List<String>, commands: MutableMap<String, Command>): Int {
        if (checkValidation(args)) {
            if (args[2].toInt() < 0) {
                println("The number of arguments cannot be less than zero.")
                return 2
            }
            commands[args[1]] = CustomCommand(args[1], args[2].toInt())
            println("Custom command successfully added.")
            return 0
        } else {
            println("WARNING: An argument is not specified.")
            return 1
        }
    }

    override fun checkValidation(args: List<String>) : Boolean {
        try {
            val id = args[2].toInt()
            return args.size == _num + 1
        } catch (e: Exception) {
            return false
        }
    }
}