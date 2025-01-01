package DAO;

import java.sql.*;
import java.util.*;
import Model.Staff;

public class StaffDAO {
    private Connection connection;

    public StaffDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setNom(rs.getString("nom"));
                staff.setPrenom(rs.getString("prenom"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setSalaire(rs.getDouble("salaire"));
                staff.setRole(Staff.Role.valueOf(rs.getString("role").toUpperCase())); // Ensure this matches the enum
                staff.setPoste(Staff.Poste.valueOf(rs.getString("poste").toUpperCase()));
                // Note: Holidays can be fetched separately if needed
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public boolean addStaff(Staff staff) {
        String query = "INSERT INTO staff (nom, prenom, email, phone, salaire, role, poste) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staff.getNom());
            pstmt.setString(2, staff.getPrenom());
            pstmt.setString(3, staff.getEmail());
            pstmt.setString(4, staff.getPhone());
            pstmt.setDouble(5, staff.getSalaire());
            pstmt.setString(6, staff.getRole().name());
            pstmt.setString(7, staff.getPoste().name());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStaff(int staffId) {
        String query = "DELETE FROM staff WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, staffId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStaff(int staffId, Staff updatedStaff) {
        String query = "UPDATE staff SET nom = ?, prenom = ?, email = ?, phone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, updatedStaff.getNom());
            pstmt.setString(2, updatedStaff.getPrenom());
            pstmt.setString(3, updatedStaff.getEmail());
            pstmt.setString(4, updatedStaff.getPhone());
            pstmt.setDouble(5, updatedStaff.getSalaire());
            pstmt.setString(6, updatedStaff.getRole().name());
            pstmt.setString(7, updatedStaff.getPoste().name());
            pstmt.setInt(8, staffId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Staff findStaffById(int staffId) {
        String query = "SELECT * FROM staff WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, staffId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setNom(rs.getString("nom"));
                staff.setPrenom(rs.getString("prenom"));
                staff.setEmail(rs.getString("email"));
                staff.setPhone(rs.getString("phone"));
                staff.setSalaire(rs.getDouble("salaire"));
                staff.setRole(Staff.Role.valueOf(rs.getString("role")));
                staff.setPoste(Staff.Poste.valueOf(rs.getString("poste")));
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}