package hadoop;

//This class holds the (i,j) co-ordinates and values of the non-zero elements

public class SparseList 

{

   private int row;

   private int column;

   private float value;



   public void setRow (int row) 

   {  this.row = row;

   }



   public void setColumn (int column) 

   {	this.column = column;

   }



   public void setValue (float value) 

   {  this.value = value;

   }

   

   public int getRow ()

   {	return row;

	}



	public int getColumn ()

	{	return column;

	}



	public float getValue ()

	{	return value;

	}

}
