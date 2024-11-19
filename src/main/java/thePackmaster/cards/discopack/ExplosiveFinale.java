package thePackmaster.cards.discopack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.vfx.discopack.DarkenFX;
import thePackmaster.vfx.discopack.SpotlightFX;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.p;

public class ExplosiveFinale extends AbstractSmoothCard{
    public static final String ID = makeID("ExplosiveFinale");
    public int oldHandSize;
    public ExplosiveFinale() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = 24;
        this.isMultiDamage = true;
    }
    public void spotlightFX(boolean isDiscard){
        ArrayList<AbstractMonster> monsterlist = new ArrayList<>();
        for (int i = 0; i < AbstractDungeon.getMonsters().monsters.size(); i++) {
            if(!AbstractDungeon.getMonsters().monsters.get(i).isDeadOrEscaped()){
                monsterlist.add(AbstractDungeon.getMonsters().monsters.get(i));
            }
        }
        if (!isDiscard) {
            for (int i = 0; i < monsterlist.size(); i++) {
                AbstractMonster mo = monsterlist.get(i);
                this.addToBot(new VFXAction(new SpotlightFX(mo)));
            }
            if (!monsterlist.isEmpty() && !monsterlist.get(0).isDeadOrEscaped()){
                CardCrawlGame.sound.playA(SpireAnniversary5Mod.makeID("Spotlight"), 0.1F);
                this.addToBot(new VFXAction(new DarkenFX(2f), 1f));
            }

        }
        for (int i = 0; i < monsterlist.size(); i++) {
            AbstractMonster mo = monsterlist.get(i);
            this.addToBot(new VFXAction(new ExplosionSmallEffect(mo.hb.cX, mo.hb.cY)));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        spotlightFX(false);
        oldHandSize = p().hand.size() - 1;
        atb(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        atb(new DiscardAction(p, p, oldHandSize, true));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (p().hand.size() == 1 || p().hand.size() == BaseMod.MAX_HAND_SIZE) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p().hand.size() == 1 || p().hand.size() == BaseMod.MAX_HAND_SIZE) {
            return super.canUse(p, m);
        }
        return false;
    }

    @Override
    public void upp() {
        this.upgradeDamage(4);
    }
}

