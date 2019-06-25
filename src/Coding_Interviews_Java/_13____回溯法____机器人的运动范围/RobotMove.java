package Coding_Interviews_Java._13____回溯法____机器人的运动范围;

/**
 * 面试题13：机器人的运动范围
 * <p>
 * 题目：地上有一个m行n列的方格。一个机器人从坐标（0,0）的格子开始移动，
 * 它每次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7=18.
 * 但它不能进入方格（35,38），因为3+5+3+8=19.
 * 请问该机器人能够到达多少格子？
 */
public class RobotMove {
    public static void main(String[] args) {
        System.out.println("矩阵有18行，18列，一个机器人从坐标(0, 0)的格子开始移动。要求机器人不能进入行坐标和列坐标的数位之和大于18的格子");
        System.out.println(movingCount(1, 2, 2));
    }


    /**
     *
     * @param threshold 门槛，阀，界
     * @param rows 矩阵的行
     * @param cols 矩阵的列
     * @return 机器人能够到达的格子的数量
     */
    private static int movingCount(int threshold, int rows, int cols){
        if(threshold < 0 || rows <= 0 || cols <= 0){
            return 0;
        }
        boolean[] visited = new boolean[rows * cols];
        for(int i = 0; i < rows * cols; i++){
            visited[i] = false;
        }
        int count = movingCountCore(threshold, rows, cols, 0, 0, visited);
//        delete[] visited;
        return count;

    }

    private static int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[] visited) {
        int count = 0;
        if(check(threshold, rows, cols, row, col, visited)){
            visited[row * cols + col] = true;
            count = 1 + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                      + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                      + movingCountCore(threshold, rows, cols, row + 1, col, visited)
                      + movingCountCore(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    // 判断机器人能否进入坐标为（row, col）的方格，
    // 而函数getDigitSum用来得到一个数字的数位之和
    private static boolean check(int threshold, int rows, int cols, int row, int col, boolean[] visited){
        if(row >= 0 && row < rows
                && col >= 0 && col < cols
                && getDigitSum(row)+getDigitSum(col)<= threshold
                && !visited[row * cols + col]){
            return true;
        }
        return false;
    }

    //用来的到一个数字的各数位之和。如getDigitSum(35) == 8
    private static int getDigitSum(int number){
        int sum = 0;
        while(number > 0){
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }




}
