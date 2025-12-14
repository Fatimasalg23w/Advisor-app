public class DatabaseManager {
    public DatabaseManager() {
        System.out.println("Database connection initialized");
    }
    public void saveClient(Client client) {
        System.out.println("Saving client: " + client.getName());
    }
    public void saveAppointment(Appointment appointment) {
        System.out.println("Saving appointment: " + appointment.getId());
    }
    public void saveReservation(Reservation reservation) {
        System.out.println("Saving reservation: " + reservation.getId());
    }
    public void close() {
        System.out.println("Database connection closed");
    }
}