//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Memory game
// Course: CS 300 Fall 2020
//
// Author: Agastya Asthana
// Email: aasthana3@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: 
// Online Sources:
//
///////////////////////////////////////////////////////////////////////////////

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
  private static int matchedCardsCount = 0; // number of cards matched so far
  // in one session of the game
  private static String message; // Displayed message to the display window
  // number of cards selected
  private static int numOfCardsSelected = 0;

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
    numOfCardsSelected = 0;
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
    processing.background(232, 228, 201); // dirty white
    for (int i = 0; i < cards.length; i++) cards[i].draw();
    matchedCardsCount = 0;
    
    for (int i = 0; i < cards.length; i++) {
      if (cards[i].isMatched()) {
        matchedCardsCount++;
      }
    }

    if (matchedCardsCount == 12) {
      displayMessage(CONGRA_MSG);
      winner = true;
    }

    if (selectedCard1 != null && selectedCard2 != null && !winner) {
      if (matchingCards(selectedCard1, selectedCard2) && numOfCardsSelected < 3){
        displayMessage(MATCHED);
        selectedCard1.setMatched(true);
        selectedCard2.setMatched(true);
      }
      else if (!matchingCards(selectedCard1, selectedCard2) && numOfCardsSelected < 3){
        displayMessage(NOT_MATCHED);
      }
      else if (numOfCardsSelected == 3) {
        numOfCardsSelected = 0;
        selectedCard1 = null;
        selectedCard2 = null;
        for (int i = 0; i < cards.length; i++) {
          if (!cards[i].isMatched() && !isMouseOver(cards[i])) {
            cards[i].deselect(); cards[i].setVisible(false);
          }
          if (isMouseOver(cards[i])) {
            selectedCard1 = cards[i];
            numOfCardsSelected++;
          }
        }
      }
    }
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
    boolean inRangeX = false, inRangeY = false;
    float imageX = card.getX(), imageY = card.getY(); 
    int imageHeight = card.getHeight(), imageWidth = card.getWidth();

    inRangeX = (Math.abs(processing.mouseX-imageX) <= imageWidth/2) ? true : false;
    inRangeY = (Math.abs(processing.mouseY - imageY) <= imageHeight / 2) ? true : false;

    return (inRangeX && inRangeY) ? true: false;
    
  }

  /**
  * Callback method called each time the user presses the mouse
  */
  public static void mousePressed(){
    for (int i = 0; i < cards.length; i++) {
      if (isMouseOver(cards[i]) && !cards[i].isMatched()) {
        numOfCardsSelected++;
        if (selectedCard1 == null) {
          selectedCard1 = cards[i];
          selectedCard1.setVisible(true);
          selectedCard1.select();
          break;
        }
        else if (selectedCard1 != null){ 
          selectedCard2 = cards[i];
          if (selectedCard1 == selectedCard2) {
            selectedCard2 = null;
            numOfCardsSelected = 1;
          }
          else{
            selectedCard2.setVisible(true);
            selectedCard2.select();
            break;
          }
          
        }
      }
    }

  }

  /**
  * Checks whether two cards match or not
  * @param card1 reference to the first card
  * @param card2 reference to the second card
  * @return true if card1 and card2 image references are the same, false otherwise
  */
  public static boolean matchingCards(Card card1, Card card2){
    return (card1.getImage() == card2.getImage()) ? true : false;
  }

  public static void main(String[] args) {
    Utility.startApplication();
  }
}