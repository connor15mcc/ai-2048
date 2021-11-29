class Main {

    public static void main(String[] args) {
        Game g = new Game();
        Expectimax e = new Expectimax();
        Heuristics h = new Heuristics();

        System.out.println("Welcome to the a new game of 2048:");
        System.out.println(g);


        while (!g.gameOver()) {
            Integer dir = e.findNextMove(g);
            if (dir == null) {
                break;
            }

            System.out.println("*".repeat(15));
            System.out.println("Score: " + h.calculateScore(g) + ", Direction: " + dir);
            System.out.println(g);
            g.move(dir);
            System.out.println(g);
            g.addTile();
            System.out.println(g);
            System.out.println("*".repeat(15));


        }
    }
}