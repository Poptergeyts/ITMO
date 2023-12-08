package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandUpdate(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            val id = packet.id?: 0
            collection.stream().filter {
                it.getID() == id
            }.forEach {
                packet.organization?.setID(id)
                it.update(packet.organization!!)
            }
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}