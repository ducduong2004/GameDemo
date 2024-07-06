package models.entity.monster;

import models.Game;

public abstract class MonsterFactory {
    Monster monster;
    public Monster createMonster(int monster_type, Game gp){
        if(monster_type == 1){
            monster = new MON_Cockroach(gp);
        }
        else if(monster_type == 2){
            monster = new MON_Spider(gp);
        }

        return monster;
    }

}
