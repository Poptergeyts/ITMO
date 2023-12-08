package organization
import java.io.Serializable as Serial

/**
 * Types of organizations.
 *
 * An enum class symbolizing a type of organization.
 */
enum class OrganizationType(private val value: String): Serial {
    COMMERCIAL("COMMERCIAL"),
    PUBLIC("PUBLIC"),
    TRUST("TRUST"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY"),
    OPEN_JOINT_STOCK_COMPANY("OPEN_JOINT_STOCK_COMPANY");

    /**
     * Overrided to output information about the type of organization.
     */
    override fun toString(): String {
        return value
    }
}