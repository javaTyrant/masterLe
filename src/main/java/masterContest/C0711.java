package masterContest;

/**
 * @author lufengxiang
 * @since 2021/7/11
 **/
public class C0711 {
    //    bool sumGame(string num) {
//        int n = num.size();
//
//        auto get = [](string&& s) -> pair<int, int> {
//            int nn = 0, qq = 0;
//            for (char ch: s) {
//                if (ch == '?') {
//                    ++qq;
//                }
//                else {
//                    nn += (ch - '0');
//                }
//            }
//            return {nn, qq};
//        };
//
//        auto [n0, q0] = get(num.substr(0, n / 2));
//        auto [n1, q1] = get(num.substr(n / 2, n / 2));
//
//        return ((q0 + q1) % 2 == 1) || (n0 - n1 != (q1 - q0) * 9 / 2);
//    }
    public boolean sumGame(String num) {
        int n = num.length();
        int[] numUnknown = new int[2];
        int sum = 0;
        int half = n / 2;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                numUnknown[i / half]++;
            } else {
                if (i < half) {
                    sum += c - '0';
                } else {
                    sum -= c - '0';
                }
            }
        }
        if ((numUnknown[0] + numUnknown[1]) % 2 == 1) {
            return true;
        }
        sum += numUnknown[0] / 2 * 9;
        sum -= numUnknown[1] / 2 * 9;
        return sum != 0;
    }
}
