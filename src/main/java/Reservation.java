import java.time.LocalDate;

public class Reservation {
    private String id;
    private String clientId;
    private String packageName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private double totalCost;

    public Reservation(String id, String clientId, String packageName, LocalDate startDate, LocalDate endDate, String status, double totalCost) {
        this.id = id;
        this.clientId = clientId;
        this.packageName = packageName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.totalCost = totalCost;
    }

    public String getId() { return id; }
    public String getClientId() { return clientId; }
    public String getPackageName() { return packageName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }
    public double getTotalCost() { return totalCost; }
    public double getTotalPrice() { return totalCost; }
    public void setStatus(String status) { this.status = status; }
}