package thePackmaster.cards.discopack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.discopack.FastMovementsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FastMovements extends AbstractSmoothCard{
    public static final String ID = makeID("FastMovements");

    public FastMovements() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
        this.baseSecondMagic =  this.secondMagic = 50;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new FastMovementsPower(p, magicNumber, secondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(25);
    }
}


