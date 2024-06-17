package controller;

import model.Ultime;

public class UltimeController {
    Ultime ultime;

    public UltimeController(Ultime ultime ){
        this.ultime = ultime;
    }

    public double getUltimeX(){
        return ultime.getX();
    }

    public double getUltimeY(){
        return ultime.getY();
    }

    public void update(){
        ultime.update();
    }

    public boolean getMustRemove(){
        return ultime.getMustRemove();
    }
    
}
