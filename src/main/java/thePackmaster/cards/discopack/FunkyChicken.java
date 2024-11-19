package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.powers.discopack.ChickenPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.att;

public class FunkyChicken extends AbstractSmoothCard {
    public static final String ID = makeID("FunkyChicken");

    public FunkyChicken() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 2;
        this.baseBlock = block = 16;

    }
    public void triggerOnManualDiscard() {
        att(new SpecificToHandFromDiscardAction(this));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new ChickenPower(p,  1 ,magicNumber));
    }


    @Override
    public void upp() {
        this.upgradeBlock(4);
    }
}