// Advisor.java - Modelo de Asesor
// ========================================

import java.util.ArrayList;
import java.util.List;

public class Advisor {
    private String advisorId;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String specialization;  // "Weddings", "Honeymoons", "Business", etc.
    private String createdAt;
    private String lastLogin;
    private boolean isActive;
    private boolean canAddTours;  // Permiso del admin
    private List<VideoCall> videoCalls;
    private List<Quotation> quotations;
    private AdvisorStats stats;

    public Advisor() {
        this.advisorId = "ADV_" + System.currentTimeMillis();
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.isActive = true;
        this.canAddTours = false;
        this.videoCalls = new ArrayList<>();
        this.quotations = new ArrayList<>();
        this.stats = new AdvisorStats();
    }

    // Clase interna: VideoCall
    public static class VideoCall {
        private String callId;
        private String clientName;
        private String clientEmail;
        private String scheduledDate;
        private String scheduledTime;
        private String duration;  // "30 min", "1 hour"
        private String status;  // "SCHEDULED", "COMPLETED", "POSTPONED", "NO_ANSWER"
        private String meetingLink;
        private String notes;
        private String completedAt;

        public VideoCall() {
            this.callId = "CALL_" + System.currentTimeMillis();
            this.status = "SCHEDULED";
        }

        // Getters y Setters
        public String getCallId() { return callId; }
        public void setCallId(String callId) { this.callId = callId; }

        public String getClientName() { return clientName; }
        public void setClientName(String clientName) { this.clientName = clientName; }

        public String getClientEmail() { return clientEmail; }
        public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

        public String getScheduledDate() { return scheduledDate; }
        public void setScheduledDate(String scheduledDate) { this.scheduledDate = scheduledDate; }

        public String getScheduledTime() { return scheduledTime; }
        public void setScheduledTime(String scheduledTime) { this.scheduledTime = scheduledTime; }

        public String getDuration() { return duration; }
        public void setDuration(String duration) { this.duration = duration; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getMeetingLink() { return meetingLink; }
        public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }

        public String getCompletedAt() { return completedAt; }
        public void setCompletedAt(String completedAt) { this.completedAt = completedAt; }
    }

    // Clase interna: Quotation (Cotización)
    public static class Quotation {
        private String quotationId;
        private String clientName;
        private String clientEmail;
        private String clientPhone;
        private String requestType;  // "WEDDING", "PROPOSAL", "HONEYMOON", etc.
        private String requestDate;
        private String status;  // "PENDING", "IN_PROGRESS", "SENT", "ACCEPTED", "REJECTED"
        private QuotationDetails details;
        private double estimatedCost;
        private String notes;
        private String createdAt;

        public Quotation() {
            this.quotationId = "QUOT_" + System.currentTimeMillis();
            this.createdAt = java.time.LocalDateTime.now().toString();
            this.status = "PENDING";
            this.details = new QuotationDetails();
        }

        public static class QuotationDetails {
            private String destination;
            private String startDate;
            private String endDate;
            private int numberOfPeople;
            private String accommodationType;  // "HOTEL", "RESORT", "AIRBNB"
            private boolean needsFlight;
            private boolean needsTours;
            private List<String> specialRequests;

            public QuotationDetails() {
                this.specialRequests = new ArrayList<>();
            }

            // Getters y Setters
            public String getDestination() { return destination; }
            public void setDestination(String destination) { this.destination = destination; }

            public String getStartDate() { return startDate; }
            public void setStartDate(String startDate) { this.startDate = startDate; }

            public String getEndDate() { return endDate; }
            public void setEndDate(String endDate) { this.endDate = endDate; }

            public int getNumberOfPeople() { return numberOfPeople; }
            public void setNumberOfPeople(int numberOfPeople) { this.numberOfPeople = numberOfPeople; }

            public String getAccommodationType() { return accommodationType; }
            public void setAccommodationType(String accommodationType) { 
                this.accommodationType = accommodationType; 
            }

            public boolean isNeedsFlight() { return needsFlight; }
            public void setNeedsFlight(boolean needsFlight) { this.needsFlight = needsFlight; }

            public boolean isNeedsTours() { return needsTours; }
            public void setNeedsTours(boolean needsTours) { this.needsTours = needsTours; }

            public List<String> getSpecialRequests() { return specialRequests; }
            public void setSpecialRequests(List<String> specialRequests) { 
                this.specialRequests = specialRequests; 
            }
        }

        // Getters y Setters
        public String getQuotationId() { return quotationId; }
        public void setQuotationId(String quotationId) { this.quotationId = quotationId; }

        public String getClientName() { return clientName; }
        public void setClientName(String clientName) { this.clientName = clientName; }

        public String getClientEmail() { return clientEmail; }
        public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

        public String getClientPhone() { return clientPhone; }
        public void setClientPhone(String clientPhone) { this.clientPhone = clientPhone; }

        public String getRequestType() { return requestType; }
        public void setRequestType(String requestType) { this.requestType = requestType; }

        public String getRequestDate() { return requestDate; }
        public void setRequestDate(String requestDate) { this.requestDate = requestDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public QuotationDetails getDetails() { return details; }
        public void setDetails(QuotationDetails details) { this.details = details; }

        public double getEstimatedCost() { return estimatedCost; }
        public void setEstimatedCost(double estimatedCost) { this.estimatedCost = estimatedCost; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    }

    // Clase interna: AdvisorStats
    public static class AdvisorStats {
        private int totalVideoCalls;
        private int completedVideoCalls;
        private int totalQuotations;
        private int acceptedQuotations;
        private int totalBookings;
        private double totalRevenue;

        // Getters y Setters
        public int getTotalVideoCalls() { return totalVideoCalls; }
        public void setTotalVideoCalls(int totalVideoCalls) { 
            this.totalVideoCalls = totalVideoCalls; 
        }

        public int getCompletedVideoCalls() { return completedVideoCalls; }
        public void setCompletedVideoCalls(int completedVideoCalls) { 
            this.completedVideoCalls = completedVideoCalls; 
        }

        public int getTotalQuotations() { return totalQuotations; }
        public void setTotalQuotations(int totalQuotations) { 
            this.totalQuotations = totalQuotations; 
        }

        public int getAcceptedQuotations() { return acceptedQuotations; }
        public void setAcceptedQuotations(int acceptedQuotations) { 
            this.acceptedQuotations = acceptedQuotations; 
        }

        public int getTotalBookings() { return totalBookings; }
        public void setTotalBookings(int totalBookings) { this.totalBookings = totalBookings; }

        public double getTotalRevenue() { return totalRevenue; }
        public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
    }

    // Getters y Setters principales
    public String getAdvisorId() { return advisorId; }
    public void setAdvisorId(String advisorId) { this.advisorId = advisorId; }

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

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getLastLogin() { return lastLogin; }
    public void setLastLogin(String lastLogin) { this.lastLogin = lastLogin; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public boolean isCanAddTours() { return canAddTours; }
    public void setCanAddTours(boolean canAddTours) { this.canAddTours = canAddTours; }

    public List<VideoCall> getVideoCalls() { return videoCalls; }
    public void setVideoCalls(List<VideoCall> videoCalls) { this.videoCalls = videoCalls; }

    public List<Quotation> getQuotations() { return quotations; }
    public void setQuotations(List<Quotation> quotations) { this.quotations = quotations; }

    public AdvisorStats getStats() { return stats; }
    public void setStats(AdvisorStats stats) { this.stats = stats; }
}