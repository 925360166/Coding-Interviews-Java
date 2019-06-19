package Coding_Interviews_Java._10____递归和循环____斐波那契数列;

/**
 *
 * 面试题10：斐波那契数列
 *
 * 题目一：求斐波那契数列的第n项。
 * 写一个函数，输入n，求斐波那契（Fibonacci）数列的第n项。
 * 斐波那契数列的定义如下：
 *         0                n=0
 * f(n) =  1                n=1
 *         f(n-1) + f(n-2)  n>1
 *
 */
public class Fibonacci {

    public static void main(String[] args){
        System.out.print("Hello World!");

    }

    /**
     * 1、用递归方法计算的时间复杂度是以n的指数的方式递增的。
     * 递归调用过程的树形结构中的重复节点会随着n的增大而急剧增大《剑指Offer（第二版）》P75
     */

    public static long Fibonacci1(int n){
        if(n <= 1)
            return n;
        else
            return Fibonacci1(n-1) + Fibonacci1(n-2);
    }


    /**
     * 2、面试官期待的实用解法
     * 更简单的方式是从下往上计算，首先根据f(0)和f(1)算出f(2),
     * 再根据f(1)和f(2)算出f(3)......以此类推就可以算出第n项了。
     * 很容量理解，这种思路的时间复杂度是O(n)
     *
     */
    public static long Fibonacci2(int n){
        if(n == 0 || n == 1){
            return n;
        }
        int fn1 = 0;
        int fn2 = 1;
        for(int i = 2; i <= n; i++){
            fn2 = fn1 + fn2;
            fn1 = fn2 - fn1;
        }
        return fn2;
    }
}
