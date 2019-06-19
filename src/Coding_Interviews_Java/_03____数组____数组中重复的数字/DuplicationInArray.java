package Coding_Interviews_Java._03____数组____数组中重复的数字;

import java.util.Arrays;

/**
 * 题目一：找出数组中重复的数字。
 * <p>
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3
 * <p>
 * <p>
 * 题目二：不修改数组找出重复的数字。
 * <p>
 * 在一个长度为n+1的数组里的所有数字都在1~n的范围内，所以数组中至少有一个数字是重复的。
 * 请找出数组中任意一个重复的数字，但不能修改输入的数组。
 * 例如，如果输入长度为8的数组{2,3,5,4,3,2,6,7}，那么对应的输出是重复的数字2或者3。
 */
public class DuplicationInArray {

    public static void main(String[] args) {

        int[] numbers = new int[]{2, 3, 1, 0, 2, 5, 3};
        int length = 7;
        int[] duplication = new int[1];

        if (findDuplicate1_1(numbers, length, duplication)) {
            System.out.println("findDuplicate1_1, true: " + duplication[0]);
        } else {
            System.out.println("findDuplicate1_1, false");
        }



        int[] numbers2 = new int[]{2, 3, 1, 0, 2, 5, 3};
        int length2 = 7;
        int[] duplication2 = new int[1];
        if (findDuplicate1_2(numbers2, length2, duplication2)) {
            System.out.println("findDuplicate1_2, true: " + duplication2[0]);
        } else {
            System.out.println("findDuplicate1_2, false");
        }
    }

    /**
     * 解题思路 https://www.weiweiblog.cn/duplicate/
     * <p>
     * 最简单的就是用一个数组或者哈希表来存储已经遍历过的数字，但是这样需要开辟额外的空间。
     * 如果题目要求不能开辟额外的空间，那我们可以用如下的方法：
     * 因为数组中的数字都在0~n-1的范围内，
     * 所以，如果数组中没有重复的数，那当数组排序后，数字i将出现在下标为i的位置。
     * 现在我们重排这个数组，从头到尾扫描每个数字，当扫描到下标为i的数字时，
     * 首先比较这个数字(记为m)是不是等于i。如果是，则接着扫描下一个数字；
     * 如果不是，则再拿它和m 位置上的数字进行比较，
     * 如果它们相等，就找到了一个重复的数字（该数字在下标为i和m的位置都出现了），返回true；
     * 如果它和m位置上的数字不相等，就把第i个数字和第m个数字交换，把m放到属于它的位置。
     * 接下来再继续循环，直到最后还没找到认为没找到重复元素，返回false。
     * <p>
     * <p>
     * <p>
     * 思路：https://www.cnblogs.com/AndyJee/p/4693099.html
     * <p>
     * 1、排序
     * 将数组排序，然后扫描排序后的数组即可。
     * 时间复杂度：O(nlogn)，空间复杂度:O(1)
     * <p>
     * 2、哈希表
     * 从头到尾扫描数组，每扫描到一个数字，判断该数字是否在哈希表中，如果该哈希表还没有这个数字，那么加入哈希表，如果已经存在，则返回该数字；
     * 时间复杂度：O(n)，空间复杂度:O(n)
     * <p>
     * 3、交换
     * 0~n-1正常的排序应该是A[i]=i；因此可以通过交换的方式，将它们都各自放回属于自己的位置；
     * 从头到尾扫描数组A，当扫描到下标为i的数字m时，首先比较这个数字m是不是等于i，
     * 如果是，则继续扫描下一个数字；
     * 如果不是，则判断它和A[m]是否相等，如果是，则找到了第一个重复的数字（在下标为i和m的位置都出现了m）；如果不是，则把A[i]和A[m]交换，即把m放回属于它的位置；
     * 重复上述过程，直至找到一个重复的数字；
     * 时间复杂度：O(n)，空间复杂度：O(1)
     * (将每个数字放到属于自己的位置最多交换两次)
     *
     * @return
     */


    /**
     * 我想到的方法，先将数组排序，如果有重复元素将会相邻。然后相邻元素两两比较即可
     *
     * @param numbers     待查找的数组
     * @param length      数组的长度，其实就是numbers.length
     * @param duplication 用于保存重复数字，第一个被找到的重复数字存放在duplication[0]中
     * @return 如果在数组中有重复元素
     */
    private static boolean findDuplicate1_1(int numbers[], int length, int[] duplication) {
        if (numbers == null || length == 0) {
            return false;
        }

        Arrays.sort(numbers);
        for (int i = 0; i < length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }


    /**
     * 推荐的做法，通过交换元素，将值i保存到numbers[i]
     * 在numbers[i]不和i相等时，如果numbers[i]和numbers[numbers[i]]相等就说明重复元素；
     * 否则就交换这两个元素，这个过程相当于排序。举个例子，通过交换将2放入numbers[2]。
     */
    private static boolean findDuplicate1_2(int numbers[], int length, int[] duplication) {
        if (numbers == null || length <= 0) {
            return false;
        }
        for (int a : numbers) {
            if (a < 0 || a > length - 1)
                return false;
        }

        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                // 现在numbers[i] != i ，设numbers[i] = j,
                // 所以如果下面的if成立,就是numbers[i] == numbers[j],说明找到重复
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, i);
            }
        }
        return false;
    }

    // swap number[i] and numbers[numbers[i]]
    private static void swap(int[] numbers, int i) {
        int value = numbers[i];
        numbers[i] = numbers[value];
        numbers[numbers[i]] = value;
    }


    ///////////////////题目2：不修改数组找出重复的数字////////////////////////
    private static int findDuplicate2(int numbers[], int length){
        if(numbers == null || length <= 0){
            return -1;
        }

        int start = 1;
        int end = length - 1;
        while(end >= start ){
            int middle = ((end - start) / 2) + start;
            int count  = countRange(numbers, length, start, middle);
            if(end == start){
                if(count > 1) return start;
                else break;
            }

            if(count > (middle - start + 1))
                end = middle;
            else
                start = middle + 1;
        }
        return -1;
    }

    private static int countRange(int numbers[], int length, int start, int end){
        if(numbers == null)
            return 0;
        int count = 0;
        for(int i = 0; i < length; i++){
            if(numbers[i] >= start && numbers[i] <= end){
                ++count;
            }
        }
        return count;
    }

}
