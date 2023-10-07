package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;

public class Ghoul extends Enemy{
    private final static int MAX_RUNE_AMOUNT = 3000;
    private final static int MIN_RUNE_AMOUNT = 318;

    public Ghoul(){
        super("Ghoul", '9', 423);
        this.addCapability(EnemyType.GHOUL);

    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "attacks", 95);
    }
    @Override
    public int getMinRunes() {
        return(MIN_RUNE_AMOUNT);
    }

    @Override
    public int getMaxRunes() {
        return(MAX_RUNE_AMOUNT );
    }
}

