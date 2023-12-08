package commands

class CommandExecuteScript(_name: String, _info: String, _num: Int) : Command(_name, _info, _num) {
    override fun execute(args: List<String>, commands: MutableMap<String, Command>): Int {
        println("Command execute script")
        return 1
    }
}