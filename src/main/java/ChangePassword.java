import javax.swing.*;

public class ChangePassword {
    String newpassword = "";
    JFrame frame = new JFrame("Changing password!");

    public String oldPassword() {
        return JOptionPane.showInputDialog(frame, "Input your old Password");
    }



    public boolean registerPassword() {
        newpassword = JOptionPane.showInputDialog(frame, "New Password");
        String confirmPass = JOptionPane.showInputDialog(frame, "Confirm Password");
        if (!newpassword.equals(confirmPass)) {
            JOptionPane notMatching = new JOptionPane("Passwords don't match!", JOptionPane.ERROR_MESSAGE);
            frame = new JFrame("don't Match!");
            JDialog dialog = notMatching.createDialog(frame, "Don't Match!");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            return false;
        }
        return true;
    }

    public String getPassword() {
        return newpassword;
    }
}
