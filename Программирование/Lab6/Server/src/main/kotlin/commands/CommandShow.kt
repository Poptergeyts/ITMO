package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandShow(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        var count = 0
        return try {
            collection.forEach {
                answer.message += "\n${it.toString()}"
                count += 1
            }
            if (count == 0) {
                answer.message += "\nCollection is empty."
            }
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}