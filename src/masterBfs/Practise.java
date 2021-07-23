package masterBfs;

import util.Link;
import util.Tree;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/1
 **/
public class Practise implements Tree{
    //二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);
        while (!stack.isEmpty()) {
            List<Integer> cur = new ArrayList<>();
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = stack.pop();
                cur.add(curNode.val);
                if (curNode.left != null) {
                    stack.offer(curNode.left);
                }
                if (curNode.right != null) {
                    stack.offer(curNode.right);
                }
            }
            res.add(cur);
        }
        return res;
    }

    //无向图中连通分量的数目
    public int countComponents(int n, int[][] edges) {
        // 第 1 步：构建图
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 无向图，所以需要添加双向引用
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // 第 2 步：开始广度优先遍历
        int res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(adj, i, visited);
                res++;
            }
        }
        return res;
    }

    /**
     * @param adj     邻接表
     * @param u       从 u 这个顶点开始广度优先遍历
     * @param visited 全局使用的 visited 布尔数组
     */
    private void bfs(List<Integer>[] adj, int u, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visited[u] = true;

        while (!queue.isEmpty()) {
            Integer front = queue.poll();
            // 获得队首结点的所有后继结点
            List<Integer> successors = adj[front];
            for (int successor : successors) {
                if (!visited[successor]) {
                    queue.offer(successor);
                    // 特别注意：在加入队列以后一定要将该结点标记为访问，否则会出现结果重复入队的情况
                    visited[successor] = true;
                }
            }
        }
    }

    //给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.pop();
                cur.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            res.add(0, cur);
        }
        return res;
    }

    public int[] levelOrderI(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return new int[]{};
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pop();
                res.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    //请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
    //第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
    public List<List<Integer>> levelOrderIII(TreeNode root) {
        //用一个变量,保存层数
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.pop();
                if (level % 2 == 0) {
                    list.add(cur.val);
                } else {
                    list.add(0, cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(list);
            level++;
        }
        return res;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    //N 叉树的层序遍历
    public List<List<Integer>> levelOrderN(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> li = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node pop = queue.pop();
                li.add(pop.val);
                for (Node c : pop.children) {
                    if (c.children != null) {
                        queue.offer(c);
                    }
                }
            }
            res.add(li);
        }
        return res;
    }

    public static void main(String[] args) {
        Practise practise = new Practise();
        Integer[] arr = {1, 2, 3, 4};
        TreeNode node = practise.of(arr);
        System.out.println(practise.isCousins(node, 4, 3));
    }

    //二叉树的堂兄弟节点
    public boolean isCousins(TreeNode root, int x, int y) {
        if (x == y) return true;
        if (x > y) {
            return isCousins(root, y, x);
        }
        return getLevel(root, x) == ((getLevel(root, y) + 1) / 2);
    }

    private int getLevel(TreeNode node, int x) {
        int level = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode cur = queue.pop();
            if (cur.val == x) return level;
            for (int i = 0; i < size; i++) {
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            level++;
        }
        return -1;
    }

    public boolean isCousins2(TreeNode root, int x, int y) {
        int[] xi = bfs(root, x);
        int[] yi = bfs(root, y);
        return xi[1] == yi[1] && xi[0] != yi[0];
    }

    int[] bfs(TreeNode root, int t) {
        Deque<Object[]> d = new ArrayDeque<>(); // 存储值为 [cur, fa, depth]
        d.addLast(new Object[]{root, null, 0});
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                Object[] poll = d.pollFirst();
                TreeNode cur = (TreeNode) poll[0], fa = (TreeNode) poll[1];
                int depth = (Integer) poll[2];

                if (cur.val == t) return new int[]{fa != null ? fa.val : 0, depth};
                if (cur.left != null) d.addLast(new Object[]{cur.left, cur, depth + 1});
                if (cur.right != null) d.addLast(new Object[]{cur.right, cur, depth + 1});
            }
        }
        return new int[]{-1, -1};
    }


}
