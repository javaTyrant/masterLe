package day;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/7/17
 **/
public class RestoreIp {
    public List<String> restoreIpAddresses(String s) {
        int index = s.length() - 1;
        List<String> list = new ArrayList<>();
        resolveIpString(s, list, new StringBuilder(), index, 0);
        return list;
    }

    public void resolveIpString(String s, List<String> list, StringBuilder sb, int index, int dot) {
        if (dot > 4 || (dot == 4 && index != -1))
            return;
        if (dot == 4) {
            list.add(sb.reverse().toString());
            sb.reverse();
            return;
        }
        int num = 0;
        for (int i = 0; i < 3 && index - i >= 0; i++) {
            if (i != 0 && s.charAt(index - i) == '0')
                continue;
            num = (s.charAt(index - i) - '0') * (int) Math.pow(10, i) + num;
            if (num > 255) {
                continue;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(num);
            sb.append(sb2.reverse());
            //sb.append(num);
            if (dot != 3)
                sb.append(".");
            resolveIpString(s, list, sb, index - i - 1, dot + 1);
            if (dot != 3)
                sb.deleteCharAt(sb.length() - 1);
            for (int j = 0; j < sb2.length(); j++) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
