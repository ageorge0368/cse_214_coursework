/**
 * This class represents a SlideList object, or a doubly-linked list made up of
 * nodes that have Slide objects as its data. A SlideList object includes a
 * pointer to the beginning of a SlideList (or its head), a pointer to the end
 * of a SlideList (or its tail), and a pointer to the current node of a
 * SlideList (or its cursor).
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class SlideList {
    // Static Variables
    /* The "size" variable represents the size of a SlideList object, or a
    presentation. */
    public int size = 0;

    // Data Fields (Member Variables)
    /* A SlideList object has the reference to three different pointers, the
     * pointer to the beginning of the linked list (head), the pointer to the
     * end of the linked list (tail), and the pointer to the current node in
     * the linked list (cursor). */
    private SlideListNode head;
    private SlideListNode tail;
    private SlideListNode cursor;

    // Default Constructor
    /**
     * This is a constructor that creates a new SlideList object.
     * It initializes the list to an empty list of SlideListNode objects, and
     * it sets the head, tail, and cursor pointers all to null.
     */
    public SlideList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    // Functions
    // List Info Methods:
    /**
     * This method returns the size of a SlideList object. The size is
     * determined as the total number of slides in a slideshow.
     *
     * @return
     * Returns an integer value of the actual size of a slideshow.
     */
    public int size() {
        return size;
    }

    /**
     * This method returns the total duration of a SlideList object, or the
     * total duration of all the slides in a slideshow.
     *
     * @return
     * Returns a double value of the total duration of a slideshow.
     */
    public double duration() {
        double totalDuration = 0.0;
        SlideListNode tempCursor = new SlideListNode();
        tempCursor = head;
        while (tempCursor != null) {
            totalDuration = totalDuration + tempCursor.getData().getDuration();
//            cursor.setNext(cursor.getNext());
            tempCursor = tempCursor.getNext();                      // Is this how you move to the next node?
        }
        return totalDuration;
    }

    /**
     * This method returns the total number of bullets of a SlideList object,
     * or the total number of bullets of all the slides in a slideshow.
     *
     * @return
     * Returns an integer value of the total number of bullets of a slideshow.
     */
    public int numBullets() {
        int totalNumBullets = 0;
        SlideListNode tempCursor = new SlideListNode();
        tempCursor = head;
        while (tempCursor != null) {
            totalNumBullets = totalNumBullets + tempCursor.getData().getNumBullets();
            tempCursor = tempCursor.getNext();
        }
        return totalNumBullets;
    }

    // Cursor Methods:
    /**
     * This method returns the current slide that the cursor is referring to
     * in the slideshow.
     *
     * @return
     * Returns a Slide object which is the corresponding slide to where the
     * cursor is in the slideshow. If the cursor points to a slide that does
     * not exist, then this method returns a null value.
     */
    public Slide getCursorSlide() {
        // Case when cursor is equal to null
        if (cursor == null) {
            return null;
        }

        return cursor.getData();
    }

    /**
     * This method resets the cursor pointer back to head. If the head pointer
     * is equal to null, then cursor is set to null. If the head pointer is not
     * equal to null, then cursor is set to point to the first slide in the
     * slideshow.
     */
    public void resetCursorToHead() throws IllegalArgumentException{
        if (cursor == null) {
            throw new IllegalArgumentException();
        }

        if (head != null) {
            cursor = head;
        } else {
            cursor = null;
        }
    }

    /**
     * This method moves the cursor pointer in a slideshow up one SlideListNode,
     * or up one slide. If the cursor is located where the tail of the
     * slideshow is, then an exception is thrown indicating that the cursor is
     * located where the tail is.
     */
    public void cursorForward() throws EndOfListException, NullPointerException{
        if (cursor == tail) {
            throw new EndOfListException();
        }
        if (cursor == null) {
            throw new NullPointerException();
        }
        cursor = cursor.getNext();
    }

    /**
     * This method moves the cursor pointer in a slideshow down one
     * SlideListNode, or down one slide. If the cursor is located where the
     * head of the slideshow is, then an exception is thrown indicating that
     * the cursor is located where the head is.
     */
    public void cursorBackward() throws EndOfListException{
        if (cursor == head) {
            throw new EndOfListException();
        }
        cursor = cursor.getPrev();
    }

    // Insertion Methods:
    /**
     * This method inserts a new slideListNode at the location right before
     * where the cursor points. If the cursor was previously equal to null,
     * then the new slide will represent the head and the tail of the slideshow.
     * However, if the cursor was previously not equal to null, then the new
     * slide gets inserted at the position right before the cursor.
     *
     * @param newSlide
     * The slide object that represents the new slide that is going to be added
     * to a slideshow.
     *
     * @throws EndOfListException
     * Thrown whenever newSlide is equal to null.
     */
    public void insertBeforeCursor(Slide newSlide) throws IllegalArgumentException, NullPointerException{
            if (newSlide == null) {
                throw new IllegalArgumentException();
            }

            // Create a new slideListNode
            SlideListNode newSlideNode = new SlideListNode(newSlide);

            if (cursor != null) {
                // Adding at the beginning of the linked list
                if (cursor == head) {
                    newSlideNode.setNext(head);
                    head = newSlideNode;
                    cursor.setPrev(newSlideNode);
                    size++;
                } else {
                    // Adding a node before the cursor
                    cursor.getPrev().setNext(newSlideNode);
                    newSlideNode.setPrev(cursor.getPrev());
                    cursor.setPrev(newSlideNode);
                    newSlideNode.setNext(cursor);

                    size++;
                }
                // Referring cursor to the now added node
                cursor = cursor.getPrev();
            }

            else if (cursor == null) {
                // Setting newSlideNode to be the new head and tail of a list
                newSlideNode.setNext(head);
                head = newSlideNode;
                cursor = newSlideNode;
                tail = newSlideNode;

                size++;
            }
    }

    /**
     * This method inserts a new slideListNode at the end of a slideshow, or
     * right after the tail pointer. If the tail was previously equal to null,
     * then the new slide will represent the head and the tail of the slideshow.
     * However, if the tail was previously not equal to null, then the new
     * slide gets inserted at the position right after the tail.
     *
     * @param newSlide
     * The slide object that represents the new slide that is going to be added
     * to a slideshow.
     *
     * @throws EndOfListException
     * Thrown whenever newSlide is equal to null.
     */
    public void appendToTail(Slide newSlide) throws IllegalArgumentException {
//        try {
            if (newSlide == null) {
                throw new IllegalArgumentException();
            }

            // Create a new slideListNode
            SlideListNode newSlideNode = new SlideListNode(newSlide);

            if (tail == null) {
                // Adding a node after the tail

                newSlideNode.setPrev(null);
                newSlideNode.setNext(null);

                head = newSlideNode;
                tail = newSlideNode;
                cursor = newSlideNode;

                size++;
            } else {
                // Setting newSlideNode to be the new head and tail of a list

                tail.setNext(newSlideNode);
                newSlideNode.setPrev(tail);
                size++;
                // Referring tail to the now added node
                tail = tail.getNext();
            }
    }

    // Deletion Method:
    /**
     * This method removes the node wherever the cursor pointer is located.
     * It sets the previous and next links of the adjacent nodes to skip over
     * the cursor node, by dafault, removing the cursor node from the slideshow.
     *
     * @return
     * Returns a Slide object which represents the removed slide from the
     * slideshow.
     *
     * @throws EndOfListException
     * Thrown whenever cursor is equal to null.
     */
    public Slide removeCursor() throws EndOfListException, NullPointerException {
        Slide removedSlide = new Slide();
        // Empty list
        if (head == null) {
            throw new NullPointerException();
        }

        if (cursor == null) {
            throw new EndOfListException();
        }

        removedSlide = this.getCursorSlide();

        // if removing node that is head
        if (cursor == head) {
            head = head.getNext();
            cursor = cursor.getPrev();
            size--;

            // Return removed slide
            return removedSlide;
        }

        // if removing in the middle
        if (cursor.getNext() != null) {
            // Setting previous node to next node
            cursor.getPrev().setNext(cursor.getNext());
            // Setting previous node to next node
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();
            size--;

            // Return removed slide
            return removedSlide;
        }

        // if removing last node
        if (cursor.getNext() == null) {
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getPrev();
            size--;

            // Return removed slide
            return removedSlide;
        }
        return removedSlide;
    }

    // Other methods:
    /**
     * This method prints the entire presentation, which includes each slide's
     * title, duration, and number of bullets. The total number of slides,
     * duration, and number of bullets is also printed.
     *
     * @throws NullPointerException
     * Thrown whenever cursor is equal to null.
     */
    public void printSlideShow () throws NullPointerException{
        if (cursor == null) {
            throw new NullPointerException();
        }
        System.out.println("\nSlideshow Summary:");
        System.out.println("==================================================");
        System.out.println("  Slide\t\t\tTitle\t\tDuration\t\tBullets");
        System.out.println("--------------------------------------------------");
        SlideListNode temp = new SlideListNode();
        temp = head;

        int slideNum = 1;
        String cursorArrow = "";
            while (temp.getNext() != null) {
                cursorArrow = "  ";
                if (temp.getData() == this.getCursorSlide()) {
                    cursorArrow = "->";
                }
                System.out.println(String.format("%-3s%-9d%-10s%10.1f%10d", cursorArrow, slideNum, temp.getData().getTitle(), temp.getData().getDuration(), temp.getData().getNumBullets()));
                temp = temp.getNext();
                slideNum++;
//                cursorArrow = "";
            }
            cursorArrow = "  ";
            if (temp.getData() == this.getCursorSlide()) {
                cursorArrow = "->";
            }

        System.out.println(String.format("%-3s%-9d%-10s%10.1f%10d", cursorArrow, slideNum, temp.getData().getTitle(), temp.getData().getDuration(), temp.getData().getNumBullets()));

        System.out.println("==================================================");
        System.out.println("Total: " + slideNum + " slide(s), " + this.duration() + " minute(s), " + this.numBullets() + " bullet(s)");
        System.out.println("==================================================\n");
    }

    /**
     * This method prints all the bullets of the cursor slide, along with
     * the cursor slide's title.
     *
     * @throws NullPointerException
     * Thrown whenever cursor is equal to null.
     */
//    public void displayCursor throws IllegalArgumentException() {
    public void displayCursor() throws NullPointerException {
        if (cursor == null) {
            throw new NullPointerException();
        }
        System.out.println("\n==================================================");
        System.out.println("  " + this.getCursorSlide().getTitle());
        System.out.println("==================================================");
        int slideNum = 1;
        for (int i = 0; i < this.getCursorSlide().getNumBullets(); i++) {
            System.out.println(slideNum + ". " + this.getCursorSlide().getBullet(i));
            slideNum++;
        }
        System.out.println("==================================================\n");
    }
}