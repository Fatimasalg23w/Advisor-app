// ========================================
// TourService.java - Servicio de Tours
// ========================================

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class TourService {
    private MongoCollection<Document> collection;
    private Gson gson;

    public TourService() {
        this.collection = MongoDBConnection.getToursCollection();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Crear tour
    public void createTour(Tour tour) {
        try {
            String json = gson.toJson(tour);
            Document doc = Document.parse(json);
            collection.insertOne(doc);
            System.out.println("✅ Tour creado: " + tour.getTourName());
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Obtener tour por nombre
    public Tour getTourByName(String tourName) {
        try {
            Document query = new Document("tourName", tourName);
            Document doc = collection.find(query).first();
            if (doc != null) {
                return gson.fromJson(doc.toJson(), Tour.class);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Obtener tour por ID
    public Tour getTourById(String tourId) {
        try {
            Document query = new Document("tourId", tourId);
            Document doc = collection.find(query).first();
            if (doc != null) {
                return gson.fromJson(doc.toJson(), Tour.class);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return null;
    }

    // Listar todos los tours
    public List<Tour> getAllTours() {
        List<Tour> tours = new ArrayList<>();
        try {
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tour tour = gson.fromJson(doc.toJson(), Tour.class);
                tours.add(tour);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return tours;
    }

    // Buscar tours por mes
    public List<Tour> getToursByMonth(String month) {
        List<Tour> tours = new ArrayList<>();
        try {
            Document query = new Document("month", month);
            MongoCursor<Document> cursor = collection.find(query).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tour tour = gson.fromJson(doc.toJson(), Tour.class);
                tours.add(tour);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return tours;
    }

    // Buscar tours por año
    public List<Tour> getToursByYear(int year) {
        List<Tour> tours = new ArrayList<>();
        try {
            Document query = new Document("year", year);
            MongoCursor<Document> cursor = collection.find(query).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tour tour = gson.fromJson(doc.toJson(), Tour.class);
                tours.add(tour);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return tours;
    }

    // Actualizar tour
    public void updateTour(Tour tour) {
        try {
            Document query = new Document("tourId", tour.getTourId());
            String json = gson.toJson(tour);
            Document newDoc = Document.parse(json);
            collection.replaceOne(query, newDoc);
            System.out.println("✅ Tour actualizado: " + tour.getTourName());
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Eliminar tour
    public void deleteTour(String tourName) {
        try {
            Document query = new Document("tourName", tourName);
            collection.deleteOne(query);
            System.out.println("✅ Tour eliminado: " + tourName);
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Buscar tours por categoría de compañía
    public List<Tour> getToursByCompania(String compania) {
        List<Tour> tours = new ArrayList<>();
        try {
            Document query = new Document("compania", compania);
            MongoCursor<Document> cursor = collection.find(query).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tour tour = gson.fromJson(doc.toJson(), Tour.class);
                tours.add(tour);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return tours;
    }

    // Buscar tours por destino
    public List<Tour> getToursByDestino(String destino) {
        List<Tour> tours = new ArrayList<>();
        try {
            Document query = new Document("destino", destino);
            MongoCursor<Document> cursor = collection.find(query).iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Tour tour = gson.fromJson(doc.toJson(), Tour.class);
                tours.add(tour);
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return tours;
    }
}