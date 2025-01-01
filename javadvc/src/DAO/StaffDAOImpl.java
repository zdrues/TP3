package DAO;

import Model.Staff;
import Model.Staff.Role;
import Model.Staff.Poste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements GenericDAOI<Staff> {

    @Override
    public void add(Staff staff) {
        String sql = "INSERT INTO Staff (nom, prenom, email, phone, salaire, role, poste) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getNom());
            stmt.setString(2, staff.getPrenom());
            stmt.setString(3, staff.getEmail());
            stmt.setString(4, staff.getPhone());
            stmt.setDouble(5, staff.getSalaire());
            stmt.setString(6, staff.getRole().name());
            stmt.setString(7, staff.getPoste().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Staff staff) {
        String sql = "UPDATE Staff SET nom = ?, prenom = ?, email = ?, phone = ?, salaire = ?, role = ?, poste = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getNom());
            stmt.setString(2, staff.getPrenom());
            stmt.setString(3, staff.getEmail());
            stmt.setString(4, staff.getPhone());
            stmt.setDouble(5, staff.getSalaire());
            stmt.setString(6, staff.getRole().name());
            stmt.setString(7, staff.getPoste().name());
            stmt.setInt(8, staff.getId());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("L'employé a été mis à jour avec succès.");
            } else {
                System.out.println("Aucun employé trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Staff WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Staff> getAll() {
        List<Staff> staffs = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Staff staff = mapRowToStaff(rs);
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

    /**
     * Utility method to map a ResultSet row to a Staff object.
     */
    private Staff mapRowToStaff(ResultSet rs) throws SQLException {
        String roleStr = rs.getString("role").toUpperCase();
        String posteStr = rs.getString("poste").toUpperCase();

        Role role = Role.valueOf(roleStr);
        Poste poste = Poste.valueOf(posteStr);

        Staff staff = new Staff(
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getDouble("salaire"),
                role,
                poste);
        staff.setId(rs.getInt("id"));
        return staff;
    }

    /**
     * Additional method to find a staff member by ID.
     */
    public Staff findById(int id) {
        String sql = "SELECT * FROM Staff WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToStaff(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}