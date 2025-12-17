
// ============================================
// ARCHIVO 2: src/main/java/models/User.java
// ============================================
package models;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class User {
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String preferences;
    private List<String> bookingHistory;
    private boolean active;
    private long createdAt;
    private long lastLogin;
    
    public User() {
        this.bookingHistory = new ArrayList<>();
        this.active = true;
        this.createdAt = System.currentTimeMillis();
    }
    
    public User(String username, String password, String email, String fullName) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
    
    public User(Document doc) {
        this.id = doc.getObjectId("_id");
        this.username = doc.getString("username");
        this.password = doc.getString("password");
        this.email = doc.getString("email");
        this.fullName = doc.getString("fullName");
        this.phone = doc.getString("phone");
        this.address = doc.getString("address");
        this.preferences = doc.getString("preferences");
        this.bookingHistory = doc.getList("bookingHistory", String.class, new ArrayList<>());
        this.active = doc.getBoolean("active", true);
        this.createdAt = doc.getLong("createdAt");
        this.lastLogin = doc.getLong("lastLogin");
    }
    
    public Document toDocument() {
        Document doc = new Document();
        if (id != null) doc.append("_id", id);
        doc.append("username", username)
           .append("password", password)
           .append("email", email)
           .append("fullName", fullName)
           .append("phone", phone)
           .append("address", address)
           .append("preferences", preferences)
           .append("bookingHistory", bookingHistory)
           .append("active", active)
           .append("createdAt", createdAt)
           .append("lastLogin", lastLogin);
        return doc;
    }
    
    public void addBooking(String bookingId) {
        if (!bookingHistory.contains(bookingId)) {
            bookingHistory.add(bookingId);
        }
    }
    
    public void updateLastLogin() {
        this.lastLogin = System.currentTimeMillis();
    }
    
    // Getters y Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
    
    public List<String> getBookingHistory() { return bookingHistory; }
    public void setBookingHistory(List<String> bookingHistory) { this.bookingHistory = bookingHistory; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    
    public long getLastLogin() { return lastLogin; }
    public void setLastLogin(long lastLogin) { this.lastLogin = lastLogin; }
    
    @Override
    public String toString() {
        return String.format("User{id=%s, username='%s', fullName='%s', email='%s'}",
            getIdAsString(), username, fullName, email);
    }
}