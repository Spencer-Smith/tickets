
package tickets.client.gui.presenters;

import tickets.client.ClientFacade;
import tickets.client.ITaskManager;
import tickets.client.TaskManager;
import tickets.client.async.AsyncManager;
import tickets.common.ClientModelUpdate;
import tickets.common.ClientStateChange;
import tickets.common.ExceptionMessage;
import tickets.common.IMessage;
import tickets.common.IObservable;
import tickets.common.Lobby;


public class LobbyPresenter implements ILobbyPresenter {
    private IObservable observable;
    private IHolderActivity holder;
    private ITaskManager manager;

    public LobbyPresenter(IHolderActivity setHolder) {
        holder = setHolder;
        manager = AsyncManager.getInstance();
        ClientFacade.getInstance().linkObserver(this);
    }
    
    public LobbyPresenter() {
    	holder = null;
        manager = TaskManager.getInstance();
        ClientFacade.getInstance().linkObserver(this);
    }

//----------------------------------------------------------------------------
//  interface methods

    @Override
    public Lobby getLobby() {
        return ClientFacade.getInstance().getLobby();
    }

    @Override
    public void startGame(String lobbyID) {
        manager.startGame(lobbyID);
        return;
    }

    @Override
    public void leaveLobby(String lobbyID) {
        manager.leaveLobby(lobbyID);
        return;
    }

    @Override
    public void notify(IMessage state) {
        if (state.getClass() == ClientStateChange.class) {
            ClientStateChange.ClientState flag = (ClientStateChange.ClientState) state.getMessage();
            checkClientStateFlag(flag);
        } else if (state.getClass() == ClientModelUpdate.class) {
            checkClientModelUpdateFlag((ClientModelUpdate.ModelUpdate) state.getMessage());
        } else if (state.getClass() == ExceptionMessage.class) {
            Exception e = (Exception) state.getMessage();
            if (holder != null)
                holder.toastException(e);
            else
                System.out.println(e.getMessage());
        } else {
            Exception err = new Exception("Observer err: invalid IMessage of type " + state.getClass());
            holder.toastException(err);
        }
        return;
    }

    @Override
    public void setObservable(IObservable setObservable) {
        observable = setObservable;
        return;
    }

//----------------------------------------------------------------------------
//  private methods

    private void checkClientStateFlag(ClientStateChange.ClientState flag) {
        switch (flag) {
            case login:
                //do nothing
                break;
            case lobbylist:
                if (holder != null)
                    holder.makeTransition(IHolderActivity.Transition.toLobbyList);
                break;
            case lobby:
                //do nothing
                break;
            case game:
                if (holder != null)
                    holder.makeTransition(IHolderActivity.Transition.toGame);
                break;
            case summary:
                //do nothing
                break;
            case update:
                holder.checkUpdate();
                break;
            default:
                Exception err = new Exception("Observer err: invalid Transition " + flag.name());
                holder.toastException(err);
                break;
        }
    }

    private void checkClientModelUpdateFlag(ClientModelUpdate.ModelUpdate flag) {
        switch (flag) {
            case lobbyListUpdated:
                if (holder != null) {
                    holder.checkUpdate();
                }
            default:
                Exception err = new Exception("Observer err: invalid Transition " + flag.name());
//                holder.toastException(err);
                break;
        }
    }
}
