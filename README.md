# RefactoredProject:

## Original Project:
### How to run:
1. Clone the repository;
2. Open IntelliJ;
3. Click the import project option;
4. Open the project;
5. Run the project.


## Projeto Refatorado:
### How to run:
1. Clone the repository;
2. Open IntelliJ;
3. Click the import project option;
4. Open the RefactoredSnake project inside the RefactoredProject folder;
5. Run the project.

# Pattern:
## Strategy:
The strategy pattern was used in the strategy of the snake movement, to get KeyEvent and put
according to the key you have to go to.

Initially in the original project, they constrained 4 Boolean variables in the **Board class**,
which were placed within the **class Moviments** for better handling.

#### According to KeyEvent were modified behaviors of the algorithm:
* KeyCode = 37, all variables were set to false less than going left.
* KeyCode = 38, all variables were set to false less than going up.
* KeyCode = 39, all variables were set to false less than to go to the right.
* KeyCode = 40, all variables were set to false less than going down.

These commands were executed within complex conditional structures, which could hinder the understanding and
the maintenance of the code by other people.
```
if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
    leftDirection = true;
    upDirection = false;
    downDirection = false;
}
```

To correct this code smell was used the **standard strategy**, which allows to deal with conditional structures
and delegates the responsibilities of what to do at runtime through polymorphism.

Four classes have been created within the package **setMoves** that they delegate at runtime and
from the type of object (LEFT, RIGHT, TOP, BOTTOM) different behaviors.

#### Method before refactoring:
```
public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
        leftDirection = true;
        upDirection = false;
        downDirection = false;
    }

    if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }

    if ((key == KeyEvent.VK_UP) && (!downDirection)) {
        upDirection = true;
        rightDirection = false;
        leftDirection = false;
    }

    if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
        downDirection = true;
        rightDirection = false;
        leftDirection = false;
    }
}
```

#### Method after refactoring:
```
public void keyPressed(KeyEvent e) {
    StrategyMoviment moviment = StrategyMoviment.values()[e.getKeyCode() - 37];
    setMove move = moviment.setMoviment();
    allMoves = move.setMoviment(allMoves);
}
```

### Classes and Methods Affected:
The strategy pattern affected the Board class and the KeyPressed method.

### Features affected:
The affected feature is where the snake will travel.



## Command:
The command pattern was used in mind of the commands that should be executed for the snake to move on the board.

The Board class has two arrays, one offset on the x axis and another on the y axis.

Four classes have been created within the package **makeMoves** that they delegate at runtime and
from the type of object (LEFT, RIGHT, TOP, BOTTOM) different behaviors.

These commands were executed within extensive conditional structures, which could hamper understanding and
the maintenance of the code by other people.

```
if (leftDirection) {
    x[0] -= DOT_SIZE;
}
```

#### According to the snake movement defined by the boolean variables from the strategy pattern we have the following:
leftDirection = true -> we add x [0] = -DOTSIZE;
rightDirection = true -> add x [0] = DOTSIZE;
topDirection = true -> add and [0] = DOTSIZE;
bottomDirection = true -> we add y [0] = -DOTSIZE.

According to the x and y coordinate axis, the snake walks the DOTSIZE value in the direction entered by the user.

#### Method before refactoring:
```
private void move() {
    for (int z = dots; z > 0; z--) {
        x[z] = x[(z - 1)];
        y[z] = y[(z - 1)];
    }

    if (leftDirection) {
        x[0] -= DOT_SIZE;
    }

    if (rightDirection) {
        x[0] += DOT_SIZE;
    }

    if (upDirection) {
        y[0] -= DOT_SIZE;
    }

    if (downDirection) {
        y[0] += DOT_SIZE;
    }
}
```

#### Method after refactoring:
```
private void move() {
    for (int z = dots; z > 0; z--) {
        x[z] = x[(z - 1)];
        y[z] = y[(z - 1)];
    }
    int direction = allMoves.getCurrent() - 1;
    CommandMoviment moviment = CommandMoviment.values()[direction];
    makeMove move = moviment.getMoviment();
    int auxDots = move.makeMoviment(DOT_SIZE);
    if (direction == 0 || direction == 2) { // horizontal
        x[0] += auxDots;
    } else { // vertical
        y[0] += auxDots;
    }
}
```

In the class makeMoves the actions to be executed are indicated, if it is to the left the return is of -DOTSIZE, to the right +DOTSIZE and equal to above and below, so I only need an if and an else to make the snake move.

### Classes and Methods Affected:
The command pattern affected the **Board class** and the Move method.

### Affected Features:
The functionality affected was snake movement.



## Singleton:
The singleton pattern was used to have only one instance created from the Board class that controls all logic and game moves.

#### Method before refactoring:
```
private void initUI() {
        
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
```
#### Method after refactoring:
```
Snake Class:
private void initUI() {
    add(Board.getInstance());
    setResizable(false);
    pack();
    
    setTitle("Snake");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
```

```
Board Class:

private static Board uniqueInstance = new Board();
private Board() {
    initBoard();
}

protected static synchronized Board getInstance() {
    if (uniqueInstance == null) {
        uniqueInstance = new Board();
    }
    return uniqueInstance;
}
```
### Classes and Methods Affected:
The strategy pattern affected the **Board class** and the **initUI** method of the **Snake class**.

### Affected Features:
The affected functionality was to create only one instance of the **Board class**, which is where all program operation happens.


NOTE: The operation before and after the refactoring using the defaults is the same, and even new features have been added.
* A Score was added at the end of the game;
* The speed now varies according to how big the snake is, the larger the game will have its difficulty increased.
