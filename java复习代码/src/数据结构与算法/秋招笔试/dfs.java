package 数据结构与算法.秋招笔试;

public class dfs {
    /** 存储节点信息*/
    private char[] vertices;

    /** 存储边信息（邻接矩阵）*/
    private  int[][] arcs;

    /** 图的节点数*/
    private int vexnum;

    /** 记录节点是否已被遍历*/
    private boolean[] visited;

    /** 初始化*/
    public dfs(int n) {
        vexnum = n;
        vertices = new char[n];
        arcs = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < vexnum; i++) {
            for (int j = 0; j < vexnum; j++) {
                arcs[i][j] = 0;
            }
        }
    }

    /** 添加边*/
    public void addEdge(int i, int j){
        if(i==j){
            return ;
        }
        // 无向图对称的.
        arcs[i][j]=1;
        arcs[j][i]=1;
    }

    /** 设置节点集*/
    public void setVertices(char[] vertices){
        this.vertices=vertices;
    }

    /** 设置节点访问标记*/
    public void setVisited(boolean[] visited){
        this.visited=visited;
    }

    /** 打印遍历节点*/
    public void visit(int i){
        System.out.print(vertices[i]+ " ");
    }

    /**
     *  输出邻接矩阵
     */
    public void pritf(int[][] arcs){
        for(int i=0;i<arcs.length;i++){
            for(int j=0;j<arcs[0].length;j++){
                System.out.print(arcs[i][j]+ "\t");
            }
            System.out.println();
        }
    }
    /** 从第i节点开始深度优先遍历*/
    public void traverse(int i){
        // 标记第i个节点已遍历
        visited[i]=true;
        // 打印当前已经遍历的节点
        visit(i);
        // 遍历邻接矩阵中第i个节点的直接连通节点
        for(int j=0;j<vexnum;j++){
            if(arcs[i][j]==1&&visited[j]==false){
                traverse(j);
            }
        }
    }

    public void dfs(){
        // 初始化节点访问标记
        for(int i=0;i<visited.length;i++){
            visited[i]=false;
        }
        // 从没有被遍历的节点开始深度优先遍历
        for(int i=0;i<vexnum;i++){
            // 如果没有被访问过.
            if(visited[i]==false){
                traverse(i);
            }
        }
        // 输出二维矩阵
        System.out.println();
        pritf(arcs);
    }

    //测试
    public static void main(String[] args) {
        dfs dfs = new dfs(9);
        // 添加节点集
        char[] vertices = {'A','B','C','D','E','F','G','H','I'};
        // 设置顶点集
        dfs.setVertices(vertices);
        // 添加边
        dfs.addEdge(0, 1);
        dfs.addEdge(0, 5);
        dfs.addEdge(1, 0);
        dfs.addEdge(1, 2);
        dfs.addEdge(1, 6);
        dfs.addEdge(1, 8);
        dfs.addEdge(2, 1);
        dfs.addEdge(2, 3);
        dfs.addEdge(2, 8);
        dfs.addEdge(3, 2);
        dfs.addEdge(3, 4);
        dfs.addEdge(3, 6);
        dfs.addEdge(3, 7);
        dfs.addEdge(3, 8);
        dfs.addEdge(4, 3);
        dfs.addEdge(4, 5);
        dfs.addEdge(4, 7);
        dfs.addEdge(5, 0);
        dfs.addEdge(5, 4);
        dfs.addEdge(5, 6);
        dfs.addEdge(6, 1);
        dfs.addEdge(6, 3);
        dfs.addEdge(6, 5);
        dfs.addEdge(6, 7);
        dfs.addEdge(7, 3);
        dfs.addEdge(7, 4);
        dfs.addEdge(7, 6);
        dfs.addEdge(8, 1);
        dfs.addEdge(8, 2);
        dfs.addEdge(8, 3);
        System.out.print("深度优先遍历（递归实现）：");
        dfs.dfs();
    }
}
