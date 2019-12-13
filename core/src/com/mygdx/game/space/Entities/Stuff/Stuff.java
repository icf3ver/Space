package com.mygdx.game.space.Entities.Stuff;

import com.mygdx.game.space.Entities.Area.Station;
import com.mygdx.game.space.Entities.Entity;

public class Stuff{
    protected boolean canStack = false;
    protected int space = 0;
    protected Entity location;
    public void moveTo(Entity e){
        location = e;
    }
}

class CommonStone extends Stuff {
    public CommonStone (){
        super.canStack = true;
        super.space = 5;

    }
}

class DenseStone extends Stuff {
    public DenseStone (){
        super.canStack = true;
        super.space = 10;
    }
}
