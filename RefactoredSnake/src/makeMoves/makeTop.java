package makeMoves;

import snake.Moviments;
import interfaces.makeMove;

public class makeTop implements makeMove {
    public int makeMoviment(int DOT_SIZE) {
        return -DOT_SIZE;
    }
}
