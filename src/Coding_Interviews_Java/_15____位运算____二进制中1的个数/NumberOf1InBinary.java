package Coding_Interviews_Java._15____位运算____二进制中1的个数;

/**
 * 面试题15：二进制中1的个数
 * <p>
 * 题目：请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。
 * 例如：把9表示成二进制是1001，有2位是1。
 * 因此，如果输入9，则该函数输出2。
 */
public class NumberOf1InBinary {

    public static void main(String[] args) {
        int n = 0x80000000;
        //Integer.MAX_VALUE = 0x7fffffff  2<sup>31</sup>-1
        //Integer.MIN_VALUE = 0x80000000  -2<sup>31</sup>
        //System.out.println("Integer.MAX_VALUE = 0x7fffffff :" + 0x7fffffff);
        //System.out.println("Integer.MIN_VALUE = 0x80000000 :" + 0x80000000);


//      测试用例：
//      正数：（包括边界值 1、0x7fffffff）
//      负数：（包括边界值 0x80000000、oxFFFFFFFF）,oxFFFFFFFF:负数值是取补码+1，所以为-1
        System.out.println("numberOf1 in " + n + ": " + numberOf1(n));
        System.out.println("numberOf1 in " + n + ": " + numberOf1_better(n));
    }


    /**
     * 不好的解法：
     * <p>
     * 首先Interger类中提供了现有方法
     * Integer.bitCount(n);
     * 要统计二进制数中1的个数，首先想到的思路是让该整数与1进行“与运算”，
     * 然后向右移位来得到1的个数，但是负数用补码表示，最高位是1，这样造成死循环
     *
     * @param n 输入值
     * @return numberOf1
     */
    private static int numberOf1_CanNotUse(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    /**
     * 常规解法：
     * <p>
     * 为了避免死循环，我们可以不右移输入的数字n。首先把n和1做与运算，判断n的最低位是不是为1.
     * 接着把1左移一位得到2，再和n做与运算，就能判断n的次低位是不是1.。。。。。
     * 这样反复左移，每次都能判断n的其中一位是不是1.
     * 基于这种思路，我们可以把代码修改如下：
     *
     * @param n 输入值
     * @return numberOf1
     */
    private static int numberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    /**
     * 解题思路
     * 如果一个整数不为0，那么这个整数至少有一位是1。
     * 如果我们把这个整数减1，那么原来处在整数最右边的1就会变为0，
     * 原来在1后面的所有的0都会变成1(如果最右边的1后面还有0的话)。
     * 其余所有位将不会受到影响。
     * <p>
     * 举个例子：一个二进制数1100，从右边数起第三位是处于最右边的一个1。
     * 减去1后，第三位变成0，它后面的两位0变成了1，而前面的1保持不变，
     * 因此得到的结果是1011.
     * 我们发现减1的结果是把最右边的一个1开始的所有位都取反了。
     * 这个时候如果我们再把原来的整数和减去1之后的结果做与运算，
     * 从原来整数最右边一个1那一位开始所有位都会变成0。
     * 如1100&1011=1000.
     * 也就是说，把一个整数减去1，再和原整数做与运算，
     * 会把该整数最右边一个1变成0.
     * 那么一个整数的二进制有多少个1，就可以进行多少次这样的操作。
     *
     * @param n 输入值
     * @return numberOf1
     */
    private static int numberOf1_better(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }


}
