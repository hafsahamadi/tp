package Main;
import Controller.*;
import DAO.*;
import Model.*;
import View.*;


public class Main {

        public static void main(String[] args) {
            Employe_HolidayView view = new Employe_HolidayView();
            EmployeDAOimpl dao = new EmployeDAOimpl();
            HolidayDAOimpl dao_holiday = new HolidayDAOimpl();
            EmployeModel model_employe = new EmployeModel(dao);
            HolidayModel model_holiday = new HolidayModel(dao_holiday);
            new EmployeController(view, model_employe);
            new HolidayController(view, model_holiday);
        }
    }

