package main.java.people;

import main.java.enums.Action;
import main.java.enums.Condition;
import main.java.exceptions.CharacterIsUnconsciousException;

public class Doctor extends Human {
    public Doctor(String name, int age) {
        super(name, age);
    }

    @Override
    public Human listen(Human human) {
        if (condition == Condition.UNCONSCIOUS) {
            throw new CharacterIsUnconsciousException(getName());
        }
        Condition was_heard = ears.listen(human);
        if (was_heard != Condition.NONE) {
            System.out.println(getName() + " услышал, что у " + human.getName() + " " +
                    was_heard.getCondition() + ".\n");
        } else System.out.println(getName() + " запутался.\n");
        return this;
    }

    @Override
    public Human look(Human human) {
        if (condition == Condition.UNCONSCIOUS) {
            throw new CharacterIsUnconsciousException(getName());
        }
        Condition was_seen = eyes.look(human);
        if (was_seen != Condition.NONE) {
            System.out.println(getName() + " увидел, что у " + human.getName() + " " +
                    was_seen.getCondition() + ".\n");
        } else System.out.println(getName() + " запутался.\n");
        return this;
    }

    @Override
    public Human actionWithHands(Human human, Action action) {
        if (condition == Condition.UNCONSCIOUS) {
            throw new CharacterIsUnconsciousException(getName());
        }
        System.out.println(getName() + " " + action.getAction() + " " + human.getName() + ".\n");
        if (action == Action.TOUCH) {
            Condition was_touched = hands.touch(human);
            if (was_touched != Condition.NONE) {
                System.out.println(getName() + " почувствовал, что у " + human.getName() + " " +
                        was_touched.getCondition() + ".\n");
            } else System.out.println(getName() + " запутался.\n");
        }
        return this;
    }
}
