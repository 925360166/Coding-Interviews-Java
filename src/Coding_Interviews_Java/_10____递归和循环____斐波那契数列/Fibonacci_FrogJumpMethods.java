package Coding_Interviews_Java._10____递归和循环____斐波那契数列;

/**
 * 题目2：青蛙跳台阶问题
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶
 * 求该青蛙跳上一级n级的台阶总共有多少种跳法
 *
 * 解答：https://blog.csdn.net/weixin_40244153/article/details/87730450
 */
public class Fibonacci_FrogJumpMethods {

    public static void main(String[] args){
        System.out.print("6级台阶的方法数：" + solution2(6));
    }

    /**
     *
     * 答题思路
     *
     * 1.如果只有1级台阶，那显然只有一种跳法
     * 2.如果有2级台阶，那么就有2种跳法，一种是分2次跳。每次跳1级，另一种就是一次跳2级
     * 3.如果台阶级数大于2，设为n的话，这时我们把n级台阶时的跳法看成n的函数，记为f(n),
     *   第一次跳的时候有2种不同的选择：
     *   一是第一次跳一级，此时跳法的数目等于后面剩下的n-1级台阶的跳法数目，即为f(n-1),
     *   二是第一次跳二级，此时跳法的数目等于后面剩下的n-2级台阶的跳法数目，即为f(n-2),
     *   因此n级台阶的不同跳法的总数为，不难看出就是斐波那契数列
     *
     */

    /**
     * 递归方法
     *
     * 时间复杂度O(2^n),空间复杂度O(n)。
     */
    public static int solution1(int n){
        if(n <= 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return solution1(n - 1) + solution1(n - 2);
    }

    /**
     * 循环实现
     *
     * @param n
     * @return
     */
    public static int solution2(int n){
        int[] res = {0, 1};
        if(n < 2){
            return res[n];
        }

        int fibMinusOne = 1;
        int fibMinusTwo = 0;
        int fibN = 0;
        for(int i = 2; i <= n; i++){
            fibN = fibMinusOne + fibMinusTwo;
            fibMinusTwo = fibMinusOne;
            fibMinusOne = fibN;
        }
        return fibN;
    }
}
