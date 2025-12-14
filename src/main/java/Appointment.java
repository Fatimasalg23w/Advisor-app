import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String id;
    private String clientId;
    private LocalDateTime dateTime;
    private String purpose;
    private String status;
    private String notes;
    private String title;
    private String type;

    public Appointment(String id, String clientId, LocalDateTime dateTime, String purpose, String status) {
        this.id = id;
        this.clientId = clientId;
        this.dateTime = dateTime;
        this.purpose = purpose;
        this.status = status;
        this.notes = "";
        this.title = purpose;
        this.type = "General";
    }

    public String getId() { return id; }
    public String getClientId() { return clientId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getPurpose() { return purpose; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return dateTime.format(formatter);
    }
    
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setTitle(String title) { this.title = title; }
    public void setType(String type) { this.type = type; }
}