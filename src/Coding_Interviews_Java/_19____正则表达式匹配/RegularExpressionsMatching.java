package Coding_Interviews_Java._19____正则表达式匹配;

/**
 * 面试题19：正则表达式匹配
 * <p>
 * 题目：请实现一个函数用来匹配包含'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配
 */
public class RegularExpressionsMatching {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    /**
     * 解题思路
     * 当模式中的第二个字符不是“*”时：
     * 1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
     * 2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。
     * <p>
     * 而当模式中的第二个字符是“*”时：
     * 如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
     * 1、模式后移2字符，相当于x*被忽略；
     * 2、字符串后移1字符，模式后移2字符；
     * 3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位。
     *
     * @return 是否匹配上
     */
    private static boolean match(char[] str, char[] pattern) {
        int sIndex = 0, pIndex = 0;
        return matchCore(str, sIndex, pIndex, pattern);
    }

    private static boolean matchCore(char[] str, int sIndex, int pIndex, char[] pattern) {
        if (sIndex >= str.length && pIndex == pattern.length) {
            return true;
        }
        if (pIndex >= pattern.length && sIndex < str.length) {
            return false;
        }

        //如果pattern下一个字符是'*'
        if (pIndex + 1 < pattern.length && pattern[pIndex + 1] == '*') {
            if (sIndex < str.length && (str[sIndex] == pattern[pIndex] || pattern[pIndex] == '.')) {
                return matchCore(str, sIndex, pIndex + 2, pattern) ||
                        matchCore(str, sIndex + 1, pIndex + 2, pattern) ||
                        matchCore(str, sIndex + 1, pIndex, pattern);
            } else {
                return matchCore(str, sIndex, pIndex + 2, pattern);
            }
        }
        if (sIndex < str.length && (str[sIndex] == pattern[pIndex] || pattern[pIndex] == '.')) {
            return matchCore(str, sIndex + 1, pIndex + 1, pattern);
        }
        return false;
    }
}
