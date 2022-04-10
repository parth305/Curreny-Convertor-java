import javax.swing.*;
import java.awt.*;

public class Myframe extends JFrame {
    MyPanal panal;
    Myframe(){
        panal=new MyPanal();
     //   panal.setBackground(Color.BLACK);

        this.add(panal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);
        this.setBackground(Color.black);
        this.setResizable(false);
        this.setTitle("Currency Convertor");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
