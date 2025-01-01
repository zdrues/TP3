package View;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.Holiday;

import java.awt.*;
import java.awt.event.ActionListener;

public class HolidayView extends JFrame {
    private JTextField txtHolidayType, txtStartDate, txtEndDate, txtId;
    private JTable holidayTable;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
    private DefaultTableModel tableModel;

    public HolidayView() {
        setTitle("Holiday Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.add(new JLabel("Holiday Type:"));
        txtHolidayType = new JTextField();
        inputPanel.add(txtHolidayType);

        inputPanel.add(new JLabel("Start Date:"));
        txtStartDate = new JTextField();
        inputPanel.add(txtStartDate);

        inputPanel.add(new JLabel("End Date:"));
        txtEndDate = new JTextField();
        inputPanel.add(txtEndDate);

        inputPanel.add(new JLabel("Staff ID:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        add(inputPanel, BorderLayout.NORTH);

        // Table Panel
        String[] columns = { "ID", "Holiday Type", "Start Date", "End Date", "Staff ID" };
        tableModel = new DefaultTableModel(columns, 0);
        holidayTable = new JTable(tableModel);
        add(new JScrollPane(holidayTable), BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setAddHolidayAction(ActionListener action) {
        btnAdd.addActionListener(action);
    }

    public void setUpdateHolidayAction(ActionListener action) {
        btnUpdate.addActionListener(action);
    }

    public void setDeleteHolidayAction(ActionListener action) {
        btnDelete.addActionListener(action);
    }

    public void setSearchHolidayAction(ActionListener action) {
        btnSearch.addActionListener(action);
    }

    public String getHolidayType() {
        return txtHolidayType.getText();
    }

    public String getStartDate() {
        return txtStartDate.getText();
    }

    public String getEndDate() {
        return txtEndDate.getText();
    }

    public String getId() {
        return txtId.getText();
    }

    public int getSelectedRow() {
        return holidayTable.getSelectedRow();
    }

    public int getSelectedHolidayId() {
        return (int) holidayTable.getValueAt(getSelectedRow(), 0);
    }

    public void updateHolidayTable(List<Holiday> holidays) {
        tableModel.setRowCount(0); // Clear the table
        for (Holiday holiday : holidays) {
            tableModel.addRow(new Object[] {
                    holiday.getHolidayId(), // Assuming you have a getHolidayId() method
                    holiday.getHolidayType(), // Assuming you have a getHolidayType() method
                    holiday.getStartDate(), // Assuming you have a getStartDate() method
                    holiday.getEndDate(), // Assuming you have a getEndDate() method
                    holiday.getStaffId() // Assuming you have a getStaffId() method
            });
        }
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}