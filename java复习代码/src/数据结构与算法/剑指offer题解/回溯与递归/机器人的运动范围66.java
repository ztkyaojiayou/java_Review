package 数据结构与算法.剑指offer题解.回溯与递归;

/**
 * 题目描述
 * 地上有一个m行和n列的方格。
 * 一个机器人从坐标(0,0)的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 * 请问该机器人能够达到多少个格子？
 */

/**
 * 思路解析：典型的回溯，DFS或BFS问题
 * 非常典型的路径问题。
 * 首先在某个节点处，要调用递归来决定某个位置的下一步去哪，此时有4个选择，每个选择都会进入下一个递归调用。
 * 当进入某个位置时，应当标记这个位置已访问过，避免之后又来到这里，从而重复计算，
 * 因此设计一个boolean的数组，这里设计的二维，也可以通过压缩，使用一维数组来表征某个位置是否访问。
 * 二维就是记录横纵坐标即第i行第j列的数据被访问了，直观，推荐新手用二维。接着就是边界条件和递归结束的条件的判断了。
 * 这类型题也是有套路的，主方法在原点作为起点，调用第一次递归后，
 * 在递归方法中，首先判断边界条件以及题目中所提的要求是否满足（本题的要求就是cal方法中的实现），
 * 都没问题，说明该位置可以访问，然后改变对应位置的标记。
 * 然后就是以该节点为中心，考虑下一步怎么走，本题就是4种走法，可以分开写，也可以一起写，
 * 由于本题是计数，所以就直接加在一起。
 * 然后return这些求和结果再+1，求和结果是下一步走的结果，
 * 而+1是本次访问此时的节点的次数。
 *
 */
class 机器人的运动范围66 {
    public int movingCount(int k, int rows, int cols) {
        //1.先定义一个用于标记该位置是否被访问过的Boolean型一维数组
        //（则需要把坐标转化为一个数，其实也可以直接定义一个二维数组的）
        boolean[] visited=new boolean[rows*cols];
        //2.再开始使用dfs深度优先搜索（也就是回溯）
        return dps(k, rows, cols, 0,0,visited);
    }

    //dps搜索的具体实现（关键）
    private int dps(int k, int rows, int cols,
                    int cur_row, int cur_col, boolean[] visited) {

        int i=cur_row*cols+cur_col;//2.0把坐标转化为一个数，便于处理
        //（因为每个位置是惟一的，则通过该换算（相当于把矩阵拉直后该位置的位置）之后也必然是惟一的）

        //2.1递归结束的条件：即若该位置越界或者被访问过或者该位置的坐标和大于了目标和，就返回0
        if(cur_row<0||cur_row>=rows||cur_col<0||cur_col>=cols||visited[i]||!checkSum(k,cur_row,cur_col)) {
            return 0;
        }

        //2.2每访问了一个位置就把该位置标记为“已被访问过”
        visited[i]=true;
        //2.3开始回溯/DFS搜索，把四个方向上的结果（相当于四种情况）都加上即可（式后的“+1”是指当前位置也算一个格子）
        return  dps(k, rows, cols,cur_row,cur_col+1,visited)//向右走
                + dps(k, rows, cols,cur_row,cur_col-1,visited)//向左走
                + dps(k, rows, cols,cur_row+1,cur_col,visited)//向下走
                + dps(k, rows, cols,cur_row-1,cur_col,visited) + 1;//向上走
    }

    //判断该位置的下标的和是否满足要求的方法
    private boolean checkSum(int threshold, int row, int col) {
        int sum=0;//0.坐标的各位上的数字和
        //1.求行的各位数字之和
        while(row!=0){//注意：%是取余，/是取商
            //先将行数对10取余，就可以得到个位上的数字，此时sum = 个位上的值，
            //接着再将行数对10取商，就可以得到十位上的数字了，再将十位上的数字对10取余，
            //其值还是不会变（相当于商0），因此再累加到sum上即为行上各位数字的和啦~
            sum+=row%10;//先取余，得到个位上的数，再加到sum1上
            row=row/10;//取商，得到十位上的数字（其实更准确的说，应该是除了个位剩下的位上的数，
            //如对于一个三位数357，当先取余后，得到个位上7，接着再取商，
            //得到的是35，也即百位和十位上的数，以此类推，每次都是从低位开始累加的）
        }
        //2.同理，再求列的各位数字之和
        while(col!=0){
            sum+=col%10;
            col=col/10;
        }
        //3.1若坐标和大于目标值，则返回false
        if(sum>threshold){
            return false;
        }
        //3.2否则，说明符合要求，返回true
        return true;
    }
    }