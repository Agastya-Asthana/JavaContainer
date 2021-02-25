import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class PasswordGenerator {

  private static String GeneratePassword(int passwordLength) {
    Random random = new Random();
    char[] characterPassword = new char[passwordLength];
    
    for (int i = 0; i < characterPassword.length; i++) {
      characterPassword[i] = (char)(random.nextInt(94) + 33);
    }

    return String.valueOf(characterPassword);
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Window");
    frame.setLayout(null);
    frame.setSize(1250, 1250);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel label = new JLabel("Password");
    label.setBounds(617, 200, 100, 30);
    frame.add(label);

    JButton button = new JButton("Generate password");
    button.setBounds(560, 300, 150, 50);
    frame.add(button);
    button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        label.setText("You pressed the button");
      }
    });

    frame.setVisible(true);
  }
}
