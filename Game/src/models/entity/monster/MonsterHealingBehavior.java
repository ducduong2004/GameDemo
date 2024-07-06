package models.entity.monster;

import models.entity.skill.SkillBehavior;

public class MonsterHealingBehavior implements SkillBehavior {
    Monster monster; 
    boolean onCooldown = false;
    private int cooldownTime = 5000;
    public MonsterHealingBehavior(Monster monster){
        this.monster = monster;
    }

    @Override
    public void useSkill() {
        if (onCooldown) {
            return;
        }
      
        if(monster.health < monster.maxHealth) {
            monster.health = monster.maxHealth;
        }
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
