package makeMoves;

import interfaces.makeMove;

public class makeLeft implements makeMove {
    public int makeMoviment(int DOT_SIZE) {
        return -DOT_SIZE;
    }
}
