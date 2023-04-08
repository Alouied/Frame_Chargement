import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Fenetre extends JFrame implements ActionListener{
    
    TextField tf;
    TextArea ta;
    Canvas c;
    JButton b;
    Fenetre (){
        tf = new TextField(20);
        b = new JButton("Charger");
        JPanel p1 = new JPanel(); 
        p1.add(new JLabel("Nom de Fichier"));
        p1.add(tf);   
        JPanel p2 = new JPanel(); 
        p2.add(b);
        JPanel p3 = new JPanel();
        ta = new TextArea(4,40);
        p3.add(ta); 
        c = new Canvas();
        c.setSize(450,30);
        c.setBackground(Color.white);
        JPanel p4 = new JPanel();
        p4.add(c);
        setLayout(new GridLayout(4,1));
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        pack();
        setVisible(true);
        b.addActionListener(this);
    }
    public void actionPerformed (ActionEvent e){
        c.repaint();
        ThreadDessin th1 = new ThreadDessin(c, tf, ta);
        th1.start();
    }
    public static void main(String[] args){
        Fenetre fen = new Fenetre();
    }
}
class ThreadDessin extends Thread{
    Canvas c;
    TextField tf;
    TextArea ta;
    ThreadDessin(Canvas c,TextField tf,TextArea ta){
        this.c = c;
        this.tf = tf;
        this.ta = ta;
    }
    public void run(){
        try {
            BufferedReader fin = new BufferedReader(new FileReader(tf.getText()));
            String l;
            int i = 0;
            l = fin.readLine();
            Graphics g = c.getGraphics();
            while(l != null){
                ta.append(l + "\n");
                g.setColor(Color.GREEN);
                g.fillRect(i*30,0,30,30);
                i++;
                sleep(1000);
            }
            g.setColor(Color.GREEN);
            g.drawString("Chargement Termine!", 40, 20);
        } catch (Exception e) {
            ta.append("Error");
        }
    }



}