// Examination.java
package myapplication.pages.CheckUps;

public class Examination {
    private int id;
    private String name;
    private int checkupId;

    public Examination(int id, String name, int checkupId) {
        this.id = id;
        this.name = name;
        this.checkupId = checkupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCheckupId() {
        return checkupId;
    }
}
