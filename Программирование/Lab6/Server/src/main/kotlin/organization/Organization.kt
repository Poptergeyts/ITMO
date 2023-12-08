package organization

import kotlinx.serialization.Serializable
import java.io.Serializable as Serial
import java.time.LocalDate

/**
 *Organization.
 *
 * A class symbolizing an organization with its [address][Address], [coordinates][Coordinates], and [type][OrganizationType].
 */
@Serializable
class Organization: Comparable<Organization>, Serial{
    companion object {
        var ID: Long = 0

        fun initOrganization(): Organization {
            var enter: String
            fun enterName(): String {
                print("Enter the name of the organization: ")
                enter = readLine() ?: ""
                if (enter.trim() == "") {
                    println("WARNING: The name of the organization cannot be empty, repeat again.")
                    return enterName()
                }
                return enter.trim()
            }

            fun enterCoordinates(): Coordinates {
                print("Enter the X and Y coordinates separated by a space: ")
                enter = readLine() ?: ""
                enter = enter.replace("\\s+".toRegex(), " ")
                return try {
                    val coordinates = enter.trim().split(" ")
                    if (coordinates.size != 2)
                        throw RuntimeException("Incorrect quantity")
                    Coordinates(coordinates[0].toDouble(), coordinates[1].toDouble())
                } catch (re: RuntimeException) {
                    when (re.message) {
                        "Incorrect quantity" -> println("WARNING: The wrong number of arguments was entered, repeat again.")
                        "Greater than 498" -> println("WARNING: The Y coordinate should be no more than 498.")
                        else -> println("WARNING: The entered coordinates are incorrect, repeat again.")
                    }
                    enterCoordinates()
                }
            }

            fun enterAnnualTurnover(): Float {
                print("Enter the annual turnover of the organization: ")
                enter = readLine() ?: ""
                return try {
                    val at = enter.toFloat()
                    if (at < 0)
                        throw RuntimeException("Negative annualTurnover")
                    at
                } catch (re: RuntimeException) {
                    if (re.message == "Negative annualTurnover")
                        println("WARNING: The annual turnover of the organization should be positive.")
                    else println("WARNING: The entered annual turnover of the organization is incorrect, repeat again.")
                    enterAnnualTurnover()
                }
            }

            fun enterType(): OrganizationType {
                print(
                    "Enter the type of organization" +
                            " (commercial, public, trust, private_limited_company, open_joint_stock_company): "
                )
                enter = readLine() ?: ""
                return when (enter.trim()) {
                    "commercial" -> OrganizationType.COMMERCIAL
                    "public" -> OrganizationType.PUBLIC
                    "trust" -> OrganizationType.TRUST
                    "private_limited_company" -> OrganizationType.PRIVATE_LIMITED_COMPANY
                    "open_joint_stock_company" -> OrganizationType.OPEN_JOINT_STOCK_COMPANY
                    else -> {
                        println("WARNING: The entered organization type is incorrect, repeat again.")
                        enterType()
                    }
                }
            }

            fun enterAddress() : Address {
                print("Enter the organization's address (street name and zip code (if any) separated by commas): ")
                enter = readLine()?: ""
                val ad = enter.split(",")
                if (ad[0] == "") {
                    println("WARNING: The street name cannot be empty.")
                    return enterAddress()
                }
                return when(ad.size) {
                    1 -> Address(ad[0].trim())
                    2 -> Address(ad[0].trim(), ad[1].trim())
                    else -> {
                        println("WARNING: The wrong number of arguments was entered, repeat again.")
                        enterAddress()
                    }
                }
            }
            return Organization(enterName(), enterCoordinates(), enterAnnualTurnover(), enterType(), enterAddress())

        }
    }

    private var id = ++ID
    var name: String
    private var annualTurnover: Float
    var coordinates: Coordinates
    var type: OrganizationType
    var officialAddress: Address
    var creationDate: String

    constructor(_id: Long, _name: String, _coordinates: Coordinates, _at: Float, _type: OrganizationType, _address: Address) {
        ID = _id
        id = _id
        name = _name
        annualTurnover = _at
        coordinates = _coordinates
        type = _type
        officialAddress = _address
        creationDate = LocalDate.now().toString()
    }

    constructor(_name: String, _coordinates: Coordinates, _at: Float, _type: OrganizationType, _address: Address) {
        name = _name
        annualTurnover = _at
        coordinates = _coordinates
        type = _type
        officialAddress = _address
        creationDate = LocalDate.now().toString()
    }

    /**
     * Checks the validity of the date of creation of the organization.
     */
    fun dataCheck() : Int {
        name = name.trim()
        if (name == "")
            throw RuntimeException("ERROR: Organization name {id: $id} can't be empty.")
        if(annualTurnover < 0)
            throw RuntimeException("ERROR: Annual turnover of the organization {id: $id} must be positive.")
        officialAddress.addressCheck(id)
        return 0
    }

    /**     * Updating all organization data.
     */
    fun update(org: Organization) : Int {
        --ID
        name = org.name
        annualTurnover = org.annualTurnover
        coordinates = org.coordinates
        type = org.type
        officialAddress = org.officialAddress
        creationDate = org.creationDate
        return 0
    }

    /**
     * @suppress
     */
    fun getID() : Long {
        return id
    }

    fun setID(_id: Long) {
        id = _id
        ID = id + 1
    }

    /**
     * Get the organization's annual turnover.
     */
    fun getAnnualTurnover() : Float {
        return annualTurnover
    }

    /**
     * Get the organization's official address.
     */
    fun getAddress() : Address {
        return officialAddress
    }

    /**
     * Get the organization's creation date.
     */
    fun getDate() : String {
        return creationDate
    }

    /**
     * Overrided to output information about the organization.
     */
    override fun toString() : String {
        return """
              - id: $id, name: $name, coordinates: $coordinates, creation date: $creationDate, annual turnover: $annualTurnover, 
                type of organization: $type, official address: $officialAddress
        """.trimIndent()
    }

    /**
     * @suppress
     */
    override fun compareTo(other: Organization): Int {
        return this.id.toInt() - other.id.toInt()
    }
}