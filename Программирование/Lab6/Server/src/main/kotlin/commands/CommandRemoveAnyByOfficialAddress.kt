package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandRemoveAnyByOfficialAddress(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            val address = packet.address?: "Undefined"
            collection.stream().filter {
                    it -> it.getAddress() == address
            }.forEach(collection::remove)
            return answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}