package thePackmaster.cards.discopack;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.vfx.discopack.SpotlightFX;

import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.p;

public class StepTaps extends AbstractSmoothCard {
    public static final String ID = SpireAnniversary5Mod.makeID("StepTaps");

    public StepTaps() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 2;
        this.isEthereal = true;
        this.cardsToPreview = new PointMove();
    }

    public void triggerWhenDrawn() {
        AbstractCard pm = new PointMove();
        for(int i = 0; i < timesUpgraded; i++){pm.upgrade();}
        addToBot(new MakeTempCardInHandAction(pm));
    }
    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void triggerOnManualDiscard() {
        atb(new DrawCardAction(magicNumber));
            atb(new VFXAction(new SpotlightFX(p()), 0.1f));
    }

    public void upp() {
        cardsToPreview.upgrade();
    }
}
