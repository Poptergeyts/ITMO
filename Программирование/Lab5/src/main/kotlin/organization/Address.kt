package organization

import kotlinx.serialization.Serializable

/**
 * Address.
 *
 * A class symbolizing an address.
 */
@Serializable
class Address {
    private var street: String
    private val zipCode: String?

    constructor(_street: String, _zipCode: String? = null){
        street = if(_street.trim() != "") _street
            else throw RuntimeException("ERROR: The street name cannot be empty.")
        zipCode = _zipCode
    }

    /**
     * Checks the validity of the address.
     */
    fun addressCheck(id: Long) : Int {
        street = street.trim()
        if (street == "")
            throw RuntimeException("ERROR: Organization street {id: $id} can't be empty.")
        return 0
    }

    /**
     * Overrided to output address.
     */
    override fun toString(): String {
        val zip = zipCode ?: "missing"
        return "{street: $street, zip code: $zip}"
    }
}