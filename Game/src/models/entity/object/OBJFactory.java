package models.entity.object;

import models.Game;

public class OBJFactory {
    OBJ obj;
    public OBJ createObj(int obj_type,Game gp){
        if(obj_type == 1){
            obj = new OBJ_Bottle(gp);
        }
        else if(obj_type == 2){
            obj = new OBJ_Shovel(gp);
        }

        return obj;
    }
}
