package masterBfs;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/3
 **/
public class Day {
    public static void main(String[] args) {
        System.out.println(frequencySort("abbccc"));
        System.out.println(frequencySortBucket("aabbccc"));
    }

    //451. 根据字符出现频率排序O(n+klogk)
    public static String frequencySort(String s) {
        //用map统计次数
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        //排序
        list.sort((a, b) -> map.get(b) - map.get(a));
        //重新组装字符串
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            int frequency = map.get(c);
            for (int j = 0; j < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //桶排序:O(n + k)
    public static String frequencySortBucket(String s) {
        //统计每个字符串的频次
        Map<Character, Integer> map = new HashMap<>();
        //统计最大的频次
        int maxFreq = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
            maxFreq = Math.max(maxFreq, frequency);
        }
        //
        StringBuffer[] buckets = new StringBuffer[maxFreq + 1];
        ///
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new StringBuffer();
        }
        //
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].append(c);
        }
        StringBuilder sb = new StringBuilder();
        //
        for (int i = maxFreq; i > 0; i--) {
            StringBuffer bucket = buckets[i];
            int size = bucket.length();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(bucket.charAt(j));
                }
            }
        }
        return sb.toString();
    }
    //12. 整数转罗马数字

    //13. 罗马数字转整数

}
