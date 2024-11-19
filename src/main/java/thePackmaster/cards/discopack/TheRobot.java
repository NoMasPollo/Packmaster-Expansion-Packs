package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.bardinspirepack.InspirationPower;
import thePackmaster.vfx.discopack.ElectricFX;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TheRobot extends AbstractSmoothCard{
    public static final String ID = makeID("TheRobot");

    public TheRobot() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        //this.baseMagicNumber = magicNumber = 5;
        this.baseDamage = damage = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<InspirationPower> powersToRegain = new ArrayList<>();
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.ID.equals(InspirationPower.POWER_ID) && power instanceof InspirationPower) {
                powersToRegain.add((InspirationPower)power);
            }
        }
        atb(new VFXAction(new ElectricFX(m, 0.75f, 5), 0.33f));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        for (InspirationPower power:powersToRegain){applyToSelf(new InspirationPower(power.owner, 1, power.amount2));}
    }

    @Override
    public void upp() {
        //upgradeMagicNumber(5);
        upgradeDamage(3);
    }
}
