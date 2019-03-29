package snake;

import interfaces.makeMove;
import makeMoves.makeBottom;
import makeMoves.makeLeft;
import makeMoves.makeRight;
import makeMoves.makeTop;

public enum CommandMoviment {
    LEFT {
        public makeMove getMoviment() {
            return new makeLeft();
        }
    },
    TOP {
        public makeMove getMoviment() {
            return new makeTop();
        }
    },
    RIGHT {
        public makeMove getMoviment() {
            return new makeRight();
        }
    },
    BOTTOM {
        public makeMove getMoviment() {
            return new makeBottom();
        }
    };

    public abstract makeMove getMoviment();
}
