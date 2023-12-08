package main.java.organs;

import main.java.enums.Condition;
import main.java.enums.Method;

public abstract class Organ implements OwnerGetter {
    private String owner;
    private Method method;
    protected Condition condition;

    public Organ(String owner) {
        this.owner = owner;
        condition = Condition.NONE;
        method = Method.INVALID;
    }

    public Organ(String owner, Condition condition, Method method) {
        this.owner = owner;
        this.condition = condition;
        this.method = method;
    }

    public Condition checkCondition(Method method) {
        if (this.method == method) return condition;
        return Condition.NONE;
    }

    @Override
    public String toString() {
        return "Organ of" + owner;
    }

    @Override
    public int hashCode() {
        return 8654 * owner.hashCode() % 1000;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Organ)) return false;

        return hashCode() == object.hashCode();
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
