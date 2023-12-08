package authorization

class User {
    private var name = "Server"
    private var password = "Server"

    fun getName(): String {
        return name
    }

    fun getPassword(): String {
        return password
    }
}
