import java.util.Stack;

/**
 * This class represents a stack of containers of Cargo, which represents a
 * physical stack of cargo on a ship.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class CargoStack extends Stack<Cargo>{

    /**
     * This is a method that pushes a new piece of cargo to the dock stack.
     * This method first checks if the new piece of cargo can be placed on top
     * of the top piece of cargo on the dock. If it fulfills the rules of Cargo
     * Strength, then the new piece of cargo can be pushed on top of the dock.
     * If not, then an exception is thrown.
     *
     * @param cargo
     * Cargo variable that represents the new piece of cargo.
     *
     * @throws CargoStrengthException
     * Thrown if the new piece of cargo violates the rules of Cargo Strength
     * when compared with the top piece of cargo on the dock.
     */
    public void pushToDock (Cargo cargo) throws CargoStrengthException {
        this.checkStrengthConditions(cargo);

        // Assuming no exceptions were thrown!
        this.push(cargo);
    }

    /**
     * This is a method that checks if a new piece of cargo fulfills the rules
     * of Cargo Strength. If the stack is empty, or if the top cargo on a stack
     * has the cargo strength of 'FRAGILE', then the new piece of cargo can be
     * pushed onto the stack. If not, then the rules get checked, and then only
     * pushed onto the stack.
     *
     * @param cargo
     * Cargo variable that represents the new piece of cargo.
     *
     * @throws CargoStrengthException
     * Thrown if the new piece of cargo violates the rules of Cargo Strength
     * when compared with the top piece of cargo on a stack.
     */
    public void checkStrengthConditions (Cargo cargo) throws CargoStrengthException {
        switch (cargo.getStrength()) {
            case FRAGILE:
                // Good for all cases! (FRAGILE objects can stack on top of FRAGILE, MODERATE, and STURDY objects!)
                break;

            case MODERATE:
                if (this.empty()) {
                    // Good, if the dock is empty!
                    break;
                }

                if (this.peek().getStrength() == CargoStrength.FRAGILE) {
                    throw new CargoStrengthException();
                }
                // MODERATE objects can stack on top of other MODERATE objects and STURDY objects, but not FRAGILE objects
                break;

            case STURDY:
                if (this.empty()) {
                    // Good, if the dock is empty!
                    break;
                }

                if ((this.peek().getStrength() == CargoStrength.FRAGILE) || (this.peek().getStrength() == CargoStrength.MODERATE)) {
                    throw new CargoStrengthException();
                }
                // STURDY objects can only stack on top of other STURDY objects
                break;

            default:
                System.out.println("Invalid Cargo Strength! How?!?!?");
                break;
        }
    }

    /**
     * This is a method that clears all the cargo in the dock. It pops all the
     * cargo off the dock stack.
     */
    public void clearDock() {
        int dockSize = this.size();
        for (int i = 0; i < dockSize; i++) {
            this.pop();
        }
    }
}
