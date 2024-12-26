package Model;
import Controller.EmployeController;
import java.util.List;

import DAO.HolidayDAOimpl;
import java.sql.Date;

public class HolidayModel {

        private HolidayDAOimpl dao;

        public HolidayModel(HolidayDAOimpl dao) {
            this.dao = dao;
        }

        public boolean addHoliday(int id, int id_employe, Date startdate, Date enddate, Type_holiday type , Employe targetEmploye) {

            if(startdate.after(enddate)) return false;
            if(startdate.equals(enddate)) return false;
            if(startdate.before(new Date(System.currentTimeMillis()))) return false;
            if(enddate.before(new Date(System.currentTimeMillis()))) return false;

            long daysBetween = (enddate.toLocalDate().toEpochDay() - startdate.toLocalDate().toEpochDay());
            if(daysBetween > targetEmploye.getSolde()) return false;
            EmployeController.updateSolde(targetEmploye.getId(), targetEmploye.getSolde() - (int) daysBetween);
            Holiday e = new Holiday(id, id_employe, startdate, enddate, type);
            dao.add(e);
            return true;
        }


        public List<Holiday> displayHoliday() {
            List<Holiday> Holidays = dao.display();
            return Holidays;
        }

        public boolean deleteHoliday(int id) {
            dao.delete(id);
            return true;
        }

        public boolean updateHoliday(int id, int id_employe, Date startdate, Date enddate, Type_holiday type , Employe targetEmploye , int olddaysbetween ) {

            long daysBetween = (enddate.toLocalDate().toEpochDay() - startdate.toLocalDate().toEpochDay());

            if(startdate.after(enddate)) return false;
            if(startdate.equals(enddate)) return false;
            if(startdate.before(new Date(System.currentTimeMillis()))) return false;
            if(enddate.before(new Date(System.currentTimeMillis()))) return false;

            if(daysBetween > (targetEmploye.getSolde()+olddaysbetween)) return false;
            EmployeController.updateSolde(targetEmploye.getId(), (targetEmploye.getSolde()+olddaysbetween) - (int) daysBetween);

            Holiday e = new Holiday(id, id_employe, startdate, enddate, type);
            dao.update(e);
            return true;
        }

    }

