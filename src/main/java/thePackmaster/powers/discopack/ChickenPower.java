package thePackmaster.powers.discopack;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;
import static thePackmaster.util.Wiz.atb;

public class ChickenPower extends AbstractPackmasterPower implements NonStackablePower, CloneablePowerInterface {
    public static final String POWER_ID = makeID("ChickenPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public ChickenPower(AbstractCreature owner,int amount, int cardsToDiscard) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, 1);
        this.isTwoAmount = true;
        amount2 = cardsToDiscard;
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        atb(new DiscardAction(owner, owner, amount2, false));
        CardCrawlGame.sound.play(modID + ":Chicken", 0.1F);
        amount -= 1;
        if(amount <= 0){removeThis();}
    }
    @Override
    public boolean isStackable(AbstractPower power){
        if (power instanceof ChickenPower) {
            return amount2 == ((ChickenPower) power).amount2;
        }
        return false;
    }
    @Override
    public void updateDescription() {//At the start of your next x turns discard 2 cards
        description = DESCRIPTIONS[0] + amount +  DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new ChickenPower(owner, amount, amount2);
    }
}
