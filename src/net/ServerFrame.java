package net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author XuMinghao
 * @create 2019/6/5-23:35
 */
public class ServerFrame extends  Frame{
    public static  final ServerFrame INSTANCE =new ServerFrame();
    TextArea taL=new TextArea();
    TextArea taR=new TextArea();
    Server server=new Server();
//    Button button=new Button("start");
    private ServerFrame(){
        this.setSize(1000,600);
        this.setLocation(100,200);
//       this.add(button,BorderLayout.NORTH);
        Panel p = new Panel(new GridLayout(1, 2));
        p.add(taL);
        p.add(taR);
        this.add(p);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        /*this.button.addActionListener((e)->{
            server.startServer();
        });*/
    }
    public void updateServerMsg(String s){
        this.taL.setText(taL.getText()+s+"\n");
    }
    public void updateClientMsg(String s){
        this.taR.setText(taR.getText()+s+"\n");
    }
    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.startServer();
    }

}
