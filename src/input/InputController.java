package input;

import javafx.scene.input.KeyCode;

import java.util.HashMap;

/**
 * Holder kontroll på alle inputs.
 * Et enkelt hashmap som har oversikt over om en nøkkel blir holdt nede eller ikke.
 */
public class InputController {
    private static final HashMap<KeyCode, Boolean> INPUT = new HashMap<>();
    public static boolean isPressed(KeyCode k){
        if(INPUT.containsKey(k)){
            return INPUT.get(k);
        }
        return false;
    }
    public static void press(KeyCode k){
        INPUT.put(k, true);
    }
    public static void release(KeyCode k){
        INPUT.put(k, false);
    }
}
