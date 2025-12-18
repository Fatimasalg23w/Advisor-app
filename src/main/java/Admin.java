// ========================================
// Admin.java - Modelo de Administrador
// ========================================

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private String adminId;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String role;  // "SUPER_ADMIN", "ADMIN"
    private String createdAt;
    private String lastLogin;
    private boolean isActive;
    private List<String> permissions;  // "CREATE_TOURS", "MANAGE_ADVISORS", etc.

    public Admin() {
        this.adminId = "ADMIN_" + System.currentTimeMillis();
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.isActive = true;
        this.role = "ADMIN";
        this.permissions = new ArrayList<>();
        initializePermissions();
    }

    private void initializePermissions() {
        permissions.add("CREATE_TOURS");
        permissions.add("EDIT_TOURS");
        permissions.add("DELETE_TOURS");
        permissions.add("MANAGE_ADVISORS");
        permissions.add("VIEW_ALL_BOOKINGS");
        permissions.add("MANAGE_USERS");
    }

    // Getters y Setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getLastLogin() { return lastLogin; }
    public void setLastLogin(String lastLogin) { this.lastLogin = lastLogin; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public List<String> getPermissions() { return permissions; }
    public void setPermissions(List<String> permissions) { this.permissions = permissions; }
}