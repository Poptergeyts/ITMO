package commands

import workModuls.*

class CommandRegistration(
    private var workDatabaseHandler: DatabaseHandler,
    private var workTokenManager: TokenManager
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            val components = key.split(" ")
            val token = sha224(
                components[0].subSequence(0, 8).toString() + components[1].subSequence(0, 8).toString()
            ).subSequence(0, 16).toString()

            if (workTokenManager.getToken(token) == null && !workDatabaseHandler.checkUser(components[0], components[1])) {
                workTokenManager.createToken(token)
                answer.result = "Successfully registration. Your token is: $token"
                workDatabaseHandler.userRegistration(components[0], components[1])
            }
            else{
                answer.result="This account already exists"
            }
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}