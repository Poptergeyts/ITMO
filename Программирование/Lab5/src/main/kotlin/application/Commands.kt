package application

import collection.loadVectorToJson
import kotlin.system.exitProcess

/**
 * check
 */
internal fun help(): Int {
    println("""
        - info : display information about the collection on the screen.
        - show : display all the elements of the collection on the screen.
        - add {element} : add a new element to the collection.
        - update id {element} : update the value of the collection element, 
          whose id is equal to the specified one.
        - remove_by_id id : remove an item from the collection by its id.
        - clear : clear the collection.
        - save :save the collection to a file.
        - execute_script file_name : read and execute the script from the specified file.
        - exit : terminate the program (without saving to a file).
        - remove_last : remove the last item from the collection.
        - add_if_max {element} : add a new element to the collection if 
          its value exceeds the value of the largest element in this collection.
        - shuffle: shuffle the collection items in random order.
        - remove_any_by_official_address officialAddress : remove one item from the collection
          whose officialAddress field value is equivalent to the specified one.
        - sum_of_annual_turnover : output the sum of the values of the annualTurnover field 
          for all elements of the collection.
        - filter_by_official_address officialAddress : output elements
          whose officialAddress field value is equal to the specified one.
        Attention: {element} is entered from a new line.
    """.trimIndent())
    return 0
}

internal fun info() : Int {
    println(collection.toString())
    return 0
}

internal fun show() : Int {
    if (collection.size != 0) {
        for (org in collection) {
            println(org.toString())
        }
        return 0
    }
    println("The collection is empty.")
    return 1
}

internal fun clear() : Int {
    if (collection.size != 0) {
        collection.clear()
        println("The collection has been cleared.")
        return 0
    }
    println("The collection is empty.")
    return 1
}

internal fun save() : Int {
    return loadVectorToJson(collection, filePath)
}

internal fun exit(status: Int) {
    if (status == 0) {
        println("The program is completed without saving the collection to a file.")
    }
    else {
        println("The program was forcibly stopped")
    }
    exitProcess(status)
}

internal fun removeLast() : Int {
    if (collection.size != 0) {
        collection.removeLast()
        println("The last item in the collection has been deleted.")
        return 0
    }
    println("The collection is empty.")
    return 1
}

internal fun shuffle() : Int {
    if (collection.size != 0) {
        collection.shuffle()
        println("The collection is mixed.")
        return 0
    }
    println("The collection is empty.")
    return 1
}

internal fun sumOfAnnualTurnover() : Int{
    if (collection.size != 0) {
        var sum = 0F
        for (org in collection) {
            sum += org.getAnnualTurnover()
        }
        println("The sum of the annual turnover is equal to $sum.")
        return 0
    }
    println("The collection is empty.")
    return 1
}