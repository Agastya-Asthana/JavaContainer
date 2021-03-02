import java.io.File;

public class Rabbit extends Animal {
  private static final String RABBIT = "images"+ File.separator + "rabbit.png";
  private static final String TYPE = "R"; // A String that represents the rabbit type
  private static int hopStep = 70; // one hop step
  private static int scanRange = 175; // scan range to watch out for threats
  private static int nextID = 1; // class variable that represents the identifier
  // of the next rabbit to be created
  private final int ID; // positive number that represents the order of this rabbit
  /**
  * Creates a new rabbit object located at a random position of the display window
  */
  public Rabbit() {
  // Set rabbit drawing parameters
    super(RABBIT);
    // Set rabbit identification fields
    ID = nextID;
    this.label = TYPE + ID; // String that identifies the current rabbit
    nextID++;
  }
  // getter of Rabbit.scanRange static field
  public static int getScanRange() {
    return scanRange;
  }
  // getter of Rabbit.hopStep static field
  public static int getHopStep() {
    return hopStep;
  }
}
