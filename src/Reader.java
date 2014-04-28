import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Reader {
	public int[][] readData(String filename) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		int n = Integer.parseInt(reader.readLine()) ;
		int matrix[][] = new int [n][];
		String str = "";
		int i = 0;
	        while ((str = reader.readLine()) != null)
	        {
	        	String arr[] = str.split(" ");
	        	matrix[i] = new int[arr.length];
	        	for (int j = 0; j<arr.length; j++) {
	        		matrix[i][j] = Integer.parseInt(arr[j]);
	        	}
	        	i++;
	        }
	    return matrix;
	}
}
