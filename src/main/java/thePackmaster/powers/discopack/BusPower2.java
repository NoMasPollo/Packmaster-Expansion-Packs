package thePackmaster.powers.discopack;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class BusPower2 extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("BusPower2");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public BusPower2(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        int adjust = 0;
        ArrayList<AbstractCard> targetList = new ArrayList<>();
        if(!AbstractDungeon.player.hand.group.isEmpty()){
            for (int i = 0; i < amount + adjust; i++){
                if(AbstractDungeon.player.hand.group.size() <= i){break;}
                AbstractCard target = AbstractDungeon.player.hand.group.get(AbstractDungeon.player.hand.group.size() - 1 - i);
                if(target != usedCard){targetList.add(target);}
                else {adjust = 1;}
            }
            for (AbstractCard card:targetList) {
                atb(new DiscardSpecificCardAction(card));
            }
        }
    }

    public void atStartOfTurn(){
        removeThis();
    }
    @Override
    public void updateDescription() {
        String plural = amount > 1 ? DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + DESCRIPTIONS[3]: DESCRIPTIONS[1] + DESCRIPTIONS[2];
        description = DESCRIPTIONS[0] +  plural + DESCRIPTIONS[4];
    }
}
