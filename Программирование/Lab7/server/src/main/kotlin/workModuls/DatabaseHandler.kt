package workModuls

import organization.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class DatabaseHandler(workUser: String, workPassword: String, workUrl: String) {
    val user = workUser
    val password = workPassword
    val url = workUrl
    val logger = Logger.getLogger("logger")
    val connection: Connection = DriverManager.getConnection(url, user, password)
    val SELECT_ALL_ORGANIZATIONS = connection.prepareStatement("SELECT * FROM organization;")
    val DELETE_NOTSAVE_ORGANIZATIONS =
        connection.prepareStatement("delete from organization where(organization.issave=false);")
    val DO_ORGANIZATION_NOTSAVE =
        connection.prepareStatement("update organization set issave=false where(organization.id=?);")
    val DO_ORGANIZATION_SAVE =
        connection.prepareStatement("update organization set issave=true where(organization.id=?);")
    val REGISTRATE_USER = connection.prepareStatement("insert into users (login, password) values(?, ?)")
    val CHECK_USER =
        connection.prepareStatement("select count(*) from users where (login=? and password=?);")
    val INSERT_ORGANIZATION = connection.prepareStatement(
        "insert into organization " +
                "(name, annual_turnover, x, y, type, street, zip_code, issave, owner) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?);"
    )
    val UPDATE_ORGANIZATION = connection.prepareStatement(
        "update organization set name=?, annual_turnover=?, x=?, y=?, type=?, street=?, zip_code=?, " +
                "issave=?, owner=? where(organization.id=?)"
    );

    fun connect(): Connection {
        try {
            if (!connection.isClosed) {
                logger.log(Level.INFO, "Successfully connect to database")
                return connection
            } else {
                throw SQLException()
            }
        } catch (e: SQLException) {
            logger.log(Level.SEVERE, "Something is wrong with database")
            throw e
        }
    }

    fun deleteNotSaveOrganization() {
        try {
            DELETE_NOTSAVE_ORGANIZATIONS.execute()
        } catch (e: SQLException) {
            logger.log(Level.SEVERE, "Exception when delete Organization")
        }
    }

    fun doOrganizationNotSave(id: Long) {
        try {
            DO_ORGANIZATION_NOTSAVE.setLong(1, id)
            DO_ORGANIZATION_NOTSAVE.execute()
        } catch (e: SQLException) {
            throw e
        }
    }

    fun doOrganizationSave(id: Long) {
        try {
            DO_ORGANIZATION_SAVE.setLong(1, id)
            DO_ORGANIZATION_SAVE.execute()
        } catch (e: SQLException) {
            println(e.message)
            throw e
        }
    }

    fun userRegistration(login: String, password: String) {
        try {
            REGISTRATE_USER.setString(1, login)
            REGISTRATE_USER.setString(2, password)
            REGISTRATE_USER.execute()
        } catch (e: SQLException) {
            throw e
        }
    }

    fun checkUser(login: String, password: String): Boolean {
        try {
            CHECK_USER.setString(1, login)
            CHECK_USER.setString(2, password)
            val resultSet = CHECK_USER.executeQuery()
            while (resultSet.next()) {
                return (resultSet.getInt("count") == 1)
            }
        } catch (e: SQLException) {
            throw e
        }
        return false
    }

    fun putOrganization(organization: Organization, issave: Boolean) {
        try {
            INSERT_ORGANIZATION.setString(1, organization.getName())
            INSERT_ORGANIZATION.setFloat(2, organization.getAnnualTurnover())
            INSERT_ORGANIZATION.setDouble(3, organization.getCoordinateX())
            INSERT_ORGANIZATION.setDouble(4, organization.getCoordinateY())
            INSERT_ORGANIZATION.setString(5, organization.getType())
            INSERT_ORGANIZATION.setString(6, organization.getStreet())
            INSERT_ORGANIZATION.setString(7, organization.getZipCode())
            INSERT_ORGANIZATION.setBoolean(8, issave)
            INSERT_ORGANIZATION.setString(9, organization.getOwner())
            INSERT_ORGANIZATION.execute()
        } catch (e: SQLException) {
            println(e.message)
            logger.log(Level.SEVERE, "Exception when save Organization")
        }
    }

    fun updateOrganization(organization: Organization) {
        try {
            UPDATE_ORGANIZATION.setString(1, organization.getName())
            UPDATE_ORGANIZATION.setFloat(2, organization.getAnnualTurnover())
            UPDATE_ORGANIZATION.setDouble(3, organization.getCoordinateX())
            UPDATE_ORGANIZATION.setDouble(4, organization.getCoordinateY())
            UPDATE_ORGANIZATION.setString(5, organization.getType())
            UPDATE_ORGANIZATION.setString(6, organization.getStreet())
            UPDATE_ORGANIZATION.setString(7, organization.getZipCode())
            UPDATE_ORGANIZATION.setBoolean(8, organization.isSave)
            UPDATE_ORGANIZATION.setString(9, organization.getOwner())
            UPDATE_ORGANIZATION.setLong(10, organization.getId())
            UPDATE_ORGANIZATION.execute()
        }catch (e: SQLException){
            println(e.message)
            logger.log(Level.SEVERE, "Exception when update Organization")
        }
    }

    fun getAllOrganizations(): Hashtable<String, Organization> {
        val checkModule = CheckModule()
        val listOfOrganizations = Hashtable<String, Organization>()
        val resultSet = SELECT_ALL_ORGANIZATIONS.executeQuery()
        while (resultSet.next()) {
            val id = resultSet.getLong("id")
            val organization = Organization(
                id,
                resultSet.getString("name"),
                resultSet.getFloat("annual_turnover"),
                Coordinates(resultSet.getDouble("x"),
                    resultSet.getDouble("y")),
                OrganizationType.valueOf((resultSet.getString("type"))),
                Address(resultSet.getString("street"),
                    resultSet.getString("zip_code")),
                resultSet.getBoolean("issave"),
                resultSet.getString("owner")
            )
            if (checkModule.check(organization)) {
                listOfOrganizations["$id"] = organization
            }
        }
        return listOfOrganizations
    }
}