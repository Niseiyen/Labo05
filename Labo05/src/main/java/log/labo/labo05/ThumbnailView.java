package log.labo.labo05;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;

public class ThumbnailView extends VBox implements ImageObserver {
    private Canvas canvas;

    public ThumbnailView(ImageModel model) {
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("IMAGE SOURCE");

        this.canvas = new Canvas(150, 150);

        Button btnLoad = new Button("Charger Image");
        btnLoad.setPrefWidth(150);
        btnLoad.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(null);
            if (f != null) model.setImagePath(f.getAbsolutePath());
        });

        this.getChildren().addAll(title, canvas, btnLoad);
        model.addObserver(this);
    }

    @Override
    public void onImageChange(ImageModel model) {
        if (model.getImagePath() != null) {
            Image img = new Image("file:" + model.getImagePath());
            canvas.getGraphicsContext2D().clearRect(0, 0, 150, 150);
            canvas.getGraphicsContext2D().drawImage(img, 0, 0, 150, 150);
        }
    }
}