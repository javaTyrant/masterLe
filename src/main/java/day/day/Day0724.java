package day.day;

import org.junit.Test;

import java.util.*;

/**
 * @author lumac
 * @since 2021/7/24
 */
public class Day0724 {
    @Test
    public void testMinRemoveToMakeValid() {
        String s = "what())";
        System.out.println(minRemoveToMakeValid(s));
    }

    //1249. 移除无效的括号
    public static String minRemoveToMakeValid(String s) {
        //
        Set<Integer> indexesToRemove = new HashSet<>();
        //
        Stack<Integer> stack = new Stack<>();
        //
        for (int i = 0; i < s.length(); i++) {
            //入栈
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            //
            if (s.charAt(i) == ')') {
                //栈为空的化.加入需要删除的set里
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    //弹出
                    stack.pop();
                }
            }
        }
        //栈不为空的话,加入到删除set
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        //
        StringBuilder sb = new StringBuilder();
        //
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    @Test
    public void testMinRemoveToMakeValidTwoPharse() {
        String s = "ss(()),,(()";
        System.out.println(minRemoveToMakeValidTwoPharse(s));
    }

    public static String minRemoveToMakeValidTwoPharse(String s) {
        // Parse 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            }
            if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // Parse 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }

    @Test
    public void testSearchMatrix() {
        int[][] arr = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        System.out.println(searchMatrix(arr, 80));
    }

    //74. 搜索二维矩阵
    //输入：matrix
    // [1,3,5,7],
    // [10,11,16,20],
    // [23,30,34,60], target = 3
    //输出：true
    public static boolean searchMatrix(int[][] matrix, int target) {
        int n = 0, m = matrix[0].length - 1;
        int i = matrix.length;
        while (n < i && m >= 0) {
            if (matrix[n][m] > target) {
                m--;
            } else if (matrix[n][m] < target) {
                n++;
            } else {
                return true;
            }
        }
        return false;
    }

    //拒绝采样:我们可以用拒绝采样的方法实现 Rand10()。
    //在拒绝采样中，如果生成的随机数满足要求，那么久返回该随机数，否则会不断生成直到一个满足要求的随机数为止。
    //若我们调用两次 Rand7()，那么可以生成 [1, 49] 之间的随机整数，我们只用到其中的 40 个，用来实现 Rand10()，而拒绝剩下的 9 个数，如下图所示。
    public int rand7() {
        Random r = new Random();
        return r.nextInt(8);
    }

    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        int i = 1 + (idx - 1) % 10;
        //
        if (i < 0) {
            System.out.println();
        }
        return i;
    }

    @Test
    public void testRandom10() {
        System.out.println(rand10());
        System.out.println(rand10());
        System.out.println(rand10());
        System.out.println(rand10());
        System.out.println(rand10());
        System.out.println(rand10());
    }

    @Test
    public void testVerify() {

    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDegree = new int[n];
        int root = -1;

        // if inDegree of any node > 1, return false
        for (final int child : leftChild)
            if (child != -1 && ++inDegree[child] == 2)
                return false;

        for (final int child : rightChild)
            if (child != -1 && ++inDegree[child] == 2)
                return false;

        // find the root (node with inDegree == 0)
        for (int i = 0; i < n; ++i)
            if (inDegree[i] == 0)
                if (root == -1)
                    root = i;
                else
                    return false; // multiple roots

        // didn't find the root
        if (root == -1)
            return false;

        return countNodes(root, leftChild, rightChild) == n;
    }

    private int countNodes(int root, int[] leftChild, int[] rightChild) {
        if (root == -1)
            return 0;
        return 1 + countNodes(leftChild[root], leftChild, rightChild) +
                countNodes(rightChild[root], leftChild, rightChild);
    }

    //课程表
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //入度表:
        int[] indgree = new int[numCourses];
        //邻接表
        List<List<Integer>> adj = new ArrayList<>();
        //初始化邻接表
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        //入度表和邻接表赋值
        for (int[] cp : prerequisites) {
            indgree[cp[0]]++;
            adj.get(cp[1]).add(cp[0]);
        }
        //
        Queue<Integer> queue = new LinkedList<>();
        //
        for (int i = 0; i < numCourses; i++)
            if (indgree[i] == 0) queue.add(i);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            numCourses--;
            for (int c : adj.get(cur)) {
                if (--indgree[c] == 0) queue.offer(c);
            }
        }
        return numCourses == 0;
    }

    //子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //index list
        find(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void find(List<List<Integer>> res, int[] nums, int index, List<Integer> cur) {
        //先add
        res.add(new ArrayList<>(cur));
        //base case隐藏在for循环里
        //循环
        for (int i = index; i < nums.length; i++) {
            //add
            cur.add(nums[i]);
            find(res, nums, i + 1, cur);
            //remove
            cur.remove(cur.size() - 1);
        }
    }

    //90. 子集 II:可能包含重复元素
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        back(nums, res, 0, new ArrayList<>());
        return res;
    }

    private void back(int[] nums, List<List<Integer>> res, int i, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            //判断重复:
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            tmp.add(nums[j]);
            back(nums, res, j + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    //31. 下一个排列
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        // 找到第一个下降点，我们要把这个下降点的值增加一点点
        // 对于511这种情况，要把前面两个1都跳过，所以要包含等于
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 如果这个下降点还在数组内，我们找到一个比它稍微大一点的数替换
        // 如果在之外，说明整个数组是降序的，是全局最大了
        if (i >= 0) {
            int j = nums.length - 1;
            // 对于151，这种情况，要把最后面那个1跳过，所以要包含等于
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 将下降点之前的部分倒序构成一个最小序列
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}