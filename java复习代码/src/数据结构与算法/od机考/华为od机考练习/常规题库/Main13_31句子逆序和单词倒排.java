package 数据结构与算法.od机考.华为od机考练习.常规题库;

import java.util.Scanner;

/**
 * 13）句子逆序--语句中除了英文字母外，不再包含其他字符
 * 描述
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 *
 * @author :zoutongkun
 * @date :2022/7/27 12:19 下午
 * @description :
 * @modyified By:
 */
class Main13句子逆序 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strArr = str.split(" ");
        //逆序输出--以一个单词为单位逆序（单词中的单词不必逆序）
        for (int i = strArr.length - 1; i >= 0; i--)
//            if(i!=0)
//                System.out.print(strArr[i]+" ");
//            else
//                //最后一个单词不用再加空格啦
//                System.out.print(strArr[i]);
            //其实没必要按照上述区分，最后一个单词会打印一个空格也没啥！
            System.out.print(strArr[i] + " ");
    }
}


/**
 * 31）单词倒排--和上一题重复，只是会包含有其他字符
 * 描述
 * 对字符串中的所有单词进行倒排。
 * <p>
 * 说明：
 * 1、构成单词的字符只有26个大写或小写英文字母；
 * 2、非构成单词的字符均视为单词间隔符；
 * 3、要求倒排后的单词间隔符以一个空格表示；
 * 如果原字符串中相邻单词间有多个间隔符时，倒排转换后也只允许出现一个空格间隔符；
 * 4、每个单词最长20个字母；
 * <p>
 * 示例1
 * 输入：
 * I am a student
 * 复制
 * 输出：
 * student a am I
 *
 * @author :zoutongkun
 * @date :2022/7/24 11:58 下午
 * @description :
 * @modyified By:
 */
class Main31单词倒排 {
    public static void main(String[] args) {
        Scanner ac = new Scanner(System.in);
        //题目中的测试用例就一个，即是一个一个测试的，因此可以不用写while
        while (ac.hasNextLine()) {
            String str = ac.nextLine();
            // 匹配非字母的字符进行分割
            String[] strArr = str.split("[^A-Za-z]");
            StringBuilder res = new StringBuilder();
            // 逆序添加分割完的单词
            for (int i = strArr.length - 1; i >= 0; i--) {
                //易知，这里会使得最后一个字符串后还会加上一个空格，
                // 但无所谓，在最后多输出一个空格没什么的呀！
//                //若就是不想加这个空格，则也可以判断一下
//                if (res.toString().length() == strArr.length-1){
//                    res.append(strArr[i]);
//                }
                res.append(strArr[i]).append(" ");
            }

            System.out.println(res);
        }
    }

}

