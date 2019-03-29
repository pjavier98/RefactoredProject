package snake;

import interfaces.makeMove;
import interfaces.setMove;
import makeMoves.makeBottom;
import makeMoves.makeLeft;
import makeMoves.makeRight;
import makeMoves.makeTop;
import setMoves.*;

public enum StrategyMoviment {
    LEFT {
        public setMove setMoviment() {
            return new setLeft();
        }
    },
    TOP {
        public setMove setMoviment() {
            return new setTop();
        }
    },
    RIGHT {
        public setMove setMoviment() {
            return new setRight();
        }
    },
    BOTTOM {
        public setMove setMoviment() {
            return new setBottom();
        }
    };

    public abstract setMove setMoviment();
}
