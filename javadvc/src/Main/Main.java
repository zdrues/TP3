package Main;

import Controller.HolidayController;
import Controller.StaffController;
import View.HolidayView;
import View.StaffView;
import Model.Holiday;
import Model.Staff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize controllers
        HolidayController holidayController = new HolidayController();
        StaffController staffController = new StaffController();

        // Initialize views
        HolidayView holidayView = new HolidayView();
        StaffView staffView = new StaffView();

        // Set up action listeners for holiday view buttons
        holidayView.setAddHolidayAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to add holiday
                String holidayType = holidayView.getHolidayType();
                String startDate = holidayView.getStartDate();
                String endDate = holidayView.getEndDate();
                String staffId = holidayView.getId();

                // Generate or retrieve the next holidayId (implement this method as needed)
                int holidayId = generateNextHolidayId(); // Placeholder for holiday ID generation

                // Convert holidayType from String to HolidayType
                Holiday.HolidayType holidayTypeEnum = Holiday.HolidayType.valueOf(holidayType.toUpperCase());

                // Create Holiday object
                Holiday holiday = new Holiday(holidayId, Integer.parseInt(staffId), holidayTypeEnum,
                        LocalDate.parse(startDate), LocalDate.parse(endDate));
                if (holidayController.addHoliday(holiday)) {
                    holidayView.showSuccessMessage("Holiday added successfully!");
                    updateHolidayTable(holidayView, holidayController);
                } else {
                    holidayView.showErrorMessage("Failed to add holiday.");
                }
            }
        });

        holidayView.setUpdateHolidayAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to update holiday
                int selectedRow = holidayView.getSelectedRow();
                if (selectedRow != -1) {
                    int holidayId = holidayView.getSelectedHolidayId();
                    String holidayType = holidayView.getHolidayType();
                    String startDate = holidayView.getStartDate();
                    String endDate = holidayView.getEndDate();
                    String staffId = holidayView.getId();

                    // Create updated Holiday object
                    Holiday updatedHoliday = new Holiday(holidayId, Integer.parseInt(staffId),
                            Holiday.HolidayType.valueOf(holidayType.toUpperCase()),
                            LocalDate.parse(startDate), LocalDate.parse(endDate));
                    if (holidayController.updateHoliday(holidayId, updatedHoliday)) {
                        holidayView.showSuccessMessage("Holiday updated successfully!");
                        updateHolidayTable(holidayView, holidayController);
                    } else {
                        holidayView.showErrorMessage("Failed to update holiday.");
                    }
                } else {
                    holidayView.showErrorMessage("Please select a holiday to update.");
                }
            }
        });

        holidayView.setDeleteHolidayAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to delete holiday
                int selectedRow = holidayView.getSelectedRow();
                if (selectedRow != -1) {
                    int holidayId = holidayView.getSelectedHolidayId();
                    if (holidayController.deleteHoliday(holidayId)) {
                        holidayView.showSuccessMessage("Holiday deleted successfully!");
                        updateHolidayTable(holidayView, holidayController);
                    } else {
                        holidayView.showErrorMessage("Failed to delete holiday.");
                    }
                } else {
                    holidayView.showErrorMessage("Please select a holiday to delete.");
                }
            }
        });

        // Set up action listeners for staff view buttons
        staffView.setAddStaffAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to add staff
                String name = staffView.getNom();
                String prenom = staffView.getPrenom();
                String email = staffView.getEmail();
                String phone = staffView.getPhone();
                double salaire = staffView.getSalaire();
                Staff.Poste poste = staffView.getPoste();
                Staff.Role role = staffView.getRole();

                // Create a new Staff object
                Staff staff = new Staff(name, prenom, email, phone, salaire, role, poste);
                if (staffController.addStaff(staff)) {
                    staffView.showSuccessMessage("Staff member added successfully!");
                    updateStaffList(staffView, staffController);
                } else {
                    staffView.showErrorMessage("Failed to add staff member.");
                }
            }
        });

        staffView.setUpdateStaffAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to update staff
                int selectedRow = staffView.getSelectedRow();
                if (selectedRow != -1) {
                    int staffId = staffView.getSelectedStaffId();
                    String name = staffView.getNom();
                    String prenom = staffView.getPrenom();
                    String email = staffView.getEmail();
                    String phone = staffView.getPhone();
                    double salaire = staffView.getSalaire();
                    Staff.Poste poste = staffView.getPoste();
                    Staff.Role role = staffView.getRole();

                    // Create updated Staff object
                    Staff updatedStaff = new Staff(name, prenom, email, phone, salaire, role, poste);
                    if (staffController.updateStaff(staffId, updatedStaff)) {
                        staffView.showSuccessMessage("Staff member updated successfully!");
                        updateStaffList(staffView, staffController);
                    } else {
                        staffView.showErrorMessage("Failed to update staff member.");
                    }
                } else {
                    staffView.showErrorMessage("Please select a staff member to update.");
                }
            }
        });

        staffView.setDeleteStaffAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to delete staff
                int selectedRow = staffView.getSelectedRow();
                if (selectedRow != -1) {
                    int staffId = staffView.getSelectedStaffId();
                    if (staffController.deleteStaff(staffId)) {
                        staffView.showSuccessMessage("Staff member deleted successfully!");
                        updateStaffList(staffView, staffController);
                    } else {
                        staffView.showErrorMessage("Failed to delete staff member.");
                    }
                } else {
                    staffView.showErrorMessage("Please select a staff member to delete.");
                }
            }
        });

        // Set up action listeners for import/export buttons
        staffView.setImportStaffAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffController.handleImport(staffView);
            }
        });

        staffView.setExportStaffAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffController.handleExport(staffView);
            }
        });

        // Load initial data
        updateHolidayTable(holidayView, holidayController);
        updateStaffList(staffView, staffController);

        // Display the views
        holidayView.setVisible(true);
        staffView.setVisible(true);
    }

    private static void updateHolidayTable(HolidayView holidayView, HolidayController holidayController) {
        List<Holiday> holidays = holidayController.getAllHolidays();
        holidayView.updateHolidayTable(holidays);
    }

    private static void updateStaffList(StaffView staffView, StaffController staffController) {
        List<Staff> staffList = staffController.getAllStaff();
        staffView.updateList(staffList);
    }

    private static int generateNextHolidayId() {
        // Implement logic to generate the next holiday ID
        return 1; // Placeholder
    }
}