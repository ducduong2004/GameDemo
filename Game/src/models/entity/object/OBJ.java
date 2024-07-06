package models.entity.object;

import models.Game;
import models.entity.Entity;
import models.entity.objbehavior.TrashBehaviorStrategy;

public class OBJ extends Entity{

    TrashBehaviorStrategy trashBehaviorStrategy;

    public OBJ(Game gp2) {
        super(gp2);
       
    }

    public TrashBehaviorStrategy getTrashBehavior() {
        return trashBehaviorStrategy;
    }


}
