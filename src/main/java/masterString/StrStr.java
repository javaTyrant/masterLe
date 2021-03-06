package masterString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/7/5
 **/
public class StrStr {
    //118. 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        //
        List<List<Integer>> ret = new ArrayList<>();
        //
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<>();
            //
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
}
