package log.labo.labo05.Models;
import log.labo.labo05.ViewPortObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewPort implements Serializable {
    private double scale = 1.0;
    private double translationX, translationY = 0;
    private final String viewID;
    private transient List<ViewPortObserver> observers = new ArrayList<>();

    public ViewPort(String id) { this.viewID = id; }
    public String getViewID() { return viewID; }

    public void addObserver(ViewPortObserver o) {
        if (observers == null) observers = new ArrayList<>();
        observers.add(o);
    }

    public double getScale() { return scale; }

    public void setScale(double s) { this.scale = s; notifyObservers(); }

    public double getTranslateX() { return translationX; }

    public double getTranslateY() { return translationY; }

    public void setTranslation(double tx, double ty) {
        this.translationX = tx;
        this.translationY = ty;
        notifyObservers();
    }

    private void notifyObservers() {
        if (observers != null) {
            for (ViewPortObserver o : observers) o.onViewPortChange(this);
        }
    }
}