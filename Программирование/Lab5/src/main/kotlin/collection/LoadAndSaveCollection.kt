package collection

import organization.Organization
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.system.exitProcess

/**
 * Function for loading a [VectorOfOrganizations] from a file.
 *
 * @param [path] The path to the file from which you want to load a [VectorOfOrganizations].
 */
internal fun loadVectorFromJson(path: String) : VectorOfOrganizations{
    if (path == "NotFound") throw RuntimeException("ERROR: The file with the collection was not found.")

    val file = File(path)
    if (!file.canRead()) throw RuntimeException("ERROR: The file with the collection is not readable.")

    val collection = VectorOfOrganizations()
    if (file.length() != 0L) {
        try {
            val sc = Scanner(file)
            for (org in Json.decodeFromString<List<Organization>>(sc.nextLine())) {
                collection.add(org)
            }
            collection.validation()
            collection.setID()
        } catch (ex: Exception) {
            println("ERROR: The file with the collection is corrupted.")
            println(ex.message)
            exitProcess(1)
        }
    }
    return collection
}

/**
 * Function for saving a [VectorOfOrganizations] to a file.
 *
 * @param [collection] [VectorOfOrganizations]  to upload to the file.
 * @param [path] The path to the file you want to upload the [VectorOfOrganizations].
 */
internal fun loadVectorToJson(collection: VectorOfOrganizations, path: String) : Int{
    val col: List<Organization> = collection
    try {
        val bw = BufferedWriter(FileWriter(path))
        bw.write(Json.encodeToString(col))
        bw.flush()
        println("Saving was successful.")
        return 0
    } catch (ex: Exception) {
        println("WARNING: Failed to save collection to file.")
        return 1
    }
}