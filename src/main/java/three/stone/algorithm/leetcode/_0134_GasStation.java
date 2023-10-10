package three.stone.algorithm.leetcode;

public class _0134_GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0, n = gas.length;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if (totalGas < totalCost) {
            return -1;
        }

        int remainGas = 0, start = 0;
        for (int i = 0; i < n; i++) {
            remainGas += gas[i];
            remainGas -= cost[i];
            if (remainGas < 0) {
                remainGas = 0;
                start = i + 1;
            }
        }
        return start;
    }
}
