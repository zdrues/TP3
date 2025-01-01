package Controller;

import java.util.*;
import DAO.HolidayDAO;
import Model.Holiday;

public class HolidayController {
    private HolidayDAO holidayDAO;

    public HolidayController() {
        holidayDAO = new HolidayDAO();
    }

    public List<Holiday> getAllHolidays() {
        return holidayDAO.getAllHolidays();
    }

    public boolean addHoliday(Holiday holiday) {
        return holidayDAO.addHoliday(holiday);
    }

    public boolean deleteHoliday(int holidayId) {
        return holidayDAO.deleteHoliday(holidayId);
    }

    public boolean updateHoliday(int holidayId, Holiday updatedHoliday) {
        return holidayDAO.updateHoliday(holidayId, updatedHoliday);
    }
}