import java.io.*;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StorageManager {
    // Static private members
    private static StorageTable StorageTable = new StorageTable();

    // Getter for StorageTable
    /**
     * This is a method that returns the StorageTable used in the program.
     *
     * @return
     * Returns the StorageTable used in the program.
     */
    public static StorageTable getStorageTable() {
        return StorageTable;
    }

    // Static Functions
    /**
     * This is a method that serves as the main program, where the user can
     * manage all of the Storage objects they currently house. The user can
     * save their workspace by quitting and pressing the "Q" button. By saving,
     * the user can rerun the program and work from where they left off. Or the
     * user can quit and delete their current workspace altogether by pressing
     * the button "X". A user can print all of their Storage objects, add,
     * remove, find a specific Storage box with an ID, and select all Storage
     * boxes that are owned by the same client.
     */
    public static void main (String[] arts) {
        // Check if storage.obj exists
            // If it does, then the file should be loaded and deserialized into a StorageTable
            // If not, then create a new StorageTable object and use it
        // Interact with the menu
        // Once you quit, the StorageTable should be serialized into a file called "storage.obj"
        // Click 'X' to delete the "storage.obj" file if it exists

/*        System.out.println("\nBox#          Contents                       Owner");
        System.out.println("----------------------------------------------------------------");

        String tableEntry1 = String.format("%-12d%-20s", 1, "2006 Dell Workstations");
//        tableEntry1 = tableEntry1 + String.format("%-50s", "SBU CS Department");
        tableEntry1 = tableEntry1 + "\t\t\tSBU CS Department";
        String tableEntry2 = String.format("%-12d%-20s", 4, "Unreturned Exams");
//        tableEntry2 = tableEntry2 + String.format("%-50s", "214 TAs");
        tableEntry2 = tableEntry2 + "\t\t\t214 TAs";
        String tableEntry3 = String.format("%-12d%-20s", 11, "Dashed Hopes and Dreams        ");
//        tableEntry3 = tableEntry3 + String.format("%-50s", "SBU CS Department");
        tableEntry3 = tableEntry3 + "\t\t\tSBU CS Department";
        String tableEntry4 = String.format("%-12d%-20s", 12, "Spare Null Pointer Exceptions        ");
//        tableEntry4 = tableEntry4 + String.format("%-50s", "214 TAs");
        tableEntry4 = tableEntry4 + "\t\t\t214 TAs";


        System.out.println(tableEntry1);
        System.out.println(tableEntry2);
        System.out.println(tableEntry3);
        System.out.println(tableEntry4);*/


        System.out.println("\nHello, and welcome to Rocky Stream Storage Manager");

        // StorageTable object
        StorageTable actualST = getStorageTable();

        // Reading in storage.obj file
        try {
            FileInputStream inputFile = new FileInputStream("storage.obj");
            ObjectInputStream inStream = new ObjectInputStream(inputFile);

            actualST = (StorageTable) inStream.readObject();
            inStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("'storage.obj' file does not exist! A new StorageTable will be created!");
        } catch (IOException e) {
            System.out.println("OutputInputStream could not load properly! Error!");
        } catch (ClassNotFoundException e) {
            System.out.println("'readObject()' could not load properly! Error!");
        }

        // Scanner object
        Scanner stdin = new Scanner(System.in);

        // Menu variables
        boolean menuBool = true;
        char menuCharOption = ' ';
        int inputId;
        String inputClient = "", inputContent = "";

        while (menuBool) {
            try {
                String menuOption = "";

                // Menu
                System.out.println("\nP - Print all storage boxes");
                System.out.println("A - Insert into storage box");
                System.out.println("R - Remove contents from a storage box");
                System.out.println("C - Select all boxes owned by a particular client");
                System.out.println("F - Find a box by ID and display its owner and contents");
                System.out.println("Q - Quit and save workspace");
                System.out.println("X - Quit and delete workspace");

                System.out.println("\nPlease select an option: ");

                while (menuOption.equals("")) {
                    // Converting menu option to upper case everytime
                    menuOption = stdin.nextLine().toUpperCase();
                }
                if (menuOption.length() != 1) {
                    System.out.println("Invalid menu option! Try again!\n");
                } else {
                    menuCharOption = menuOption.charAt(0);
                }

                switch (menuCharOption) {
                    case 'P':
                        Set<Map.Entry<Integer, Storage>> allMappings = actualST.entrySet();

                        System.out.println("\nBox#          Contents                       Owner");
                        System.out.println("----------------------------------------------------------------");
                        for (Map.Entry<Integer, Storage> i : allMappings) {
                            String tableEntry = String.format("%-12d%-15s", i.getKey(), i.getValue().getContent());
                            tableEntry = tableEntry + "\t\t\t" + i.getValue().getClient();
                            System.out.println(tableEntry);
                        }

                        System.out.println();

                        break;

                    case 'A':
                        Storage newStorage = new Storage();

                        System.out.println("Please enter id: ");
                        inputId = stdin.nextInt();
                        inputClient = "";
                        System.out.println("Please enter client: ");
                        while (inputClient.equals("")) {
                            inputClient = stdin.nextLine();
                        }
//                        inputClient = stdin.nextLine();
                        System.out.println("Please enter contents: ");
                        inputContent = stdin.nextLine();

                        // Updating newStorage
                        newStorage.setId(inputId);
                        newStorage.setClient(inputClient);
                        newStorage.setContent(inputContent);

                        // Putting newStorage into the StorageTable
                        actualST.putStorage(inputId, newStorage);

                        System.out.println("\nstorage " + newStorage.getId() + " set!");

                        break;

                    case 'R':
                        System.out.println("Please select ID: ");
                        inputId = stdin.nextInt();

                        // Removing the element from the table given the id
                        actualST.remove(inputId);

                        System.out.println("Box " + inputId + " is now removed.");
                        break;

                    case 'C':
                        System.out.println("Please enter the name of the client: ");
                        inputClient = stdin.nextLine();

                        // Prints client storage value if it exists
                        actualST.printClientValue(inputClient);

                        break;

                    case 'F':
                        System.out.println("Please enter ID: ");
                        inputId = stdin.nextInt();

                        Storage retrievedStorage = actualST.getStorage(inputId);

                        System.out.println("\nBox " + retrievedStorage.getId());
                        System.out.println("Contents: " + retrievedStorage.getContent());
                        System.out.println("Owner: " + retrievedStorage.getClient());

                        break;

                    case 'Q':
                        try {
                          FileOutputStream outputFile = new FileOutputStream("storage.obj");
                          ObjectOutputStream outStream = new ObjectOutputStream(outputFile);

                          // saving StorageTable onto storage.obj file
                          outStream.writeObject(actualST);
                          outStream.close();

                          System.out.println("Storage Manager is quitting, current storage is saved for next session.");

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException("'storage.obj' file does not exist!");
                        } catch (IOException e) {
                            throw new RuntimeException("OutputOutputStream could not load properly! Error");
                        }

                        menuBool = false;

                        break;

                    case 'X':
                        // Delete storage.obj file if it exists
                            // If storage.obj file does not exist, then throw error
                        // Then do not serialize StorageTable into a file (don't save StorageTable!)

                        File outputObjectFile = new File("storage.obj");

                        // If storage.obj exists
                        if (outputObjectFile.exists()) {
                            // Delete storage.obj
                            outputObjectFile.delete();

                            System.out.println("Storage Manager is quitting, all data is being erased.");
                        } else {
                            System.out.println("You cannot delete the workspace if the workspace has not been saved yet! Try again!");
                        }

                        menuBool = false;

                        break;

                    default:
                        System.out.println("Wrong character! Try again!\n");
                        break;
                }
            } catch (InputMismatchException x) {
                System.out.println("Invalid input. Try again!\n");
                stdin.nextLine();
            }
        }
    }

}