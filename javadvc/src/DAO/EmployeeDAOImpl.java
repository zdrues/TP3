package DAO;

import Model.Staff;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements DataImportExport<Staff> {

    @Override
    public void exportData(List<Staff> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Écrire l'en-tête du fichier CSV
            writer.write("ID,Nom,Prenom,Email,Phone,Salaire,Poste,Role\n");

            // Écrire les données de chaque employé
            for (Staff staff : data) {
                writer.write(String.format("%d,%s,%s,%s,%s,%.2f,%s,%s\n",
                        staff.getId(),
                        staff.getNom(),
                        staff.getPrenom(),
                        staff.getEmail(),
                        staff.getPhone(),
                        staff.getSalaire(),
                        staff.getPoste(),
                        staff.getRole()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Staff> importData(String filePath) {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Ignorer l'en-tête
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 8) {
                    Staff staff = new Staff(
                            parts[1], // Nom
                            parts[2], // Prenom
                            parts[3], // Email
                            parts[4], // Phone
                            Double.parseDouble(parts[5]), // Salaire
                            Staff.Role.valueOf(parts[7]), // Role
                            Staff.Poste.valueOf(parts[6]) // Poste
                    );
                    staff.setId(Integer.parseInt(parts[0])); // ID
                    staffList.add(staff);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staffList;
    }
}