// ========================================
// AdminService.java
// ========================================

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private MongoCollection<Document> collection;
    private Gson gson;

    public AdminService() {
        this.collection = MongoDBConnection.getAdminsCollection();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Crear admin
    public void createAdmin(Admin admin) {
        try {
            if (adminExists(admin.getUsername())) {
                System.err.println("❌ Admin ya existe");
                return;
            }

            String json = gson.toJson(admin);
            Document doc = Document.parse(json);
            collection.insertOne(doc);
            System.out.println("✅ Admin creado: " + admin.getUsername());
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Verificar si admin existe
    public boolean adminExists(String username) {
        try {
            Document query = new Document("username", username);
            return collection.find(query).first() != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Login admin
    public Admin login(String username, String password) {
        try {
            Document query = new Document()
                .append("username", username)
                .append("password", password)
                .append("isActive", true);
            
            Document doc = collection.find(query).first();
            if (doc != null) {
                Admin admin = gson.fromJson(doc.toJson(), Admin.class);
                admin.setLastLogin(java.time.LocalDateTime.now().toString());
                updateAdmin(admin);
                System.out.println("✅ Admin login: " + username);
                return admin;
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Actualizar admin
    private void updateAdmin(Admin admin) {
        try {
            Document query = new Document("adminId", admin.getAdminId());
            String json = gson.toJson(admin);
            Document newDoc = Document.parse(json);
            collection.replaceOne(query, newDoc);
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Listar admins
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Admin admin = gson.fromJson(doc.toJson(), Admin.class);
                admins.add(admin);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return admins;
    }
}
