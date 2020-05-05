public class Spelplan {
    private int [] spelplan;
    private int width;
    private int height;

    public Spelplan(int w, int h) {
        this.width = w;
        this.height = h;
        spelplan = new int [w*h];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static void main (String[] args) {
    }



}
