package organization

import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 *Organization.
 *
 * A class symbolizing an organization with its [address][Address], [coordinates][Coordinates], and [type][OrganizationType].
 */
@Serializable
class Organization: Comparable<Organization>{
    private val id: Long = ++unique_ID
    private var name: String
    private var annualTurnover: Float
    private var coordinates: Coordinates
    private var type: OrganizationType
    private var officialAddress: Address
    private var creationDate: String

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

    /**
     * Updating all organization data.
     */
    fun update(org: Organization) : Int {
        setUniqueID(-1)
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