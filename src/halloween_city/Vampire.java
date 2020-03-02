package halloween_city;

import util.Helper;
import java.awt.Color;
import java.util.ArrayList;
import util.DotPanel;

public class Vampire extends Creature {

    //Vmapire constructor, defalt x and y coordinate and color Red 
    public Vampire(int x, int y) 
    {
        super(x,y); 
        this.color = Color.RED;
    }   

     //changes the direction of the Vampire depending on the other creatures around it
     public void turn(City city)
     {
        //only change the direction if the creature needs to change based on the creatures around it 
        if(!isNear(city.listCreatures))
        {
            double newDirection = Helper.nextDouble();   
            int curDirection = this.direction;
    
            //if percent generated = 20%, generate a new direction
            if(newDirection <= .2) 
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
    //Converts Humans to Vampires
    public void convertToVampire(ArrayList<Creature> toBeConverted, ArrayList<Creature> listCreature)
    {
        for(int i = 0; i<toBeConverted.size(); i++)
        {
            int tempX = toBeConverted.get(i).x; //save current x position of human
            int tempY = toBeConverted.get(i).y; //save current y position of human
    
            Vampire newVamp = new Vampire(tempX, tempY); //create a new Vampire at the saved (x,y)
            listCreature.add(newVamp); //add new vampire to listCreatures
            listCreature.remove(toBeConverted.get(i)); //remove the human from the list and therefore the city
        }  
    }

    //Takes in 2 Creature arraylists, one the total listCreatures of all the creatures on the board,
    //and one of just the lsit of Humans that need to be converted
    //Converts Humans to Vampires
    public void convertToHuman(ArrayList<Creature> toBeConverted2, ArrayList<Creature> listCreature)
    {
        for(int i = 0; i<toBeConverted2.size(); i++)
        {
            int tempX = toBeConverted2.get(i).x; //save current x position of human
            int tempY = toBeConverted2.get(i).y; //save current y position of human
    
            Human newHuman = new Human(tempX, tempY); //create a new Vampire at the saved (x,y)
            listCreature.add(newHuman); //add new vampire to listCreatures
            listCreature.remove(toBeConverted2.get(i)); //remove the human from the list and therefore the city
        }  
    }

    //Checks if every Human on the board is within 10 squares of the Vampire, and if so, 
    //converts human to vampire
    //converts preists to humans
    public boolean isNear(ArrayList<Creature> listCreature)
    {
        boolean alreadyTurned = false;
        ArrayList<Creature> toBeConverted = new ArrayList<>();
        ArrayList<Creature> toBeConverted2 = new ArrayList<>();

        //for each creatures in listCreature arrayList
        for(int i = 0; i<listCreature.size(); i++)
        {
            //generate the x and y distance between the creature (Priest) at i and the Vampire
            int xDistance; 
            int yDistance;

            //only continue the method if the creature at i is a Preist
            if(listCreature.get(i) instanceof Preist)
            {
                xDistance = (listCreature.get(i).x - this.x);
                yDistance = (listCreature.get(i).y - this.y);
                if(Math.abs(xDistance) + Math.abs(yDistance) <= 1 )
                {
                    toBeConverted2.add(listCreature.get(i));
                }
            }

            //only continue the method if the creature at i is a Human, else go to next creature
            if(!(listCreature.get(i) instanceof Human))
            {
                continue;
            }
             
            //generate the x and y distance between the creature (Human) at i and the Vampire
            xDistance = (listCreature.get(i).x - this.x);
            yDistance = (listCreature.get(i).y - this.y);
    
            //Only continue if the distance between the human in x or y direction is not greater than 10
            if(Math.abs(xDistance) > 10 || Math.abs(yDistance) > 10) //if you're close
            {
                continue;
            }
        
            //if the Vampire and Preist are at the same locatin, the Vampire is added to an arrayList of
            //creatures to be converted later 
            if(Math.abs(xDistance) + Math.abs(yDistance) <= 1 )
            {
                toBeConverted.add(listCreature.get(i));
            }

            //Updates the direction of Vampire depedning on it's relation to the Human
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
        convertToVampire(toBeConverted, listCreature);
        //Converts Humans that need to be converted
        convertToHuman(toBeConverted2, listCreature);

        return alreadyTurned;
    }

}