package main.java.items;

import main.java.enums.Method;
import main.java.people.Human;

public abstract class Item {
    private String name;
    private Method method;

    public Item(String name, Method method) {
        this.name = name;
        this.method = method;
    }

    protected boolean checkMethod(Method method) {
        return this.method == method;
    }

    public String getName() {
        return name;
    }

    public abstract int use(Human human, Method method);

    @Override
    public String toString() {
        return getClass() + getName();
    }

    @Override
    public int hashCode() {
        return 8654 * getName().hashCode() % 100;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Item)) return false;

        return hashCode() == object.hashCode();
    }
}
