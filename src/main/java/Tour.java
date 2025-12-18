
// ========================================
// Tour.java - Modelo Completo de Tour
// ========================================

import java.util.ArrayList;
import java.util.List;

public class Tour {
    private String tourId;
    private String tourName;
    private int year;
    private String month;
    private int arrivalDate;
    private int departureDate;
    private Airport airport;
    private List<Day> days;
    private List<String> compania;    // ["family", "partner", "friends"]
    private List<String> destino;     // ["beach", "nature", "city"]
    private List<String> especial;    // ["honeymoon", "anniversary", "wedding"]
    private List<String> plan;        // ["adventure", "relaxation", "cultural"]
    private String createdBy;
    private String createdAt;

    public Tour() {
        this.tourId = "TOUR_" + System.currentTimeMillis();
        this.days = new ArrayList<>();
        this.compania = new ArrayList<>();
        this.destino = new ArrayList<>();
        this.especial = new ArrayList<>();
        this.plan = new ArrayList<>();
        this.createdAt = java.time.LocalDateTime.now().toString();
    }

    // Clase interna: Airport
    public static class Airport {
        private String name;
        private String code;
        private String transfersIncluded;

        // Getters y Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getTransfersIncluded() { return transfersIncluded; }
        public void setTransfersIncluded(String transfersIncluded) { 
            this.transfersIncluded = transfersIncluded; 
        }
    }

    // Clase interna: Day
    public static class Day {
        private int day;
        private String activity;
        private String link;
        private String pickup;
        private String dropOff;
        private String departures;
        private String totalTime;
        private String startTime;
        private String finishTime;
        private String cancelationPolicy;
        private String mealsIncluded;
        private String provider;
        private Pricing pricing;
        private String description;
        private List<String> pictures;

        public Day() {
            this.pictures = new ArrayList<>();
        }

        // Getters y Setters
        public int getDay() { return day; }
        public void setDay(int day) { this.day = day; }

        public String getActivity() { return activity; }
        public void setActivity(String activity) { this.activity = activity; }

        public String getLink() { return link; }
        public void setLink(String link) { this.link = link; }

        public String getPickup() { return pickup; }
        public void setPickup(String pickup) { this.pickup = pickup; }

        public String getDropOff() { return dropOff; }
        public void setDropOff(String dropOff) { this.dropOff = dropOff; }

        public String getDepartures() { return departures; }
        public void setDepartures(String departures) { this.departures = departures; }

        public String getTotalTime() { return totalTime; }
        public void setTotalTime(String totalTime) { this.totalTime = totalTime; }

        public String getStartTime() { return startTime; }
        public void setStartTime(String startTime) { this.startTime = startTime; }

        public String getFinishTime() { return finishTime; }
        public void setFinishTime(String finishTime) { this.finishTime = finishTime; }

        public String getCancelationPolicy() { return cancelationPolicy; }
        public void setCancelationPolicy(String cancelationPolicy) { 
            this.cancelationPolicy = cancelationPolicy; 
        }

        public String getMealsIncluded() { return mealsIncluded; }
        public void setMealsIncluded(String mealsIncluded) { 
            this.mealsIncluded = mealsIncluded; 
        }

        public String getProvider() { return provider; }
        public void setProvider(String provider) { this.provider = provider; }

        public Pricing getPricing() { return pricing; }
        public void setPricing(Pricing pricing) { this.pricing = pricing; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public List<String> getPictures() { return pictures; }
        public void setPictures(List<String> pictures) { this.pictures = pictures; }
    }

    // Clase interna: Pricing
    public static class Pricing {
        private double adultPriceMXN;
        private double childPriceMXN;

        // Getters y Setters
        public double getAdultPriceMXN() { return adultPriceMXN; }
        public void setAdultPriceMXN(double adultPriceMXN) { 
            this.adultPriceMXN = adultPriceMXN; 
        }

        public double getChildPriceMXN() { return childPriceMXN; }
        public void setChildPriceMXN(double childPriceMXN) { 
            this.childPriceMXN = childPriceMXN; 
        }
    }

    // Getters y Setters principales
    public String getTourId() { return tourId; }
    public void setTourId(String tourId) { this.tourId = tourId; }

    public String getTourName() { return tourName; }
    public void setTourName(String tourName) { this.tourName = tourName; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public int getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(int arrivalDate) { this.arrivalDate = arrivalDate; }

    public int getDepartureDate() { return departureDate; }
    public void setDepartureDate(int departureDate) { this.departureDate = departureDate; }

    public Airport getAirport() { return airport; }
    public void setAirport(Airport airport) { this.airport = airport; }

    public List<Day> getDays() { return days; }
    public void setDays(List<Day> days) { this.days = days; }

    public List<String> getCompania() { return compania; }
    public void setCompania(List<String> compania) { this.compania = compania; }

    public List<String> getDestino() { return destino; }
    public void setDestino(List<String> destino) { this.destino = destino; }

    public List<String> getEspecial() { return especial; }
    public void setEspecial(List<String> especial) { this.especial = especial; }

    public List<String> getPlan() { return plan; }
    public void setPlan(List<String> plan) { this.plan = plan; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}