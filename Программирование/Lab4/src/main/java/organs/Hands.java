package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;
import main.java.people.Human;

public class Hands extends Organ {
    public Hands(String owner) {
        super(owner);
    }

    public Condition touch(Human human) {
        return human.getOrgansCondition(Method.IS_TOUCHABLE);
    }

    @Override
    public String toString() {
        return "Hands of" + getOwner();
    }
}
