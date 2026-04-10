package log.labo.labo05;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;

public class Main extends Application {
    private ImageModel imageModel = new ImageModel();
    private ViewPort viewPort1 = new ViewPort("Perspective A");
    private ViewPort viewPort2 = new ViewPort("Perspective B");

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Fichier");
        MenuItem saveItem = new MenuItem("Sauvegarder l'état");
        MenuItem loadItem = new MenuItem("Charger l'état");
        fileMenu.getItems().addAll(saveItem, loadItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);

        saveItem.setOnAction(e -> sauvegarder());
        loadItem.setOnAction(e -> charger());

        ThumbnailView thumbView = new ThumbnailView(imageModel);
        root.setLeft(thumbView);

        HBox workSpace = new HBox(20);
        workSpace.setPadding(new Insets(20));
        workSpace.setStyle("-fx-background-color: #ecf0f1;");

        ViewPortView view1 = new ViewPortView(imageModel, viewPort1);
        ViewPortView view2 = new ViewPortView(imageModel, viewPort2);

        workSpace.getChildren().addAll(view1, view2);
        root.setCenter(workSpace);

        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Labo 5");
        stage.show();
        stage.setScene(scene);
    }

    private void sauvegarder() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("session.ser"))) {
            oos.writeObject(imageModel.getImagePath());
            oos.writeObject(viewPort1);
            oos.writeObject(viewPort2);
            showAlert("Succès", "L'état de l'application a été sauvegardé.");
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void charger() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("session.ser"))) {
            imageModel.setImagePath((String) ois.readObject());
            ViewPort s1 = (ViewPort) ois.readObject();
            ViewPort s2 = (ViewPort) ois.readObject();

            viewPort1.setTranslation(s1.getTranslateX(), s1.getTranslateY());
            viewPort1.setScale(s1.getScale());
            viewPort2.setTranslation(s2.getTranslateX(), s2.getTranslateY());
            viewPort2.setScale(s2.getScale());
            showAlert("Succès", "L'état a été restauré.");
        } catch (Exception e) { showAlert("Erreur", "Aucun fichier de sauvegarde trouvé."); }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}