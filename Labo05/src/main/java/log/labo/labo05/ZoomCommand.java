package log.labo.labo05;

public class ZoomCommand implements Commande {
    private ViewPort viewPort;
    private double scalePrecedent, newScale;

    public ZoomCommand(ViewPort vp, double scalePrecedent, double newScale) {
        this.viewPort = vp; this.scalePrecedent = scalePrecedent; this.newScale = newScale;
    }
    @Override public void executer() { viewPort.setScale(newScale); }
    @Override public void retour() { viewPort.setScale(scalePrecedent); }
}