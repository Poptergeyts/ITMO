package main.java;

import main.java.enums.Action;
import main.java.enums.Condition;
import main.java.exceptions.CharacterIsUnconsciousException;
import main.java.exceptions.UseableNotSupportedException;
import main.java.items.Ammonia;
import main.java.items.Item;
import main.java.items.Useable;
import main.java.items.Valerian;
import main.java.people.Doctor;
import main.java.people.Dunno;
import main.java.enums.Method;
import main.java.people.Human;
import main.java.people.NotNegative;
import main.java.space.Galaxy;

import java.lang.reflect.Field;

public class Lab3 {
    public static void main(String[] args){
        System.out.println("Ускорение свободного падения на Земле равно " +
                Galaxy.PhysicsLaws.calculateFreeFallAcceleration(5.97E24, 6378.1) + "\n");

        Galaxy milkyWay = new Galaxy("Млечный путь");
        Galaxy.PlanetarySystem solarSystem = milkyWay.new PlanetarySystem("Солнечная система");

        System.out.println(milkyWay.getGalaxyName());
        System.out.println(solarSystem.getPlanetarySystemName());
        milkyWay.destroyGalaxy();
        System.out.println(milkyWay.getGalaxyName() + "\n");
        milkyWay = null;
        solarSystem = null;

        Useable magicPill = (human, method) -> {
            if (human.getOrgansCondition(Method.IS_AUDIBLE) == Condition.HEART_IS_NOT_BEATING) {
                System.out.println(human.getName() + " использовал волшебную таблетку и это помогло.\n");
                human.regainConsciousness();
                return 0;
            }
            System.out.println("У " + human.getName() + " и так всё отлично.");
            return 1;
        };

        Dunno dunno = new Dunno("Незнайка", 18);
        dunno.loseConsciousness();
        Doctor pillman = new Doctor("Пилюлькин", -3);
        Ammonia ammonia = new Ammonia("Нашатырный спирт");

        try{
        Class cl = Class.forName("main.java.people.Human");
        Field[] fields = cl.getFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(NotNegative.class)){
                if ((int)f.get(dunno) < 0 || (int)f.get(pillman) < 0){
                    System.out.println("Attention: " + f.getName() + " is negative\n");
                }
                else{
                    System.out.println("Attention: " + f.getName() + " not negative ");
                }
            }
        }
        }
        catch (ClassNotFoundException|IllegalAccessException exp){
            exp.printStackTrace();
        }

        Valerian valerian = new Valerian("Валерьянка");

        try {
            pillman.look(dunno).actionWithHands(dunno, Action.SHAKE).actionWithHands(dunno, Action.TOUCH).listen(dunno);
        }
        catch(CharacterIsUnconsciousException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            dunno.sniff(ammonia);
        }
        catch (UseableNotSupportedException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            dunno.actionWithHands(pillman, Action.TOUCH);
        }
        catch(CharacterIsUnconsciousException exception) {
            System.out.println(exception.getMessage());
        }

        new Item("MagicPill", Method.IS_EATABLE) {
            int use(Human human) {
                System.out.println(human.getName() + " выпил волшебную таблетку.");
                return human.regainConsciousness();
            }
        }.use(dunno);

        try {
            pillman.sniff(valerian);
        }
        catch (UseableNotSupportedException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
