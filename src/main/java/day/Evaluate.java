package day;

/**
 * @author lufengxiang
 * @since 2021/7/15
 **/
public class Evaluate {
    public int evalRPNStack(String[] ts) {
        int[] stack = new int[ts.length / 2 + 1];
        int top = -1;
        for (String t : ts) {
            if ("+-/*".contains(t)) {
                int num1 = stack[top--], num2 = stack[top--];
                int res = cal(num1, num2, t);
                stack[++top] = res;
            } else {
                stack[++top] = Integer.parseInt(t);
            }
        }
        return stack[0];
    }

    int cal(int num1, int num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "/":
                return num2 / num1;
            default:
                return num1 * num2;
        }
    }

    public static void main(String[] args) {
        Evaluate solution = new Evaluate();
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(solution.evalRPNStack(tokens));
        System.out.println(solution.evalRPN(tokens));
    }

    //150. 逆波兰表达式求值:后缀表达式
    //逆波兰表达式：
    //逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
    //平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
    //该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
    //逆波兰表达式主要有以下两个优点：
    //去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
    //适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
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
}
