package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CustomCommand (_name: String, private val args_number: Int)
    : Command(_name, "it's your custom command") {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        answer.message += "\nIt's your custom command ${getName()}"
        return answer
    }
}