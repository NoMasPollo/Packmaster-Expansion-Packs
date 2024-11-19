package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.discopack.BusPower;
import thePackmaster.powers.discopack.BusPower2;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.att;

public class TheBusStop extends AbstractSmoothCard{
    public static final String ID = makeID("TheBusStop");

    public TheBusStop() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
        this.baseSecondMagic = this.secondMagic = 1;
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber));
        if (!upgraded)
            applyToSelf(new BusPower(p, secondMagic));
        else {
            ArrayList<AbstractCard> choices = new ArrayList<>();

            AbstractPackmasterCard c1 = (AbstractPackmasterCard) this.makeCopy();
            c1.cost = -2;
            c1.magicNumber = c1.baseMagicNumber = this.magicNumber;
            c1.secondMagic = -1;
            c1.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            c1.initializeDescription();

            AbstractPackmasterCard c2 = (AbstractPackmasterCard) this.makeCopy();
            c2.cost = -2;
            c2.magicNumber = c2.baseMagicNumber = this.secondMagic;
            c2.secondMagic = -2;
            c2.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
            c2.initializeDescription();

            choices.add(c1);
            choices.add(c2);
            this.addToBot(new ChooseOneAction(choices));
        }
    }
    public void onChoseThisOption() {
        if (secondMagic == -1)
            applyToSelf(new BusPower(AbstractDungeon.player, 1));
        if (secondMagic == -2)
            applyToSelf(new BusPower2(AbstractDungeon.player, 1));
    }
    public void triggerOnManualDiscard() {
        att(new SpecificToHandFromDiscardAction(this));
    }
    @Override
    public void upp() {
        //this.upgradeMagicNumber(2);
    }
}
