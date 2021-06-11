package Classes;

public class Promiser {
    private int id;
    private int User_Pasport;
    private String title;
    private String author;

    public Promiser(int id, int user_Pasport, String title, String author) {
        this.id = id;
        User_Pasport = user_Pasport;
        this.title = title;
        this.author = author;
    }
    public Promiser() {
        this.id = 0;
        User_Pasport = 0;
        this.title = "title";
        this.author = "author";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_Pasport() {
        return User_Pasport;
    }

    public void setUser_Pasport(int user_Pasport) {
        User_Pasport = user_Pasport;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
