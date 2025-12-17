// Archivo: src/main/java/CompleteTourAdvisorApp.java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CompleteTourAdvisorApp {
    private static AdminService adminService;
    private static AdvisorService advisorService;
    private static TourService tourService;
    private static UserService userService;
    private static BookingService bookingService;
    private static Scanner scanner;
    
    private static Admin currentAdmin;
    private static Advisor currentAdvisor;
    private static User currentUser;
    private static String userType;  // "ADMIN", "ADVISOR", "USER"

    public static void main(String[] args) {
        printWelcome();
        
        MongoDBConnection.connect();
        initializeServices();
        scanner = new Scanner(System.in);

        if (authenticateUser()) {
            switch (userType) {
                case "ADMIN":
                    adminMenu();
                    break;
                case "ADVISOR":
                    advisorMenu();
                    break;
                case "USER":
                    userMenu();
                    break;
            }
        }

        scanner.close();
        MongoDBConnection.close();
        System.out.println("\n👋 ¡Hasta luego!");
    }

    private static void printWelcome() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║      🌍 TOUR ADVISOR - Sistema Completo v2.0    ║");
        System.out.println("║                                                   ║");
        System.out.println("║  • Administradores - Gestión completa             ║");
        System.out.println("║  • Advisors - Cotizaciones y videollamadas        ║");
        System.out.println("║  • Usuarios - Reservas y tours                    ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }

    private static void initializeServices() {
        adminService = new AdminService();
        advisorService = new AdvisorService();
        tourService = new TourService();
        userService = new UserService();
        bookingService = new BookingService();
    }

    private static boolean authenticateUser() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("AUTENTICACIÓN");
            System.out.println("=".repeat(50));
            System.out.println("1. 🔐 Login como Administrador");
            System.out.println("2. 🔐 Login como Advisor");
            System.out.println("3. 🔐 Login como Usuario");
            System.out.println("4. 📝 Registrarse como Usuario");
            System.out.println("0. 🚪 Salir");
            System.out.println("=".repeat(50));
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    if (loginAdmin()) return true;
                    break;
                case 2:
                    if (loginAdvisor()) return true;
                    break;
                case 3:
                    if (loginUser()) return true;
                    break;
                case 4:
                    registerUser();
                    break;
                case 0:
                    return false;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }

    private static boolean loginAdmin() {
        System.out.print("\n👤 Username: ");
        String username = scanner.nextLine();
        System.out.print("🔑 Password: ");
        String password = scanner.nextLine();
        
        currentAdmin = adminService.login(username, password);
        if (currentAdmin != null) {
            userType = "ADMIN";
            return true;
        }
        System.out.println("❌ Credenciales inválidas");
        return false;
    }

    private static boolean loginAdvisor() {
        System.out.print("\n👤 Username: ");
        String username = scanner.nextLine();
        System.out.print("🔑 Password: ");
        String password = scanner.nextLine();
        
        currentAdvisor = advisorService.login(username, password);
        if (currentAdvisor != null) {
            userType = "ADVISOR";
            return true;
        }
        System.out.println("❌ Credenciales inválidas");
        return false;
    }

    private static boolean loginUser() {
        System.out.print("\n👤 Username: ");
        String username = scanner.nextLine();
        System.out.print("🔑 Password: ");
        String password = scanner.nextLine();
        
        currentUser = userService.login(username, password);
        if (currentUser != null) {
            userType = "USER";
            return true;
        }
        System.out.println("❌ Credenciales inválidas");
        return false;
    }

    private static void registerUser() {
        System.out.println("\n📝 REGISTRO DE USUARIO");
        System.out.println("-".repeat(50));
        
        User user = new User();
        System.out.print("Username: ");
        user.setUsername(scanner.nextLine());
        System.out.print("Email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Password: ");
        user.setPassword(scanner.nextLine());
        System.out.print("Nombre completo: ");
        user.setFullName(scanner.nextLine());
        System.out.print("Teléfono: ");
        user.setPhone(scanner.nextLine());
        
        userService.createUser(user);
    }

    // ========================================
    // MENÚ ADMINISTRADOR
    // ========================================
    private static void adminMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("👑 PANEL ADMINISTRADOR: " + currentAdmin.getFullName());
            System.out.println("=".repeat(50));
            System.out.println("GESTIÓN DE TOURS:");
            System.out.println("  1. ➕ Crear nuevo tour");
            System.out.println("  2. 📋 Listar todos los tours");
            System.out.println("  3. 🔍 Buscar tour");
            System.out.println("  4. 🗑️  Eliminar tour");
            System.out.println("\nGESTIÓN DE ADVISORS:");
            System.out.println("  5. 👥 Ver todos los advisors");
            System.out.println("  6. ➕ Crear nuevo advisor");
            System.out.println("  7. ✅ Dar permiso para agregar tours");
            System.out.println("  8. 📞 Asignar videollamada a advisor");
            System.out.println("\nGESTIÓN DE ADMINISTRADORES:");
            System.out.println("  9. 👑 Crear nuevo administrador");
            System.out.println(" 10. 📋 Ver todos los administradores");
            System.out.println("\nREPORTES:");
            System.out.println(" 11. 📊 Ver todas las reservas");
            System.out.println(" 12. 📈 Ver estadísticas generales");
            System.out.println("\n  0. 🚪 Cerrar sesión");
            System.out.println("=".repeat(50));
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: adminCreateTour(); break;
                case 2: adminListTours(); break;
                case 3: adminSearchTour(); break;
                case 4: adminDeleteTour(); break;
                case 5: adminListAdvisors(); break;
                case 6: adminCreateAdvisor(); break;
                case 7: adminGrantTourPermission(); break;
                case 8: adminAssignVideoCall(); break;
                case 9: adminCreateAdmin(); break;
                case 10: adminListAdmins(); break;
                case 11: adminViewAllBookings(); break;
                case 12: adminViewStats(); break;
                case 0: running = false; break;
                default: System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void adminCreateTour() {
        System.out.println("\n➕ CREAR NUEVO TOUR");
        System.out.println("-".repeat(50));

        Tour tour = new Tour();
        System.out.print("Nombre del tour: ");
        tour.setTourName(scanner.nextLine());
        System.out.print("Año: ");
        tour.setYear(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Mes: ");
        tour.setMonth(scanner.nextLine());
        System.out.print("Día llegada: ");
        tour.setArrivalDate(scanner.nextInt());
        System.out.print("Día salida: ");
        tour.setDepartureDate(scanner.nextInt());
        scanner.nextLine();

        Tour.Airport airport = new Tour.Airport();
        System.out.print("Aeropuerto: ");
        airport.setName(scanner.nextLine());
        System.out.print("Código: ");
        airport.setCode(scanner.nextLine());
        airport.setTransfersIncluded("Todos");
        tour.setAirport(airport);

        System.out.print("¿Cuántos días de actividades? ");
        int numDays = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numDays; i++) {
            System.out.println("\n--- DÍA " + (i + 1) + " ---");
            Tour.Day day = new Tour.Day();
            day.setDay(i + 1);
            
            System.out.print("Actividad: ");
            day.setActivity(scanner.nextLine());
            System.out.print("Link: ");
            day.setLink(scanner.nextLine());
            System.out.print("Pickup: ");
            day.setPickup(scanner.nextLine());
            System.out.print("DropOff: ");
            day.setDropOff(scanner.nextLine());
            day.setDepartures("Daily");
            System.out.print("Tiempo total: ");
            day.setTotalTime(scanner.nextLine());
            System.out.print("Hora inicio: ");
            day.setStartTime(scanner.nextLine());
            System.out.print("Hora fin: ");
            day.setFinishTime(scanner.nextLine());
            day.setCancelationPolicy("No returnable");
            System.out.print("Comidas: ");
            day.setMealsIncluded(scanner.nextLine());
            System.out.print("Proveedor: ");
            day.setProvider(scanner.nextLine());
            
            Tour.Pricing pricing = new Tour.Pricing();
            System.out.print("Precio adulto: ");
            pricing.setAdultPriceMXN(scanner.nextDouble());
            System.out.print("Precio niño: ");
            pricing.setChildPriceMXN(scanner.nextDouble());
            scanner.nextLine();
            day.setPricing(pricing);
            
            System.out.print("Descripción: ");
            day.setDescription(scanner.nextLine());
            System.out.print("URL foto (Enter para omitir): ");
            String foto = scanner.nextLine();
            if (!foto.isEmpty()) {
                day.getPictures().add(foto);
            }
            
            tour.getDays().add(day);
        }

        tour.setCompania(Arrays.asList("family", "partner"));
        tour.setDestino(Arrays.asList("beach", "nature"));
        tour.setEspecial(Arrays.asList("none"));
        tour.setPlan(Arrays.asList("adventure"));

        tourService.createTour(tour);
    }

    private static void adminListTours() {
        System.out.println("\n📋 TODOS LOS TOURS");
        System.out.println("-".repeat(50));
        
        List<Tour> tours = tourService.getAllTours();
        if (tours.isEmpty()) {
            System.out.println("No hay tours");
            return;
        }

        for (int i = 0; i < tours.size(); i++) {
            Tour tour = tours.get(i);
            System.out.println("\n" + (i+1) + ". 🎫 " + tour.getTourName());
            System.out.println("   📅 " + tour.getMonth() + " " + tour.getYear());
            System.out.println("   ✈️  " + (tour.getAirport() != null ? tour.getAirport().getCode() : "N/A"));
            System.out.println("   📍 Días: " + tour.getDays().size());
        }
    }

    private static void adminSearchTour() {
        System.out.print("\n🔍 Nombre del tour: ");
        String nombre = scanner.nextLine();
        
        Tour tour = tourService.getTourByName(nombre);
        if (tour != null) {
            System.out.println("\n✅ Tour encontrado:");
            System.out.println("Nombre: " + tour.getTourName());
            System.out.println("Fecha: " + tour.getMonth() + " " + tour.getYear());
            System.out.println("Días: " + tour.getDays().size());
        } else {
            System.out.println("❌ Tour no encontrado");
        }
    }

    private static void adminDeleteTour() {
        System.out.print("\n🗑️  Nombre del tour: ");
        String nombre = scanner.nextLine();
        System.out.print("¿Confirmar? (si/no): ");
        if (scanner.nextLine().equalsIgnoreCase("si")) {
            tourService.deleteTour(nombre);
        }
    }

    private static void adminListAdvisors() {
        System.out.println("\n👥 TODOS LOS ADVISORS");
        System.out.println("-".repeat(50));
        
        List<Advisor> advisors = advisorService.getAllAdvisors();
        for (Advisor adv : advisors) {
            System.out.println("\n👤 " + adv.getUsername());
            System.out.println("   Nombre: " + adv.getFullName());
            System.out.println("   Email: " + adv.getEmail());
            System.out.println("   Puede agregar tours: " + (adv.isCanAddTours() ? "Sí" : "No"));
            System.out.println("   Videollamadas: " + adv.getVideoCalls().size());
            System.out.println("   Cotizaciones: " + adv.getQuotations().size());
        }
    }

    private static void adminCreateAdvisor() {
        System.out.println("\n➕ CREAR ADVISOR");
        System.out.println("-".repeat(50));
        
        Advisor advisor = new Advisor();
        System.out.print("Username: ");
        advisor.setUsername(scanner.nextLine());
        System.out.print("Email: ");
        advisor.setEmail(scanner.nextLine());
        System.out.print("Password: ");
        advisor.setPassword(scanner.nextLine());
        System.out.print("Nombre completo: ");
        advisor.setFullName(scanner.nextLine());
        System.out.print("Teléfono: ");
        advisor.setPhone(scanner.nextLine());
        System.out.print("Especialización (Weddings/Honeymoons/Business): ");
        advisor.setSpecialization(scanner.nextLine());
        
        advisorService.createAdvisor(advisor);
    }

    private static void adminGrantTourPermission() {
        System.out.print("\n✅ Username del advisor: ");
        String username = scanner.nextLine();
        advisorService.grantTourPermission(username);
    }

    private static void adminAssignVideoCall() {
        System.out.println("\n📞 ASIGNAR VIDEOLLAMADA");
        System.out.println("-".repeat(50));
        
        System.out.print("Username del advisor: ");
        String username = scanner.nextLine();
        
        Advisor.VideoCall call = new Advisor.VideoCall();
        System.out.print("Nombre del cliente: ");
        call.setClientName(scanner.nextLine());
        System.out.print("Email del cliente: ");
        call.setClientEmail(scanner.nextLine());
        System.out.print("Fecha (YYYY-MM-DD): ");
        call.setScheduledDate(scanner.nextLine());
        System.out.print("Hora (HH:MM): ");
        call.setScheduledTime(scanner.nextLine());
        System.out.print("Duración: ");
        call.setDuration(scanner.nextLine());
        System.out.print("Link de reunión: ");
        call.setMeetingLink(scanner.nextLine());
        
        advisorService.addVideoCall(username, call);
    }

    private static void adminCreateAdmin() {
        System.out.println("\n👑 CREAR ADMINISTRADOR");
        System.out.println("-".repeat(50));
        
        Admin admin = new Admin();
        System.out.print("Username: ");
        admin.setUsername(scanner.nextLine());
        System.out.print("Email: ");
        admin.setEmail(scanner.nextLine());
        System.out.print("Password: ");
        admin.setPassword(scanner.nextLine());
        System.out.print("Nombre completo: ");
        admin.setFullName(scanner.nextLine());
        System.out.print("Teléfono: ");
        admin.setPhone(scanner.nextLine());
        
        adminService.createAdmin(admin);
    }

    private static void adminListAdmins() {
        System.out.println("\n👑 ADMINISTRADORES");
        System.out.println("-".repeat(50));
        
        List<Admin> admins = adminService.getAllAdmins();
        for (Admin adm : admins) {
            System.out.println("\n👑 " + adm.getUsername());
            System.out.println("   Nombre: " + adm.getFullName());
            System.out.println("   Email: " + adm.getEmail());
            System.out.println("   Role: " + adm.getRole());
            System.out.println("   Activo: " + (adm.isActive() ? "Sí" : "No"));
        }
    }

    private static void adminViewAllBookings() {
        System.out.println("\n📊 TODAS LAS RESERVAS");
        System.out.println("-".repeat(50));
        
        List<BookingConfirmation> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No hay reservas");
            return;
        }

        for (BookingConfirmation booking : bookings) {
            System.out.println("\n🎫 " + booking.getClientBookingNumber());
            System.out.println("   Cliente: " + booking.getClientName());
            System.out.println("   Proveedor#: " + booking.getProviderBookingNumber());
            System.out.println("   Tipo: " + booking.getBookingType());
            System.out.println("   Total: $" + booking.getTotalCost());
            System.out.println("   Estado: " + booking.getStatus());
        }
    }

    private static void adminViewStats() {
        System.out.println("\n📈 ESTADÍSTICAS GENERALES");
        System.out.println("-".repeat(50));
        
        System.out.println("Tours: " + tourService.getAllTours().size());
        System.out.println("Advisors: " + advisorService.getAllAdvisors().size());
        System.out.println("Admins: " + adminService.getAllAdmins().size());
        System.out.println("Reservas: " + bookingService.getAllBookings().size());
    }

    // ========================================
    // MENÚ ADVISOR
    // ========================================
    private static void advisorMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("👨‍💼 PANEL ADVISOR: " + currentAdvisor.getFullName());
            System.out.println("=".repeat(50));
            System.out.println("VIDEOLLAMADAS:");
            System.out.println("  1. 📞 Ver mis videollamadas");
            System.out.println("  2. ➕ Agregar videollamada");
            System.out.println("  3. ✅ Actualizar estado de videollamada");
            System.out.println("\nCOTIZACIONES:");
            System.out.println("  4. 📝 Ver mis cotizaciones");
            System.out.println("  5. ➕ Crear cotización");
            System.out.println("  6. ✅ Actualizar estado de cotización");
            System.out.println("\nRESERVAS:");
            System.out.println("  7. 🎫 Crear reserva (Hotel+Vuelo)");
            System.out.println("  8. 🎫 Crear reserva (Hotel+Vuelo+Tour)");
            System.out.println("  9. 📋 Ver mis reservas");
            System.out.println(" 10. 🔍 Buscar reserva");
            
            if (currentAdvisor.isCanAddTours()) {
                System.out.println("\nTOURS (PERMISO CONCEDIDO):");
                System.out.println(" 11. ➕ Agregar tour");
                System.out.println(" 12. 📋 Ver tours");
            }
            
            System.out.println("\n  0. 🚪 Cerrar sesión");
            System.out.println("=".repeat(50));
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: advisorViewVideoCalls(); break;
                case 2: advisorAddVideoCall(); break;
                case 3: advisorUpdateVideoCall(); break;
                case 4: advisorViewQuotations(); break;
                case 5: advisorCreateQuotation(); break;
                case 6: advisorUpdateQuotation(); break;
                case 7: advisorCreateBooking("HOTEL_FLIGHT"); break;
                case 8: advisorCreateBooking("HOTEL_FLIGHT_TOUR"); break;
                case 9: advisorViewMyBookings(); break;
                case 10: advisorSearchBooking(); break;
                case 11: if (currentAdvisor.isCanAddTours()) adminCreateTour(); break;
                case 12: if (currentAdvisor.isCanAddTours()) adminListTours(); break;
                case 0: running = false; break;
                default: System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void advisorViewVideoCalls() {
        System.out.println("\n📞 MIS VIDEOLLAMADAS");
        System.out.println("-".repeat(50));
        
        List<Advisor.VideoCall> calls = currentAdvisor.getVideoCalls();
        if (calls.isEmpty()) {
            System.out.println("No tienes videollamadas");
            return;
        }

        for (Advisor.VideoCall call : calls) {
            System.out.println("\n📞 " + call.getCallId());
            System.out.println("   Cliente: " + call.getClientName());
            System.out.println("   Email: " + call.getClientEmail());
            System.out.println("   Fecha: " + call.getScheduledDate() + " " + call.getScheduledTime());
            System.out.println("   Estado: " + call.getStatus());
            if (call.getNotes() != null) {
                System.out.println("   Notas: " + call.getNotes());
            }
        }
    }

    private static void advisorAddVideoCall() {
        System.out.println("\n➕ AGREGAR VIDEOLLAMADA");
        System.out.println("-".repeat(50));
        
        Advisor.VideoCall call = new Advisor.VideoCall();
        System.out.print("Nombre del cliente: ");
        call.setClientName(scanner.nextLine());
        System.out.print("Email del cliente: ");
        call.setClientEmail(scanner.nextLine());
        System.out.print("Fecha (YYYY-MM-DD): ");
        call.setScheduledDate(scanner.nextLine());
        System.out.print("Hora (HH:MM): ");
        call.setScheduledTime(scanner.nextLine());
        System.out.print("Duración: ");
        call.setDuration(scanner.nextLine());
        System.out.print("Link de reunión: ");
        call.setMeetingLink(scanner.nextLine());
        
        advisorService.addVideoCall(currentAdvisor.getUsername(), call);
        currentAdvisor = advisorService.getAdvisorByUsername(currentAdvisor.getUsername());
    }

    private static void advisorUpdateVideoCall() {
        System.out.print("\n✅ ID de la videollamada: ");
        String callId = scanner.nextLine();
        System.out.println("Estado: COMPLETED / POSTPONED / NO_ANSWER");
        System.out.print("Nuevo estado: ");
        String status = scanner.nextLine();
        System.out.print("Notas/Comentarios: ");
        String notes = scanner.nextLine();
        
        advisorService.updateVideoCallStatus(currentAdvisor.getUsername(), callId, status, notes);
        currentAdvisor = advisorService.getAdvisorByUsername(currentAdvisor.getUsername());
    }

    private static void advisorViewQuotations() {
        System.out.println("\n📝 MIS COTIZACIONES");
        System.out.println("-".repeat(50));
        
        List<Advisor.Quotation> quotations = currentAdvisor.getQuotations();
        if (quotations.isEmpty()) {
            System.out.println("No tienes cotizaciones");
            return;
        }

        for (Advisor.Quotation quot : quotations) {
            System.out.println("\n📝 " + quot.getQuotationId());
            System.out.println("   Cliente: " + quot.getClientName());
            System.out.println("   Tipo: " + quot.getRequestType());
            System.out.println("   Destino: " + quot.getDetails().getDestination());
            System.out.println("   Costo estimado: $" + quot.getEstimatedCost());
            System.out.println("   Estado: " + quot.getStatus());
        }
    }

    private static void advisorCreateQuotation() {
        System.out.println("\n➕ CREAR COTIZACIÓN");
        System.out.println("-".repeat(50));
        
        Advisor.Quotation quot = new Advisor.Quotation();
        System.out.print("Nombre del cliente: ");
        quot.setClientName(scanner.nextLine());
        System.out.print("Email: ");
        quot.setClientEmail(scanner.nextLine());
        System.out.print("Teléfono: ");
        quot.setClientPhone(scanner.nextLine());
        
        System.out.println("Tipo: WEDDING / PROPOSAL / HONEYMOON / ANNIVERSARY / BACHELOR_PARTY / BIRTHDAY / CUSTOM_TRIP / BUSINESS_TRIP / GROUP_TRIP");
        System.out.print("Tipo de request: ");
        quot.setRequestType(scanner.nextLine());
        
        System.out.print("Destino: ");
        quot.getDetails().setDestination(scanner.nextLine());
        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        quot.getDetails().setStartDate(scanner.nextLine());
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        quot.getDetails().setEndDate(scanner.nextLine());
        System.out.print("Número de personas: ");
        quot.getDetails().setNumberOfPeople(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Necesita vuelo? (si/no): ");
        quot.getDetails().setNeedsFlight(scanner.nextLine().equalsIgnoreCase("si"));
        System.out.print("Necesita tours? (si/no): ");
        quot.getDetails().setNeedsTours(scanner.nextLine().equalsIgnoreCase("si"));
        System.out.print("Costo estimado: ");
        quot.setEstimatedCost(scanner.nextDouble());
        scanner.nextLine();
        
        advisorService.addQuotation(currentAdvisor.getUsername(), quot);
        currentAdvisor = advisorService.getAdvisorByUsername(currentAdvisor.getUsername());
    }

    private static void advisorUpdateQuotation() {
        System.out.print("\n✅ ID de la cotización: ");
        String quotId = scanner.nextLine();
        System.out.println("Estado: PENDING / IN_PROGRESS / SENT / ACCEPTED / REJECTED");
        System.out.print("Nuevo estado: ");
        String status = scanner.nextLine();
        
        advisorService.updateQuotationStatus(currentAdvisor.getUsername(), quotId, status);
        currentAdvisor = advisorService.getAdvisorByUsername(currentAdvisor.getUsername());
    }

    private static void advisorCreateBooking(String bookingType) {
        System.out.println("\n🎫 CREAR RESERVA - " + bookingType);
        System.out.println("-".repeat(50));
        
        BookingConfirmation booking = new BookingConfirmation();
        booking.setBookingType(bookingType);
        booking.setAdvisorId(currentAdvisor.getAdvisorId());
        
        System.out.print("Nombre del cliente: ");
        booking.setClientName(scanner.nextLine());
        System.out.print("Email del cliente: ");
        booking.setClientEmail(scanner.nextLine());
        System.out.print("Número de reserva con proveedor: ");
        booking.setProviderBookingNumber(scanner.nextLine());
        
        // Hotel
        BookingConfirmation.HotelDetails hotel = new BookingConfirmation.HotelDetails();
        System.out.println("\n--- HOTEL ---");
        System.out.print("Nombre del hotel: ");
        hotel.setHotelName(scanner.nextLine());
        System.out.print("Check-in (YYYY-MM-DD): ");