package log.labo.labo05;
import java.io.Serializable;

public interface Commande extends Serializable {
    void executer();
    void retour();
}