package Coding_Interviews_Java._12____回溯法____矩阵中的路径;

/**
 * 面试题12：矩阵中的路径
 * <p>
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如，在下面的3x4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用下划线标出）。
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子字后，
 * 路径不能再次进入这个格子。
 * <p>
 * a  b  t  g
 * c  f  c  s
 * j  d  e  h
 */
public class StringPathInMatrix {

    public static void main(String[] args) {

        /**
         *
         * =======测试代码=======
         *
         */

        /**
         *  A B T G
         *  C F C S
         *  J D E H
         */
        //BFCTB
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }

    //BFCTB
    private static void test1() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "BFCTB".toCharArray();
        if(!hasPath(matrix, rows, cols, str)){
            System.out.println("Test1 passed.");
        }else{
            System.out.println("Test1 failed.");
        }
    }

    //BFCE
    private static void test2() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "BFCE".toCharArray();
        if(hasPath(matrix, rows, cols, str)){
            System.out.println("Test2 passed.");
        }else{
            System.out.println("Test2 failed.");
        }
    }

    //matrix = null
    private static void test3() {
        char[] matrix = null;
        int rows = 0;
        int cols = 0;
        char[] str = "BFCE".toCharArray();
        if(!hasPath(matrix, rows, cols, str)){
            System.out.println("Test3 passed.");
        }else{
            System.out.println("Test3 failed.");
        }
    }

    //str = null
    private static void test4() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = null;
        if(!hasPath(matrix, rows, cols, str)){
            System.out.println("Test4 passed.");
        }else{
            System.out.println("Test4 failed.");
        }
    }
    private static void test5() {
        char[] matrix = "A".toCharArray();
        int rows = 1;
        int cols = 1;
        char[] str = "A".toCharArray();
        if(hasPath(matrix, rows, cols, str)){
            System.out.println("Test5 passed.");
        }else{
            System.out.println("Test5 failed.");
        }
    }

    //matrix = A
    //str = B
    private static void test6() {
        char[] matrix = "A".toCharArray();
        int rows = 1;
        int cols = 1;
        char[] str = "B".toCharArray();
        if(!hasPath(matrix, rows, cols, str)){
            System.out.println("Test6 passed.");
        }else{
            System.out.println("Test6 failed.");
        }
    }

    //matrix =
    // A A A A
    // A A A A
    // A A A A
    //
    // str = AAAAAAAAAAAA
    private static void test7() {
        char[] matrix = "AAAAAAAAAAAA".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "AAAAAAAAAAAA".toCharArray();
        if(hasPath(matrix, rows, cols, str)){
            System.out.println("Test7 passed.");
        }else{
            System.out.println("Test7 failed.");
        }
    }


    private static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) {
            return false;
        }

        boolean[] visited = new boolean[rows * cols];
        for (boolean v : visited) {
            v = false;
        }

        int pathLength = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, rows, cols, row, col, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPathCore(char[] matrix,
                                int rows,
                                int cols,
                                int row,
                                int col,
                                char[] str,
                                int pathLength,
                                boolean[] visited) {
        if (row < 0
                || col < 0
                || row >= rows
                || col >= cols
                || visited[row * cols + col] == true
                || str[pathLength] != matrix[row * cols + col]){
            return false;
        }
        if(pathLength == str.length - 1){
            return true;
        }

        boolean hasPath = false;
        visited[row * cols + col] = true;
        hasPath = hasPathCore(matrix, rows, cols, row-1, col, str, pathLength + 1, visited)
                || hasPathCore(matrix, rows, cols, row + 1, col, str, pathLength + 1, visited)
                || hasPathCore(matrix, rows, cols, row, col + 1, str, pathLength + 1, visited)
                || hasPathCore(matrix, rows, cols, row, col+1, str, pathLength+1, visited);
        if(hasPath){
            visited[row*cols+col] = false;
        }
        return hasPath;
    }
}
