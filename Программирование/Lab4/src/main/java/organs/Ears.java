package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;
import main.java.people.Human;

public class Ears extends Organ {
    public Ears(String owner) {
        super(owner);
    }

    public Condition listen(Human human) {
        return human.getOrgansCondition(Method.IS_AUDIBLE);
    }

    @Override
    public String toString() {
        return "Ears of" + getOwner();
    }
}
