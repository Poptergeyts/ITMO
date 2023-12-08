package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;

public class Heart extends Organ {
    private Vessels vessels;
    private Eyes eyes;

    public Heart(String owner, Vessels vessels, Eyes eyes) {
        super(owner, Condition.HEART_IS_BEATING, Method.IS_AUDIBLE);
        this.vessels = vessels;
        this.eyes = eyes;
    }

    public int stopBeating() {
        if (condition == Condition.HEART_IS_BEATING) {
            condition = Condition.HEART_IS_NOT_BEATING;
            System.out.println("У " + getOwner() + " остановилось сердце.");
            vessels.heartStopBeating();
            eyes.closeEyes();
            return 0;
        }
        return 1;
    }

    public int startBeating() {
        if (condition == Condition.HEART_IS_NOT_BEATING) {
            condition = Condition.HEART_IS_BEATING;
            System.out.println("У " + getOwner() + " забилось сердце.");
            vessels.heartStartBeating();
            eyes.openEyes();
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Heart of" + getOwner();
    }
}
