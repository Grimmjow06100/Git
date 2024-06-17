package model;

import javafx.animation.PauseTransition;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;

public class Item extends GameObject{

    boolean MustRemove;
    public enum ItemID{
        ManaPotion, HealthPotion, SpeedPotion
    }
    private ItemID itemId;

    public Item(double x, double y, ItemID itemId){
        super(x, y);
        this.id= ID.ITEM;
        this.itemId=itemId;
        GameObject.gameObjects.add(this);
    }

    public void  update() {
        MustRemove=false;
        if(getCollision()){
            GameObject.gameObjects.remove(this);
            MustRemove=true;
        }
    }

    public boolean getCollision(){

        for(GameObject go: GameObject.gameObjects){
            if(go.getId()== ID.PLAYER){
                if(this.getBounds().intersects(go.getBounds())){
                    if(this.itemId==ItemID.ManaPotion){
                        if (((Player)go).mana<200)
                            ((Player)go).incrementMana(50);
                    }
                    else if(this.itemId==ItemID.HealthPotion){
                        if(((Player)go).HP<200)
                            ((Player)go).incrementHP(50);
                    }
                    else if(this.itemId==ItemID.SpeedPotion){
                        applyTemporaryEffect((Player)go, "speed", 5, 5000);

                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getMustRemove(){
        return MustRemove;
    }

    public ItemID getItemId(){
        return itemId;
    }


    @Override
    public Bounds getBounds() {
        return new Rectangle(x,y,30,30).getBoundsInLocal();
    }

    private void applyTemporaryEffect(Player player, String effectType, int effectValue, int durationMillis) {
        switch (effectType) {
            case "speed":
                player.incrementSpeed(effectValue);
                break;
            // Add cases for other effects if needed
        }

        PauseTransition pause = new PauseTransition(Duration.millis(durationMillis));
        pause.setOnFinished(event -> {
            switch (effectType) {
                case "speed":
                    player.incrementSpeed(-effectValue); // Revert the speed increase
                    break;
                // Add cases to revert other effects if needed
            }
        });
        pause.play();
    }
}
