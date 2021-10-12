package util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lumac
 * @since 2020/7/3
 */
public interface Tree {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    default TreeNode of(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode curr;
        TreeNode root = null;
        Queue<TreeNode> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                curr = new TreeNode(nums[i]);
                root = curr;
                queue.add(curr);
            }
            if (!queue.isEmpty()) {
                curr = queue.poll();
            } else {
                break;
            }
            if (i + 1 < nums.length && nums[i + 1] != null) {
                curr.left = new TreeNode(nums[i + 1]);
                queue.add(curr.left);
            }
            if (i + 2 < nums.length && nums[i + 2] != null) {
                curr.right = new TreeNode(nums[i + 2]);
                queue.add(curr.right);
            }
            i = i + 1;
        }
        return root;
    }
}
