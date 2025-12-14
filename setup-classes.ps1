# setup-classes.ps1
# Script para crear todas las clases necesarias

Write-Host "Configurando clases de la aplicación..." -ForegroundColor Cyan

# 1. Renombrar el archivo principal
Write-Host "Renombrando archivo principal..." -ForegroundColor Yellow
if (Test-Path "src\main\java\3RPANEL.java") {
    Rename-Item "src\main\java\3RPANEL.java" "AdvisorApp.java"
    Write-Host "✓ AdvisorApp.java renombrado" -ForegroundColor Green
}

# 2. Crear Client.java
Write-Host "Creando Client.java..." -ForegroundColor Yellow
$clientContent = @'
public class Client {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String notes;

    public Client(String id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.notes = "";
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getNotes() { return notes; }

    // Setters
    public void setNotes(String notes) { this.notes = notes; }
}
'@
Set-Content -Path "src\main\java\Client.java" -Value $clientContent
Write-Host "✓ Client.java creado" -ForegroundColor Green

# 3. Crear Advisor.java
Write-Host "Creando Advisor.java..." -ForegroundColor Yellow
$advisorContent = @'
public class Advisor {
    private String id;
    private String username;
    private String name;

    public Advisor(String id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getName() { return name; }
}
'@
Set-Content -Path "src\main\java\Advisor.java" -Value $advisorContent
Write-Host "✓ Advisor.java creado" -ForegroundColor Green

# 4. Crear Appointment.java
Write-Host "Creando Appointment.java..." -ForegroundColor Yellow
$appointmentContent = @'
import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private String clientId;
    private LocalDateTime dateTime;
    private String purpose;
    private String status;
    private String notes;

    public Appointment(String id, String clientId, LocalDateTime dateTime, String purpose, String status) {
        this.id = id;
        this.clientId = clientId;
        this.dateTime = dateTime;
        this.purpose = purpose;
        this.status = status;
        this.notes = "";
    }

    // Getters
    public String getId() { return id; }
    public String getClientId() { return clientId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getPurpose() { return purpose; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
}
'@
Set-Content -Path "src\main\java\Appointment.java" -Value $appointmentContent
Write-Host "✓ Appointment.java creado" -ForegroundColor Green

# 5. Crear Reservation.java
Write-Host "Creando Reservation.java..." -ForegroundColor Yellow
$reservationContent = @'
import java.time.LocalDate;

public class Reservation {
    private String id;
    private String clientId;
    private String packageName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private double totalCost;

    public Reservation(String id, String clientId, String packageName, 
                      LocalDate startDate, LocalDate endDate, String status, double totalCost) {
        this.id = id;
        this.clientId = clientId;
        this.packageName = packageName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.totalCost = totalCost;
    }

    // Getters
    public String getId() { return id; }
    public String getClientId() { return clientId; }
    public String getPackageName() { return packageName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }
    public double getTotalCost() { return totalCost; }

    // Setters
    public void setStatus(String status) { this.status = status; }
}
'@
Set-Content -Path "src\main\java\Reservation.java" -Value $reservationContent
Write-Host "✓ Reservation.java creado" -ForegroundColor Green

# 6. Crear DatabaseManager.java
Write-Host "Creando DatabaseManager.java..." -ForegroundColor Yellow
$databaseContent = @'
public class DatabaseManager {
    
    public DatabaseManager() {
        // Inicializar conexión a base de datos (placeholder)
        System.out.println("Database connection initialized");
    }

    public void saveClient(Client client) {
        // Guardar cliente en BD
        System.out.println("Saving client: " + client.getName());
    }

    public void saveAppointment(Appointment appointment) {
        // Guardar cita en BD
        System.out.println("Saving appointment: " + appointment.getId());
    }

    public void saveReservation(Reservation reservation) {
        // Guardar reservación en BD
        System.out.println("Saving reservation: " + reservation.getId());
    }

    public void close() {
        // Cerrar conexión
        System.out.println("Database connection closed");
    }
}
'@
Set-Content -Path "src\main\java\DatabaseManager.java" -Value $databaseContent
Write-Host "✓ DatabaseManager.java creado" -ForegroundColor Green

Write-Host ""
Write-Host "================================" -ForegroundColor Cyan
Write-Host "¡Configuración completada!" -ForegroundColor Green
Write-Host "================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Archivos creados:" -ForegroundColor Yellow
Get-ChildItem "src\main\java\*.java" | ForEach-Object { Write-Host "  - $($_.Name)" -ForegroundColor White }
Write-Host ""
Write-Host "Ahora puedes ejecutar: .\run.ps1" -ForegroundColor Cyan