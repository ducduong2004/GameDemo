package models.entity;

public class LightFootSkillBehavior implements SkillBehavior {

    private Player player;
    private final int cooldownTime = 4000;
    private boolean onCooldown;

    public LightFootSkillBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void useSkill() {

        if (onCooldown) {
            return;
        }
        
        
        player.setSpeed(player.speed + 3);
        
        System.out.println(player.speed);
        skillCoolDown();
        
    }

    @Override
    public void skillCoolDown() {
        onCooldown = true;
        new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                player.setSpeed(player.defaultSpeed);
                System.out.println(player.speed);
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
