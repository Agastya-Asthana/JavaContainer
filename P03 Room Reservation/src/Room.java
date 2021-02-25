//////////////////////// FILE HEADER //////////////////////////
//
// Title: The room class to create and maintain classrooms
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

import java.util.ArrayList;

public class Room {
  public static ArrayList<String> names = new ArrayList<>();
  private String name;
  private Person[] occupants;
  private int currentOccupancy = 0;
  private int capacity = 0;
  private int capacityCOVID = 0;

  /**
   * Constructor for this class. Checks for faulty args and assigns name and capcity variables
   * accordingly. Adds the name to the names ArrayList too.
   * @param name the name variable relating to the name of the room
   * @param capacity the maximum number of occupants that can fit in a room.
   */
  public Room(String name, int capacity) {
    if (capacity == 0 /*|| names.contains(name)*/) {
      throw new IllegalArgumentException("The capacity passed in was 0 - INVALID");
    }
    if (names.contains(name)) {
      throw new IllegalArgumentException("The name passed in already exists");
    }
    this.name = name;
    this.capacity = capacity;
    if (capacity %2 == 0) {
      capacityCOVID = capacity/2;
    }
    else{
      capacityCOVID = (capacity/2) + 1;
    }
    Room.names.add(name);
    occupants = new Person[capacity];
  }

  /**
   * Returns a 1D string containing the elements from names ArrayList
   * 
   * @return variable output which contains the names from the names array
   */
  public static String[] getNames() {
    String[] output = new String[names.size()];
    for (int i = 0; i < names.size(); i++)
      output[i] = names.get(i);
    return output;
  }

  /**
   * Accessor for the name variable
   * @return the name variable
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor for the currentOccupancy variable
   * @return the currentOccupancy variable
   */
  public int getOccupancy() {
    return currentOccupancy;
  }

  /**
   * Method returns the current capacity of the room
   * @return the int variable capcity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Returns the number of people that can fit in a room given COVID guidelines
   * Which is just half the total capacity
   * @return the currentOccupancy variable divided by 2
   */
  public int getCOVIDCapacity(){
    return capacityCOVID;
  }

  /**
   * The method cheks whether the varibale of type Person passed in as args exists in the
   * occupants array
   * @param p of type Person
   * @return true if the variable of type Person exists and false otherwise
   */
  public boolean contains(Person p) {
    for (int i = 0; i < occupants.length; i++) {
      if (occupants[i] != null && occupants[i].equals(p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * The methods checks whether the variable "in" of type Person and adds them into the occupants
   * list if the person is not null and there is actually space to store the variable
   * @param in of type Person that passed in as argument
   * @return true if the variable can be added and false otherwise
   */
  public boolean checkIn(Person in) {
    if (in == null) {
      throw new IllegalArgumentException("Person is null");
    }

    if (currentOccupancy+1 > capacityCOVID) {
      return false;
    }

    for (int i = 0; i < occupants.length; i++) {
      if (occupants[i] != null && occupants[i].equals(in)) {
        throw new IllegalArgumentException("Same Person added more than once");
      }
    }


    for (int i = 0; i < occupants.length; i++) {
      if (i % 2 == 0 && occupants[i] == null) {
        occupants[i] = in;
        in.toggleWaiting();
        currentOccupancy++;
        break;
      }
    }
    return true;
  }

  /**
   * This method removes the specified element from occupants array by replacing it with null
   * @param out of type Person representing the element that has to be removed
   * @return true if the element can be checked out and false otherwise
   */
  public boolean checkOut(Person out) {
    if (out == null) throw new IllegalArgumentException("Person is null");
    for (int i = 0; i < occupants.length; i++) {
      if (occupants[i] != null && occupants[i].equals(out)) {
        currentOccupancy--;
        occupants[i].toggleWaiting();
        occupants[i] = null;
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns a string representation of the occupants array as specififed in the 
   * course project description
   * @return a string that contains the names of the people on occupants array
   */
  public String toString() {
    String output = "";
    output += name + "\n===\n";
    for (int i = 0; i < occupants.length; i++) {
      if (i == occupants.length - 1) {
        if (occupants[i] == null) {
          output += "-";
          break;
        }
        output += occupants[i];
        break;
      }
      if (occupants[i] == null) {
        output += "-\n";
        continue;
      }
      output += occupants[i]+"\n";
    }
    return output;
  }
  
}