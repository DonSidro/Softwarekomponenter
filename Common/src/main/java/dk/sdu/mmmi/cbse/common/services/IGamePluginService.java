package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

public interface IGamePluginService {

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     * Start is called when en Entity is getting loaded in 
     * @param gameData
     * is the data that are getting send for the entity
     * @param world
     * the current world with the enity's 
     * pre- Entity is getting called
     * post- Entity is loaded in to the world
     * 
     * 
     */
    void start(GameData gameData, Map<String, Entity> world);

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     *what do do when stop
     * @param gameData
     * remove entity from the game 
     */
    void stop(GameData gameData);
}
