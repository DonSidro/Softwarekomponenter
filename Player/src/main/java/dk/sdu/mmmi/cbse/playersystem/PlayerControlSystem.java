package dk.sdu.mmmi.cbse.playersystem;


import dk.sdu.mmmi.cbse.common.data.Entity;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameData;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

    
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
            
        if(entity.getType() == PLAYER){
 
            wrap(gameData,entity);
             setShape(entity);
             controls(gameData,entity);
    }
    }
    
    private void setShape(Entity e) {
        
        float[] sX = new float[4];
        float[] sY = new float[4];
        
        float randians = e.getRadians();
        
        sX[0] = e.getX() + (float) Math.cos(randians) * 8;
        sY[0] = e.getY() + (float) Math.sin(randians) * 8;

        sX[1] = e.getX() + (float) Math.cos(randians - 4 * 3.1415f / 5) * 8;
        sY[1] = e.getY() + (float) Math.sin(randians - 4 * 3.1415f / 5) * 8;

        sX[2] = e.getX() + (float) Math.cos(randians + 3.1415f) * 5;
        sY[2] = e.getY() + (float) Math.sin(randians + 3.1415f) * 5;
        
        sX[3] = e.getX() + (float) Math.cos(randians + 4 * 3.1415f / 5) * 8;
        sY[3] = e.getY() + (float) Math.sin(randians + 4 * 3.1415f / 5) * 8;
        
        e.setShapeX(sX);
        e.setShapeY(sY);
    }
    
    private void wrap(GameData gd, Entity e) {
		if(e.getX() < 0) e.setPosition(gd.getDisplayWidth(), e.getY());
		if(e.getX() > gd.getDisplayWidth()) e.setPosition(0, e.getY());
		if(e.getY() < 0) e.setPosition(e.getX(), gd.getDisplayHeight());
		if(e.getY() > gd.getDisplayHeight()) e.setPosition(e.getX(), 0); 
	}
    
    private void controls(GameData gd, Entity e){
                float radians;
                float radianSpeed;
                float dx;
                float dy;
                float acceleration;
                float acceleratingTimer = 0;
                float deceleration;
                float x;
                float y;
                float  maxSpeed;
                maxSpeed = e.getMaxSpeed();
                x = e.getX();
                y = e.getY();
                deceleration = e.getDeacceleration();
                acceleration = e.getAcceleration();
                dx = e.getDx();
                dy = e.getDy();
                radians = e.getRadians();
                radianSpeed = e.getRotationSpeed();
        
        if(gd.getKeys().isDown(gd.getKeys().LEFT)){
                radians += radianSpeed * gd.getDelta();
                e.setRadians(radians);
        }
        else if(gd.getKeys().isDown(gd.getKeys().RIGHT)){
                radians -= radianSpeed * gd.getDelta();
                e.setRadians(radians);
        
        }
        
        if(gd.getKeys().isDown(gd.getKeys().UP)){
            
                    dx += Math.cos(radians) * acceleration * gd.getDelta();
                    dy += Math.sin(radians) * acceleration * gd.getDelta();
			acceleratingTimer += gd.getDelta();
			if(acceleratingTimer > 0.1f) {
				acceleratingTimer = 0;
			}
        
                }else{
                        acceleratingTimer = 0;
        }
    
        
        float vec = (float) Math.sqrt(e.getDx() * e.getDx() + e.getDy() * e.getDy());
		if(vec > 0) {
			dx -= (dx / vec) * deceleration * gd.getDelta();
			dy -= (dy / vec) * deceleration * gd.getDelta();
		}
		if(vec > maxSpeed) {
			dx = (dx / vec) * maxSpeed;
			dy = (dy / vec) * maxSpeed;
		}
		
		// set position
		x += dx * gd.getDelta();
		y += dy * gd.getDelta();
                
                e.setDx(dx);
                e.setDy(dy);
                e.setPosition(x, y);
                
    
    }


}
