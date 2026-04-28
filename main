import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseManager db = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);

        db.connect();

        while (true) {

            System.out.println("\n=== MUSIC DATABASE CLI ===");
            System.out.println("1. Login");
            System.out.println("2. Create User");
            System.out.println("3. Delete User");
            System.out.println("4. View Followed Artists");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Username: ");
                    String user = scanner.nextLine();

                    System.out.print("Password: ");
                    String pass = scanner.nextLine();

                    boolean valid = LoginValidation.validateCredentials(user, pass, db);
                    System.out.println(valid ? "Login successful" : "Login failed");
                    break;

                case 2:
                    System.out.print("Username: ");
                    String newUser = scanner.nextLine();

                    System.out.print("Password: ");
                    String newPass = scanner.nextLine();

                    boolean created = NewUserCreation.createNewUser(newUser, newPass, db);
                    System.out.println(created ? "User created" : "User already exists");
                    break;

                case 3:
                    System.out.print("Username: ");
                    String delUser = scanner.nextLine();

                    UserDeletion.deleteUser(delUser, db);
                    System.out.println("User deleted (if existed)");
                    break;

                case 4:
                    System.out.print("User ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    FollowedArtists fa = new FollowedArtists();
                    System.out.println(fa.followedArtists(id, db));
                    break;

                case 5:
                    db.disconnect();
                    System.out.println("Goodbye");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
