import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Math;

/**
 * This class represents a ship loader, where the ShipLoader class provides an
 * interface for the user to create a cargo ship, initialize all the ship's
 * aspects, push cargo and pop cargo, along with use a Dock to load cargo from
 * the Dock into the ship.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class ShipLoader {
    // Main Function
    /**
     * This method is responsible for running the entire program for managing
     * a cargo ship. This is a menu-driven application where you can select
     * your stack and cargo, utilize all the functions from the CargoShip
     * class, and quit from the menu by typing "Q" for Quit. The menu is driven
     * by a while loop that continuously loops until a "Q" is typed as a menu
     * option.
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        // CargoShip variables
        int initNumStacks, initMaxHeight;
        double initMaxWeight;

        System.out.println("Welcome to ShipLoader!");
        System.out.println("\nCargo Ship Parameters");
        System.out.println("--------------------------------------------------");
        System.out.println("Number of Stacks: ");
        initNumStacks = stdin.nextInt();
        System.out.println("Maximum height of stacks: ");
        initMaxHeight = stdin.nextInt();
        System.out.println("Maximum total cargo weight: ");
        initMaxWeight = stdin.nextDouble();

        // Create new CargoShip
        CargoShip cargoShip = new CargoShip(initNumStacks, initMaxHeight, initMaxWeight);

        System.out.println("Cargo ship created.");
        System.out.println("Pulling ship in to dock...");
        System.out.println("Cargo ship ready to be loaded.");

        // Creating a Dock
        CargoStack dock = new CargoStack();

        // For menu (variables)
        boolean menuBool = true;
        char menuCharOption = ' ';
        String endingStatement = "";
        String newCargoName = "";
        double newCargoWeight;
        String newCargoStrengthString = "";
        char newCargoStrengthChar = ' ';
        Cargo topOfDock = new Cargo();
        int stackIndex1, stackIndex2;
        String cargoName = "";

        while (menuBool) {
            try {
                endingStatement = "cargo ship created. \npulling ship in to dock...\ncargo ship ready to be loaded.";
                String menuOption = "";

                System.out.println("\nPlease select an option:");
                System.out.println("C) Create new cargo");
                System.out.println("L) Load cargo from dock");
                System.out.println("U) Unload cargo from ship");
                System.out.println("M) Move cargo between stacks");
                System.out.println("K) Clear dock");
                System.out.println("P) Print ship stacks");
                System.out.println("S) Search for cargo");
                System.out.println("R) Remove cargo");
                System.out.println("Q) Quit\n");

                // While loop added here for better menu navigation
                System.out.println("Select a menu option: ");
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
                    case 'C':
                        System.out.println("Enter the name of the cargo: ");
                        newCargoName = stdin.nextLine();
                        System.out.println("Enter the weight of the cargo: ");
                        newCargoWeight = stdin.nextDouble();
                        System.out.println("Enter the cargo strength (F/M/S): ");
//                        newCargoStrengthString = stdin.nextLine().toUpperCase();
                        newCargoStrengthString = "";
                        while (newCargoStrengthString.equals("")) {
                            // Converting menu option to upper case everytime
                            newCargoStrengthString = stdin.nextLine().toUpperCase();
                        }
                        // This doesn't work
                        if ((!newCargoStrengthString.equals("F")) && (!newCargoStrengthString.equals("M")) && (!newCargoStrengthString.equals("S"))) {
                            System.out.println("Invalid input for cargo strength! Try again!");
                            break;
                        } else {
                            newCargoStrengthChar = newCargoStrengthString.charAt(0);
                        }

                        // Assigning cargo strength
                        CargoStrength newCargoStrength = null;
                        if (newCargoStrengthChar == 'F') {
                            newCargoStrength = CargoStrength.FRAGILE;
                        } else if (newCargoStrengthChar == 'M') {
                            newCargoStrength = CargoStrength.MODERATE;
                        } else if (newCargoStrengthChar == 'S') {
                            newCargoStrength = CargoStrength.STURDY;
                        }

                        try {
                            Cargo newCargo = new Cargo(newCargoName, newCargoWeight, newCargoStrength);
                            // Pushes to dock, also checks for Cargo Strengths
                            dock.pushToDock(newCargo);
                            System.out.println("Cargo '" + dock.peek().getName() + "' pushed onto the dock.\n");
                            // Print stacks and dock
                            miniPrintStacks(cargoShip, dock);
//                            System.out.println("\nTotal Weight = " + cargoShip.totalWeight() + " / " + cargoShip.getMaxWeight());
                        } catch (IllegalArgumentException x) {
                            System.out.println("Either the cargo name or the cargo weight are invalid! Try again!");
                        } catch (CargoStrengthException y) {
//                            System.out.println("You cannot add a piece of cargo that is heavier than the last one added! Try again!");
                            System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                        }
                        break;

                    case 'L':
                        System.out.println("Select the load destination stack index: ");
                        stackIndex1 = stdin.nextInt();
                        try {
                            topOfDock = dock.peek();
                            loadDocktoShip(cargoShip, dock, stackIndex1);
//                            cargoShip.pushCargo(topOfDock, stackIndex1);
//                            dock.pop();
                            System.out.println("Cargo " + "'" + topOfDock.getName() + "' moved from dock to stack " + stackIndex1 + ".\n");
                            // Print stacks and dock
                            miniPrintStacks(cargoShip, dock);
//                            stackIndex1 = 0;
//                            System.out.println("\nTotal Weight = " + cargoShip.totalWeight() + " / " + cargoShip.getMaxWeight());
                        } catch (CargoStrengthException v) {
                            System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
//                            System.out.println("Operation failed! Cargo at top of stack cannot support weight from dock.");
                        } catch (ShipOverweightException w) {
//                            System.out.println("Adding this cargo will go over the maximum weight allowed for the ship! Try again!");
                            System.out.println("operation failed! cargo would put ship overweight.");
//                            endingStatement = "operation failed! cargo would put ship overweight.";
                        } catch (FullStackException x) {
                            System.out.println("Operation failed! Cargo stack is at maximum height.");
                        } catch (IllegalArgumentException y) {
                            System.out.println("Either your cargo is null, or the stack index is invalid! Try again!");
                        } catch (EmptyStackException_2 z) {
                            System.out.println("Dock is empty right now, cannot load from dock! Try again!");
                        } catch (EmptyStackException a) {
                            System.out.println("Dock is empty right now. Cannot load from dock! Try again!");
                        }
                        break;

                    case 'U':
                        System.out.println("Select the unload source stack index: ");
                        stackIndex1 = stdin.nextInt();
                        topOfDock = cargoShip.getStacks()[stackIndex1 - 1].peek();
                        try {
                            unloadStacktoDock(cargoShip, dock, stackIndex1);
//                            dock.pushToDock();
                            System.out.println("Cargo " + "'" + topOfDock.getName() + "' moved from stack " + stackIndex1 + " to dock.");
//                            System.out.println("Cargo moved from stack " + stackIndex1 + " to dock.\n");
                            // Print stacks and dock
                            miniPrintStacks(cargoShip, dock);
//                            stackIndex1 = 0;
//                            System.out.println("\nTotal Weight = " + cargoShip.totalWeight() + " / " + cargoShip.getMaxWeight());
                        } catch (CargoStrengthException x) {
                            System.out.println("Operation failed! Cargo at top of dock cannot support weight from stack.");
                        } catch (EmptyStackException_2 y) {
                            System.out.println("You are popping an empty stack! Try again!");
                        } catch (IllegalArgumentException z) {
                            System.out.println("The stack index is out of range! Try again!");
                        }
                        break;

                    case 'M':
                        System.out.println("Source stack index: ");
                        stackIndex1 = stdin.nextInt();
                        System.out.println("Destination stack index: ");
                        stackIndex2 = stdin.nextInt();
                        try {
                            Cargo tempCargo = cargoShip.getStacks()[stackIndex1 - 1].peek();
                            cargoShip.pushCargo(cargoShip.getStacks()[stackIndex1 - 1].peek(), stackIndex2);
                            cargoShip.popCargo(stackIndex1);
                            System.out.println("\nCargo '" + tempCargo.getName() + "' moved from stack " + stackIndex1 + " to stack " + stackIndex2 + ".\n");
                            miniPrintStacks(cargoShip, dock);
                        } catch (ShipOverweightException e) {
                            System.out.println("This shouldn't matter! You're all good!");
                        } catch (CargoStrengthException e) {
                            System.out.println("Operation failed! Cargo at top of stack " + stackIndex2 + "cannot support weight from stack " + stackIndex1);
                        } catch (FullStackException e) {
                            System.out.println("Operation failed! Cargo stack is at maximum height.");
                        } catch (EmptyStackException_2 e) {
                            System.out.println("You are popping an empty stack! Try again!");
                        }
                        break;

                    case 'K':
                        dock.clearDock();
                        System.out.println("Dock cleared.");
                        miniPrintStacks(cargoShip, dock);
                        break;

                    case 'P':
                        System.out.println();
                        miniPrintStacks(cargoShip, dock);
                        break;

                    case 'S':
                        System.out.println("Enter the name of the cargo: ");
                        cargoName = stdin.nextLine();
                        String searchTable = cargoShip.findAndPrint(cargoName);
                        endingStatement = searchTable;
                        break;

                    case 'R':
                        System.out.println("What item would you like to remove?: ");
                        cargoName = stdin.nextLine();
                        // Clearing dock
                        dock.clearDock();
                        System.out.println("\nDock cleared.");
                        System.out.println("Removing cargo '" + cargoName + "'.");
                        try {
                            removeCargo(cargoShip, dock, cargoName);
                        } catch (CargoStrengthException x) {
                            System.out.println("Unable to delete all instances of the cargo! Try again!");
                        }
                        break;

                    case 'Q':
                        menuBool = false;
                        System.out.println("Program terminating normally...");
                        System.out.println(endingStatement);
                        break;

                    default:
                        System.out.println("Wrong character! Try again!\n");
                        endingStatement = "invalid";
                        break;
                }


            } catch (InputMismatchException x) {
                System.out.println("Invalid input. Try again.\n");
                stdin.nextLine();
            }
        }
    }

    // Other Functions
    /**
     * This method is responsible for printing a small representation of each
     * of the stacks in a cargo ship along with the dock. It represents cargo
     * that is 'STURDY' with a 'S', 'MODERATE' with a 'M', and 'FRAGILE' with
     * an 'F'. It also shows the total weight of the cargo in the cargo ship,
     * along with the maximum weight allowed for the cargo ship.
     *
     * @param cargoShip
     * CargoShip object that represents the cargo ship from the main function.
     *
     * @param dock
     * CargoStack object that represents the dock from the main function.
     */
    public static void miniPrintStacks(CargoShip cargoShip, CargoStack dock) {
        int numStacks = 1;
        String stackStrengths = "";
        // Printing Stack
        int numOfStacks = cargoShip.getStacks().length;
        for (int i = 0; i < numOfStacks; i++) {
            System.out.print("Stack " + numStacks + ": ");
            if (cargoShip.getStacks()[i] == null) {
                continue;
            }

            if (cargoShip.getStacks()[i].size() == 1) {
                if (cargoShip.getStacks()[i].peek().getStrength() == CargoStrength.FRAGILE) {
                    stackStrengths = "F\n";
                } else if (cargoShip.getStacks()[i].peek().getStrength() == CargoStrength.MODERATE) {
                    stackStrengths = "M\n";
                } else if (cargoShip.getStacks()[i].peek().getStrength() == CargoStrength.STURDY) {
                    stackStrengths = "S\n";
                }

                System.out.print(stackStrengths);

            } else {
                Stack<Cargo> tempStack = new Stack<>();
                Cargo tempCargo, tempPoppedCargo;
                int stackSize = cargoShip.getStacks()[i].size();
                for (int j = 0; j < stackSize; j++) {
                    tempCargo = cargoShip.getStacks()[i].pop();
                    stackStrengths = "";

                    // Adding stack elements to temp stack
                    tempStack.push(tempCargo);
                }

                for (int k = 0; k < stackSize; k++) {
                    if (k == (stackSize - 1)) {
                        if (tempStack.peek().getStrength() == CargoStrength.FRAGILE) {
                            stackStrengths = "F";
                        } else if (tempStack.peek().getStrength() == CargoStrength.MODERATE) {
                            stackStrengths = "M";
                        } else if (tempStack.peek().getStrength() == CargoStrength.STURDY) {
                            stackStrengths = "S";
                        }
                        // Printing out result
                        System.out.print(stackStrengths);

                        // Pop the temp stack, and return contents of OG stack
                        tempPoppedCargo = tempStack.pop();
                        cargoShip.getStacks()[i].push(tempPoppedCargo);

                        // Break out of for loop for last case
                        break;
                    }

                    if (tempStack.peek().getStrength() == CargoStrength.FRAGILE) {
                        stackStrengths = "F, ";
                    } else if (tempStack.peek().getStrength() == CargoStrength.MODERATE) {
                        stackStrengths = "M, ";
                    } else if (tempStack.peek().getStrength() == CargoStrength.STURDY) {
                        stackStrengths = "S, ";
                    }

                    // Printing out result
                    System.out.print(stackStrengths);

                    tempPoppedCargo = tempStack.pop();
                    cargoShip.getStacks()[i].push(tempPoppedCargo);
                }
                System.out.println();
            }
            numStacks++;
        }

        System.out.print("Dock: ");
        if (dock.size() == 1) {
            if (dock.peek().getStrength() == CargoStrength.FRAGILE) {
                stackStrengths = "F";
            } else if (dock.peek().getStrength() == CargoStrength.MODERATE) {
                stackStrengths = "M";
            } else if (dock.peek().getStrength() == CargoStrength.STURDY) {
                stackStrengths = "S";
            }

            System.out.print(stackStrengths);
        } else {
            Stack<Cargo> tempStack = new Stack<>();
            Cargo tempCargo, tempPoppedCargo;
            int stackSize = dock.size();
            for (int j = 0; j < stackSize; j++) {
                tempCargo = dock.pop();
                stackStrengths = "";

                // Adding stack elements to temp stack
                tempStack.push(tempCargo);
            }

            for (int k = 0; k < stackSize; k++) {
                if (k == (stackSize - 1)) {
                    if (tempStack.peek().getStrength() == CargoStrength.FRAGILE) {
                        stackStrengths = "F";
                    } else if (tempStack.peek().getStrength() == CargoStrength.MODERATE) {
                        stackStrengths = "M";
                    } else if (tempStack.peek().getStrength() == CargoStrength.STURDY) {
                        stackStrengths = "S";
                    }
                    // Printing out result
                    System.out.print(stackStrengths);

                    // Pop the temp stack, and return contents of OG stack
                    tempPoppedCargo = tempStack.pop();
                    dock.push(tempPoppedCargo);

                    // Break out of for loop for last case
                    break;
                }

                if (tempStack.peek().getStrength() == CargoStrength.FRAGILE) {
                    stackStrengths = "F, ";
                } else if (tempStack.peek().getStrength() == CargoStrength.MODERATE) {
                    stackStrengths = "M, ";
                } else if (tempStack.peek().getStrength() == CargoStrength.STURDY) {
                    stackStrengths = "S, ";
                }

                // Printing out result
                System.out.print(stackStrengths);

                tempPoppedCargo = tempStack.pop();
                dock.push(tempPoppedCargo);
            }
            System.out.println();
        }
//        System.out.println("\nTotal Weight = " + cargoShip.totalWeight() + " / " + cargoShip.getMaxWeight());
        System.out.println("\nTotal Weight = " + String.format("%.0f", cargoShip.totalWeight()) + " / " + String.format("%.0f", cargoShip.getMaxWeight()));
//        String.format("%.0f", cargoShip.totalWeight());
    }

    /**
     * This method is responsible for pushing the cargo from the dock stack
     * onto a corresponding stack on the cargo ship. It first checks if the
     * dock is empty, if it is, then it would throw an exception saying that
     * you cannot load anything from the dock, as it is empty. Then, it pushes
     * the cargo with the .pushCargo() function onto the corresponding stack,
     * by checking and throwing exceptions for Cargo Strength, cargo ship
     * weight and reaching maximum height on a stack before pushing onto the
     * stack. The cargo from the dock is then popped.
     *
     * @param cargoShip
     * CargoShip object that represents the cargo ship from the main function.
     *
     * @param dock
     * CargoStack object that represents the dock from the main function.
     *
     * @param stackIndex
     * Int variable that represents the specific stack to push the cargo onto.
     *
     * @throws CargoStrengthException
     * Thrown when the cargo from the dock violates the Cargo Strength rules
     * with the cargo on top of the corresponding stack.
     *
     * @throws ShipOverweightException
     * Thrown when the cargo from the dock would make the total weight on the
     * ship exceed the maximum weight allowed.
     *
     * @throws FullStackException
     * Thrown when the cargo pushed onto the corresponding stack would exceed
     * the maximum height of the stack.
     *
     * @throws EmptyStackException_2
     * Thrown if the dock is empty.
     */
    public static void loadDocktoShip (CargoShip cargoShip, CargoStack dock, int stackIndex) throws CargoStrengthException, ShipOverweightException, FullStackException, EmptyStackException_2 {
        // If the dock is empty when called
        if (dock.isEmpty()) {
            throw new EmptyStackException_2();
        }

        cargoShip.pushCargo(dock.peek(), stackIndex);
        dock.pop();
    }

    /**
     * This method is responsible for unloading the top piece of cargo from a
     * corresponding stack on a cargo ship onto the dock. It first checks if
     * the dock is empty, if it is, then it can push the cargo from a stack
     * onto the dock without checking for the Cargo Strength rules. If the
     * dock is not empty, then it first checks the Cargo Strength rules of the
     * cargo and the top of the dock. If not violated, then the cargo is pushed
     * to the dock, and is popped from the corresponding stack. Else, then an
     * exception is thrown for cargo strength.
     *
     * @param cargoShip
     * CargoShip object that represents the cargo ship from the main function.
     *
     * @param dock
     * CargoStack object that represents the dock from the main function.
     *
     * @param stackIndex
     * Int variable that represents the specific stack to pop the cargo from,
     * and push onto the dock.
     *
     * @throws CargoStrengthException
     * Thrown when the cargo from the stack violates the Cargo Strength rules
     * with the cargo on top of the dock.
     *
     * @throws EmptyStackException_2
     * Thrown if the corresponding stack is empty.
     */
    public static void unloadStacktoDock (CargoShip cargoShip, CargoStack dock, int stackIndex) throws CargoStrengthException, EmptyStackException_2 {
        // Actual Stack Index (zero-indexed)
        int actualStackIndex = stackIndex - 1;

        // When the dock is empty (Edge case)
        if (dock.empty()) {
            Cargo tempCargo = cargoShip.getStacks()[actualStackIndex].peek();
            dock.push(tempCargo);
            cargoShip.popCargo(stackIndex);
            return;
        }

        // Checks if Cargo strengths are viable, returns CargoStrengthException if failed
        cargoShip.getStacks()[actualStackIndex].checkStrengthConditions(dock.peek());

        // Push the cargo to dock if viable
        Cargo tempCargo = cargoShip.getStacks()[actualStackIndex].peek();
        dock.push(tempCargo);
        cargoShip.popCargo(stackIndex);
    }

    public static void removeCargo (CargoShip cargoShip, CargoStack dock, String cargoName) throws CargoStrengthException {
        // Steps:
            // Find the first instances of the cargo (on top of a stack)
            // Once found, put into the dock
            // Then place middle cargo onto other stacks (follwing Cargo Strength rules)
            // Then place these cargo onto the dock

        int numOfStack = cargoShip.getStacks().length;
//        int maxMoves = (int) (Math.pow(2, cargoShip.getMaxHeight()) - 1);
        int maxMoves = cargoShip.getMaxHeight() * cargoShip.getStacks().length;
        int stepNum = 0;

        boolean breakOutofJ = false;
        boolean breakOutofI = false;

        for (int i = 0; i < maxMoves; i++) {
            if (breakOutofI) {
                break;
            }

            for (int j = 0; j < numOfStack; j++) {
                if (breakOutofJ) {
                    breakOutofI = true;
                    break;
                }

                // Empty case
                if (cargoShip.getStacks()[j].empty()) {
                    continue;
                }

                for (int k = 0; k < numOfStack; k++) {
                    if (cargoShip.searchCargo(cargoName) == false) {
                        breakOutofJ = true;
                        break;
                    }

                    // Empty case
                    if (cargoShip.getStacks()[j].empty()) {
                        continue;
                    }

                    if (cargoShip.getStacks()[j].peek().getName().equals(cargoName)) {
                        dock.push(cargoShip.getStacks()[j].peek());
                        cargoShip.getStacks()[j].pop();
                        stepNum++;
                        System.out.println("\nStep " + stepNum + ": Moved cargo from stack " + (j + 1) + " to dock.\n");
                        miniPrintStacks(cargoShip, dock);
                        continue;
                    }
//                }
                // Finding first instances of cargo and putting them into dock, and popping from stacks
/*                if (cargoShip.getStacks()[j].peek().getName().equals(cargoName)) {
                    dock.push(cargoShip.getStacks()[j].peek());
                    cargoShip.getStacks()[j].pop();
                    stepNum++;
                    System.out.println("Step " + stepNum + ": Moved cargo from stack " + (j + 1) + " to dock.");
                    miniPrintStacks(cargoShip, dock);
//                    continue;
                }*/
//                else {
//                    for (int k = 0; k < numOfStack; k++) {
//                        if (cargoShip.searchCargo(cargoName) == false) {
//                            breakOutofJ = true;
//                            break;
//                        }

                        // Check for cargo strength rules
                        Cargo tempCargo = cargoShip.getStacks()[j].peek();
                        cargoShip.getStacks()[k].checkStrengthConditions(tempCargo);

                        // Assuming that there are no exceptions!
                            // Push cargo from one stack to another
                        cargoShip.getStacks()[k].push(tempCargo);
                        cargoShip.getStacks()[j].pop();
                        stepNum++;
                        System.out.println("\nStep " + stepNum + ": Moved cargo from stack " + (j + 1) + " to " + (k + 1) + ".\n");
                        miniPrintStacks(cargoShip, dock);
                    }
            }
        }

        System.out.println("Cargo '" + cargoName + "' removed from the ship.");
        System.out.println("Steps taken: " + stepNum);
    }
}