package module

import commands.*
import kotlin.collections.MutableMap

fun loadCommands(): MutableMap<String, Command> {
    val commandHelp = CommandHelp("help",
        ": get help", 0)
    val commandInfo = CommandInfo("info",
        ": display information about the collection on the screen", 0)
    val commandShow = CommandShow("show",
        ": display all the elements of the collection on the screen", 0)
    val commandAdd = CommandAdd("add",
        "{element} : add a new element to the collection", 0)
    val commandUpdate = CommandUpdate("update",
        "id {element} : update the value of the collection element,\n" +
                "  whose id is equal to the specified one", 1)
    val commandRemoveByID = CommandRemoveByID("remove_by_id",
        "id : remove an item from the collection by its id", 1)
    val commandClear = CommandClear("clear",
        ": clear the collection", 0)
//    val commandSave = CommandSave("save",
//        ": save the collection to a file", 0)
    val commandExecuteScript = CommandExecuteScript("execute_script",
        "file_name : read and execute the script from the specified file", 1)
    val commandExit = CommandExit("exit",
        ": terminate the program (without saving to a file)", 0)
    val commandRemoveLast = CommandRemoveLast("remove_last",
        ": remove the last item from the collection", 0)
    val commandAddIfMax = CommandAddIfMax("add_if_max",
        "{element} : add a new element to the collection if\n" +
            "  its value exceeds the value of the largest element in this collection", 0)
    val commandShuffle = CommandShuffle("shuffle",
        ": shuffle the collection items in random order", 0)
    val commandRemoveAnyByOfficialAddress =
        CommandRemoveAnyByOfficialAddress("remove_any_by_official_address",
            "{element} : remove one item from the collection\n" +
                    "  whose officialAddress field value is equivalent to the specified one", 0)
    val commandSumOfAnnualTurnover = CommandSumOfAnnualTurnover("sum_of_annual_turnover",
        ": output the sum of the values of the annualTurnover field\n" +
                "  for all elements of the collection", 0)
    val commandFilterByOfficialAddress =
        CommandFilterByOfficialAddress("filter_by_official_address",
            "{element} : output elements\n" +
                    "  whose officialAddress field value is equal to the specified one", 0)
    val commandAddNewCommand = CommandAddNewCommand("add_new_command",
        "name args_number : add new command", 2)

    val commands = mutableMapOf<String, Command>()
    commands[commandHelp.getName()] = commandHelp
    commands[commandInfo.getName()] = commandInfo
    commands[commandShow.getName()] = commandShow
    commands[commandAdd.getName()] = commandAdd
    commands[commandUpdate.getName()] = commandUpdate
    commands[commandRemoveByID.getName()] = commandRemoveByID
    commands[commandClear.getName()] = commandClear
//    commands[commandSave.getName()] = commandSave
    commands[commandExecuteScript.getName()] = commandExecuteScript
    commands[commandExit.getName()] = commandExit
    commands[commandRemoveLast.getName()] = commandRemoveLast
    commands[commandAddIfMax.getName()] = commandAddIfMax
    commands[commandShuffle.getName()] = commandShuffle
    commands[commandRemoveAnyByOfficialAddress.getName()] = commandRemoveAnyByOfficialAddress
    commands[commandSumOfAnnualTurnover.getName()] = commandSumOfAnnualTurnover
    commands[commandFilterByOfficialAddress.getName()] = commandFilterByOfficialAddress
    commands[commandAddNewCommand.getName()] = commandAddNewCommand

    return commands
}