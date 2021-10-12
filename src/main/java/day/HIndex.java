package day;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/11
 **/
public class HIndex {
    //H指数
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        //边界检查且 citations[i] > h
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    //citations = [0,1,3,5,6]
    //给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
    //由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
    public int hIndex2(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //n - mid是什么意思(右边的论文数量) . citations[mid] mid处的被引用次数.
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //n - left呢.同上.
        return n - left;
    }
}

