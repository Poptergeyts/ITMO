package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandInfo(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        answer.message += "\n${collection.toString()}"
        return answer
    }
}