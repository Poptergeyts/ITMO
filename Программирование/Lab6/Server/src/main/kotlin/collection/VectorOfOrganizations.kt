package collection

import kotlinx.serialization.Serializable
import organization.Organization
import java.time.LocalDate
import java.util.*

/**
 * Vector of organizations.
 *
 * A vector-type collection class for storing and working with objects of [Organization].
 */
@Serializable
class VectorOfOrganizations() : Vector<Organization>(0) {

    /**
     * Checks the validity of objects of [Organization] stored in the collection.
     */
    fun validation() : Int {
        for (org in this) org.dataCheck()
        return 0
    }

    /**
     * Sets a unique id for according to the last added object of [Organization] added to the collection.
     */
    fun setID() : Int {
        var id = 0L
        for (org in this) id = kotlin.math.max(id, org.getID())
        Organization.ID = id
        return 0
    }

    fun getID(): Long {
        var id = 0L
        for (org in this) id = kotlin.math.max(id, org.getID())
        return id
    }

    /**
     * Overrided to output information about a stored object of [Organization].
     */
    override fun toString(): String {
        var date: LocalDate = LocalDate.now()
        for (org in this)
        {
            if (date > LocalDate.parse(org.getDate())) date = LocalDate.parse(org.getDate())
        }
        return """
            Collection Type: Vector,
            Initialization date: $date,
            Number of elements: ${this.size}.
        """.trimIndent()
    }
}