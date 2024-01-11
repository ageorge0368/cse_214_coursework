
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the NeoViewer object, which is a class that is used as
 * the main function to represent how a NeoDatabase object is used. The main
 * function utilizes a menu, where a page can be added to the database, where
 * the database can be sorted (by referenceID, approach date, miss distance,
 * and diameter), and print the database as a neat table.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */

public class NeoViewer {


/**
     * This method represents the main function, where the functionality from
     * above is carried out.
     */

    public static void main (String[] args) {

//        NeoDatabase newDB = new NeoDatabase();

/*        newDB.addAll("https://api.nasa.gov/neo/rest/v1/neo/browse?page=0&api_key=HcpkwUesG8VtmnQ53EP1XoqCWmzgXjncx1u7aaWO");

//        newDB.printAll();
        newDB.printTable();

        DiameterComparator sortDiameter = new DiameterComparator();

        newDB.sort(sortDiameter);

//        newDB.printAll();
        newDB.printTable();*/


        System.out.println("\nWelcome to NEO Viewer!");

        // NEO Database
        NeoDatabase newDB = new NeoDatabase();

        Scanner stdin = new Scanner(System.in);

        // Menu Variables
        boolean menuBool = true;
        char menuCharOption = ' ';

        while (menuBool) {
            try {
                String menuOption = "";

                // Menu
                System.out.println("\nOption menu: ");
                System.out.println("\tA) Add a page to the database");
                System.out.println("\tS) Sort the database");
                System.out.println("\tP) Print the database as a table");
                System.out.println("\tQ) Quit");

                System.out.println("\nSelect a menu option: ");

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
                    case 'A':
                        int pageNum;
                        System.out.println("Enter the page to load: ");
                        pageNum = stdin.nextInt();
                        try {
                            String newQueryURL = newDB.buildQueryURL(pageNum);
                            newDB.addAll(newQueryURL);
                        } catch (IllegalArgumentException x) {
                            System.out.println("Error! Problem with .buildQuery() or with .addAll()!");
                        }

                        System.out.println("Page loaded successfully!");
                        break;

                    case 'S':
                        // Sorting Menu
                        String sortMenuOption = "";
                        char sortMenuOptionChar = ' ';


                        System.out.println("\nR) Sort by referenceID");
                        System.out.println("D) Sort by diameter");
                        System.out.println("A) Sort by approach date");
                        System.out.println("M) Sort by miss distance");

                        System.out.println("\nSelect a menu option: ");

                        while (sortMenuOption.equals("")) {
                            // Converting menu option to upper case everytime
                            sortMenuOption = stdin.nextLine().toUpperCase();
                        }
                        if (sortMenuOption.length() != 1) {
                            System.out.println("Invalid menu option! Try again!\n");
                        } else {
                            sortMenuOptionChar = sortMenuOption.charAt(0);
                        }
                        try {
                            switch (sortMenuOptionChar) {
                                case 'R':
                                    ReferenceIDComparator sortReferenceID = new ReferenceIDComparator();
                                    newDB.sort(sortReferenceID);

                                    System.out.println("Table sorted on referenceID.");
                                    break;

                                case 'D':
                                    DiameterComparator sortDiameter = new DiameterComparator();
                                    newDB.sort(sortDiameter);

                                    System.out.println("Table sorted on diameter.");
                                    break;

                                case 'A':
                                    ApproachDateComparator sortApproachDate = new ApproachDateComparator();
                                    newDB.sort(sortApproachDate);

                                    System.out.println("Table sorted on approach date.");
                                    break;

                                case 'M':
                                    MissDistanceComparator sortMissDistance = new MissDistanceComparator();
                                    newDB.sort(sortMissDistance);

                                    System.out.println("Table sorted on miss distance.");
                                    break;

                                default:
                                    System.out.println("Wrong character in sort menu! Try again!\n");
                                    break;
                            }
                        } catch (IllegalArgumentException y) {
                            System.out.println("Error! Problem with .sort()!");
                        }


                        break;
                    case 'P':
                        newDB.printTable();
                        break;

                    case 'Q':
                        menuBool = false;
                        System.out.println("\nProgram terminating normally...");
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