package log.labo.labo05.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import log.labo.labo05.*;
import log.labo.labo05.Controllers.TranslationController;
import log.labo.labo05.Controllers.ZoomController;
import log.labo.labo05.Models.ImageModel;
import log.labo.labo05.Models.ViewPort;

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

        Label title = new Label("Viewport : " + vp.getViewID());

        this.canvas = new Canvas(320, 320);

        Button buttonUndo = new Button("Undo");
        buttonUndo.setOnAction(e -> CommandManager.getInstance().retour(vp.getViewID()));

        this.getChildren().addAll(title, canvas, buttonUndo);

        model.addObserver(this);
        vp.addObserver(this);

        TranslationController translationController = new TranslationController(vp);
        ZoomController zoomController = new ZoomController(vp);

        canvas.setOnMousePressed(translationController::handleMousePressed);
        canvas.setOnMouseDragged(translationController::handleMouseDragged);
        canvas.setOnMouseReleased(translationController::handleMouseReleased);
        canvas.setOnScroll(zoomController::handleScroll);
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