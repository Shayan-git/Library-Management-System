import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private Scanner scanner = new Scanner(System.in);
    private List<Book> booksList = new ArrayList<>();
    private List<Member> membersList = new ArrayList<>();
    private List<Librarian> librariansList = new ArrayList<>();

    public void mainMenu() {
//        * * * * * * * * * * * * * * * * * * *
//        Main Librarian
        Librarian l = new Librarian("l", "l", "l", "l");
        librariansList.add(l);
//        * * * * * * * * * * * * * * * * * * *

        boolean key = true;
        while (key) {
            System.out.print("Main Menu\n1.Member Menu\n2.Librarian Menu\n3.Exit\nEnter your choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1":
                    memberMenu();
                    break;
                case "2":
                    librarianMenu();
                    break;
                case "3":
                    key = false;
                    break;
                default:
                    System.out.println("Invalid!\n");
            }
        }
    }

//    Member
    private void memberMenu() {
        boolean key = true;
        while (key) {
            System.out.print("Member Menu\n1.Sign In\n2.Sign Up\n3.Back To Main Menu\nEnter your choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1":
                    memberSignIn();
                    break;
                case "2":
                    memberSignUp();
                    break;
                case "3":
                    key = false;
                    break;
                default:
                    System.out.println("Invalid!\n");
            }
        }
    }

    private void memberSignUp() {
        while (true) {
            boolean key = false;

            System.out.println("Member Sign Up");
            System.out.print("First name[Back to Menu = 0]: ");
            String firstName = scanner.nextLine();
            System.out.print("Last name[Back to Menu = 0]: ");
            String lastName = scanner.nextLine();
            System.out.print("Library id(MUST BE UNIQUE)[Back to Menu = 0]: ");
            String libraryId = scanner.nextLine();
            System.out.print("Password[Back to Menu = 0]: ");
            String password = scanner.nextLine();
            System.out.println();

            if (firstName.equals("0") || lastName.equals("0") || libraryId.equals("0") || password.equals("0"))
                break;

            if (firstName.length() == 0 || lastName.length() == 0 || libraryId.length() == 0 || password.length() == 0) {
                System.out.println("Invalid or given!\n");
                continue;
            }

            for (Member member : membersList) {
                if (libraryId.equals(member.getLibraryId())) {
                    key = true;
                    break;
                }
            }

            if (key)
                System.out.println("Invalid or given!\n");
            else {
                membersList.add(new Member(firstName, lastName, libraryId, password));
                System.out.println("Done!\nWelcome to library!\n");
                break;
            }
        }
    }

    private void memberSignIn() {
        while (true) {
            boolean key = false;

            System.out.println("Member Sign In");
            System.out.print("Library id[Back to Menu = 0]: ");
            String libraryId = scanner.nextLine();
            System.out.print("Password[Back to Menu = 0]: ");
            String password = scanner.nextLine();
            System.out.println();

            if (libraryId.equals("0") || password.equals("0"))
                break;

            if (libraryId.length() == 0 || password.length() == 0 || membersList.size() == 0) {
                System.out.println("Invalid or does not exist!\n");
                continue;
            }

            for (Member member : membersList) {
                if (libraryId.equals(member.getLibraryId()) && password.equals(member.getPassword())) {
                    memberChoicesMenu(member);
                    key = true;
                    break;
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }

    private void memberChoicesMenu(Member member) {
        boolean key = true;
        while (key) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
            LocalDateTime dateTimeNow = LocalDateTime.now();
            String dateTime = dateTimeNow.format(dateTimeFormatter);

            System.out.print("Welcome " + member.getFirstName() + " " + member.getLastName() +
                    "\nDate & Time: " + dateTime +
                    "\nMember Choices Menu\n1.Show All Books\n2.Show Borrowed Books\n3.Borrow A Book" +
                    "\n4.Borrow Back A Book\n5.Back To Member Menu\nEnter your choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1":
                    showAllBooks();
                    System.out.println();
                    break;
                case "2":
                    showBorrowedBooks(member);
                    System.out.println();
                    break;
                case "3":
                    Borrow.borrowBook(member, booksList);
                    break;
                case "4":
                    Borrow.borrowBackBook(member);
                    break;
                case "5":
                    key = false;
                    break;
                default:
                    System.out.println("Invalid!\n");
            }
        }
    }

//    Librarian
    private void librarianMenu() {
        boolean key = true;
        while (key) {
            System.out.print("Librarian Menu\n1.Sign In\n2.Back To Main Menu\nEnter your choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1":
                    librarianSignIn();
                    break;
                case "2":
                    key = false;
                    break;
                default:
                    System.out.println("Invalid!\n");
            }
        }
    }

    private void librarianSignIn() {
        while (true) {
            boolean key = false;

            System.out.println("Librarian Sign In");
            System.out.print("Library id[Back to Menu = 0]: ");
            String libraryId = scanner.nextLine();
            System.out.print("Password[Back to Menu = 0]: ");
            String password = scanner.nextLine();
            System.out.println();

            if (libraryId.equals("0") || password.equals("0"))
                break;

            if (libraryId.length() == 0 || password.length() == 0 || librariansList.size() == 0) {
                System.out.println("Invalid or does not exist!\n");
                continue;
            }

            for (Librarian librarian : librariansList) {
                if (libraryId.equals(librarian.getLibraryId()) && password.equals(librarian.getPassword())) {
                    librarianChoicesMenu(librarian);
                    key = true;
                    break;
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }

    private void librarianChoicesMenu(Librarian librarian) {
        boolean key = true;
        while (key) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
            LocalDateTime dateTimeNow = LocalDateTime.now();
            String dateTime = dateTimeNow.format(dateTimeFormatter);

            System.out.print("Welcome " + librarian.getFirstName() + " " + librarian.getLastName() +
                    "\nDate & Time: " + dateTime +
                    "\nLibrarian Choices Menu\n1.Show All Books\n2.Show Borrowed Books\n3.Borrow A Book" +
                    "\n4.Borrow Back A Book\n5.Show All Members\n6.Show All Librarians\n7.Add A Librarian" +
                    "\n8.Remove A Librarian\n9.Add A Member\n10.Remove A Member\n11.Add A Book" +
                    "\n12.Remove A Book\n13.Show Borrowed Information\n14.Back To Librarian Menu\nEnter your choice: ");
            String choice = scanner.nextLine();
            System.out.println();
            switch (choice) {
                case "1":
                    showAllBooks();
                    System.out.println();
                    break;
                case "2":
                    showBorrowedBooks(librarian);
                    System.out.println();
                    break;
                case "3":
                    Borrow.borrowBook(librarian, booksList);
                    break;
                case "4":
                    Borrow.borrowBackBook(librarian);
                    break;
                case "5":
                    showAllMembers();
                    break;
                case "6":
                    showAllLibrarians();
                    break;
                case "7":
                    librarianSignUp();
                    break;
                case "8":
                    removeLibrarian();
                    break;
                case "9":
                    memberSignUp();
                    break;
                case "10":
                    removeMember();
                    break;
                case "11":
                    addBookToLibrary();
                    break;
                case "12":
                    removeBookFromLibrary();
                    break;
                case "13":
                    showBorrowedInformation();
                    break;
                case "14":
                    key = false;
                    break;
                default:
                    System.out.println("Invalid!\n");
            }
        }
    }

    private void showAllMembers() {
        System.out.println("Show All Members(First name/Last name/Library id/Password/Show Borrowed Books):\n");
        if (membersList.size() == 0) {
            System.out.println("Nothing!");
            return;
        }

        for (Member member : membersList) {
            System.out.println("First name: " + member.getFirstName());
            System.out.println("Last name: " + member.getLastName());
            System.out.println("Library id: " + member.getLibraryId());
            System.out.println("Password: " + member.getPassword());
            showBorrowedBooks(member);
            System.out.println();
        }
    }

    private void showAllLibrarians() {
        System.out.println("Show All Librarians(First name/Last name/Library id/Password/Show Borrowed Books):\n");
        if (librariansList.size() == 0) {
            System.out.println("Nothing!");
            return;
        }

        for (Librarian librarian : librariansList) {
            System.out.println("First name: " + librarian.getFirstName());
            System.out.println("Last name: " + librarian.getLastName());
            System.out.println("Library id: " + librarian.getLibraryId());
            System.out.println("Password: " + librarian.getPassword());
            showBorrowedBooks(librarian);
            System.out.println();
        }
    }

    private void librarianSignUp() {
        while (true) {
            boolean key = false;

            System.out.println("Librarian Sign Up");
            System.out.print("First name[Back to Menu = 0]: ");
            String firstName = scanner.nextLine();
            System.out.print("Last name[Back to Menu = 0]: ");
            String lastName = scanner.nextLine();
            System.out.print("Library id(MUST BE UNIQUE)[Back to Menu = 0]: ");
            String libraryId = scanner.nextLine();
            System.out.print("Password[Back to Menu = 0]: ");
            String password = scanner.nextLine();
            System.out.println();

            if (firstName.equals("0") || lastName.equals("0") || libraryId.equals("0") || password.equals("0"))
                break;

            if (firstName.length() == 0 || lastName.length() == 0 || libraryId.length() == 0 || password.length() == 0) {
                System.out.println("Invalid or given!\n");
                continue;
            }

            for (Librarian librarian : librariansList) {
                if (libraryId.equals(librarian.getLibraryId())) {
                    key = true;
                    break;
                }
            }

            if (key)
                System.out.println("Invalid or given!\n");
            else {
                librariansList.add(new Librarian(firstName, lastName, libraryId, password));
                System.out.println("Done!\nWelcome to library!\n");
                break;
            }
        }
    }

    private void removeLibrarian() {
        while (true) {
            boolean key = false;

            System.out.println("Remove Librarian");
            System.out.print("Library id[Back to Menu = 0]: ");
            String libraryId = scanner.nextLine();
            System.out.print("Password[Back to Menu = 0]: ");
            String password = scanner.nextLine();
            System.out.println();

            if (libraryId.equals("0") || password.equals("0"))
                break;

            if (libraryId.length() == 0 || password.length() == 0 || librariansList.size() <= 1) {
                System.out.println("Invalid or does not exist!\n");
                continue;
            }

            for (int i = 0; i < librariansList.size(); i++) {
                Librarian librarian = librariansList.get(i);
                if (libraryId.equals(librarian.getLibraryId()) && password.equals(librarian.getPassword())) {
                    librariansList.remove(librarian);
                    key = true;
                    System.out.println(librarian.getFirstName() + " " + librarian.getLastName() + " removed!\n");
                    break;
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }

    private void removeMember() {
        while (true) {
            boolean key = false;

            System.out.println("Remove Member");
            System.out.print("Library id[Back to Menu = 0]: ");
            String libraryId = scanner.nextLine();
            System.out.print("Password[Back to Menu = 0]: ");
            String password = scanner.nextLine();
            System.out.println();

            if (libraryId.equals("0") || password.equals("0"))
                break;

            if (libraryId.length() == 0 || password.length() == 0 || membersList.size() == 0) {
                System.out.println("Invalid or does not exist!\n");
                continue;
            }

            for (int i = 0; i < membersList.size(); i++) {
                Member member = membersList.get(i);
                if (libraryId.equals(member.getLibraryId()) && password.equals(member.getPassword())) {
                    membersList.remove(member);
                    key = true;
                    System.out.println(member.getFirstName() + " " + member.getLastName() + " removed!\n");
                    break;
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }

    private void addBookToLibrary() {
        while (true) {
            boolean key = false;

            System.out.println("Add A Book");
            System.out.print("Name[Back to Menu = 0]: ");
            String name = scanner.nextLine();
            System.out.print("ISBN[Back to Menu = 0]: ");
            String isbn = scanner.nextLine();
            System.out.println();

            if (name.equals("0") || isbn.equals("0"))
                break;

            if (name.length() == 0 || isbn.length() == 0) {
                System.out.println("Invalid or exists!\n");
                continue;
            }

            for (Book book : booksList) {
                if (isbn.equals(book.getIsbn())) {
                    key = true;
                    break;
                }
            }

            if (key)
                System.out.println("Invalid or exists!\n");
            else {
                booksList.add(new Book(name, isbn, true));
                System.out.println(name + " is added to library!\n");
                break;
            }
        }
    }

    private void removeBookFromLibrary() {
        while (true) {
            boolean key = false;

            System.out.println("Remove A Book");
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

            for (int i = 0; i < booksList.size(); i++) {
                Book book = booksList.get(i);
                if (name.equals(book.getName()) && isbn.equals(book.getIsbn())) {
                    booksList.remove(book);
                    key = true;
                    System.out.println(name + " is removed from library!\n");
                    break;
                }
            }

            if (key)
                break;
            else
                System.out.println("Invalid or does not exist!\n");
        }
    }

//    Common
    private void showAllBooks() {
        System.out.println("Show All Books(Name/ISBN/Availability):");
        if (booksList.size() == 0) {
            System.out.println("Nothing!");
            return;
        }

        for (Book book : booksList) {
            System.out.print(book.getName() + "  " + book.getIsbn() + "  ");
            if (book.isAvailable())
                System.out.println("Available");
            else
                System.out.println("Not Available");
        }
    }

    private void showBorrowedBooks(Member member) {
        System.out.println("Show Borrowed Books(Name/ISBN):");
        if (member.getBorrowedBooks().size() == 0) {
            System.out.println("Nothing!");
            return;
        }

        for (Book book : member.getBorrowedBooks()) {
            System.out.println(book.getName() + "  " + book.getIsbn());
        }
    }

    private void showBorrowedInformation() {
        List<Borrow> borrowedList = Borrow.getAllPeopleWhoBorrowed();

        System.out.println("Show Borrowed Information:\n");
        if (borrowedList.size() == 0) {
            System.out.println("Nothing!");
            return;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        for (Borrow borrow : borrowedList) {
            System.out.print("First name/Family name/Library id: ");
            System.out.println(borrow.getMember().getFirstName() + " " + borrow.getMember().getLastName() + " "  +
                    borrow.getMember().getLibraryId());
            System.out.print("Book name/ISBN: ");
            System.out.println(borrow.getBook().getName() + " " + borrow.getBook().getIsbn());
            System.out.print("Borrow Date Time: ");
            LocalDateTime borrowDateTime = borrow.getBorrowDateTime();
            String dateTime1 = borrowDateTime.format(dateTimeFormatter);
            System.out.println(dateTime1);
            if (borrow.getBorrowBackDateTime() != null) {
                System.out.print("Borrow Back Date Time: ");
                LocalDateTime borrowBackDateTime = borrow.getBorrowBackDateTime();
                String dateTime2 = borrowBackDateTime.format(dateTimeFormatter);
                System.out.println(dateTime2);
            }
            System.out.println();
        }
    }
}
