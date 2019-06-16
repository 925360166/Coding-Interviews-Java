package Coding_Interviews_Java._07_重建二叉树;

/**
 *
 * 面试题7：重建二叉树
 *
 * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建如图2.6所示的二叉树并输出它的头结点。
 * 二叉树节点的定义如下：
 * struct BinaryTreeNode{
 *     int m_nValue;
 *     BinaryTreeNode* m_pLeft;
 *     BinaryTreeNode* m_pRight;
 * };
 *
 * 二叉树:
 *                1
 *         2              3
 *     4               5     6
 *         7               8
 *
 *
 * 解题思路
 * 我们知道，前序遍历的第一个节点就是树的根节点，
 * 所以我们先根据前序遍历序列的第一个数字创建根结点，
 * 接下来在中序遍历序列中找到根结点的位置，根节点的左边就是左子树，右边就是右子树，
 * 这样就能确定左、右子树结点的数量。
 * 在前序遍历和中序遍历的序列中划分了左、右子树结点的值之后，
 * 就可以递归地去分别构建它的左右子树。
 *
 *
 */
public class ConstructBinaryTree {

    public static void main(String[] args){
        System.out.println("Hello World");
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] inOrder = {4,7,2,1,5,3,8,6};
        TreeNode head = contructBinaryTree(preOrder, inOrder);

        //遍历二叉树并打印：head
        printBTPreOrder(head);

    }

    //先序遍历
    public static void printBTPreOrder(TreeNode root){
        if(root == null){
            return;
        }
        //打印根节点
        System.out.print(root.value + " ");
        //打印左叶子
        printBTPreOrder(root.left);
        //打印右叶子
        printBTPreOrder(root.right);
    }

    //二叉树节点
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value){
            this.value = value;
        }
    }

    /**
     * 重建二叉树
     * @param preOrder 前序遍历
     * @param inOrder  中序遍历
     */
    public static TreeNode contructBinaryTree(int[] preOrder, int[] inOrder){
        if(preOrder.length == 0 || inOrder.length == 0){
            return null;
        }
        return contruct(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    /**
     *
     * @param preOrder 前序遍历数组
     * @param pStart
     * @param pEnd
     * @param inOrder 中序遍历数组
     * @param iStart
     * @param iEnd
     * @return
     */
    public static TreeNode contruct(int[] preOrder, int pStart, int pEnd, int[] inOrder, int iStart, int iEnd){
        if(pStart > pEnd || iStart > iEnd){
            return  null;
        }
        TreeNode root = new TreeNode(preOrder[pStart]);
        for(int i = iStart; i <= iEnd; i++){
            if(inOrder[i] == preOrder[pStart]){
                root.left = contruct(preOrder, pStart+1, pStart+i-iStart, inOrder, iStart, i-1);
                root.right = contruct(preOrder, pStart+i-iStart+1, pEnd, inOrder, i+1, iEnd);
                break;
            }
        }
        return root;
    }
}
