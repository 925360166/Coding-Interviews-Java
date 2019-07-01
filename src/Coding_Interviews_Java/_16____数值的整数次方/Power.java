package Coding_Interviews_Java._16____数值的整数次方;

/**
 * 面试题16：数值的整数次方
 * <p>
 * 题目：实现函数double Power(double base, int exponent),
 * 求base的exponent次方。不得使用库函数，同事不需要考虑大数问题。
 */
public class Power {

    public static void mian(String[] args) {
        System.out.println("Hello World");
    }

    /**
     * 解题思路:
     * <p>
     * 指数为负时，可以先对指数求绝对值，算出次方的结果后再取倒数
     * 当底数为0，指数为负时，会出现对0求倒数情况，要特殊处理
     * 0的0次方在数学上没有意义，因此无论输出0还是1都是可以接受的
     * 在计算次方的时候，除了简单的遍历，我们可以使用递归的思想，如下公式，来减少计算量：
     *
     * @param base     底数
     * @param exponent 指数
     * @return 结果
     */
    private static double Power(double base, int exponent) {
        int n = exponent;
        if (exponent == 0) {
            //当指数为0底数为0时，没有意义，返回0或者返回1都可以
            return 1;
        } else if (exponent < 0) {
            if (base == 0) {
                throw new RuntimeException("分母不能为0");
            }
            n = -exponent;
        }
        double res = PowerUnsighedExponent(base, n);
        return exponent < 0 ? 1 / res : res;
    }

    /**
     * @param base 底数
     * @param n    指数
     * @return 结果
     */
    private static double PowerUnsighedExponent(double base, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }
        //递归
        double res = PowerUnsighedExponent(base, n / 2);
        res *= res;
        if (n % 2 == 1) {
            res *= base;
        }
        return res;
    }

    /**
     * 代码优化：
     * 可以使用右移运算符代替除以2，
     * 用位与运算符（&）代替求余运算符（%）来判断一个数是奇数还是偶数
     *
     * @param base 底数
     * @param n    指数
     * @return 结果
     */
    private double PowerUnsighedExponent_2(double base, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }
        //递归
        double res = PowerUnsighedExponent_2(base, n >> 1);
        res *= res;
        if ((n & 1) == 1) {
            res *= base;
        }
        return res;
    }
}
