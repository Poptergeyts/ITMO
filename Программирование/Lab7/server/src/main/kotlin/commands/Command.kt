package commands

import workModuls.Answer
import workModuls.Task

abstract class Command {
    abstract fun commandDo(key: String, task: Task): Answer
}