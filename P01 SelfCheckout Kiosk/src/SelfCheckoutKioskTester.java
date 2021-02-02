//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Method tester for SelfCheckoutKiosk class
// Course:   CS 300 Fall 2020
//
// Author:   Agastya Asthana
// Email:    aasthana3@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  github.com: helped with source control
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class is responsible for testing the methods available in the
 * SelfCheckoutKiosk class
 */
public class SelfCheckoutKioskTester {

  /**
   * This method tests the getters for item name and price
   * @return a boolean value true if test passes, false if test fails
   */
  public static boolean testItemNameAndPriceGetterMethods() {
    for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
      //Tests item name getter
      if (SelfCheckoutKiosk.getItemName(i) != SelfCheckoutKiosk.GROCERY_ITEMS[i][0]){
        System.out.printf("Test faliure: Grocery list contains \"%s\" at index %d, however" + 
          " getItemName() returns \"%s\"\n", SelfCheckoutKiosk.GROCERY_ITEMS[i][0],
            i, SelfCheckoutKiosk.getItemName(i));
            return false;
      }

      //Tests iten price getter
      double listValue = Double.parseDouble(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1));
      double methodValue = SelfCheckoutKiosk.getItemPrice(i);
      if (listValue != methodValue) {
        System.out.printf("Test faliure: Grocery list states price of %s as $%d, but" + 
          " getItemPrice() states the price as $%d\n",
            SelfCheckoutKiosk.GROCERY_ITEMS[i][0], listValue, methodValue);
        
        return false;
      }        
    }
    return true;
  }

  /**
   * This is the main method which runs when file is executed to run the tests
   * @param args any arguments that may be passed to the file
   */
  public static void main(String[] args) {
    System.out.println("testItemNameAndPriceGetterMethods(): " + 
      testItemNameAndPriceGetterMethods());
    SelfCheckoutKiosk.printCatalog();
  }
}
