package application

import application.commands.*
import collection.VectorOfOrganizations
import collection.loadVectorFromJson
import java.util.Vector

internal var filePath = ""
internal var collection = VectorOfOrganizations()
internal var commands = mutableMapOf<String, Command>()

internal fun console(path: String) {
    commands["help"] = CommandHelp()
    commands["info"] = CommandInfo()
    commands["show"] = CommandShow()
    commands["add"] = CommandAdd()
    commands["update"] = CommandUpdate()
    commands["remove_by_id"] = CommandRemoveByID()
    commands["clear"] = CommandClear()
    commands["save"] = CommandSave()
    commands["execute_script"] = CommandExecuteScript()
    commands["exit"] = CommandExit()
    commands["remove_last"] = CommandRemoveLast()
    commands["add_if_max"] = CommandAddIfMax()
    commands["shuffle"] = CommandShuffle()
    commands["remove_any_by_official_address"] = CommandRemoveAnyByOfficialAddress()
    commands["sum_of_annual_turnover"] = CommandSumOfAnnualTurnover()
    commands["filter_by_official_address"] = CommandFilterByOfficialAddress()
    commands["add_new_command"] = CommandAddNewCommand()

    filePath = path
    collection = loadVectorFromJson(path)

    var enter: String
    println("Enter \"help\" to get help.")

    while (true) {
        print("-> ")
        enter = readLine() ?: ""
        enter = enter.replace("\\s+".toRegex(), " ")

        val enterList = enter.split(" ")
        val command = commands[enterList[0]]

        if (command != null) {
            command.execute(enterList)
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
        }
    }
}