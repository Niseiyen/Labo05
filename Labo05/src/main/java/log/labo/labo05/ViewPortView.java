package log.labo.labo05;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class ViewPortView extends VBox implements ImageObserver, ViewPortObserver {
    private Canvas canvas;
    private ViewPort viewPort;
    private Image currentImage;

    public ViewPortView(ImageModel model, ViewPort vp) {
        this.viewPort = vp;
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-border-color: #3498db; -fx-border-radius: 5; -fx-border-width: 2; -fx-background-color: white;");

        Label title = new Label("Perspective : " + vp.getViewID());

        this.canvas = new Canvas(320, 320);

        Button btnUndo = new Button("↩ Annuler (Undo)");
        btnUndo.setOnAction(e -> CommandManager.getInstance().retour(vp.getViewID()));

        this.getChildren().addAll(title, canvas, btnUndo);

        model.addObserver(this);
        vp.addObserver(this);

        TranslationController tCtrl = new TranslationController(vp);
        ZoomController zCtrl = new ZoomController(vp);

        canvas.setOnMousePressed(tCtrl::handleMousePressed);
        canvas.setOnMouseDragged(tCtrl::handleMouseDragged);
        canvas.setOnMouseReleased(tCtrl::handleMouseReleased);
        canvas.setOnScroll(zCtrl::handleScroll);
    }

    @Override public void onImageChange(ImageModel model) {
        if (model.getImagePath() != null) {
            this.currentImage = new Image("file:" + model.getImagePath());
            render();
        }
    }

    @Override public void onViewPortChange(ViewPort view) { render(); }

    public void render() {
        if (currentImage == null) return;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.save();
        gc.translate(viewPort.getTranslateX(), viewPort.getTranslateY());
        gc.scale(viewPort.getScale(), viewPort.getScale());
        gc.drawImage(currentImage, 0, 0);
        gc.restore();
    }
}