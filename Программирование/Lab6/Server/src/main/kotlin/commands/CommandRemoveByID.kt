package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection
import java.sql.PreparedStatement

class CommandRemoveByID(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            val id = packet.id
            collection.stream().filter {
                    it -> it.getID() == id
            }.forEach(collection::remove)

            val preparedStatement: PreparedStatement =
                connection.prepareStatement("DELETE FROM movies where user_name = ? and id = ?")
            preparedStatement.setString(1, packet.userName)
            packet.id?.toInt()?.let { preparedStatement.setInt(2, it) }
            preparedStatement.executeUpdate()
            return answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}