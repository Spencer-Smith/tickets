
package tickets.client.async;

import tickets.common.response.LeaveLobbyResponse;

import tickets.client.model.ClientModelRoot;
import tickets.client.ServerProxy;
import tickets.client.model.observable.*;


public class LeaveLobbyAsync /*extends AsyncTask<String, Void, LeaveLobbyResponse>*/ {
	ClientModelRoot modelRoot;

	public LeaveLobbyAsync(ClientModelRoot setRoot) {
		modelRoot = setRoot;
	}

	public void execute(Object... args) {}

	// @Override
	public LeaveLobbyResponse doInBackground(String... data) {
		if (data.length != 2) {
			AsyncException error = new AsyncException(this.getClass(), "invalid execute() parameters");
			return new LeaveLobbyResponse(error);
		}
		
		String lobbyID = data[0];
		String authToken = data[1];
		LeaveLobbyResponse response = ServerProxy.getInstance().leaveLobby(lobbyID, authToken);
		return response;
	}

	// @Override
	public void onPostExecute(LeaveLobbyResponse response) {
		if (response.getException() == null) {
			ClientStateChange.ClientState stateVal;
			stateVal = ClientStateChange.ClientState.lobbylist;
			ClientStateChange state = new ClientStateChange(stateVal);

			modelRoot.updateObservable(state);
		}
		else {
			Exception ex = response.getException();
			ExceptionMessage msg = new ExceptionMessage(ex);

			modelRoot.updateObservable(msg);
		}

		return;
	}
}