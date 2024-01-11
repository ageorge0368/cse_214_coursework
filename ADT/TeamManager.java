import javax.naming.InvalidNameException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents a manager of teams, where the Team Manager has an
 * array of teams that is 5 teams big.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class TeamManager {
    // Static Variables
    /* A Team Manager object has a maximum of 5 teams. */
    public static final int MAX_TEAMS = 5;

    // Data Fields (Member Variables)
    /* A Team Manager object has a private data field representing the array of
    * teams that a team manager can manage. */
    private Team[] teams = new Team[MAX_TEAMS];

    // Constructor
    /**
     * This is a constructor that creates a new TeamManager object. It
     * initializes the array of teams to an array that is 5 elements long.
     * At the start, the array of teams is initially initialized with
     * 5 teams made with the default Team constructor.
     */
    public TeamManager () {
        this.teams = new Team[MAX_TEAMS];

        for (int i = 0; i < MAX_TEAMS; i++) {
            Team defaultTeam = new Team();
            this.teams[i] = defaultTeam;
        }
    }

    // Main Function
    /**
     * This method is responsible for running the entire program for managing
     * all 5 teams. This is a menu-driven application where you can select your
     * team, utilize all the functions from the Team class, and quit from by
     * typing "Q" for Quit. The menu is driven by a while loop that continuously
     * loops until a "Q" is typed as a menu option.
     */
    public static void main (String[] args) {
        // Create new TeamManager
        TeamManager teamManager = new TeamManager();

        Scanner stdin = new Scanner(System.in);
        Team firstTeam = new Team();
        int teamIndex = 0;
        int secondTeamIndex;
        teamManager.teams[teamIndex] = firstTeam;

        // For menu (variables)
        String playerName;
        int playerHits, playerErrors, playerPosition, playerStatNum;
        String playerStat;
        String endingStatement = "";

        boolean menuBool = true;

        System.out.println("Welcome to TeamManager!");
        System.out.println("Team 1 is currently selected.");
        while (menuBool) {
            try {
                // To help with taking care of the extra "Enter" key after every menu is selected.
                // Helps with making the menu navigation easier
                String menuOption = "";

                System.out.println("Please select an option:");
                System.out.println("A)  Add Player.");
                System.out.println("G)  Get Player stats.");
                System.out.println("L)  Get leader in a stat.");
                System.out.println("R)  Remove a player.");
                System.out.println("P)  Print all players.");
                System.out.println("S)  Size of team.");
                System.out.println("T)  Select team.");
                System.out.println("C)  Clone team.");
                System.out.println("E)  Team equals.");
                System.out.println("U)  Update stat.");
                System.out.println("Q)  Quit.");

                // While loop added here for better menu navigation
                System.out.println("Select a menu option: ");
                while (menuOption.equals("")) {
                    // Converting menu option to upper case everytime
                    menuOption = stdin.nextLine().toUpperCase();
                }

                char menuCharOption = menuOption.charAt(0);

                switch (menuCharOption) {
                    case 'A':
                        System.out.println("Enter the player's name: ");
                        playerName = stdin.nextLine();
                        System.out.println("Enter the number of hits: ");
                        playerHits = stdin.nextInt();
                        System.out.println("Enter the number of errors: ");
                        playerErrors = stdin.nextInt();
                        System.out.println("Enter the position: ");
                        playerPosition = stdin.nextInt();

                        Player newPlayer = new Player();
                        newPlayer.setName(playerName);
                        newPlayer.setNumHits(Integer.valueOf(playerHits));
                        newPlayer.setNumErrors(Integer.valueOf(playerErrors));

                        // Adding new player to the first team
                        try {
                            teamManager.teams[teamIndex].addPlayer(newPlayer, (playerPosition));
                            System.out.println("Player Added: " + teamManager.teams[teamIndex].getPlayer(playerPosition).toString());
                        } catch (IllegalArgumentException x) {
                            System.out.println("Invalid Position! Position is not in valid range!\n");
                            endingStatement = "invalid position for adding the new player";
                        } catch (FullTeamException y) {
                            System.out.println("There is a full team here! Cannot add a player!\n");
                        }
                        break;

                    case 'G':
                        System.out.println("Enter the position: ");
                        playerPosition = stdin.nextInt();
                        try {
                            System.out.println(teamManager.teams[teamIndex].getPlayer(playerPosition).toString());
                        } catch (IllegalArgumentException x) {
                            System.out.println("Invalid Position! Position is not in valid range!\n");
                        }
                        break;

                    case 'L':
                        System.out.println("Enter the stat: ");
                        System.out.println("(Enter the stat as either 'hits' or 'errors'.)");
                        playerStat = stdin.nextLine();
                        try {
                            System.out.println("Leader in " + playerStat + ": " + teamManager.teams[teamIndex].getLeader(playerStat).toString());
                        } catch (IllegalArgumentException x) {
                            System.out.println("Invalid stat! It was neither 'hits' or 'errors'!\n");
                            endingStatement = "no such statistic";
                        }
                        break;

                    case 'R':
                        System.out.println("Enter the position: ");
                        playerPosition = stdin.nextInt();
                        try {
                            String removedPlayerName = teamManager.teams[teamIndex].getPlayer(playerPosition).getName();
                            teamManager.teams[teamIndex].removePlayer(playerPosition);
                            endingStatement = "player removed at position " + playerPosition + "\n" + removedPlayerName + " has been removed from the team";
                        } catch (IllegalArgumentException x) {
                            System.out.println("Invalid position! Try again!.\n");
                            endingStatement = "no player at position " + playerPosition + " to remove";
                        }
                        break;

                    case 'P':
                        // Print table of players
                        System.out.println("Select team index: ");
                        teamIndex = stdin.nextInt();
                        if (teamIndex > 5) {
                            System.out.println("Selected team is out of bounds! Try again!\n");
                            stdin.nextLine();
                            continue;
                        }
                        teamIndex = teamIndex - 1;
                        teamManager.teams[teamIndex].printAllPlayers();
                        stdin.nextLine();
                        break;

                    case 'S':
                        System.out.println("There are " + teamManager.teams[teamIndex].size() + " player(s) in the current team.\n");
                        break;

                    case 'T':
                        System.out.println("Enter team index to select: ");
                        int oldTeamIndex = teamIndex;
                        teamIndex = stdin.nextInt();
                        teamIndex = teamIndex - 1;
                        if (!((teamIndex >= 0 && teamIndex <= 4))) {
                            teamIndex = oldTeamIndex;
                            System.out.println("Invalid team index! Team indexes are only between 1-5, try again!\n");
                            endingStatement = "invalid index for team";
                        } else {
                            System.out.println("Team " + (teamIndex + 1) + " has been selected.\n");
                        }
                        break;

                    case 'C':
                        System.out.println("Select team to clone from: ");
                        teamIndex = stdin.nextInt();
                        System.out.println("Select team to clone to: ");
                        secondTeamIndex = stdin.nextInt();

                        if (teamIndex > 5 || secondTeamIndex > 5) {
                            System.out.println("One of the teams selected are out of bounds! Try again!\n");
                            stdin.nextLine();
                            continue;
                        }

                        teamManager.teams[secondTeamIndex - 1] = (Team) teamManager.teams[teamIndex - 1].clone();
                        System.out.println("Team " + teamIndex + " has been copied to team " + secondTeamIndex + "." + "\n");
                        stdin.nextLine();
                        break;

                    case 'E':
                        System.out.println("Select first team index: ");
                        teamIndex = stdin.nextInt();
                        System.out.println("Select second team index: ");
                        secondTeamIndex = stdin.nextInt();

                        if (teamIndex > 5 || secondTeamIndex > 5) {
                            System.out.println("One of the team are out of bounds! Try again!\n");
                            stdin.nextLine();
                            continue;
                        }

                        boolean isTeamEquals = teamManager.teams[teamIndex - 1].equals(teamManager.teams[secondTeamIndex - 1]);
                        if (isTeamEquals) {
                            System.out.println("These teams are equal.\n");
                        } else {
                            System.out.println("These teams are not equal.\n");
                        }
                        stdin.nextLine();
                        break;

                    case 'U':
                        System.out.println("Enter the player to update: ");
                        playerName = stdin.nextLine();
                        System.out.println("Enter stat to update: ");
                        System.out.println("(Enter the stat as either 'hits' or 'errors'.)");
                        playerStat = stdin.nextLine();
                        System.out.println("Enter the new number of " + playerStat + ": ");
                        playerStatNum = stdin.nextInt();

                        // Update the stat
                        try {
                            teamManager.teams[teamIndex].updateStat(playerName, playerStat, playerStatNum);
                        } catch (IllegalArgumentException x) {
                            System.out.println(x.getMessage());
                            endingStatement = "invalid update error";
                        } catch (InvalidNameException y) {
                            System.out.println(y.getMessage());
                            endingStatement = "player not found";
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
            }
            catch (InputMismatchException e){
                System.out.println("Invalid input. Try again.\n");
                stdin.nextLine();
            }
        }
    }
}
