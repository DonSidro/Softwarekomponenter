package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

public interface IEntityProcessingService {

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     * controlles the movment and shape mm.
     * @param gameData
     * @param world
     * @param entity
     * what to do then buttons are pressed
     */
    void process(GameData gameData, Map<String, Entity> world, Entity entity);
}
