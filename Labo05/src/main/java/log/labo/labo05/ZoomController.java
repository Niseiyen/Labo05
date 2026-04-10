package log.labo.labo05;
import javafx.animation.PauseTransition;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;

public class ZoomController {
    private ViewPort viewPort;
    private double scaleInitial;
    private PauseTransition timer;

    public ZoomController(ViewPort vp) {
        this.viewPort = vp;
        this.timer = new PauseTransition(Duration.millis(500));
        this.timer.setOnFinished(e -> terminerZoom());
    }

    public void handleScroll(ScrollEvent e) {
        if (timer.getStatus() != javafx.animation.Animation.Status.RUNNING) {
            scaleInitial = viewPort.getScale();
        }

        double factor = (e.getDeltaY() > 0) ? 1.05 : 0.95;
        viewPort.setScale(viewPort.getScale() * factor);

        timer.playFromStart();
    }

    private void terminerZoom() {
        ZoomCommand cmd = new ZoomCommand(viewPort, scaleInitial, viewPort.getScale());
        viewPort.setScale(scaleInitial);
        CommandManager.getInstance().executerCommande(viewPort.getViewID(), cmd);
    }
}