package LRU_Algorithm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LRU缓存算法的实现
 * https://blog.csdn.net/qq_26440803/article/details/83795122
 */
public class LRUCache<V>  {

    /**
     * 容量 1024
     */
    private int capacity = 1024;

    //Node记录表
    private Map<String, Node<String, V>> table = new ConcurrentHashMap();

    //双向链表头部
    private Node<String, V> head;

    //双向链表尾部
    private Node<String, V> tail;

    public LRUCache(int capacity){
        this();
        this.capacity = capacity;
    }

    //初始化
    public LRUCache(){
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        head.pre = null;
        tail.pre = head;
        tail.next = null;
    }


    /**
     * 双向链表内部类
     */
    public static class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    public V get(String key){
        Node<String, V> node = table.get(key);
        //如果Node不在表中，代表缓存中并没有
        if(node == null){
            return null;
        }

        //如果存在，则需要移动Node节点到表头
        //截断链表，node.prev -> node  -> node.next ====> node.prev -> node.next
        //         node.prev <- node <- node.next  ====>  node.prev <- node.next
        node.pre.next = node.next;
        node.next.pre = node.pre;

        //移动节点到表头
        node.next = head.next;
        head.next.pre = node;
        node.pre = head;
        head.next = node;
        //存在缓存表中
        table.put(key, node);
        return node.value;
    }

    public void put(String key, V value){
        Node<String, V> node = table.get(key);
        //如果存在，则需要移动Node节点到表头
        if(node == null){
            if(table.size() == capacity){
                //超过容量了，首先移除尾部的节点
                table.remove(tail.pre.key);
                tail = tail.pre;
                tail.next = null;
            }
            node = new Node<>();
            node.key = key;
            node.value = value;
            table.put(key, node);
        }
        //如果存在，则需要移动Node节点到表头
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public static void main(String[] args){
        LRUCache<Node> cache = new LRUCache<>(4);
        Node<String, Integer> node1 = new Node<>("key1", 1);
        Node<String, Integer> node2 = new Node<>("key2", 2);
        Node<String, Integer> node3 = new Node<>("key3", 3);
        Node<String, Integer> node4 = new Node<>("key4", 4);
        Node<String, Integer> node5 = new Node<>("key5", 5);

        cache.put("key1", node1);
        cache.put("key2", node2);
        cache.put("key3", node3);
        cache.put("key4", node4);
        cache.get("key2");
        cache.put("key5", node5);
        cache.get("key2");
    }
}
