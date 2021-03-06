
package tickets.common;

public class ClientStateChange implements IMessage {

    public enum ClientState {
        login,
        lobbylist,
        lobby,
        game,
        summary, update
    }

    private ClientState newState;

    public ClientStateChange(ClientState newState) {
        this.newState = newState;
    }

    @Override
    public Object getMessage() {
        return newState;
    }

}
