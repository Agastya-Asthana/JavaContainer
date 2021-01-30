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
        return null;
    }

    /**
     * 
     * @param index the index of the item whose price is being searched for
     * @return
     */
    public static double getItemPrice(int index) {
        return index;

    }

    /**
     * Prints the entire catalog of items available and their prices
     */
    public static void printCatalog() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Item id \tName \t Price");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        for (int i = 0; i < GROCERY_ITEMS.length; i++) {
            System.out.println(/*item id*/ + "\t\t" + /*item name*/ +
            " \t " + /*$item price*/);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        
}