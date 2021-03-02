import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class CarrotPatch extends PApplet {
  private PImage backgroundImage;
  protected ArrayList<GUIListener> objects;

  /**
   * Defines the initial environment properties such as screen size and to load background images
   * and fonts as the program starts. It also initializes the backgroundImage, the data fields, and
   * sets the display window for the different graphic objects which will be drawn in this
   * application
   */
  @Override
  public void setup() {
    carrotPatchSettings(); 

    objects = new ArrayList<GUIListener>();
	
	  // load the background image 
    backgroundImage = this.loadImage("images" + File.separator + "background.png");

    Carrots.settings(this);
    Button.setProcessing(this);

    // Adds buttons to the display window
    PlantCarrotsButton carrotsButton = new PlantCarrotsButton(43, 16);
    AddRabbitButton rabbitButton = new AddRabbitButton(129, 16);
    AddWolfButton wolfButton = new AddWolfButton(215, 16);
    RestartButton restartButton = new RestartButton(301, 16);

    objects.add((GUIListener) carrotsButton);
    objects.add((GUIListener) rabbitButton);
    objects.add((GUIListener) wolfButton);
    objects.add((GUIListener) restartButton);

    Animal.setProcessing(this);

  }

  /**
   * Sets the display window title, text allignment, image mode, rectangle mode, and activates this
   * PApplet object to listen to the mouse events and user inputs
   */
  private void carrotPatchSettings() {
    this.getSurface().setTitle("Carrot Patch"); // Displays text in the title of the display window
    this.textAlign(PApplet.CENTER, PApplet.CENTER); // Sets the current alignment for drawing text
                                                    // to CENTER
    this.imageMode(PApplet.CENTER); // Sets the location from which images are drawn to CENTER
    this.rectMode(PApplet.CORNERS); // Sets the location from which rectangles are drawn.
    // rectMode(CORNERS) interprets the first two parameters of rect() method as the location of one
    // corner, and the third and fourth parameters as the location of the opposite corner.
    // rect() method draws a rectangle to the display window
    this.focused = true; // Confirms that our Processing program is "focused," meaning that it is
                         // active and will accept mouse or keyboard input.
  }


  /**
   * Sets the size of the application display window
   */
  @Override
  public void settings() {
    size(800, 600); // sets the size of the display window to 800 x 600 pixels
  }

  /**
   * Callback method called in an infinite loop. It draws the Jungle Park's window display
   */
  @Override
  public void draw() {
  	// TODO: 
  	
    // draw the background image at the center of the display window
    this.image(backgroundImage, this.width / 2, this.height / 2);
    // Draw carrots
    Carrots.draw();
    // traverse the objects list and draw all the interactive objects
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }

  }

  /**
   * Callback method called each time the mouse is pressed
   */
  @Override
  public void mousePressed() {
    // TODO: traverse the objects list and call mousePressed() method defined for every interactive
    // object
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break;
      }
    }

  }

  /**
   * Callback method called each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    // TODO:
    // traverse the objects list and call mouseReleased() method defined for every interactive
    // object
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }

  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyPressed() {

    switch (Character.toUpperCase(this.key)) {
      case 'R':
        // TODO:
        // Add a new Rabbit to this CarrotPath's objects list

        break;
      case 'W':
        // TODO:
        // Add a new Wolf to this CarrotPath's objects list

        break;
      case 'D':
        // TODO:
        // Remove an animal from this carrot patch if the mouse is over it
        // Traverse the objects list and consider only animal objects in your search
        // Only one animal will be removed. If the mouse is over more than one animal
        // (overlapping animals) when the key-D is pressed, the first one in the objects
        // list (stored at the lowest index) will be removed.

    }

  }

  /**
   * Removes all the animals and planted carrots from this carrot patch
   */
  public void removeAll() {
    // TODO

    // Remove all the carrots from this CarrotPatch.
    // [Hint] check whether there is a method in the Carrots class which can help implement this
    // behavior


    // Traverse the objects ArrayList and remove only the instances of Animal from the list
    // [Hints]:
    // DO NOT USE a for-each loop, use a normal for loop or a while loop
    // If you use a for loop to traverse the objects list and remove the animals, make sure
    // to decrement the index i each time you remove an animal before going to the next iteration.
    // Otherwise, the next element will be skipped

  }
  public static void main(String[] args) {
    PApplet.main("CarrotPatch");
  }
}

/**
 * PlantCarrotsButton */
class PlantCarrotsButton extends Button{

  public PlantCarrotsButton(float x, float y){
    super("Plant Carrots", x, y);
  }

  @Override
  public void mousePressed() {
    //System.out.println("Plant Carrots Button Pressed");
    Carrots.plant();
  }
  
}

class AddRabbitButton extends Button{

  public AddRabbitButton(float x, float y){
    super("Add Rabbits", x, y);
  }

  @Override
  public void mousePressed() {
    
  }

}

class AddWolfButton extends Button{

  public AddWolfButton(float x, float y){
    super("Add Wolf", x, y);
  }

  @Override
  public void mousePressed() {
    System.out.println("Add Wolf Button Pressed!");
  }

}

class RestartButton extends Button{

  public RestartButton(float x, float y){
    super("Restart", x, y);
  }

  @Override
  public void mousePressed() {
    System.out.println("Reset Button Pressed!");
  }

}
