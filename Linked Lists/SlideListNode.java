/**
 * This class represents a slide list node, where a slide list node has the
 * data (which is a Slide object), a reference to the next slide list node, and
 * a reference to the previous slide list node.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class SlideListNode {
    // Data Fields (Member Variables)
    /* A SlideListNode object has the data from a slide, its reference to the
    * previous SlideListNode, and its reference to the next SlideListNode in
    * sequence. */
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;

    // Default Constructor
    public SlideListNode () {
        Slide tempSlide = new Slide();
        this.data = tempSlide;
        this.next = null;
        this.prev = null;
    }

    // Arg Constructor
    /**
     * This is a constructor that creates a new SlideListNode object. It
     * initializes the data in a SlideListNode from a Slide object taken in
     * as a parameter, along with initializing the reference to the previous
     * and next SlideListNodes to null.
     *
     * @param initData
     * The Slide object that initializes the data of a SlideListNode object
     *
     * @throws IllegalArgumentException
     * Thrown whenever initData is null.
     */
    public SlideListNode(Slide initData) throws IllegalArgumentException{
        try {
            if (initData == null) {
                throw new IllegalArgumentException();
            }
            // Making data of this SlideListNode to initData
            this.data = initData;

            // Setting next and prev to null
            this.next = null;
            this.prev = null;
        } catch (IllegalArgumentException x) {
            System.out.println("InitData is equal to null! Try again!");
        }
    }

    // Getters
    /**
     * This method returns the data of a SlideListNode.
     *
     * @return
     * The Slide object representing the data of a SlideListNode.
     */
    public Slide getData() {
        return data;
    }

    /**
     * This method returns the reference to the next SlideListNode from the
     * current SlideListNode.
     *
     * @return
     * The SlideListNode object reference to the next SlideListNode from the
     * current SlideListNode.
     */
    public SlideListNode getNext() {
        return next;
    }

    /**
     * This method returns the reference to the previous SlideListNode from the
     * current SlideListNode.
     *
     * @return
     * The SlideListNode object reference to the previous SlideListNode from
     * the current SlideListNode.
     */
    public SlideListNode getPrev() {
        return prev;
    }

    // Setters
    /**
     * This method sets the data of a SlideListNode object from a Slide object
     * that is taken as a parameter.
     *
     * @param newData
     * The Slide object that initializes a SlideListNode's data.
     *
     * @throws IllegalArgumentException
     * Thrown when newData is a null Slide object.
     */
    public void setData(Slide newData) throws IllegalArgumentException{
        try {
            if (newData == null) {
                throw new IllegalArgumentException();
            }
            this.data = newData;
        } catch (IllegalArgumentException x) {
            System.out.println("newData is equal to null! Try again!");
        }
    }

    /**
     * This method sets the reference of the current SlideListNode object's
     * next node.
     *
     * @param newNext
     * The SlideListNode object that references the current SlideListNode
     * object's next node.
     */
    public void setNext(SlideListNode newNext) {
        this.next = newNext;
    }

    /**
     * This method sets the reference of the current SlideListNode object's
     * previous node.
     *
     * @param newPrev
     * The SlideListNode object that references the current SlideListNode
     * object's previous node.
     */
    public void setPrev(SlideListNode newPrev) {
        this.prev = newPrev;
    }
}