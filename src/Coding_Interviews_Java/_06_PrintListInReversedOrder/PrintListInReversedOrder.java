package Coding_Interviews_Java._06_PrintListInReversedOrder;

import java.util.Stack;

/**
 *
 * 面试题6：从尾到头打印链表
 *
 * 题目：输入一个链表的头结点，从尾到头反过来打印出每个节点的值。链表的节点定义如下：
 *
 * struct ListNode{
 *     int m_nKey;
 *     ListNode* m_pNext;
 * }
 *
 *
 * 解法一
 * 初看题目意思就是输出的时候链表尾部的元素放在前面，链表头部的元素放在后面。
 * 这不就是 先进后出，后进先出么。
 * 什么数据结构符合这个要求？
 * 栈 ！
 *
 * 解法二
 * 第二种方法也比较容易想到，
 * 通过链表的构造，如果将末尾的节点存储之后，
 * 剩余的链表处理方式还是不变，
 * 所以可以使用递归的形式进行处理。
 *
 * 解法三
 * 如果你还知道链表的更多性质的话，肯定能想到用 头插法 为逆序的特点来解决。
 *
 * 头插法：将链表的左边称为链表头部，右边称为链表尾部。
 * 头插法是将右边固定，每次新增的元素都在左边头部增加。
 *
 */
public class PrintListInReversedOrder {

    public static void main(String[] args){

        //组装链表，值为0,1,2,3,4,5,6,7,8,9
        Node<Integer> headNode = new Node<>();
        Node<Integer> curNode = headNode;
        for(int i  = 0; i < 9; i++){
            curNode.value = i;
            Node<Integer> nextNode = new Node<>();
            nextNode.value = i + 1;
            curNode.next = nextNode;
            curNode = nextNode;
        }


//        printListReversedOrder_1(headNode);
//        printListReversedOrder_2(headNode);
        printListReversedOrder_3(headNode);

    }

    public static class Node<T>{
        //节点的值
        public T value;
        //指向下一节点
        public Node<T> next;
    }


    //基于栈的循环版本
    public static void printListReversedOrder_1(Node<Integer> head){
        //使用栈这种数据结构
        Stack<Node<Integer>> stack = new Stack<>();
        //将链表元素全部存放在栈里面
        while(head != null){
            stack.push(head);
            head = head.next;
        }
        //取出栈里面的元素
        while (!stack.isEmpty()){
            System.out.println(stack.pop().value);
        }
    }

    //给予递归的版本
    public static void printListReversedOrder_2(Node<Integer> head){
        if(head.next == null){
            System.out.println(head.value);
        }else{
            printListReversedOrder_2(head.next);
            System.out.println(head.value);
        }
    }

    //头插法
    public static void printListReversedOrder_3(Node<Integer> curNode) {
        //头插法构建逆序链表
        Node<Integer> head = new Node<>();
        head.value = -1;

        while(curNode != null){
            Node<Integer> nextNode = curNode.next;
            curNode.next = head;
            head = curNode;
            curNode = nextNode;
        }

        while(head != null && head.next != null){
            System.out.println(head.value);
            head = head.next;
        }
    }

}
