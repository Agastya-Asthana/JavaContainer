//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    A self checkout kiosk program
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
 * This class handles the kiosk interaction like buying, getting products, and
 * checking out
 */
public class SelfCheckoutKiosk {
  public static final double TAX_RATE = 0.06; // Sales Tax

  public static final String[][] GROCERY_ITEMS = new String[][] { { "Apple", "$1.59" }, 
          { "Avocado", "$0.59" }, { "Banana", "$0.49" }, { "Beef", "$3.79" }, 
          { "Blueberry", "$6.89" }, { "Broccoli", "$1.79" }, { "Butter", "$4.59" }, 
          { "Carrot", "$1.19" }, { "Cereal", "$3.69" }, { "Cheese", "$3.49" },
          { "Chicken", "$5.09" }, { "Chocolate", "$3.19" }, { "Cookie", "$9.5" }, 
          { "Cucumber", "$0.79" }, { "Eggs", "$3.09" }, { "Grape", "$2.29" }, 
          { "Ice Cream", "$5.39" }, { "Milk", "$2.09" }, { "Mushroom", "$1.79" }, 
          { "Onion", "$0.79" }, { "Pepper", "$1.99" }, { "Pizza", "$11.5" },
          { "Potato", "$0.69" }, { "Spinach", "$3.09" }, { "Tomato", "$1.79" } };

  /**
   * This method returns the name of the item at a given index
   * 
   * @param index the index of item being searched for
   * @return
   */
  public static String getItemName(int index) {
    return GROCERY_ITEMS[index][0];
  }

  /**
   * Returns the price of the item at a given index
   * @param index the index of the item whose price is being searched for
   * @return
   */
  public static double getItemPrice(int index) {
    String priceWithoutDollarSymbol = GROCERY_ITEMS[index][1].substring(1);
    return Double.parseDouble(priceWithoutDollarSymbol);

  }

  /**
   * Prints the entire catalog of items available and their prices
   */
  public static void printCatalog() {
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id \tName \t\t Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < GROCERY_ITEMS.length; i++) {
        System.out.println(i + "\t\t" + GROCERY_ITEMS[i][0] +
        "    \t " + GROCERY_ITEMS[i][1]);
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Adds the item at a given id to the bagging area
   * @param id the index of the object to add to bag
   * @param items an oversized array containing a bag of customer items
   * @param size a size variable to gauge the number of valid items in bag
   * @return the size variable which equates to the number of items in bag
   */
  public static int addItemToBaggingArea(int id, String[] items, int size) { 
    if (size < items.length){
        items[size] = GROCERY_ITEMS[id][0];
        size++;
    }
    else{
        System.out.println("Error! No additional item can be scanned." +
                            " Please wait for assistance.");
    }
    return size;
  }

  /**
   * Returns the number of times a given item in the items array occurs
   * @param item the item being searched for
   * @param items an oversize array of all items
   * @param size size variable corresponding with the oversize array items
   * @return the count variable showing the number of times the item occured
   */
  public static int count(String item, String[] items, int size) { 
    int count = 0;
    for (int i = 0; i < size; i++) {
        if (items[i].equalsIgnoreCase(item)) {
            count++;
        }
    }
    return count;
  }

  /**
   * Method returns the first occurance of an item in the items array
   * @param item the item whose index is to be found
   * @param items an oversize array of elements in the "bag"
   * @param size a size variable corresponding to the items oversize array
   * @return the index variable showing the first index at which the item occured
   */
  public static int indexOf(String item, String[] items, int size){
    int index = -1;
    for (int i = 0; i < size; i++) {
        if (items[i].equalsIgnoreCase(item)) {
            index = i;
            break;
        }
    }
    return index;
  }

  /**
   * Removes the first occurance of a given items and adjust the oversize array
   * @param itemToRemove the name of the item to remove
   * @param items an oversize array of items in "bag"
   * @param size a size variable corresponding with oversize array items
   * @return the size of the array
   */
  public static int remove(String itemToRemove, String[] items, int size) { 
    boolean itemFound = false;

    if (size == 0) {
      return 0;
    }
    
    for (int i = 0; i < size; i++) {

      if (itemFound){
        items[i-1] = items[i];
      }
      else if (items[i].equalsIgnoreCase(itemToRemove)) {
        itemFound = true;
      }
    }

    if (itemFound) {
      size--;
      items[size] = null;
    }
    else{
      System.out.println("WARNING: item not found.");
    }
    return size;
  }

  /**
   * Method returns to number that does not count the number of duplicates in items array
   * As an addition the items set array will be perfect array although the driver program will treat
   * it as an oversize array which is why the method output returns a size
   * @param items array of items whose uniqeness has to be found out - oversize array
   * @param size number of elements in items array
   * @param tempItemsSet a new array of those unique items
   * @return the size of the new itemsSet
   */
  public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet){
    String[] tempItemsSet = new String[20];
    tempItemsSet[0] = items[0];
    int tempItemsSetSize = 1;

    for (int i = 1; i < size; i++) {
      boolean doesExist = false;
        for (int j = 0; j < tempItemsSetSize; j++) {
          if (items[i] == tempItemsSet[j]) {
            doesExist = true;
            break;
          }
        }
        if (!doesExist) {
          tempItemsSet[tempItemsSetSize] = items[i];
          tempItemsSetSize++;
        }
    }


    for (int i = 0; i < tempItemsSetSize; i++) {
        itemsSet[i] = tempItemsSet[i];
    }

    return tempItemsSetSize;
  }

  /**
   * Returns the total price of the items in the items array without tax
   * @param items an oversize array of item names
   * @param size the number of elements in items array
   * @return the double variable that corresponds to the total sum
   */
  public static double getSubTotalPrice(String[] items, int size) {
    double sum = 0.0;
    for (int i = 0; i < size; i++) {

        for (int j = 0; j < GROCERY_ITEMS.length; j++) {
            if (GROCERY_ITEMS[j][0].equalsIgnoreCase(items[i])){
                sum += Double.parseDouble(GROCERY_ITEMS[j][1].substring(1));
                break;
            }
        }
    }

    return sum;
  }

}

