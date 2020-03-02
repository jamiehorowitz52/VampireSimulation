package halloween_city;

import util.Helper;
import java.awt.Color;
import java.util.ArrayList;
import util.DotPanel;

public class Preist extends Creature {

    //Priest constructor, defalt x and y coordinate and color whtie 
    public Preist(int x, int y) 
    {
        super(x,y); 
        this.color = Color.WHITE;
    }   

     //changes the direction of the preist depending on the other creatures around it
     public void turn(City city)
     {
        //only change the direction if the creature needs to change based on the creatures around it 
        if(!isNear(city.listCreatures))
        {
            double newDirection = Helper.nextDouble();   
            int curDirection = this.direction;
    
            //if percent generated = 15%, generate a new direction
            if(newDirection <= .15) 
            {
                while(this.direction == curDirection)
                {
                    this.newDirection();
                }
            }
        }
     }

    //Takes in 2 Creature arraylists, one the total listCreatures of all the creatures on the board,
    //and one of just the lsit of Vampires that need to be converted
    //Converts Vampires to Humans
    public void convert(ArrayList<Creature> toBeConverted, ArrayList<Creature> listCreature)
    {
        //convert all vampires from Vampire to Human
        for(int i = 0; i<toBeConverted.size(); i++)
        {
            int tempX = toBeConverted.get(i).x; //save current x position of vampire
            int tempY = toBeConverted.get(i).y; //save current y position of vampire
    
            Human newHuman = new Human(tempX, tempY); //create a new human at the saved (x,y)
            listCreature.add(newHuman); //add new human to listCreatures
            listCreature.remove(toBeConverted.get(i)); //remove the vampire from the list and therefore the city
        }      
    }

    //Takes in 2 Creature arraylists, one the total listCreatures of all the creatures on the board,
    //and one of just the lsit of Vampires that need to be removed
    //Removes each vampire from toBeRemoved list
    public void removeVampire(ArrayList<Creature> toBeRemoved, ArrayList<Creature> listCreature)
    {
        for(int i = 0; i<toBeRemoved.size(); i++)
        {
            listCreature.remove(toBeRemoved.get(i));
        }      
    }

    //Checks if every Vampire on the board is within 5 squares of the Preist, and if so, 
    //converts vampires to humans
    //Removes Vampires if holy water is sprayed
    public boolean isNear(ArrayList<Creature> listCreature)
    {
        boolean alreadyTurned = false;
        ArrayList<Creature> toBeConverted = new ArrayList<>();
        ArrayList<Creature> toBeRemoved = new ArrayList<>();

        for(int i = 0; i<listCreature.size(); i++)
        {
            //only continue the method if the creature at i is a Vampire, else go to next creature
            if(!(listCreature.get(i) instanceof Vampire))
            {
                continue;
            }
            
            //generate the x and y distance between the creature (Vampire) at i and the Preist
            int xDistance = (listCreature.get(i).x - this.x);
            int yDistance = (listCreature.get(i).y - this.y);
    
            //if((Math.abs(xDistance) <= 2 || (Math.abs(yDistance) <= 2) && (Helper.nextDouble() <= .2))

            //Only continue if the distance between the vampire in x or y direction is not greater than 5
            if((Math.abs(xDistance) > 5 || Math.abs(yDistance) > 5)) 
            {
                continue;
            }

            //"Holy Water" is thrown 20% of the time
            //if the Vmapire is within 2 squares of the Preist, the Vampire is added to an arrauyList of
            //creatures to be removed later
            if((Math.abs(xDistance) <= 2 || Math.abs(yDistance) <= 2) && Helper.nextDouble() <= .2) 
            {
                //System.out.println("Holy Water");
                toBeRemoved.add(listCreature.get(i));
            }

            //if the Vampire and Preist are at the same locatin, the Vampire is added to an arrauyList of
            //creatures to be converted later 
            if(Math.abs(xDistance) + Math.abs(yDistance) <= 1 ) 
            {
                toBeConverted.add(listCreature.get(i));
            }

            //Updates the direction of Preist depedning on it's relation to the Vampire
            //flag alreadyTurned is set = to true becuase turning occurred
            if(xDistance < 0) 
            {
                direction = 3; //Go Left
                alreadyTurned = true;
            }
            
            else if(xDistance > 0)
            {
                direction = 2; //Go Riight
                alreadyTurned = true;
            }

            else if(yDistance < 0)
            {
                direction = 1; //Go Down
                alreadyTurned = true;
            }

            else if(yDistance > 0)
            {
                direction = 0; //Go Up
                alreadyTurned = true;
            }
        }

        //Converts Vampires that need to be converted
        //Removes Vampires that need to be removed
        removeVampire(toBeRemoved, listCreature);
        convert(toBeConverted, listCreature);

        return alreadyTurned;
    }

}