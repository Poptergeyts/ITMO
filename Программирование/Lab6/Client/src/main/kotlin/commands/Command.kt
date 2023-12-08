package commands

abstract class Command (private val name: String,
                        private val information: String, private val args_number: Int) {
    open fun execute(args: List<String>,
                         commands: MutableMap<String, Command>) : Int {
        return 1
    }

    open fun checkValidation(args: List<String>) : Boolean {
        return args.size == args_number + 1
    }

    fun getName() = name
    fun getInformation() = information
}