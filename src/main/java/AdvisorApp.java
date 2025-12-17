import java.util.*;
import models.*;
import services.*;

public class CompleteTourAdvisorApp {
    private static Scanner scanner = new Scanner(System.in);
    private static MongoDBConnection dbConnection;
    private static AdminService adminService;
    private static AdvisorService advisorService;
    private static TourService tourService;
    private static UserService userService;
    private static BookingService bookingService;
    
    private static Admin currentAdmin;
    private static Advisor currentAdvisor;
    private static User currentUser;
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║    🌍 TOUR ADVISOR - Sistema Completo    ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        
        try {
            dbConnection = new MongoDBConnection();
            adminService = new AdminService(dbConnection.getAdminsCollection());
            advisorService = new AdvisorService(dbConnection.getAdvisorsCollection());
            tourService = new TourService(dbConnection.getToursCollection());
            userService = new UserService(dbConnection.getUsersCollection());
            bookingService = new BookingService(dbConnection.getBookingsCollection());
            
            System.out.println("✅ Conexión a MongoDB establecida");
            mainMenu();
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (dbConnection != null) {
                dbConnection.close();
            }
            scanner.close();
        }
    }
    
    private static void mainMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║         MENÚ PRINCIPAL             ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. 👤 Login Admin");
            System.out.println("2. 🎯 Login Advisor");
            System.out.println("3. 👥 Login Usuario/Cliente");
            System.out.println("4. 📝 Registro Usuario Nuevo");
            System.out.println("0. ❌ Salir");
            System.out.print("\nSeleccione opción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: loginAdmin(); break;
                case 2: loginAdvisor(); break;
                case 3: loginUser(); break;
                case 4: registerUser(); break;
                case 0:
                    System.out.println("👋 ¡Hasta luego!");
                    return;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    
    // ========================================
    // LOGIN METHODS
    // ========================================
    
    private static void loginAdmin() {
        System.out.println("\n🔐 LOGIN ADMINISTRADOR");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        currentAdmin = adminService.authenticate(username, password);
        
        if (currentAdmin != null) {
            System.out.println("✅ Bienvenido Admin: " + currentAdmin.getFullName());
            adminMenu();
        } else {
            System.out.println("❌ Credenciales incorrectas");
        }
    }
    
    private static void loginAdvisor() {
        System.out.println("\n🔐 LOGIN ADVISOR");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        currentAdvisor = advisorService.authenticate(username, password);
        
        if (currentAdvisor != null) {
            System.out.println("✅ Bienvenido Advisor: " + currentAdvisor.getFullName());
            advisorMenu();
        } else {
            System.out.println("❌ Credenciales incorrectas");
        }
    }
    
    private static void loginUser() {
        System.out.println("\n🔐 LOGIN USUARIO");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        currentUser = userService.authenticate(username, password);
        
        if (currentUser != null) {
            System.out.println("✅ Bienvenido: " + currentUser.getFullName());
            userMenu();
        } else {
            System.out.println("❌ Credenciales incorrectas");
        }
    }
    
    private static void registerUser() {
        System.out.println("\n📝 REGISTRO DE USUARIO");
        System.out.println("-".repeat(50));
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String fullName = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        
        User newUser = new User(username, password, email, fullName);
        newUser.setPhone(phone);
        
        if (userService.createUser(newUser)) {
            System.out.println("✅ Usuario registrado exitosamente");
        } else {
            System.out.println("❌ Error al registrar usuario");
        }
    }
    
    // ========================================
    // ADMIN MENU
    // ========================================
    
    private static void adminMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║       MENÚ ADMINISTRADOR           ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. 🎯 Gestión de Advisors");
            System.out.println("2. 🌍 Gestión de Tours");
            System.out.println("3. 👥 Gestión de Usuarios");
            System.out.println("4. 📊 Reportes");
            System.out.println("0. 🚪 Cerrar Sesión");
            System.out.print("\nSeleccione opción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: adminAdvisorsMenu(); break;
                case 2: adminToursMenu(); break;
                case 3: adminUsersMenu(); break;
                case 4: adminReports(); break;
                case 0:
                    currentAdmin = null;
                    return;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    
    private static void adminAdvisorsMenu() {
        while (true) {
            System.out.println("\n🎯 GESTIÓN DE ADVISORS");
            System.out.println("1. Crear Advisor");
            System.out.println("2. Listar Advisors");
            System.out.println("3. Ver Detalles");
            System.out.println("0. Volver");
            System.out.print("\nOpción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: adminCreateAdvisor(); break;
                case 2: adminListAdvisors(); break;
                case 3: adminViewAdvisor(); break;
                case 0: return;
                default: System.out.println("❌ Opción inválida");
            }
        }
    }
    
    private static void adminCreateAdvisor() {
        System.out.println("\n➕ CREAR ADVISOR");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String fullName = scanner.nextLine();
        
        Advisor advisor = new Advisor(username, password, email, fullName);
        
        if (advisorService.createAdvisor(advisor)) {
            System.out.println("✅ Advisor creado");
        } else {
            System.out.println("❌ Error al crear");
        }
    }
    
    private static void adminListAdvisors() {
        System.out.println("\n📋 LISTA DE ADVISORS");
        List<Advisor> advisors = advisorService.getAllAdvisors();
        
        if (advisors.isEmpty()) {
            System.out.println("No hay advisors");
            return;
        }
        
        for (Advisor adv : advisors) {
            System.out.printf("%s | %s | %s%n",
                adv.getAdvisorId(),
                adv.getFullName(),
                adv.isActive() ? "✅" : "❌"
            );
        }
    }
    
    private static void adminViewAdvisor() {
        System.out.print("\n🔍 ID del Advisor: ");
        String id = scanner.nextLine();
        
        Advisor advisor = advisorService.getAdvisorById(id);
        
        if (advisor == null) {
            System.out.println("❌ No encontrado");
            return;
        }
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("Nombre: " + advisor.getFullName());
        System.out.println("Email: " + advisor.getEmail());
        System.out.println("Activo: " + (advisor.isActive() ? "✅" : "❌"));
    }
    
    private static void adminToursMenu() {
        while (true) {
            System.out.println("\n🌍 GESTIÓN DE TOURS");
            System.out.println("1. Crear Tour");
            System.out.println("2. Listar Tours");
            System.out.println("3. Ver Detalles");
            System.out.println("0. Volver");
            System.out.print("\nOpción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: adminCreateTour(); break;
                case 2: adminListTours(); break;
                case 3: adminViewTour(); break;
                case 0: return;
                default: System.out.println("❌ Opción inválida");
            }
        }
    }
    
    private static void adminCreateTour() {
        System.out.println("\n➕ CREAR TOUR");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        System.out.print("Precio: ");
        double price = scanner.nextDouble();
        System.out.print("Duración (días): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ubicación: ");
        String location = scanner.nextLine();
        System.out.print("Categoría: ");
        String category = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();
        
        Tour tour = new Tour(name, description, price, duration, location, category, capacity);
        
        if (tourService.createTour(tour)) {
            System.out.println("✅ Tour creado");
        } else {
            System.out.println("❌ Error");
        }
    }
    
    private static void adminListTours() {
        System.out.println("\n📋 LISTA DE TOURS");
        List<Tour> tours = tourService.getAllTours();
        
        if (tours.isEmpty()) {
            System.out.println("No hay tours");
            return;
        }
        
        for (Tour tour : tours) {
            System.out.printf("%s | $%.2f | %s | %d/%d%n",
                tour.getName(),
                tour.getPrice(),
                tour.getLocation(),
                tour.getAvailableSpots(),
                tour.getMaxCapacity()
            );
        }
    }
    
    private static void adminViewTour() {
        System.out.print("\n🔍 ID del Tour: ");
        String id = scanner.nextLine();
        
        Tour tour = tourService.getTourById(id);
        
        if (tour == null) {
            System.out.println("❌ No encontrado");
            return;
        }
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("Nombre: " + tour.getName());
        System.out.println("Precio: $" + tour.getPrice());
        System.out.println("Ubicación: " + tour.getLocation());
        System.out.println("Disponibles: " + tour.getAvailableSpots());
    }
    
    private static void adminUsersMenu() {
        while (true) {
            System.out.println("\n👥 GESTIÓN DE USUARIOS");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Ver Detalles");
            System.out.println("0. Volver");
            System.out.print("\nOpción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: adminListUsers(); break;
                case 2: adminViewUser(); break;
                case 0: return;
                default: System.out.println("❌ Opción inválida");
            }
        }
    }
    
    private static void adminListUsers() {
        System.out.println("\n📋 LISTA DE USUARIOS");
        List<User> users = userService.getAllUsers();
        
        for (User user : users) {
            System.out.printf("%s | %s | %s%n",
                user.getUsername(),
                user.getFullName(),
                user.getEmail()
            );
        }
    }
    
    private static void adminViewUser() {
        System.out.print("\n🔍 Username: ");
        String username = scanner.nextLine();
        
        User user = userService.getUserByUsername(username);
        
        if (user == null) {
            System.out.println("❌ No encontrado");
            return;
        }
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("Nombre: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Teléfono: " + user.getPhone());
    }
    
    private static void adminReports() {
        System.out.println("\n📊 REPORTES");
        System.out.println("Total Advisors: " + advisorService.getTotalAdvisors());
        System.out.println("Total Tours: " + tourService.getTotalTours());
        System.out.println("Total Usuarios: " + userService.getTotalUsers());
    }
    
    // ========================================
    // ADVISOR MENU
    // ========================================
    
    private static void advisorMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║         MENÚ ADVISOR               ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. 📹 Videollamadas");
            System.out.println("2. 💰 Cotizaciones");
            System.out.println("3. 📋 Reservas");
            System.out.println("0. 🚪 Cerrar Sesión");
            System.out.print("\nOpción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: advisorVideoCallsMenu(); break;
                case 2: advisorQuotationsMenu(); break;
                case 3: advisorBookingsMenu(); break;
                case 0:
                    currentAdvisor = null;
                    return;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    
    private static void advisorVideoCallsMenu() {
        System.out.println("\n📹 MIS VIDEOLLAMADAS");
        System.out.println("1. Programar Videollamada");
        System.out.println("2. Ver Mis Videollamadas");
        System.out.println("0. Volver");
        System.out.print("\nOpción: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            advisorScheduleVideoCall();
        } else if (choice == 2) {
            advisorListVideoCalls();
        }
    }
    
    private static void advisorScheduleVideoCall() {
        System.out.println("\n📹 PROGRAMAR VIDEOLLAMADA");
        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Hora (HH:mm): ");
        String time = scanner.nextLine();
        
        Advisor.VideoCall call = new Advisor.VideoCall();
        call.setClientEmail(email);
        call.setScheduledDateTime(date + "T" + time);
        call.setStatus("SCHEDULED");
        call.setMeetingLink("https://meet.example.com/" + UUID.randomUUID().toString());
        
        advisorService.scheduleVideoCall(currentAdvisor.getUsername(), call);
        currentAdvisor = advisorService.getAdvisorByUsername(currentAdvisor.getUsername());
        
        System.out.println("✅ Videollamada programada");
        System.out.println("Link: " + call.getMeetingLink());
    }
    
    private static void advisorListVideoCalls() {
        System.out.println("\n📋 MIS VIDEOLLAMADAS");
        
        if (currentAdvisor.getVideoCalls().isEmpty()) {
            System.out.println("No tienes videollamadas programadas");
            return;
        }
        
        for (Advisor.VideoCall call : currentAdvisor.getVideoCalls()) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("Cliente: " + call.getClientEmail());
            System.out.println("Fecha: " + call.getScheduledDateTime());
            System.out.println("Estado: " + call.getStatus());
            System.out.println("Link: " + call.getMeetingLink());
        }
    }
    
    private static void advisorQuotationsMenu() {
        System.out.println("\n💰 COTIZACIONES");
        System.out.println("1. Crear Cotización");
        System.out.println("2. Mis Cotizaciones");
        System.out.println("0. Volver");
        System.out.print("\nOpción: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            advisorCreateQuotation();
        } else if (choice == 2) {
            advisorListQuotations();
        }
    }
    
    private static void advisorCreateQuotation() {
        System.out.println("\n💰 CREAR COTIZACIÓN");
        System.out.print("Email del cliente: ");
        String email = scanner.nextLine();
        System.out.print("Destino: ");
        String destination = scanner.nextLine();
        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        System.out.print("Número de personas: ");
        int people = scanner.nextInt();
        System.out.print("Costo estimado: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();
        
        Advisor.Quotation quot = new Advisor.Quotation();
        quot.setClientEmail(email);
        quot.setStatus("PENDING");
        quot.setEstimatedCost(cost);
        
        Advisor.QuotationDetails details = new Advisor.QuotationDetails();
        details.setDestination(destination);
        details.setStartDate(startDate);
        details.setEndDate(endDate);
        details.setNumberOfPeople(people);
        quot.setDetails(details);
        
        advisorService.addQuotation(currentAdvisor.getUsername(), quot);
        currentAdvisor = advisorService.getAdvisorByUsername(currentAdvisor.getUsername());
        
        System.out.println("✅ Cotización creada");
    }
    
    private static void advisorListQuotations() {
        System.out.println("\n📋 MIS COTIZACIONES");
        
        if (currentAdvisor.getQuotations().isEmpty()) {
            System.out.println("No tienes cotizaciones");
            return;
        }
        
        for (Advisor.Quotation quot : currentAdvisor.getQuotations()) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("Cliente: " + quot.getClientEmail());
            System.out.println("Destino: " + quot.getDetails().getDestination());
            System.out.println("Estado: " + quot.getStatus());
            System.out.println("Costo: $" + quot.getEstimatedCost());
        }
    }
    
    private static void advisorBookingsMenu() {
        System.out.println("\n📋 RESERVAS");
        System.out.println("Función en desarrollo");
    }
    
    // ========================================
    // USER MENU
    // ========================================
    
    private static void userMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║         MENÚ USUARIO               ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("1. 🌍 Ver Tours Disponibles");
            System.out.println("2. 🔍 Buscar Tours");
            System.out.println("3. 📋 Mis Reservas");
            System.out.println("0. 🚪 Cerrar Sesión");
            System.out.print("\nOpción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: userViewTours(); break;
                case 2: userSearchTours(); break;
                case 3: userMyBookings(); break;
                case 0:
                    currentUser = null;
                    return;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    
    private static void userViewTours() {
        System.out.println("\n🌍 TOURS DISPONIBLES");
        List<Tour> tours = tourService.getActiveTours();
        
        for (Tour tour : tours) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("Nombre: " + tour.getName());
            System.out.println("Precio: $" + tour.getPrice());
            System.out.println("Ubicación: " + tour.getLocation());
            System.out.println("Disponibles: " + tour.getAvailableSpots());
        }
    }
    
    private static void userSearchTours() {
        System.out.print("\n🔍 Buscar por nombre: ");
        String keyword = scanner.nextLine();
        
        List<Tour> tours = tourService.searchTours(keyword);
        
        if (tours.isEmpty()) {
            System.out.println("No se encontraron tours");
            return;
        }
        
        for (Tour tour : tours) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("Nombre: " + tour.getName());
            System.out.println("Precio: $" + tour.getPrice());
        }
    }
    
    private static void userMyBookings() {
        System.out.println("\n📋 MIS RESERVAS");
        System.out.println("Historial: " + currentUser.getBookingHistory().size() + " reservas");
    }
}