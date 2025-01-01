package DAO;

import Model.Holiday;
import java.util.*;

public class HolidayDAO {
    private List<Holiday> holidays;

    public HolidayDAO() {
        holidays = new ArrayList<>();
    }

    public List<Holiday> getAllHolidays() {
        return holidays;
    }

    public boolean addHoliday(Holiday holiday) {
        return holidays.add(holiday);
    }

    public boolean deleteHoliday(int holidayId) {
        return holidays.removeIf(holiday -> holiday.getHolidayId() == holidayId);
    }

    public boolean updateHoliday(int holidayId, Holiday updatedHoliday) {
        for (int i = 0; i < holidays.size(); i++) {
            if (holidays.get(i).getHolidayId() == holidayId) {
                holidays.set(i, updatedHoliday);
                return true;
            }
        }
        return false;
    }
}