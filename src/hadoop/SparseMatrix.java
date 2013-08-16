package hadoop;

public class SparseMatrix {

	// This class provides the list node for the linked list. with

	// references to the next non-zero entry in the same row and

	// the next non-zero entry in the same column.

	class Entry {

		private int row;

		private int column;

		private int value;

		private Entry right;

		private Entry down;

	}

	private Entry[] rowHead;

	private Entry[] columnHead;

	/*************************************************************
	 * Create an m-by-n SparseMatrix with storing it in the linked
	 * 
	 * list.
	 * 
	 * @param m
	 *            the number of rows in this Matrix.
	 * @param n
	 *            the number of columns in this Matrix.
	 **************************************************************/

	public SparseMatrix(int m, int n) // constructor

	{

		if (m <= 0)

		{

			System.out.println("The rows of the matrix must be greater than zero.");

			System.exit(1);

		}

		if (n <= 0)

		{

			System.out.println("The columns of the matrix must be greater than zero.");

			System.exit(1);

		}

		rowHead = new Entry[m];

		columnHead = new Entry[n];

	}

	// Return the row dimension of M.

	// post: returns the number of rows

	public int numRows()

	{
		return 0; // bug

		// return row;

	}

	// Return the column dimension of M.

	// post: returns the number of columns

	public int numCols()

	{
		return 0; // bug
		// return column;

	}

	// Set entry M(i,j) to a.

	// pre: 1<=i<=numRows(), 1<=j<=numCols()

	// post: M(i,j)==a

	public void setEntry(int i, int j, int a)

	{

		// ...

	}

	// Return entry M(i,j).

	// pre: 1<=i<=numRows(), 1<=j<=numCols()

	// post: returns M(i,j)

	public void getEntry(int i, int j, int a)

	{

		// ...

	}

	// ...isZero();copy();transpose();...

	/*************************************************************
	 * Add the passed SparseMatrix to this SparseMatrix.
	 * 
	 * @param B
	 *            the SparseMatrix to add to this SparseMatrix.
	 * @return a reference to a new SparseMatrix object that is equal
	 * 
	 *         to the sum of this SparseMatrix and the passed one.
	 * 
	 *         If the passed SparseMatrix is null or the number of rows and
	 * 
	 *         columns of this SparseMatrix is not equal to the passed one, null
	 *         will be returned.
	 **************************************************************/

	public SparseMatrix add(SparseMatrix B)

	{

		SparseMatrix C = null;

		if (B != null && rowHead.length == B.rowHead.length && columnHead.length == B.columnHead.length)

		{

			C = new SparseMatrix(rowHead.length, columnHead.length);

			SparseList[] scaleOne = new SparseList[rowHead.length * columnHead.length];

			Entry aNext, bNext, rowNext, newNode;

			for (int i = 0; i < rowHead.length; i++)

			{

				if (rowHead[i] != null || B.rowHead[i] != null)

				{

					aNext = rowHead[i]; // trace Matrix this' row linked list

					bNext = B.rowHead[i]; // trace Matrix B's row linked list

					newNode = new Entry(); // obtain node for new value

					C.rowHead[i] = newNode;

					do

					{

						if (aNext == null)

						{

							rowNext = bNext;

							bNext = bNext.right;

						} else if (bNext == null)

						{

							rowNext = aNext;

							aNext = aNext.right;

						} else

						{

							if (bNext.column < aNext.column)

							{

								rowNext = bNext;

								bNext = bNext.right;

							} else if (bNext.column > aNext.column)

							{

								rowNext = aNext;

								aNext = aNext.right;

							} else if (Math.abs(aNext.value + bNext.value) > 0.0001)

							{

								rowNext = new Entry();

								rowNext.row = aNext.row;

								rowNext.column = aNext.column;

								rowNext.value = aNext.value + bNext.value;

								aNext = aNext.right;

								bNext = bNext.right;

							} else // at the same position && sum of the values
									// = 0

							{

								aNext = aNext.right;

								bNext = bNext.right;

								continue;

							}

						}

						newNode.row = rowNext.row;

						newNode.column = rowNext.column;

						newNode.value = rowNext.value;

						C.insertColumn(newNode);

						rowNext = rowNext.right;

						if (aNext != null || bNext != null)

						{

							newNode.right = new Entry();

							newNode = newNode.right;

						} else // the last node

						{
							newNode.right = null;

							newNode.down = null;

						}

					} while (aNext != null || bNext != null);

				}

			}

		}

		return C;

	}

	/*************************************************************
	 * Multiply the passed number to all entrys in
	 * 
	 * this SparseMatrix.
	 * 
	 * @param k
	 *            the number to multiply this SparseMatrix
	 * 
	 *            by.
	 * @return a reference to a new Matrix object that is equal to
	 * 
	 *         the product of this SpaseMatrix and the passed
	 * 
	 *         number.
	 **************************************************************/

	public SparseMatrix scale(float k)

	{

		SparseMatrix C = new SparseMatrix(rowHead.length, columnHead.length);

		Entry newNode, rowNext;

		if (k != 0.) // if k is equal to zero, no new node is added

		{

			for (int i = 0; i < rowHead.length; i++)

			{

				if (rowHead[i] != null)

				{

					rowNext = rowHead[i]; // trace this row linked list

					newNode = new Entry(); // obtain node for new value

					C.rowHead[i] = newNode;

					do

					{

						int rowNo = rowNext.row;

						int columnNo = rowNext.column;

						newNode.row = rowNo;

						newNode.column = columnNo;

						newNode.value = (int) k * rowNext.value;

						C.insertColumn(newNode);

						rowNext = rowNext.right;

						if (rowNext != null)

						{

							newNode.right = new Entry();

							newNode = newNode.right;

						} else // the last node

						{
							newNode.right = null;

							newNode.down = null;

						}

					} while (rowNext != null);

				}

			}

		}

		return C;

	}

	/*************************************************************
	 * Return the maximum absolute row sum of this
	 * 
	 * SparseMatrix.
	 * 
	 * @return the infinity norm of this SparseMatrix.
	 **************************************************************/

	public float norm()

	{

		double max = 0.;

		double sum;

		Entry newNode;

		for (int i = 0; i < rowHead.length; i++)

		{

			sum = 0.;

			// add the value in the same row list

			for (newNode = rowHead[i]; newNode != null; newNode = newNode.right)

			{

				sum += Math.abs(newNode.value);

			}

			if (sum > max)

				max = sum;

		}

		return (float) max;

	}

	/*************************************************************
	 * Initialize this SparseMatrix with the values
	 * 
	 * from the passed array.
	 * 
	 * @param a
	 *            the array to be initialized this SparseMatrix.
	 **************************************************************/

	public void setMatrix(SparseList[] a)

	{

		for (int i = 0; i < rowHead.length; i++)

			rowHead[i] = null;

		for (int j = 0; j < columnHead.length; j++)

			columnHead[j] = null;

		Entry newNode, rowPrevious, rowNext, columnPrevious, columnNext;

		for (int i = 0; i < a.length; i++)

		{

			// test if this Entry is in this matrix and the entry's value is not
			// equal to zero

			if (a[i].getRow() < rowHead.length && a[i].getColumn() < columnHead.length && a[i].getValue() != 0)

			{

				int rowNo = a[i].getRow();

				int columnNo = a[i].getColumn();

				newNode = new Entry(); // obtain node for new value

				newNode.right = null;

				newNode.down = null;

				newNode.row = rowNo;

				newNode.column = columnNo;

				newNode.value = (int) a[i].getValue();

				// the later one will overwrite the former one if at the same
				// position

				if (rowHead[rowNo] != null && columnNo == rowHead[rowNo].column)

					rowHead[rowNo].value = (int) a[i].getValue();

				else
					// no former one at the same position

					insertRow(newNode);

				insertColumn(newNode);

			}

		}

	}

	/*************************************************************
	 * Display this SparseMatrix as a rectangular grid
	 * 
	 * of values on the screen. It prints all the entrys in the
	 * 
	 * matrix, including the implicit zeros.
	 **************************************************************/

	public void displayMatrix()

	{

		int count;

		Entry newNode;

		for (int i = 0; i < rowHead.length; i++)

		{

			count = 0; // count the number of the printed entrys

			for (newNode = rowHead[i]; newNode != null; newNode = newNode.right)

			{

				// print the 0's before and between the nodes

				for (int j = count; j < newNode.column; j++)

					System.out.print("0.000 ");

				System.out.print(newNode.value);

				for (int j = 0; j < 9 - new Integer(newNode.value).toString().length(); j++)

					System.out.print(" ");

				count = newNode.column + 1;

			}

			// print the 0's after the last node or in the empty linked list.

			for (int j = count; j < columnHead.length; j++)

				System.out.print("0.000 ");

			System.out.println();

		}

		System.out.println();

	}

	// insert to the row linked list

	private void insertRow(Entry node)

	{

		Entry rowPrevious, rowNext;

		int rowNo = node.row;

		int columnNo = node.column;

		// see if the node goes first in the list

		if (rowHead[rowNo] == null || columnNo < rowHead[rowNo].column)

		{

			node.right = rowHead[rowNo];

			rowHead[rowNo] = node;

		} else // find place to link the node

		{

			rowPrevious = rowHead[rowNo];

			rowNext = rowHead[rowNo].right;

			while (rowNext != null && columnNo > rowNext.column)

			{

				rowPrevious = rowNext;

				rowNext = rowNext.right;

			}

			// adjust links to complete insertion

			rowPrevious.right = node;

			node.right = rowNext;

		}

	}

	// insert to the column linked list

	private void insertColumn(Entry node)

	{

		Entry columnPrevious, columnNext;

		int rowNo = node.row;

		int columnNo = node.column;

		// see if the node goes first in the list

		if (columnHead[columnNo] == null || rowNo < columnHead[columnNo].row)

		{

			node.down = columnHead[columnNo];

			columnHead[columnNo] = node;

		} else // find place to link the node

		{

			columnPrevious = columnHead[columnNo];

			columnNext = columnHead[columnNo].down;

			while (columnNext != null && rowNo > columnNext.row)

			{

				columnPrevious = columnNext;

				columnNext = columnNext.down;

			}

			// adjust links to complete insertion

			columnPrevious.down = node;

			node.down = columnNext;

		}

	}

}