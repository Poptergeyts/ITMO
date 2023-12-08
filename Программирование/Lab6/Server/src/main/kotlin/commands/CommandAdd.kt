package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.concurrent.locks.ReentrantLock

class CommandAdd(_name: String, _info: String) : Command(_name, _info) {
    override fun execute(
        packet: Packet,
        collection: VectorOfOrganizations,
        commands: MutableMap<String, Command>,
        connection: Connection
    ): Packet {
        val answer = Packet()
        return try {
            val id = collection.getID()
            val organization = packet.organization
            val locker =ReentrantLock()
            locker.lock()
            collection.add(organization)
            val preparedStatement: PreparedStatement = connection.prepareStatement("INSERT INTO Organization" +
                    "(name, x, y, annual_turnover, type, street, zipuser_id, user_name ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
            if (organization != null) {
                preparedStatement.setString(1, organization.name)
                preparedStatement.setFloat(2, organization.coordinates.x.toFloat())
                preparedStatement.setFloat(3, organization.coordinates.y.toFloat())
                preparedStatement.setFloat(4, organization.getAnnualTurnover())
                preparedStatement.setString(5, organization.type.name)
                preparedStatement.setString(6, organization.officialAddress.street)
                preparedStatement.setString(7, organization.officialAddress.zipCode)
                preparedStatement.setString(8, packet.userName)
                preparedStatement.executeUpdate()
            }
            locker.unlock()
            answer
        } catch (e: RuntimeException){
            answer.message = "Command exception"
            answer
        }
    }
}