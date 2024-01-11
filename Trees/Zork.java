import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * This class represents the Zork class, where the main function serves as the
 * larger program that loads in a game from an input file and asks the user to
 * either play the game, to edit the game, or to exit from the program. The
 * .editTree() function serves as a program that asks the user to edit the
 * current game that is loaded, and the .playTree() function allows the user to
 * play the game.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */

public class Zork {
    /**
     * This is a method that serves as the main program, where the user can
     * load in a game from an input file and can either play the game, or edit
     * the game. This program runs until the option "Q" is pressed.
     *
     * @throws DataFormatException
     * Thrown if the format of the data in the input file is not correct.
     *
     * @throws FileNotFoundException
     * Thrown if the input file does not exist.
     */
    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        //String test = "1 | Let's Play! | It is Monday morning and your alarm goes off. What do you do?";
        //String ignore = " | ";
/*        String test = "1-1-2-1-1";
        String[] testSubStrings = test.split("-");

        for (int i = 0; i < testSubStrings.length; i++) {
            System.out.println(testSubStrings[i]);
        }*/

        Scanner stdin = new Scanner(System.in);

        // Menu variables
        String fileName = "";
        boolean menuBool = true;
        char menuCharOption = ' ';

        // Creating a new StoryTree with default constructor
        StoryTree newTree = new StoryTree();

//        System.out.println("\nHello and Welcome to Zork!");
        System.out.println("\n" + newTree.root.message);
        System.out.println("Please enter the file name: ");
        fileName = stdin.nextLine();

        System.out.println("Loading game from file...");

        // Creating a Storytree from the textfile
        newTree = StoryTree.readTree(fileName);

        if (newTree.root.leftChild != null) {
            System.out.println("File loaded!");
        }

        while (menuBool) {
            try {
                String menuOption = "";
                System.out.println("Would you like to edit (E), play (P) or quit (Q)? ");

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
                    case 'E':
                        // Edit the tree from the input file
//                        editTree(fileName, newTree);
                        editTree(fileName, newTree, stdin);
                        System.out.println();
                        break;

                    case 'P':
                        // Play tree from input file
                        playTree(newTree, stdin);
                        System.out.println();
                        break;

                    case 'Q':
                        menuBool = false;
                        System.out.println("Program terminating normally...");
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

    /**
     * This is a method that serves as the edit program, where a user can view
     * the cursor node's attributes, select a child of the cursor node, set the
     * option and message data fields of the cursor, add a child node to the
     * cursor, delete a child of the cursor node, and move the cursor to the
     * root of the StoryTree object passed in. Quitting the program also saves
     * all the changes to an output file.
     *
     * @param tempFileName
     * String variable that represents the name of the output file.
     *
     * @param tree
     * StoryTree object which represents the playable game from the main
     * function.
     *
     * @param stdin
     * Scanner object that helps with getting inputs from the user.
     */
    public static void editTree (String tempFileName, StoryTree tree, Scanner stdin) {
        // Edit menu variables
        boolean editMenuBool = true;
        char editMenuCharOption = ' ';
        int cursorChildOption = 0;
        String newOption = "";
        String newMessage = "";

        while (editMenuBool) {
            try {
                String editMenuOption = "";

                // Edit menu
                System.out.println("Zork Editor:");
                System.out.println("\tV: View the cursor's position, option and message.");
                System.out.println("\tS: Select a child of this cursor (options are 1, 2, and 3).");
                System.out.println("\tO: Set the option of the cursor.");
                System.out.println("\tM: Set the message of the cursor.");
                System.out.println("\tA: Add a child StoryNode to the cursor.");
                System.out.println("\tD: Delete one of the cursor's children and all its descendants.");
                System.out.println("\tR: Move the cursor to the root of the tree.");
                System.out.println("\tQ: Quit editing and return to main menu.");

                System.out.println("\nPlease select an option: ");

                while (editMenuOption.equals("")) {
                    // Converting menu option to upper case everytime
                    editMenuOption = stdin.nextLine().toUpperCase();
                }
                if (editMenuOption.length() != 1) {
                    System.out.println("Invalid menu option! Try again!\n");
                } else {
                    editMenuCharOption = editMenuOption.charAt(0);
                }

                switch (editMenuCharOption) {
                    case 'V':
                        if ((tree.cursor == tree.root) && (tree.root.leftChild != null)) {
                            tree.startGame();
                        }

                        System.out.println("\nPosition: " + tree.getCursorPosition());
                        System.out.println("Option: " + tree.cursor.option);
                        System.out.println("Message: " + tree.getCursorMessage() + "\n");
                        break;

                    case 'S':
                        if (tree.cursor.numOfChildren() == 0) {
                            System.out.println("No Child");
                            break;
                        }

                        if ((tree.cursor == tree.root) && (tree.root.leftChild != null)) {
                            tree.startGame();
                        }

                        cursorChildOption = 0;

                        System.out.print("\nPlease select a child: [");
                        for (int i = 0; i < tree.cursor.numOfChildren() - 1; i++) {
                            System.out.print((i + 1) + ",");
                        }
                        System.out.print(tree.cursor.numOfChildren() + "]\n");

                        // Getting cursor child option
                        cursorChildOption = stdin.nextInt();

                        if (cursorChildOption > tree.cursor.numOfChildren() || cursorChildOption < 1) {
                            System.out.println("Error. No child " + cursorChildOption + " for the current node.\n");
                            break;
                        }

                        if (cursorChildOption == 1) {
                            tree.cursor = tree.cursor.leftChild;
                        } else if (cursorChildOption == 2) {
                            tree.cursor = tree.cursor.middleChild;
                        } else if (cursorChildOption == 3) {
                            tree.cursor = tree.cursor.rightChild;
                        }

                        System.out.println("Cursor child " + cursorChildOption + " has been selected.\n");
                        break;

                    case 'O':
                        if ((tree.cursor == tree.root) && (tree.root.leftChild != null)) {
                            tree.startGame();
                        }

                        newOption = "";

                        System.out.println("Please enter a new option: ");
                        newOption = stdin.nextLine();

                        // Setting new option of cursor
                        tree.setCursorOption(newOption);

                        System.out.println("Option set.\n");
                        break;

                    case 'M':
                        if ((tree.cursor == tree.root) && (tree.root.leftChild != null)) {
                            tree.startGame();
                        }

                        newMessage = "";

                        System.out.println("Please enter a new message: ");
                        newMessage = stdin.nextLine();

                        // Setting new message of cursor
                        tree.setCursorMessage(newMessage);

                        System.out.println("Message set.\n");
                        break;

                    case 'A':
                        newOption = "";
                        newMessage = "";

                        System.out.println("Enter an option: ");
                        newOption = stdin.nextLine();
                        System.out.println("Enter a message: ");
                        newMessage = stdin.nextLine();

                        // Adding a new child to cursor
                        tree.addChild(newOption, newMessage);

                        System.out.println("Child added.\n");
                        break;

                    case 'D':
                        if (tree.cursor.numOfChildren() == 0) {
                            System.out.println("No Child");
                            break;
                        }

                        cursorChildOption = 0;

                        System.out.print("Please select a child: [");
                        for (int i = 0; i < tree.cursor.numOfChildren() - 1; i++) {
                            System.out.print((i + 1) + ",");
                        }
                        System.out.print(tree.cursor.numOfChildren() + "]\n");

                        // Getting cursor child option
                        cursorChildOption = stdin.nextInt();

                        if (cursorChildOption > tree.cursor.numOfChildren() || cursorChildOption < 1) {
                            System.out.println("Error. No child " + cursorChildOption + " for the current node.\n");
                            break;
                        }

                        if (cursorChildOption == 1) {
                            tree.removeChild(tree.cursor.leftChild.position);
                        } else if (cursorChildOption == 2) {
                            tree.removeChild(tree.cursor.middleChild.position);
                        } else if (cursorChildOption == 3) {
                            tree.removeChild(tree.cursor.rightChild.position);
                        }

                        System.out.println("Subtree deleted.\n");
                        break;

                    case 'R':
                        tree.resetCursor();
                        System.out.println("Cursor moved to root.\n");
                        break;

                    case 'Q':
                        editMenuBool = false;
                        // Save game here!
                        StoryTree.saveTree(tempFileName, tree);
                        break;

                    default:
                        System.out.println("Wrong character! Try again!\n");
                        break;
                }

            } catch (InputMismatchException y) {
                System.out.println("Invalid input. Try again!\n");
                stdin.nextLine();
            } catch (NodeNotPresentException e) {
                System.out.println("Invalid child node. Try again!\n");
                stdin.nextLine();
            } catch (TreeFullException e) {
                System.out.println("Error");
                System.out.println("Tree is full! Try again!");
            }
        }

    }

    /**
     * This is a method that serves as the playing program, where a user can
     * play the game that they loaded in from an input file. The game starts
     * from the first node, and prints the node's message, and the options of
     * its children. This done for every node that gets traveersed until the
     * user reaches a winning node or a losing node.
     *
     * @param tree
     * StoryTree object which represents the playable game from the main
     * function.
     *
     * @param stdin
     * Scanner object that helps with getting inputs from the user.
     */
    public static void playTree (StoryTree tree, Scanner stdin) {
        // Reset cursor to root of tree
        tree.resetCursor();

        // Start Game
        tree.startGame();

        // Print option of root node
        System.out.println("\n" + tree.cursor.option);

        // While the current node is not a leaf
        while (!tree.cursor.isLeaf()) {
            int optionChoice = 0;

            // Print current node's message
            System.out.println(tree.cursor.message);

            // Populate options array with relevant info
            tree.populateOptionsArray();

            // Printing all options of the current node
            for (int i = 0; i < tree.cursor.numOfChildren(); i++) {
                System.out.println((i + 1) + ") " + tree.getOptions()[i][1]);
            }

            // Picking an option choice!
            System.out.println("Please make a choice. ");
            optionChoice = stdin.nextInt();

            if (optionChoice > tree.cursor.numOfChildren() || optionChoice < 1) {
                System.out.println("That's an invalid choice! Try again!");
                System.out.println();
                continue;
            }

            if (optionChoice == 1) {
                tree.cursor = tree.cursor.leftChild;
            } else if (optionChoice == 2) {
                tree.cursor = tree.cursor.middleChild;
            } else if (optionChoice == 3) {
                tree.cursor = tree.cursor.rightChild;
            }

            System.out.println();
        }

        // Now you are at leaf node => Either a winning node or a losing node
            // Winning or losing message!
        System.out.println(tree.cursor.message);
        System.out.println("\nThanks for playing.");
    }
}
