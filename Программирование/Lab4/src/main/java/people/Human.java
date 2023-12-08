package main.java.people;

import main.java.enums.Action;
import main.java.enums.Condition;
import main.java.enums.Method;
import main.java.exceptions.CharacterIsUnconsciousException;
import main.java.exceptions.UseableNotSupportedException;
import main.java.items.Item;
import main.java.items.Useable;
import main.java.organs.*;

public abstract class Human {
    @NotNegative
    public int age;
    protected Condition condition = Condition.CONSCIOUS;
    protected String name;
    protected Organ[] organs = new Organ[6];
    protected Ears ears;
    protected Eyes eyes;
    protected Hands hands;
    protected Vessels vessels;
    protected Nose nose;
    protected Heart heart;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;

        ears = new Ears(name);
        eyes = new Eyes(name);
        hands = new Hands(name);
        vessels = new Vessels(name);
        nose = new Nose(name);
        heart = new Heart(name, vessels, eyes);

        organs[0] = ears;
        organs[1] = eyes;
        organs[2] = hands;
        organs[3] = vessels;
        organs[4] = nose;
        organs[5] = heart;
    }

    public int regainConsciousness() {
        if (condition == Condition.UNCONSCIOUS) {
            heart.startBeating();
            condition = Condition.CONSCIOUS;
            System.out.println(getName() + " пришёл в сознание.\n");
            return 0;
        }
        return 1;
    }

    public int loseConsciousness() {
        if (condition == Condition.CONSCIOUS) {
            heart.stopBeating();
            condition = Condition.UNCONSCIOUS;
            System.out.println(getName() + " потерял сознание.\n");
            return 0;
        }
        return 1;
    }

    public Condition getOrgansCondition(Method method) {
        for (Organ organ : organs) {
            Condition temporary_condition = organ.checkCondition(method);
            if (temporary_condition != Condition.NONE) return temporary_condition;
        }
        return Condition.NONE;
    }

    public Human listen(Human human) throws CharacterIsUnconsciousException {
        if (condition == Condition.UNCONSCIOUS) {
            throw new CharacterIsUnconsciousException(getName());
        }
        System.out.println(getName() + " внимательно слушает " + human.getName() + ".\n");
        return this;
    }

    public Human look(Human human) throws CharacterIsUnconsciousException{
        if (condition == Condition.UNCONSCIOUS) {
            throw new CharacterIsUnconsciousException(getName());
        }
        System.out.println(getName() + " внимательно смотрит на " + human.getName() + ".\n");
        return this;
    }

    public Human sniff(Item item) throws UseableNotSupportedException {
        if (!(item instanceof Useable)) {
            throw new UseableNotSupportedException(item.getName());
        }
        nose.sniff(this, (Useable) item);
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass() + getName();
    }

    @Override
    public int hashCode() {
        return 8654 * getName().hashCode() % 10000;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Human)) return false;

        return hashCode() == object.hashCode();
    }

    public abstract Human actionWithHands(Human dunno, Action action);
}
