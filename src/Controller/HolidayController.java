package Controller;

import DAO.EmployeDAOimpl;
import Model.*;
import View.*;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HolidayController {

        private final Employe_HolidayView View;
        public HolidayModel model_holiday;
        public static int id = 0;
        public static int oldselectedrow = -1;
        public static boolean test = false;
        int id_employe = 0;
        String nom_employe = "";
        public static String OldstartDate = null;
        public static String OldendDate = null;
        Type_holiday type = null;
        int oldsolde = 0;
        int solde = 0;
        boolean updatereussi = false;
        Employe targetEmploye = null;

        public HolidayController(Employe_HolidayView view, HolidayModel model) {
            this.View = view;
            this.model_holiday= model;

            View.getdeleteButton_holiday().addActionListener(e -> deleteHoliday());
            View.getupdateButton_holiday().addActionListener(e -> updateHoliday());
            Employe_HolidayView.Tableau1.getSelectionModel().addListSelectionListener(e -> updateHolidaybyselect());
            View.getaddButton_holiday().addActionListener(e -> addHoliday());
            View.getdisplayButton_holiday().addActionListener(e -> displayHoliday());
        }

        private void addHoliday() {
            int id_employe = View.getId_employe();
            Date startDate = Date.valueOf(View.getStartDate());
            Date endDate = Date.valueOf(View.getEndDate());
            Type_holiday type = View.getType_holiday();

            View.viderChamps_ho();

            Employe targetEmploye = null;

            // Rechercher l'employé correspondant
            for (Employe employe : new EmployeModel(new EmployeDAOimpl()).displayEmploye()) {
                if (employe.getId() == id_employe) {
                    targetEmploye = employe;
                    break;
                }
            }

            if (targetEmploye == null) {
                View.afficherMessageErreur("Cet employé n'existe pas.");
                return;
            }

            // Calculer la durée du congé demandé
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
                    startDate.toLocalDate(),
                    endDate.toLocalDate()
            );

            if (daysBetween <= 0) {
                View.afficherMessageErreur("Les dates de début et de fin sont invalides.");
                return;
            }

            // Vérifier si le solde de congé est suffisant
            if (targetEmploye.getSolde() < daysBetween) {
                View.afficherMessageErreur("Le solde de congé de l'employé est insuffisant.");
                return;
            }

            try {
                // Ajouter la demande de congé
                boolean addReussi = model_holiday.addHoliday(0, id_employe, startDate, endDate, type, targetEmploye);

                if (addReussi) {
                    // Réduire le solde de congé après l'ajout réussi
                    targetEmploye.setSolde(targetEmploye.getSolde() - (int) daysBetween);
                    View.afficherMessageSucces("Holiday a bien été ajoutée.");
                } else {
                    View.afficherMessageErreur("Holiday n'a pas été ajoutée.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                View.afficherMessageErreur("Erreur lors de l'ajout : " + e.getMessage());
            }
        }

        private void displayHoliday() {
            List<Holiday> Holidays = model_holiday.displayHoliday();
            if (Holidays == null || Holidays.isEmpty()) {
                View.afficherMessageErreur("Aucune holiday.");
                return; // Retourner pour ne pas continuer l'exécution si la liste est vide
            }

            DefaultTableModel tableModel1 = (DefaultTableModel) Employe_HolidayView.Tableau1.getModel();
            tableModel1.setRowCount(0); // Clear existing rows in the table

            for (Holiday e : Holidays) {
                String nom_employe = null;
                List<Employe> Employes = new EmployeModel(new EmployeDAOimpl()).displayEmploye();
                for (Employe em : Employes) {
                    if (em.getId() == e.getId_employe()) {
                        nom_employe = em.getId() + " - " + em.getNom() + " " + em.getPrenom();
                        break;
                    }
                }
                // Ajout de la ligne dans le tableau
                tableModel1.addRow(new Object[]{e.getId_holiday(), nom_employe, e.getStartDate(), e.getEndDate(), e.getType()});
            }
            View.remplaire_les_employes();
        }


        private void deleteHoliday(){
            int selectedrow = Employe_HolidayView.Tableau1.getSelectedRow();
            if(selectedrow == -1){
                View.afficherMessageErreur("Veuillez selectionner une ligne.");
            }else{
                int id = (int) Employe_HolidayView.Tableau1.getValueAt(selectedrow, 0);
                int id_employe = Integer.parseInt((Employe_HolidayView.Tableau1.getValueAt(selectedrow, 1)).toString().split(" - ")[0]);
                int olddaysbetween =  (int) ( (Date.valueOf(OldendDate).toLocalDate().toEpochDay() - Date.valueOf(OldstartDate).toLocalDate().toEpochDay()));
                for(Employe e : new EmployeModel(new EmployeDAOimpl()).displayEmploye()){
                    if(e.getId() == id_employe){
                        solde = e.getSolde();
                        break;
                    }
                }
                EmployeController.updateSolde(id_employe,solde+olddaysbetween);
                boolean deletereussi = model_holiday.deleteHoliday(id);
                if(deletereussi){
                    View.afficherMessageSucces("Holiday a bien ete supprimer.");
                    displayHoliday();
                }else{
                    View.afficherMessageErreur("Holiday n'a pas ete supprimer.");
                }
            }
        }

        private void updateHolidaybyselect(){
            int selectedrow = Employe_HolidayView.Tableau1.getSelectedRow();

            if (selectedrow == -1) {
                return;
            }
            try{
                id = (int) Employe_HolidayView.Tableau1.getValueAt(selectedrow, 0);
                nom_employe = (String) Employe_HolidayView.Tableau1.getValueAt(selectedrow, 1);
                id_employe = Integer.parseInt(nom_employe.split(" - ")[0]);
                OldstartDate = String.valueOf(Employe_HolidayView.Tableau1.getValueAt(selectedrow, 2));
                OldendDate = String.valueOf(Employe_HolidayView.Tableau1.getValueAt(selectedrow, 3));
                type = (Type_holiday) Employe_HolidayView.Tableau1.getValueAt(selectedrow, 4);
                View.remplaireChamps_ho(id_employe, OldstartDate, OldendDate, type);
                test = true;
            }catch(Exception e){
                View.afficherMessageErreur("Erreur lors de la récupération des données");
            }
        }


        private void updateHoliday(){
            if (!test) {
                View.afficherMessageErreur("Veuillez d'abord sélectionner une ligne à modifier.");
                return;
            }
            try {
                nom_employe = View.getNom();
                Date startDate_holiday = Date.valueOf(View.getStartDate());
                Date endDate_holiday = Date.valueOf(View.getEndDate());
                type = View.getType_holiday();
                id_employe = View.getId_employe();

                int olddaysbetween =  (int) ( (Date.valueOf(OldendDate).toLocalDate().toEpochDay() - Date.valueOf(OldstartDate).toLocalDate().toEpochDay()));


                for (Employe employe : new EmployeModel(new EmployeDAOimpl()).displayEmploye()) {
                    if (employe.getId() == id_employe) {
                        targetEmploye = employe;
                        break;
                    }
                }

                boolean updateSuccessful = model_holiday.updateHoliday(id, id_employe, startDate_holiday, endDate_holiday, type , targetEmploye , olddaysbetween);

                if (updateSuccessful) {
                    test = false;
                    View.afficherMessageSucces("Holiday a été modifié avec succès.");
                    displayHoliday();
                    View.viderChamps_ho();
                } else {
                    View.afficherMessageErreur("Erreur lors de la mise à jour de holiday.");
                }
            } catch (Exception e) {

                View.afficherMessageErreur("Erreur lors de la mise à jour");
            }
        }
    }

