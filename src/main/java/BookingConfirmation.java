// ========================================
// BookingConfirmation.java - Modelo de Reserva
// ========================================

import java.util.ArrayList;
import java.util.List;

public class BookingConfirmation {
    private String bookingId;
    private String clientBookingNumber;    // Número de reserva para el cliente
    private String providerBookingNumber;  // Número de reserva con el proveedor
    private String clientName;
    private String clientEmail;
    private String advisorId;
    private String bookingType;  // "HOTEL_FLIGHT", "HOTEL_FLIGHT_TOUR"
    private String createdAt;
    private String status;  // "CONFIRMED", "PENDING", "CANCELLED"
    private HotelDetails hotel;
    private FlightDetails flight;
    private List<String> tourIds;
    private double totalCost;
    private String paymentStatus;  // "PAID", "PENDING", "REFUNDED"
    private String notes;

    public BookingConfirmation() {
        this.bookingId = "BK_" + System.currentTimeMillis();
        this.clientBookingNumber = "CLI-" + System.currentTimeMillis();
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.status = "CONFIRMED";
        this.tourIds = new ArrayList<>();
        this.paymentStatus = "PENDING";
    }

    // Clase interna: HotelDetails
    public static class HotelDetails {
        private String hotelName;
        private String hotelAddress;
        private String checkInDate;
        private String checkOutDate;
        private String roomType;
        private int numberOfRooms;
        private String confirmationNumber;

        // Getters y Setters
        public String getHotelName() { return hotelName; }
        public void setHotelName(String hotelName) { this.hotelName = hotelName; }

        public String getHotelAddress() { return hotelAddress; }
        public void setHotelAddress(String hotelAddress) { this.hotelAddress = hotelAddress; }

        public String getCheckInDate() { return checkInDate; }
        public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

        public String getCheckOutDate() { return checkOutDate; }
        public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

        public String getRoomType() { return roomType; }
        public void setRoomType(String roomType) { this.roomType = roomType; }

        public int getNumberOfRooms() { return numberOfRooms; }
        public void setNumberOfRooms(int numberOfRooms) { this.numberOfRooms = numberOfRooms; }

        public String getConfirmationNumber() { return confirmationNumber; }
        public void setConfirmationNumber(String confirmationNumber) { 
            this.confirmationNumber = confirmationNumber; 
        }
    }

    // Clase interna: FlightDetails
    public static class FlightDetails {
        private String airline;
        private String flightNumber;
        private String departureAirport;
        private String arrivalAirport;
        private String departureDate;
        private String departureTime;
        private String arrivalDate;
        private String arrivalTime;
        private String confirmationNumber;

        // Getters y Setters
        public String getAirline() { return airline; }
        public void setAirline(String airline) { this.airline = airline; }

        public String getFlightNumber() { return flightNumber; }
        public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

        public String getDepartureAirport() { return departureAirport; }
        public void setDepartureAirport(String departureAirport) { 
            this.departureAirport = departureAirport; 
        }

        public String getArrivalAirport() { return arrivalAirport; }
        public void setArrivalAirport(String arrivalAirport) { 
            this.arrivalAirport = arrivalAirport; 
        }

        public String getDepartureDate() { return departureDate; }
        public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }

        public String getDepartureTime() { return departureTime; }
        public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

        public String getArrivalDate() { return arrivalDate; }
        public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }

        public String getArrivalTime() { return arrivalTime; }
        public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

        public String getConfirmationNumber() { return confirmationNumber; }
        public void setConfirmationNumber(String confirmationNumber) { 
            this.confirmationNumber = confirmationNumber; 
        }
    }

    // Getters y Setters principales
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getClientBookingNumber() { return clientBookingNumber; }
    public void setClientBookingNumber(String clientBookingNumber) { 
        this.clientBookingNumber = clientBookingNumber; 
    }

    public String getProviderBookingNumber() { return providerBookingNumber; }
    public void setProviderBookingNumber(String providerBookingNumber) { 
        this.providerBookingNumber = providerBookingNumber; 
    }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public String getAdvisorId() { return advisorId; }
    public void setAdvisorId(String advisorId) { this.advisorId = advisorId; }

    public String getBookingType() { return bookingType; }
    public void setBookingType(String bookingType) { this.bookingType = bookingType; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public HotelDetails getHotel() { return hotel; }
    public void setHotel(HotelDetails hotel) { this.hotel = hotel; }

    public FlightDetails getFlight() { return flight; }
    public void setFlight(FlightDetails flight) { this.flight = flight; }

    public List<String> getTourIds() { return tourIds; }
    public void setTourIds(List<String> tourIds) { this.tourIds = tourIds; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    } 