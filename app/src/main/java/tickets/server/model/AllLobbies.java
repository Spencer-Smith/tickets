package tickets.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tickets.common.Lobby;
import tickets.common.Player;

public class AllLobbies {

    private static AllLobbies INSTANCE = null;

    public static AllLobbies getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AllLobbies();
        }
        return INSTANCE;
    }

    private List<Lobby> lobbies;

    private AllLobbies(){
        lobbies = new ArrayList<>();
    }

    public void addLobby(Lobby lobby){
        String newId = UUID.randomUUID().toString();
        lobby.setId(newId);
        lobbies.add(lobby);
    }

    public void removeLobby(String lobbyId){
        for (Lobby lobby : lobbies){
            if (lobby.getId().equals(lobbyId)){
                lobbies.remove(lobby);
                return;
            }
        }
    }

    public Lobby getLobby(String lobbyId){
        for (Lobby lobby : lobbies){
            if (lobby.getId().equals(lobbyId)) return lobby;
        }
        return null;
    }

    public List<Lobby> getAllLobbies(){ return lobbies; }

    public List<Lobby> getLobbiesWithUser(String username) {
        List<Lobby> result = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            for (Player player : lobby.getPlayers()) {
                if (player.getName().equals(username)) {
                    result.add(lobby);
                    break;
                }
            }
        }
        return result;
    }
}
