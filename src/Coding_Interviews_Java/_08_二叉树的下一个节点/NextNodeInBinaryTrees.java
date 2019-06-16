package Coding_Interviews_Java._08_二叉树的下一个节点;

/**
 *
 * 面试题8：二叉树的下一个节点
 *
 * 题目：给定一颗二叉树和其中的一个节点，如何找出中序遍历序列的下一个节点？
 * 树中的节点除了有两个分别指向左、右子节点的指针，还有一个指向父节点的指针
 *
 *
 */
public class NextNodeInBinaryTrees {

    public static void main(String[] args){
        System.out.print("Hello World!");
    }

    public static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value){
            this.value = value;
        }
    }

    public static TreeNode getNext(TreeNode pNode){
        if(pNode == null){
            return null;
        }
        //如果当前是根节点，向下寻找
        if(pNode.right != null){
            pNode = pNode.right;
            while (pNode.left != null){
                pNode = pNode.left;
            }
            return pNode;
        }
        //否则返回根节点，继续寻找
        while(pNode.parent != null){
            if(pNode.parent.left == pNode){
                return pNode.parent;
            }
            pNode = pNode.parent;
        }
        return null;
    }
}
