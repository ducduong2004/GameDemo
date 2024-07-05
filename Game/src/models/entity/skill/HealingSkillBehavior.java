package models.entity.skill;

import models.entity.player.Player;

public class HealingSkillBehavior implements SkillBehavior {

    private Player player;
    private final int cooldownTime = 10000;
    private boolean onCooldown;

    public HealingSkillBehavior(Player player) {
        this.player = player;
    }

  public void useSkill() {
    if (onCooldown) {
      return;
    }

    if(player.health < player.maxHealth) {
        player.health += 1;
    }
    onCooldown = true;
    skillCoolDown();

  }

  @Override
  public void skillCoolDown() {
    onCooldown = true;
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
