package models.entity.monster;

import models.entity.skill.SkillBehavior;

public class MonsterSpeedUp implements SkillBehavior{
    private Monster monster;
    private int currentSpeed;
    private int cooldownTime = 10000;
    private boolean onCooldown = false;
    public MonsterSpeedUp(Monster monster){
        this.monster = monster;
        currentSpeed = 1;
    }


    @Override
    public void useSkill() {
        if (onCooldown) {  
            monster.defaultSpeed = currentSpeed;         
            return;

        }else 
        monster.defaultSpeed +=2;
       
        onCooldown = true;
        skillCoolDown();
       
    }

    @Override
    public void skillCoolDown() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {                                    
                    Thread.sleep(cooldownTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    onCooldown = false;
                }
            }
            }).start();
    }

}
