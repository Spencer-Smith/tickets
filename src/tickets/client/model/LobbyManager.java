package tickets.client.model;

import java.util.Map;

import tickets.common.Lobby;

public class LobbyManager {
	private Map<String, Lobby> lobbyList;

	public Map<String, Lobby> getLobbyList() {
		return lobbyList;
	}
	
	public void updateLobbyList(Map<String, Lobby> lobbyList) {
		this.lobbyList = lobbyList;
	}
	
	public Lobby getLobby(String id) {
		return lobbyList.get(id);
	}
}
