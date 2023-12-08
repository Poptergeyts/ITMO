package main.java.people;

import main.java.enums.Action;
import main.java.enums.Condition;

public class Dunno extends Human {
    public Dunno(String name) {
        super(name);
    }

    @Override
    public Human actionWithHands(Human human, Action action) {
        if (condition == Condition.CONSCIOUS) {
            System.out.println(getName() + " больно ущипнул " + human.getName() + "а.");
        }
        return this;
    }
}
