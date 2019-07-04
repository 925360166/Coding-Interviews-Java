package Coding_Interviews_Java._17____打印从1到最大的n位数;

/**
 * 面试题17：打印从1到最大的n位数
 * <p>
 * 题目：
 * 输入数字n，按顺序打印出从1到最大的n位的十进制数。
 * 比如输入3，则打印出1、2、3一直到最大的3位数999.
 * <p>
 * 作者：程序猿li
 * 来源：CSDN
 * 原文：https://blog.csdn.net/u013132035/article/details/80563507
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class Print1ToMaxOfNDigits {

    public static void main(String[] args) {
//        printToMaxOfNDigits(2);
//        printToMaxOfNDigits_better(2);
        printToMaxOfDigits_Recursively(2);
    }

    /**
     * 上面的代码考虑的问题很简单，默认为最大数值为long型，
     * 没有考虑长整型也会溢出的情况。故上面的代码鲁棒性很差。
     *
     * @param n
     */
    public static void printToMaxOfNDigits(int n) {
        long number = 1;
        int i = 0;
        while (i < n) { //利用while循环计算出比n位十进制多1的数
            number *= 10;
            ++i;
        }
        for (long j = 1; j < number; j++) { //利用for循环输出从1到最大的n位数
            System.out.println(j);
        }
    }

    /**
     * 鲁棒性好的思路：
     * <p>
     * 因为上面分析可知我们在遇到比长整型还大的数时，上面的代码是不能成功执行的，即基本类型已经存储不下的数字。
     * 这时我们需要考虑用字符串或者数组来表示大数。
     * <p>
     * 用字符串表示数字的时候，最直观的方法就是字符串里每个字符都是‘0’到‘9’之间的某一个字符，用来表示数字中的一位。
     * 因为最大的是n位的，因此我们需要一个长度为n+1的字符串（字符串中最后一个是结束符号‘\0’。当实际数字不够n位的时候，在字符串的前半部分补0）。
     * <p>
     * 首先我们把字符串中的每一个数字都初始化为‘0’，然后每一次为字符串表示的数字加1，再打印出来。
     * 故我们只需要做两件事：一是在字符串表达数字上模拟加法，二是把字符串表达的数字打印出来。
     * <p>
     * 在字符串表达数字上模拟加法，我们首先设置是否溢出标识，是否进位标识，以及取得字符数组长度，
     * 遍历这个字符数组，在末尾进行+1操作，如果末尾字符在+1后变为不小于10的数字，我们将末尾减去10加上‘0’字符赋值为末位，进位标识设置为1，
     * 在循环次位时+1，然后再判断是否为不小于10，是的话重复上面的步骤。
     * 直到判断高位是不是不小于10，是的话字符数组溢出；
     * 如果末尾字符在+1后是小于10的数字，直接加上‘0’赋值给末尾，跳出当前循环，返回没有溢出。
     * <p>
     * 在字符串表达的数字打印出来方法时，没有什么特别，直接利用for循环遍历输出字符数组，但是要从高位第一个不是0的开始输出。
     * ---------------------
     * 作者：程序猿li
     * 来源：CSDN
     * 原文：https://blog.csdn.net/u013132035/article/details/80563507
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */


    //打印1到最大的n位数
    public static void printToMaxOfNDigits_better(int n) {
        if (n <= 0) {
            System.out.println("输入的n没有意义");
            return;
        }
        //声明字符数组,用来存放一个大数
        char number[] = new char[n];
        for (int i = 0; i < number.length; ++i) { //放字符0进行初始化
            number[i] = '0';
        }
        while (!incrementNumber(number)) { //如果大数自加，直到自溢退出
            printNumber(number); //打印大数
        }
    }


    //自加
    private static boolean incrementNumber(char[] number) {
        boolean isOverflow = false; //判断是否溢出
        int nTakeOver = 0; //判断是否进位
        int nLength = number.length;
        for (int i = nLength - 1; i >= 0; --i) {
            int nSum = number[i] - '0' + nTakeOver; //取到第i位的字符转换为数字 +进位符
            if (i == nLength - 1) { //末尾自加1
                ++nSum;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isOverflow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) (nSum + '0');
                break;
            }
        }
        return isOverflow;
    }

    //打印数字
    private static void printNumber(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; ++i) {
            if (isBeginning0 && number[i] != '0') {
                isBeginning0 = false;
            }
            if (!isBeginning0) {
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }


    /**
     *
     * 递归:牛逼！
     *
     */


    //打印1到最大的n位数的主方法
    public static void printToMaxOfDigits_Recursively(int n){
        if(n <= 0){
            System.out.println("输入的n没有意义");
            return;
        }
        char number[] = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        for (int i = 0; i < 10; ++i) {
            number[0] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, 0);
        }
    }
    //利用递归实现1到最大的n位数的全排列
    public static void printToMaxOfNDigitsRecursively(char[] number, int n, int index) {
        if(index == n - 1){
            printNumber_Recursively(number);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            number[index + 1] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, index + 1);
        }
    }

    //输出
    private static void printNumber_Recursively(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; ++i) {
            if(isBeginning0 && number[i]!='0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }

}
