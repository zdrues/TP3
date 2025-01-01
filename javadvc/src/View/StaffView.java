package View;

import Model.Staff;
import Model.Staff.Poste;
import Model.Staff.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class StaffView extends JFrame {
    private JTextField txtNom, txtPrenom, txtEmail, txtPhone, txtSalaire;
    private JTable staffTable;
    private JButton btnAdd, btnUpdate, btnDelete, btnExport, btnImport; // Added Import and Export buttons
    private DefaultTableModel tableModel;

    // Declare the combo box fields as instance variables
    private JComboBox<Poste> posteField;
    private JComboBox<Role> roleField;

    public StaffView() {
        setTitle("Staff Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.add(new JLabel("Nom:"));
        txtNom = new JTextField();
        inputPanel.add(txtNom);

        inputPanel.add(new JLabel("Prenom:"));
        txtPrenom = new JTextField();
        inputPanel.add(txtPrenom);

        inputPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);

        inputPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        inputPanel.add(txtPhone);

        inputPanel.add(new JLabel("Salaire:"));
        txtSalaire = new JTextField();
        inputPanel.add(txtSalaire);

        // Initialize the combo boxes for Poste and Role
        posteField = new JComboBox<>(Poste.values());
        roleField = new JComboBox<>(Role.values());

        inputPanel.add(new JLabel("Poste:"));
        inputPanel.add(posteField);
        inputPanel.add(new JLabel("Role:"));
        inputPanel.add(roleField);

        add(inputPanel, BorderLayout.NORTH);

        // Table Panel
        String[] columns = { "ID", "Nom", "Prenom", "Email", "Phone", "Salaire", "Poste", "Role" };
        tableModel = new DefaultTableModel(columns, 0);
        staffTable = new JTable(tableModel);
        add(new JScrollPane(staffTable), BorderLayout.CENTER);

        // Button Panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centered alignment with spacing
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnExport = new JButton("Export"); // Added Export button
        btnImport = new JButton("Import"); // Added Import button

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnExport); // Add Export button to the panel
        buttonPanel.add(btnImport); // Add Import button to the panel

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Existing methods...

    public void setAddStaffAction(ActionListener action) {
        btnAdd.addActionListener(action);
    }

    public void setUpdateStaffAction(ActionListener action) {
        btnUpdate.addActionListener(action);
    }

    public void setDeleteStaffAction(ActionListener action) {
        btnDelete.addActionListener(action);
    }

    // New methods for Import and Export buttons
    public void setExportStaffAction(ActionListener action) {
        btnExport.addActionListener(action);
    }

    public void setImportStaffAction(ActionListener action) {
        btnImport.addActionListener(action);
    }

    public String getNom() {
        return txtNom.getText();
    }

    public String getPrenom() {
        return txtPrenom.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getPhone() {
        return txtPhone.getText();
    }

    public double getSalaire() {
        return Double.parseDouble(txtSalaire.getText());
    }

    public Poste getPoste() {
        return (Poste) posteField.getSelectedItem();
    }

    public Role getRole() {
        return (Role) roleField.getSelectedItem();
    }

    public int getSelectedRow() {
        return staffTable.getSelectedRow();
    }

    public int getSelectedStaffId() {
        return (int) staffTable.getValueAt(getSelectedRow(), 0);
    }

    public void updateList(List<Staff> staffList) {
        tableModel.setRowCount(0); // Clear the table
        for (Staff staff : staffList) {
            tableModel.addRow(new Object[] {
                    staff.getId(),
                    staff.getNom(),
                    staff.getPrenom(),
                    staff.getEmail(),
                    staff.getPhone(),
                    staff.getSalaire(),
                    staff.getPoste(),
                    staff.getRole()
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