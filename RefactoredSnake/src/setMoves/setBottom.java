package setMoves;

import interfaces.setMove;
import snake.*;

public class setBottom implements setMove {
    public Moviments setMoviment(Moviments allMoves) {
        if(allMoves.getTop() != 2) {
            return new Moviments(0, 0, 0, 4, 4);
        }
        return allMoves;
    }
}
