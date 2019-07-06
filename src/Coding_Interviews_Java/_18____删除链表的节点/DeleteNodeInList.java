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
 *
 *
 * 题目二：删除链表中重复的节点。
 *
 * 在一个排序的链表中，如何删除重复的节点？
 *
 *
 */
public class DeleteNodeInList {

    public static void main(String[] args) {
        System.out.println("Hello World!");


        Node head1 = new Node(1);
        Node second1 = new Node(2);
        Node third1 = new Node(3);
        Node fourth1 = new Node(4);
        Node fifth1 = new Node(5);

        head1.next = second1;
        second1.next = third1;
        third1.next = fourth1;
        fourth1.next = fifth1;


        Node newHead1 = deleteNode(head1, fifth1);
        if (newHead1 == null) {
            System.out.println("null");
        } else {
            System.out.println(newHead1.value);
            while ((newHead1 = newHead1.next) != null) {
                System.out.println(newHead1.value);
            }
        }


        System.out.println("\n\n\n");


        Node head2 = new Node(2);
        Node second2 = new Node(2);
        Node third2 = new Node(3);
        Node fourth2 = new Node(4);
        Node fifth2 = new Node(5);

        head2.next = second2;
        second2.next = third2;
        third2.next = fourth2;
        fourth2.next = fifth2;


        Node newHead2 = deleteDuplication(head2);
        if (newHead2 == null) {
            System.out.println("null");
        } else {
            System.out.println(newHead2.value);
            while ((newHead2.next) != null) {
                newHead2 = newHead2.next;
                System.out.println(newHead2.value);
            }
        }
    }

    /**
     *
     * 题目一：在O(1)时间内删除链表节点
     *
     * @param head 头结点
     * @param nodeToDeleted 待删除的节点
     * @return 返回头结点
     */
    private static Node deleteNode(Node head, Node nodeToDeleted) {
        if (head == null || nodeToDeleted == null) {
            throw new RuntimeException("head node cannot be null!");
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


    private static Node deleteDuplication(Node head){
        if(head == null){
            throw new RuntimeException("head node cannot be null!");
        }
        Node preNode = null;
        Node curNode = head;
        while(curNode != null){
            Node next = curNode.next;
            boolean needDelete = false;
            if(next != null && next.value == curNode.value){
                needDelete = true;
            }
            if(!needDelete){
                preNode = curNode;
                curNode = next;
            }else{
                int value = curNode.value;
                Node nodeToDelete = curNode;
                while(nodeToDelete != null && nodeToDelete.value == value){
                    next = nodeToDelete.next;
                    nodeToDelete = next;
                }
                if(preNode == null){
                    head = next;
                }else{
                    preNode.next = next;
                }
                curNode = next;
            }
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
