package log.labo.labo05;
import javafx.scene.input.MouseEvent;

public class TranslationController {
    private ViewPort viewPort;
    private double lastX, lastY, startX, startY;

    public TranslationController(ViewPort vp) { this.viewPort = vp; }

    public void handleMousePressed(MouseEvent e) {
        lastX = e.getX(); lastY = e.getY();
        startX = viewPort.getTranslateX();
        startY = viewPort.getTranslateY();
    }

    public void handleMouseDragged(MouseEvent e) {
        double valueX = e.getX() - lastX;
        double valueY = e.getY() - lastY;
        viewPort.setTranslation(viewPort.getTranslateX() + valueX, viewPort.getTranslateY() + valueY);
        lastX = e.getX(); lastY = e.getY();
    }

    public void handleMouseReleased(MouseEvent e) {
        TranslationCommand cmd = new TranslationCommand(viewPort, startX, startY, viewPort.getTranslateX(), viewPort.getTranslateY());
        viewPort.setTranslation(startX, startY);
        CommandManager.getInstance().executerCommande(viewPort.getViewID(), cmd);
    }
}