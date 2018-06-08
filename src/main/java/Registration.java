import javax.swing.*;

public class Registration {
    String username = "";
    String password = "";
    JFrame frame = new JFrame("Let's register!");

    public void registerUsername() {
        username = JOptionPane.showInputDialog(frame, "Username", "Register", 1);
    }

    public boolean registerPassword() {
        password = JOptionPane.showInputDialog(frame, "Password");
        String confirmPass = JOptionPane.showInputDialog(frame, "Confirm Password");
        if (!password.equals(confirmPass)) {
            JOptionPane notMatching = new JOptionPane("Passwords don't match!", JOptionPane.ERROR_MESSAGE);
            frame = new JFrame("don't Match!");
            JDialog dialog = notMatching.createDialog(frame, "Don't Match!");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            return false;
        }
        return true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}



