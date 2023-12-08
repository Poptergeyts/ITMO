package main.java;

import main.java.pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Lab2 {
    public static void main(String[] args){
        Battle battle = new Battle();
        Bruxish bruxish = new Bruxish("Гандж", 8);
        Surskit surskit = new Surskit("Цезарь", 6);
        Masquerain masquerain = new Masquerain("Грозны", 6);
        Ralts ralts = new Ralts("Евро", 8);
        Kirlia kirlia = new Kirlia("Грим", 11);
        Gallade gallade = new Gallade("Буян", 11);

        battle.addAlly(bruxish);
        battle.addAlly(surskit);
        battle.addAlly(masquerain); 
        battle.addFoe(ralts);
        battle.addFoe(kirlia);
        battle.addFoe(gallade);
        battle.go();
    }
}
