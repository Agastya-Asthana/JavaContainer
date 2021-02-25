//////////////////////// FILE HEADER //////////////////////////
//
// Title: Person class to create and maintain a person object
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

public class Person {
  private String name;
  private boolean isWaiting;

  /**
   * Constructor for this class takes in a name variable and updates corresponding fields
   * @param name name of the Person
   */
  public Person(String name){
    this.name = name;
    isWaiting = true;
  }

  /**
   * Accessor for the name variable
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * Accessor for the isWaiting variable
   * @return boolean value of isWaiting
   */
  public boolean isWaiting() {
    return isWaiting;
  }

  /**
   * Method flips the value of the isWaiting variable. If it was false then this makes it true 
   * and if it was true then this makes it false
   */
  public void toggleWaiting() {
    this.isWaiting = !this.isWaiting;
  }

  /**
   * Compares the current object to one passed in as parameters
   * @param o the object which is getting comapred to this one
   */
  public boolean equals(Object o) {
    if (o instanceof Person){
      return this.name.equals(((Person) o).name);
    }
    return false;
  }
  
}