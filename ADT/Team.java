import javax.naming.InvalidNameException;

/**
 * This class represents a player in a team, where a team has an array of
 * players that is 40 players long.
 *
 * @author Alan George
 * ID: 114484206
 * Recitation #: 03
 */
public class Team {
    // Static Variables
    /* A Team object has a maximum of 40 players in a team. The "size" variable
    * represents the size of a team. */
    public static final int MAX_PLAYERS = 40;
    public int size = 0;

    // Data Fields (Member Variables)
    /* A Team object has a private data field representing the array of players
    * that a team has. */
    private Player[] players;

    // Default Constructor
    /**
     * This is a constructor that creates a new Team object. It initializes
     * the array of players to an array that is 40 elements long. At the
     * start, the array of players is initially initialized with 40 players
     * made with the default Player constructor.
     */
    public Team () {
        this.players = new Player[MAX_PLAYERS];

        // Initializing all the elements in a Team object as a 40-long array of
        // default players
        for (int i = 0; i < MAX_PLAYERS; i++) {
            Player defaultPlayer = new Player();
            this.players[i] = defaultPlayer;
        }
    }

    // Getter
    /**
     * This method returns the array of players for a Team object.
     *
     * @return
     * The array of players representing the Team.
     */
    public Player[] getPlayers() {
        return players;
    }

    // Other Functions
    /**
     * This method clones the content of a team into another team. It creates a
     * deep copy of each of the elements in the team being copied for the team
     * that is getting copied into.
     *
     * @return
     * Returns a Team object that represents the team that has been copied into.
     */
    public Object clone() {
        Team cloneTeam = new Team();
        Player[] clonedPlayerArray = getPlayers();

        for (int j = 0; j < size; j++) {
            Player clonedPlayer = new Player();

            clonedPlayer.setName(clonedPlayerArray[j].getName());
            clonedPlayer.setNumHits(clonedPlayerArray[j].getNumHits());
            clonedPlayer.setNumErrors(clonedPlayerArray[j].getNumErrors());

            try {
                cloneTeam.addPlayer(clonedPlayer, j + 1);
            } catch (FullTeamException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return cloneTeam;
    }

    /**
     * This method compares if two teams are equal or not. Two teams are equal
     * if they have the same players and their stats, whilst having the players
     * in the same position.
     *
     * @param obj
     * The team that is being compared to being equal with
     *
     * @return
     * Returns a boolean value if two teams are equal or not. "true" if the
     * teams are equal, "false" if the teams are not equal.
     */
    public boolean equals(Object obj) {
        if (obj == null){                                   // Checking if the object even exists
            return false;
        }

        if (obj instanceof Team){
            Team secondTeam = (Team) obj;
            if (secondTeam.size() != this.size()){
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                boolean sameName = this.players[i].getName().equals(secondTeam.players[i].getName());
                boolean sameHits = this.players[i].getNumHits() == (secondTeam.players[i].getNumHits());
                boolean sameErrors = this.players[i].getNumErrors() == (secondTeam.players[i].getNumErrors());
                if (!(sameName && sameHits && sameErrors)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method returns the size of a team. The size is determined as the
     * number of actual players in a team (not the maximum numbers of players
     * allowed in a Team object).
     *
     * @return
     * Returns an integer value of the actual size of a team.
     */
    public int size() {
        return size;
    }

    /**
     * This method adds a player to a team in a position that is viable in the
     * team. If the position from the user is equal to the size of the team plus
     * one (size + 1), then the player is added at position. If the new
     * player's position already has a player there, then the array of players
     * for the team is shifted once to the right until the position from the
     * user, and then the new player is added. When a player is added, then the
     * size of a team is summed with one.
     *
     * @param p
     * The Player object that is being added to the team.
     * @param position
     * The integer representing the position where the new player is added to
     * the team.
     *
     * @throws IllegalArgumentException
     * Thrown when the position is not within the range of
     * 1 ≤ position ≤ size + 1.
     * @throws FullTeamException
     * Thrown when a team already has 40 valid players.
     */
    public void addPlayer(Player p, int position) throws IllegalArgumentException, FullTeamException{
        if (!((position >= 1) && (position <= (this.size() + 1)))) {
            throw new IllegalArgumentException();
        }

        if (this.size() == 40) {
            throw new FullTeamException();
        }

        if (this.players[position - 1].getName().equals("")) {
            this.players[position - 1] = p;
            size++;
        } else {
            // Moving all the spaces in the array one over; up until [position]
            for (int i = size; i >= position; i--) {
                this.players[i] = this.players[i - 1];
            }

            // Placing the new player into the wanted position
            this.players[position - 1] = p;
            size++;
        }
    }

    /**
     * This method removes a player to a team in a position that is viable in
     * the team. A player is removed from the team by shifting the players
     * right of the position by one; hence removing the player desired. At the
     * end, the player in position "size + 1" is ensured to be a default player
     * and the size of the team is decreased by one.
     *
     * @param position
     * The integer representing the position where the player is removed from.
     *
     * @throws IllegalArgumentException
     * Thrown when the position is not within the range of 1 ≤ position ≤ size.
     */
    public void removePlayer(int position) throws IllegalArgumentException{
        if (!((position >= 1) && (position <= this.size()))) {
            throw new IllegalArgumentException();
        }

        // Printing removed player's name
        System.out.println("Player removed in position " + position);
        System.out.println(this.players[position - 1].getName() + " has been removed from the team.\n");

        // Removing the player in the wanted position
        for (int i = position - 1; i < this.size(); i++) {
            this.players[i] = this.players[i + 1];
        }
        // Making sure that the player in the size() + 1 position is a default player
        Player defaultPlayer = new Player();
        this.players[this.size()] = defaultPlayer;
        size--;
    }

    /**
     * This method returns a player from a position indicated by the user.
     *
     * @param position
     * The integer representing the position where the player is in the team.
     *
     * @return
     * Returns the Player object in the position in the team. (Since indexes in
     * an array are zero-indexed, the player is returned from the index that is
     * "position - 1".)
     *
     * @throws IllegalArgumentException
     * Thrown when the position is not within the range of 1 ≤ position ≤ size.
     */
    public Player getPlayer(int position) throws IllegalArgumentException{
        if (!((position >= 1) && (position <= this.size()))) {
            throw new IllegalArgumentException();
        }
        return this.players[position - 1];
    }

    /**
     * This method returns the player in a team that has either the most hits
     * or the least errors.
     *
     * @param stat
     * The String representing which stat the user wants to find the leader in.
     * Either "hits" for the best hitter in the team, or "errors" for the least
     * erroneous in the team.
     *
     * @return
     * Returns the Player object that has either the most hits in the team, or
     * the least errors in the team.
     *
     * @throws IllegalArgumentException
     * Thrown when the stat referenced does not match either "hits" or "errors".
     */
    public Player getLeader(String stat) throws IllegalArgumentException {
        if (!stat.equalsIgnoreCase("hits") && !stat.equalsIgnoreCase("errors")) {
            throw new IllegalArgumentException();
        }

        // Best Hitter
        if (stat.equalsIgnoreCase("hits")) {
            Player bestHitter = new Player();
            for (int i = 0; i < this.size() + 1; i++) {
                if (this.players[i].getNumHits() > bestHitter.getNumHits()) {
                    bestHitter = this.players[i];
                }
            }
            return bestHitter;
        }

        // Least Erroneous
        Player leastErrors = this.players[0];
        for (int i = 1; i < this.size(); i++) {
            if (this.players[i].getNumErrors() < leastErrors.getNumErrors()) {
                leastErrors = this.players[i];
            }
        }
        return leastErrors;
    }

    /**
     * This method is responsible for printing all of the players in a team in
     * a neat, organized table. This method calls the .toString() function for a
     * Team object. This method prints the String result from the .toString()
     * function to the console.
     */
    public void printAllPlayers() {
        String table = this.toString();
        System.out.println(table);
        System.out.println();
    }

    /**
     * This method returns the entire table of players in a team, with
     * the entire table being condensed into one String.
     *
     * @return
     * Returns one String that has the table heading, the position of each
     * player, each player's name, number of hits, and number of errors all
     * represented as a neat table.
     */
    public String toString() {
        String titleToTable = "Player#\t\tName\t\t\t    Hits\t    Errors";
        String titleLines = "\n----------------------------------------------------";
        StringBuilder totalTable = new StringBuilder(titleToTable + titleLines);
        for (int i = 0; i < this.size(); i++) {
            String printEachLine = String.format("\n%-12d%-15s%10d%10d", i + 1, this.players[i].getName(), this.players[i].getNumHits(), this.players[i].getNumErrors());
            totalTable.append(printEachLine);
        }
        return totalTable.toString();
    }

    // Added Functions
    /**
     * This method updates a player's number of hits, or number of errors in a
     * team. This method the player in a team by brute-force searching, and
     * then updates their stat.
     *
     * @param playerName
     * The String representing the name of a Player in a team.
     * @param stat
     * The String representing which stat the user wants to find the leader in.
     * Either "hits" for the best hitter in the team, or "errors" for the least
     * erroneous in the team.
     * @param statNum
     * The integer representing the value of the new stat.
     *
     * @throws IllegalArgumentException
     * Thrown when the stat referenced does not match either "hits" or "errors".
     */
    public void updateStat(String playerName, String stat, int statNum) throws IllegalArgumentException, InvalidNameException {
        if (!stat.equalsIgnoreCase("hits") && !stat.equalsIgnoreCase("errors")) {
            throw new IllegalArgumentException("Invalid stat! It was neither 'hits' or 'errors'!\n");
        }
        if (statNum < 0){
            throw new IllegalArgumentException("Error! New statistic cannot be negative!\n");
        }

        boolean nameFlag = true;

        for (int i = 0; i < this.size(); i++) {
            if (this.players[i].getName().equals(playerName)) {
                nameFlag = false;
                if (stat.equalsIgnoreCase("hits")) {
                    this.players[i].setNumHits(statNum);
                } else if (stat.equalsIgnoreCase("errors")) {
                    this.players[i].setNumErrors(statNum);
                }
            }
        }

        if (nameFlag){
            throw new InvalidNameException("Error! Player does not exist!\n");
        }
        System.out.println("Updated " + playerName + " " + stat + "\n");
    }
}