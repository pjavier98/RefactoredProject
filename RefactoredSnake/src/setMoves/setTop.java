package setMoves;

import interfaces.setMove;
import snake.*;

public class setTop implements setMove {
    public Moviments setMoviment(Moviments allMoves) {
        if(allMoves.getBottom() != 4) {
            return new Moviments(0, 2, 0, 0, 2);
        }
        return allMoves;
    }
}
