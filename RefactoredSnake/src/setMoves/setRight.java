package setMoves;

import interfaces.setMove;
import snake.*;

public class setRight implements setMove {
    public Moviments setMoviment(Moviments allMoves) {
        if(allMoves.getLeft() != 1) {
            return new Moviments(0, 0, 3, 0, 3);
        }
        return allMoves;
    }
}
