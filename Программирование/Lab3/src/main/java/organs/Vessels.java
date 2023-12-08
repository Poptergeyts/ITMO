package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;

public class Vessels extends Organ{
    public Vessels(String owner) {
        super(owner, Condition.IS_PULSE, Method.IS_TOUCHABLE);
    }

    public int heartStopBeating() {
        if (condition == Condition.IS_PULSE) {
            condition = Condition.IS_NOT_PULSE;
            System.out.println("У " + getOwner() + " перестал прощупываться пульс.");
            return 0;
        }
        return 1;
    }

    public int heartStartBeating() {
        if (condition == Condition.IS_NOT_PULSE) {
            condition = Condition.IS_PULSE;
            System.out.println("У " + getOwner() + " снова прощупывается пульс.");
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Vessels of" + getOwner();
    }
}
