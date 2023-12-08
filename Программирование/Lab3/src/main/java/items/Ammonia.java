package main.java.items;

import main.java.enums.Method;
import main.java.people.Human;
import java.lang.Math;

public class Ammonia extends Item implements Medicine{
    public Ammonia(String name) {
        super(name, Method.IS_OLFACTORY);
    }

    @Override
    public int use(Human human, Method method) {
        if (checkMethod(method)) {
            System.out.println(human.getName() + " понюхал " + getName() + ".\n");
            if (!applyEffect(human)) System.out.println(getName() + " не произвел никакого эффекта.\n");
            return 0;
        }
        System.out.println(human.getName() + " ничего не смог сделать с " + getName() + ".\n");
        return 1;
    }

    private boolean applyEffect(Human human) {
        if ((Math.random() * 3 - 2) > 0) {
            human.regainConsciousness();
            return true;
        }
        return false;
    }
}
