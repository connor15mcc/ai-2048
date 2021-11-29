import java.util.HashMap;

public class Heuristics {
    static double freeSquareWeight = 10;
    static double monotonicityWeight = 1;
    static double smoothnessWeight = 2;

    public double calculateScore(Game g) {
        double freeSquareScore = calcFreeSquareScore(g);
        double monotonicityScore = calcMonoScore(g);
        double smoothnessScore = calcSmoothnessScore(g);
//        System.out.println("FSS: " + freeSquareScore + ", MTS: " + monotonicityScore + ", SS:" + smoothnessScore);
        return freeSquareScore + monotonicityScore + smoothnessScore;
    }

    private double calcSmoothnessScore(Game g) {
        Integer result = 0;
        HashMap<Integer, Integer> counter = new HashMap<>();
        Integer[][] val = g.getValues();
        for (int i = 0; i < g.size; i++) {
            for (int j = 0; j < g.size; j++) {
                Integer k = val[i][j];
                if (k == null) {
                    continue;
                } else if (counter.containsKey(k)) {
                    int oldValue = counter.get(k);
                    counter.put(k, oldValue + 1);
                } else {
                    counter.put(k, 1);
                }
            }
        }
        for (Integer k : counter.keySet()) {
            result += k * counter.get(k);
        }
        return result * smoothnessWeight;
    }

    private double calcFreeSquareScore(Game g) {
        return g.countFreeSquares() * freeSquareWeight;
    }

    private double calcMonoScore(Game g) {
        Integer[][] values = g.getValues();
        Integer[] pathIndex1 = {0, 1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 15, 14, 13, 12};
        Integer[] valuesLin = new Integer[g.size * g.size];
        for (int i = 0; i < g.size; i++) {
            for (int j = 0; j < g.size; j++) {
                valuesLin[g.size * i + j] = values[i][j];
            }
        }
        Integer[] pathValues1 = new Integer[g.size * g.size];
        for (int i = 0; i < g.size * g.size; i++) {
            pathValues1[i] = valuesLin[pathIndex1[i]];
        }
        double monotonicityScore = monotonicityHelper(pathValues1);
        return monotonicityWeight * monotonicityScore;
    }

    private int tileRank(int i) {
        int result = (int) (Math.log(i) / Math.log(2));
        return result;
    }

    private double monotonicityHelper(Integer[] val) {
        int len = val.length;
        float incrScore = 0;
        int curr = 0;
        while (curr < len) {
            int next = curr + 1;
            if (next >= len) return incrScore;

            Integer currValue = (val[curr] == null) ? 0 : tileRank(val[curr]);
            Integer nextValue = (val[next] == null) ? 0 : tileRank(val[next]);

            if (nextValue > currValue) {
                incrScore -= (nextValue - currValue) * (len - curr);
            } else if (nextValue < currValue) {
                incrScore += (currValue - nextValue) * (len - curr);
            }
            curr++;
        }
        return incrScore;
    }


}
