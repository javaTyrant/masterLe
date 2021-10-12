package day.day;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/8/13
 **/
public class Day0813 {
    public int evalRPNStack(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            //是数字,入栈
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                //pop两个
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    private int N = -1;

    public int evalRPN(String[] tokens) {
        if (N == -1)
            N = tokens.length - 1;
        String s = tokens[N--];
        char c = s.charAt(0);
        if (s.length() == 1 && "+-*/".indexOf(c) != -1) {
            int a = evalRPN(tokens);
            int b = evalRPN(tokens);
            switch (c) {
                case '+':
                    return a + b;
                case '-':
                    return b - a;
                case '*':
                    return a * b;
                case '/':
                    return b / a;
                default:
                    break;
            }
        }
        return Integer.parseInt(s);
    }

    // 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
    public int evalRPN2(String[] tokens) {
        int n = tokens.length;
        //假设字符串有n个，而数字总比运算符多一个，因此n肯定为奇数
        //因此数字是(n+1)/2  运算符为(n-1)/2
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (String token : tokens) {
            switch (token) {
                //遇到运算符,index回退
                //stack[index]
                case "+":
                    index--;
                    stack[index] = stack[index] + stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                //遇到数字,index增加
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }

    @Test
    public void testAddOperators() {
        System.out.println(addOperators("105", 5));
    }
    //282. 给表达式添加运算符
    char[] num;
    char[] exp;
    int target;
    List<String> res;

    public List<String> addOperators(String num, int target) {
        this.res = new ArrayList<>();
        this.num = num.toCharArray();
        this.target = target;
        this.exp = new char[num.length() * 2];
        dfs(0, 0, 0, 0);
        return res;
    }

    private void dfs(int pos, int len, long prev, long curr) {
        if (pos == num.length) {
            if (curr == target) {
                res.add(new String(exp, 0, len));
            }
            return;
        }
        /*
          s 记录该次 dfs的起始位置
          pos是num的位置
         */
        int s = pos;
        /*
          len是 放数字 的位置
          l是 放运算符 的位置
         */
        int l = len;
        if (s != 0) {
            len++;
        }
        long n = 0;
        while (pos < num.length) {
            if (num[s] == '0' && pos - s > 0) {
                break;
            }
            n = n * 10 + (num[pos] - '0');
            if (n > Integer.MAX_VALUE) {
                break;
            }
            exp[len++] = num[pos++];
            if (s == 0) {
                dfs(pos, len, n, n);
                continue;
            }
            exp[l] = '+';
            dfs(pos, len, n, curr + n);
            exp[l] = '-';
            dfs(pos, len, -n, curr - n);
            exp[l] = '*';
            dfs(pos, len, prev * n, curr - prev + prev * n);
        }
    }
}
