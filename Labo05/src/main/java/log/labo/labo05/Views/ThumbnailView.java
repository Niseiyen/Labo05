package log.labo.labo05.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import log.labo.labo05.Models.ImageModel;
import log.labo.labo05.ImageObserver;

import java.io.File;

public class ThumbnailView extends VBox implements ImageObserver {
    private Canvas canvas;

    public ThumbnailView(ImageModel model) {
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("IMAGE SOURCE");

        this.canvas = new Canvas(150, 150);

        Button buttonCharger = new Button("Charger Image");
        buttonCharger.setPrefWidth(150);
        buttonCharger.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(null);
            if (f != null) model.setImagePath(f.getAbsolutePath());
        });

        this.getChildren().addAll(title, canvas, buttonCharger);
        model.addObserver(this);
    }

    @Override
    public void onImageChange(ImageModel model) {
        if (model.getImagePath() != null) {
            Image image = new Image("file:" + model.getImagePath());
            canvas.getGraphicsContext2D().clearRect(0, 0, 150, 150);
            canvas.getGraphicsContext2D().drawImage(image, 0, 0, 150, 150);
        }
    }
}