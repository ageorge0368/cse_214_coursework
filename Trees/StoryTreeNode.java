/**
 * This class represents a node in the larger Story Tree for the game "Zork".
 * This class contains 3 data fields that represent the current position of the
 * node, the option from each node, and the message contained in each node.
 * Each node also has 3 data fields that points to each of its children, its
 * left child, middle child, and right child.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class StoryTreeNode {
    // Static Constants
    static final String WIN_MESSAGE = "YOU WIN";
    static final String LOSE_MESSAGE = "YOU LOSE";

    // Data Fields (Member Variables)
    String position;
    String option;
    String message;

    StoryTreeNode leftChild;
    StoryTreeNode middleChild;
    StoryTreeNode rightChild;

    // Constructors
        // Default Constructor
    /**
     * This is a no-argument constructor that creates a new StoryTreeNode
     * object. It initializes the position, option, and message as all
     * "default", and the left, middle, and right children as all null.
     */
    public StoryTreeNode() {
        // What about the position, option, and message in a default constructor??
        this.position = "default";
        this.option = "default";
        this.message = "default";

        // What about the left, middle, and right children???
            // Default case => all children are null
        this.leftChild = null;
        this.middleChild = null;
        this.rightChild = null;
    }

        // Arged-Constructor
    /**
     * This is a argument constructor that creates a new StoryTreeNode
     * object. It initializes the position with initPosition, option as
     * initOption, and message as initMessage. The left, middle, and right
     * children are all initialized to null.
     */
    public StoryTreeNode(String initPosition, String initOption, String initMessage) {
        this.position = initPosition;
        this.option = initOption;
        this.message = initMessage;

        // What about the left, middle, and right children???
        this.leftChild = null;
        this.middleChild = null;
        this.rightChild = null;
    }

    // Other Functions
    /**
     * This is a method that checks if the current StoryTreeNode is a leaf in
     * the larger StoryTree object.
     *
     * @return
     * True if the current node is a leaf in the larger StoryTree
     * object. False if the current node is not a leaf.
     */
    public boolean isLeaf() {
        if (this.leftChild == null && this.middleChild == null && this.rightChild == null) {
            return true;
        }

        return false;                           // If a node does not have any children, then return false
    }

    /**
     * This is a method that checks if the current StoryTreeNode is a leaf that
     * is the winning node in the game.
     *
     * @return
     * True if the current node is a winning node. False if the current node is
     * not a winning node.
     */
    public boolean isWinningNode() {
        if (this.isLeaf()) {                // if the current node is a leaf
            if (this.message.contains(WIN_MESSAGE)) {
                return true;                            // if the leaf has the "YOU WIN" message, then it is the winning node
            }
            return false;
        }

        return false;                       // if the node is not a leaf, then return false
    }

    /**
     * This is a method that checks if the current StoryTreeNode is a leaf that
     * is the losing node in the game.
     *
     * @return
     * True if the current node is a losing node. False if the current node is
     * not a losing node.
     */
    public boolean isLosingNode() {
        if (this.isLeaf()) {
            return this.message.contains(LOSE_MESSAGE);
        }
        return false;
    }

    /**
     * This is a method that checks the number of children that a StoryTreeNode
     * has.
     *
     * @return
     * Returns 1-3 children depending on the number of children. If a node has
     * no children, then the method returns a 0.
     */
    public int numOfChildren() {
        if (this.leftChild != null && this.middleChild != null && this.rightChild != null) {
            return 3;
        }

        if (this.leftChild != null && this.middleChild != null) {
            return 2;
        }

        if (this.leftChild != null) {
            return 1;
        }

        // no children, is a leaf!
        return 0;
    }
}