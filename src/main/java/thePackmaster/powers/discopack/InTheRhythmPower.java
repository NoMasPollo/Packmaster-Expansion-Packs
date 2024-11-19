package thePackmaster.powers.discopack;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class InTheRhythmPower extends AbstractPackmasterPower implements DiscardHookPatch.OnDiscardThing{
    public static final String POWER_ID = makeID("InTheRhythmPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public InTheRhythmPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = 0;
        this.updateDescription();
    }

    public void onManualDiscardThing() {
        amount2 += amount;
        this.flash();
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        if(amount2 > 0){
            this.flash();
            CardCrawlGame.sound.play("TINGSHA");
            applyToSelf(new StrengthPower(owner, amount2));
            applyToSelf(new LoseStrengthPower(owner, amount2));
            applyToSelf(new DexterityPower(owner, amount2));
            applyToSelf(new LoseDexterityPower(owner, amount2));
            updateDescription();
        }
        amount2 = 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3] + amount2 + DESCRIPTIONS[4];
    }
}

