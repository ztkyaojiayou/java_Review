package 数据结构与算法.第三遍;

//使用hashMap即可，因为二者只是异位词，但他们的各个字母出现的频率肯定是一样的，抓住这一点即可
public class demo47_判断两个字符串是否为异位词 {
    public boolean isAnagram01(String s, String t) {
        int[] map = new int[26];
        for (int i = 0;i<s.length();i++){
            map[s.charAt(i)]++;
            map[t.charAt(i)]--;
        }

        for (int i = 0;i<map.length;i++){
            if (map[i] != 0){
                return false;
            }
        }

        return true;
    }
}
