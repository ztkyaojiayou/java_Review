package 数据结构与算法.第二遍.二叉树;

public class 序列化和反序列化二叉树 {
    //1.序列化
    public String Serialize(Node17 root){
        //递归结束的标志
        if (root == null){
            return "#";
        }
        String res = root.val+"," + Serialize(root.left)+"," + Serialize(root.right);
        return res;
    }

    //2.反序列化
    public Node17 Deserialize(String str){
        String[] new_str = str.split(",");
        int index = 0;
        if (index > new_str.length){
            return null;
        }
        //既然要建立二叉树，则要先建立根节点，接着再递归构建即可
        Node17 root = null;
        if (!new_str[index].equals("#")){
            root = new Node17(Integer.parseInt(new_str[index]));
            root.left = Deserialize(str);
            root.right= Deserialize(str);
            index++;
        }
return root;
    }
}
