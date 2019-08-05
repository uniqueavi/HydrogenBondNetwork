
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import javax.swing.JOptionPane;


public class ImportFile {

	public static final String delimiter = ",";
	public static ArrayList<String> data = new ArrayList<String>();
	public static ArrayList<String> col1 = new ArrayList<String>();
	public static ArrayList<String> col2 = new ArrayList<String>();
	public static ArrayList<String> matcol1 = new ArrayList<String>();
	public static ArrayList<String> matcol2 = new ArrayList<String>();
	public static ArrayList<String> merge = new ArrayList<String>();
	public static ArrayList<String> merge1 = new ArrayList<String>();
	public static ArrayList<Integer> matval = new ArrayList<Integer>();
	public static ArrayList<Integer> tempval = new ArrayList<Integer>();
	static String csvFile;
	static PrintStream myconsole;
	
	
	
	public static void read(String csvFile) {
		
		//Reading from file
		try {
			File file = new File(csvFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			

			String[] tempArr;
			while ((line = br.readLine()) != null) 
			{
				tempArr = line.split(delimiter);
				
				for (String tempStr : tempArr) 
						data.add(tempStr);
				
			}
			//System.out.println(data);
			br.close();
			//split the data into two arraylist
			for(int i=0; i<data.size();i+=2)
			{
				col1.add(data.get(i).substring(0, 3).trim()); 
			}
			for(int i=1; i<data.size();i+=2)
			{
				col2.add(data.get(i).substring(0, 4).trim()); 
			}
			
			//Trimming data
			LinkedHashSet<String> lhs = new LinkedHashSet<String>();
			lhs.addAll(col1);
			matcol1.clear();
			matcol1.addAll(lhs);
			matcol1.add("PRO");
			Collections.sort(matcol1);
			
			
			lhs.clear();
			matcol2.clear();
			lhs.addAll(col2);
			matcol2.addAll(lhs);
			Collections.sort(matcol2);
//			System.out.println(matcol2);
//			System.out.println(matcol1);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	public static void matOp() {
//--------------------MATRIX OPERATIONS----------------------------
		
        //merging col1 and col2
        
        for(int i=0;i<col1.size();i++)
        {
        	merge.add(col1.get(i).concat(col2.get(i)));
        }
        //merging matcol1 matcol2
        for(int i=0;i<matcol1.size();i++)
        {
        	for(int j=0;j<matcol1.size();j++)
        	merge1.add(matcol1.get(i).concat(matcol2.get(j)));
        }
        
 
       
        //Getting the no of appearances of the data
        for(int i=0;i<merge1.size();i++)
        {
        	int count=0;
        	for(int j=0;j<merge.size();j++)
        	{
        		if(merge1.get(i).equals(merge.get(j)))
        		{
        			count++;
        			//System.out.print(count);
        		}
        	}
        	matval.add(count);
        }
        
	}
	
	public static void display() throws IOException{
		 //--------DISPLAY------------
       
		myconsole = new PrintStream(new File("C:\\\\Users\\\\62772C1\\\\Desktop\\\\excel\\\\resultdata.xls"));
		System.setOut(myconsole);

		
		myconsole.print("\t");
        for(int i=0;i<matcol1.size();i++)
        {
        	myconsole.print(matcol1.get(i)+"\t");
        }
        myconsole.print("\n");
        int ct=0;
        for(int i=0;i<matcol1.size();i++)
        {
        	myconsole.print(matcol1.get(i)+"\t");
        	for(int j=0;j<matcol2.size();j++)
        	{
        		//dispmat[i][j]=matval.get(j);
        		myconsole.print(matval.get(ct)+"\t");
        		ct++;
        	}
        	
        	//System.out.println(tempval);
        	myconsole.println();
        }
	}
	
	public static void main(String[] args) throws IOException 
	{
		
		// csv file to read
		csvFile = JOptionPane.showInputDialog(null, "Enter the path of the csv file");
		String success = "Your Data Saved Successfully";
		String saved = "Saved";
		System.out.println("File name:- "+csvFile+"\n");
		ImportFile.read(csvFile);
		ImportFile.matOp();
		ImportFile.display();
		JOptionPane.showMessageDialog(null, success, saved, 1);

		
    }


        
		
	}

