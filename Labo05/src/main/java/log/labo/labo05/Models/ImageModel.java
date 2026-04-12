package log.labo.labo05.Models;
import log.labo.labo05.ImageObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements Serializable {
    private String imagePath;
    private transient List<ImageObserver> observers = new ArrayList<>();

    public void addObserver(ImageObserver o) {
        if (observers == null) observers = new ArrayList<>();
        observers.add(o);
    }

    public void setImagePath(String path) {
        this.imagePath = path;
        notifyObserver();
    }

    public String getImagePath() { return imagePath; }

    public void notifyObserver() {
        if (observers != null) {
            for (ImageObserver o : observers) o.onImageChange(this);
        }
    }
}