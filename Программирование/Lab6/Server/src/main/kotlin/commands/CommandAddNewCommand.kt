package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandAddNewCommand(_name: String, _info: String, ) : Command(_name, _info)  {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        commands[packet.another!![1]] = CustomCommand(packet.another!![1], packet.another!![2].toInt())
        answer.message += "\nCustom command ${getName()} successfully added."
        return answer
    }
}