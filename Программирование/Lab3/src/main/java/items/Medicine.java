package main.java.items;

import main.java.enums.Method;
import main.java.people.Human;

@FunctionalInterface
public interface Medicine {
    int use(Human human, Method method);

    public String toString();
}
