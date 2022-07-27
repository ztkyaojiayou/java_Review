package 数据结构与算法.离职后刷题;

/**
 * @author :zoutongkun
 * @date :2022/4/27 2:16 下午
 * @description :
 * @modyified By:
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyCalc extends JFrame implements ActionListener {
    JFrame jf;

    JLabel jl1;
    JLabel jl2;
    JLabel jl3;

    JButton jb1;
    JButton jb2;
    JButton jb3;
    JButton jb4;

    JTextField jt1;
    JTextField jt2;
    JTextField jt3;
    JPanel jp1;
    JPanel jp2;
    JPanel jp3;
    JPanel jp4;
    Container con;

    public void start() {

        //窗口设置
        this.setLayout(new FlowLayout());
        this.setTitle("计算");
        this.setSize(150,200);

        con=this.getContentPane();
        //标签设置
        jl1=new JLabel("数字一");
        jl2=new JLabel("数字二");
        jl3=new JLabel("数字三");
        //按钮设置，及其注册事件监听
        jb1=new JButton("+");
        jb1.addActionListener(this);
        jb2=new JButton("-");
        jb2.addActionListener(this);
        jb3=new JButton("*");
        jb3.addActionListener(this);
        jb4=new JButton("/");
        jb4.addActionListener(this);
        //添加到面板
        jp1=new JPanel(new GridLayout(1,2));
        jp2=new JPanel(new GridLayout(1,2));
        jp3=new JPanel(new GridLayout(1,2));
        jp4=new JPanel(new GridLayout(1,2));
        //穿件文本框
        jt1=new JTextField();
        jt2=new JTextField();
        jt3=new JTextField();
        jp1.setVisible(true);

        jp1.add(jl1);
        jp1.add(jt1);
        con.add(jp1);

        jp2.add(jl2);
        jp2.add(jt2);
        con.add(jp2);

        jp3.add(jl3);
        jp3.add(jt3);
        con.add(jp3);

        jp4.add(jb1);
        jp4.add(jb2);
        jp4.add(jb3);
        jp4.add(jb4);
        con.add(jp4);

        this.setVisible(true);

    }
    public static void main(String [] args) {
        MyCalc a= new MyCalc();
        a.start();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jb1) {
            jt3.setText(String.valueOf(Double.parseDouble(jt1.getText())+Double.parseDouble(jt2.getText())));
        }else if(e.getSource()==jb2) {
            jt3.setText(String.valueOf(Double.parseDouble(jt1.getText())-Double.parseDouble(jt2.getText())));
        }else if(e.getSource()==jb3) {
            jt3.setText(String.valueOf(Double.parseDouble(jt1.getText())*Double.parseDouble(jt2.getText())));
        }else {
            if(Double.parseDouble(jt2.getText())==0.0) {
                jt3.setText("除数不能为零");
            }else {
                jt3.setText(String.valueOf(Double.parseDouble(jt1.getText())/Double.parseDouble(jt2.getText())));
            }
        }

    }
}
