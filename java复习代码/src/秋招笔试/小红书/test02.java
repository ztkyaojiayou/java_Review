package 秋招笔试.小红书;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test02 {
    static List<List<String>> res = new ArrayList<List<String>>();
    public static int solution(String s) {
        int min = Integer.MAX_VALUE;
        helper(s,0,new ArrayList<String>());
        for (int i = 0;i<res.size();i++){
            int size = res.get(i).size();
            if (size < min){
                min = size;
            }
        }
        return min;
    }

    public static void helper(String s,int index,List<String> list ){
        if( list.size() > 0 && index >= s.length()){
            ArrayList<String> temp = new ArrayList<String>(list);
            res.add(temp);
        }
        for(int i = index; i < s.length(); i++){
            if(isPalindrome(s,index,i)){
                if(index == i){
                    list.add(Character.toString(s.charAt(i)));
                }else{
                    list.add(s.substring(index, i+1));
                }
                helper(s,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
    public static boolean isPalindrome(String str, int l, int r){
        if(l==r) return true;
        while(l<r){
            if(str.charAt(l)!=str.charAt(r)) return false;
            l++;r--;
        }
        return true;
    }
}



