public class Spelplan {
    private int [] spelplan;

    public Spelplan() {
        spelplan = new int [100];
    }
    public static void main (String[] args) {
    }
    public class World {
        private int width;
        private int height;

        public World(int w, int h) {
            this.width = w;
            this.height = h;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
       }
       

}
