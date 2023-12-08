package application

internal fun removeByID(id: Long): Int {
    for (org in collection) {
        if (org.getID() == id) {
            collection.remove(org)
            println("The organization was successfully removed from the collection.")
            return 0
        }
    }
    println("WARNING: The organization with this id is not in the collection.")
    return 1
}

internal fun update(id: Long): Int {
    for (org in collection) {
        if (org.getID() == id) {
            org.update(enterOrganization())
            println("The organization has been successfully updated.")
            return 0
        }
    }
    println("WARNING: The organization with this id is not in the collection.")
    return 1
}

internal fun removeAnyByOfficialAddress(): Int {
    val address = enterAddress()
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
}

internal fun filterByOfficialAddress(): Int {
    var wasFound = false
    val address = enterAddress()
    for (org in collection) {
        if (org.getAddress().toString() == address.toString()) {
            wasFound = true
            println(org.toString())
        }
    }
    if (!wasFound) {
        println("WARNING: There are no organizations with this address in the collection.")
        return 1
    }
    return 0
}