package log.labo.labo05;

import log.labo.labo05.Models.ViewPort;

public class ZoomCommand implements Commande {
    private ViewPort viewPort;
    private double scalePrecedent, newScale;

    public ZoomCommand(ViewPort vp, double scalePrecedent, double newScale) {
        this.viewPort = vp; this.scalePrecedent = scalePrecedent; this.newScale = newScale;
    }
    @Override public void executer() { viewPort.setScale(newScale); }
    @Override public void retour() { viewPort.setScale(scalePrecedent); }
}