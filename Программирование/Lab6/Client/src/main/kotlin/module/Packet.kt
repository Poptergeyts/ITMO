package module

import authorization.User
import organization.Address
import organization.Organization
import java.io.Serializable

class Packet: Serializable {
    var message: String? = "Success\n----------\n"
    var userName: String
    var userPassword: String
    var command: String? = null
    var id: Long? = null
    var address: Address? = null
    var organization: Organization? = null
    var another: List<String>? = null

    constructor(user: User) {
        userName = user.getName()
        userPassword = user.getPassword()
    }

    constructor() {
        userName = "Admin"
        userPassword = "Admin"
    }

    private fun reset() {
        message = null
        command = null
        id = null
        address = null
        organization = null
        another = null
    }

    fun createPacket(_command: String, _id: Long): Packet {
        reset()
        command = _command
        id = _id
        return this
    }

    fun createPacket(_command: String, _organization: Organization): Packet {
        reset()
        command = _command
        organization = _organization
        return this
    }

    fun createPacket(_command: String, _id: Long, _organization: Organization): Packet {
        reset()
        command = _command
        id = _id
        organization = _organization
        return this
    }

    fun createPacket(_command: String, _address: Address): Packet {
        reset()
        command = _command
        address = _address
        return this
    }

    fun createPacket(_command: String, _another: List<String>): Packet {
        reset()
        command = _command
        another = _another
        return this
    }
}