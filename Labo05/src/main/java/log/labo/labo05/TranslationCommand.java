package log.labo.labo05;

import log.labo.labo05.Models.ViewPort;

public class TranslationCommand implements Commande {
    private ViewPort viewPort;
    private double translationXPrecendent, translationYPrecendent, newTranslationX, newTranslationY;

    public TranslationCommand(ViewPort vp, double ox, double oy, double nx, double ny) {
        this.viewPort = vp; this.translationXPrecendent = ox; this.translationYPrecendent = oy; this.newTranslationX = nx; this.newTranslationY = ny;
    }
    @Override public void executer() { viewPort.setTranslation(newTranslationX, newTranslationY); }
    @Override public void retour() { viewPort.setTranslation(translationXPrecendent, translationYPrecendent); }
}