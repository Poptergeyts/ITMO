package commands

import collection.VectorOfOrganizations
import module.Packet
import java.lang.Float.max
import java.lang.RuntimeException
import java.sql.Connection

class CommandAddIfMax(_name: String, _info: String, ) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        var AT = 0F
        return try {
            val id = collection.getID()
            for (org in collection) {
                AT = max(org.getAnnualTurnover(), AT)
            }
            packet.organization?.let {
                if (it.getAnnualTurnover() > AT) {
                    it.setID(id)
                    collection.add(it)
                }
            }
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}