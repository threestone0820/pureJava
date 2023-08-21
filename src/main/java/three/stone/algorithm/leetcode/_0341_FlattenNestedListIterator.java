package three.stone.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _0341_FlattenNestedListIterator implements Iterator<Integer> {
     interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    private List<Integer> list = new ArrayList<>();
     int index = 0;

    public _0341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        flattenNestedInteger(nestedList);
    }

    private void flattenNestedInteger(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                flattenNestedInteger(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

}
