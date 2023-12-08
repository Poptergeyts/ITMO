package main.java.enums;

public enum Action {
    SHAKE("потряс"),
    TOUCH("потрогал");

    private String action;
    Action(String action){
        this.action = action;
    }

    public String getAction(){
        return  action;
    }
}
