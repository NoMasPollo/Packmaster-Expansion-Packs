package thePackmaster.cards.discopack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.powers.bardinspirepack.InspirationPower;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.alignmentpack.FlashImageEffect;
import thePackmaster.vfx.discopack.ymcaFlash;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class YMCA extends AbstractSmoothCard {
    public static final String ID = makeID("YMCA");

    private int discardCount = 2;
    public YMCA() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseSecondMagic = secondMagic = discardCount;
        this.baseMagicNumber = magicNumber = 25;
        //this.exhaust = true;
    }
    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public void flasher(int i, AbstractPlayer m){
        String pngnum = "" + i%4;
        Texture img = TexLoader.getTexture(makeImagePath("vfx/discopack/YMCA/" + getLangString() + "/YMCA" + pngnum + ".png"));
        TextureRegion imgReg = new TextureRegion(img);
        int scale;
        if(m.hb.width >= m.hb.height){scale = (int)m.hb.width/100;}
        else{scale = (int)m.hb.height/100;}
        atb(new VFXAction(new FlashImageEffect(imgReg, m.hb.cX, m.hb.cY, scale, Color.WHITE), 0.2f));
        if(i == discardCount - 1){
            atb(new VFXAction(new ymcaFlash()));
            //logger.info("stop trigger");
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play(SpireAnniversary5Mod.makeID("YMCA"));
        for(int i = 0; i < this.discardCount; ++i) {
            applyToSelf(new InspirationPower(p, 1, magicNumber));
            flasher(i, p);
        }
        //discardCount = 2;
        //baseSecondMagic = discardCount;
    }
    public void triggerOnManualDiscard() {
        discardCount = discardCount + 1;
        baseSecondMagic = discardCount;
        att(new SpecificToHandFromDiscardAction(this));
    }
    @Override
    public void upp() {
        upgradeMagicNumber(25);
    }

}


