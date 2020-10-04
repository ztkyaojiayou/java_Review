package 数据结构与算法.LeetCode题解.字符串;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。
 * 字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 */

/**
 * 1.对每个字符串排序
 * 2.跟map中对比，一样就把原字符串加入到value中，不一样就建一个新的
 * 3.把map中的value都加入结果。
 *
 * 1.首先明确什么是"字母异位词","字母异位词"指字母相同，但排列不同的字符串。
 * 假设字符串s = "abc", s的字母异位词是"abc" "acb" "bac" "bca" "cab" "cba" ;
 * n个字母组成的字符串有n!符合条件的字母异位词。
 * 2.其次思考给定两个字符串如何判断他们是否为字母异位词呢？
 * 我们将字符串按照字母序排列，如果相等即为字母异位词。
 * 如"acb" "cba" 按照字母序排序以后均为"abc" 。
 * 也就是说字母异位词具有统一的形式
 * 3.保存结果：开一个HashMap，key存放每个排序后的字符串（统一形式），List存放key对应形式下的List。
 */
class 字母异位词分组49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        //1.先开一个map，用于存储对异位词分类，即把异位词和其排序后的字符串相对应，
        //其中，key存放每个排序后的字符串（即它们的统一形式，如abc），
        //而value则存放该排序后的字符串所对应的各种乱序字符串的集合List（如[acb,cba])。
        //则易知，最后的结果集为该map中的所有的value值。
        Map<String,List<String>> map=new HashMap<>();

        //2.再遍历字符串数组，将每一个字符串变为字符数组，并排序，之后再把其变回字符串
        for (String str:strs){
            //2.1将每一个字符串变为字符数组，便于排序
            char [] s= str.toCharArray();
            //2.2对每一个字符串排序，此时该字符串就变成了一个有序的字符串
            Arrays.sort(s);
            //2.3再把该排序后的字符数组转化为字符串
            String sorted_str=new String(s);

            //3.再去map的key中查找，看是否存在该字符串
            if (map.containsKey(sorted_str)){
                //3.1若存在，则说明还有其对应的异位词list，
                //1）于是先获取到该排序后的字符串在map中所对应的之前就添加进去了的所有异位词的list，并将当前字符串也添加到该list中
                // (因为map中存在当前字符串通过排序后的字符串，说明该字符串也是其异位词，因此就要添加进该list）
                //2）同时，把该排序后的字符串和其对应的所有异位词的list都存入map中
                List<String> old_list=map.get(sorted_str);
                old_list.add(str);
                map.put(sorted_str,old_list);
            }
            else{//3.2而若map中不存在该排序后的字符串，则说明也无其异位词，
                // 1)此时只需将当前字符串直接存入一个新的list中，
                // 2)同时也把该排序后的字符串和刚才创建的list都存入map中即可
                List<String> arr=new ArrayList<>();
                arr.add(str);
                map.put(sorted_str,arr);
            }
        }

        //4.最后，返回一个包含结果集的list即可（新建一个list，把所有结果（多个list）通过构造器的方式传入即可）
        //values()：用于获取集合中的所有的value值，
        //这里的value值是一个一个的分类的异位词的list集合；
        return new ArrayList<>(map.values());
    }
}


/**
 * 242. 判断两个字符串是否互为异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */

/**
 * 解题思路：有两种常见的思路
 * （1）思路一：排序：即通过对s和t进行排序，如果 t 是 s 的变位词，对两个字符串进行排序将产生两个相同的字符串。
 *      此外，如果 s 和 t 的长度不同，t 不能是 s的变位词，我们可以提前返回。
 *
 *  复杂度分析
 *  1）时间复杂度：O(nlogn)，假设 n 是 s 的长度，排序成本 O(nlogn) 和比较两个字符串的成本 O(n)。
 *  排序时间占主导地位，总体时间复杂度为 O(nlogn)。
 *  2）空间复杂度：O(1)，空间取决于排序实现，如果使用 heapsort，通常需要 O(1) 辅助空间。
 *  注意，在 Java 中，toCharArray() 制作了一个字符串的拷贝，所以它花费 O(n) 额外的空间，
 *  但是我们忽略了这一复杂性分析，因为：
 *  这依赖于语言的细节。
 *  这取决于函数的设计方式。例如，可以将函数参数类型更改为 char[]。
 */
class solution有效的字母异位词242_1{
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}

/**
 * （2）思路二：利用哈希表做字典映射（但这里我们可以不用真正的hashMap，使用一个数组即可，
 * 即令每个字符的ASCII码减去字符a的ASCII码所得到的的数值作为该字符在数组中的下标，令其出现的频率为该下标所对应的值即可）
 * 为了检查 t 是否是 s 的重新排列，我们可以计算两个字符串中每个字母的出现次数并进行比较。
 * 因为 S 和 T 都只包含 A−Z 的字母，所以一个简单的 26 位计数器表就足够了。
 *
 *  1）写法1：我们需要两个计数器数表进行比较吗？
 * 实际上不是，因为我们可以用一个计数器表计算 s 字母的频率，用 t 减少计数器表中的每个字母的计数器，然后检查计数器是否回到零。
 *
 *  2）写法2：或者我们可以先用计数器表计算 s，然后用 t 减少计数器表中的每个字母的计数器。
 * 如果在任何时候计数器低于零，我们知道 t 包含一个不在 s 中的额外字母，并立即返回 FALSE。
class solution49_2{
    /**
     * 写法1：
     */
class solution有效的字母异位词242_2{
    public boolean isAnagram01(String s, String t) {
        //0.特判
        if (s.length() != t.length()) {
            return false;
        }
        //1.创建一个数组，作用和hashMap一样，对字符做哈希映射
        int[] map = new int[26];
        //2.对每一个字符进行频率的统计及处理
        for (int i = 0; i < s.length(); i++) {
            //2.1若是s中的字符，就让其频率加一，若是t中的字符，就让该对应的频率减一
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        //3.再对map数组进行遍历，检查每个字符的频率count是否为0，若不是，则返回false，
        // 因为若这两个字符串是异位词的话，则它们中的每个字符虽然出现的顺序可能不一样，但各个字符出现的频率肯定是一样的，
        // 则通过一加一减的操作，其最后肯定为0，而若不为0，只能说明其中某一个字符串中有出现的频率高于或低于另一个字符串的字符，则肯定就不是异位词啦~
        for (int count : map) {
            if (count != 0) {
                return false;
            }
        }
        //4.若最后都没有检查出一处“count != 0”的情况，则说明是异位词，返回true即可
        return true;
    }

    /**
     * 写法2：
     */
    public boolean isAnagram02(String s, String t) {
        //特判
        if (s.length() != t.length()) {
            return false;
        }
        //定义一个数组当做map
        int[] map = new int[26];
        //先统计s中各字符的出现频率，使其对应的频率累加
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        //再统计t中各字符的频率，但是此时就把“在刚才存好了s字符串中各字符的”map中的对应处的字符频率减1
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
            //若发现t中某个字符在map中为负数，就表明t中该字符在s中根本没有，于是就可以断定该字符串t不可能是s的异位词啦~
            if (map[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}


/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * 示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */

/**
 * 解题思路：也是使用滑动窗口，和第76题思路完全相同，只是要的结果不同而已
 *
 * 参考链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/shi-yong-hua-dong-chuang-kou-jie-ti-hen-rong-yi-by/
 */
class 找到字符串中所有字母异位词438 {
        public List<Integer> findAnagrams(String s, String p) {
            //0.结果集（默认为空）
            List<Integer> list = new ArrayList<>();
            //1.特判
            if (s==null || s.length() == 0 || p == null || p.length() == 0)
                return list;
            //2.定义一个数组，用来记录字符串 p 中出现字符的频率，
            //也就是窗口内需要匹配的字符和相应的频率（因为需要匹配的字符串可能有重复）
            //和使用hashMap做哈希映射相同，这里就是使用字符作为下标，用其值作为频率/出现的次数
            int[] map = new int[256];
            for (char c: p.toCharArray()) {//把p转化为字符数组是为了便于处理
                map[c]++;
            }
            //3.再定义左右指针和要匹配的字符长度count，其中，左右指针一开始都指向头部
            int left = 0;
            int right = 0;
            int count = p.length();//表示要在字符串s中匹配的字符串长度，易知，若为0，则表示匹配结束/成功

            //4.开始移动左右指针，维护滑动窗口
            while (right < s.length()) {
                //4.1先处理右指针，即先让右指针向右走，直至包含所有的目标字符串中的字母
                if (map[s.charAt(right)] > 0) {//2）若右指针指向的字符在map中有，且还大于0，
                    //则表示该字符串是目标字符串p中的字符，则让需要匹配的长度减1即可
                    count--;
                }
                //1）先使map中的该字符的频率减1（不管该字符是否为p中的字符，这么写都符合逻辑，很巧妙，
                //比如，若不是p中的字符，则map[s.charAt(right)] =0，
                //则其减1之后，就小于0，则不会经过上述的if判断语句，没毛病）
                map[s.charAt(right)]--;
                //3）同时，右指针右移，以同样地方式判断下一个字符
                right++;

                //4.2当count为0时，表示通过右指针的移动，已经匹配了p中的所有字符，此时将左指针的下标加入到list中即可
                //（为什么此时左指针一定就在异位词的第一个位置？？？左指针不都还没动吗？？）
                if (count == 0) {
                    list.add(left);
                }

                int window_size = right - left;//当前窗口长度
                if (window_size == p.length()) {//4.3若滑动窗口长度等于了目标串s的长度时，此时第一个异位词已经匹配成功，开始匹配下一个，
                    // 已经就不该再移动右指针了，而应该移动左指针，
                    // 当左指针向右移动一位时，若该字符是字符串p中的字符时，则就相当于删除了一个有效字符（即字符串p中的字符），
                    // 则让count+1即可，即把这个移除的字符丢给右指针来匹配
                    // 同时让其在map中的频率也加一
                    if (map[s.charAt(left)] >= 0) {
                        count++;//2）把移除的字符重新记录在count中，让右指针来匹配
                    }
                    map[s.charAt(left)]++;//1）频率加一
                    left++;//3）同时，右指针右移，以同样地方式判断下一个字符
                }
            }
            //5.最后，返回list即可
            return list;
        }
}