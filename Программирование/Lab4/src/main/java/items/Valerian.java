package main.java.items;

import main.java.enums.Method;
import main.java.people.Human;

public class Valerian extends Item {
    public Valerian(String name) {
        super(name, Method.IS_EATABLE);
    }

    public int use(Human human, Method method) {
        if (checkMethod(method)) {
            System.out.println(human.getName() + " выпил " + getName() + ".\n");
            if (!applyEffect(human)) System.out.println(getName() + " не произвел никакого эффекта.\n");
            return 0;
        }
        System.out.println(human.getName() + " ничего не смог сделать с " + getName() + ".\n");
        return 1;
    }

    private boolean applyEffect(Human human) {
        System.out.println(human.getName() + " стал немного спокойнее.\n");
        return true;
    }
}
