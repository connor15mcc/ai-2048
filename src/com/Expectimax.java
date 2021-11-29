public class Expectimax {
    Integer maxDepth = 1;
    Integer maxBreadth = 400;

    public Integer findNextMove(Game g) {
        Integer bestMove = null;
        double bestScore = Integer.MIN_VALUE;
        for (int move : new int[]{0, 1, 2, 3}) {
            double score = calculateScore(g, move);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public double calculateScore(Game g, int move) {
        Game newBoard = new Game(g);
        newBoard.move(move);
        if (newBoard.getValues() == g.getValues()) {
            return Integer.MIN_VALUE;
        }
        return generateScore(newBoard, 0, maxDepth);
    }

    public double generateScore(Game g, int currD, int maxD) {
        if (currD >= maxD) {
            return calculateFinalScore(g);
        }
        int totalScore = 0;
        for (int i = 0; i < maxBreadth; i++) {
            totalScore += calculateMoveScore(g, currD, maxD);
        }
        return totalScore;
    }

    public double calculateMoveScore(Game g, int currD, int maxD) {
        double bestScore = 0;
        for (int move : new int[]{0, 1, 2, 3}) {
            Game newBoard = new Game(g);
            newBoard.move(move);
            newBoard.addTile();
            if (newBoard.getValues() != g.getValues()) {
                double score = generateScore(g, currD + 1, maxD);
                bestScore = Math.max(bestScore, score);
            }
        }
        return bestScore;
    }

    public double calculateFinalScore(Game g) {
        Heuristics h = new Heuristics();
        return h.calculateScore(g);
    }
}
