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

    public String getId() { return id; }
    public String getClientId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}