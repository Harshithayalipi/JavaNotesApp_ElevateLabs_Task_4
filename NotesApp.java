import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n*** WELCOME TO NOTES APP ***");
            System.out.println("1. 'FILE WRITER' - Add a Note");
            System.out.println("2. 'FILE READER' - Reads the Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting the Notes App.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again,choose between 1-3.");
            }

        } while (choice != 3);

        scanner.close();
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note here: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(note + System.lineSeparator());
            System.out.println("Note has been added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note.");
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        System.out.println("\n*** Your Notes ***");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasContent = false;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasContent = true;
            }

            if (!hasContent) {
                System.out.println("No notes found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add a note first.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the notes.");
            e.printStackTrace();
        }
    }
}

