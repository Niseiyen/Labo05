package log.labo.labo05;
import java.util.HashMap;
import java.util.Stack;

public class CommandManager {
    private static CommandManager instance;
    private HashMap<String, Stack<Commande>> historiques = new HashMap<>();

    private CommandManager() {}

    public static CommandManager getInstance() {
        if (instance == null) instance = new CommandManager();
        return instance;
    }

    public void executerCommande(String viewId, Commande cmd) {
        cmd.executer();
        historiques.computeIfAbsent(viewId, k -> new Stack<>()).push(cmd);
    }

    public void retour(String viewId) {
        if (historiques.containsKey(viewId) && !historiques.get(viewId).isEmpty()) {
            historiques.get(viewId).pop().retour();
        }
    }
}