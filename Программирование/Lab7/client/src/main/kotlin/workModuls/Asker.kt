package workModuls

import org.*
import organization.Address
import organization.Coordinates
import organization.Organization
import organization.OrganizationType
import java.util.function.Predicate

typealias TypeCaster<T> = (userInput: String) -> T

class Asker {
    private val toLongCaster: TypeCaster<Long> = { it.trim().toLong() }
    private val toFloatCaster: TypeCaster<Float> = { it.trim().toFloat()}
    private val toDoubleCaster: TypeCaster<Double> = { it.trim().toDouble()}

    private inline fun <reified T : Enum<T>> toEnumCaster(userInput: String): T {
        return enumValueOf(userInput.trim().uppercase())
    }

    private fun <T> readType(caster: TypeCaster<T>, validator: Predicate<T>): T {
        var output: T
        while (true) {
            try {
                val userInput = readln()
                output = caster(userInput)
            } catch (e: Exception) {
                when (e) {
                    is NumberFormatException -> {
                        println("Incorrect number input. Try again")
                        continue
                    }

                    is IllegalArgumentException -> {
                        println("Incorrect input. Try again")
                        continue
                    }

                    else -> {
                        println("Incorrect input. Try again")
                        continue
                    }
                }
            }
            if (validator.test(output)) {
                break
            } else {
                println("Incorrect input. Try again")
            }
        }
        return output
    }

    private fun askAddress(): Address {
        println("Enter street name")
        val streetName = readType(caster = { it }, validator = { it.isNotEmpty() })
        println("Enter zip code or nothing.")
        val zipCode = readType(caster = {it}, validator = { true })
        return Address(
            streetName,
            zipCode
        )
    }

    fun askStreet(): Organization {
        println("Enter street name")
        val streetName = readType(caster = { it }, validator = { it.isNotEmpty() })
        return Organization(
            0L,
            "None",
            0F,
            Coordinates(0.0, 0.0),
            OrganizationType.PUBLIC,
            Address(streetName, "0"),
            false
        )
    }

    private fun askCoordinates(): Coordinates {
        println("Enter coordinate by х")
        val x = readType(caster = toDoubleCaster, validator = { true })
        println("Enter coordinate by у")
        val y = readType(caster = toDoubleCaster, validator = { it < 498 })
        return Coordinates(x, y)
    }

    fun askOrganization(): Organization {
        println("Enter the name of the organization.")
        val name = readType(caster = { it }, validator = { it.isNotEmpty() })
        val coordinates = askCoordinates()
        println("Enter the annual turnover of the organization.")
        val annualTurnover = readType(caster = toFloatCaster, validator = { it >= 0 })
        println("Enter type of organization ${OrganizationType.values().map { it.toString() }}.")
        val organizationType = readType(caster = { toEnumCaster<OrganizationType>(it) }, validator = { true })
        return Organization(
            0L,
            name,
            annualTurnover,
            coordinates,
            organizationType,
            askAddress(),
            false
        )
    }

    fun askCommand(): String {
        return readType(caster = { it }, validator = { it.isNotEmpty() })
    }

    fun askLong(): Long {
        return readType(caster = toLongCaster, validator = { it > 0 })
    }

    fun askLoginAndPasswordForRegistration(): String {
        return readType(caster = { it }, validator = { it.isNotEmpty() && it.matches(Regex("[a-zA-Z0-9]{8,}")) })
    }
}