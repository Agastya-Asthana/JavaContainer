//////////////////////// FILE HEADER //////////////////////////
//
// Title: Occupancy tester class to test classes Person & Room
// Course: CS 300 Fall 2020
//
// Author: Agastya Asthana
// Email: aasthana3@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

public class OccupancyTester {

  /**
   * Tests the person class with test data to make sure it works as intended
   * The tests check for nulls, toggleWaiting, Constructor, and equals
   * @return true if the test passed and false otherwise
   */
  public static boolean testPerson() {
    Person p1 = new Person("Agastya");
    if (p1 == null){
      System.out.println("The Person object is null. Problem with constructor");
      return false;
    }
    if (!p1.getName().equals("Agastya")) {
      System.out.println("The accessor method to get name is faulty");
      return false;
    }
    p1.toggleWaiting();
    if (p1.isWaiting()) {
      System.out.println("Either the toggle method or the accessor method of is waiting is faulty");
      return false;
    }
    Person p2 = new Person("Agastya");
    Person p3 = new Person("Claudia");
    if (!p1.equals(p2) || p1.equals(p3)) {
      System.out.println("Faulty equals method. I don't know how you messed this up!");
      return false;
    }
    return true;
  }

  /**
   * This method rigourously tests the constructor of the room class
   * Method checks against, duplicates, full capcity, and normal instantiation
   * @return true if the constructor passed the tests and false otherwise
   */
  public static boolean testRoomConstructor() {
    boolean errorThrown = false;
    Room room1 = new Room("Math 222", 30);
    if (room1 == null) {
      System.out.println("The room constructor is faulty");
      return false;
    }

    try {
      Room room2 = new Room("Math 222", 30);
    } catch (IllegalArgumentException e) {
      errorThrown = true;
    }
    if (!errorThrown){
      System.out.println("Creating another room with the same name does not cause error!");
      return false;
    }

    errorThrown = false;
    try {
      Room room3 = new Room("Comm Arts A", 0);
    } catch (IllegalArgumentException e) {
      errorThrown = true;
    }
    if (!errorThrown) {
      System.out.println("Creating room with 0 capacity does not cause error!");
      return false;
    }
    return true;
  }

  /**
   * Method checks the accessors of the room class.
   * Checks against COVID capacity, notmal capacity, getting room names, and getting room name
   * @return true if the accessors in the room class passed all tests and false otherwise
   */
  public static boolean testRoomAccessors() {
    Room r1 = new Room("CS 252", 32);
    Room r2 = new Room("Math 221", 20);
    Room r3 = new Room("Psych 202", 32);

    if (!r1.getName().equals("CS 252")) {
      System.out.println("Faulty getter method for name variable");
      return false;
    }
    if (r1.getOccupancy() != 0) {
      System.out.println("Faulty getter method for occupancy variable");
      return false;
    }
    if (r1.getCapacity() != 32) {
      System.out.println("Faulty getter method for capacity variable");
      return false;
    }
    if (r1.getCOVIDCapacity() != 16) {
      System.out.println("Faulty getter method for COVID capacity");
      return false;
    }

    // Added Math 222 because it was added from a previous call in a different method
    String[] expected = new String[]{"Math 222", "CS 252", "Math 221", "Psych 202"};  
    String[] returned = Room.getNames();
    for (int i = 0; i < returned.length; i++) {
      if (expected[i] != returned[i]) {
        System.out.println("Faulty static getter method getNames()");
        return false;
      }
    }
    return true;
  }

  /**
   * Methods checks whether the checkIn method performs as expected and handles all corner cases
   * Checks against duplicates, variable updaing, nulls, and exceeding capacity
   * @return true if the checkIn method passes all testa and false otherwise
   */
  public static boolean testRoomCheckIn() {
    Person neal = new Person("Neal");
    Person mozzie = new Person("Mozzie");
    Person diana = new Person("Diana");
    Room r1 = new Room("French 102", 6);

    if (!r1.checkIn(neal) || !r1.checkIn(mozzie)) {
      System.out.println("Faulty checkIn method. The persons should've been checked in");
      return false; 
    }
    if (neal.isWaiting() || mozzie.isWaiting()) {
      System.out.println("The waiting variable associated with each person is faulty");
      return false;
    }

    boolean errorThrown = false;
    try {
      r1.checkIn(neal);
    } catch (IllegalArgumentException e) {
      errorThrown = true;
    }
    if (!errorThrown) {
      System.out.println("Adding the same person twice did not result in error!");
      return false;
    }
    errorThrown = false;

    try {
      r1.checkIn(null);
    } catch (IllegalArgumentException e) {
      errorThrown = true;
    }
    if (!errorThrown) {
      System.out.println("Adding a null person did not result in an error!");
      return false;
    }
    errorThrown = false;

    //Gotta add a random extra person in to fill the room
    r1.checkIn(new Person("bsdk"));

    if (r1.checkIn(diana)) {
      System.out.println("Adding more people than capacity did not return false");
      return false;
    }

    return true;
  }

  /**
   * Method runs tests on the checkOut method to see if it performs as expected
   * Checks against nulls, duplicates, and checkout when the item does not exit
   * @return true if the checkOut method passes all the tests and false otherwise
   */
  public static boolean testRoomCheckout() {
    Room r1 = new Room("AIS 100", 6);
    r1.checkIn(new Person("Hobbes"));
    r1.checkIn(new Person("Shaw"));
    r1.checkIn(new Person("Dom"));

    boolean errorThrown = false;
    try {
      r1.checkOut(null);
    } catch (IllegalArgumentException e) {
      errorThrown = true;
    }
    if (!errorThrown) {
      System.out.println("Adding a null person did not result in error");
      return false;
    }

    if (!r1.checkOut(new Person("Hobbes"))) {
      System.out.println("The function returned false when it should've returned true");
      return false;
    }
    if (r1.checkOut(new Person("Hobbes"))) {
      System.out.println("The functioned returned true when it should've return false");
      return false;
    }


    return true;
  }

  /**
   * Method runs tests on the toString method to see if it performs to expectation
   * Sample input and expected output is given to be checked against
   * @return true if the toString method passes all tests and false otherwise
   */
  public static boolean testRoomToString() {
    Room r1 = new Room("CS 1240", 9);
    r1.checkIn(new Person("Mouna"));
    r1.checkIn(new Person("Hobbes"));
    r1.checkIn(new Person("Placeholder"));
    r1.checkIn(new Person("Michelle"));
    r1.checkIn(new Person("Sulong"));
    r1.checkOut(new Person("Placeholder"));

    String expectedOutput = "CS 1240\n===\nMouna\n-\nHobbes\n-\n-\n-\nMichelle\n-\nSulong";
    String actual = r1.toString();
    if (!expectedOutput.equals(actual)) {
      System.out.println("The expected string was not returned");
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(testPerson());
    System.out.println(testRoomConstructor());
    System.out.println(testRoomAccessors());
    System.out.println(testRoomCheckIn());
    System.out.println(testRoomCheckout());
  }
}