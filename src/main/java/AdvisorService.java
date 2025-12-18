
// AdvisorService.java
// ========================================

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class AdvisorService {
    private MongoCollection<Document> collection;
    private Gson gson;

    public AdvisorService() {
        this.collection = MongoDBConnection.getAdvisorsCollection();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Crear advisor
    public void createAdvisor(Advisor advisor) {
        try {
            if (advisorExists(advisor.getUsername())) {
                System.err.println("❌ Advisor ya existe");
                return;
            }

            String json = gson.toJson(advisor);
            Document doc = Document.parse(json);
            collection.insertOne(doc);
            System.out.println("✅ Advisor creado: " + advisor.getUsername());
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Verificar si advisor existe
    public boolean advisorExists(String username) {
        try {
            Document query = new Document("username", username);
            return collection.find(query).first() != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Login advisor
    public Advisor login(String username, String password) {
        try {
            Document query = new Document()
                .append("username", username)
                .append("password", password)
                .append("isActive", true);
            
            Document doc = collection.find(query).first();
            if (doc != null) {
                Advisor advisor = gson.fromJson(doc.toJson(), Advisor.class);
                advisor.setLastLogin(java.time.LocalDateTime.now().toString());
                updateAdvisor(advisor);
                System.out.println("✅ Advisor login: " + username);
                return advisor;
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Actualizar advisor
    public void updateAdvisor(Advisor advisor) {
        try {
            Document query = new Document("advisorId", advisor.getAdvisorId());
            String json = gson.toJson(advisor);
            Document newDoc = Document.parse(json);
            collection.replaceOne(query, newDoc);
            System.out.println("✅ Advisor actualizado");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Obtener advisor por username
    public Advisor getAdvisorByUsername(String username) {
        try {
            Document query = new Document("username", username);
            Document doc = collection.find(query).first();
            if (doc != null) {
                return gson.fromJson(doc.toJson(), Advisor.class);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Listar advisors
    public List<Advisor> getAllAdvisors() {
        List<Advisor> advisors = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Advisor advisor = gson.fromJson(doc.toJson(), Advisor.class);
                advisors.add(advisor);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return advisors;
    }

    // Dar permiso para agregar tours
    public void grantTourPermission(String username) {
        try {
            Advisor advisor = getAdvisorByUsername(username);
            if (advisor != null) {
                advisor.setCanAddTours(true);
                updateAdvisor(advisor);
                System.out.println("✅ Permiso concedido a: " + username);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Agregar videollamada
    public void addVideoCall(String username, Advisor.VideoCall call) {
        try {
            Advisor advisor = getAdvisorByUsername(username);
            if (advisor != null) {
                advisor.getVideoCalls().add(call);
                advisor.getStats().setTotalVideoCalls(
                    advisor.getStats().getTotalVideoCalls() + 1
                );
                updateAdvisor(advisor);
                System.out.println("✅ Videollamada agregada: " + call.getCallId());
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Actualizar estado de videollamada
    public void updateVideoCallStatus(String username, String callId, String newStatus, String notes) {
        try {
            Advisor advisor = getAdvisorByUsername(username);
            if (advisor != null) {
                for (Advisor.VideoCall call : advisor.getVideoCalls()) {
                    if (call.getCallId().equals(callId)) {
                        call.setStatus(newStatus);
                        call.setNotes(notes);
                        if (newStatus.equals("COMPLETED")) {
                            call.setCompletedAt(java.time.LocalDateTime.now().toString());
                            advisor.getStats().setCompletedVideoCalls(
                                advisor.getStats().getCompletedVideoCalls() + 1
                            );
                        }
                        break;
                    }
                }
                updateAdvisor(advisor);
                System.out.println("✅ Videollamada actualizada: " + callId);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Agregar cotización
    public void addQuotation(String username, Advisor.Quotation quotation) {
        try {
            Advisor advisor = getAdvisorByUsername(username);
            if (advisor != null) {
                advisor.getQuotations().add(quotation);
                advisor.getStats().setTotalQuotations(
                    advisor.getStats().getTotalQuotations() + 1
                );
                updateAdvisor(advisor);
                System.out.println("✅ Cotización agregada: " + quotation.getQuotationId());
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Actualizar estado de cotización
    public void updateQuotationStatus(String username, String quotId, String newStatus) {
        try {
            Advisor advisor = getAdvisorByUsername(username);
            if (advisor != null) {
                for (Advisor.Quotation quot : advisor.getQuotations()) {
                    if (quot.getQuotationId().equals(quotId)) {
                        quot.setStatus(newStatus);
                        if (newStatus.equals("ACCEPTED")) {
                            advisor.getStats().setAcceptedQuotations(
                                advisor.getStats().getAcceptedQuotations() + 1
                            );
                        }
                        break;
                    }
                }
                updateAdvisor(advisor);
                System.out.println("✅ Cotización actualizada: " + quotId);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
