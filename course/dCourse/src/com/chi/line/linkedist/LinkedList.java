package com.chi.line.linkedist;

public class LinkedList<E> {

    // 1.内部类 表示1个节点
    private class Node {
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 2. 初始化
    private Node head; // 头节点
    private int size; // 长度

    // 3. 构造函数
    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 查询指定index的值
     *
     * @param index 索引
     * @return E 返回值
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get Failed, index is illegal");
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取链表头节点的值
     *
     * @return E 节点的值
     */
    public E getFirst() {
        return get(size);
    }

    /**
     * 获取链表尾节点的值
     *
     * @return E 节点的值
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改指定索引的元素
     *
     * @param index 索引
     * @param e     设置的新值
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get Failed, index is illegal");
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 在链表头增加节点
     *
     * @param e 新增节点的数据
     */
    public void addFirst(E e) {
        // 基本
        Node newNode = new Node(e);
        newNode.next = head;
        head = newNode;
        // 优化1
        // Node newNode = new Node(e, head); // 直接创建一个连接到链表头的节点
        // head = newNode;// 移动链表头

        // 优化2
        head = new Node(e, head); // 新建之后直接给了链表头
        size++;
    }

    /**
     * 在链表尾增加节点
     *
     * @param e 新增节点的数据
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在任意位置添加元素
     *
     * @param index 索引
     * @param e     新增节点的数据
     */
    public void add(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("add Failed, index is illegal");

        if (index == 0) {
            addFirst(e);
        } else {
            // 1. 暂存头节点用于下面向前走
            Node prev = head;
            // 2.初始化新元素
            Node newNode = new Node(e);
            // 3.找到前一个节点
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            // 4.找到之后 先把后面的起来，再把前面的连起来！
            newNode.next = prev.next;
            prev.next = newNode;
            size++;

            // 优化1
            // Node newNode =  newNode(e, prev.next);
            // prev.next = newNode;

            // 优化2
            // prev.next = newNode(e, prev.next);

        }
    }

    /**
     * 删除链表头节点
     *
     * @return 返回链表头
     */
    public E removeFirst() {
        // 如果为空 就直接null
        if (head == null) return null;

        // 1.暂存链表头
        Node delNode = head;
        // 2.移动
        head = head.next;
        // 3.删除暂存的链表头
        delNode.next = null;

        return delNode.e;
    }

    /**
     * 删除链表尾节点
     *
     * @return 删除掉的节点值
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除链表任意位置的节点
     *
     * @param index 索引位置
     * @return 删除掉的节点值
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove Failed, index is illegal");
        if (index == 0) return removeFirst();
        // 移动用暂存
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        // 1. 暂存
        Node delNode = prev.next;
        // 2. 掐断第一条线，直接略过本身指向下一个
        prev.next = delNode.next;
        // 3. 掐断第二条线 暂存指向null 彻底断开
        delNode.next = null;
        size--;
        return delNode.e;
    }

}
