package module.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class MakeConnection {
    private lateinit var userName: String
    private lateinit var password: String
    private val connectionUrl = "jdbc:postgresql://localhost:5432/studs"
    private lateinit var connection: Connection
    private lateinit var statement: Statement

    @Throws(ClassNotFoundException::class)
    fun makeDatabaseConnection() {
        print("Enter user name: ")
        userName = readLine()?: " "
        print("Enter password: ")
        password = readLine()?: " "

        Class.forName("org.postgresql.Driver")
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password)
            statement = connection.createStatement()
            println("DB is connected.")
        } catch (e: Exception) {
            println("Incorrect input.")
            println(e.message)
            makeDatabaseConnection()
        }
    }

    fun getConnection(): Connection {
        return connection
    }

    fun getStatement(): Statement {
        return statement
    }
}
