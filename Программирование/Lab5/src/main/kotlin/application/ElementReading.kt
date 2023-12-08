package application

import organization.*

internal fun enterAddress() : Address {
    print("Enter the organization's address (street name and zip code (if any) separated by commas): ")
    val enter: String = readLine()?: ""
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

internal fun enterOrganization() : Organization {
    var enter: String
    fun enterName() : String {
        print("Enter the name of the organization: ")
        enter = readLine()?: ""
        if (enter.trim() == "") {
            println("WARNING: The name of the organization cannot be empty, repeat again.")
            return enterName()
        }
        return enter.trim()
    }

    fun enterCoordinates() : Coordinates {
        print("Enter the X and Y coordinates separated by a space: ")
        enter = readLine()?: ""
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

    fun enterAnnualTurnover() : Float {
        print("Enter the annual turnover of the organization: ")
        enter = readLine()?: ""
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

    fun enterType() : OrganizationType {
        print("Enter the type of organization" +
                " (commercial, public, trust, private_limited_company, open_joint_stock_company): ")
        enter = readLine()?: ""
        return when(enter.trim()) {
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
    return Organization(enterName(), enterCoordinates(), enterAnnualTurnover(), enterType(), enterAddress())
}