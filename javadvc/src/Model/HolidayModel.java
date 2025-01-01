package Model;

import DAO.HolidayDAOImpl;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HolidayModel {
    private HolidayDAOImpl dao;
    private static final int MAX_HOLIDAY_BALANCE = 25;

    public HolidayModel(HolidayDAOImpl dao) {
        this.dao = dao;
    }

    public boolean addHoliday(Holiday holiday, int remainingBalance) {
        long duration = ChronoUnit.DAYS.between(holiday.getStartDate(), holiday.getEndDate()) + 1;

        if (remainingBalance >= duration) {
            dao.add(holiday);
            return true;
        } else {
            System.out.println("Solde insuffisant. Demande de congé refusée.");
            return false;
        }
    }

    public void updateHoliday(Holiday holiday) {
        dao.update(holiday);
    }

    public Holiday findHolidayById(int holidayId) {
        return dao.findById(holidayId);
    }

    public List<Holiday> getAllHolidays() {
        return dao.getAll();
    }

    public void deleteHoliday(int holidayId) {
        dao.delete(holidayId);
    }

    public int calculateRemainingBalance(List<Holiday> holidays) {
        int usedDays = 0;
        for (Holiday holiday : holidays) {
            long duration = ChronoUnit.DAYS.between(holiday.getStartDate(), holiday.getEndDate()) + 1;
            usedDays += duration;
        }
        return MAX_HOLIDAY_BALANCE - usedDays;
    }
}