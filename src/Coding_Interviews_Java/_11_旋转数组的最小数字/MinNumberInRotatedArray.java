package Coding_Interviews_Java._11_旋转数组的最小数字;

/**
 *
 * 面试题11：旋转数组的最小数字
 *
 * 题目：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 *
 */
public class MinNumberInRotatedArray {


    public static void main(String[] args){
        System.out.println("Hello World!");
        int[] numbers = {3,4,5,1,2};
        int min = solution(numbers, numbers.length);
        System.out.println("最小值：" + min);

        int[] numbers2 = {1,1,1,0,1};
        int min2 = solutionBetter(numbers2, numbers2.length);
        System.out.println("最小值：" + min2);
    }

    //第一种解法
    private static int solution(int[] numbers, int length) {
        if(numbers == null || length <= 0){
            throw new RuntimeException("Invalid parameters");
        }

        int first = 0;
        int last = length - 1;
        int middle = first;
        while(numbers[first] >= numbers[last]){
            if(last - first == 1){
                middle = last;
                break;
            }
            middle = (first + last)/2;
            if(numbers[middle] >= numbers[first]){
                first = middle;
            }else if(numbers[middle] <= numbers[last]){
                last = middle;
            }
        }
        return numbers[middle];
    }

    //解法的改进

    /**
     * 考虑例子{1,0,1,1,1}、{1,1,1,0,1}
     *
     *
     * @param numbers
     * @param length
     * @return
     */
    private static int solutionBetter(int[] numbers, int length){
        if(numbers == null || length <= 0){
            throw new RuntimeException("Invalid parameters");
        }
        int first = 0;
        int last = length - 1;
        int middle = first;
        while(numbers[first] >= numbers[last]){
            if(last - first == 1){
                middle = last;
                break;
            }
            middle = (first + last) / 2;
            //如果下标为firs、last和middle指向的三个数字相等，
            //则只能顺序查找
            if(numbers[first] == numbers[last] && numbers[first] == numbers[middle]){
                return minInOrder(numbers, first, last);
            }
            if(numbers[middle] >= numbers[first]){
                first = middle;
            }else if(numbers[middle] <= numbers[last]){
                last = middle;
            }
        }
        return numbers[middle];
    }

    private static int minInOrder(int[] numbers, int first, int last){
        int result = numbers[first];
        for(int i = first + 1; i <= last; i++){
            if(numbers[i] < result){
                result = numbers[i];
            }
        }
        return result;
    }
}
