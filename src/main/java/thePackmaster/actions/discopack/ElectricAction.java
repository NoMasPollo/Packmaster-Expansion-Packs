package thePackmaster.actions.discopack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.vfx.discopack.ElectricFX;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.util.Wiz.att;
import static thePackmaster.util.Wiz.p;

public class ElectricAction extends AbstractGameAction {

    private final List<AbstractCard> selectedCards = new ArrayList<>();

    public ElectricAction(List<AbstractCard> selectedCards) {
        this.selectedCards.addAll(selectedCards);
    }

    @Override
    public void update() {
        for (AbstractCard c : selectedCards) {
            AbstractDungeon.effectsQueue.add(new ElectricFX(p(), 0.75f,  2));
            DiscardCard(c);
            att(new GainEnergyAction(1));
        }
        isDone = true;
    }
    public void DiscardCard(AbstractCard c){
        //SpireAnniversary5Mod.logger.info("Starting discard with card:  " + c);
        p().hand.moveToDiscardPile(c);
        c.triggerOnManualDiscard();
        GameActionManager.incrementDiscard(false);
    }
}