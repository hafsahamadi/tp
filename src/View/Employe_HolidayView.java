package View;
import DAO.EmployeDAOimpl;
import Model.Employe;
import Model.EmployeModel;
import Model.Post;
import Model.Role;
import Model.Type_holiday;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Employe_HolidayView extends JFrame{



        // le tableau de employe et congé
        private JTabbedPane tabbedPane = new JTabbedPane();

        // les tabs
        private JPanel employeTab = new JPanel();
        private JPanel holidayTab = new JPanel();

        // les panels
        private JPanel Employepan = new JPanel();
        private JPanel Holidaypan = new JPanel();
        private JPanel Display_Table_employe = new JPanel();
        private JPanel Display_Table_holiday = new JPanel();
        private final JPanel Forme_employe = new JPanel();
        private final JPanel Forme_holiday = new JPanel();
        private JPanel panButton_employe = new JPanel();
        private JPanel panButton_holiday = new JPanel();

        // les labels du l'employe
        private JLabel label_nom = new JLabel("Nom");
        private JLabel label_prenom = new JLabel("Prenom");
        private JLabel label_email = new JLabel("Email");
        private JLabel label_tele = new JLabel("Telephone");
        private JLabel label_salaire = new JLabel("Salaire");
        private JLabel label_role = new JLabel("Role");
        private JLabel label_poste = new JLabel("Poste");

        // les labels du congé
        private JLabel label_employe = new JLabel("Nom de l'employé");
        private JLabel label_startDate = new JLabel("Date de debut (YYYY-MM-DD)");
        private JLabel label_endDate = new JLabel("Date de fin (YYYY-MM-DD)");
        private JLabel label_type = new JLabel("Type");
        private JComboBox<Type_holiday> TypeComboBox = new JComboBox<>(Type_holiday.values());

        // les textfield du l'employe
        private JTextField text_nom = new JTextField();
        private JTextField text_prenom = new JTextField();
        private JTextField text_email = new JTextField();
        private JTextField text_tele = new JTextField();
        private JTextField text_salaire = new JTextField();

        private JComboBox<Role> roleComboBox = new JComboBox<>(Role.values());
        private JComboBox<Post> posteComboBox = new JComboBox<>(Post.values());

        // les textfield du congé
        private JComboBox<String> text_employe = new JComboBox<>();
        private JTextField text_startDate = new JTextField("");
        private JTextField text_endDate = new JTextField("");

        // les boutons du l'employe
        private JButton addButton_employe = new JButton("Ajouter");
        private JButton updateButton_employe = new JButton("Modifier");
        private JButton deleteButton_employe = new JButton("Supprimer");
        private JButton displayButton_employe = new JButton("Afficher");

        // les boutons du congé
        private JButton addButton_holiday = new JButton("Ajouter");
        private JButton updateButton_holiday = new JButton("Modifier");
        private JButton deleteButton_holiday = new JButton("Supprimer");
        private JButton displayButton_holiday = new JButton("Afficher");


        // le tableau de l'employe
        JPanel pan0 = new JPanel(new BorderLayout());
        public static String[] columnNames_employe = {"ID", "Nom", "Prenom", "Email", "Téléphone", "Salaire", "Role", "Poste","solde"};
        public static DefaultTableModel tableModel = new DefaultTableModel(columnNames_employe, 0);
        public static JTable Tableau = new JTable(tableModel);

        // le tableau du congé
        JPanel pan1 = new JPanel(new BorderLayout());
        public static String[] columnNames_holiday = {"ID", "nom_employe","date_debut","date_fin","type"};
        public static DefaultTableModel tableModel1 = new DefaultTableModel(columnNames_holiday, 0);
        public static JTable Tableau1 = new JTable(tableModel1);

        public Employe_HolidayView() {

            setTitle("Gestion des employes et des congés");
            setSize(1000, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            add(tabbedPane);

            // Employe Tab
            employeTab.setLayout(new BorderLayout());
            employeTab.add(Employepan, BorderLayout.CENTER);

            Employepan.setLayout(new BorderLayout());
            Employepan.add(Display_Table_employe, BorderLayout.CENTER);
            Tableau.setFillsViewportHeight(true);
            Dimension preferredSize = new Dimension(900, 500);
            Tableau.setPreferredScrollableViewportSize(preferredSize);
            pan0.add(new JScrollPane(Tableau), BorderLayout.CENTER);
            Display_Table_employe.add(pan0);

            Employepan.add(panButton_employe, BorderLayout.SOUTH);
            panButton_employe.add(addButton_employe);
            panButton_employe.add(updateButton_employe);
            panButton_employe.add(deleteButton_employe);
            panButton_employe.add(displayButton_employe);

            Employepan.add(Forme_employe, BorderLayout.NORTH);
            Forme_employe.setLayout(new GridLayout(7, 2, 10, 10));
            Forme_employe.add(label_nom);
            Forme_employe.add(text_nom);
            Forme_employe.add(label_prenom);
            Forme_employe.add(text_prenom);
            Forme_employe.add(label_email);
            Forme_employe.add(text_email);
            Forme_employe.add(label_tele);
            Forme_employe.add(text_tele);
            Forme_employe.add(label_salaire);
            Forme_employe.add(text_salaire);
            Forme_employe.add(label_role);
            Forme_employe.add(roleComboBox);
            Forme_employe.add(label_poste);
            Forme_employe.add(posteComboBox);

            // Holiday Tab
            holidayTab.setLayout(new BorderLayout());
            holidayTab.add(Holidaypan, BorderLayout.CENTER);
            Holidaypan.setLayout(new BorderLayout());
            Holidaypan.add(Display_Table_holiday, BorderLayout.CENTER);

            Tableau1.setFillsViewportHeight(true);
            Tableau1.setPreferredScrollableViewportSize(preferredSize);
            pan1.add(new JScrollPane(Tableau1), BorderLayout.CENTER);
            Display_Table_holiday.add(pan1);

            Holidaypan.add(Forme_holiday, BorderLayout.NORTH);
            Forme_holiday.setLayout(new GridLayout(4, 2, 10, 10));
            Forme_holiday.add(label_employe);
            Forme_holiday.add(text_employe);
            Forme_holiday.add(label_startDate);
            Forme_holiday.add(text_startDate);
            Forme_holiday.add(label_endDate);
            Forme_holiday.add(text_endDate);
            Forme_holiday.add(label_type);
            Forme_holiday.add(TypeComboBox);

            Holidaypan.add(panButton_holiday, BorderLayout.SOUTH);
            panButton_holiday.add(addButton_holiday);
            panButton_holiday.add(updateButton_holiday);
            panButton_holiday.add(deleteButton_holiday);
            panButton_holiday.add(displayButton_holiday);

            // TabbedPane
            tabbedPane.addTab("Employe", employeTab);
            tabbedPane.addTab("Holiday", holidayTab);


            remplaire_les_employes();
            setVisible(true);
        }

        public void remplaire_les_employes () {
            List<Employe> Employes = new EmployeModel(new EmployeDAOimpl()).displayEmploye();
            text_employe.removeAllItems();
            for (Employe elem : Employes) {
                text_employe.addItem(elem.getId() + " - " + elem.getNom()+" "+elem.getPrenom());
            }
        }






        public int getId_employe() {
            return Integer.parseInt(text_employe.getSelectedItem().toString().split(" - ")[0]);
        }
        public String getNom() {
            return text_nom.getText();
        }

        public JTable getTable() {
            return (JTable) Display_Table_employe.getComponent(0);
        }

        public String getPrenom() {
            return text_prenom.getText();
        }

        public String getEmail() {
            return text_email.getText();
        }

        public String getTelephone() {
            return text_tele.getText();
        }

        public double getSalaire() {
            return Double.parseDouble(text_salaire.getText());
        }

        public Role getRole() {
            return (Role) roleComboBox.getSelectedItem();
        }

        public Post getPoste() {
            return (Post) posteComboBox.getSelectedItem();
        }

        public JButton getaddButton_employe () {
            return addButton_employe;
        }

        public JButton getupdateButton_employe () {
            return updateButton_employe;
        }

        public JButton getdeleteButton_employe () {
            return deleteButton_employe;
        }

        public JButton getdisplayButton_employe () {
            return displayButton_employe;
        }

        public JButton getaddButton_holiday () {
            return addButton_holiday;
        }

        public JButton getupdateButton_holiday () {
            return updateButton_holiday;
        }
        public JButton getdeleteButton_holiday () {
            return deleteButton_holiday;
        }

        public JButton getdisplayButton_holiday () {
            return displayButton_holiday;
        }
        public String getStartDate () {
            return text_startDate.getText();
        }

        public String getEndDate() {
            return text_endDate.getText();
        }

        public Type_holiday getType_holiday(){
            return (Type_holiday) TypeComboBox.getSelectedItem();
        }

        // methods d'affichage des messages
        public void afficherMessageErreur(String message) {
            JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        public void afficherMessageSucces(String message) {
            JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
        }

        // methodes de vider les champs
        public void viderChamps_em() {
            text_nom.setText("");
            text_prenom.setText("");
            text_email.setText("");
            text_tele.setText("");
            text_salaire.setText("");
            roleComboBox.setSelectedIndex(0);
            posteComboBox.setSelectedIndex(0);
        }

        public void viderChamps_ho() {
            text_startDate.setText("");
            text_endDate.setText("");
            TypeComboBox.setSelectedIndex(0);
        }

        // methodes de remplir les champs
        public void remplaireChamps_em (int id, String nom, String prenom, String email, String telephone, double salaire, Role role, Post poste) {
            text_nom.setText(nom);
            text_prenom.setText(prenom);
            text_email.setText(email);
            text_tele.setText(telephone);
            text_salaire.setText(String.valueOf(salaire));
            roleComboBox.setSelectedItem(role);
            posteComboBox.setSelectedItem(poste);
        }

        public void remplaireChamps_ho(int id_employe, String date_debut, String date_fin, Type_holiday type) {
            List<Employe> Employes = new EmployeModel(new EmployeDAOimpl()).displayEmploye();
            text_employe.removeAllItems();
            for (Employe elem : Employes) {
                if (elem.getId() == id_employe) {
                    text_employe.addItem(elem.getId() + " - " + elem.getNom()+" "+elem.getPrenom());
                    text_employe.setSelectedItem(elem.getId() + " - " + elem.getNom()+" "+elem.getPrenom());
                }
            }
            text_startDate.setText(date_debut);
            text_endDate.setText(date_fin);
            TypeComboBox.setSelectedItem(type);
        }


        public boolean testChampsVide_em (){
            return text_nom.getText().equals("") || text_prenom.getText().equals("") || text_email.getText().equals("") || text_tele.getText().equals("") || text_salaire.getText().equals("");
        }

        public boolean testChampsVide_ho () {
            return text_employe.getSelectedItem().equals("") || text_startDate.getText().equals("") || text_endDate.getText().equals("") || TypeComboBox.getSelectedItem().equals("");
        }

    }
