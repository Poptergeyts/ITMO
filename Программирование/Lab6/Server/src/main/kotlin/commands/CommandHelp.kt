package commands

import collection.VectorOfOrganizations
import module.Packet
import java.lang.RuntimeException
import java.sql.Connection

class CommandHelp(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            commands.values.stream().forEach {
                answer.message += "\n- ${it.getName()} ${it.getInfo()}."
            }
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}