package main.java.enums;

public enum Condition {
    HEART_IS_BEATING ("сердце бьётся"),
    HEART_IS_NOT_BEATING ("сердце не бьётся"),
    EYES_IS_OPEN ("глаза открыты"),
    EYES_IS_CLOSE ("глаза закрыты"),
    IS_PULSE ("пульс пощупывается"),
    IS_NOT_PULSE ("пульс не прощупывается"),
    CONSCIOUS ("человек в сознании"),
    UNCONSCIOUS ("человек без сознания"),
    NONE ("у органа нет состояния или неверный метод");

    private String condition;

    Condition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
