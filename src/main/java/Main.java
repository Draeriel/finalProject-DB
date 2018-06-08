import com.sun.xml.internal.bind.v2.model.annotation.RuntimeInlineAnnotationReader;
import org.omg.SendingContext.RunTime;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String where = "";
        String display = "";
        String userPassword = "";
        String username = "";

        boolean start = true;
        boolean changePassword = false;
        boolean logged = false;
        boolean registered = false;
        boolean filterTable = false;
        int counter = 0;


        try {

            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/finalproject?" +
                            "user=root&password=nodeseoesedon1991");
            while (start) {
//Questions if is registered --------------------------------------------------------------------------------------------------------------------------------------------------------
            int reply = JOptionPane.showConfirmDialog(null, "Do you have already an account?", "Account?", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                registered = true;
            }

// Registration---------------------------------------------------------------------------------------------------------------------------------------------------------------------
            while (!registered) {
                Registration register = new Registration();
                register.registerUsername();
                stmt = conn.createStatement();
                if (stmt.execute("select distinct Username, Password from Accounts where username = '" + register.getUsername() + "'")) {
                    rs = stmt.getResultSet();
                    while (rs.next()) {
                        username = rs.getString("Username");
                        if (username.equals(register.getUsername())) {
                            JOptionPane already = new JOptionPane("Username already exists!", JOptionPane.ERROR_MESSAGE);
                            JFrame frame = new JFrame("Already Exists");
                            JDialog dialog = already.createDialog(frame, "Registration Error");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);

                        }

                    }
                }
                if (!username.equals(register.getUsername()) && register.registerPassword()) {
                    stmt.executeUpdate("insert into Accounts ( Username, Password) Values (\"" + register.getUsername() + "\", \"" + register.getPassword() + "\")\n");
                    registered = true;
                }
            }


//Login--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            while (!logged) {
                Login login = new Login();
                login.userLogin();
                stmt = conn.createStatement();
                if (stmt.execute("select distinct Username, Password from Accounts where username = '" + login.getUsername() + "'")) {
                    rs = stmt.getResultSet();
                    while (rs.next()) {
                        username = rs.getString("Username");
                        if (username.equals(login.getUsername())) {
                            String password = rs.getString("Password");
                            if (!password.equals(login.getPassword())) {
                                JOptionPane notMatching = new JOptionPane("Username or password are not correct", JOptionPane.ERROR_MESSAGE);
                                JFrame frame = new JFrame("No connection!");
                                JDialog dialog = notMatching.createDialog(frame, "No connection!");
                                dialog.setAlwaysOnTop(true);
                                dialog.setVisible(true);
                            } else {
                                userPassword = login.getPassword();
                                JOptionPane notMatching = new JOptionPane("Logged Successfuly", 1);
                                JFrame frame = new JFrame(":)");
                                JDialog dialog = notMatching.createDialog(frame, ":)");
                                dialog.setAlwaysOnTop(true);
                                dialog.setVisible(true);
                                logged = true;
                            }
                        }
                    }


                }
            }
//What do you wanna do? --------------------------------------------------------------------------------------------------------------------------------------------------------------
            while (logged) {
                String[] choices = {"Read the table", "Change Password", "Delete Account", "Log out", "Shut down"};
                String input = (String) JOptionPane.showInputDialog(null, "What do you wanna do?",
                        "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null,
                        choices, // Array of choices
                        choices[0]); // Initial choice

                if (input == choices[0]) {
                    filterTable = true;
                }
                if (input == choices[1]) {
                    changePassword = true;
                }
                if (input == choices[2]) {
                    JFrame frame = new JFrame("Confirm your pasword");
                    String pass = JOptionPane.showInputDialog(frame, "Confirm your password");
                    if (userPassword.equals(pass)) {
                        reply = JOptionPane.showConfirmDialog(null, "Are you sure do you wanna delete your account?", "I will miss you T__T", JOptionPane.YES_NO_OPTION, 2);
                        if (reply == JOptionPane.YES_OPTION) {
                            stmt.executeUpdate("delete from Accounts where Username = '" + username + "'");

                            JOptionPane notMatching = new JOptionPane("Account succesfuly deleted", 1);
                            frame = new JFrame(":(");
                            JDialog dialog = notMatching.createDialog(frame, ":(");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                            logged = false;
                        }
                    }

                }
                if (input == choices[3]) {
                    logged = false;
                }
                if (input == choices[4]) {
                    Runtime.getRuntime().halt(0);

                }


//Filter-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                while (filterTable) {
                    where = "";
                    display = "";
                    Where query = new Where(where);
                    showColumns column = new showColumns(display);

                    where = "";
                    display = "";
                    where += query.getWhere();
                    display += column.getColumns();


                    ArrayList<Integer> displayer = new ArrayList<Integer>();
                    Boolean displayName = false;
                    Boolean displayStatus = false;
                    Boolean displayGender = false;
                    Boolean displayProvince = false;
                    Boolean displayBirthPlace = false;
                    Boolean displayDeathDate = false;
                    Boolean displayDeathCause = false;
                    Boolean displayActor = false;
                    displayer = column.getIndex();
                    System.out.println("select distinct" + display + " from Table_Name " + where);

                    PreparedStatement prep = conn.prepareStatement("select distinct" + display + " from Table_Name " + where);

                    ResultSet rss = prep.executeQuery();

                    for (int numero : displayer) {
                        if (numero == 1) {
                            displayName = true;
                        }
                        if (numero == 2) {
                            displayStatus = true;
                        }
                        if (numero == 3) {
                            displayGender = true;
                        }
                        if (numero == 4) {
                            displayProvince = true;
                        }
                        if (numero == 5) {
                            displayBirthPlace = true;
                        }
                        if (numero == 6) {
                            displayDeathDate = true;
                        }
                        if (numero == 7) {
                            displayDeathCause = true;
                        }
                        if (numero == 8) {
                            displayActor = true;
                        }
                    }

//Prints---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    counter = 0;
                    while (rss.next()) {

                        File file = new File("C:\\Users\\pc\\Downloads\\patterns-builder-ejercicio\\lastproject\\result.txt");
                        try {
                            counter += 1;
                            FileWriter bw = new FileWriter(file.getAbsoluteFile(), true);
                            bw.write(counter + ".   ");


                            Integer i = 1;
                            if (displayName) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayStatus) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayGender) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayProvince) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayBirthPlace) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayDeathDate) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayDeathCause) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i) + "   ");
                                i += 1;
                            }
                            if (displayActor) {
                                bw.write(rss.getString(i) + "   ");
                                System.out.print(rss.getString(i));
                            }
                            bw.write("\r\n");
                            System.out.println();


                            bw.close();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    reply = JOptionPane.showConfirmDialog(null, "Do you wanna read the table again?", "Filter or not filter", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.NO_OPTION) {
                        filterTable = false;
                    }
                }


//Changepassword-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
                while (changePassword) {
                    ChangePassword change = new ChangePassword();
                    if (change.oldPassword().equals(userPassword)) {
                        if (change.registerPassword()) {
                            stmt.executeUpdate("update Accounts set Password = '" + change.getPassword() + "' where Username = '" + username + "'");
                            userPassword = change.getPassword();
                            changePassword = false;
                        }
                    } else {
                        JOptionPane notMatching = new JOptionPane("That's not your old password", 1);
                        JFrame frame = new JFrame(":)");
                        JDialog dialog = notMatching.createDialog(frame, ":(");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }
                }
            }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            }
            } catch(SQLException ex){
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }


    }



