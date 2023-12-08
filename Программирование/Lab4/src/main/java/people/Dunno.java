package main.java.people;

import main.java.enums.Action;
import main.java.enums.Condition;
import main.java.exceptions.CharacterIsUnconsciousException;

public class Dunno extends Human {
    public Dunno(String name, int age) {
        super(name, age);
    }

    @Override
    public Human actionWithHands(Human human, Action action) {
        if (condition == Condition.UNCONSCIOUS) {
            throw new CharacterIsUnconsciousException(getName());
        }
        System.out.println(getName() + " больно ущипнул " + human.getName() + "а.");
        return this;
    }
}
