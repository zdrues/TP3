package DAO;

import Model.Holiday;
import java.util.ArrayList;
import java.util.List;

public class HolidayDAOImpl {
    private List<Holiday> holidays;

    public HolidayDAOImpl() {
        holidays = new ArrayList<>();
    }

    public List<Holiday> getAll() {
        return new ArrayList<>(holidays); // Return a copy of the list to prevent external modification
    }

    public boolean add(Holiday holiday) {
        return holidays.add(holiday); // Add the holiday to the list
    }

    public boolean update(Holiday updatedHoliday) {
        for (int i = 0; i < holidays.size(); i++) {
            if (holidays.get(i).getHolidayId() == updatedHoliday.getHolidayId()) {
                holidays.set(i, updatedHoliday); // Update the holiday in the list
                return true; // Return true if the update was successful
            }
        }
        return false; // Return false if the holiday was not found
    }

    public Holiday findById(int holidayId) {
        for (Holiday holiday : holidays) {
            if (holiday.getHolidayId() == holidayId) {
                return holiday; // Return the holiday if found
            }
        }
        return null; // Return null if not found
    }

    public boolean delete(int holidayId) {
        return holidays.removeIf(holiday -> holiday.getHolidayId() == holidayId); // Remove the holiday if found
    }
}