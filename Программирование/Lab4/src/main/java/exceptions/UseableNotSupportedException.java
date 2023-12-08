package main.java.exceptions;

public class UseableNotSupportedException extends Exception{
    public UseableNotSupportedException(String name) {
        super("Exception: " + name + " не реализует интерфейс Useable.\n");
    }
}
