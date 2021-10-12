package util;

/**
 * 链表帮助类
 * 1.从数组中构造链表
 * 2.打印链表
 *
 * @author lumac
 * @since 2020/6/24
 */
public interface Link {
    class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    //真牛逼
    default ListNode arrayToListNode(int[] arr) {
        ListNode list = new ListNode(-1);
        ListNode cur = list;
        for (int a : arr) {
            cur.next = new ListNode(a);
            cur = cur.next;
        }
        return list.next;
    }

    default void printNode(ListNode node) {
        ListNode cur = node;
        while (cur != null) {
            if (cur.next != null) {
                System.out.print(cur.val + "->");
            } else {
                System.out.print(cur.val);
            }
            cur = cur.next;
        }
        System.out.println();
    }
}
