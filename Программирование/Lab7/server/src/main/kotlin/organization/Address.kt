package organization

import kotlinx.serialization.Serializable
import java.io.Serializable as Serial

/**
 * Address.
 *
 * A class symbolizing an address.
 */
@Serializable
class Address: Serial {
    var street: String
    val zipCode: String?

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

    companion object {
        fun initAddress(): Address {
            print("Enter the organization's address (street name and zip code (if any) separated by commas): ")
            val enter: String = readLine()?: ""
            val ad = enter.split(",")
            if (ad[0] == "") {
                println("WARNING: The street name cannot be empty.")
                return initAddress()
            }
            return when(ad.size) {
                1 -> Address(ad[0].trim())
                2 -> Address(ad[0].trim(), ad[1].trim())
                else -> {
                    println("WARNING: The wrong number of arguments was entered, repeat again.")
                    initAddress()
                }
            }
        }
    }
}