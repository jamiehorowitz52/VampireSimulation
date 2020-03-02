package halloween_city;

import util.Helper;
import java.awt.Color;
import java.util.ArrayList;
import util.DotPanel;

public class Ogre extends Creature {

    //Ogre constructor, defalt x and y coordinate and color Green 
    public Ogre(int x, int y) 
    {
        super(x,y); 
        this.color = Color.GREEN;
    }   

     //changes the direction of the Ogre depending on the other creatures around it
     public void turn(City city)
     {
        //only change the direction if the creature needs to change based on the creatures around it 
        if(!isNear(city.listCreatures))
        {
            double newDirection = Helper.nextDouble();   
            int curDirection = this.direction;
    
            //if percent generated = .05%, generate a new direction
            if(newDirection <= .05) 
            {
                while(this.direction == curDirection)
                {
                    this.newDirection();
                }
            }
        }
     }

    //Takes in 2 Creature arraylists, one the total listCreatures of all the creatures on the board,
    //and one of just the lsit of Humans that need to be converted
    //Converts Humans to Preists
    public void convert(ArrayList<Creature> toBeConverted, ArrayList<Creature> listCreature)
    {
        for(int i = 0; i<toBeConverted.size(); i++)
        {
            int tempX = toBeConverted.get(i).x; //save current x position of humans
            int tempY = toBeConverted.get(i).y; //save current y position of human
    
            Preist newPreist = new Preist(tempX, tempY); //create a new Preist at the saved (x,y)
            listCreature.add(newPreist); //add new Preist to listCreatures
            listCreature.remove(toBeConverted.get(i)); //remove the human from the list and therefore the city
        }
       
    }

    //Checks if every Human on the board is within 2 squares of the Ogre, and if so, 
    //converts human to preist
    //converts humans to preists
    public boolean isNear(ArrayList<Creature> listCreature)
    {
        boolean alreadyTurned = false;
        ArrayList<Creature> toBeConverted = new ArrayList<>();
      
        for(int i = 0; i<listCreature.size(); i++)
        {
            //only continue the method if the creature at i is a Human, else go to next creature
            if(!(listCreature.get(i) instanceof Human))
            {
                continue;
            }
            
            //generate the x and y distance between the creature (Human) at i and the Ogre
            int xDistance = (listCreature.get(i).x - this.x);
            int yDistance = (listCreature.get(i).y - this.y);
    
            //Only continue if the distance between the human in x or y direction is not greater than 2
            if(Math.abs(xDistance) > 2 || Math.abs(yDistance) > 2) //if you're close
            {
               // System.out.println("Within 10");
                continue;
            }

            double rand = Helper.nextDouble(); 

            //if the Ogre and Human are at the same locatin, and there is a 5% chance
            //generated, the Human is added to an arrayList of creatures to be converted later 
            if(Math.abs(xDistance) + Math.abs(yDistance) <= 1 && (rand <= .05)) //if you are adjacent, convert
            {
                toBeConverted.add(listCreature.get(i));
                //convert(listCreature.get(i), listCreature);
            }

            //Updates the direction of Ogre depedning on it's relation to the Human
            //flag alreadyTurned is set = to true becuase turning occurred
            if(xDistance < 0)
            {
                //System.out.println("Go Left");
                direction = 3;
                alreadyTurned = true;
            }
            
            else if(xDistance > 0)
            {
              //  System.out.println("Go Right");
                direction = 2;
                alreadyTurned = true;
            }

            else if(yDistance < 0)
            {
                //System.out.println("Go Down");
                direction = 1;
                alreadyTurned = true;
            }

            else if(yDistance > 0)
            {
               // System.out.println("Go Up");
                direction = 0;
                alreadyTurned = true;
            }
        }

        //Converts Humans that need to be converted
        convert(toBeConverted, listCreature);

        return alreadyTurned;
    }

}