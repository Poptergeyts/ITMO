package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandRemoveLast(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            if (collection.size != 0) {
                collection.removeLast()
            }
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}