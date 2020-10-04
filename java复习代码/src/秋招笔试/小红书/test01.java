package 秋招笔试.小红书;

public class test01 {
    public int findMin(int[] tree) {
        if (tree == null || tree.length == 0){
            return 0;
        }
        int len = tree.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0;i<len;i++){
            if (tree[i] < min){
                min = tree[i];
            }
        }
        if (min > 1){
            return 1;
        }else {
            return 0;
        }

    }
}
