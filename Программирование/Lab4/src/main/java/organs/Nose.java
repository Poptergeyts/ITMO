package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;
import main.java.items.Item;
import main.java.items.Useable;
import main.java.people.Human;

public class Nose extends Organ {
    public Nose(String owner) {
        super(owner);
    }

    public int sniff(Human human, Useable item) {
        if (item.use(human, Method.IS_OLFACTORY) == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Nose of" + getOwner();
    }
}
