package module

import authorization.User
import commands.Command
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class FileExecutor {
    companion object {
        var level = 0
        fun execute(path: String, launcher: Launcher, user: User): ArrayList<Packet> {
            val packetList = ArrayList<Packet>()
            if (level > 3) {
                println("WARNING: So many scripts.")
                return packetList
            }

            val file = File(path)
            if (!file.canRead()) {
                println("WARNING: The script is not readable.")
                return packetList
            }
            val scanner = Scanner(file)
            var enter: String
            while (scanner.hasNext()) {
                enter = scanner.nextLine().replace("\\s+".toRegex(), " ")

                val enterList = enter.split(" ").toMutableList()
                if (launcher.checkCommand(enterList)) {
                    val packet = launcher.packageCommand(enterList, user)
                    packetList.add(packet)
                }
            }
            scanner.close()
            return packetList
        }
    }
}