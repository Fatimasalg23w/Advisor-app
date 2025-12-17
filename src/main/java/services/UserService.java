// ====================================================
// ARCHIVO 2: src/main/java/services/UserService.java
// ====================================================
package services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import models.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private MongoCollection<Document> collection;
    
    public UserService(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    
    public boolean createUser(User user) {
        try {
            if (usernameExists(user.getUsername())) {
                System.err.println("❌ El username ya existe");
                return false;
            }
            if (emailExists(user.getEmail())) {
                System.err.println("❌ El email ya existe");
                return false;
            }
            
            Document doc = user.toDocument();
            collection.insertOne(doc);
            user.setId(doc.getObjectId("_id"));
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error al crear usuario: " + e.getMessage());
            return false;
        }
    }
    
    public User getUserById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Document doc = collection.find(Filters.eq("_id", objectId)).first();
            return doc != null ? new User(doc) : null;
        } catch (Exception e) {
            System.err.println("❌ Error al obtener usuario: " + e.getMessage());
            return null;
        }
    }
    
    public User getUserByUsername(String username) {
        try {
            Document doc = collection.find(Filters.eq("username", username)).first();
            return doc != null ? new User(doc) : null;
        } catch (Exception e) {
            System.err.println("❌ Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }
    
    public User getUserByEmail(String email) {
        try {
            Document doc = collection.find(Filters.eq("email", email)).first();
            return doc != null ? new User(doc) : null;
        } catch (Exception e) {
            System.err.println("❌ Error al buscar usuario: " + e.getMessage());
            return null;
        }
    }
    
    public User authenticate(String username, String password) {
        try {
            Document doc = collection.find(
                Filters.and(
                    Filters.eq("username", username),
                    Filters.eq("password", password),
                    Filters.eq("active", true)
                )
            ).first();
            
            if (doc != null) {
                User user = new User(doc);
                user.updateLastLogin();
                updateUser(user);
                return user;
            }
            return null;
        } catch (Exception e) {
            System.err.println("❌ Error en autenticación: " + e.getMessage());
            return null;
        }
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                users.add(new User(cursor.next()));
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar usuarios: " + e.getMessage());
        }
        return users;
    }
    
    public List<User> getActiveUsers() {
        List<User> users = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find(Filters.eq("active", true)).iterator()) {
            while (cursor.hasNext()) {
                users.add(new User(cursor.next()));
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar usuarios activos: " + e.getMessage());
        }
        return users;
    }
    
    public boolean updateUser(User user) {
        try {
            Document doc = user.toDocument();
            doc.remove("_id");
            collection.updateOne(
                Filters.eq("_id", user.getId()),
                new Document("$set", doc)
            );
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updatePassword(String userId, String newPassword) {
        try {
            ObjectId objectId = new ObjectId(userId);
            collection.updateOne(
                Filters.eq("_id", objectId),
                Updates.set("password", newPassword)
            );
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar contraseña: " + e.getMessage());
            return false;
        }
    }
    
    public boolean addBookingToUser(String userId, String bookingId) {
        try {
            User user = getUserById(userId);
            if (user != null) {
                user.addBooking(bookingId);
                return updateUser(user);
            }
            return false;
        } catch (Exception e) {
            System.err.println("❌ Error al agregar booking: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deactivateUser(String userId) {
        try {
            ObjectId objectId = new ObjectId(userId);
            collection.updateOne(
                Filters.eq("_id", objectId),
                Updates.set("active", false)
            );
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error al desactivar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean activateUser(String userId) {
        try {
            ObjectId objectId = new ObjectId(userId);
            collection.updateOne(
                Filters.eq("_id", objectId),
                Updates.set("active", true)
            );
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error al activar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteUser(String userId) {
        try {
            ObjectId objectId = new ObjectId(userId);
            collection.deleteOne(Filters.eq("_id", objectId));
            return true;
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean usernameExists(String username) {
        return collection.countDocuments(Filters.eq("username", username)) > 0;
    }
    
    public boolean emailExists(String email) {
        return collection.countDocuments(Filters.eq("email", email)) > 0;
    }
    
    public long getTotalUsers() {
        return collection.countDocuments();
    }
    
    public long getActiveUserCount() {
        return collection.countDocuments(Filters.eq("active", true));
    }
}