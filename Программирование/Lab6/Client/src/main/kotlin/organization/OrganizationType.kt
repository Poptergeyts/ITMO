package organization
import java.io.Serializable as Serial

/**
 * Types of organizations.
 *
 * An enum class symbolizing a type of organization.
 */
enum class OrganizationType(private val value: String): Serial {
    COMMERCIAL("Commercial"),
    PUBLIC("Public"),
    TRUST("Trust"),
    PRIVATE_LIMITED_COMPANY("Private limited company"),
    OPEN_JOINT_STOCK_COMPANY("Open joint stock company");

    /**
     * Overrided to output information about the type of organization.
     */
    override fun toString(): String {
        return value
    }
}