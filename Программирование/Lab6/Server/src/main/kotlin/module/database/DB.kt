package module.database

import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement

class DB {
    private lateinit var statement: Statement
    private lateinit var connection: Connection

    fun getStatement(): Statement {
        return statement
    }

    fun getConnection(): Connection {
        return connection
    }

    @Throws(ClassNotFoundException::class, SQLException::class)
    fun startDataBase() {
        val DB = MakeConnection()
        DB.makeDatabaseConnection()
        val defaultDB = CreateDefaultDB(DB.getStatement())
        defaultDB.makeTables()
        statement = DB.getStatement()
        connection = DB.getConnection()
    }
}
