package day.day;

import org.junit.Test;
import util.Tree;

import java.util.LinkedList;

/**
 * @author lufengxiang
 * @since 2021/7/27
 **/
public class Day0727 implements Tree {
    @Test
    public void testFindSecondMinimumValue() {

    }

    //671. 二叉树中第二小的节点
    public int findSecondMinimumValue(TreeNode root) {
        return traversal(root, root.val);
    }

    private int traversal(TreeNode root, int value) {
        //为空返回-1
        if (root == null) {
            return -1;
        }
        //如果大于value,
        if (root.val > value) {
            return root.val;
        }
        // 寻找左右子节点中，第一个大于自己的节点
        int l = traversal(root.left, value);
        int r = traversal(root.right, value);

        // 存在两个子节点
        if (l >= 0 && r >= 0) {
            return Math.min(l, r);
        }
        //　存在0个子节点
        return Math.max(l, r);
    }

    //二叉搜索树中第K小的元素
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
    //

    //
}
