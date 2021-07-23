package masterBfs;

import util.Link;

import static util.Link.*;

/**
 * @author lufengxiang
 * @since 2021/7/2
 **/
public class AddTwoNumbers implements Link {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //做链表的题一定要搞清楚需要几个指针
        //1.carry
        int carry = 0;
        //
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null || p2 != null) {
            int a = p1 == null ? 0 : p1.val;
            int b = p2 == null ? 0 : p2.val;
            //
            int sum = a + b + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
