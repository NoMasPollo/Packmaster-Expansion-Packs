package thePackmaster.cards.discopack;


import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.discopack.ElectricAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ElectricSlide extends AbstractSmoothCard {
    public static final String ID = makeID("ElectricSlide");

    public ElectricSlide() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
    }
    public String text = cardStrings.EXTENDED_DESCRIPTION[0];

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(magicNumber, text, true, true, card -> true,(list) -> atb(new ElectricAction(list))));
    }

    //public void triggerOnManualDiscard() {att(new GainEnergyAction(1));}

    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }
}
