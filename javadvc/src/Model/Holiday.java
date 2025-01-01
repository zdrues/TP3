package Model;

import java.time.LocalDate;

public class Holiday {
    private int holidayId;
    private int staffId;
    private HolidayType holidayType;
    private LocalDate startDate;
    private LocalDate endDate;

    public enum HolidayType {
        PAID_LEAVE,
        UNPAID_LEAVE,
        SICK_LEAVE
    }

    public Holiday(int holidayId, int staffId, HolidayType holidayType, LocalDate startDate, LocalDate endDate) {
        this.holidayId = holidayId;
        this.staffId = staffId;
        this.holidayType = holidayType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}