package Coding_Interviews_Java._04____数组____二维数组中的查找;

/**
 * 面试题四：二维数组中的查找
 * <p>
 * 题目：
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * <p>
 * 解题思路：
 * 首先选取数组中右上角的数字。
 * 如果该数字等于要查找的数字，查找过程结束；
 * 如果该数字大于要查找的数字，剔除这个数字所在的列；
 * 如果该数字小于要查找的数字，剔除这个数字所在的行。
 * 也就是说如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围中剔除一行或者一列，
 * 这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找范围为空。
 * <p>
 * https://www.cnblogs.com/edisonchou/p/4737944.html
 */
public class FindInPartiallySortedMatrix {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    private static boolean find(int[][] martrix, int rows, int columns, int number) {

        boolean found = false;
        if (martrix != null && rows > 0 && columns > 0) {

            //从第一行开始
            int row = 0;
            //从最后一列开始
            int column = columns - 1;
            //查找时，行：从上到下，列：从右到左
            while (row < rows && column >= 0) {
                if (martrix[row][column] == number) {
                    found = true;
                    break;
                } else if (martrix[row][column] > number) {
                    column--;
                } else {
                    row++;
                }
            }
        }
        return found;
    }
}














