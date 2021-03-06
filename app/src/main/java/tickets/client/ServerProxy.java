package tickets.client;

import java.util.List;

import tickets.common.ChoiceDestinationCards;
import tickets.common.Command;
import tickets.common.DestinationCard;
import tickets.common.IServer;
import tickets.common.Lobby;
import tickets.common.Route;
import tickets.common.TrainCard;
import tickets.common.TrainCardWrapper;
import tickets.common.UserData;
import tickets.common.response.AddToChatResponse;
import tickets.common.response.ClaimRouteResponse;
import tickets.common.response.ClientUpdate;
import tickets.common.response.DestinationCardResponse;
import tickets.common.response.JoinLobbyResponse;
import tickets.common.response.LeaveLobbyResponse;
import tickets.common.response.LoginResponse;
import tickets.common.response.LogoutResponse;
import tickets.common.response.Response;
import tickets.common.response.ResumeGameResponse;
import tickets.common.response.ResumeLobbyResponse;
import tickets.common.response.StartGameResponse;
import tickets.common.response.TrainCardResponse;

public class ServerProxy implements IServer {
    //Singleton structure
    private static ServerProxy INSTANCE = null;

    private ServerProxy() {
        clientCommunicator = ClientCommunicator.getInstance();
    }

    public static ServerProxy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerProxy();
        }
        return (INSTANCE);
    }

    //member variables
    private ClientCommunicator clientCommunicator;

    public LoginResponse login(UserData userData) {
        Object[] parameters = {userData};
        String[] parameterTypes = {UserData.class.getName()};
        Command command = new Command("login", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (LoginResponse) result;
    }

    public LoginResponse register(UserData userData) {
        Object[] parameters = {userData};
        String[] parameterTypes = {UserData.class.getName()};
        Command command = new Command("register", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (LoginResponse) result;
    }

    public JoinLobbyResponse joinLobby(String lobbyID, String authToken) {
        Object[] parameters = {lobbyID, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("joinLobby", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (JoinLobbyResponse) result;
    }

    public JoinLobbyResponse createLobby(Lobby lobby, String authToken) {
        Object[] parameters = {lobby, authToken};
        String[] parameterTypes = {Lobby.class.getName(), String.class.getName()};
        Command command = new Command("createLobby", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (JoinLobbyResponse) result;
    }

    public ResumeLobbyResponse resumeLobby(String lobbyID, String authToken) {
        Object[] parameters = {lobbyID, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("resumeLobby", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (ResumeLobbyResponse) result;
    }

    public ResumeGameResponse resumeGame(String gameID, String authToken) {
        Object[] parameters = {gameID, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("resumeGame", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (ResumeGameResponse) result;
    }

    public LogoutResponse logout(String authToken) {
        Object[] parameters = {authToken};
        String[] parameterTypes = {String.class.getName()};
        Command command = new Command("logout", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (LogoutResponse) result;
    }

    public StartGameResponse startGame(String lobbyID, String authToken) {
        Object[] parameters = {lobbyID, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("startGame", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (StartGameResponse) result;
    }

    public LeaveLobbyResponse leaveLobby(String lobbyID, String authToken) {
        Object[] parameters = {lobbyID, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("leaveLobby", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (LeaveLobbyResponse) result;
    }

    public AddToChatResponse addToChat(String message, String authToken) {
        Object[] parameters = {message, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("addToChat", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (AddToChatResponse) result;
    }

    public TrainCardResponse drawTrainCard(String authToken) {
        Object[] parameters = {authToken};
        String[] parameterTypes = {String.class.getName()};
        Command command = new Command("drawTrainCard", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (TrainCardResponse) result;
    }

    public TrainCardResponse drawFaceUpCard(int position, String authToken) {
        Object[] parameters = {position, authToken};
        String[] parameterTypes = {int.class.getName(), String.class.getName()};
        Command command = new Command("drawFaceUpCard", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (TrainCardResponse) result;
    }

    public DestinationCardResponse drawDestinationCards(String authToken) {
        Object[] parameters = {authToken};
        String[] parameterTypes = {String.class.getName()};
        Command command = new Command("drawDestinationCards", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (DestinationCardResponse) result;
    }

    public ClientUpdate updateClient(String lastReceivedCommandID, String authToken) {
        Object[] parameters = {lastReceivedCommandID, authToken};
        String[] parameterTypes = {String.class.getName(), String.class.getName()};
        Command command = new Command("updateClient", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (ClientUpdate) result;
    }

    public ClaimRouteResponse claimRoute(Route route, TrainCardWrapper cards, String authToken) {
        Object[] parameters = {route, cards, authToken};
        String[] parameterTypes = {Route.class.getName(), TrainCardWrapper.class.getName(), String.class.getName()};
        Command command = new Command("claimRoute", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (ClaimRouteResponse) result;
    }

    public DestinationCardResponse discardDestinationCard(ChoiceDestinationCards discard, String authToken) {
        Object[] parameters = {discard, authToken};
        String[] parameterTypes = {ChoiceDestinationCards.class.getName(), String.class.getName()};
        Command command = new Command("discardDestinationCard", parameterTypes, parameters);
        Object result = clientCommunicator.send(command);
        return (DestinationCardResponse) result;
    }
}
