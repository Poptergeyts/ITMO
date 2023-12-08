package main.java.moves.status;

import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {
    public Swagger() {
        super(Type.NORMAL, Double.NaN, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.addEffect(new Effect().stat(Stat.ATTACK, +2).turns(-1));
        Effect.confuse(p);
    }

    @Override
    protected String describe() {
        return ("раздражает цель, провоцируя её на необдуманные действия");
    }
}
