package snake;

import interfaces.makeMove;
import interfaces.setMove;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private final int B_WIDTH = 300; // largura da tela
    private final int B_HEIGHT = 300; // comprimento da tela
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private int DELAY = 100;
    private int SCORE = 0; // pontuacao

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots; // tamanho da cobra
    private int apple_x; // coordenada x da maça
    private int apple_y; // coordenada y da maça

    private int getDELAY() {
        return DELAY;
    }

    private void setDELAY(int DELAY) {
        this.DELAY = DELAY;
    }

    private Moviments allMoves = new Moviments(0, 0, 3, 0, 3); // objeto que tem os movimentos da cobra
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    private static Board uniqueInstance = new Board(); // Singleton
    private Board() {
        initBoard();
    }

    protected static synchronized Board getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Board();
        }
        return uniqueInstance;
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("src/images/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/images/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/images/head.png");
        head = iih.getImage();
    }

    private void initGame() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();
        timer = new Timer(getDELAY(), this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over (Score: " + SCORE + ")";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
            timer.setDelay(getDELAY());
            setDELAY(getDELAY() - 5);
            SCORE += 1;
        }
    }

    private void move() { // Strategy
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

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
        if (y[0] >= B_HEIGHT || y[0] < 0 || x[0] >= B_WIDTH || x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int RAND_POS = 29;
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter { // Strategy
        @Override
        public void keyPressed(KeyEvent e) {
            StrategyMoviment moviment = StrategyMoviment.values()[e.getKeyCode() - 37];
            setMove move = moviment.setMoviment();
            allMoves = move.setMoviment(allMoves);
        }
    }
}
