// ========================================
// UserService.java - Servicio de Usuarios
// ========================================

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private MongoCollection<Document> collection;
    private Gson gson;

    public UserService() {
        this.collection = MongoDBConnection.getUsersCollection();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Crear usuario
    public void createUser(User user) {
        try {
            if (userExists(user.getUsername())) {
                System.err.println("❌ Usuario ya existe");
                return;
            }

            String json = gson.toJson(user);
            Document doc = Document.parse(json);
            collection.insertOne(doc);
            System.out.println("✅ Usuario creado: " + user.getUsername());
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Verificar si usuario existe
    public boolean userExists(String username) {
        try {
            Document query = new Document("username", username);
            return collection.find(query).first() != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Login usuario
    public User login(String username, String password) {
        try {
            Document query = new Document()
                .append("username", username)
                .append("password", password)
                .append("isActive", true);
            
            Document doc = collection.find(query).first();
            if (doc != null) {
                User user = gson.fromJson(doc.toJson(), User.class);
                user.setLastLogin(java.time.LocalDateTime.now().toString());
                updateUser(user);
                System.out.println("✅ Usuario login: " + username);
                return user;
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Actualizar usuario
    public void updateUser(User user) {
        try {
            Document query = new Document("userId", user.getUserId());
            String json = gson.toJson(user);
            Document newDoc = Document.parse(json);
            collection.replaceOne(query, newDoc);
            System.out.println("✅ Usuario actualizado");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Obtener usuario por username
    public User getUserByUsername(String username) {
        try {
            Document query = new Document("username", username);
            Document doc = collection.find(query).first();
            if (doc != null) {
                return gson.fromJson(doc.toJson(), User.class);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Listar usuarios
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                User user = gson.fromJson(doc.toJson(), User.class);
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return users;
    }

    // Agregar tour a favoritos
    public void addFavoriteTour(String username, String tourId) {
        try {
            User user = getUserByUsername(username);
            if (user != null) {
                if (!user.getFavoriteToursIds().contains(tourId)) {
                    user.getFavoriteToursIds().add(tourId);
                    updateUser(user);
                    System.out.println("✅ Tour agregado a favoritos");
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Agregar booking al historial
    public void addBookingToHistory(String username, String bookingId) {
        try {
            User user = getUserByUsername(username);
            if (user != null) {
                user.getBookingHistory().add(bookingId);
                updateUser(user);
                System.out.println("✅ Reserva agregada al historial");
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}