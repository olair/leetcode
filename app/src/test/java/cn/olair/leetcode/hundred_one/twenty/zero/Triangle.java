package cn.olair.leetcode.hundred_one.twenty.zero;

import java.util.List;

/**
 * 动态规划
 * 给定一个三角形，找出自顶到底的最小和，每次你只能在下层最邻近的左右两个数之间移动。
 *
 * 比如：
 * [
 *    [2],
 *   [3,4],
 *  [6,5,7],
 * [4,1,8,3]
 * ]
 *
 * 这个三角的最小和为2+3+5+1=11。
 *
 * Created by olair on 18.1.3.
 */

public class Triangle {

    /**
     * 感觉自己真的是逗比到爆，思考出来的解决方案往往与LeetCode上提交的方案的解题思路产生颠倒，怎么个颠倒呢？
     * 比如这个，我是从上到下的思考，实际上从下到上的来可以很简单的避免这次计算覆盖掉上次计算的值的问题。
     * 可是我偏偏注意不到反过来看一看，于是只能不断的加重作业.
     * (需要说明的是，从上到下有不需要两个数组的方案，只需要每次赋值向右对齐，但是同样加重了作业。)
     * 还有一次是我记得好像要存一个数据，LeetCode上存的是非0的相关数据，我存的是0相关的，不过好在这次没有加重很大负担，
     * 但是我很早之前就在心里暗暗提醒了自己要注意反过来看一看，只能再次对自己强调了。
     *
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null)
            return 0;
        int[] target1 = new int[triangle.get(triangle.size() - 1).size()];
        int[] target2 = new int[triangle.get(triangle.size() - 1).size()];
        target2[0] = triangle.get(0).get(0);
        boolean isOne = false;
        // i为第几层
        for (int i = 1; i < triangle.size(); i++) {
            isOne = !isOne;
            List<Integer> list = triangle.get(i);
            int len = list.size();
            for (int j = 0; j < len; j++) {
                if (isOne) {
                    if (j == 0) {
                        target1[j] = target2[j] + triangle.get(i).get(j);
                    } else if (j == len - 1) {
                        target1[j] = target2[j - 1] + triangle.get(i).get(j);
                    } else {
                        target1[j] = (target2[j] < target2[j - 1] ? target2[j] : target2[j - 1])
                                + triangle.get(i).get(j);
                    }
                } else {
                    if (j == 0) {
                        target2[j] = target1[j] + triangle.get(i).get(j);
                    } else if (j == len - 1) {
                        target2[j] = target1[j - 1] + triangle.get(i).get(j);
                    } else {
                        target2[j] = (target1[j] < target1[j - 1] ? target1[j] : target1[j - 1])
                                + triangle.get(i).get(j);
                    }
                }
            }
        }

        int num;
        int[] target = target2;
        if (isOne) {
            target = target1;
        }
        num = target[0];
        for (int i : target) {
            if (num > i) {
                num = i;
            }
        }

        return num;
    }

}
