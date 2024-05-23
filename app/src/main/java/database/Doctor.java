package database;

public class Doctor {

    private long id;
    private String name;
    private String location;
    private String availability;
    private String contact;
    private String email;

    public Doctor() {
    }

    public Doctor(String name, String location, String availability, String contact, String email) {
        this.name = name;
        this.location = location;
        this.availability = availability;
        this.contact = contact;
        this.email = email;
    }

    // Getters and setters for each field
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
