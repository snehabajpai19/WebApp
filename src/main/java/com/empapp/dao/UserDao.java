package com.empapp.dao;

import com.empapp.bean.User;
import com.empapp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // Insert new user
	public int insert(User u) {
	    String sql = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, u.getName());
	        ps.setString(2, u.getEmail());
	        ps.setString(3, u.getCountry());
	        return ps.executeUpdate(); // 1 if inserted
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return 0;
	    }
	}


    // Update user
    public int update(User u) {
        String sql = "UPDATE users SET name=?, email=?, country=? WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getCountry());
            ps.setInt(4, u.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // Delete user by id
    public int delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // Get user by id
    public User getById(int id) {
        String sql = "SELECT id, name, email, country FROM users WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("country")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // not found
    }

    // Get all users
    public List<User> getAll() {
        String sql = "SELECT id, name, email, country FROM users";
        List<User> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("country")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
