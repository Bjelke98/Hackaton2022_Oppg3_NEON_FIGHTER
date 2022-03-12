package game;

/**
 * Konfigurasjons fil hvor en kan endre skaleringer, hastigheter og lignende.
 */
public class Settings {
    public static final double BASE_SCALE = 4;
    public static final double BASE_CELL = 8; // 8 piksler celler
    public static final double CELL_SIZE = BASE_CELL*BASE_SCALE;
    public static final double CELL_SIZE_HALF = CELL_SIZE/2;
    public static final double CHARACTER_SIZE = CELL_SIZE*2;
    public static final double SPEED_MULTIPLIER = .25;
    public static final double MOVEMENT_SPEED = 3*BASE_SCALE*SPEED_MULTIPLIER;
    public static final double SPRITE_SIZE = 48;
    public static final double SWING_DAMAGE = 20;
    public static final boolean DEBUG = false;
}
