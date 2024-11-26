public class Book {
    private String name;
    private String isbn;
    private boolean available;

    public Book(String name, String isbn, boolean available) {
        this.name = name;
        this.isbn = isbn;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
