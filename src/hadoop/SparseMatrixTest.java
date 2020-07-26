package hadoop;

import java.io.*;

/***********************************************
 * This is the test for the Matrix class.
 ***********************************************/

public class SparseMatrixTest

{

	public static void main(String[] args)

	{

		// create SparseMatrix A

		System.out.print("Input the command ");

		System.out.println("(\"i\" for inputting from keyboard or another key for default):");

		BufferedReader In = new BufferedReader(new InputStreamReader(System.in));

		String inputText;

		try

		{

			inputText = In.readLine();

		} catch (IOException IOE)

		{

			System.out.println(IOE.toString());

			return;

		}

		SparseList[] a;

		SparseMatrix A;

		if (inputText.equals("i"))

		{

			int row, column, num;

			try

			{

				System.out.print("Input the rows of the SparseMatrix A: ");

				row = Integer.parseInt(In.readLine());

				System.out.print("Input the columns of the SparseMatrix A: ");

				column = Integer.parseInt(In.readLine());

				System.out.print("Input the number of entrys in SparseMatrix A: ");

				num = Integer.parseInt(In.readLine());

			} catch (IOException IOE)

			{

				System.out.println(IOE.toString());

				System.out.println("Unable to get the integer data.");

				return;

			}

			A = new SparseMatrix(row, column);

			a = new SparseList[num];

			for (int i = 0; i < num; i++)

			{

				System.out.println("The entry " + i + ":");

				a[i] = new SparseList();

				int row_num, column_num;

				try

				{

					System.out.print("Input the row:");

					row_num = Integer.parseInt(In.readLine());

					System.out.print("Input the column:");

					column_num = Integer.parseInt(In.readLine());

				} catch (IOException IOE)

				{

					System.out.println(IOE.toString());

					System.out.println("Unable to get the integer data.");

					return;

				}

				a[i].setRow(row_num);

				a[i].setColumn(column_num);

				System.out.print("Input the value:");

				int value;

				try

				{

					value = Integer.parseInt(In.readLine());

				} catch (IOException IOE)

				{

					System.out.println(IOE.toString());

					System.out.println("Unable to get the double data.");

					return;

				}

				a[i].setValue(value);

			}

		} else

		{

			a = new SparseList[3];

			a[0] = new SparseList();

			a[0].setRow(4);

			a[0].setColumn(6);

			a[0].setValue(4.0f);

			a[1] = new SparseList();

			a[1].setRow(5);

			a[1].setColumn(3);

			a[1].setValue(6.0f);

			a[2] = new SparseList();

			a[2].setRow(9);

			a[2].setColumn(5);

			a[2].setValue(7.0f);

			A = new SparseMatrix(10, 15);

		}

		A.setMatrix(a);

		System.out.println("Matrix A:");

		A.displayMatrix();

		System.out.println("k * Matrix A:");

		A.scale(5.2f).displayMatrix();

		System.out.print("norm |A| = ");

		System.out.println(A.norm() + "\n");

		// create SparseMatrix B

		System.out.print("Input the command ");

		System.out.println("(\"i\" for inputting from keyboard or another key for default):");

		try

		{

			inputText = In.readLine();

		} catch (IOException IOE)

		{

			System.out.println(IOE.toString());

			return;

		}

		SparseList[] b;

		SparseMatrix B;

		if (inputText.equals("i"))

		{

			int row, column, num;

			try

			{

				System.out.print("Input the rows of the SparseMatrix B: ");

				row = Integer.parseInt(In.readLine());

				System.out.print("Input the columns of the SparseMatrix B: ");

				column = Integer.parseInt(In.readLine());

				System.out.print("Input the number of entrys in SparseMatrix B: ");

				num = Integer.parseInt(In.readLine());

			} catch (IOException IOE)

			{

				System.out.println(IOE.toString());

				System.out.println("Unable to get the integer data.");

				return;

			}

			B = new SparseMatrix(row, column);

			b = new SparseList[num];

			for (int i = 0; i < num; i++)

			{

				System.out.println("The entry " + i + ":");

				b[i] = new SparseList();

				int row_num, column_num;

				try

				{

					System.out.print("Input the row:");

					row_num = Integer.parseInt(In.readLine());

					System.out.print("Input the column:");

					column_num = Integer.parseInt(In.readLine());

				} catch (IOException IOE)

				{

					System.out.println(IOE.toString());

					System.out.println("Unable to get the integer data.");

					return;

				}

				b[i].setRow(row_num);

				b[i].setColumn(column_num);

				System.out.print("Input the value:");

				int value;

				try

				{

					value = Integer.parseInt(In.readLine());

				} catch (IOException IOE)

				{

					System.out.println(IOE.toString());

					System.out.println("Unable to get the double data.");

					return;

				}

				b[i].setValue(value);

			}

		} else

		{

			b = new SparseList[4];

			b[0] = new SparseList();

			b[0].setRow(3);

			b[0].setColumn(7);

			b[0].setValue(14.45f);

			b[1] = new SparseList();

			b[1].setRow(5);

			b[1].setColumn(3);

			b[1].setValue(76.23f);

			b[2] = new SparseList();

			b[2].setRow(3);

			b[2].setColumn(5);

			b[2].setValue(5.0f);

			b[3] = new SparseList();

			b[3].setRow(6);

			b[3].setColumn(5);

			b[3].setValue(0.34f);

			B = new SparseMatrix(10, 15);

		}

		B.setMatrix(b);

		System.out.println("Matrix B:");

		B.displayMatrix();

		System.out.print("norm |B| = ");

		System.out.println(B.norm() + "\n");

		System.out.println("Matrix (A + B):");

		if (A.add(B) != null)

			A.add(B).displayMatrix();

		else

			System.out.println("null\n");

	}

}
