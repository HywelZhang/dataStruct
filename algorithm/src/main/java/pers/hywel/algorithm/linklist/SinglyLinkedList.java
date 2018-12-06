package pers.hywel.algorithm.linklist;

/**
 * @author HywelZhang
 * dicription: 此类定义的头指针head：指向第一个节点
 */
public class SinglyLinkedList {
    /**
     * 链表节点类定义
     */
    public class ListNode {
        private int val;
        public ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        public int getVal() {
            return this.val;
        }
    }

    public ListNode head;
    private ListNode tail;
    private int length;

    /**
     * Initialize your data structure here.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.length = 0;
    }

    /**
     * 判断该列表是否为空
     *
     * @return if the linkedList is empty return true else return false
     */
    private boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * Get the value of the index-th ListNode in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        }
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a ListNode of value val before the first element of the linked list. After the insertion, the new ListNode will be the first ListNode of the linked list.
     */
    public void addAtHead(int val) {
        ListNode listNode = new ListNode(val);
        if (length == 0) {
            head = listNode;
            tail = listNode;
        } else {
            listNode.next = head;
            this.head = listNode;
        }
        this.length++;
    }

    /**
     * Append a ListNode of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode listNode = new ListNode(val);
        if (length == 0) {
            this.head = listNode;
            this.tail = listNode;
        } else {
            tail.next = listNode;
            this.tail = listNode;
        }
        this.length++;

    }

    /**
     * Add a ListNode of value val before the index-th ListNode in the linked list. If index equals to the length of linked list, the ListNode will be appended to the end of linked list. If index is greater than the length, the ListNode will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > this.length || index < 0) {
            return;
        }
        if (index == this.length) {
            addAtTail(val);
        } else if (index == 0) {
            addAtHead(val);
        } else {
            ListNode ListNode = new ListNode(val);
            ListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            ListNode.next = cur.next;
            cur.next = ListNode;
            this.length++;
        }

    }

    /**
     * Delete the index-th ListNode in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= length || index < 0) {
            return;
        }
        if (1 == length) {
            head = null;
            tail = null;
            length--;
        } else if (0 == index) {
            head = head.next;
            length--;
        } else {
            ListNode cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            if (null == cur.next) {
                tail = cur;
            }
            length--;
        }
    }

    public void printHead() {
        if (null == head) {
            System.out.println("Linked List is empty");
        }
        {
            System.out.println(this.head.val);
        }
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("The Linked List is Empty");
        } else {
            ListNode cur = head;
            for (int i = 0; i < length; i++) {
                System.out.print(cur.val + " ");
                if (null != cur.next) {
                    cur = cur.next;
                }
            }
            System.out.println();
        }
    }


    /**
     * Source: Linked List Cycle
     * <p>
     * 返回链表的环节点，如果没有环则返回null
     * <p>
     * 解决办法：快慢指针
     * 实现步骤：
     * 1.1) Using a slow pointer that move forward 1 step each time
     * <p>
     * 1.2) Using a fast pointer that move forward 2 steps each time
     * <p>
     * 1.3) If the slow pointer and fast pointer both point to the same location after several moving steps, there is a cycle;
     * <p>
     * 1.4) Otherwise, if (fast == NULL || fast->next == NULL), there has no cycle.
     *
     * @param head 链表头节点
     * @return 是否存在环
     */
    public boolean detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * Source: Linked List Cycle 2
     * <p>
     * target: 如果链表存在环，返回环节点。否则返回NULL
     * <p>
     * 算法证明，设
     * L1： head到环节点的距离
     * L2: 环节点到相遇的距离
     * 相遇时Slow的路程： L1+L2
     * 相遇时fast的路程： L1+L2+nc(c为一圈环的长度，n为fast相遇时所转的圈数)
     * fast速度和距离均为slow两倍，则有：
     * 2(L1+L2) = L1+L2+nc
     * L1+L2 = nc
     * L1 = nc - L2
     * L1 = (n-1)c + (c-L2)
     * ==> head到环节点的距离等于n-1圈环后再跑到环节点的距离。所以总会在环节点相遇
     * <p>
     * STEP：
     * STEP 1：判断链表是否存在环
     * STEP 2：在相遇点，一个指针从head开始往前，slow指针从相遇点开始继续往前，他们相遇的点就是环节点
     */
    public ListNode detectCycleListNode(ListNode head) {
        if (null == head || null == head.next) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode cur = head;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                while (slow != cur) {
                    cur = cur.next;
                    slow = slow.next;
                }
                return cur;
            }
        }
        return null;
    }


    /**
     * 返回如下两个交叉链表的交叉点，如果没有则返回null
     * （要求：1. 时间复杂度为O(n),空间复杂度为O(1)）
     * <p>
     * <p>
     * For example, the following two linked lists:
     * <p>
     * A:          a1 → a2
     * ↘
     * c1 → c2 → c3
     * ↗
     * B:     b1 → b2 → b3
     * begin to intersect at node c1.
     * <p>
     * 实现方法：
     * 1. 利用两次迭代，经历相同循环后，同时指向交叉点
     * （a1+a2+ c1+c2+c3 +b1+b2+b3 =
     * b1+b2+b3+ c1+c2+c3+ a1+a2）所以这么多次迭代次数后在交叉点相遇
     * <p>
     * 2. 拿到两个链表的Length，去掉较长的部分，再开始迭代。例如把上边例子中B链表去掉较长部分
     * A:       a1 → a2
     * ↘
     * c1 → c2 → c3
     * ↗
     * B:       b2 → b3
     */
    //方法一实现
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    /**
     * 移除尾部第N个节点
     * <p>
     * Example:
     * <p>
     * Given linked list: 1->2->3->4->5, and n = 2.
     * <p>
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * <p>
     * 要求：仅遍历链表一次，并且传入的n总是可用的
     *
     * @param head 链表头节点
     * @param n    删除从尾部第n的节点
     * @return 删除后的链表头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //先定义一个快指针到位置
        ListNode fast = head;
        for (int i = 1; i < n; i++) {
            fast = fast.next;
        }
        //判断如果删除的是头节点，直接头节点删除然后返回
        if (fast.next == null) {
            head = head.next;
            return head;
        } else {
            //fast再前进一步，确保slow指针是在删除节点前一个
            fast = fast.next;
        }
        //fast和slow节点一起往前走，当fast到达链表末尾，slow就是删除节点的前一节点
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }


    /**
     * 链表翻转
     * Reverse a singly linked list.
     * <p>
     * Example:
     * <p>
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     */
    public ListNode reserveListByItertively(ListNode head) {
        ListNode tempHead = null;
        ListNode temp = null;
        while (head != null) {
            temp = head;
            head = head.next;
            temp.next = tempHead;
            tempHead = temp;
        }
        return tempHead;
    }

    /**
     * 链表翻转（递归实现）
     * Reserve single linkedlist by recursively
     *
     * @param head
     * @return
     */
    public ListNode reserveListByRecursively(ListNode head) {
        return reserverByRec(head, null);
    }

    private ListNode reserverByRec(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reserverByRec(next, head);
    }

    /**
     * 移除单链表中所有给定的值
     * Remove all elements from a linked list of integers that have value val.
     * <p>
     * Example:
     * <p>
     * Input:  1->2->6->3->4->5->6, val = 6
     * Output: 1->2->3->4->5
     */
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode cur = head;
        ListNode temp = head;
        while (cur != null) {
            if (cur.val == val) {
                temp.next = cur.next;
                cur = temp;
            }
            temp = cur;
            cur = cur.next;
        }
        return head;
    }

    /**
     * 重排链表为：奇数节点+偶数节点
     * Odd Even Linked List
     * Go to Discuss
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
     * <p>
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
     * <p>
     * Example 1:
     * <p>
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     * Example 2:
     * <p>
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     */
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {
            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = head.next;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    /**
     * 判断链表是否存在回文
     *
     * Palindrome Linked List
     * Go to Discuss
     * Given a singly linked list, determine if it is a palindrome.
     * <p>
     * Example 1:
     * <p>
     * Input: 1->2
     * Output: false
     * Example 2:
     * <p>
     * Input: 1->2->2->1
     * Output: true
     * Follow up:
     * Could you do it in O(n) time and O(1) space?
     * <p>
     * 判断是否链表存在回文(1,0,1这样的也是)
     * <p>
     * Solve：通过快慢指针找到一半，然后把前半链表翻转，再后后半链表比较
     */
    public boolean isPalindrome(ListNode head) {
        if (null == head || null == head.next) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //链表翻转
        ListNode tempHead = null;
        ListNode temp;
        while (!head.equals(slow)) {
            temp = head;
            head = head.next;
            temp.next = tempHead;
            tempHead = temp;
        }

        //判断总长度是否为单数链表
        if (null != fast) {
            slow = slow.next;
        }
        while (null != slow) {
            if (slow.getVal() != tempHead.getVal()) {
                return false;
            } else {
                slow = slow.next;
                tempHead = tempHead.next;
            }
        }
        return true;
    }

    /**
     * 合并两个sorted链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode mergedLinkedListHead;
        if (null == l1) {
            mergedLinkedListHead = l2;
            return mergedLinkedListHead;
        } else if (null == l2) {
            mergedLinkedListHead = l1;
            return mergedLinkedListHead;
        } else if (l1.val < l2.val) {
            mergedLinkedListHead = l1;
            l1 = l1.next;
        } else {
            mergedLinkedListHead = l2;
            l2 = l2.next;
        }

        ListNode cur = mergedLinkedListHead;
        while (null != l1 && null != l2) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }

        if (null == l1) {
            while (null != l2) {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        } else {
            while (null != l1) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            }
        }
        return mergedLinkedListHead;
    }

    /**
     * 两链表相加
     *
     * 给定两个非负链表，每个链表代表一个数。每个节点都反序存着一位数.
     * 将这两个链表的数加起来并以同样的方式返回一个链表.
     * tips： 这个数的头元素不会是0，除非这个数本身为0的情况
     *
     * Example:
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode=null;
        ListNode cur = null;
        int isCarry = 0;
        while (null!=l1 || null!=l2){
            int val;
            if(null != l1 && null!=l2) {
                val = l1.val + l2.val + isCarry;
                l1 = l1.next;
                l2 = l2.next;
            }else if(null == l1){
                if(1==isCarry){val = l2.val+1;}
                else {val = l2.val;}
                l2 = l2.next;
            }else {
                if(1==isCarry){val = l1.val+1;}
                else {val = l1.val;}
                l1 = l1.next;
            }
            if(val>=10){
                val = val - 10;
                isCarry = 1;
            }else { isCarry = 0; }
            ListNode newNode = new ListNode(val);
            if(null==resultNode){
                resultNode = newNode;
                cur = resultNode;
            }
            else{
                cur.next = newNode;
                cur = newNode;
            }
        }
        if (1 == isCarry) {
            ListNode newNode = new ListNode(1);
            cur.next = newNode;
            cur = newNode;
        }
        return resultNode;
    }

    /**
     * 翻转k以后节点
     *
     * 将k以后的节点放到前边
     * Example：
     * Input: 1->2->3->4->5->NULL, k = 2
     * Output: 4->5->1->2->3->NULL
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(null==head){return null;}
        ListNode slow = head;
        ListNode fast = head;
        for(int i=0;i<k;i++){
            fast = fast.next;
            ///如果k大于链表长度，将k对链表长度取余后再更新fast指针（防止k过大）
            if(null==fast){
                fast = head;
                k = k%(i+1);
                for (int j=0;j<k;j++){
                    fast = fast.next;
                }
                break;
            }
        }
        //k==链表长度，链表不发生改变，直接返回
        if(fast==slow){return head;}
        while (null!=fast.next){
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
}


