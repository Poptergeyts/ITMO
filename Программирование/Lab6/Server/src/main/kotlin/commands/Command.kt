package commands

import collection.VectorOfOrganizations
import module.Packet
import java.sql.Connection

abstract class Command(private val name: String, private val info: String) {
    abstract fun execute(packet: Packet, collection: VectorOfOrganizations,
                         commands: MutableMap<String, Command>, connection: Connection): Packet

    fun getName() = name
    fun getInfo() = info
}