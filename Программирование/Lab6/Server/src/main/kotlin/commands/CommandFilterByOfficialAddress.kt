package commands

import collection.VectorOfOrganizations
import module.Packet
import java.lang.RuntimeException
import java.sql.Connection
import java.util.stream.Collectors

class CommandFilterByOfficialAddress(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            val address = packet.address?: "Undefined"
            collection.stream().collect(Collectors.toList()).filter {
                    it -> it.getAddress() == address
            }.forEach {
                answer.message += "\n${it.toString()}"
            }
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}