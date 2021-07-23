package ByteDance;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lufengxiang
 * @since 2021/7/20
 **/
public class LC950 {
    public static void main(String[] args) {
        int[] arr = {17, 13, 11, 2, 3, 5, 7};
        System.out.println(Arrays.toString(deckRevealedIncreasing(arr)));
    }

    public static int[] deckRevealedIncreasing(int[] deck) {
        int N = deck.length;
        Deque<Integer> index = new LinkedList<>();
        for (int i = 0; i < N; ++i)
            index.add(i);
        int[] ans = new int[N];
        Arrays.sort(deck);
        for (int card : deck) {
            ans[index.pollFirst()] = card;
            if (!index.isEmpty())
                index.add(index.pollFirst());
        }
        return ans;
    }
}
