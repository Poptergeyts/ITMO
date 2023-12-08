package workModuls

import organization.Organization

class CheckModule {
    fun check(organization: Organization): Boolean {
        return organization.getCoordinateY() < 498 && organization.getAnnualTurnover() > 0F
    }
}