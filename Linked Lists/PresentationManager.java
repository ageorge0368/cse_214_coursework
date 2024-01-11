import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class represents a manager of a presentation, where the
 * PresentationManager class provides an interface for the user to manipulate
 * a presentation (or a SlideList object) with adding, removing and editing
 * slides.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class PresentationManager {
    //Main Function
    /**
     * This method is responsible for running the entire program for managing
     * a presentation. This is a menu-driven application where you can select
     * your Slide, utilize all the functions from the SlideList class, and quit
     * from the menu by typing "Q" for Quit. The menu is driven by a while loop
     * that continuously loops until a "Q" is typed as a menu option.
     */
    public static void main (String[] args) {
        // Create new SlideList
        SlideList slideShow = new SlideList();

        Scanner stdin = new Scanner(System.in);

        // For menu (variables)
        char menuCharOption = ' ';
        Slide newInputSlide;
        String slideTitle;
        double slideDuration;
        String smallerMenuOption;
        char smallerMenuOptionChar;
        int bulletNum;
        boolean forLoopBreak;
        String endingStatement = "";
        boolean menuBool = true;

        System.out.println("Welcome to PresentationManager!");
        while (menuBool) {
            try {
                String menuOption = "";

                System.out.println("\nPlease select an option:");
                System.out.println("F) Move cursor forward");
                System.out.println("B) Move cursor backward");
                System.out.println("D) Display cursor slide");
                System.out.println("E) Edit cursor slide");
                System.out.println("P) Print presentation summary");
                System.out.println("A) Append new slide to tail");
                System.out.println("I) Insert new slide before cursor");
                System.out.println("R) Remove slide at cursor");
                System.out.println("H) Reset cursor to head");
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
                    case 'F':
                        try {
                            slideShow.cursorForward();
                            System.out.println("The cursor moved forward to slide '" + slideShow.getCursorSlide().getTitle() + "'.\n");
                        } catch (EndOfListException x) {
                            System.out.println("Cursor is at the tail of the list! Try again!");
                            endingStatement = "end of list cannot move forward";
                        } catch (NullPointerException x) {
                            endingStatement = "end of list cannot move forward";
                        }
                        break;

                    case 'B':
                        try {
                            slideShow.cursorBackward();
                            System.out.println("The cursor moved backward to slide '" + slideShow.getCursorSlide().getTitle() + "'.\n");
                        } catch (EndOfListException x) {
                            System.out.println("Cursor is at the head of the list! Try again!");
                            endingStatement = "end of list cannot move backward";
                        }
                        break;

                    case 'D':
                        try {
                            slideShow.displayCursor();
                        }
                        catch (NullPointerException x) {
                            System.out.println("You cannot display an empty slide! Try again!");
                            endingStatement = "empty slideshow";
                        }
//                        slideShow.displayCursor();
                        break;

                    case 'E':
                        if (slideShow.getCursorSlide() == null) {
                            System.out.println("You cannot edit an empty slide! Try again!");
                            endingStatement = "empty slideshow";
                            break;
                        }
                        System.out.println("Edit title, duration, or bullets? (t/d/b): ");
                        smallerMenuOption = stdin.nextLine().toLowerCase();
                        smallerMenuOptionChar = smallerMenuOption.charAt(0);

                        String oldSlideTitle = slideShow.getCursorSlide().getTitle();

                        switch (smallerMenuOptionChar) {
                            case 't':
                                // Title case
                                System.out.println("New title: ");
                                slideTitle = stdin.nextLine();
                                // Editing the title
                                slideShow.getCursorSlide().setTitle(slideTitle);
//                                System.out.println("'" + slideShow.getCursorSlide().getTitle() + "' slide's title has been edited.\n");
                                System.out.println("'" + oldSlideTitle + "' slide's title has been edited.\n");
                                break;

                            case 'd':
                                // Duration case
                                System.out.println("New duration: ");
                                slideDuration = stdin.nextDouble();
                                // Editing the title
                                slideShow.getCursorSlide().setDuration(slideDuration);
                                System.out.println("'" + oldSlideTitle + "' slide's duration has been edited.\n");
                                break;

                            case 'b':
                                // Bullets case
                                System.out.println("Bullet index: ");
                                bulletNum = stdin.nextInt();

                                String deleteOrEdit = "";
                                System.out.println("Delete or edit? (d/e): ");
                                while (deleteOrEdit.equals("")) {
                                    // Converting menu option to upper case everytime
                                    deleteOrEdit = stdin.nextLine().toLowerCase();
                                }
                                char deleteOrEditChar = deleteOrEdit.charAt(0);
                                if (deleteOrEditChar == 'd') {
                                    // Deleting a bullet
                                    try {
                                        slideShow.getCursorSlide().setBullet(null, (bulletNum));
                                        System.out.println("Bullet " + bulletNum + " has been deleted.\n");
                                    } catch (IllegalArgumentException x) {
                                        System.out.println("Bullet is not in valid range! Try again!");
                                    }
//                                    slideShow.getCursorSlide().setBullet(null, (bulletNum));
//                                    System.out.println("Bullet " + bulletNum + " has been deleted.\n");

                                } else if (deleteOrEditChar == 'e') {
                                    // Editing a bullet
                                    System.out.println("Bullet " + bulletNum + ": ");
                                    String newBullet = stdin.nextLine();
                                    slideShow.getCursorSlide().setBullet(newBullet, (bulletNum));
                                    System.out.println("Bullet " + bulletNum + " has been edited.\n");
                                }
                                break;

                            default:
                                System.out.println("Invalid choice! Try again!\n");
                                break;
                        }
                        break;

                    case 'P':
                        try {
                            slideShow.printSlideShow();
                        } catch (NullPointerException x) {
                            System.out.println("You cannot print an empty slideshow! Try again!");
                            endingStatement = "==============================================" + "\nTotal: 0 slide(s), 0.0 minute(s), 0 bullet(s)";
                        }
                        break;

                    case 'A':

                        newInputSlide = new Slide();
                        System.out.println("Enter the slide title: ");
                        slideTitle = stdin.nextLine();
                        System.out.println("Enter the slide duration: ");
                        slideDuration = stdin.nextDouble();

                        // Setting title and duration of new slide
                        newInputSlide.setTitle(slideTitle);
                        newInputSlide.setDuration(slideDuration);

                        smallerMenuOption = "y";
                        smallerMenuOptionChar = smallerMenuOption.charAt(0);

                        bulletNum = 0;
//                        String bulletString = "";
                        forLoopBreak = false;

                        for (int i = 0; i < 5; i++) {
                            String bulletString = "";

                            switch (smallerMenuOptionChar){
                                case 'y':
                                    System.out.println("Bullet " + (bulletNum + 1) + ": ");
                                    while (bulletString.equals("")) {
                                        // Converting menu option to upper case everytime
                                        bulletString = stdin.nextLine();
                                        // Adding new bullet to new slide
                                        newInputSlide.setBullet(bulletString, i);
                                    }
                                    // Incrementing bulletNum
                                    bulletNum++;
                                    if (bulletNum >= 5) {
                                        System.out.println("No more bullets allowed. Slide is full.\n");
                                        forLoopBreak = true;
                                        break;                              // Meant to be break out of while loop
                                    }

                                    System.out.println("Add another bullet point?: (y/n)");
                                    smallerMenuOption = stdin.nextLine().toLowerCase();
                                    smallerMenuOptionChar = smallerMenuOption.charAt(0);

                                    break;

                                case 'n':
                                    forLoopBreak = true;
                                    break;

                                default:
                                    System.out.println("Wrong character! Try again!");
                                    System.out.println("Add another bullet point?: (y/n)");
                                    smallerMenuOption = stdin.nextLine().toLowerCase();
                                    smallerMenuOptionChar = smallerMenuOption.charAt(0);
                                    break;
                            }

                            if (forLoopBreak) {
                                break;
                            }
                        }

                        // Appending new slide into tail of SlideList
                        try {
                            slideShow.appendToTail(newInputSlide);
//                            System.out.println("Slide '" + newInputSlide.getTitle() + "' added to presentation.\n");
                            System.out.println("Slide " + "\"" + newInputSlide.getTitle() + "\"" + " added to presentation.\n");
                        } catch (IllegalArgumentException x) {
                            System.out.println("newSlide is equal to null! Try again!\n");
                        }
                        break;

                    case 'I':
                        newInputSlide = new Slide();
                        System.out.println("Enter the slide title: ");
                        slideTitle = stdin.nextLine();
                        System.out.println("Enter the slide duration: ");
                        slideDuration = stdin.nextDouble();

                        // Setting title and duration of new slide
                        newInputSlide.setTitle(slideTitle);
                        newInputSlide.setDuration(slideDuration);

                        smallerMenuOption = "y";
                        smallerMenuOptionChar = smallerMenuOption.charAt(0);

                        bulletNum = 0;
                        forLoopBreak = false;

                        for (int i = 0; i < 5; i++) {
                            String bulletString = "";

                            switch (smallerMenuOptionChar){
                                case 'y':
                                    System.out.println("Bullet " + (bulletNum + 1) + ": ");
                                    while (bulletString.equals("")) {
                                        // Converting menu option to upper case everytime
                                        bulletString = stdin.nextLine();
                                        // Adding new bullet to new slide
                                        newInputSlide.setBullet(bulletString, i);
                                    }
                                    // Incrementing bulletNum
                                    bulletNum++;
                                    if (bulletNum >= 5) {
                                        System.out.println("No more bullets allowed. Slide is full.\n");
                                        forLoopBreak = true;
                                        break;                              // Meant to be break out of while loop
                                    }

                                    System.out.println("Add another bullet point?: (y/n)");
                                    smallerMenuOption = stdin.nextLine().toLowerCase();
                                    smallerMenuOptionChar = smallerMenuOption.charAt(0);

                                    break;

                                case 'n':
                                    forLoopBreak = true;
                                    break;

                                default:
                                    System.out.println("Wrong character! Try again!");
                                    System.out.println("Add another bullet point?: (y/n)");
                                    smallerMenuOption = stdin.nextLine().toLowerCase();
                                    smallerMenuOptionChar = smallerMenuOption.charAt(0);
                                    break;
                            }

                            if (forLoopBreak) {
                                break;
                            }
                        }

                        // Inserting new slide before cursor of SlideList
                        try {
                            slideShow.insertBeforeCursor(newInputSlide);
                            System.out.println("Slide " + "\"" + newInputSlide.getTitle() + "\"" + " added to presentation.");
                        } catch (IllegalArgumentException x) {
                            System.out.println("newSlide is equal to null! Try again!\n");
                        } catch (NullPointerException x) {
                            endingStatement = "No more bullets allowed. Slide is full." + "\nSlide " + "\"" + newInputSlide.getTitle() + "\"" + " added to presentation.";
                        }

                        break;

                    case 'R':
                        Slide removedSlide = slideShow.getCursorSlide();
                        try {
                            removedSlide = slideShow.removeCursor();
                            System.out.println("Slide " + "\"" + removedSlide.getTitle() + "\"" + " has been removed.");
                            endingStatement = "Slide " + "\"" + removedSlide.getTitle() + "\"" + " has been removed.";

                        } catch (EndOfListException x) {
//                            System.out.println("Cursor is a null object! Try again!\n");
                            endingStatement = "Slide " + "\"" + removedSlide.getTitle() + "\"" + " has been removed.";
                        } catch (NullPointerException x) {
                            System.out.println("Cursor is a null object! Try again!\n");
                            endingStatement = "empty slideshow";
                        }
                        break;

                    case 'H':
                        try {
                            slideShow.resetCursorToHead();
                            System.out.println("The cursor has been reset to head.\n");
                        } catch (IllegalArgumentException x) {
                            System.out.println("You cannot reset to head on an empty slideshow! Try again!");
                            endingStatement = "empty slideshow";
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

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.\n");
                stdin.nextLine();
            }
        }
    }
}
