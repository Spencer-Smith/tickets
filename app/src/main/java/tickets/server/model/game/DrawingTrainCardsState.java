
package tickets.server.model.game;

import tickets.common.DestinationCard;

import tickets.server.model.game.ServerPlayer;
import tickets.server.model.game.ServerPlayer.PlayerTurnState;


//package private
class DrawingTrainCardsState extends PlayerTurnState {
	DrawingTrainCardsState(ServerPlayer player) {
		player.super();
	}

	void drawTrainCard() {
		//---

		//---
		return;
	}

	// The player has already drawn one train card,
	//   so they can't draw a face up card if it is a wild
	//
	void drawFaceUpCard(int position) {
		//---

		//---
		return;
	}

	// These functions do not apply to this state
	void claimRoute(Route route, int numWildCards) {
		//do nothing
		return;
	}
	void drawDestinationCard() {
		//do nothing
		return;
	}
	void discardDestinationCard(DestinationCard discard) {
		//do nothing
		return;
	}
}