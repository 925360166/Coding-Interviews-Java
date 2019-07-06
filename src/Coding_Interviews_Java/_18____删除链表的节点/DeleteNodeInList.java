package Coding_Interviews_Java._18____删除链表的节点;

/**
 * 面试题18：删除链表的节点
 * <p>
 * 题目一：在O(1)时间内删除链表节点。
 * 给定单向链表的头指针和一个节点指针，定义一个函数在O(1)时间内删除该节点。
 * 链表节点与函数的定义如下：
 * <p>
 * struct ListNode{
 *      int m_nValue;
 *      ListNode *m_pNext;
 * };
 * <p>
 * void DeleteNode(ListNode** pListHead, ListNode* pToBeDeleted)
 */
public class DeleteNodeInList {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;


        Node res = deleteNode(head, fifth);
        if (res == null) {
            System.out.println("null");
        } else {
            System.out.println(head.value);
            while ((head = head.next) != null) {
                System.out.println(head.value);
            }
        }
    }

    private static Node deleteNode(Node head, Node nodeToDeleted) {
        if (head == null || nodeToDeleted == null) {
            throw new RuntimeException("head node is null!");
        }
        //待删除的是否是尾结点
        if (nodeToDeleted.next != null) {
            Node next = nodeToDeleted.next;
            nodeToDeleted.value = next.value;
            nodeToDeleted.next = next.next;
        }
        //链表只有一个节点，删除头结点（也是尾结点）
        else if (head == nodeToDeleted) {
            head = head.next;
        }
        //链表中有多个节点，删除尾节点
        else {
            Node node = head;
            while (node.next != nodeToDeleted) {
                node = node.next;
            }
            node.next = null;
        }
        return head;
    }

    public static class Node {

        public Node(int value) {
            this.value = value;
        }

        public Node next;
        public int value;
    }

}
