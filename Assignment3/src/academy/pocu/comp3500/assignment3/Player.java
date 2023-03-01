package academy.pocu.comp3500.assignment3;

import academy.pocu.comp3500.assignment3.chess.PlayerBase;

public class Player extends PlayerBase {
    private boolean isWhite;

    public Player(final boolean isWhite, int maxMoveTimeMilliseconds) {
        super(isWhite, maxMoveTimeMilliseconds);
    }
}