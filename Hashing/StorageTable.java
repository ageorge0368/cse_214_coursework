import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the StorageTable class, where it is an extension of
 * the HashMap class. Specific functions are made to uniquely put a Storage
 * object in a StorageTable object, and to retrieve a Storage object from
 * the table after knowing that Storage object's id.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class StorageTable extends HashMap implements Serializable {
    // Static Field
    public static int serialVersionUID;

    // Default Constructor
    /**
     * This is the default constructor for the StorageTable class, where the
     * constructor does not set any data field values (as it does not have any).
     * The constructor serves to help make a StorageTable object in the main
     * function.
     */
    public StorageTable () { }

    // Other Functions
    /**
     * This is a method that adds a Storage object into the StorageTable object.
     * If the id provided already has a Storage object associated with it, then
     * the new Storage object passed in as a parameter is not added into the
     * table.
     *
     * @param storageId
     * Integer variable representing the id number of the storage object.
     *
     * @param storage
     * Storage object that is being added to the StorageTable object.
     *
     * @throws IllegalArgumentException
     * Thrown if storageId is less than 0, or if the storageId already has a
     * storage object associated with it, or if the storage object that is being
     * added to the StorageTable does not exist.
     */
    public void putStorage (int storageId, Storage storage) throws IllegalArgumentException {
        // Check if the storage id does not already exist in the table
        if (storageId < 0 || this.containsKey(storageId) || storage == null) {
            throw new IllegalArgumentException("storageId either is invalid or is used, or storage object is null!");
        }

        // Puts storage object into hashmap with given storageId
        this.put(storageId, storage);
    }

    /**
     * This is a method that returns the Storage object associated with the
     * storageId passed in as a parameter. If the Storage object does not exist,
     * then the function returns null.
     *
     * @param storageId
     * Integer variable representing the id number of the storage object.
     */
    public Storage getStorage (int storageId) {
        if (this.containsKey(storageId)) {
            return (Storage) this.get(storageId);
        }

        // Otherwise return null if the storageId does not exist
        return null;
    }

    /**
     * This is a method that prints all the instances of a Storage object in
     * the StorageTable if a Storage object has the same client as inputted by
     * the user. If the client does not exist in the StorageTable, then an error
     * message is thrown.
     *
     * @param tempClient
     * String variable representing the client string in a Storage object in
     * the StorageTable.
     */
    public void printClientValue(String tempClient) {
        Set<Entry<Integer, Storage>> tempAllMappings = this.entrySet();

        boolean clientExists = false;

        for (Map.Entry<Integer, Storage> i : tempAllMappings) {
            if (i.getValue().getClient().equals(tempClient)) {
                clientExists = true;
                break;
            }
        }

        if (clientExists) {
            System.out.println("\nBox#          Contents                       Owner");
            System.out.println("----------------------------------------------------------------");
            for (Map.Entry<Integer, Storage> i : tempAllMappings) {
                if (i.getValue().getClient().equals(tempClient)) {
                    String tableEntry = String.format("%-12d%-15s", i.getKey(), i.getValue().getContent());
                    tableEntry = tableEntry + "\t\t\t" + i.getValue().getClient();
                    System.out.println(tableEntry);
                }
            }

        } else {
            System.out.println("The inputted client does not exist! Try again!");
        }
    }
}