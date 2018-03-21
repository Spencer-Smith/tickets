
package tickets.server.model.game;

import java.util.List;

import tickets.common.TrainCard;
import tickets.common.DestinationCard;
import tickets.common.Route;

// Part of the state pattern describing a player's overall turn
//
// At this state, the player has drawn three destination cards
//	 and must now choose whether to discard two, one or zero of them.
//
class DrewDestCardsState extends PlayerTurnState {

	// Singleton pattern
	private static DrewDestCardsState INSTANCE;

	static DrewDestCardsState getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DrewDestCardsState();
		}
		return INSTANCE;
	}

	private DrewDestCardsState(){}

	@Override
	String drawTrainCard(TrainCard card, ServerPlayer player) {
		return "You must choose which destination cards (if any) to discard.";
	}

	@Override
	String drawFaceUpCard(TrainCard card, ServerPlayer player) {
		return "You must choose which destination cards (if any) to discard.";
	}

	@Override
	String claimRoute(Route route, List<TrainCard> cards, ServerPlayer player) {
		return "You must choose which destination cards (if any) to discard.";
	}

	@Override
	String drawDestinationCards(List<DestinationCard> cards, ServerPlayer player) {
		return "You must choose which destination cards (if any) to discard.";
	}

	@Override
	String discardDestinationCard(DestinationCard card, ServerPlayer player) {
		List<DestinationCard> options = player.getDestinationCardOptions();
		for (DestinationCard playerCard : options) {
		    if (!playerCard.equals(card)) player.addDestinationCardToHand(playerCard);
        }
        player.changeState(States.NOT_MY_TURN);
		return null;
	}

	@Override
	String endTurn(ServerPlayer player) {
		return "You must choose which destination cards (if any) to discard.";
	}
}