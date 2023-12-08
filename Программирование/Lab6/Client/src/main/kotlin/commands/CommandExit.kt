package commands

import kotlin.system.exitProcess

class CommandExit(_name: String, _info: String, _num: Int) : Command(_name, _info, _num) {
    override fun execute(args: List<String>, commands: MutableMap<String, Command>): Int {
        println("The program is completed.")
        exitProcess(0)
    }
}