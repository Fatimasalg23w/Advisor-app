// Archivo: src/main/java/MongoDBConnection.java

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    // Tu cadena de conexión de MongoDB Atlas
    private static final String CONNECTION_STRING = 

    
    private static final String DATABASE_NAME = "project0";

    public static void connect() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("✅ Conectado a MongoDB Atlas");
            System.out.println("📂 Database: " + DATABASE_NAME);
            System.out.println("📋 Collections disponibles:");
            System.out.println("   - admins");
            System.out.println("   - advisor");
            System.out.println("   - tours");
            System.out.println("   - users");
            System.out.println("   - bookings");
            System.out.println("   - quotations");
        } catch (Exception e) {
            System.err.println("❌ Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Collection: admins
    public static MongoCollection<Document> getAdminsCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("admins");
    }

    // Collection: advisor
    public static MongoCollection<Document> getAdvisorsCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("advisor");
    }

    // Collection: tours
    public static MongoCollection<Document> getToursCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("tours");
    }

    // Collection: users (clientes)
    public static MongoCollection<Document> getUsersCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("users");
    }

    // Collection: bookings (confirmaciones de reserva)
    public static MongoCollection<Document> getBookingsCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("bookings");
    }

    // Collection: quotations (cotizaciones)
    public static MongoCollection<Document> getQuotationsCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("quotations");
    }

    // Collection: videocalls
    public static MongoCollection<Document> getVideoCallsCollection() {
        if (database == null) {
            connect();
        }
        return database.getCollection("videocalls");
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("🔒 Conexión cerrada");
        }
    }

    // Método para verificar la conexión
    public static boolean testConnection() {
        try {
            if (database == null) {
                connect();
            }
            // Intentar listar collections como prueba
            database.listCollectionNames().first();
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            return false;
        }
    }
}
