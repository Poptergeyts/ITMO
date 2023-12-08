package module.database

import java.sql.SQLException
import java.sql.Statement

class CreateDefaultDB(private val statement: Statement) {
    fun makeTables() {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users(name text PRIMARY KEY, password text);")
        statement.executeUpdate(
            "CREATE TABLE IF NOT EXISTS Organization(id serial PRIMARY KEY" +
                    ", name text, x float, y float, annual_turnover float, type text, street text, zip text, " +
                    "user_name text REFERENCES Users(name));")
    }

    fun makeOrganizationTable() {
        try {
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Organization(id serial PRIMARY KEY" +
                        ", name text, x double, y double, annual_turnover float, type text, street text, zip text" +
                        "user_id integer)"
            )
            statement.executeUpdate("alter table Organization add constraint user_id_FK " +
                    "FOREIGN KEY (user_id) REFERENCES Users(id)")
        } catch (_: SQLException) {
        }
    }
}
