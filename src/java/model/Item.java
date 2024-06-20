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

                        ((Player)go).incrementMana(50);
                    }
                    else if(this.itemId==ItemID.HealthPotion){

                        ((Player)go).incrementHP(50);
                    }
                    else if(this.itemId==ItemID.SpeedPotion){
                        ((Player)go).setFireSpeed(400);

                        PauseTransition pause = new PauseTransition(Duration.millis(10000));
                        pause.setOnFinished(event -> {
                            ((Player)go).setFireSpeed(800);

                        });
                        pause.play();

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

    private void applyTemporaryEffect(Player player, int effectValue, int durationMillis) {

        player.setFireSpeed(effectValue);

        PauseTransition pause = new PauseTransition(Duration.millis(durationMillis));
        pause.setOnFinished(event -> {
            player.setFireSpeed(-effectValue);

        });
        pause.play();
    }
}
