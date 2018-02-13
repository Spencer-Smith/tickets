package tickets.client.gui.activities;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tickets.client.gui.presenters.IHolderActivity;
import tickets.client.gui.presenters.LobbyListPresenter;
import tickets.common.Lobby;

/**
 * Created by Pultilian on 2/10/2018.
 */

public class LobbyListActivity extends AppCompatActivity implements IHolderActivity {

    //Views
    private Button joinButton;
    private Button logoutButton;
    private Button createGameButton;
    private RecyclerView lobbyList;
    private RecyclerView.LayoutManager lobbyListManager;
    private LobbyListAdapter lobbyListAdapter;
    private LobbyListPresenter presenter;
    private EditText gameName;
    private EditText numPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LobbyListPresenter(this);
        setContentView(R.layout.activity_lobby_list);

        joinButton = (Button) this.findViewById(R.id.join);
        logoutButton = (Button) this.findViewById(R.id.log_out);
        createGameButton = (Button) this.findViewById(R.id.create_game);
        lobbyList = (RecyclerView) findViewById(R.id.lobby_list);

        lobbyList.setLayoutManager(new LinearLayoutManager(this));
        lobbyListManager = new LinearLayoutManager(this);
        lobbyList.setLayoutManager(lobbyListManager);

        joinButton.setEnabled(false);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.joinLobby(UUID.randomUUID().toString());
                return;
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.logout();
                return;
            }
        });

        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lobbyName = gameName.getText().toString();
                String players = numPlayers.getText().toString();
                try {
                    int p = Integer.parseInt(players);
                    presenter.createLobby(new Lobby(lobbyName, p));
                    Intent intent = new Intent(LobbyListActivity.this, LobbyActivity.class);
                    startActivity(intent);
                }
                catch(NumberFormatException e) {
                    Toast.makeText(LobbyListActivity.this, "Invalid number of players", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        });

        gameName = findViewById(R.id.num_players);
        gameName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        numPlayers = findViewById(R.id.name_game);
        numPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        updateUI();
        return;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void makeTransition(Transition toActivity){
        if(toActivity == Transition.toLobby) {
            System.out.println("Calling transition");
            Intent intent = new Intent(this, LobbyActivity.class);
            startActivity(intent);
        }
    }

    @Override
    //from IHolderActivity
    public void toastMessage(String var1){
        Context context = getApplicationContext();
        CharSequence text = var1;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    //from IHolderActivity
    public void toastException(Exception var1){
        Context context = getApplicationContext();
        CharSequence text = var1.getMessage();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    void updateUI() {
        lobbyListAdapter = new LobbyListAdapter(this);
        checkButton();
        lobbyList.setAdapter(lobbyListAdapter);
    }


    class LobbyListAdapter extends RecyclerView.Adapter<LobbyListHolder> {
        private ArrayList<Lobby> curLobbyList;
        private LayoutInflater inflater;

        public LobbyListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            curLobbyList = new ArrayList<>();
            for (Lobby l : presenter.getLobbyList()) {
                curLobbyList.add(l);
            }

        }

        @Override
        public LobbyListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.lobby_list_item, parent, false);
            return new LobbyListHolder(view);
        }

        // Grabs an individual row and assigns its values through the holder class.
        @Override
        public void onBindViewHolder(LobbyListHolder holder, int position) {
            Lobby item = curLobbyList.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return curLobbyList.size();
        }

    }

    class LobbyListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gameTitle;
        TextView numPlayers;
        int maxPlayers;
        int curPlayers;

        public LobbyListHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            gameTitle = (TextView) view.findViewById(R.id.title);
            numPlayers = (TextView) view.findViewById(R.id.description);
            maxPlayers = 0;
        }

        // Assigns values in the layout.
        void bind(Lobby item) {
            gameTitle.setText(item.getName());
            numPlayers.setText(item.getCurrentMembers() + "/" + item.getMaxMembers());
            maxPlayers = item.getMaxMembers();
            curPlayers = item.getCurrentMembers();
        }

        @Override
        public void onClick(View view) {
            if(curPlayers < maxPlayers) {
                joinButton.setEnabled(true);
            }

        }
    }

    void checkButton() {
        // Register Button
        if (gameName.getText().toString().length() != 0 &&
                numPlayers.getText().toString().length() != 0){
            createGameButton.setEnabled(true);
        } else {
            createGameButton.setEnabled(false);
        }
    }
}
