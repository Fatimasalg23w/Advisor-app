// ========================================
// User.java - Modelo de Usuario/Cliente
// ========================================

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private List<String> favoriteToursIds;
    private List<String> bookingHistory;
    private String createdAt;
    private String lastLogin;
    private boolean isActive;

    public User() {
        this.userId = "USER_" + System.currentTimeMillis();
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.isActive = true;
        this.favoriteToursIds = new ArrayList<>();
        this.bookingHistory = new ArrayList<>();
    }

    // Getters y Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

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

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<String> getFavoriteToursIds() { return favoriteToursIds; }
    public void setFavoriteToursIds(List<String> favoriteToursIds) { 
        this.favoriteToursIds = favoriteToursIds; 
    }

    public List<String> getBookingHistory() { return bookingHistory; }
    public void setBookingHistory(List<String> bookingHistory) { 
        this.bookingHistory = bookingHistory; 
    }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getLastLogin() { return lastLogin; }
    public void setLastLogin(String lastLogin) { this.lastLogin = lastLogin; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}