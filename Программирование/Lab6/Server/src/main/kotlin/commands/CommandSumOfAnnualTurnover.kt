package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

class CommandSumOfAnnualTurnover(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            var AT = 0F
            collection.stream().forEach {
                AT += it.getAnnualTurnover()
            }
            answer.message += "sum of annual turnover = $AT"
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}