import java.util.*;

/**
 * @author BrennanAyers
 *
 */
public class Ecosystem {
	public static void main(String args[]) {
		// create ecosystem arrayList
		ArrayList<Animal> ecosystem = new ArrayList<Animal>();
		// populate ecosystem arrayList
		ecosystem.add(0, new Salmon(100));
		ecosystem.add(1, new Shark(5));
		ecosystem.add(2, new Orca(8));
		// Q3dvi: Initially the Orca came out as the only survivor. I changed the Orca
		// starting population down to 8, which causes the salmon population to survive.

		// loop representing 10 years
		for (int year = 1; year <= 10; year++) {
			// Q3c: You cannot use for : each because we don't have an array or item that
			// extends Iterable

			// print ecosystem for year
			System.out.println(String.format("Year %1$d = %2$s", year, ecosystem));

			// For:each loop through ecosystem to execute eating behaviors on all other
			// members. 
			// Q3dv: These lines are polymorphic, as when they are called you cannot tell what the behavior is, 
			//as each class has different behaviors for eat() and reproduce(). 
			for (Animal predator : ecosystem) {
				for (Animal prey : ecosystem) {
					predator.eat(prey);

				}
				predator.reproduce();

			}
			// Q3dii The for:each loop gives an error in this way because we are removing
			// indexes that the loop is trying to 'test' and iterate through, so it gives a
			// runtime error. We must use a for loop in this case to iterate through
			// ecosystem instead.
			for (int animals = 0; animals < ecosystem.size(); animals++) {
				if (ecosystem.get(animals).getPopulation() < 1) {
					ecosystem.remove(animals);
				}
			}
		}

	}
}

/**
 * Animal class provides framework for Animal objects
 *
 * @param name       provides name field for objects
 * @param population dictates the population of each type of animal
 */
//Q1a: An abstract class is one that cannot be instantiated, instead it is written to be the 
//framework for methods which subclasses will determine the individual behavior of. 
//Animal needs to be an abstract method because the simulator will only use specific animals 
// & their behaviors in the simulation
abstract class Animal {
	private String name;
	private int population;

	/**
	 * Constructor for abstract animal class
	 * 
	 * @param name
	 * @param population
	 */
	public Animal(String name, int population) {
		super();
		setPopulation(population);
		this.name = name;
	}

	// Q1a2: The reason we don't need to do negative population checking is
	// because the client
	// class will no longer have a population of given animal in the simulation
	// if they drop to or below 0.
	/**
	 * Gets population of object
	 * 
	 * @return population value for object
	 */
	public int getPopulation() {
		return this.population;
	}

	/**
	 * Sets population value for object
	 * 
	 * @param population
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * toString method to return a formatted String for printing
	 * 
	 * @return formatted string to print animal object values
	 */
	public String toString() {
		return String.format("[Animal %1$s = %2$d]", this.name, this.getPopulation());
	}

	// Q1a3: Abstract methods have no given behavior in the parent class,
	// as they are meant to be "filled in" by the classes that inherit the
	// parent class with class specific behavior.
	/**
	 * Abstract method eat
	 */
	abstract void eat(Animal a);

	/**
	 * Abstract reproduce method
	 */
	abstract void reproduce();
}

/**
 * Salmon class represents population and behavior of current salmon
 *
 */
class Salmon extends Animal {
	// Q2a1:
	// constructor for superclass must be called first in the subclass
	// constructor in order to have fields to overwrite

	/**
	 * Constructor for Salmon class
	 * 
	 * @param population
	 */
	public Salmon(int population) {
		super("Salmon", population);
	}

	// Q2a2: We don't need to write a get/set population because the parent
	// class already defines it,
	// and the Salmon class doesn't have special behavior when it comes to
	// population initialization.

	// Q2a3: The red x for eat and reproduce are present because in the parent
	// class, we defined them as abstract methods. This means they have no
	// "built in" behavior, and must be defined in subclasses. For the set/get
	// population, they have a default behavior defined in the parent class,
	// which means it's not required that they be defined later.

	/**
	 * Sets eat behavior for Salmon class, which is none
	 */
	public void eat(Animal a) {
		// Q2a4: I'm writing this empty function, as the parent eat() method is
		// abstract, so it's required the subclasses define a method for
		// themselves, even with no behavior.
	}

	/**
	 * Sets reproduction behavior for Salmon class
	 */
	public void reproduce() {
		// reproduce by 30% per year
		this.setPopulation((int) (this.getPopulation() * 1.3));
	}

}

/**
 * Shark class representing population and behavior of sharks in simulation
 *
 */
class Shark extends Animal {

	/**
	 * Constructor for Shark class
	 * 
	 * @param population
	 */
	public Shark(int population) {
		super("Shark", population);
	}

	/**
	 * Eat behavior for Shark class for eating Salmon
	 *
	 * @param Animal a, the animal object passed to be eaten
	 */
	public void eat(Animal a) {
		if (a instanceof Salmon) {
			// Q2a5: Subtracts a salmon for every current shark alive by
			// assigning the current population of salmon (a.setPop) minus the current
			// population of shark (this.getPop)
			a.setPopulation(a.getPopulation() - this.getPopulation());
		}
	}

	/**
	 * Sets reproduction behaviors for Shark class
	 */
	public void reproduce() {
		this.setPopulation((int) (this.getPopulation() * 1.1));

	}

}

/**
 * Orca class which extends the Animal parent class
 *
 */
class Orca extends Animal {

	/**
	 * Constructor for Orca class
	 * 
	 * @param population
	 */
	public Orca(int population) {
		super("Orca", population);
	}

	/**
	 * Dictates eating behavior for Orca class, differing based on whether a Shark
	 * or Salmon was passed
	 * 
	 * @param Animal a, the passed in animal which is either a Shark or Salmon
	 */
	public void eat(Animal a) {
		if (a instanceof Shark) {
			// Q2a6: if the Animal a is a shark and there are 3 or more Orca, then one shark
			// get eaten, otherwise no changes
			if (this.getPopulation() >= 3) {
				a.setPopulation(a.getPopulation() - 1);
			}
		}
		// behavior of Orca when a Salmon is the animal being encountered, which eats 5
		// salmon per Orca alive
		if (a instanceof Salmon) {
			a.setPopulation(a.getPopulation() - (this.getPopulation() * 5));
		}
	}

	/**
	 * Dictates reproduction of Orca class, subtracting by 1 every call (every year)
	 */
	public void reproduce() {
		this.setPopulation(this.getPopulation() - 1);
	}

}
