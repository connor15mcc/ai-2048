public class Heuristics {
    float score;
    static float freeSquareWeight = 1;
    static float monotonicityWeight = 1;

    public float calculateScore(Game g) {
        float freeSquareScore = calcFreeSquareScore(g);
        float monotonicityScore = calcMonoScore(g);
        System.out.println("FSS: " + freeSquareScore + ", MTS: " + monotonicityScore);
        return freeSquareScore + monotonicityScore;
    }

    private float calcFreeSquareScore(Game g) {
        return g.countFreeSquares() * freeSquareWeight;
    }

    private float calcMonoScore(Game g) {
        Integer[][] values = g.getValues();
        float rowScore = 0;
        float colScore = 0;
        for (int i = 0; i < g.size; i++) {
            rowScore -= monotonicityHelper(g.getRow(i));
            colScore -= monotonicityHelper(g.getCol(i));
        }
        float score = rowScore + colScore;
        return score * monotonicityWeight;
    }

    private int tileRank(int i) {
        int result = (int) (Math.log(i) / Math.log(2));
        return result;
    }

    private float monotonicityHelper(Integer[] val) {
        int len = val.length;
        float decrScore = 0;
        float incrScore = 0;
        int curr = 0;
        while (curr < len) {
            while (curr < len && val[curr] == null) {
                curr++;
            }
            int next = curr + 1;
            while (next < len && val[next] == null) {
                next++;
            }
            if (curr >= len || next >= len) return incrScore + decrScore;

            Integer currValue = tileRank(val[curr]);
            Integer nextValue = tileRank(val[next]);

            if (nextValue > currValue) {
                incrScore += nextValue - currValue;
            } else if (nextValue < currValue) {
                decrScore += currValue - nextValue;
            }
            curr++;
        }
        System.out.println(incrScore);
        System.out.println(decrScore);
        return incrScore + decrScore;
    }


}
