package tickets.file_dao;

import java.util.List;

import tickets.common.database.FileAccess;
import tickets.server.dataaccess.DataAccess;
import tickets.server.dataaccess.interfaces.PlayerDataAccess;

/**
 * Created by Pultilian on 4/12/2018.
 */

public class PlayerDA_File extends DataAccess implements PlayerDataAccess {
    private FileAccess fileAccess;

    public PlayerDA_File() throws Exception {
        fileAccess = new FileAccess();
    }

    @Override
    public void addPlayer(String player, String gameID, String username) throws Exception {
        String newID = gameID + username;
        fileAccess.checkpointUpdate(player, "player", newID);
    }

    @Override
    public List<String> getPlayers() throws Exception {
        return fileAccess.getAllObjects("player");
    }

    @Override
    public void removePlayers(String username, String gameID) throws Exception {
        String newID = gameID + username;
        fileAccess.removeFile("player", newID);
    }

    @Override
    public void clear() throws Exception {
        fileAccess.removeAllFiles();
    }

    @Override
    public void addDeltas(String command, String gameID, String username) throws Exception {
        String newID = gameID + username;
        fileAccess.addUpdate(command, "player", newID);
    }

    @Override
    public List<String> getDeltas(String gameID, String username) throws Exception {
        String newID = gameID + username;
        return fileAccess.getDeltasForObject("player", newID);
    }

    public void clearDeltas() throws Exception{
        return;
    }
}
