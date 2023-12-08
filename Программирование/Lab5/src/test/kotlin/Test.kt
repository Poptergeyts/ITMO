import collection.VectorOfOrganizations
import io.mockk.*
import io.mockk.mockkClass
import org.junit.jupiter.api.Test
import organization.Address
import organization.Coordinates
import organization.Organization
import organization.OrganizationType
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class Tests {
    private val org = Organization("Test", Coordinates(1.0, 1.0), 1F, OrganizationType.PUBLIC, Address("Test"))

    private val org1 = mockkClass(Organization::class)
    private val org2 = mockkClass(Organization::class)
    private val org3 = mockkClass(Organization::class)

    private val vecOrganization = VectorOfOrganizations()

    @BeforeTest
    fun before() {
        every { org1.dataCheck() } returns 0
        every { org2.dataCheck() } returns 0
        every { org3.dataCheck() } returns 0

        every {org1.update(org2)} returns 0

        vecOrganization.add(org1)
        vecOrganization.add(org2)
        vecOrganization.add(org3)
    }

    @Test
    fun testGetAT() {
        assertEquals(1.0F, org.getAnnualTurnover(), "Fail")
    }

    @Test
    fun testData() {
        assertEquals(0, org.dataCheck())
    }

    @Test
    fun testCollection() {
        assertEquals(0, vecOrganization.validation())
    }

    @Test
    fun testUpdateOrgM() {
        assertEquals(0, org1.update(org2))
    }

    @Test
    fun testUpdateOrg() {
        assertEquals(0, org.update(Organization("Test", Coordinates(1.0, 1.0), 1F, OrganizationType.PUBLIC, Address("Test"))))
    }
}