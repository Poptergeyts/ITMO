package module

import authorization.User
import organization.Address
import organization.Organization

class Launcher() {
    private val vectorOfCommands = loadCommands()

    fun checkCommand(enterList: List<String>): Boolean {
        val command = vectorOfCommands[enterList[0]]
        if (command != null) {
            if (command.checkValidation(enterList)) {
                return true
            } else {
                println("WARNING: An argument is not specified.")
            }
        } else {
            println("WARNING: A nonexistent command is entered.")
        }
        return false
    }

    fun packageCommand(enterList: List<String>, user: User): Packet {
        val packet = Packet(user)
        if (enterList[0] == "add") {
            packet.createPacket(enterList[0], Organization.initOrganization())
        } else if (enterList[0] == "update") {
            packet.createPacket(enterList[0], enterList[1].toLong(), Organization.initOrganization())
        } else if (enterList[0] == "add_if_max") {
            packet.createPacket(enterList[0], Organization.initOrganization())
        } else if (enterList[0] == "filter_by_official_address") {
            packet.createPacket(enterList[0], Address.initAddress())
        } else if (enterList[0] == "remove_any_by_official_address") {
            packet.createPacket(enterList[0], Address.initAddress())
        } else if (enterList[0] == "remove_by_id") {
            packet.createPacket(enterList[0], enterList[1].toLong())
        } else if (enterList[0] == "add_new_command") {
            vectorOfCommands["add_new_command"]?.execute(enterList, vectorOfCommands)
            packet.createPacket(enterList[0], enterList)
        } else {
            packet.createPacket(enterList[0], enterList)
        }
        return packet
    }
}
