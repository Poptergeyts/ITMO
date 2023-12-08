package workModuls

import organization.Organization
import java.io.Serializable

data class Task(
    val describe: MutableList<String>,
    var organization: Organization? = null,
    val listOfCommands: MutableList<String>,
    var login: String = "",
    var token: String = "",
) : Serializable {}