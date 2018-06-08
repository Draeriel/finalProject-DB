import javax.swing.*;
import java.util.Arrays;

public class Where {
    String where;
    boolean filter = false;

    boolean first = true;
    JList filterList = new JList(new String[] {"Name", "Status", "Gender", "Province", "Birth Place", "Death Date", "Death Cause", "Actor"});

    Where(String where){
        this.where = where;
        wantFilter();
        if (filter) {
            filterResults();
            if (filterList.getSelectedIndices().length >= 1) {
                this.where += " where";
                for (int numero : filterList.getSelectedIndices() ) {
                    if (!first){
                        this.where += "and ";
                    }
                    if (numero == 0) {
                        nameFilter();
                    }
                    if (numero == 1) {
                        statusFilter();
                    }
                    if (numero == 2) {
                        genderFilter();
                    }
                    if (numero == 3) {
                        provinceFilter();
                    }
                    if (numero == 4) {
                        birthPlaceFilter();
                    }
                    if (numero == 5) {
                        deathDateFilter();
                    }
                    if (numero == 6) {
                        deathCauseFilter();
                    }
                    if (numero == 7) {
                        actorFilter();
                    }
                }
            }
        }

    }

    public String getWhere() {
        return where;
    }

    private void wantFilter() {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want to filter the results?", "Filter or not filter", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            filter = true;
        }
    }
    private void filterResults() {
        JOptionPane.showMessageDialog(
                null, filterList, "What do you want to filter?", JOptionPane.PLAIN_MESSAGE);

    }
    private void nameFilter() {
        JFrame frame = new JFrame("Filter by name");
        boolean selected = false;
        String name = "";
        while (!selected) {
            name = JOptionPane.showInputDialog(frame, "Input a text like you want to filter the name");
            if (name != "" && name != null) {
                selected = true;
            }
        }
        System.out.print(name);
        this.where += " name like '\"%" + name + "%\"' ";
        first = false;
    }
    private void statusFilter() {
        JList statusList = new JList(new String[] {"Civilian", "Non-Civilian"});
        boolean selected = false;
        while(!selected) {
            JOptionPane.showMessageDialog(
                    null, statusList, "Which status do you want to filter?", JOptionPane.PLAIN_MESSAGE, null);
            if (Arrays.toString(statusList.getSelectedIndices()) != "[]") {
                selected = true;
            }
        }
        boolean primero = true;
        String valor = "";
        for (int number : statusList.getSelectedIndices()) {
            if (!primero) {
                this.where += "or ";
            }
            if (number == 0) {
                valor = "Civilian";
            }
            if (number == 1) {
                valor = "Non-Civilian";
            }
            this.where += " status = '\"" + valor + "\"' ";

            primero = false;
        }
        first = false;
    }
    private void genderFilter() {
        JList genderList = new JList(new String[] {"Child - Male", "Adult - Female", "Child - Female", "Adult - Male"});
        boolean selected = false;
        while (!selected) {
            JOptionPane.showMessageDialog(
                    null, genderList, "Which gender do you want to filter?", JOptionPane.PLAIN_MESSAGE, null);
            if (Arrays.toString(genderList.getSelectedIndices()) != "[]") {
                selected = true;
            }
        }
        boolean primero = true;
        String valor = "";
        for (int number : genderList.getSelectedIndices()) {
            if (!primero) {
                this.where += "or ";
            }
            if (number == 0) {
                valor = "Child - Male";
            }
            if (number == 1) {
                valor = "Adult - Female";
            }
            if (number == 2) {
                valor = "Child - Female";
            }
            if (number == 3) {
                valor = "Adult - Male";
            }
            this.where += " gender = '\"" + valor + "\"' ";

            primero = false;
        }
        first = false;
    }
    private void provinceFilter() {
        JList provinceList = new JList(new String[] {"Homs",
                "Hama",
                "Hasakeh",
                "Idlib",
                "Damascus",
                "Damascus Suburbs",
                "Daraa",
                "Aleppo",
                "Raqqa",
                "Deir Ezzor",
                "Other Nationalities",
                "Tartous",
                "Lattakia",
                "Unknown",
                "Sweida",
                "Quneitra"});
        boolean selected = false;
        while (!selected) {
            JOptionPane.showMessageDialog(
                    null, provinceList, "Which province do you want to filter?", JOptionPane.PLAIN_MESSAGE, null);
            if (Arrays.toString(provinceList.getSelectedIndices()) != "[]") {
                selected = true;
            }
        }
        boolean primero = true;
        String valor = "";
        for (int number : provinceList.getSelectedIndices()) {
            if (!primero) {
                this.where += "or ";
            }
            if (number == 0) {
                valor = "Homs";
            }
            if (number == 1) {
                valor = "Hama";
            }
            if (number == 2) {
                valor = "Hasakeh";
            }
            if (number == 3) {
                valor = "Idlib";
            }
            if (number == 4) {
                valor = "Damascus";
            }
            if (number == 5) {
                valor = "Damascus Suburbs";
            }
            if (number == 6) {
                valor = "Daraa";
            }
            if (number == 7) {
                valor = "Aleppo";
            }
            if (number == 8) {
                valor = "Ragga";
            }
            if (number == 9) {
                valor = "Deir Ezzor";
            }
            if (number == 10) {
                valor = "Other Nationalities";
            }
            if (number == 11) {
                valor = "Tartous";
            }
            if (number == 12) {
                valor = "Lattakia";
            }
            if (number == 13) {
                valor = "Unknown";
            }
            if (number == 14) {
                valor = "Sweida";
            }
            if (number == 15) {
                valor = "Quneitra";
            }
            this.where += " province = '\"" + valor + "\"' ";

            primero = false;
        }
        first = false;
    }
    private void birthPlaceFilter() {
        JFrame frame = new JFrame("Filter by name");
        boolean selected = false;
        String birthPlace = "";
        while (!selected) {
            birthPlace = JOptionPane.showInputDialog(frame, "Input a text like you want to filter the birth place");
            if (birthPlace != "" && birthPlace != null) {
                selected = true;
            }
        }
        this.where += " birthPlace like '\"%" + birthPlace + "%\"' ";
        first = false;
    }
    private void deathDateFilter() {
        JTextField fldDay = new JTextField("",3);
        JTextField fldMonth = new JTextField("",3);
        JTextField fldYear = new JTextField("",4);
        JPanel message = new JPanel();
        message.add(fldDay);
        message.add(new JLabel("/"));
        message.add(fldMonth);
        message.add(new JLabel("/"));
        message.add(fldYear);
        boolean first = true;
        int counter = 0;
        while (counter < 2) {
            String title = (first)?"From when?":"To when?";
            boolean selected = false;
            while (!selected) {
                JOptionPane.showMessageDialog(null, message, title + " (dd/mm/yyyy)", JOptionPane.PLAIN_MESSAGE, null);
                if (!fldDay.getText().equals("")){
                   selected = true;
                    counter += 1;
                }
            }
            String query = (first)?" deathDate between '" + fldYear.getText() + "-" + fldMonth.getText() + "-" + fldDay.getText() + "' ":"and '" + fldYear.getText() + "-" + fldMonth.getText() + "-" + fldDay.getText() + "' ";
            first = false;
            this.where += query;
        }
        this.first = false;
    }
    private void deathCauseFilter() {
        JList deathCauseList = new JList(new String[] {"Warplane shelling",
                "Shelling",
                "Detention - Torture",
                "Shooting",
                "Explosion",
                "Kidnapping - Execution",
                "Unknown",
                "Other",
                "Field Execution",
                "Chemical and toxic gases",
                "Un-allowed to seek Medical help",
                "Siege",
                "Detention - Execution",
                "Kidnapping - Torture",
                "Kidnapping - Torture - Execution",
                "Detention - Torture - Execution",});
        boolean selected = false;
        while(!selected) {
            JOptionPane.showMessageDialog(
                    null, deathCauseList, "Which death cause do you want to filter?", JOptionPane.PLAIN_MESSAGE, null);
            if (Arrays.toString(deathCauseList.getSelectedIndices()) != "[]") {
                selected = true;
            }
        }
        boolean primero = true;
        String valor = "";
        for (int number : deathCauseList.getSelectedIndices()) {
            if (!primero) {
                this.where += "or ";
            }
            if (number == 0) {
                valor = "Warplane shelling";
            }
            if (number == 1) {
                valor = "Shelling";
            }
            if (number == 2) {
                valor = "Detention - Torture";
            }
            if (number == 3) {
                valor = "Shooting";
            }
            if (number == 4) {
                valor = "Explosion";
            }
            if (number == 5) {
                valor = "Kidnapping - Execution";
            }
            if (number == 6) {
                valor = "Unknown";
            }
            if (number == 7) {
                valor = "Other";
            }
            if (number == 8) {
                valor = "Field Execution";
            }
            if (number == 9) {
                valor = "Chemical and toxic gases";
            }
            if (number == 10) {
                valor = "Un-allowed to seek Medical help";
            }
            if (number == 11) {
                valor = "Siege";
            }
            if (number == 12) {
                valor = "Detention - Execution";
            }
            if (number == 13) {
                valor = "Kidnapping - Torture";
            }
            if (number == 14) {
                valor = "Kidnapping - Torture - Execution";
            }
            if (number == 15) {
                valor = "Detention - Torture - Execution";
            }
            this.where += " deathCause = '\"" + valor + "\"' ";

            primero = false;
        }
        first = false;
    }
    private void actorFilter() {
        JList actorList = new JList(new String[] {"Syrian government and affiliated militias",
                "Russian troops",
                "Armed opposition groups",
                "Not identified",
                "Self administration forces",
                "The organization of the Islamic State in Iraq and the Levant - ISIS",
                "International coalition forces",
                "Al-Nusra Front",
                "Unknown",
                "NA"
                });
        boolean selected = false;
        while(!selected) {
            JOptionPane.showMessageDialog(
                    null, actorList, "Which actor do you want to filter?", JOptionPane.PLAIN_MESSAGE, null);
            if (Arrays.toString(actorList.getSelectedIndices()) != "[]") {
                selected = true;
            }
        }
        boolean primero = true;
        String valor = "";
        for (int number : actorList.getSelectedIndices()) {
            if (!primero) {
                this.where += "or ";
            }
            if (number == 0) {
                valor = "Syrian government and affiliated militias";
            }
            if (number == 1) {
                valor = "Russian troops";
            }
            if (number == 2) {
                valor = "Armed opposition groups";
            }
            if (number == 3) {
                valor = "Not identified";
            }
            if (number == 4) {
                valor = "Self administration forces";
            }
            if (number == 5) {
                valor = "The organization of the Islamic State in Iraq and the Levant - ISIS";
            }
            if (number == 6) {
                valor = "International coalition forces";
            }
            if (number == 7) {
                valor = "Al-Nusra Front";
            }
            if (number == 8) {
                valor = "Unknown";
            }
            if (number == 9) {
                valor = "NA";
            }

            this.where += " actor = '\"" + valor + "\"' ";

            primero = false;
        }
        first = false;
    }
}
