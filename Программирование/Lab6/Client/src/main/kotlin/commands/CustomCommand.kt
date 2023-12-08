package commands

class CustomCommand (_name: String, private val args_number: Int)
    : Command(_name, "it's your custom command", args_number) {

}