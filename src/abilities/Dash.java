package abilities;

import character.Player;

/**
 * Klassen har ingen underliggende funksjon utenom å sette opp nødvendige timere
 * Valgte å lage den som en vanlig ability i tilfelle det ønskes å legge til mer funksjonalitet i ettertid
 */
public class Dash extends Ability{

    public Dash(Player player) {
        super(player);
    }

    @Override
    protected void update() {

    }

    @Override
    protected void lastTick() {

    }
}
