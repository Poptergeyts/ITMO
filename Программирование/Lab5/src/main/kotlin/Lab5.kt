import kotlin.system.exitProcess

/**
 * @suppress
 */
fun main() {
    System.setProperty("file.encoding", "UTF-8")
    try {
        val path = System.getenv("LAB")?: "NotFound"
        application.console(path)
    } catch (ex: Exception) {
        println("ERROR: An unexpected error has occurred.")
        exitProcess(1)
    }
}