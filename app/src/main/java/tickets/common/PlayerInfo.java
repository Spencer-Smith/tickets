
package tickets.common;


public class PlayerInfo {
	private Faction playerFaction;
	private String name;
	private int points;
	private int shipsLeft;
	private int trainCardCount;
	private int destinationCardCount;

	public PlayerInfo(){
	    playerFaction = null;
	    name = null;
	    points = 0;
	    shipsLeft = 45;
	    trainCardCount = 0;
	    destinationCardCount= 0;
    }

	public void setFaction(Faction set) {
		playerFaction = set;
		return;
	}

	public Faction getFaction() {
		return playerFaction;
	}

	public void setName(String set) {
		name = set;
		return;
	}

	public String getName() {
		return name;
	}

	public void setScore(int set) {
		points = set;
	}

	public void addToScore(int points){
	    this.points += points;
    }

	public int getScore() {
		return points;
	}

	public void setShipsLeft(int set) {
		shipsLeft = set;
		return;
	}

	public void useShips(int ships) {
	    shipsLeft -= ships;
    }

	public int getShipsLeft() {
		return shipsLeft;
	}

    public void addTrainCard() {
        trainCardCount++;
    }

    public void useTrainCards(int cards) {
        trainCardCount -= cards;
    }

    public int getTrainCardCount() {
	    return trainCardCount;
    }

    public void addDestinationCards(int numCards) {
        destinationCardCount += numCards;
    }

    public int getDestinationCardCount() {
        return destinationCardCount;
    }
}
