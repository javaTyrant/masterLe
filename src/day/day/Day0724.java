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
        int[] indgree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            indgree[cp[0]]++;
            adj.get(cp[1]).add(cp[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
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
}
