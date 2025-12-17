/ ============================================
// ARCHIVO 1: src/main/java/models/Tour.java
// ============================================
package models;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class Tour {
    private ObjectId id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String location;
    private String category;
    private int maxCapacity;
    private int availableSpots;
    private List<String> included;
    private List<String> notIncluded;
    private List<String> requirements;
    private String schedule;
    private boolean active;
    private String createdBy;
    private long createdAt;
    
    public Tour() {
        this.included = new ArrayList<>();
        this.notIncluded = new ArrayList<>();
        this.requirements = new ArrayList<>();
        this.active = true;
        this.createdAt = System.currentTimeMillis();
    }
    
    public Tour(String name, String description, double price, int duration, 
                String location, String category, int maxCapacity) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.location = location;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.availableSpots = maxCapacity;
    }
    
    public Tour(Document doc) {
        this.id = doc.getObjectId("_id");
        this.name = doc.getString("name");
        this.description = doc.getString("description");
        this.price = doc.getDouble("price");
        this.duration = doc.getInteger("duration");
        this.location = doc.getString("location");
        this.category = doc.getString("category");
        this.maxCapacity = doc.getInteger("maxCapacity");
        this.availableSpots = doc.getInteger("availableSpots");
        this.included = doc.getList("included", String.class, new ArrayList<>());
        this.notIncluded = doc.getList("notIncluded", String.class, new ArrayList<>());
        this.requirements = doc.getList("requirements", String.class, new ArrayList<>());
        this.schedule = doc.getString("schedule");
        this.active = doc.getBoolean("active", true);
        this.createdBy = doc.getString("createdBy");
        this.createdAt = doc.getLong("createdAt");
    }
    
    public Document toDocument() {
        Document doc = new Document();
        if (id != null) doc.append("_id", id);
        doc.append("name", name)
           .append("description", description)
           .append("price", price)
           .append("duration", duration)
           .append("location", location)
           .append("category", category)
           .append("maxCapacity", maxCapacity)
           .append("availableSpots", availableSpots)
           .append("included", included)
           .append("notIncluded", notIncluded)
           .append("requirements", requirements)
           .append("schedule", schedule)
           .append("active", active)
           .append("createdBy", createdBy)
           .append("createdAt", createdAt);
        return doc;
    }
    
    public boolean reserveSpots(int spots) {
        if (spots <= availableSpots && spots > 0) {
            availableSpots -= spots;
            return true;
        }
        return false;
    }
    
    public void releaseSpots(int spots) {
        availableSpots = Math.min(availableSpots + spots, maxCapacity);
    }
    
    public boolean isAvailable(int spots) {
        return active && availableSpots >= spots;
    }
    
    // Getters y Setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    
    public int getAvailableSpots() { return availableSpots; }
    public void setAvailableSpots(int availableSpots) { this.availableSpots = availableSpots; }
    
    public List<String> getIncluded() { return included; }
    public void setIncluded(List<String> included) { this.included = included; }
    
    public List<String> getNotIncluded() { return notIncluded; }
    public void setNotIncluded(List<String> notIncluded) { this.notIncluded = notIncluded; }
    
    public List<String> getRequirements() { return requirements; }
    public void setRequirements(List<String> requirements) { this.requirements = requirements; }
    
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
    
    @Override
    public String toString() {
        return String.format("Tour{id=%s, name='%s', price=%.2f, location='%s', available=%d/%d}",
            getIdAsString(), name, price, location, availableSpots, maxCapacity);
    }
}