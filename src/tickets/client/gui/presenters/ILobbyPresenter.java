
package tickets.client.gui.presenters;

import tickets.common.Lobby;

import tickets.client.model.observable.*;


public interface ILobbyPresenter extends IObserver {
  
  public void startGame(String lobbyID);
  public void leaveLobby(String lobbyID);
  public void addGuest(String lobbyID);

  // from IObserver
  public void notify(IMessage state);
  public void setObservable(ClientObservable setObservable);
  
}
