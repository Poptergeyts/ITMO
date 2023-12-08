package application.commands

import application.collection
import application.filePath
import collection.loadVectorToJson

class CommandSave : Command {
    override fun execute(args: List<String>): Int {
        if (args.size == 1) {
            return loadVectorToJson(collection, filePath)
        } else {
            println("WARNING: A nonexistent command is entered, or an argument is not specified.")
            return 1
        }
    }

    override fun toString(): String {
        return "- save : save the collection to a file."
    }
}