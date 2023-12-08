package application.commands

interface Command {
    override fun toString() : String

    fun execute(args: List<String>) : Int
}