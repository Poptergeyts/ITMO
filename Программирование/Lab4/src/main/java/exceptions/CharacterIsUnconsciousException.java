package main.java.exceptions;

public class CharacterIsUnconsciousException extends RuntimeException{
    public CharacterIsUnconsciousException(String name) {
        super("Runtime Exception: " + name + " без сознания.\n");
    }
}
