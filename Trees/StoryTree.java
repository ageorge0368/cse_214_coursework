import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.zip.DataFormatException;
import java.util.Scanner;

/**
 * This class represents a StoryTree object, which is the tree created from the
 * input game file.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class StoryTree {
    // Data Fields (Member Variables)
    StoryTreeNode root;
    StoryTreeNode cursor;
    GameState state;
    String[][] optionsArray;

    // Constructor
    /**
     * This is a no-argument constructor that creates a new StoryTree object.
     * It initializes a StoryTree object with a root of "root", an option of
     * "root", and a message of "Hello, and welcome to Zork!". The cursor is
     * initialized to the root and the state of the tree is initialized to
     * "GAME_NOT_OVER". The optionsArray is initialized to all the string
     * "nothing" initially as well.
     */
    public StoryTree() {
        // Initialize root
        root = new StoryTreeNode("root", "root", "Hello, and welcome to Zork!");

        // Initialize cursor
        cursor = root;

        // Initialize state
        state = GameState.GAME_NOT_OVER;

        // Initializing optionsArray to all "nothing" first
        optionsArray = new String[3][2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                optionsArray[i][j] = "nothing";
            }
        }
    }

    // Getters
    /**
     * This is a method that returns the state of a StoryTree object.
     *
     * @return
     * Returns the state of the StoryTree object.
     */
    public GameState getState() {
        return state;
    }

    /**
     * This is a method that returns the position string of the cursor node of
     * a StoryTree object.
     *
     * @return
     * Returns the position string of the cursor node.
     */
    public String getCursorPosition() {
        return cursor.position;
    }

    /**
     * This is a method that returns the message string of the cursor node of
     * a StoryTree object.
     *
     * @return
     * Returns the message string of the cursor node.
     */
    public String getCursorMessage() {
        return cursor.message;
    }

    /**
     * This is a method that returns the options array of the cursor node of a
     * StoryTree object.
     *
     * @return
     * Returns the options array of the cursor node of a StoryTree object.
     */
    public String[][] getOptions() {
        return optionsArray;                // return options array once optionsArray is updated
    }

    // Setters
    /**
     * This is a method that sets the message of the cursor node in a
     * StoryTree object.
     *
     * @param message
     * String that represents the new message of the cursor node in a
     * StoryTree object.
     */
    public void setCursorMessage(String message) {
        cursor.message = message;
    }

    /**
     * This is a method that sets the option of the cursor node in a
     * StoryTree object.
     *
     * @param option
     * String that represents the new option of the cursor node in a
     * StoryTree object.
     */
    public void setCursorOption(String option) {
        cursor.option = option;
    }

    // Other Functions
    /**
     * This is a method that resets the cursor back to the root of a StoryTree
     * object.
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * This is a method that sets the cursor back to the beginning node of the
     * Zork game, or the left child of the root node.
     */
    public void startGame() {
        cursor = root.leftChild;
    }

    /**
     * This is a method that sets the elements of the optionsArray matrix, where
     * the first column represents the position strings of the cursor node's
     * children, and the second column represents the options of the cursor
     * node's children.
     *
     * @return
     * Returns an updated optionsArray with the positions and options of the
     * cursor node's children.
     */
    public String[][] populateOptionsArray () {
        if (this.cursor.isLeaf()) {             // if the cursor is at a leaf
            return optionsArray;
        }

        if (this.cursor.leftChild != null) {
            optionsArray[0][0] = this.cursor.leftChild.position;
            optionsArray[0][1] = this.cursor.leftChild.option;
        }

        if (this.cursor.middleChild != null) {
            optionsArray[1][0] = this.cursor.middleChild.position;
            optionsArray[1][1] = this.cursor.middleChild.option;
        }

        if (this.cursor.rightChild != null) {
            optionsArray[2][0] = this.cursor.rightChild.position;
            optionsArray[2][1] = this.cursor.rightChild.option;
        }

        return optionsArray;
    }

    /**
     * This is a method that selects one of the children of the cursor node in
     * a StoryTree object.
     *
     * @param position
     * String that represents the position of the cursor node's child that the
     * user wants to select.
     *
     * @throws IllegalArgumentException
     * Thrown if the position input string is empty or is null.
     *
     * @throws NodeNotPresentException
     * Thrown if the node (cursor node's child) does not actually exist.
     */
    public void selectChild (String position) throws IllegalArgumentException, NodeNotPresentException{
        if (position.isEmpty() || position == null) {
            throw new IllegalArgumentException();
        }

        if (this.nodeTraversalCheck(position)) {
            throw new NodeNotPresentException();
        }

        // Take positions from position string and put it into an array of Strings
        String[] nodePosition = position.split("-");

        // cursor points to node at position
        nodeTraversal(this, position, nodePosition.length);

    }

    /**
     * This is a method that returns true or false if a node exists in a
     * StoryTree object. This is done by iterating through the input position
     * string starting from the beginning node, the left child of the root node.
     *
     * @return
     * Returns true if the node exists in a StoryTree object. Returns false if
     * not.
     */
    public boolean nodeTraversalCheck (String position) {
        // Set cursor of tempStory back to root first
        this.cursor = this.root;

        // Boolean value for node exists or not
        boolean noNode = false;

        // Take positions from position string and put it into an array of Strings
        String[] nodePosition = position.split("-");

        for (int i = 0; i < nodePosition.length; i++) {
            if (this.cursor == null) {
                noNode = true;
                break;
            }

            switch (nodePosition[i]) {
                case "1":
                    this.cursor = this.cursor.leftChild;
                    break;

                case "2":
                    this.cursor = this.cursor.middleChild;
                    break;

                case "3":
                    this.cursor = this.cursor.rightChild;
                    break;
            }
        }

        return noNode;
    }

    /**
     * This is a method that traverses down a StoryTree object until it finds
     * the corresponding node from the input position string.
     *
     * @param tempStory
     * The StoryTree object of the game that is created from the input file.
     *
     * @param position
     * String that represents the position of the node that the user wants to
     * traverse to.
     *
     * @param numOfLayers
     * Int variable that represents the number of paths down to the node the
     * user is traversing to.
     */
    public static void nodeTraversal(StoryTree tempStory, String position, int numOfLayers) {
        // Set cursor of tempStory back to root first
        tempStory.cursor = tempStory.root;

        // Take positions from position string and put it into an array of Strings
        String[] nodePosition = position.split("-");

        for (int i = 0; i < numOfLayers; i++) {
            if (nodePosition[i].equals("1")) {
                tempStory.cursor = tempStory.cursor.leftChild;
            } else if (nodePosition[i].equals("2")) {
                tempStory.cursor = tempStory.cursor.middleChild;
            } else if (nodePosition[i].equals("3")) {
                tempStory.cursor = tempStory.cursor.rightChild;
            }
        }
    }

    /**
     * This is a method that traverses down a StoryTree object until it finds
     * the corresponding node from the input position string.
     *
     * @param option
     * String that represents the option field of the new StoryTreeNode being
     * added.
     *
     * @param message
     * String that represents the message field of the new StoryTreeNode being
     * added.
     *
     * @throws IllegalArgumentException
     * Thrown if either (or both) option and message input strings are empty or
     * null.
     *
     * @throws TreeFullException
     * Thrown if a subtree in the StoryTree object does not have any space to
     * add another node.
     */
    public void addChild (String option, String message) throws IllegalArgumentException, TreeFullException {
        if (option.isEmpty() || option == null || message.isEmpty() || message == null) {
            throw new IllegalArgumentException();
        }

        if (this.noChild()) {
            throw new TreeFullException();
        }

        StringBuilder newChildPosition = new StringBuilder(this.cursor.position);
        StoryTreeNode newChild = new StoryTreeNode(this.cursor.position, option, message);

        if (this.cursor.leftChild == null) {
            newChildPosition.append("-1");
            this.cursor.leftChild = newChild;
        } else if (this.cursor.middleChild == null) {
            newChildPosition.append("-2");
            this.cursor.middleChild = newChild;
        } else if (this.cursor.rightChild == null) {
            newChildPosition.append("-3");
            this.cursor.rightChild = newChild;
        }

        newChild.position = newChildPosition.toString();
    }

    /**
     * This is a method that checks if a subtree in the StoryTree object is
     * full with children nodes or not.
     *
     * @return
     * Returns true if there is no space for another child node, returns false
     * else not.
     */
    public boolean noChild () {
        return this.cursor.leftChild != null && this.cursor.middleChild != null && this.cursor.rightChild != null;
    }

    /**
     * This is a method that deletes a node (a subtree if the node has children)
     * from the StoryTree object.
     *
     * @param position
     * String that represents the option field of the new StoryTreeNode being
     * added.
     *
     * @return
     * Returns a reference to the node in the StoryTree object that was deleted.
     *
     * @throws NodeNotPresentException
     * Thrown if the node to be deleted does not exist in the StoryTree object.
     */
    public StoryTreeNode removeChild (String position) throws NodeNotPresentException{
        if (this.nodeTraversalCheck(position)) {
            throw new NodeNotPresentException();
        }

        // Set cursor of tempStory back to root first
        this.cursor = this.root;

        // Take positions from position string and put it into an array of Strings
        String[] nodePosition = position.split("-");

        for (int i = 0; i < nodePosition.length - 1; i++) {
            switch (nodePosition[i]) {
                case "1":
                    this.cursor = this.cursor.leftChild;
                    break;

                case "2":
                    this.cursor = this.cursor.middleChild;
                    break;

                case "3":
                    this.cursor = this.cursor.rightChild;
                    break;
            }
        }

        StoryTreeNode removedChild = new StoryTreeNode();

        switch (nodePosition[nodePosition.length - 1]) {
            case "1":
                removedChild = this.cursor.leftChild;
                this.cursor.leftChild = null;

                // Moving children nodes
                this.cursor.leftChild = this.cursor.middleChild;
                this.cursor.middleChild = this.cursor.rightChild;
                this.cursor.rightChild = null;

                // Adjusting position strings
                this.cursor.leftChild.position = this.cursor.position + "-1";
                this.cursor.middleChild.position = this.cursor.position + "-2";

                break;
            case "2":
                removedChild = this.cursor.middleChild;
                this.cursor.middleChild = null;

                // Moving children nodes
                this.cursor.middleChild = this.cursor.rightChild;
                this.cursor.rightChild = null;

                // Adjusting position string
                this.cursor.middleChild.position = this.cursor.position + "-2";

                break;
            case "3":
                removedChild = this.cursor.rightChild;
                this.cursor.rightChild = null;

                // Moving children nodes (no movement needed!)
                break;
        }

        // Return removed child
        return removedChild;
    }

    /**
     * This is a method that reads an input file, and creates a StoryTree
     * object from it that represents the playable game.
     *
     * @param filename
     * String that represents the name of the input file.
     *
     * @throws IllegalArgumentException
     * Thrown if either (or both) option and message input strings are empty or
     * null.
     *
     * @throws DataFormatException
     * Thrown if the data from the input file does not follow the correct
     * format.
     *
     * @throws FileNotFoundException
     * Thrown if the input file does not exist.
     */
    public static StoryTree readTree (String filename) throws IllegalArgumentException, DataFormatException, FileNotFoundException {
        if (filename.isEmpty() || filename == null) {
            throw new IllegalArgumentException();
        }

        // Checks the format of the data
        if (!checkData(filename)) {
            throw new DataFormatException();
        }

        // Create new StoryTree
        StoryTree newGame = new StoryTree();

        try {
            File inputFile = new File(filename);
            if (!inputFile.exists()) {                                              // Checks if a file exists in the first place
                throw new FileNotFoundException("File not found: " + filename);
            }

            Scanner fileIn = new Scanner(inputFile);
            String fileLine = "";
            String[] fileLineSubStrings;
            while (fileIn.hasNextLine()) {
                StoryTreeNode newNode = new StoryTreeNode();
                fileLine = fileIn.nextLine();
                fileLineSubStrings = fileLine.split(" \\| ");

                newNode.position = fileLineSubStrings[0];
                newNode.option = fileLineSubStrings[1];
                newNode.message = fileLineSubStrings[2];

                // Add new node to tree
                addNodeToTree(newGame, newNode);
            }

            // Close the file
            fileIn.close();

        }

        catch (FileNotFoundException fnfe) {
            System.out.println("An error occurred when opening " + filename + ".");
        }


        return newGame;
    }

    /**
     * This is a method that checks if the data from each line of the input
     * file follows the correct format. Checks to see if each line has a string
     * for the position, option, and message of a node.
     *
     * @param filename
     * String that represents the name of the input file.
     *
     * @return
     * Returns true if the format of the data is correct, and returns dalse if
     * the format of the data is not correct.
     *
     * @throws FileNotFoundException
     * Thrown if the input file does not exist.
     */
    public static boolean checkData(String filename) throws FileNotFoundException {
        try {
            File inputFile = new File(filename);
            Scanner fileIn = new Scanner(inputFile);
            String fileLine = "";
            String[] fileLineSubStrings;

            while (fileIn.hasNextLine()) {
                fileLine = fileIn.nextLine();
                fileLineSubStrings = fileLine.split(" \\| ");

                if (fileLineSubStrings.length != 3) {
                    return false;
                }

            }

        } catch (FileNotFoundException x) {
            System.out.println("Error! Cannot open file " + filename);
        }

        // Data is correct
        return true;
    }

    /**
     * This is a method that adds a new node to a StoryTree object. The method
     * is called during the .readTree() function when making a playable game
     * from an input file.
     *
     * @param tempStory
     * StoryTree object that represents a playable game.
     *
     * @param tempNewNode
     * StoryTreeNode object that represents the new node that is being added to
     * the tempStory object.
     */
    public static void addNodeToTree(StoryTree tempStory, StoryTreeNode tempNewNode) {
        // if the cursor of the tree is equivalent to the root of the tree, and if the root of the tree does not have a left child
        if ((tempStory.cursor == tempStory.root) && (tempStory.root.leftChild == null)) {
            tempStory.root.leftChild = tempNewNode;                     // Add first Node to the tree
        }

        // Set cursor of tempStory back to root first
        tempStory.cursor = tempStory.root;

        // Take positions from position string and put it into an array of Strings
        String[] nodePosition = tempNewNode.position.split("-");

        // Now, we are at the node before the newNode is added; parent of newNode
        nodeTraversal(tempStory, tempNewNode.position, nodePosition.length - 1);

        // Add newNode to the tree
        switch (nodePosition[nodePosition.length - 1]) {
            case "1":
                tempStory.cursor.leftChild = tempNewNode;
                break;

            case "2":
                tempStory.cursor.middleChild = tempNewNode;
                break;
            case "3":
                tempStory.cursor.rightChild = tempNewNode;
                break;
        }
    }

    // Write back to the same input file?
        // The tree? In preorder traversal?
    /**
     * This is a method that saves a StoryTree object to a file. Each node in
     * the StoryTree object is saved into the output file in preorder format,
     * and each node is saved in the format of "position | option | message".
     *
     * @param filename
     * String that represents the name of the input file.
     *
     * @param tree
     * StoryTree object that represents a playable game.
     *
     * @throws IllegalArgumentException
     * Thrown if the filename string is empty, or null, or if tree object is
     * null.
     */
    public static void saveTree(String filename, StoryTree tree) throws IllegalArgumentException{
        if (filename.isEmpty() || filename == null || tree == null) {
            throw new IllegalArgumentException();
        }

        try {
            File outputFile = new File(filename);
            if (!outputFile.exists()) {                                              // Checks if a file exists in the first place
                throw new FileNotFoundException("File not found: " + filename);
            }

            PrintWriter fileOut = new PrintWriter(filename);

            // reset cursor back to root
            tree.resetCursor();

            // to starting game state
            tree.startGame();

            // calling preorder print function
            tree.printPreorder(fileOut, tree.cursor);

            // Close the file
            fileOut.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when opening " + filename + ".");
        }
    }

    /**
     * This is a method that traverses down a StoryTree object in preorder and
     * prints each visited node into an output file with the format
     * "position | option | message".
     **
     * @param tempFileOut
     * PrintWriter object which represents the output file that the method is
     * writing to.
     *
     * @param tempTreeCursor
     * StoryTreeNode object that represents the cursor node of the StoryTree
     * object.
     */
    public void printPreorder (PrintWriter tempFileOut, StoryTreeNode tempTreeCursor) {
        // Terminating case
        if (tempTreeCursor == null) {
            return;
        }

        // Current Node
        tempFileOut.println(tempTreeCursor.position + " | " + tempTreeCursor.option + " | " + tempTreeCursor.message);

        // Left child
        printPreorder(tempFileOut, tempTreeCursor.leftChild);

        // Middle child
        printPreorder(tempFileOut, tempTreeCursor.middleChild);

        // Right child
        printPreorder(tempFileOut, tempTreeCursor.rightChild);
    }
}