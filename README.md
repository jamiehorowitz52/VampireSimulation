# Bloooooood! 
-----
Add the UML diagram for your project and the expected behavior and description.

Approach to the Problem:
-----
I created an abstract class, called Creatures, which contains the basics of each specific reature that I create later on. This is because all creatures created, Human, Vampire, Preist, and Ogre, all have the same general characterists. These include a position, so an x and y, a direction, and a color. All creatures also can move the same way, where their x and y values are updated depending on what direction they are chosen to move in.

Simulation and City:
-----
These two classes generate a game board in which the simluation runs. Within simulation, actions that respond from the keyboard are excecuted. Within City, I create an ArrayList of Creatures called listCreatures, in which all creatures (Vampires, Humans, and Priests) are listed. Converting or removing a creature is manipulated via this list, and it is accessed wtihin each creature class. City populates, updates and draws, as well as has a boolean method which returns whether or not a current x,y position is a wall/border or not.

Creature:
-----
This is the abstract class for all creatures: Vampires, Humans, and Priests. All of these classes inheret from Creatures a frame structure of functions and attributes including x and y, direction, color, newDirection(), move(), turn(), and draw(). Each respective subclass overwrites the inherited objects with attributes unique to themselves, including color and when to turn.

Human:
-----
The Human class is a subclass of Creature. Humans can move randomly around the board and have a 10% chance of changing directions. Humans can be converted into Vampires by crossing paths with a Vampire. This was completed via a turn method whcih takes in the city and successfully selects and updates a new direction 10% of the time.

Vampire:
-----
The Vampire class is a subclass of Creature. Vampires can move randomly around the board and have a 20% chance of changing directions. Humans can be converted into Vampires by crossing paths with a Vampire. At the beginning of the simulation, 1 vampire is created in a random location, and if it sees a human within 10 squares in the direction its facing, it moves towards it. Vampires disapear from the city if they are splashed with holy water by a preist. The turn method of Vampire generates a new direction for the Vampire if isNear returns ture. The isNear() method converts humans to vampires and checks if a vampire is within 10 spaces of a human. Also, if a vampire is adjacent to a preist, it converts it back into a human, which is also implemented through the isNear method. 

Preist:
-----
The Vampire class is a subclass of Creature. Vampires can move randomly around the board and have a 15% chance of changing directions. Vampires can be converted into Humans by crossing paths with a Preist. If a preist sees a vampire within 5 squares of the direction its facing, it moves towards the vampire. The turn method of Preist generates a new direction for the Vampire if isNear returns ture. The isNear() method converts vampires to humans and checks if a vampire is within 5 spaces of a human. It adds all humans that need to be converted to an arraylist of type Creatures that is passed into a convert method. The same happens for vampires that need to be removed, but with a toBeRemoved method. Also, Priests have a 20% chance of splashing holy water onto Vampires within 2 squares of them and removing them from the baord. I implement this with a conditinoal in the isNear method. 
 
 Extra Feature - Ogre Class:
-----
The Ogre class is a subclass of Creature and their color is green. Ogres can move randomly around the board and have a .05% chance of changing directions. Ogres funciton similarly to other creatures except that they have the power to convert humans into preists, if there is 5% chance. 