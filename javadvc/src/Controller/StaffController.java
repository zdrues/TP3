package Controller;

import DAO.StaffDAO;
import DAO.EmployeeDAOImpl;
import Model.Staff;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;

public class StaffController {
    private StaffDAO staffDAO;
    private EmployeeDAOImpl employeeDAO;

    public StaffController() {
        staffDAO = new StaffDAO();
        employeeDAO = new EmployeeDAOImpl();
    }

    // Gestionnaire d'événements pour l'importation
    public void handleImport(JFrame view) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "csv"));
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                List<Staff> importedStaff = importStaffData(filePath);
                for (Staff staff : importedStaff) {
                    addStaff(staff);
                }
                JOptionPane.showMessageDialog(view, "Importation réussie", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'importation: " + ex.getMessage(), "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Gestionnaire d'événements pour l'exportation
    public void handleExport(JFrame view) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "csv"));
        int returnValue = fileChooser.showSaveDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }
            try {
                List<Staff> staffList = getAllStaff();
                exportStaffData(staffList, filePath);
                JOptionPane.showMessageDialog(view, "Exportation réussie", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'exportation: " + ex.getMessage(), "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Méthodes existantes
    public void exportStaffData(List<Staff> staffList, String filePath) {
        employeeDAO.exportData(staffList, filePath);
    }

    public List<Staff> importStaffData(String filePath) {
        return employeeDAO.importData(filePath);
    }

    public List<Staff> getAllStaff() {
        return staffDAO.getAllStaff();
    }

    public boolean addStaff(Staff staff) {
        return staffDAO.addStaff(staff);
    }

    public boolean deleteStaff(int staffId) {
        return staffDAO.deleteStaff(staffId);
    }

    public boolean updateStaff(int staffId, Staff updatedStaff) {
        return staffDAO.updateStaff(staffId, updatedStaff);
    }
}