package tickets.client.commandline;

public class Main {

	public static void main(String[] args) {
		CommandlineView activeView = new LoginView();
		while (activeView != null){
			activeView.display();
		}
	}
	
}