package character;

import abilities.Dash;
import abilities.Swing;
import cell.Cell;
import cell.Point;
import game.GameController;
import game.Settings;
import input.InputController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import map.Map;
import miscs.EntityOptions;

/**
 * Hovedklasse for spillere.
 * Inneholder kollisjon, bevegelse, animasjoner, med mer...
 */
public abstract class Player extends Character{

    private static final double DIAGONAL_FACTOR = 0.7;
    private static final double DIAGONAL_MOVEMENT_SPEED = Settings.MOVEMENT_SPEED* DIAGONAL_FACTOR;

    public static final int START_LIVES = 3;
    private static final Duration ABILITY_COOLDOWN = Duration.millis(1000);

    private final Timeline COOLDOWN;
    private final Timeline DASH_COOLDOWN;
    private Swing SWING;
    private final Dash DASH;

    public int playerNumber;


    protected KeyCode[] keyBindings = {
            KeyCode.UP,
            KeyCode.DOWN,
            KeyCode.LEFT,
            KeyCode.RIGHT,
            KeyCode.DIGIT1,
            KeyCode.DIGIT2,
            KeyCode.DIGIT3,
            KeyCode.DIGIT4,
            KeyCode.DIGIT5,
            KeyCode.DIGIT6
    };

    EventHandler<ActionEvent> handleCooldown = e-> {
        SWING.reset();
    };

    EventHandler<ActionEvent> handleAbilityCooldown = e-> {
        // If extra action is needed
    };


    protected Player(Image image, Map map, Point point) {
        super(image, map, point);
        lives = START_LIVES;

        COOLDOWN = new Timeline(new KeyFrame(ABILITY_COOLDOWN, handleCooldown));
        COOLDOWN.setCycleCount(1);
        DASH_COOLDOWN = new Timeline(new KeyFrame(ABILITY_COOLDOWN, handleAbilityCooldown));
        DASH_COOLDOWN.setCycleCount(1);
        SWING = new Swing(this);
        DASH = new Dash(this);
    }

    @Override
    public void stop(){
        COOLDOWN.stop();
        DASH_COOLDOWN.stop();
        super.stop();
    }

    public void swingHit(){
        hitPoints-=Settings.SWING_DAMAGE;
        checkHP();
        Map.playerStatus.updateHP(playerNumber, hitPoints);
    }

    public void checkHP(){
        if(hitPoints<=0){
            hitPoints=0;
            lives--;
            Map.playerStatus.updateLife(playerNumber, lives);
            hitPoints = START_HP;
        }
        if(lives<1){
            hitPoints=0;
            die();
        }
    }

    public void die(){
        map.endGame(playerNumber);
    }

    @Override
    protected void update() {
        direction(getDirection());
        move(getDirection());
        attack(getAttack());
        dash(getDash());
    }

    private void direction(int d){
        switch (d){
            case 1 -> setViewport(EntityOptions.VIEWS[1]);
            case 2 -> setViewport(EntityOptions.VIEWS[2]);
            case 3, 11, 12 -> setViewport(EntityOptions.VIEWS[3]);
            case 4, 13, 14 -> setViewport(EntityOptions.VIEWS[4]);
        }
    }

    private void dash(int d){
        if(d!=0){
            if(DASH_COOLDOWN.getStatus()==Animation.Status.STOPPED){
                DASH.play(d);
                DASH_COOLDOWN.play();
            }
        }
    }

    private int getDash(){
        if(InputController.isPressed(keyBindings[9]))return 1;
        return 0;
    }

    private void attack(int side){
        if(side!=0){
            if(COOLDOWN.getStatus() == Animation.Status.STOPPED){
                SWING.play(side);
                COOLDOWN.play();
            }
        }
    }

    private int getAttack(){
        if(InputController.isPressed(keyBindings[4]))return 1;
        if(InputController.isPressed(keyBindings[5]))return 2;
        if(InputController.isPressed(keyBindings[6]))return 3;
        if(InputController.isPressed(keyBindings[7]))return 4;
        return 0;
    }

    private void move(int direction){

        double deltaX = 0;
        double deltaY = 0;

        if(direction<=4){
            switch (direction){
                case 1 -> deltaY=-Settings.MOVEMENT_SPEED;
                case 2 -> deltaY=Settings.MOVEMENT_SPEED;
                case 3 -> deltaX=-Settings.MOVEMENT_SPEED;
                case 4 -> deltaX=Settings.MOVEMENT_SPEED;
            }
        } else {
            deltaX = switch (direction){ // Diagonal X
                case 11,12 -> -DIAGONAL_MOVEMENT_SPEED;
                case 13,14 -> DIAGONAL_MOVEMENT_SPEED;
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            };
            deltaY = switch (direction){ // Diagonal Y
                case 11,14 -> -DIAGONAL_MOVEMENT_SPEED;
                case 12,13 -> DIAGONAL_MOVEMENT_SPEED;
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            };
        }

        if(DASH.getStatus()!= Animation.Status.STOPPED){
            deltaX*=2;
            deltaY*=2;
            setViewport(EntityOptions.VIEWS[0]);
        }

        for (Cell c : map.walls){
            boolean isColliding = c.getLayoutBounds().intersects(getLayoutBounds());
            if(isColliding){
                if(deltaX!=0){
                    if ((deltaX<0 && c.getX()<getX()) || (deltaX>0 && c.getX()>getX()) ){ // X
                        deltaX=0;
                        //deltaX=deltaX<0?Settings.BASE_SCALE:-Settings.BASE_SCALE;
                    }
                }
                if(deltaY!=0){
                    if ((deltaY<0 && c.getY()<getY()) || (deltaY>0 && c.getY()>getY()) ){ // Y
                        deltaY=0;
                        //deltaY=deltaY<0?Settings.BASE_SCALE:-Settings.BASE_SCALE;
                    }
                }
            }

        }

        setX(getX()+deltaX);
        setY(getY()+deltaY);
    }

    /*
     *
     * 0 = Standing
     *
     * 1 = UP
     * 2 = DOWN
     * 3 = LEFT
     * 4 = RIGHT
     *
     * 11 = UP / LEFT
     * 12 = LEFT / DOWN
     * 13 = DOWN / RIGHT
     * 14 = RIGHT / UP
     *
     */
    private int getDirection(){
        int inputCount = 0;
        boolean[] input = {
            InputController.isPressed(keyBindings[0]),
            InputController.isPressed(keyBindings[1]),
            InputController.isPressed(keyBindings[2]),
            InputController.isPressed(keyBindings[3])
        };

        for (boolean b : input){
            if(b)inputCount++;
        }
        int sum = 0;
        if(input[0]){
            sum+=1;
        }
        if(input[1]){
            sum-=1;
        }
        if(input[2]){
            sum+=3;
        }
        if(input[3]){
            sum-=3;
        }

        switch (inputCount){
            case 0, 4 -> {
                return 0;
            }
            case 1, 2, 3 -> {
                return switch (sum){

                    case 0 -> 0;

                    case 1 -> 1; // UP
                    case -1 -> 2; // DOWN
                    case 3 -> 3; // LEFT
                    case -3 -> 4; // RIGHT

                    case 4 -> 11; // UP / LEFT
                    case 2 -> 12; // LEFT / DOWN
                    case -4 -> 13; // DOWN / RIGHT
                    case -2 -> 14; // RIGHT / UP

                    default -> throw new IllegalStateException("Unexpected value: " + sum);
                };
            }
        }

        return 0; // Default
    }
}
