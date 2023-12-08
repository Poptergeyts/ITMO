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
data class Organization(
    private var id: Long = 0,
    private val name: String = "",
    private val annualTurnover: Float,
    private val coordinates: Coordinates,
    private val type: OrganizationType,
    private val officialAddress: Address,
    var isSave: Boolean = false,
    private var owner: String = ""): Comparable<Organization>, Serial{
    private val creationDate: String

    init {
        creationDate = LocalDate.now().toString()
    }

    fun setID(_id: Long) {
        id = _id
    }

    fun getName(): String {
        return name
    }

    fun getAnnualTurnover(): Float {
        return annualTurnover
    }

    fun getCoordinateX():Double {
        return coordinates.x
    }

    fun getCoordinateY():Double {
        return coordinates.y
    }

    fun getType(): String{
        return type.toString()
    }

    fun getStreet(): String {
        return officialAddress.street
    }

    fun getZipCode(): String? {
        return officialAddress.zipCode
    }

    fun getOwner(): String {
        return owner
    }

    fun setOwner(workOwner: String) {
        owner = workOwner
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

    fun getId(): Long {
        return id
    }
}