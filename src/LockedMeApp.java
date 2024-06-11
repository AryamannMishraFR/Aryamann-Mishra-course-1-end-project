import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LockedMeApp {
    private static final String ROOT_DIR = "root_directory";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // This is to ensure that the root directory exists.
        File rootDir = new File(ROOT_DIR);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        while (running) {
            System.out.println("Welcome to LockedMe.com");
            System.out.println("Developer: Aryamann Mishra");
            System.out.println("1. Retrieve files in ascending order");
            System.out.println("2. Business Operations");
            System.out.println("3. Close Application");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    retrieveFiles();
                    break;
                case 2:
                    businessOperations(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private static void retrieveFiles() {
        File dir = new File(ROOT_DIR);
        String[] filesArray = dir.list(); // This gets the list of file names in the directory
        if (filesArray == null || filesArray.length == 0) {
            System.out.println("No files found.");
        } else {
            ArrayList<String> filesList = new ArrayList<>(Arrays.asList(filesArray));
            Collections.sort(filesList); // This sorts the list of file names in ascending order
            System.out.println("Files in ascending order:");
            for (String file : filesList) {
                System.out.println(file);
            }
        }
    }

    private static void businessOperations(Scanner scanner) {
        boolean inOperationsMenu = true;
        while (inOperationsMenu) {
            System.out.println("Business Operations:");
            System.out.println("1. Add a file");
            System.out.println("2. Delete a file");
            System.out.println("3. Search for a file");
            System.out.println("4. Return to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name to add: ");
                    String addFileName = scanner.nextLine();
                    addFile(addFileName);
                    break;
                case 2:
                    System.out.print("Enter file name to delete: ");
                    String deleteFileName = scanner.nextLine();
                    deleteFile(deleteFileName);
                    break;
                case 3:
                    System.out.print("Enter file name to search: ");
                    String searchFileName = scanner.nextLine();
                    searchFile(searchFileName);
                    break;
                case 4:
                    inOperationsMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addFile(String fileName) {
        File file = new File(ROOT_DIR, fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File added successfully: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while adding the file.");
            e.printStackTrace();
        }
    }

    private static void deleteFile(String fileName) {
        File file = new File(ROOT_DIR, fileName);
        if (file.exists() && file.delete()) {
            System.out.println("File deleted successfully: " + fileName);
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    private static void searchFile(String fileName) {
        File file = new File(ROOT_DIR, fileName);
        if (file.exists()) {
            System.out.println("File found: " + fileName);
        } else {
            System.out.println("File not found: " + fileName);
        }
    }
}
