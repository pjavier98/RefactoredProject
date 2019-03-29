package snake;

public class Moviments {
    private int left; // 1
    private int top; // 2
    private int right; // 3
    private int bottom; // 4
    private int current;
    // 0 -> nulo


    public Moviments(int left, int top, int right, int bottom, int current) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.current = current;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public int getCurrent() {
        return current;
    }
}
