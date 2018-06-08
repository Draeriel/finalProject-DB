import javax.swing.*;


public class Login {
    String username = "";
    String password = "";
    JFrame frame = new JFrame("Let's Loggin!");

    public void userLogin() {
        username = JOptionPane.showInputDialog(frame, "Insert your username","Let's Loggin!", 1);
        password = JOptionPane.showInputDialog(frame, "Insert your password", "Let's Loggin!", 1);

    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }
}