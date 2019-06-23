package Very_Hard;

/**
 *
 * 一栋建筑有10层楼，在1楼有一小人，小人每次都可向上跳n层（n>=1），
 *
 * 请问：当前小人在1楼，如果要跳到10楼，共有多少种跳法？
 *
 */
public class Jump_10_Floor {

    public static void main(String[] args) {
        int allFloorNum = 10;
        System.out.print("跳到"+allFloorNum+"层楼，总方法数：" + allJumpTypes(allFloorNum));
    }

    //
    //解法： allJumpTypes(n) = S(n-2) + S(n-3) + ... + S(1) + S(0)
    //  S(0): 在中间楼层停留0次，直接跳到10楼终点的总跳法数。（一步到位，）
    //  S(1): 在中间楼层停留1次，再跳到10楼的方法数
    //  S(2): 在中间楼层停留2次，再跳到10楼的方法数
    //  S(3): 在中间楼层停留3次，再跳到10楼的方法数
    // ......
    // S(n-2): 在中间楼层停留n-2次，再跳到10楼的方法数（相当于每次向上跳1层：在中间楼层停留了n-2次）

    private static int allJumpTypes(int n){
        if( n < 2 ){
            throw new RuntimeException("invalid param for allJumpTypes");
        }
        int sum = 0;
        for(int stayFloorNum = n-2; stayFloorNum >=0; stayFloorNum--){
            int res = jumpTypes(n-2, stayFloorNum);
            sum += res;
        }
        return sum;
    }

    /**
     * 思路：
     * 因为小人是从1层开始向上跳，最后一步落脚点必然是第10层，那种之前一步可能落脚点楼层为：2层、3层...9层，（2层——9层，共n-2个地方）
     * 所以函数返回值就是：从n-2个落脚点楼层中选selectedNum个不同的楼层，看有多少种选法？
     *
     * @param floorNum 总楼层数
     * @param selectedNum 到达终点前可能的落脚点
     * @return 在规定的落脚点个数下，最终跳到楼顶层的方法数（即组合公式：C(n,m), n>=m）
     */
    private static int jumpTypes(int floorNum, int selectedNum){
        if(floorNum < selectedNum){
            throw new RuntimeException("invalid params!");
        }
        //使用组合公式C(n,m)计算所有组合数
        return factorial(floorNum) / factorial(selectedNum) / factorial(floorNum - selectedNum);
    }

    //n的阶乘
    private static int factorial(int n){
        if(n < 0){
            throw new RuntimeException("invalid params for factorial!");
        }
        if(n == 0){
            return 1;
        }
        int res = 1;
        for(int i = n; i>=1; i--){
            res = res*i;
        }
        return res;
    }
}
