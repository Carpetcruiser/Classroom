import java.util.*;

/**
 * This program allows the creation and modification of a primitive spreadsheet.
 * 
 * @author BrennanAyers
 * 
 */
public class Tiny123 {
	/**
	 * Contains testing behavior for program O(1)
	 */
	public static void main(String[] args) {
		Sheet sht = new Sheet();

		System.out.println("Initial State");
		System.out.println(sht + "\n");

		sht.insertRow(0);
		System.out.println("Insert Row 0");
		System.out.println(sht + "\n");

		sht.deleteRow(8);
		System.out.println("Delete Row 8");
		System.out.println(sht + "\n");

		sht.insertCol(0);
		System.out.println("Insert Col 0");
		System.out.println(sht + "\n");

		sht.deleteCol(6);
		System.out.println("Delete Col 6");
		System.out.println(sht + "\n");

		for (int row = 1; row <= 3; row++) {
			for (int col = 1; col <= 3; col++) {
				sht.setString(row, col, "" + ((row - 1) * 3 + col));
			}
		}
		System.out.println("Fill 3x3 array");
		System.out.println(sht + "\n");

		sht.insertRow(2);
		sht.insertRow(4);
		sht.insertCol(2);
		sht.insertCol(4);
		System.out.println("Insert Row 2,4, Col 2,4");
		System.out.println(sht + "\n");

		for (int i = 10; i >= 6; i--) {
			sht.deleteRow(i);
			sht.deleteCol(i);
		}
		System.out.println("Delete Row 10-6, Col 10-6");
		System.out.println(sht + "\n");

		for (int i = 0; i <= 5; i++) {
			sht.deleteRow(0);
			sht.deleteCol(0);
		}
		System.out.println("Delete Row 0x6, Col 0x6");
		System.out.println(sht + "\n");
	}

}

/**
 * Sheet handles the creation and modification of the spreadsheet, dictating
 * insertion and deletion methods.
 *
 */
class Sheet {
	private LinkedList<SheetRow> rows;

	/**
	 * Creates the 9x9 spreadsheet using add commands 
	 * 
	 * O(N^2) lops through 2 linkedLists to create sheet 
	 */
	public Sheet() {
		// set rows LinkedList to new list
		this.rows = new LinkedList<SheetRow>();
		// for loop 9 times assigning rows to sheet
		for (int i = 1; i <= 9; i++) {
			// create 9x9 sheet
			this.rows.add(new SheetRow(i));
		}
	}

	/**
	 * This method inserts a row at the given index 
	 * O(N) - Stepping through rows
	 * of sheet twice, but not within the other
	 * 
	 * @param row - the row index wanting to be inserted
	 */
	public void insertRow(int row) {
		// insert row at row index
		this.rows.add(row, new SheetRow(row));// inserts row at 0-based index
	}

	/**
	 * This method deletes the row at the parameter passed in 
	 * O(N) - walking
	 * down rows
	 * 
	 * @param row - row index to be deleted
	 */
	public void deleteRow(int row) {
		// deletes row at 0-based index
		this.rows.remove(row);
	}

	/**
	 * This method inserts a column at the passed in index
	 * O(N^2) - Walks
	 * through each row, then through the row to the desired column
	 * 
	 * @param col - index for column to be inserted
	 */
	public void insertCol(int col) {

		Iterator<SheetRow> itr = this.rows.iterator();
		int count = 0;
		// iterate through rows
		while (itr.hasNext()) {
			// insert cell corresponding to column desired in row
			itr.next().addCell(col, "R" + count + "C" + col);
			count++;
		}
	}

	/**
	 * This method deletes the column at the given index 
	 * O(N)
	 * 
	 * @param col - index of column to be deleted
	 */
	public void deleteCol(int col) {

		Iterator<SheetRow> itr = this.rows.iterator();
		int count = 0;
		// iterate through rows
		while (itr.hasNext()) {
			// remove cell according to desired col removal
			itr.next().removeCell(col);
			count++;
		}
	}

	/**
	 * Sets the string value of given cell using row and col parameters 
	 * O(N^2) -
	 * setting the cell based on row and column
	 * 
	 * @param row - row to set cell
	 * @param col - column to set cell
	 * @param s   - value to be added at given cell
	 */
	public void setString(int row, int col, String s) {
		// sets String value
		this.rows.get(row).setString(col, s);
	}

	/**
	 * Gets the value of the given cell using row and col parameters 
	 * O(N^2) -
	 * walking down row and columns
	 * 
	 * @param row - row to find value
	 * @param col - column to find value
	 * @return int value at given row and column
	 */
	public int getValue(int row, int col) {
		// gets int value of cell at row/col
		return this.rows.get(row).getValue(col);
	}

	/**
	 * Dictates the return value of row
	 * O(n^2) - walks through rows twice inside of itself 
	 * @return result - String value of the row's cells
	 */
	public String toString() {
		String result = "";
		int holderSum = 0;
		int testSize = 0;
		// for each row in rows
		for (SheetRow row : this.rows) {
			// concat new line and the row's cell
			result += "\n" + row.toString();
			// take size of row for use in loop 
			testSize = row.size(); 

			
		}
		// add extra line skip to move below sheet
		result += "\n";
		
		for (int column = 0; column < testSize; column++) {
			// reset the sum for each loop
			holderSum = 0;
			
			// for each row in present rows 
			for (SheetRow row : this.rows) {
				// use getValue at the index of column
				holderSum += row.getValue(column);
			}
			// add half spaced formatted string to result to fit spreadsheet
			result += String.format("Sum=%-4s", holderSum);
		}
		
		// return row's values
		return result;
	}
}

/**
 * Handles the creation and modification of rows in spreadsheet
 * 
 */
class SheetRow {
	private LinkedList<String> cells;

	/**
	 * Creates the rows to be added to the spreadsheet
	 * 
	 *  O(n) - for loop with potentially variable size 
	 * 
	 * @param row - row to be created
	 */
	public SheetRow(int row) {
		// create new LinkedList
		LinkedList<String> list = new LinkedList<String>();
		// for loop, 1 - 9
		for (int i = 1; i <= 9; i++) {
			// add cell with default values
			list.add("R" + row + "C" + i);
		}
		// set cells to list
		this.cells = list;

	}

	/**
	 * Adds a cell at given index in the row, with passed in String value 
	 * 
	 * O(n) -
	 * walking down row to add
	 * 
	 * @param index - index in row to add cell
	 * @param s     - String to add to cell
	 */
	public void addCell(int index, String s) {
		// adds 0-based col to this row
		this.cells.add(index, s);
	}

	/**
	 * Removes a cell at given index in row O(n) - walking down row to remove
	 * 
	 * @param index - index of cell to be removed from row
	 */
	public void removeCell(int index) {
		// removes 0-based col from this row
		this.cells.remove(index);
	}

	/**
	 * Sets the value of given cell 
	 * O(n) - walking down row to set string
	 * 
	 * @param cell - cell to be modified
	 * @param s    - value to modify cell with
	 */
	public void setString(int cell, String s) {
		// set given cell to the string s
		this.cells.set(cell, s);
	}

	/**
	 * Returns the number of columns in the row 
	 * O(1) - iterator through row
	 * 
	 * @return int - amount of columns in row
	 */
	public int size() {
		// create iterator of cells
		Iterator<String> itr = this.cells.iterator();

		int count = 0;
		// count each time iterator has next
		while (itr.hasNext()) {
			itr.next();
			count++;
		}
		return count;
	}

	/**
	 * This method dictates the output of calling a sheetRow to print. O(N) -
	 * walking through cells
	 * 
	 * @return String of the value of the cells in the row
	 */
	public String toString() {
		String returner = "";
		int sumTotal = 0;
		// create counter for calculating sum
		// for each cell in cells object
		for (String cell : this.cells) {
			// add formatted string of cell value
			returner += String.format("%-8s", cell + "");
			// if cell contains int value
			if (!cell.startsWith("R")) {
				// add int to sum total
				sumTotal += Integer.parseInt(cell);
			}
		}
		// concat sum to end of returner
		returner += "Sum = " + sumTotal;
		return returner;
	}

	/**
	 * Gets the value of the column sum
	 * 
	 * O(n) - retrieves the col value from the list
	 * 
	 * @param col - index of column to be summed
	 * @return int - value of column's cells summed
	 */
	public int getValue(int col) {
		// create scanner based on column cell
		Scanner myScanner = new Scanner(this.cells.get(col));
		// if it's an integer
		if (myScanner.hasNextInt()) {
			// return the value to be added 
			return myScanner.nextInt();
		} else {
			// else return 0
			return 0;
		}

	}

}
