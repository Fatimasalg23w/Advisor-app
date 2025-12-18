// ========================================
// BookingService.java
// ========================================

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private MongoCollection<Document> collection;
    private Gson gson;

    public BookingService() {
        this.collection = MongoDBConnection.getBookingsCollection();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Crear booking
    public void createBooking(BookingConfirmation booking) {
        try {
            String json = gson.toJson(booking);
            Document doc = Document.parse(json);
            collection.insertOne(doc);
            System.out.println("✅ Reserva creada: " + booking.getClientBookingNumber());
            System.out.println("   Cliente: " + booking.getClientName());
            System.out.println("   Tipo: " + booking.getBookingType());
            System.out.println("   Total: $" + booking.getTotalCost());
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Buscar por número de reserva del cliente
    public BookingConfirmation getByClientNumber(String clientBookingNumber) {
        try {
            Document query = new Document("clientBookingNumber", clientBookingNumber);
            Document doc = collection.find(query).first();
            if (doc != null) {
                return gson.fromJson(doc.toJson(), BookingConfirmation.class);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Buscar por número de reserva del proveedor
    public BookingConfirmation getByProviderNumber(String providerBookingNumber) {
        try {
            Document query = new Document("providerBookingNumber", providerBookingNumber);
            Document doc = collection.find(query).first();
            if (doc != null) {
                return gson.fromJson(doc.toJson(), BookingConfirmation.class);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Listar todas las reservas
    public List<BookingConfirmation> getAllBookings() {
        List<BookingConfirmation> bookings = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                BookingConfirmation booking = gson.fromJson(doc.toJson(), BookingConfirmation.class);
                bookings.add(booking);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return bookings;
    }

    // Actualizar estado de reserva
    public void updateBookingStatus(String clientBookingNumber, String newStatus) {
        try {
            BookingConfirmation booking = getByClientNumber(clientBookingNumber);
            if (booking != null) {
                booking.setStatus(newStatus);
                Document query = new Document("bookingId", booking.getBookingId());
                String json = gson.toJson(booking);
                Document newDoc = Document.parse(json);
                collection.replaceOne(query, newDoc);
                System.out.println("✅ Reserva actualizada: " + clientBookingNumber);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Listar reservas por advisor
    public List<BookingConfirmation> getBookingsByAdvisor(String advisorId) {
        List<BookingConfirmation> bookings = new ArrayList<>();
        try {
            Document query = new Document("advisorId", advisorId);
            MongoCursor<Document> cursor = collection.find(query).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                BookingConfirmation booking = gson.fromJson(doc.toJson(), BookingConfirmation.class);
                bookings.add(booking);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return bookings;
    }
}