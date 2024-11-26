import java.util.ArrayList;
import java.util.List;

public class Member {
    private String firstName;
    private String lastName;
    private String libraryId;
    private String password;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String firstName, String lastName, String libraryId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.libraryId = libraryId;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBook(Book book) {
        this.borrowedBooks.add(book);
    }

    public void removeBook(Book book) {
        this.borrowedBooks.remove(book);
    }
}
