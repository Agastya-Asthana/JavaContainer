import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

public class MemoryGame{
  //Congratulations message
  private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
  // Cards not matched message
  private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
  // Cards matched message
  private final static String MATCHED = "CARDS MATCHED! Good Job!";
  // 2-D array which stores cards coordinates on the window display
  private final static float[][] CARDS_COORDINATES = 
    new float[][] {{170, 170}, {324, 170}, {478, 170}, {632, 170}, {170, 324}, {324, 324},
                  {478, 324}, {632, 324}, {170, 478}, {324, 478}, {478, 478}, {632, 478}};
  //Array that stores the card images filenames
  private final static String[] CARD_IMAGES_NAMES = new String[] {"ball.png", "redFlower.png",
      "yellowFlower.png", "apple.png", "peach.png", "shark.png"};

  private static PApplet processing; // PApplet object that represents
  // the graphic display window
  private static Card[] cards; // one dimensional array of cards
  private static PImage[] images = new PImage[6]; // array of images of the different cards
  private static Card selectedCard1; // First selected card
  private static Card selectedCard2; // Second selected card
  private static boolean winner; // boolean evaluated true if the game is won,
  // and false otherwise
  private static int matchedCardsCount; // number of cards matched so far
  // in one session of the game
  private static String message; // Displayed message to the display window

  /**
  * Defines the initial environment properties of this game as the program starts
  */
  public static void setup(PApplet processing){
    //load ball.png image file as PImage object and store its reference
    for (int i = 0; i < 6; i++) {
      images[i] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[i]);
    }
    MemoryGame.processing = processing;
    startNewGame();

  }

  /**
   * Initialize the game
   */
  public static void startNewGame() {
    selectedCard1 = null;
    selectedCard2 = null;
    matchedCardsCount = 0;
    winner = false;
    message = "";

    cards = new Card[CARDS_COORDINATES.length];
    int[] mixedUp = Utility.shuffleCards(cards.length);
    for (int i = 0; i < mixedUp.length; i++) {
      cards[i] = new Card(images[mixedUp[i]], CARDS_COORDINATES[i][0], CARDS_COORDINATES[i][1]);
    }
  }

  /**
  * Callback method called each time the user presses a key
  */
  public static void keyPressed(){
    if (processing.key == 'N' || processing.key == 'n') startNewGame();
  }

  /**
  * Callback method draws continuously this application window display
  */
  public static void draw(){
    // Set the color used for this background of the Processing window
    processing.background(32, 48, 56); // Grayish black
    for (int i = 0; i < cards.length; i++) cards[i].draw();
  }

  /**
   * Displays a given message to the display window
   * 
   * @param message to be displayed to the display window
   */
  public static void displayMessage(String message) {
    processing.fill(0);
    processing.textSize(20);
    processing.text(message, processing.width / 2, 50);
    processing.textSize(12);
  }

  /**
  * Checks whether the mouse is over a given Card
  * @return true if the mouse is over the storage list, false otherwise
  */
  public static boolean isMouseOver(Card card){
    PImage cardImage = card.getImage();
    
  }
  public static void main(String[] args) {
    Utility.startApplication();
  }
}