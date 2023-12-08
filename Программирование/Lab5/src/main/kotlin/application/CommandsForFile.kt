package application

import organization.*
import java.util.Scanner

internal fun addFromFile(scanner: Scanner) : Int {
    try {
        collection.add(organizationFromFile(scanner))
        println("The new item has been successfully added to the collection.")
        return 0
    } catch (ex: Exception) {
        println("WARNING: Error in the script.")
        return 2
    }
}

internal fun addFromFileIfMax(scanner: Scanner) : Int {
    try {
        val organization = organizationFromFile(scanner)
        val annualTurnover = organization.getAnnualTurnover()
        for (org in collection) {
            if (annualTurnover > org.getAnnualTurnover()) {
                collection.add(organization)
                println(
                    "The value of the element is greater than the value of the largest element in this collection.\n" +
                            "The new item has been successfully added to the collection.")
                return 0
            }
        }
        println("The value of the element does not exceed the value of the largest element of this collection.\n" +
                "The new item has not been added to the collection.")
        return 1
    } catch (ex: Exception) {
        println("WARNING: Error in the script.")
        return 2
    }
}

internal fun updateFromFile(scanner: Scanner, id: Long) : Int{
    try {
        for (org in collection) {
            if (org.getID() == id) {
                org.update(organizationFromFile(scanner))
                println("Организация успешно обновлена.")
                return 0
            }
        }
        println("WARNING: The organization with this id is not in the collection.")
        return 1
    } catch (re: RuntimeException) {
        println("WARNING: Error in the script.")
        return 2
    }
}

internal fun removeAnyByOfficialAddressFromFile(scanner: Scanner): Int {
    try {
        val address = addressFromFile(scanner)
        for (org in collection) {
            if (org.getAddress().toString() == address.toString())
            {
                collection.remove(org)
                println("The organization was successfully deleted.")
                return 0
            }
        }
        println("WARNING: The organization with this address is not in the collection.")
        return 1
    } catch (ex: Exception) {
        println("WARNING: Error in the script.")
        return 2
    }
}

internal fun filterByOfficialAddressFromFile(scanner: Scanner): Int {
    try {
        val address = addressFromFile(scanner)
        var wasFound = false
        for (org in collection) {
            if (org.getAddress().toString() == address.toString()) {
                println(org.toString())
                wasFound = true
            }
        }
        if (!wasFound) {
            println("WARNING: There are no organizations with this address in the collection.")
            return 1
        }
        return 0
    } catch (ex: Exception) {
        println("WARNING: Error in the script.")
        return 2
    }
}

internal fun addressFromFile(scanner: Scanner) : Address {
    val ad = scanner.nextLine().split(", ")
    val address = when(ad.size) {
        1 -> Address(ad[0].trim())
        2 -> Address(ad[0].trim(), ad[1].trim())
        else -> throw RuntimeException()
    }
    return address
}

internal fun organizationFromFile(scanner: Scanner) : Organization{
    val name = scanner.nextLine()
    val coords = scanner.nextLine()
    val coordinates = coords.replace("\\s+".toRegex(), " ").trim().split(" ")
    val at = scanner.nextLine().trim().toFloat()
    val type = when(scanner.nextLine().trim()) {
        "commercial" -> OrganizationType.COMMERCIAL
        "public" -> OrganizationType.PUBLIC
        "trust" -> OrganizationType.TRUST
        "private_limited_company" -> OrganizationType.PRIVATE_LIMITED_COMPANY
        "open_joint_stock_company" -> OrganizationType.OPEN_JOINT_STOCK_COMPANY
        else -> throw RuntimeException()
    }
    return Organization(name, Coordinates(coordinates[0].toDouble(),coordinates[1].toDouble()),
        at, type, addressFromFile(scanner))
}