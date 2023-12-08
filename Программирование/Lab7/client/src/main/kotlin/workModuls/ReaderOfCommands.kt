package workModuls

import client.Client
import java.security.MessageDigest
import kotlin.system.exitProcess

class ReaderOfCommands {
    private val listOfCommands = mutableListOf("help", "log_in", "registration")
    private val asker = Asker()
    private val client = Client()

    fun readCommand() {
        val tokens = CommandComponentsManager()
        val readerOfScripts = ReaderOfScripts()
        while (true) {
            print("-> ")
            val command = asker.askCommand()
            val commandComponents = tokens.returnCommandCommand(command)
            if (commandComponents[0] == "execute_script") {
                val listOfCommandsScript =
                    readerOfScripts.readScript(commandComponents[1], tokens, mutableListOf())
                for (i in listOfCommandsScript) {
                    val task = Task(i, listOfCommands = listOfCommands)
                    specialActions(task, asker)
                    client.sendTask(task)
                }
            } else {
                if (listOfCommands.contains(commandComponents[0])) {
                    val task = Task(commandComponents, listOfCommands = listOfCommands)
                    specialActions(task, asker)
                    client.sendTask(task)
                    listOfCommands.addAll(client.returnNewCommands())
                    client.clearNewCommands()
                } else {
                    println("Command ${commandComponents[0]} does not exist")
                }
            }
        }
    }

    private fun specialActions(task: Task, asker: Asker) {
        if (task.describe[0] == "exit") {
            client.sendTask(Task(mutableListOf("log_out"), listOfCommands = listOfCommands))
            exitProcess(0)
        }
        if (task.describe[0] == "add" || task.describe[0] == "add_if_max") {
            task.organization = asker.askOrganization()
            task.organization?.setOwner(client.login)
        }
        if (task.describe[0] == "filter_by_address") {
            task.organization = asker.askStreet()
        }
        if (task.describe[0] == "update") {
            task.organization = asker.askOrganization()
            println("Enter id for update")
            task.organization!!.setID(asker.askLong())
            task.organization?.setOwner(client.login)
        }
        if (task.describe[0] == "registration" || task.describe[0] == "log_in") {
            println("Enter login, after enter password")
            task.describe.add("${asker.askLoginAndPasswordForRegistration()} ${sha384(asker.askLoginAndPasswordForRegistration())}")
        }
    }

    private fun sha384(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-384")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}