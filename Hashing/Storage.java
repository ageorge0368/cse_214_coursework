import java.io.Serializable;

/**
 * This class represents the Storage class, where Storage objects are created
 * that represent the client of the storage object, the actual content, and
 * then a unique key to find a Storage object in a StorageTable object.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class Storage implements Serializable {
    // Static Field
    public static long serialVersionUID;

    // Data Fields (Member Variables)
    private int id;                             // Keys in the hash map
    private String client;
    private String content;

    // No-Arg Constructor
    /**
     * This is the no-argument constructor for the Storage class, where the id
     * is set to -1, and the client and content strings are both set to
     * the string "default".
     */
    public Storage () {
        id = -1;
        client = "default";
        content = "default";
    }

    // Default Constructor
    /**
     * This is the default constructor for the Storage class, where the
     * constructor takes in parameters for the id, client, and content data
     * fields and sets their corresponding values to a new Storage object.
     */
    public Storage (int tempId, String tempClient, String tempContent) {
        id = tempId;
        client = tempClient;
        content = tempContent;
    }

    // Getters
    /**
     * This is a method that returns the id of a Storage object.
     *
     * @return
     * Returns the id of a Storage object.
     */
    public int getId() {
        return id;
    }

    /**
     * This is a method that returns the client of a Storage object.
     *
     * @return
     * Returns the client of a Storage object.
     */
    public String getClient() {
        return client;
    }

    /**
     * This is a method that returns the content of a Storage object.
     *
     * @return
     * Returns the content of a Storage object.
     */
    public String getContent() {
        return content;
    }

    // Setters
    /**
     * This is a method that sets the id of a Storage object.
     *
     * @param id
     * Integer value that represents the new Id value for a Storage object.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This is a method that sets the client of a Storage object.
     *
     * @param client
     * String variable that represents the new client for a Storage object.
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * This is a method that sets the content of a Storage object.
     *
     * @param content
     * String variable that represents the new content for a Storage object.
     */
    public void setContent(String content) {
        this.content = content;
    }
}
