package setMoves;

import interfaces.setMove;
import snake.*;

public class setLeft implements setMove {
    public Moviments setMoviment(Moviments allMoves) {
        if(allMoves.getRight() != 3) {
            return new Moviments(1, 0, 0, 0, 1);
        }
        return allMoves;
    }
}
