
package tickets.client.gui.presenters;

import java.util.List;

import tickets.common.DestinationCard;
import tickets.common.HandTrainCard;
import tickets.common.IMessage;
import tickets.common.IObservable;
import tickets.common.IObserver;

public interface IGamePresenter extends IObserver {

    // public void takeTurn();

    //----------------------------------------
    // Game Setup
    public void chooseDestinationCards(DestinationCard toDiscard);
        // this shouldn't be useful

    //----------------------------------------
    // Turn actions
    public void drawTrainCard();
    public void drawFaceUpTrainCard(int position);
    public void drawDestinationCard();
    public void discardDestination(DestinationCard discard);
        // can be used for setup or during a turn

    public void claimPath();
        // params?

    //----------------------------------------
    // Player data getters
    public List<DestinationCard> getPlayerDestinations();
    public HandTrainCard getPlayerHand();


    // from IObserver
    void notify(IMessage state);
    void setObservable(IObservable setObservable);
}