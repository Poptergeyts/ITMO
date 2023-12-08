package application

import java.io.File
import java.util.Scanner

internal fun executeScript(path: String) {
    val file = File(path)
    if (!file.canRead()) {
        println("WARNING: The script is not readable.")
        return
    }
    val scanner = Scanner(file)
    var enter: String
    while (scanner.hasNext()) {
        enter = scanner.nextLine().replace("\\s+".toRegex(), " ")

        val enterList = enter.split(" ").toMutableList()
        val command = commands[enterList[0]]

        if (enterList.size > 2) enterList[0] = "Undefined"
        else if (enterList.size == 1) {
            when (enterList[0]) {
                "add" -> addFromFile(scanner)
                "add_if_max" -> addFromFileIfMax(scanner)
                "remove_any_by_official_address" -> removeAnyByOfficialAddressFromFile(scanner)
                "filter_by_official_address" -> filterByOfficialAddressFromFile(scanner)
                else -> if (command != null) {
                            command.execute(enterList)
                        } else {
                            println("WARNING: Error in the script.")
                        }
            }
        } else {
            when (enterList[0]) {
                "update" -> {
                    try {
                        updateFromFile(scanner, enterList[1].trim().toLong())
                    } catch (e: Exception) {
                        println("WARNING: The id specified in the script is not an integer.")
                    }
                }
                else -> if (command != null) {
                        command.execute(enterList)
                        } else {
                        println("WARNING: Error in the script.")
                        }
            }
        }
    }
    scanner.close()
}