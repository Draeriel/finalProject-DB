import javax.swing.*;
import java.util.ArrayList;



public class showColumns {

    boolean display = false;
    boolean first = true;
    JList columnList = new JList(new String[] {"Name", "Status", "Gender", "Province", "Birth Place", "Death Date", "Death Cause", "Actor"});
    String[] values = new String[] {"name", "status", "gender", "province", "birthPlace", "deathDate", "deathCause", "actor"};
    String columns = "";
    String firstElement = "";
    ArrayList<Integer> index = new ArrayList<Integer>();

    showColumns(String columns) {
        this.columns = columns;
        wantDisplay();
        if (display) {
            displayColumns();
            for (int numero : columnList.getSelectedIndices() ) {
                index.add(numero);
                firstElement = (first)?" ":", ";
                this.columns += firstElement + values[numero];
                first = false;
            }
        } if (!display || columnList.getSelectedIndices().length == 0) {
            this.columns = " *";
            for (int i = 1 ; i <= 8; i += 1) {
                this.index.add(i);
            }
        }

    }
    public String getColumns() {
        return this.columns;
    }

    public ArrayList<Integer> getIndex() {
        return index;
    }

    private void wantDisplay() {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to see all columns displayed?", "Display or not display", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            display = true;
        }

    }
    private void displayColumns() {
        JOptionPane.showMessageDialog(
                null, columnList, "Which columns do you want to see displayed?", JOptionPane.PLAIN_MESSAGE);
    }

}
