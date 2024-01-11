/**
 * This class represents a CargoShip object which holds multiple stacks of
 * Cargo objects. This object has an array of stacks, a maximum height for each
 * stack, and a maximum weight for all the cargo in the ship.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class CargoShip {

    // Data Fields (Member Variables)
    private CargoStack[] stacks;
    private int maxHeight;
    private double maxWeight;

    // Default Constructor
    /**
     * This is a constructor that creates a new Cargo object. It initializes
     * the name, weight, and strength of a piece of cargo as arguments into
     * the constructor.
     *
     * @param numStacks
     * Int variable that represents the number of stacks for a cargo ship.
     *
     * @param initMaxHeight
     * Int variable that represents the maximum height of each stack on the
     * cargo ship.
     *
     * @param initMaxWeight
     * Int that represents the maximum weight of all the cargo in the cargo
     * ship.
     *
     * @throws IllegalArgumentException
     * Thrown if either numStacks, initMaxHeight, or initMaxWeight are less
     * than or equal to 0.
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) throws IllegalArgumentException{
        if ((numStacks <= 0) || (initMaxHeight <= 0) || (initMaxWeight <= 0.0)) {
            throw new IllegalArgumentException();
        }

        stacks = new CargoStack[numStacks];
        // Initializing each stack of the ship to be empty
        for (int i = 0; i < numStacks; i++) {
            stacks[i] = new CargoStack();
        }
        maxHeight = initMaxHeight;
        maxWeight = initMaxWeight;
    }

    // Getters
    /**
     * This method returns the array of stacks of a CargoShip object.
     *
     * @return
     * The array of CargoStack objects representing the stacks of a CargoShip
     * object.
     */
    public CargoStack[] getStacks() {
        return stacks;
    }

    /**
     * This method returns the maximum height of a stack on a CargoShip object.
     *
     * @return
     * The int representing the maximum height.
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * This method returns the maximum weight of all cargo on a CargoShip
     * object.
     *
     * @return
     * The double representing the maximum weight.
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    // Other Functions
    /**
     * This is a method that pushes a Cargo object into one of the stacks in
     * a CargoShip object. This method checks initially if the new Cargo object
     * is legitimate, if the stack that it is being added to is also legitimate,
     * and if the Cargo object added would exceed the maximum height of a stack
     * or the maximum weight of all the cargo allotted for a ship.
     *
     * @param cargo
     * Cargo variable that represents the new Cargo object added to a stack.
     *
     * @param stack
     * Int variable that represents the index of the specific stack that the
     * cargo will be added onto.
     *
     * @throws IllegalArgumentException
     * Thrown if either cargo is equal to null, or if the stack index is not a
     * part of the array of stacks of a CargoShip object.
     *
     * @throws FullStackException
     * Thrown if the indicated stack has reached its maximum height.
     *
     * @throws ShipOverweightException
     * Thrown if the new cargo object added with the current weight of all
     * the cargo in the ship exceeds the maximum weight of all the cargo
     * specified when the CargoShip object was initialized.
     *
     * @throws CargoStrengthException
     * Thrown if the new cargo being added cannot be added on top of a weaker
     * piece of cargo on the indicated stack.
     */
    public void pushCargo(Cargo cargo, int stack) throws IllegalArgumentException, FullStackException, ShipOverweightException, CargoStrengthException {
        if (cargo == null) {
            throw new IllegalArgumentException();
        }

        if (!(stack >= 1 && stack <= this.stacks.length)) {
            throw new IllegalArgumentException();
        }

        if (this.stacks[stack - 1].size() == this.maxHeight) {
            throw new FullStackException();
        }

        // If the cargo does not exist in the ship, then do calculation of weight check
            // (pushing cargo from dock to ship)
        // If not, then assume that the cargo already exists on the ship
            // (pushing cargo from one stack on ship to another stack on ship)
        if (!this.searchCargo(cargo.getName())) {
            if ((this.totalWeight() + cargo.getWeight()) > this.maxWeight){
                throw new ShipOverweightException();
            }
        }

        // Empty case!
        if (this.stacks[stack - 1].empty() == true) {
            this.stacks[stack - 1].push(cargo);
            return;
        }

        this.stacks[stack - 1].checkStrengthConditions(cargo);
        // Assuming no exceptions were thrown!
        this.stacks[stack - 1].push(cargo);
    }

    /**
     * This is a method that pops a Cargo object from one of the stacks in
     * a CargoShip object. This method checks initially if the stack that it is
     * being popped from is legitimate, and if the selected stack is already
     * empty, and cannot be popped from.
     *
     * @param stack
     * Int variable that represents the index of the specific stack that the
     * cargo will be added onto.
     *
     * @return
     * Returns the popped cargo from the stack.
     *
     * @throws IllegalArgumentException
     * Thrown if the stack index is not a part of the array of stacks of a
     * CargoShip object.
     *
     * @throws EmptyStackException_2
     * Thrown if the indicated stack is already empty.
     */
    public Cargo popCargo (int stack) throws IllegalArgumentException, EmptyStackException_2 {
        if (!(stack >= 1 && stack <= this.stacks.length)) {
            throw new IllegalArgumentException();
        }

        // Empty case!
        if (this.stacks[stack - 1].empty()) {
            throw new EmptyStackException_2();
        }

        Cargo poppedCargo = this.stacks[stack - 1].peek();
        this.stacks[stack - 1].pop();
        return poppedCargo;
    }

    /**
     * This is a method that finds and prints all the occurrences of a cargo in
     * the ship by iterating through all the stacks in the ship, and displaying
     * which stack it is located in, how far from the top is the cargo in the
     * stack, the weight of the cargo, and the cargo's strength. This method
     * checks through all cargo in the ship by checking each cargo's name, and
     * comparing it with the input name given by the user.
     */
    public String findAndPrint (String name) {
        // For Option S from the menu!
        String titleOfSeach = "\n Stack   Depth    Weight      Strength";
        String titleLines = "\n=======+=======+===========+===========";
        StringBuilder totalSearchTable = new StringBuilder(titleOfSeach + titleLines);

        boolean foundOne = false;
        int foundCount = 0;
        double foundTotalWeight = 0.0;
        int numOfStacks = this.stacks.length;
        for (int i = 0; i < numOfStacks; i++) {
            CargoStack tempStack = new CargoStack();
            int stackSize = this.stacks[i].size();
            for (int j = 0; j < stackSize; j++) {
                if (this.stacks[i].empty()) {
                    continue;
                }
                // Popping corresponding stack
                Cargo tempCargo = this.stacks[i].pop();
                if (tempCargo.getName().equalsIgnoreCase(name)) {
                    foundOne = true;
//                    totalSearchTable = new StringBuilder(totalSearchTable + "\n   " + (i + 1) + "   |   " + (j) + "   |   " + tempCargo.getWeight() + "   |   " + tempCargo.getStrength());
                    totalSearchTable = new StringBuilder(totalSearchTable + "\n   " + (i + 1) + "   |   " + (j) + "   |   " + String.format("%.0f", tempCargo.getWeight()) + "   |   " + tempCargo.getStrength());
                    foundTotalWeight = foundTotalWeight + tempCargo.getWeight();
                    foundCount++;
                }
                tempStack.push(tempCargo);
            }

            // Returns elements from tempStack back to the corresponding stack
            int tempSize = tempStack.size();
            for (int j = 0; j < tempSize; j++) {
/*                if (tempStack.empty()) {
                    continue;
                }*/
                this.stacks[i].push(tempStack.pop());
            }
        }

        if (foundOne) {
            totalSearchTable = new StringBuilder("\nCargo '" + name + "' found at the following locations: " + totalSearchTable);
//            System.out.println("\nCargo '" + name + "' found at the following locations: ");
//            System.out.println(totalSearchTable);
            totalSearchTable = new StringBuilder(totalSearchTable + "\nTotal count: " + foundCount);
//            System.out.println("\nTotal count: " + foundCount);
//            System.out.println("Total weight: " + foundTotalWeight);
            totalSearchTable = new StringBuilder(totalSearchTable + "\nTotal weight: " + String.format("%.0f", foundTotalWeight));
            System.out.println(totalSearchTable);
        } else {
            System.out.println("\nCargo '" + name + "' could not be found on the ship.");
        }

        return totalSearchTable.toString();
    }

    /**
     * This is a method that iterates through all the cargo in a cargo ship and
     * calculates the total weight of all of the cargo. This is done by
     * utilizing another stack to store the contents of a stack on a ship, and
     * then summing together the weights of all of the cargo. After one stack,
     * the contents of the temporary stack get popped into the corresponding
     * actual stack on the ship.
     */
    public double totalWeight () {
        double totalWeight = 0.0;
        int numOfStacks = this.stacks.length;
        for (int i = 0 ; i < numOfStacks; i++) {
//            Stack<Cargo> tempStack = new Stack<Cargo>();
            CargoStack tempStack = new CargoStack();
            Cargo tempCargo, tempPoppedCargo;

            int stackSize = this.stacks[i].size();
            for (int j = 0; j < stackSize; j++) {
                tempCargo = this.stacks[i].pop();
                totalWeight = totalWeight + tempCargo.getWeight();
                tempStack.push(tempCargo);
            }

            int tempSize = tempStack.size();
            // Now this.stacks[i] is empty! Push elements back into it!
            for (int k = 0; k < tempSize; k++) {
                tempPoppedCargo = tempStack.pop();
                this.stacks[i].push(tempPoppedCargo);
            }
            // Now this.stacks[i] is restored back!
        }

        return totalWeight;
    }

    /**
     * This is a method that iterates through all the cargo in a cargo ship and
     * checks if a piece of cargo exists in the cargo ship or not, by returning
     * a boolean value. This method, similar to the .findAndPrint() function,
     * brute-force checks all of the cargo in the cargo ship.
     *
     * @return
     * Returns a boolean value that represents whether or not the cargo in
     * question exists in the cargo ship.
     */
    public boolean searchCargo (String cargoName) {
        boolean foundOne = false;
        int numOfStacks = this.stacks.length;
        for (int i = 0; i < numOfStacks; i++) {
            CargoStack tempStack = new CargoStack();
            int stackSize = this.stacks[i].size();
            for (int j = 0; j < stackSize; j++) {
                if (this.stacks[i].empty()) {
                    continue;
                }
                // Popping corresponding stack
                Cargo tempCargo = this.stacks[i].pop();
                if (tempCargo.getName().equals(cargoName)) {
                    foundOne = true;
                }
                tempStack.push(tempCargo);
            }

            // Returns elements from tempStack back to the corresponding stack
            int tempSize = tempStack.size();
            for (int j = 0; j < tempSize; j++) {
/*                if (tempStack.empty()) {
                    continue;
                }*/
                this.stacks[i].push(tempStack.pop());
            }
        }

        if (foundOne) {
            return true;
        } else {
            return false;
        }
    }
}