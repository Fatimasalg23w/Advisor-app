public class Advisor {
    private String id;
    private String username;
    private String name;

    public Advisor(String id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getName() { return name; }
}