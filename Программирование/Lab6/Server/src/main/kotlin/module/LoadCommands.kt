package module

import commands.*
import kotlin.collections.MutableMap

fun loadCommands(): MutableMap<String, Command> {
    val commands = mutableMapOf<String, Command>()
    val commandHelp = CommandHelp("help",
        ": get help")
    val commandInfo = CommandInfo("info",
        ": display information about the collection on the screen")
    val commandShow = CommandShow("show",
        ": display all the elements of the collection on the screen")
    val commandAdd = CommandAdd("add",
        "{element} : add a new element to the collection")
    val commandUpdate = CommandUpdate("update",
        "id {element} : update the value of the collection element,\n" +
                "  whose id is equal to the specified one")
    val commandRemoveByID = CommandRemoveByID("remove_by_id",
        "id : remove an item from the collection by its id")

    val commandRemoveLast = CommandRemoveLast("remove_last",
        ": remove the last item from the collection")
    val commandAddIfMax = CommandAddIfMax("add_if_max",
        "{element} : add a new element to the collection if\n" +
            "  its value exceeds the value of the largest element in this collection")
    val commandShuffle = CommandShuffle("shuffle",
        ": shuffle the collection items in random order")
    val commandRemoveAnyByOfficialAddress =
        CommandRemoveAnyByOfficialAddress("remove_any_by_official_address",
            "{element} : remove one item from the collection\n" +
                    "  whose officialAddress field value is equivalent to the specified one")
    val commandSumOfAnnualTurnover = CommandSumOfAnnualTurnover("sum_of_annual_turnover",
        ": output the sum of the values of the annualTurnover field\n" +
                "  for all elements of the collection")
    val commandFilterByOfficialAddress =
        CommandFilterByOfficialAddress("filter_by_official_address",
            "{element} : output elements\n" +
                    "  whose officialAddress field value is equal to the specified one")

    commands[commandHelp.getName()] = commandHelp
    commands[commandInfo.getName()] = commandInfo
    commands[commandShow.getName()] = commandShow
    commands[commandAdd.getName()] = commandAdd
    commands[commandUpdate.getName()] = commandUpdate
    commands[commandRemoveByID.getName()] = commandRemoveByID

    commands[commandRemoveLast.getName()] = commandRemoveLast
    commands[commandAddIfMax.getName()] = commandAddIfMax
    commands[commandShuffle.getName()] = commandShuffle
    commands[commandRemoveAnyByOfficialAddress.getName()] = commandRemoveAnyByOfficialAddress
    commands[commandSumOfAnnualTurnover.getName()] = commandSumOfAnnualTurnover
    commands[commandFilterByOfficialAddress.getName()] = commandFilterByOfficialAddress

    return commands
}