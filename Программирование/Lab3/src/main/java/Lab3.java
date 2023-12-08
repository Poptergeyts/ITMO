package main.java;

import main.java.enums.Action;
import main.java.enums.Condition;
import main.java.items.Ammonia;
import main.java.items.Medicine;
import main.java.people.Doctor;
import main.java.people.Dunno;
import main.java.enums.Method;

public class Lab3 {
    public static void main(String[] args) {
        Medicine magicPill = (human, method) -> {
            if (human.getOrgansCondition(Method.IS_AUDIBLE) == Condition.HEART_IS_NOT_BEATING) {
                System.out.println(human.getName() + " использовал волшебную таблетку и это помогло.\n");
                human.regainConsciousness();
                return 0;
            }
            System.out.println("У " + human.getName() + " и так всё отлично.");
            return 1;
        };

        Dunno dunno = new Dunno("Незнайка");
        dunno.loseConsciousness();
        Doctor pillman = new Doctor("Пилюлькин");
        Ammonia ammonia = new Ammonia("Нашатырный спирт");

        pillman.look(dunno).actionWithHands(dunno, Action.SHAKE).actionWithHands(dunno, Action.TOUCH).listen(dunno);

        dunno.sniff(ammonia);

        dunno.actionWithHands(pillman, Action.TOUCH);

        magicPill.use(dunno, Method.IS_OLFACTORY);
    }
}
