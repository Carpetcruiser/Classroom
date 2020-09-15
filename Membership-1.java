// REGRADE PREFACE: Line 26, updated Javadoc 
// Line 214, removed user input which was not in the spec. Changed to parameter. 

import java.util.*;
import java.io.*;

/**
 * Programming Project 2 Part A - ArrayLists Part B - Sets/Maps
 * 
 * This project is replicating the basic function of ArrrayLists(construct,
 * search, remove, add, sort) with manual methods
 * 
 * Brennan Ayers 5/12/2020// REGRADE PREFACE: Line 26, updated Javadoc // Line
 * 214, removed user input which was not in the spec. Changed to parameter.
 * 
 * import java.util.*; import java.io.*;
 * 
 * /** Programming Project 2 Part A - ArrayLists Part B - Sets/Maps
 * 
 * This project is replicating the basic function of ArrrayLists(construct,
 * search, remove, add, sort) with manual methods
 * 
 * Brennan Ayers 5/12/2020
 *
 */
public class Membership {

	/**
	 * Runs PartA and PartB
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		PartA();
		PartB();
	}

	/**
	 * This function takes a .csv file of members, writes them to an ArrayList and
	 * allows for the removal of members, or the addition of new members. It prints
	 * the changes along the way. We use calls to readNames() to execute these
	 * desired functions.
	 */
//	public static void PartA() throws FileNotFoundException {
//		// establish ArrayLists
//		String members = "members.csv";
//		ArrayList<Person> membership = readNames(members);
//		String removeName = "removes.csv";
//		ArrayList<Person> removes = readNames(removeName);
//
//		// Print total amount of names in membership List
//		System.out.println(String.format("Read Total of %d names", membership.size()));
//
//		// Print amount of removals in removes List
//		System.out.println(String.format("Read Total of <%1$s> removes", removes.size()));
//
//		// Create boolean value to test if any names were found matching
//		boolean personFound = false;
//		int personTracker = 0;
//
//		// Compare emails from removes list to membership list
//		for (Person toLookForRemoval : removes) {
//			personFound = false;
//
//			// You cannot use nested for:each loops, as we're modifying the List
//			// as we iterate
//			for (int searchMembers = 0; searchMembers <= membership.size() - 1; searchMembers++) {
//
//				// Test whether each entry in membership matches the email from
//				// removes
//				if (membership.get(searchMembers).compareTo(toLookForRemoval) == 0) {
//					membership.remove(searchMembers);
//					personFound = true;
//					personTracker++;
//				}
//
//			}
//
//			// If the user from the removes list was NOT found, print this
//			// message
//			if (personFound == false) {
//				System.out.println(String.format("Could not find %1$s with Email: %2$s to remove!",
//						toLookForRemoval.getFirstName(), toLookForRemoval.getEmail()));
//			}
//
//			// 4.3.3 I did not find any of the 10 names to delete, and cannot
//			// use .equals because it compares the whole objects against each
//			// other
//
//			// 4.3.5 It crashes because we're "pulling the rug" out from under
//			// the for:each loop by modifying the List while going through it
//
//			// 4.3.6 We don't need to adjust the index, as ArrayLists shift
//			// their values forward when an index is removed.
//
//			// 4.3.7 We wouldn't be able to use removeAll because we are trying
//			// to only filter by specific values (the email) in the object,
//			// which can have duplicate values
//		}
//
//		// testing/verification about removals from membership functioning
//		// properly
//		System.out.print("Amount of removals found:");
//		System.out.println(personTracker);
//		System.out.println(String.format("Total of %1$s names after removals", membership.size()));
//
//		// create new ArrayList adds
//		String addsFileName = "adds.csv";
//		ArrayList<Person> adds = readNames(addsFileName);
//
//		// verifying the read from adds worked correctly
//		System.out.println(String.format("Read total of %1$s adds", adds.size()));
//
//		// for each loop of last names to add
//		for (Person toBeAdded : adds) {
//			boolean nameAddedTracker = false;
//
//			// Q5.3 We cannot use compareTo, because for this type of
//			// sorting there would be no way to differentiate inputs to make
//			// it polymorphic.
//
//			// Q5.3.1 I'm adding the new objects to membership to the index 1
//			// AFTER the index with the matching object name
//
//			// Q5.6 We can't use .addAll in this code because it would add all
//			// of the objects to the end of the List, instead of placed where we
//			// want them.
//
//			// for loop the length of membership
//			for (int findIndexToAdd = 0; findIndexToAdd <= membership.size() - 1; findIndexToAdd++) {
//
//				// if lastName of value to be added equals the last name of any
//				// value in membership, add the value 1 index after
//				if (toBeAdded.getLastName().equals(membership.get(findIndexToAdd).getLastName())
//						&& nameAddedTracker != true) {
//					membership.add(findIndexToAdd + 1, toBeAdded);
//					nameAddedTracker = true;
//				}
//			}
//
//			// tests whether the last name being tested had been added to the
//			// List
//			if (nameAddedTracker != true) {
//				System.out.println(String.format("Last name %1$s couldn't be found!", toBeAdded.getLastName()));
//			}
//		}
//
//		// testing List size after adding new values from adds
//		System.out.println("New size after adding is " + membership.size());
//
//		Collections.sort(membership);
//		testPartA(membership);
//	}

	/**
	 * PartB() reads a master and slave list from files, and determines what the
	 * differences in records exist between the slave and master lists. It will then
	 * change the slave Map to match.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void PartB() throws FileNotFoundException {
		// read master list
		Map<String, Person> masterMap = (readNamesMap("Master.csv"));
		// read slave list
		Map<String, Person> slaveMap = (readNamesMap("Slave.csv"));
		System.out.println("Master map size is: " + masterMap.size());
		System.out.println("Slave map size is: " + slaveMap.size());

		// create ArrayList to hold changes further in the function
		ArrayList<Person> changesToBeMadeList = new ArrayList<Person>();

		// create iterator for master
		Iterator<String> masterIterator = masterMap.keySet().iterator();

		// check if any of the keys from masterMap are not in slaveMap

		while (masterIterator.hasNext()) {
			// hold next value for comparison
			String masterKeyChecker = masterIterator.next();
			// check if slaveMap contains first value from master
			if (slaveMap.containsKey(masterKeyChecker) != true) {
				// create copied person to add to list
				Person copyOfMissingPerson = new Person(masterMap.get(masterKeyChecker).getFirstName(),
						masterMap.get(masterKeyChecker).getLastName(), masterMap.get(masterKeyChecker).getEmail());

				// add +Slave to end of email address
				copyOfMissingPerson.setEmail(copyOfMissingPerson.getEmail() + "+Slave");
				// add copied person to arrayList
				changesToBeMadeList.add(copyOfMissingPerson);
			}
		}

		// create new copy set for keys of slaveList to remove
		Set<String> copyOfKeysForRemoval = new HashSet<String>(slaveMap.keySet());

		// keep keys that are present in slave and not master map
		copyOfKeysForRemoval.removeAll(masterMap.keySet());

		// Q7.2: I am creating a copy of the keys from the SlaveMap in order to have a
		// set of values to modify and change, without changing our original Map. I am
		// doing this to preserve the original data, and also to not "pull the rug out"
		// of the Map for further comparisons. If I did not have a copy, I would be
		// removing all of the values I intend to keep in my original Maps.
		for (String removesToTrack : copyOfKeysForRemoval) {
			// create copy person object
			Person personToRemove = new Person(slaveMap.get(removesToTrack).getFirstName(),
					slaveMap.get(removesToTrack).getLastName(), slaveMap.get(removesToTrack).getEmail());
			personToRemove.setEmail(personToRemove.getEmail() + "-Slave");
			// add new person
			changesToBeMadeList.add(personToRemove);
		}
		// print each person in the "to be added" list
		for (Person people : changesToBeMadeList) {
			System.out.println(people);
		}
		// Q7.6 One advantage of using set operation is that it saves a bit of code, and
		// a disadvantage is that your code may become unclear as to what information is
		// being passed, especially if unforeseen mistakes are presented in the data
		testPartB(masterMap, slaveMap, changesToBeMadeList);
	}

	// create readNamesMap

	/**
	 * This method reads the contents of the passed in fileName parameter and
	 * created Person objects of the people read in. They are written to a Map
	 * linking the Person to their email address.
	 * 
	 * @param fileName - file to be read from
	 * @return a map of <String, Person> containing the data from the read file
	 * @throws FileNotFoundException
	 */
	public static Map<String, Person> readNamesMap(String fileName) throws FileNotFoundException {
		// create Map to store values in for returning
		// Q2: We use a HashMap because there is no need to have the Map sorted.
		// As HashMaps have faster search speeds, but are unsorted, we will use
		// a HashMap.
		Map<String, Person> personFromFileMap = new HashMap<String, Person>();
		// Create new file using fileName parameter
		File newFile = new File(fileName);

		// Open scanner on file "members.csv"
		Scanner sc = new Scanner(newFile);

		// Skip over first line
		sc.nextLine();

		// Loop through lines of the file
		while (sc.hasNextLine()) {

			// Get the next line
			String line = sc.nextLine();
			Scanner scLine = new Scanner(line);

			// This allows us to use comma as the delimiter instead of
			// whitespace
			scLine.useDelimiter(",");

			// Scan the line for the names & emails
			String first = scLine.next();
			String last = scLine.next();
			String email = scLine.next();

			// Put the data read into a temporary Person object
			Person personFromFile = new Person(first, last, email);
			
			// Put the personFromFile into the HashMap to populate
			personFromFileMap.put(email, personFromFile);
		}

		// return defined arrayList, populated with people from file
		return personFromFileMap;
	}

	/**
	 * Takes user input of filename to add into ArrayList of Person type
	 * 
	 * @return ArrayList of Person class
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Person> readNames(String fileName) throws FileNotFoundException {

		// Create new file using fileName parameter
		File newFile = new File(fileName);

		// Open scanner on file "members.csv"
		Scanner sc = new Scanner(newFile);

		// Create Arraylist of Person peopleFromFile
		ArrayList<Person> peopleFromFile = new ArrayList<Person>();

		// Discard first line - it's the headers "first_name last_name email"
		// etc
		sc.nextLine();

		// Loop through lines of the file
		while (sc.hasNextLine()) {

			// Get the next line
			String line = sc.nextLine();
			Scanner scLine = new Scanner(line);

			// This allows us to use comma as the delimiter instead of
			// whitespace
			scLine.useDelimiter(",");

			// Scan the line for the names & emails
			String first = scLine.next();
			String last = scLine.next();
			String email = scLine.next();

			// Put the personFromFile into the ArrayList to populate
			Person personFromFile = new Person(first, last, email);
			peopleFromFile.add(personFromFile);
		}

		// return defined arrayList, populated with people from file
		return peopleFromFile;
	}

	/**
	 * Testing code - do not modify Tests functionality of Part B()
	 * 
	 * @param membership
	 */
	public static void testPartA(ArrayList<Person> membership) {
		// Test for correct total # of names
		if (membership.size() != 445) {
			System.out.println("Wrong number of names.  There should be 445 after all removals and adds");
			return;
		}

		// Test all 10 names removed
		for (Person p : membership) {
			if (p.getEmail().contains("-")) {
				System.out.println(String.format("Oops - didn't remove person %s %s %s", p.getFirstName(),
						p.getLastName(), p.getEmail()));
				return;
			}
		}

		// Test that only 5 names added
		int count = 0;
		for (Person p : membership) {
			if (p.getEmail().contains("*"))
				count++;
		}
		if (count != 5) {
			System.out.println("didn't add the right number of names");
			return;
		}

		// Check sorting & overall work at specific random items
		int[] memberIndexes = { 0, 10, 20, 30, 40, 400 };
		String[] expectedEmails = { "Bill_frey@frey.com*", "alaine_bergesen@cox.net", "amber_monarrez@monarrez.org",
				"apinilla@cox.net", "barrett.toyama@toyama.org", "tasia_andreason@yahoo.com" };
		for (int i = 0; i < memberIndexes.length; i++) {
			String memberEmail = membership.get(memberIndexes[i]).getEmail();
			if (!memberEmail.equals(expectedEmails[i]))
				System.out.println(String.format("Index %d expected %s but found %s", memberIndexes[i],
						expectedEmails[i], memberEmail));
		}

		System.out.println("Congrats - you passed all tests");
	}

	/**
	 * Testing code - do not modify Tests functionality of Part B()
	 * 
	 * @param masterMap   - map of Master names
	 * @param slaveMap    - map of Slave names
	 * @param changesList - ArrayList of changes.
	 */
	public static void testPartB(Map<String, Person> masterMap, Map<String, Person> slaveMap,
			List<Person> changesList) {
		Boolean passed = true;

		// Did Master map email get modified?
		if (masterMap.size() != 100
				|| !masterMap.get("+E*louisa@cronauer.com").getEmail().equalsIgnoreCase("+E*louisa@cronauer.com")) {
			passed = false;
			System.out.println("Oops - did you forgot to copy the Person before appending to the email name?");
		}

		// Did slaveMap get modified?
		if (slaveMap.size() != 101) {
			passed = false;
			System.out.println("Oops - did you forgot to copy the Map before performing set Operations?");
		}

		String[] expectedEmails = { "+E*louisa@cronauer.com+Slave", "+E*alease@buemi.com+Slave",
				"-E*oretha_menter@yahoo.com-Slave", "-E*tsmith@aol.com-Slave", "-E*kris@gmail.com-Slave", };

		// Check total # of changes
		if (changesList.size() != expectedEmails.length) {
			passed = false;
			System.out.println(String.format("Expected %d changes, received %d changes", expectedEmails.length,
					changesList.size()));
		}

		// Check for each change
		for (String sExpectedEmail : expectedEmails) {
			Boolean found = false;
			Iterator<Person> iter = changesList.iterator();
			while (iter.hasNext()) {
				Person p = iter.next();
				if (p.getEmail().equals(sExpectedEmail)) {
					found = true;
					// remove from list so we can see what's leftover
					// accidentally
					iter.remove();
				}
			} // while iter
				// Output any missing changes
			if (!found) {
				passed = false;
				System.out.println(String.format("Expected to find email: %s", sExpectedEmail));
			}
		} // for-each

		// print any extras
		for (Person pChange : changesList) {
			System.out.printf("Not expected but found change %s\n", pChange.getEmail());
		}

		if (passed)
			System.out.println("Congratulations - passed Part B");
		else
			System.out.println("Oops - failed Part B");
	}
}

/**
 * Represents a person - first, last, email
 *
 */
class Person implements Comparable<Person> {
	private String firstName;
	private String lastName;
	private String email;

	/**
	 * Constructor for Person class
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public Person(String firstName, String lastName, String email) {
		super();
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Print out the name
	 */
	@Override
	public String toString() {
		return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + "]";
	}

	/**
	 * Tests email field of object to another object to test if they're the same
	 * 
	 * @param toLookForRemoval
	 * @return int 0 if true, -1 if false
	 */
	@Override
	public int compareTo(Person toLookForRemoval) {
		return getEmail().compareTo(toLookForRemoval.getEmail());
	}

}
