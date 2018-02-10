package common;

public interface IClient {

    // FOR CLIENTS IN LOBBY LIST
    public void addLobbyToList(Lobby lobby);
    public void removeLobbyFromList(Lobby lobby);
    public void addPlayerToLobbyInList(Lobby lobby, Player playerToAdd);
    public void removePlayerFromLobbyInList(Lobby lobby, Player player);

    // FOR CLIENTS IN A LOBBY
    public void addPlayer(Player player);
    public void removePlayer(Player player);
    public void startGame();

    // FOR CLIENTS IN A GAME
    public void endCurrentTurn();
}
