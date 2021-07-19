package day;

/**
 * @author lufengxiang
 * @since 2021/7/15
 **/
public class Muti {
    
    public static void main(String[] args) {
        Muti solution = new Muti();
        System.out.println(solution.multiply("123567", "45678"));
    }

    public String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int[] data = new int[m + n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                data[data.length - (i + j) - 2] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        //
        for (int i = 0; i < data.length - 1; i++) {
            int carry = data[i] / 10;
            data[i] = data[i] % 10;
            data[i + 1] += carry;
        }
        StringBuilder sb = new StringBuilder();
        int i = data.length - 1;
        while (i > 0 && data[i] == 0) {
            i--;
        }
        for (int j = 0; j <= i; j++) {
            sb.append(data[j]);
        }
        return sb.reverse().toString();
    }
}
