package thePackmaster.cards.discopack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.discopack.InTheRhythmPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InTheRhythm extends AbstractSmoothCard{
    public static final String ID = makeID("InTheRhythm");

    public InTheRhythm() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new InTheRhythmPower(p, magicNumber));
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}