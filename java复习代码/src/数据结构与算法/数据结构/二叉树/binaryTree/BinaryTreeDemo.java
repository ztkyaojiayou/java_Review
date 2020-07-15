package 数据结构与算法.数据结构.二叉树.binaryTree;


/**
 * 本Demo演示的是二叉树的三种遍历方法和其对应的查找指定结点的算法以及删除结点的方法
 */

//1.先创建HeroNode结点类，在其中编写具体的结点的遍历方法、删除结点方法和查找方法,因为所有的方法都是基于结点的
// （这是重点和核心）
class HeroNode {
    //1.1定义基本成员变量
    private int no;//序号
    private String name;//序号对应的名称（即结点数据）
    private HeroNode left; //左结点，默认null
    private HeroNode right; //右节点，默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }

    //1.2定义/编写具体的遍历方法和查找方法(使用的递归）
    //注意：前序后序中序遍历都是针对的根节点
    //1.2.1.前序遍历
    public void preOrder() {
        System.out.println(this); //先输出（每一次遍历的）父结点
        //递归向左子树前序遍历
        if(this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null) {
            this.right.preOrder();
        }
    }
    //1.2.2.中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if(this.left != null) {
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
    //1.2.3.后序遍历
    public void postOrder() {
        if(this.left != null) {
            this.left.postOrder();
        }
        if(this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //1.2.4.前序遍历查找
    /**
     *
     * @param no 查找目标：no
     * @return 如果找到就返回该Node ,如果没有找到返回 null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        //比较当前结点值是不是no
        //1.若是，则直接返回
        if(this.no == no) {
            return this;
        }
        //2.若不是,则开始左递归前序查找，判断当前结点的左子节点是否为空，若不为空，则继续向左递归前序查找
        //  若找到结点，则返回
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null) {//说明我们左子树找到
            return resNode;
        }
        //3.若还没有找到，则开始右递归前序查找，判断当前结点（this）的右子节点是否为空，若不为空，则继续向右递归前序查找
        // 若找到结点，则返回
        if(this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //1.2.5.中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if(this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if(this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    //1.2.6.后序遍历查找
    public HeroNode postOrderSearch(int no) {

        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null) {//说明在左子树找到
            return resNode;
        }

        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if(this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        //如果左右子树都没有找到，就比较当前结点是不是
        if(this.no == no) {
            return this;
        }
        return resNode;
    }


    //1.2.7.递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {//这个no并不是要删除的结点，而是借助它去删除其子节点

        //思路（删除=置为null）
		/*
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除，而不能去判断当前这个结点是不是需要删除.
		 * （也即：要想删除某一结点，应该借助其父节点进行删除）
			2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.

		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if(this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if(this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.若没有删除，则需要向左子树进行递归删除
        if(this.left != null) {
            this.left.delNode(no);
        }
        //5.若还没有删除，则需要向右子树进行递归删除
        if(this.right != null) {
            this.right.delNode(no);
        }
    }

}

//2.再定义BinaryTree二叉树（即把结点加到二叉树上），再在这棵树上执行上述操作
class BinaryTree {
    private HeroNode root;//根结点，所有的方法都是从root结点开始的

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //2.1前序遍历
    public void preOrder() {//注意：这个preOrder方法是在这里新定义的
        if(this.root != null) {
            this.root.preOrder();//这个preOrder方法是HeroNode类中的（由root调用也可看出）
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //2.2中序遍历
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //2.3后序遍历
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //2.4前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //2.5中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //2.6后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //2.7删除结点
    public void delNode(int no) {
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }

}

//3.再测试（先创建一个BinaryTree对象，再往此树上添加结点，最后再调用相应的方法进行测试验证）
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //3.1先需要创建一颗二叉树
       BinaryTree binaryTree = new BinaryTree();
        //3.2再创建/添加需要的结点（此时还没有安置到二叉树上）
        HeroNode root = new  HeroNode(1, "宋江");//根节点
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //3.3再把以上结点按照要求放置到各个分支上(说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树)
        //至此，一颗包含指定结点的二叉树创建完成
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);


        //4.最后，开始测试（均测试通过）
        //4.1测试前序遍历
        System.out.println("前序遍历"); // 1,2,3,5,4
        binaryTree.preOrder();

        //4.2测试中序遍历
        System.out.println("中序遍历");
        binaryTree.infixOrder(); // 2,1,5,3,4

        //4.3测试后序遍历
        System.out.println("后序遍历");
        binaryTree.postOrder(); // 2,5,4,3,1

        //4.4测试前序遍历查找
        //前序遍历的次数 ：4
        System.out.println("前序遍历方式~~~");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

        //4.5测试中序遍历查找
        //中序遍历3次
//		System.out.println("中序遍历方式~~~");
//		HeroNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //4.6测试后序遍历查找
        //后序遍历查找的次数  2次
//		System.out.println("后序遍历方式~~~");
//		HeroNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //4.7测试删除结点
        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); //  1,2,3,5,4
        binaryTree.delNode(5);
        //binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4

    }

}



