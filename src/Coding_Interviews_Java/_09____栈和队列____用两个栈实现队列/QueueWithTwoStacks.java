package Coding_Interviews_Java._09____栈和队列____用两个栈实现队列;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 面试题9：用两个栈实现队列
 *
 * 题目：用两个栈实现一个队列。队列的声明如下，
 * 请实现它的两个函数appendTail 和 deleteHead,
 * 分别完成在队列尾部插入节点和在队列头部删除节点的功能。
 *
 * （题目2：用两个队列实现一个栈）
 */
public class QueueWithTwoStacks {
    public static void main(String[] args){
        System.out.print("Hello World!");
    }


    /**
     * 用两个栈实现队列
     */
    public static class Solution1{
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        //队列是先进先出，栈是先进后出。
        //用两个栈来实现队列，可以先进A，出栈A后进入B，最终B的出栈顺序为出队列的顺序。
        public void appendTail(int node){
            stack1.push(node);
        }

        public int deleteHead(){
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    /**
     * 用两个队列实现栈
     *
     * 思路：两个队列保持其中一个为空。
     * 栈的push：将元素push到空的那个队列中，并将非空队列全部入队到这个队列中。
     *          （这样，另个队列就空了。）
     *
     * 栈的pop：将非空队列出队即可。
     */
    public static class TwoQueueToStack{
        Queue<Integer> a = new LinkedList<>();
        Queue<Integer> b = new LinkedList<>();

        //保证有一个队列为空，
        // 当插入的时候，把新插入的元素放入空队列中，并出队非空队列入队该队列中
        public void push(int node){
            if(a.isEmpty()){
                a.offer(node);
                while(!b.isEmpty()){
                    a.offer(b.poll());
                }
            }else if(b.isEmpty()){
                b.offer(node);
                while (!a.isEmpty()){
                    b.offer(a.poll());
                }
            }
        }

        public int pop(){
            if(a.isEmpty()){
                return b.poll();
            }else{
                return a.poll();
            }
        }
    }
}
