package collections

import organization.Organization
import workModuls.DatabaseHandler
import java.util.*
import java.util.concurrent.locks.ReentrantLock

class Collection {
    var collection = Hashtable<String, Organization>()
    private val lock = ReentrantLock()

    fun add(organization: Organization, databaseHandler: DatabaseHandler) {
        lock.lock()
        try {
            val id = getId()
            organization.setID(id)
            databaseHandler.putOrganization(organization, organization.isSave)
        } finally {
            lock.unlock()
        }
    }

    fun update(organization: Organization, databaseHandler: DatabaseHandler) {
        lock.lock()
        try {
            databaseHandler.updateOrganization(organization)
        } finally {
            lock.unlock()
        }
    }

    fun remove(key: String, databaseHandler: DatabaseHandler) {
        lock.lock()
        try {
            collection[key]?.let { databaseHandler.doOrganizationNotSave(it.getId()) }
            collection.remove(key)
        } finally {
            lock.unlock()
        }
    }

    private fun getId(): Long {
        lock.lock()
        var id = 0L
        try {
            id = collection.values.max().getId() + 1
        } catch (_: RuntimeException) {
        } finally {
            lock.unlock()
        }
        return id
    }
}