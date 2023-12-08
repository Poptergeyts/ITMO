package commands

import workModuls.Answer
import workModuls.Task
import workModuls.TokenManager

class CommandLogOut (
    private var workTokenManager: TokenManager,
) : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            workTokenManager.removeToken(key)
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            answer
        }
    }
}