package halloween_city;

import util.Helper;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Color;

public class City {

	/** Extra FUN
	 * 
	 * This is a option for students who want an extra challenge. 
	 * If you do use the walls you will get extra credit (only if your project works).
	 * 
	 *  walls is a 2D array with an entry for each space in the city.
	 *  If walls[x][y] is true, that means there is a wall in the space.
	 *  else the space is free. Humans, vampires, and priests should never go into spaces that
	 *  have a wall.
	 *
	 */
	private boolean walls[][];
	private int width, height;
	public ArrayList<Creature> listCreatures = new ArrayList<>();

	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 * You should modify this function to take the number of:
	 *  vampires,priests, and other creatures too. 
	 */
	public City(int w, int h, int numB, int numP) {
		width = w;
		height = h;
		walls = new boolean[w][h];
		
		randomBuildings(numB);
		
		populate(numP);
	}

	//boolean function that returns true if the inputted (x,y) is a wall or
	//a boundry of the screen.
	//else returns the boolean value of walls at that give (x,y)
	public boolean isWall(int x, int y)
	{
		if((x < 0) || ( x >= width -1) || (y < 0) || (y >= height -1))
			return true;
		else
			return walls[x][y];
	}

	/**
	 * Generates numPeople random people distributed throughout the city.
	 * ETRA FUN : People, vampires & other creatures must not be placed inside walls!
	 * You can modify this function to 
	 *
	 * @param numPeople the number of people to generate
	 * 
	 * 
	 */
	private void populate(int numPeople)
	{
		int x = 0;
		int y = 0;
		// Generate numPeople new humans randomly placed around the city.
		for(int i = 0; i < numPeople; i++)
		{
			x = Helper.nextInt(width); //generate x value within the border
			y = Helper.nextInt(height); //generate y value within the border

			while(isWall(x, y)) //while there is a wall at current index
			{
				x = Helper.nextInt(width); //current x value is updated and changed randomly
				y = Helper.nextInt(height); // current y value is updated and changed randomly
			} //leave while loop when current location is not a wall
			Human newHuman = new Human(x,y); //create new Human at current location
			listCreatures.add(newHuman); //add the new human to the list of humans

			//add 1 ogre for every 50 humans on the screen
			if(i%50 == 0)
			{
				Ogre newOgre = new Ogre(x,y);
				listCreatures.add(newOgre);
			}
		}
		x = Helper.nextInt(width); //generate x value within the border
		y = Helper.nextInt(height); //generate y value within the border

		Vampire newVampire = new Vampire(x,y);
		listCreatures.add(newVampire);

		x = Helper.nextInt(width); //generate x value within the border
		y = Helper.nextInt(height); //generate y value within the border

		Preist newPreist = new Preist(x,y);
		listCreatures.add(newPreist);
	}

	/**
	 * Generates a random set of numB buildings.
	 *
	 * @param numB the number of buildings to generate
	 */
	private void randomBuildings(int numB) {
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;

		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++) {
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;

			for(int r = ty; r < ty + th; r++) {
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++) {
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}

	/**
	 * Updates the state of the city for a time step.
	 */
	public void update() {
		// Call move method on each creature on the board
		for(int i = 0; i < listCreatures.size(); i++)
		{
			listCreatures.get(i).move(this, 1);
		}
	}

	/**
	 * Draw all humans, vampires, and buildings (.
	 */
	public void draw(){
		/* Clear the screen */
		Simulation.dp.clear(Color.BLACK);
		drawWalls();

		//draw each creature in listCreatures to the board
		for(int i = 0; i < listCreatures.size(); i++)
		{
			listCreatures.get(i).draw();
		}
	}

	/**
	 * EXTRA FUN function... not used unless you are going for that
	 * Draw the buildings.
	 * First set the color for drawing, then draw a dot at each space
	 * where there is a wall.
	 */
	private void drawWalls() {
		Simulation.dp.setPenColor(Color.DARK_GRAY);
		for(int r = 0; r < height; r++)
		{
			for(int c = 0; c < width; c++)
			{
				if(walls[c][r])
				{
					Simulation.dp.drawDot(c, r);
				}
			}
		}
	}

}
