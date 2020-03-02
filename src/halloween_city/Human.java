package halloween_city;

import util.Helper;
import java.awt.Color;
import util.DotPanel;

public class Human extends Creature {

    //Human constructor, defalt x and y coordinate
    public Human(int x, int y) 
    {
        super(x,y); 
    }

    //changes the direction of the human 
    public void turn(City city)
    {
        double newDirection = Helper.nextDouble();   //random double generator
        int curDirection = this.direction;     //set the current direction into a variable

        //if percent generated = 10%, generate a new direction
        if(newDirection <= .1) 
        {
            //as long as the current direction is = to curdirection
            while(this.direction == curDirection)
            {
                this.newDirection();
            }
        }
        
    }

    //humans are set to color WHITE 
    public void draw()
    {
        Simulation.dp.setPenColor(Color.WHITE);
        Simulation.dp.drawDot(x,y);
    }

}