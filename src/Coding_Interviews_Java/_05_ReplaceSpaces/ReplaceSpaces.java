package Coding_Interviews_Java._05_ReplaceSpaces;

/**
 * 题目：
 * 请实现一个函数，把字符串中的每个空格替换成“%20”。
 * 例如，输入“We are happy.”，则输出“We%20are%20happy.”。
 *
 */
public class ReplaceSpaces {

    /**
     *
     * 2.1 O(n2)的解法
     * 　　最直观的做法是从头到尾扫描字符串，每一次碰到空格字符的时候做替换。由于是把1个字符替换成3个字符，我们必须要把空格后面所有的字符都后移两个字节，否则就有两个字符被覆盖了。下图展示了从前往后把字符串中的空格替换成'%20'的过程：
     *
     *
     *
     * 　　假设字符串的长度是n。对每个空格字符，需要移动后面O(n)个字符，因此对含有O(n)个空格字符的字符串而言总的时间效率是O(n2)。
     *
     * 2.2 O(n)的解法
     * 　　Step1.先遍历一次字符串，这样就能统计出字符串中空格的总数，并可以由此计算出替换之后的字符串的总长度。
     *
     * 　　以前面的字符串"We arehappy."为例，"We are happy."这个字符串的长度是14（包括结尾符号'\0'），里面有两个空格，因此替换之后字符串的长度是18。
     *
     * 　　Step2.从字符串的后面开始复制和替换。
     *
     * 　　准备两个指针，P1和P2。P1指向原始字符串的末尾，而P2指向替换之后的字符串的末尾。接下来向前移动指针P1，逐个把它指向的字符复制到P2指向的位置，直到碰到第一个空格为止。接着向前复制，直到碰到第二、三或第n个空格。
     *
     */
}
