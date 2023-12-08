package module

import collection.VectorOfOrganizations
import collection.loadVectorToJson
import module.database.DB
import organization.Address
import organization.Coordinates
import organization.Organization
import organization.OrganizationType
import java.sql.Connection
import java.sql.PreparedStatement

class Launcher(_path: String) {
    private val vectorOfCommands = loadCommands()
    private val DB = DB()
    private var vectorOfOrganizations = VectorOfOrganizations()
    private var path: String = _path

    init {
        DB.startDataBase()
        readData(DB.getConnection())
    }

    fun readData(connection: Connection) {
        val preparedStatement = connection.prepareStatement("SELECT * FROM Organization")
        val resultSet = preparedStatement.executeQuery()

        while (resultSet.next()) {
            val id = resultSet.getLong("id")
            val name = resultSet.getString("name")
            val coordinates = Coordinates(resultSet.getDouble("x"), resultSet.getDouble("y"))
            val at = resultSet.getFloat("annual_turnover")
            val type = OrganizationType.valueOf(resultSet.getString("type"))
            val address = Address(resultSet.getString("street"), resultSet.getString("zip"))

            val organization = Organization(id, name, coordinates, at, type, address)
            vectorOfOrganizations.add(organization)
        }
    }

    fun save() {
        loadVectorToJson(vectorOfOrganizations, path)
    }

    fun execute(packet: Packet): Packet {
        val answer = Packet()

        if (packet.command == "registration") {
            val preparedStatement_oneMore =
                DB.getConnection().prepareStatement("SELECT name from Users where name = ?")
            preparedStatement_oneMore.setString(1, packet.userName)
            val resultSet = preparedStatement_oneMore.executeQuery()
            if (resultSet.next()) {
                answer.message = "\nWARNING: A user with this name already exists."
                return answer
            }
            val preparedStatement: PreparedStatement =
                DB.getConnection().prepareStatement("INSERT INTO Users(name, password) VALUES(?,?)")
            preparedStatement.setString(1, packet.userName)
            preparedStatement.setString(2, packet.userPassword)
            preparedStatement.executeUpdate()
            answer.message += "\nAuthorization successful"
        } else if (packet.command == "authorization") {
            val preparedStatement: PreparedStatement =
                DB.getConnection().prepareStatement("SELECT name from Users where Users.name = ? AND Users.password = ?")
            preparedStatement.setString(1, packet.userName)
            preparedStatement.setString(2, packet.userPassword)
            val resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                answer.message += "\nAuthorization successful"
                return answer
            } else {
                answer.message = "\nWARNING: Incorrect name or password."
                return answer
            }
        }

        val commandName = packet.command?: "Undefined"
        val command = vectorOfCommands[commandName]
        if (command != null) {
            println("Command \"${packet.command}\" was received")
            return command.execute(packet, vectorOfOrganizations, vectorOfCommands, DB.getConnection())
        } else {
            answer.message = "ERROR: Invalid command."
            return answer
        }
    }
}