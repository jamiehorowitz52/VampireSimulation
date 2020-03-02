package halloween_city;

import util.Helper;
import util.DotPanel;
import java.awt.Color;

// Global variables that are accesible to the creature
public abstract class Creature {
     int x; 
     int y;
     int direction;
     Color color;

//Constructor for the creature class.
//All creatures have an x and y position, as well as a direction (0, 1, 2, 3) 
public Creature(int x, int y)
{
    this.x = x;
    this.y = y;
    this.direction = Helper.nextInt(4); // 4 different directions
}

// 0 = Noth, 1 = South, 2 = East, 3 = West
//This method updates the direction of the current creature with
// a random direction, represented through 0, 1, 2, 3
public void newDirection()
{
    direction = Helper.nextInt(4); 
}

//@param city is the current city in the current world 
//@param numSquares is the number of spaces the creature should move
//This method calls turn, which sets a valid direction for the creature to 
//move. The x or y values of the creature will be updated depending on the
//direction generated and if the new direction is not a wall.
public void move(City city, int numSquares)
{
    turn(city);
    //if i did not move or you are in a wall
    while(true)
    {
        // SOUTH
        if(direction == 0 && !city.isWall(x, y+1))
        {
            y = y + numSquares;
            break;
        }
        // NORTH
       if(direction == 1 && !city.isWall(x, y-1))
        {
            y = y - numSquares;
            break;
        }
        //EAST
        if(direction == 2 && !city.isWall(x+1, y))
        {
            x = x + numSquares;
            break;
        }
        //WEST
        if(direction == 3 && !city.isWall(x-1, y))
        {
            x = x - numSquares;
            break;
        }
        
        else{
            this.newDirection();
        }
    }
}

//default method that each specific creature will overwrite in their own classes
    public void turn(City city)
    {

    }
     
//default method that each specific creature will inheret in their own classes
//sets the pen color to the color assigned to that class, and draws a dot at the 
//current x and y value of the creature
    public void draw()
    {
        Simulation.dp.setPenColor(this.color);
        Simulation.dp.drawDot(x,y);
    }
}