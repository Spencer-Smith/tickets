package server;

import common.Command;
import common.IClient;
import common.Lobby;
import common.Player;

import java.util.ArrayDeque;
import java.util.Queue;

public class ClientProxy implements IClient {

    private String authToken;
    private Queue<Command> unprocessedCommands;

    public ClientProxy(String authToken){
        this.authToken = authToken;
        unprocessedCommands = new ArrayDeque<>();
    }

    public String getAuthToken(){ return authToken; }

    @Override
    public void addLobbyToList(Lobby lobby) {
        unprocessedCommands.add(new Command(new Object[]{lobby}, new String[]{Lobby.class.getName()}, "addLobbyToList"));
    }

    @Override
    public void removeLobbyFromList(Lobby lobby) {
        unprocessedCommands.add(new Command(new Object[]{lobby}, new String[]{Lobby.class.getName()}, "removeLobbyFromList"));
    }

    @Override
    public void addPlayersToLobbyInList(Lobby lobby, int numToAdd) {
        unprocessedCommands.add(new Command(
                new Object[]{lobby, numToAdd},
                new String[]{Lobby.class.getName(), int.class.getName()},
                "addPlayersToLobbyInList"));
    }

    @Override
    public void removePlayersFromLobbyInList(Lobby lobby, int numToRemove) {
        unprocessedCommands.add(new Command(
                new Object[]{lobby, numToRemove},
                new String[]{Lobby.class.getName(), int.class.getName()},
                "removePlayersFromLobbyInList"));
    }

    @Override
    public void addPlayer(Player player) {
        unprocessedCommands.add(new Command(new Object[]{player}, new String[]{Player.class.getName()}, "addPlayer"));
    }

    @Override
    public void removePlayer(Player player) {
        unprocessedCommands.add(new Command(new Object[]{player}, new String[]{Player.class.getName()}, "removePlayer"));
    }

    @Override
    public void startGame() {
        unprocessedCommands.add(new Command(new Object[0], new String[0], "startGame"));
    }

    @Override
    public void endCurrentTurn() {
        unprocessedCommands.add(new Command(new Object[0], new String[0], "endCurrentTurn"));
    }
}
