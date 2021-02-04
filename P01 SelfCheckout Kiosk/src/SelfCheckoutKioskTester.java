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
   * This method tests the addItemToBaggingArea() method with corner cases too
   * @return true is the tests pass and false if the tests fail
   */
  public static boolean testAddItemToBaggingArea() {
    String[] items = new String[10];
    int size = 0;

    size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);
    if (size != 1) {
      System.out.println("Test faliure: Tried to add one item to an empty "
        + "bagging area. The returned size must be 1. But addItemToBaggingArea "
        + "method returned a different output");

      return false;
    }
    if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
      System.out.println("Test Faliure: Tried to add an apple to an empty bagging area. " +
        "But that item wasnot appropriately added to the contents of the items array.");
      return false;
    }

    items = new String[] {"Milk", "Chocolate", "Onion", null, null, null, null};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);
    if (size != 4) {
      System.out.println("Problem detected: Tried to add only one item to an empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
      return false;
    }
    if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
      System.out.println("Problem detected: Tried to add one item to an non-empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
      return false;
    }

    items = new String[] {"Pizza", "Eggs", "Apples"};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);
    if (size != 3) {
      System.out.println("Problem detected: Tried to add one item to an full, "
          + "bagging area. But instead of giving back the same size of the array, "
          + "a different size was returned.");
      return false;
    }

    return true;
  }

  /**
   * This method tests the count method of Self Checkout class 
   * @return true is the tests pass and false if they fail
   */
  public static boolean testCount() {
    //0 occurance
    String[] items = new String[] {"Apple", "Apple", "Apple", "Grape", "Grape", null, null};
    int size = 5;
    int methodOutput = SelfCheckoutKiosk.count("pizza", items, size);
    if (methodOutput != 0) {
      System.out.println("Test faliure: An item not existing in the items array was searched for "+
        "program returned a non-zero value.");
      return false;
    }

    methodOutput = SelfCheckoutKiosk.count("apple", items, size);
    if (methodOutput != 3) {
      System.out.println("Test faliure: An item has three occurances but the  "
        + "program returned a different value than three.");
      return false;
    }
    return true;
  }

  /**
   * Tests the method to find index of an item being searched for
   * @return true if the tests pass and false is they don't
   */
  public static boolean testIndexOf() {
    String[] items = new String[] {"Apple", "Apple", "Apple", "Grape", "Grape", null, null};
    int size = 5;
    if (SelfCheckoutKiosk.indexOf("Grape", items, size) != 3){
      System.out.println("Test Faliure: the item being searched is present at index 3 but" +
        " the program returned something else");
      return false;
    }
    if (SelfCheckoutKiosk.indexOf("Mango", items, size) != -1) {
      System.out.println("Test Faliure: the item being searched is not present in the array but"
          + " the program returned something else");
      return false;
    }
    return true;
    
  }

  /**
   * This method tests whether the method from SelfCheckoutKiosk is able to correctly
   * to remove objects and move the remaining items
   * @return true if the program was able to correctly remove the product and false otherwise
   */
  public static boolean testRemove() {
    String[] items = new String[] {"eggs", "banana", "Avocado", "Milk", "Potato", null, null, null};
    int size = 5;
    String[] outputArray = new String[] {"eggs", "Avocado", "Milk", "Potato", null, 
                                        null, null, null};

    if (SelfCheckoutKiosk.remove("banana", items, size) != 4) {
      System.out.println("Test faliure 1: the number of items returned were incorrect");
      if (!items.equals(outputArray)) {
        System.out.println("The array given was not changed to the correct state.");
      }
      return false;
    }
    if (SelfCheckoutKiosk.remove("random", items, size-1) != 4) {
      System.out.println("Test faliure 2: the number of items returned were incorrect");
      return false;
    }
    return true;
  }

  public static boolean testGetUniqueCheckedOutItems(){
    String[] items = new String[] {"eggs", "banana", "Avacado", "eggs", "Milk", "Potato", "Milk",
                                  null, null};
    String[] itemsSet = new String[7];
    int size = 7;
    int methodOutput = SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet);
    String[] expectedItemSet = new String[] {"eggs", "banana", "Avacado", "Milk", "Potato", 
      null, null};

    for (int i = 0; i < 5; i++) {
      if (!itemsSet[i].equals(expectedItemSet[i]) || methodOutput != 5){
        System.out.println("Test Faliure: The array returned did not equal the expected one or "
            + "the size returned was incorrect");
        return false;
      }
    }
    return true;
  }
  
  public static boolean testGetSubTotalPrice(){
    String[] items = new String[] {"Carrot", "Chicken", "Cereal", "Pepper", "Pizza", null};
    int size = 5;
    double methodOutput = SelfCheckoutKiosk.getSubTotalPrice(items, size);
    if (methodOutput != 23.46) {
      System.out.println("Test Faliure: the number given back is not the correct subtotal");
      return false;
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
    System.out.println("testAddItemToBaggingArea(): " + testAddItemToBaggingArea());
    System.out.println("testCount(): " + testCount());
    System.out.println("testIndexOf(): " + testIndexOf());
    System.out.println("testRemove(): " + testRemove());
    System.out.println("testGetUniqueCheckedItems(): " + testGetUniqueCheckedOutItems());
    System.out.println("testGetSubTotalPrice(): " + testGetSubTotalPrice());
  }
}
