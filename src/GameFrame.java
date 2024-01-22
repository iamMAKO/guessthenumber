import javax.swing.*;

public class GameFrame extends JFrame {

    //SET JFrame
    GameFrame(){
        this.setTitle("Guess The Number");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new GamePanel());
        this.pack();
        this.setVisible(true);
    }
}
