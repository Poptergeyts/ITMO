package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;
import main.java.people.Human;

public class Eyes extends Organ {
    public Eyes(String owner) {
        super(owner, Condition.EYES_IS_OPEN, Method.IS_VISUAL);
    }

    public int closeEyes() {
        if (condition == Condition.EYES_IS_OPEN) {
            condition = Condition.EYES_IS_CLOSE;
            System.out.println(getOwner() + " закрыл глаза.");
        return 0;
        }
        return 1;
    }

    public int openEyes() {
        if (condition == Condition.EYES_IS_CLOSE) {
            condition = Condition.EYES_IS_OPEN;
            System.out.println(getOwner() + " открыл глаза.");
            return 0;
        }
        return 1;
    }

    public Condition look(Human human) {
        return human.getOrgansCondition(Method.IS_VISUAL);
    }

    @Override
    public String toString() {
        return "Eyes of" + getOwner();
    }
}
