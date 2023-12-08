package commands

class CommandRemoveByID(_name: String, _info: String, private val _num: Int) : Command(_name, _info, _num) {
    override fun checkValidation(args: List<String>) : Boolean {
        try {
            val id = args[1].toLong()
            return args.size == _num + 1
        } catch (e: Exception) {
            return false
        }
    }
}