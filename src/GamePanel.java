import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {

    //PREDEFINED
    JTextField textField;
    JLabel text;
    JPanel firstGuess = new JPanel();
    JPanel secondGuess = new JPanel();
    JPanel thirdGuess = new JPanel();
    JPanel fourthGuess = new JPanel();
    JPanel fifthGuess = new JPanel();
    JPanel sixthGuess = new JPanel();
    JPanel seventhGuess = new JPanel();
    JPanel eighthGuess = new JPanel();
    JPanel ninthGuess = new JPanel();
    JPanel tenthGuess = new JPanel();

    JPanel[] guesses = {firstGuess, secondGuess, thirdGuess, fourthGuess, fifthGuess,
            sixthGuess, seventhGuess, eighthGuess, ninthGuess, tenthGuess};

    Random random = new Random();
    String number, guess;
    int counter = 1;

    //MAIN GAME CONSTUCTOR
    GamePanel(){
        this.setPreferredSize(new Dimension(400,720));
        this.setLayout(new GridLayout(12,1,10,5));

        textField = new JTextField();
        textField.setBounds(10,10,380,50);
        textField.addKeyListener(this);

        text = new JLabel("Guess the number between 1000 and 9999");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font(null,Font.PLAIN,13));

        this.add(text);
        this.add(textField);

        number = newNumber();

        guesses[0] = firstGuess;
        System.out.println(guesses[0].getComponentCount());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //CLEAR TEXTFIELD ON BACKSPACE
        if(e.getKeyCode() == 8 && textField.getText().length() > 4)
            textField.setText("");

        //CHECK IF ENTERED TEXT IS FOUR DIGITS NUMBER
        if(e.getKeyCode() == 10){
            guess = textField.getText();
            if (guess.length() != 4){
                textField.setText("THE NUMBER MUST BE FOUR DIGITS!");
                return;
            }
            if (!isNumber(guess)){
                textField.setText("YOU MUST ENTER FOUR DIGITS NUMBER!");
                return;
            }
            textField.setText("");

            //ADD JPANEL WITH PLAYER GUESS 1-10
            switch (counter){
                case 1:
                    newGuess(firstGuess);
                    this.add(firstGuess);
                    this.revalidate();
                    break;
                case 2:
                    newGuess(secondGuess);
                    this.add(secondGuess);
                    this.revalidate();
                    break;
                case 3:
                    newGuess(thirdGuess);
                    this.add(thirdGuess);
                    this.revalidate();
                    break;
                case 4:
                    newGuess(fourthGuess);
                    this.add(fourthGuess);
                    this.revalidate();
                    break;
                case 5:
                    newGuess(fifthGuess);
                    this.add(fifthGuess);
                    this.revalidate();
                    break;
                case 6:
                    newGuess(sixthGuess);
                    this.add(sixthGuess);
                    this.revalidate();
                    break;
                case 7:
                    newGuess(seventhGuess);
                    this.add(seventhGuess);
                    this.revalidate();
                    break;
                case 8:
                    newGuess(eighthGuess);
                    this.add(eighthGuess);
                    this.revalidate();
                    break;
                case 9:
                    newGuess(ninthGuess);
                    this.add(ninthGuess);
                    this.revalidate();
                    break;
                case 10:
                    newGuess(tenthGuess);
                    this.add(tenthGuess);
                    this.revalidate();
                    break;
            }
            counter++;

            //END GAME OPTIONS
            if (counter > 10)
                endGame("TryAgain");
            if (guess.equals(number))
                endGame("You win!");
        }

        if (e.getKeyCode() == 81)
            System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 82)
            restartGame();
    }

    //FUNCTION TO GENERATE NEW NUMBER
    String newNumber(){
        return String.valueOf(random.nextInt(9000)+1000);
    }

    //FUNCTION TO CHECK IF TEXT IS NUMBER
    boolean isNumber(String string){
        boolean check = true;
        for(int i = 0; i < string.length(); i++){
            if (string.charAt(i) < '0' || string.charAt(i) > '9') {
                check = false;
                break;
            }
        }
        return check;
    }

    //FUNCTION TO CREATE JLABELS WITH PLAYERS NUMBER AND ADD THEM TO JPANEL
    void newGuess(JPanel panel){
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        for(int i = 0; i < 4; i++){
            if(guess.charAt(i) == number.charAt(i)){
                JLabel custom = new JLabel(String.valueOf(guess.charAt(i)));
                custom.setFont(new Font(null,Font.BOLD,25));
                custom.setForeground(Color.GREEN);
                custom.setOpaque(true);
                custom.setBackground(Color.WHITE);
                custom.setBorder(border);
                custom.setPreferredSize(new Dimension(50,50));
                custom.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(custom);
            } else {
                JLabel custom = new JLabel(String.valueOf(guess.charAt(i)));
                custom.setFont(new Font(null,Font.BOLD,25));
                custom.setForeground(Color.RED);
                custom.setOpaque(true);
                custom.setBackground(Color.WHITE);
                custom.setBorder(border);
                custom.setPreferredSize(new Dimension(50,50));
                custom.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(custom);
            }
        }
    }

    //FUNCTION TO END GAME
    void endGame(String message){
        textField.setText(message);
        textField.setEditable(false);
        text.setText("Restart game [R] / Quit game [Q]");
        //add restart game
    }

    void restartGame(){
        //REMOVE OLD NUMBERS
        for (int i = 0; i < counter-1; i++){
            guesses[i].removeAll();
            this.remove(guesses[i]);
        }

        //SET NEW STATS
        counter = 1;
        number = newNumber();
        textField.setEditable(true);
        textField.setText("");
        text.setText("Guess the number between 1000 and 9999");

        this.repaint();
        this.revalidate();
    }

}
