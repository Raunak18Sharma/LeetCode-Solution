import java.util.ArrayList;
import java.util.List;

public class Solution {
    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) {
            list.add((double) card);
        }
        return solve(list);
    }

    private boolean solve(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24) < 1e-6;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) continue;
                List<Double> newList = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != i && k != j) {
                        newList.add(list.get(k));
                    }
                }
                for (int k = 0; k < 4; k++) {
                    if (k < 2 && i > j) continue; // Avoid duplicate operations for + and *
                    double a = list.get(i);
                    double b = list.get(j);
                    double res = 0;
                    switch (k) {
                        case 0: res = a + b; break;
                        case 1: res = a * b; break;
                        case 2: res = a - b; break;
                        case 3: 
                            if (b == 0) continue;
                            res = a / b; 
                            break;
                    }
                    newList.add(res);
                    if (solve(newList)) {
                        return true;
                    }
                    newList.remove(newList.size() - 1);
                }
            }
        }
        return false;
    }
}