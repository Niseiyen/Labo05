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
        double dx = e.getX() - lastX;
        double dy = e.getY() - lastY;
        viewPort.setTranslation(viewPort.getTranslateX() + dx, viewPort.getTranslateY() + dy);
        lastX = e.getX(); lastY = e.getY();
    }

    public void handleMouseReleased(MouseEvent e) {
        TranslationCommand cmd = new TranslationCommand(viewPort, startX, startY, viewPort.getTranslateX(), viewPort.getTranslateY());
        viewPort.setTranslation(startX, startY);
        CommandManager.getInstance().executerCommande(viewPort.getViewID(), cmd);
    }
}