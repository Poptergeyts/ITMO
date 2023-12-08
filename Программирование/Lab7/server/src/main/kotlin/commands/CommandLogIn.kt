package commands

import workModuls.*

class CommandLogIn(
    private var workDatabaseHandler: DatabaseHandler,
    private var workTokenManager: TokenManager,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            val components = key.split(" ")
            val token = sha224(
                components[0].subSequence(0, 8).toString() +
                        components[1].subSequence(0, 8).toString()).subSequence(0, 16).toString()

            if (!workDatabaseHandler.checkUser(components[0], components[1])) {
                answer.result = "Wrong password or login"
            }
            else{
                if (workTokenManager.getToken(token)!=null){
                    answer.result = "This account already used"
                }
                else {
                    workTokenManager.createToken(token)
                    answer.result = "Successfully log in. Your token is: $token"
                }
            }
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}