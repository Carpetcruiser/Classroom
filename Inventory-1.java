import java.util.*;
import java.io.*;

/**
 * @author BrennanAyers
 *
 */
public class Inventory {
	private static ArmourPiece[] inventory = new ArmourPiece[20];

	public static void main(String[] args) throws FileNotFoundException {
		ArmourPiece[] inventory = readFile();
		printInventory(inventory);
		filterInventory();
	}

	/**
	 * Fills inventory[] array with read values from input file
	 *
	 * @return ArmourPiece[] inventory with file values
	 * @throws FileNotFoundException for non existent file
	 */
	public static ArmourPiece[] readFile() throws FileNotFoundException {
		Scanner readScan = new Scanner(new File("gearinventory.txt"));
		readScan.nextLine();
		while (readScan.hasNextLine()) {
			for (int rowScan = 0; rowScan < inventory.length; rowScan++) {
				if (readScan.hasNext()) {
					inventory[rowScan] = new ArmourPiece(readScan.next(),
							readScan.next(), readScan.nextInt(),
							readScan.next());
				}
			}
		}
		return inventory;
	}

	/**
	 * prints the values in array using toString() method
	 * 
	 * @param inventory array
	 */
	public static void printInventory(ArmourPiece[] inventory) {
		int counter = 0;
		// call arrayMax to set counter amount for printing
		counter = arrayMax(inventory, counter);
		for (int i = 0; i < counter; i++) {
			System.out.println(inventory[i].toString());
		}
		System.out.println();

		// call printSets to print the armourSets values
		printSets(inventory, counter);
	}

	/**
	 * uses for loop/counter to identify highest assigned index inside
	 * inventory[]
	 * 
	 * @param inventory array
	 * @param counter   from printInventory()
	 * @return int of highest assigned value index
	 */
	public static int arrayMax(ArmourPiece[] inventory, int counter) {
		// loop through inventory array, counter increase if the index has
		// filled value
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				break;
			} else {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Uses for loop to print .getGearSet() values for each object in array
	 * 
	 * @param inventory array
	 * @param counter   int to determine when to stop printing
	 */
	public static void printSets(ArmourPiece[] inventory, int counter) {
		System.out.print("GearSets are: " + inventory[0].getGearSet());
		for (int i = 1; i < counter; i++) {
			System.out.print(", " + inventory[i].getGearSet());
		}
		System.out.println("\n");
	}

	/**
	 * runs subroutines fullPrompt and userFieldPrompt to filter through
	 * inventory
	 */
	public static void filterInventory() {
		// create scanner fieldScan
		Scanner fieldScan = new Scanner(System.in);
		// gets prompt for field to sort by
		String filterField = userFieldPrompt(fieldScan);
		// uses while loop to get input for field and value sorts
		while ((filterField.contains("done") != true)) {
			filterField = fullPrompt(fieldScan, filterField);
			if (filterField.contains("Value is not valid!")) {
				break;
			}
		}
	}

	// asks user for field prompt and returns scanner input
	/**
	 * @param fieldScan passes scanner for user prompt
	 * @return String userField containing user input
	 */
	public static String userFieldPrompt(Scanner fieldScan) {
		// Takes field input with scanner, returns value
		System.out.print("Which field to filter on? ");
		String userField = fieldScan.next();
		return userField;
	}

	/**
	 * Takes user input for field and calls setCompareObject to create object
	 * for sorting
	 * 
	 * @param fieldScan   passes scanner for user prompt
	 * @param filterField is the standardized user input
	 * @return newFieldInput for next loop
	 */
	public static String fullPrompt(Scanner fieldScan, String filterField) {
		// checks for "done" in user input to initialize ending the program
		if (filterField == "done") {
			return null;
		} else {
			// uses findField to translate user inputs to standard form
			String finalFieldValue = findField(inventory, filterField);
			// gets user input for value to match
			System.out.print("Enter the value to match: ");
			String userValue = fieldScan.next();
			// uses setCompareObject to create loop specific object for
			// comparison
			ArmourPiece compareObject = setCompareObject(inventory,
					finalFieldValue, userValue);
			if (compareObject == null) {
				return "Value is not valid!";
			}
			// uses compareLists to find and print matching objects
			compareLists(inventory, compareObject, finalFieldValue);
			// initializes field variable for next loop
			String newFieldInput = userFieldPrompt(fieldScan);
			return newFieldInput;
		}
	}

	/**
	 * takes filterField from fullPrompt, returns standardized string
	 * 
	 * @param inventory   array
	 * @param filterField user input for field
	 * @return standardized translation of user input
	 * @throws IllegalArgumentException for improper input
	 */
	public static String findField(ArmourPiece[] inventory, String filterField)
			throws IllegalArgumentException {
		if (filterField.toLowerCase().charAt(0) == 'g') {
			return "gear";
		}
		if (filterField.toLowerCase().charAt(0) == 't') {
			return "type";
		}
		if (filterField.toLowerCase().charAt(0) == 'b') {
			return "bonus";
		}
		if (filterField.toLowerCase().charAt(0) == 'c') {
			return "combat";
		} else {
			return "ERROR";
		}
	}

	/**
	 * Takes the converted user input value and compareObject to find matching
	 * values in the inventory array
	 * 
	 * @param inventory       array
	 * @param compareObject   to compare to inventory objects
	 * @param finalFieldValue sorts by user field
	 */
	public static void compareLists(ArmourPiece[] inventory,
			ArmourPiece compareObject, String finalFieldValue) {
		// uses arrayMax to find the last object in inventory[]
		int counter = 0;
		counter = arrayMax(inventory, counter);
		// if the field to be searched has matching value with
		// object in inventory[], print that object with toString()
		for (int i = 0; i < counter; i++) {
			if (finalFieldValue == "gear") {
				if (inventory[i].getGearSet()
						.contains(compareObject.getGearSet()) == true) {
					System.out.println(inventory[i].toString());
				}
			}
			if (finalFieldValue == "type") {
				if (inventory[i].getType()
						.contains(compareObject.getType()) == true) {
					System.out.println(inventory[i].toString());
				}
			}
			if (finalFieldValue == "bonus") {
				if (inventory[i].getBonusStat() == compareObject
						.getBonusStat()) {
					System.out.println(inventory[i].toString());
				}
			}
			if (finalFieldValue == "combat") {
				if (inventory[i].getCombatBoost()
						.contains(compareObject.getCombatBoost())) {
					System.out.println(inventory[i].toString());
				}
			}
		}
	}

	/**
	 * creates a temporary object based on user input to compare with
	 * inventory[] objects to find match
	 * 
	 * @param inventory   array
	 * @param filterField user input for field
	 * @param userValue   user input for value
	 * @return ArmourPiece comparePiece to compare with inventory[] objects
	 */
	public static ArmourPiece setCompareObject(ArmourPiece[] inventory,
			String filterField, String userValue) {
		String toSet = findField(inventory, filterField);
		String convertedUserValue = toStandard(userValue);
		// test if translated user input for field is one of 4 possible,
		// then write inputed value to that field in object
		if (toSet.contains("gear")) {
			ArmourPiece comparePiece = new ArmourPiece(convertedUserValue, null,
					0, null);
			return comparePiece;
		}
		if (toSet.contains("type")) {
			ArmourPiece comparePiece = new ArmourPiece(null, convertedUserValue,
					0, null);
			return comparePiece;
		}
		// Uses scanner to test whether input for this field specifically
		// is valid, as it must be an int, else return null and end input
		if (toSet.contains("bonus")) {
			Scanner tempValueScanner = new Scanner(userValue);
			if (tempValueScanner.hasNextInt() == true) {
				ArmourPiece comparePiece = new ArmourPiece(null, null,
						Integer.parseInt(userValue), null);
				// close local scanner before proceeding
				tempValueScanner.close();
				return comparePiece;
			} else {
				ArmourPiece comparePiece = null;
				return comparePiece;
			}
		}
		if (toSet.contains("combat")) {
			ArmourPiece comparePiece = new ArmourPiece(null, null, 0,
					convertedUserValue);
			return comparePiece;
		} else {
			ArmourPiece comparePiece = null;
			return comparePiece;
		}
	}

	/**
	 * converts the user input for value into a standard form for insertion into
	 * ArmourClass object
	 * 
	 * @param userValue user input for value field to be converted
	 * @return convertedValue user input after standardizing
	 */
	public static String toStandard(String userValue) {
		char toConvert = userValue.toUpperCase().charAt(0);
		String convertedValue = Character.toString(toConvert);
		for (int i = 1; i < userValue.length(); i++) {
			convertedValue += userValue.toLowerCase().charAt(i);
		}
		return convertedValue;
	}
}

class ArmourPiece {
	private String gearSet;
	private String type;
	private int bonusStat;
	private String combatBoost;

	/**
	 * Constructor for ArmourPiece object class
	 * 
	 * @param gearSet
	 * @param type
	 * @param bonusStat
	 * @param combatBoost
	 */
	public ArmourPiece(String gearSet, String type, int bonusStat,
			String combatBoost) {
		setGearSet(gearSet);
		setType(type);
		setBonusStat(bonusStat);
		setCombatBoost(combatBoost);
	}

	/**
	 * sets gearSet
	 * 
	 * @param gearSet
	 */
	public void setGearSet(String gearSet) {
		this.gearSet = gearSet;
	}

	/**
	 * gets this.gearSet
	 * 
	 * @return this.getSet
	 */
	public String getGearSet() {
		return this.gearSet;
	}

	/**
	 * sets this.type
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * gets this.type
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * sets this.bonusStat
	 * 
	 * @param bonusStat
	 */
	public void setBonusStat(int bonusStat) {
		this.bonusStat = bonusStat;
	}

	/**
	 * gets this.bonusStat
	 * 
	 * @return
	 */
	public int getBonusStat() {
		return this.bonusStat;
	}

	/**
	 * sets this.combatBoost
	 * 
	 * @param combatBoost
	 */
	public void setCombatBoost(String combatBoost) {
		this.combatBoost = combatBoost;
	}

	/**
	 * gets this.combatBoost
	 * 
	 * @return
	 */
	public String getCombatBoost() {
		return this.combatBoost;
	}

	/**
	 * @return formatted Print string for object using getters/setters
	 */
	public String toString() {
		return ("ArmourPiece [GearSet=" + this.getGearSet() + ", Type="
				+ this.getType() + ", BonusStat=" + this.getBonusStat()
				+ ", CombatBoost=" + this.getCombatBoost() + "]");
	}
}
