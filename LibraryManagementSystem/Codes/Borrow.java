import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Borrow {
    private static Scanner scanner = new Scanner(System.in);
    private Member member;
    private Book book;
    private LocalDateTime borrowDateTime;
    private LocalDateTime borrowBackDateTime;

    private static List<Borrow> allPeopleWhoBorrowed = new ArrayList<>();

    public Borrow(Member member, Book book, LocalDateTime borrowDateTime) {
        this.member = member;
        this.book = book;
        this.borrowDateTime = borrowDateTime;
        this.borrowBackDateTime = null;
    }

    private void setBorrowBackDateTime(LocalDateTime borrowBackDateTime) {
        this.borrowBackDateTime = borrowBackDateTime;
    }

    public static List<Borrow> getAllPeopleWhoBorrowed() {
        return allPeopleWhoBorrowed;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getBorrowDateTime() {
        return borrowDateTime;
    }

    public LocalDateTime getBorrowBackDateTime() {
        return borrowBackDateTime;
    }

    public static void borrowBook(Member member, List<Book> booksList) {
        while (true) {
            boolean key = false;

            System.out.println("Borrow Book");
            System.out.print("Name[Back to Menu = 0]: ");
            String name = scanner.nextLine();
            System.out.print("ISBN[Back to Menu = 0]: ");
            String isbn = scanner.nextLine();
            System.out.println();

            if (name.equals("0") || isbn.equals("0"))
                break;

            if (name.length() == 0 || isbn.length() == 0 || booksList.size() == 0) {
                System.out.println("Invalid or does not exist!\n");
                continue;
            }

            for (Book book : booksList) {
                if (name.equals(book.getName()) && isbn.equals(book.getIsbn())) {
                    if (book.isAvailable()) {
                        book.setAvailable(false);
                        member.addBook(book);
                        allPeopleWhoBorrowed.add(new Borrow(member, book, LocalDateTime.now()));
                        key = true;
                        System.out.println("Now you are having " + book.getName() + "!\n");
                        break;
                    }
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }

    public static void borrowBackBook(Member member) {
        while (true) {
            boolean key = false;

            System.out.println("Borrow Back Book");
            System.out.print("Name[Back to Menu = 0]: ");
            String name = scanner.nextLine();
            System.out.print("ISBN[Back to Menu = 0]: ");
            String isbn = scanner.nextLine();
            System.out.println();

            if (name.equals("0") || isbn.equals("0"))
                break;

            if (name.length() == 0 || isbn.length() == 0) {
                System.out.println("Invalid or does not exist!\n");
                continue;
            }

            for (int i = 0; i < member.getBorrowedBooks().size(); i++) {
                Book book = member.getBorrowedBooks().get(i);
                if (name.equals(book.getName()) && isbn.equals(book.getIsbn())) {
                    book.setAvailable(true);
                    member.removeBook(book);

                    for (Borrow borrow : allPeopleWhoBorrowed) {
                        if (borrow.member == member && borrow.book == book && borrow.borrowBackDateTime == null) {
                            borrow.setBorrowBackDateTime(LocalDateTime.now());
                            break;
                        }
                    }

                    key = true;
                    System.out.println("Now you are not having " + book.getName() + "!\n");
                    break;
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }
}
