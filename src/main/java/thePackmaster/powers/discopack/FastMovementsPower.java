package thePackmaster.powers.discopack;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.bardinspirepack.InspirationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.hand;

public class FastMovementsPower extends AbstractPackmasterPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = makeID("FastMovementsPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public FastMovementsPower(AbstractCreature owner, int amount, int percent) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        amount2 = percent;
        updateDescription();
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        int handDiff = 3 - hand().size();
        if(handDiff > 0){applyToSelf(new InspirationPower(owner, amount * handDiff, amount2));}
    }

    @Override
    public void updateDescription() {
        String plural = amount > 1  ? DESCRIPTIONS[4]:"";
        String upg = amount2 > 50 ? "+" :"";
        name = NAME + upg;
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + plural + DESCRIPTIONS[3] + DESCRIPTIONS[5];
    }
    @Override
    public boolean isStackable(AbstractPower power){
        if (power instanceof FastMovementsPower) {
            return amount2 == ((FastMovementsPower) power).amount2;
        }
        return false;
    }

    @Override
    public AbstractPower makeCopy() {
        return new FastMovementsPower(owner, amount, amount2);
    }
}
