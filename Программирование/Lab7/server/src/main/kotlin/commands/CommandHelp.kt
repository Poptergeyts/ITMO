package commands

import workModuls.Answer
import workModuls.Task
import java.lang.RuntimeException

class CommandHelp : Command() {
    override fun commandDo(key: String, task: Task): Answer {
        val answer= Answer()
        try {
            answer.result = ("""
    help : get help
    info : display information about the collection on the screen
    show : display all elements of the collection on the screen
    sum_of_AT : display sum of annual turnover 
    filter_by_address : display all of the collection with this address
    add : add a new element to the collection
    add_if_max : add a new element if AT is max
    update : update the organization
    remove {id} : remove an item from the collection by its id
    remove_last : remove last item from the collection
    clear : remove your organizations
    execute_script {path to file} : execute script from file 
    exit : end the program (without saving)
                """.trim())
            return answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception"
            return answer
        }
    }
}