package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart_Login_Data.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		//Below is the Logic
		String logindata[][]=new String[totalrows][totalcols]; //created for to diamentional array which can store
		
		for(int i=1; i<=totalrows; i++) //1  //read the data from x1 storing in two diamentional array
		{
			for(int j=0; j<totalcols; j++) //0       i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j); //1,0
			}
		}
		return logindata; //returning two diamentional array
	}	
	
	//DataProvider 2
			//--If we want we can create more number of DataProvider Methods here in future.
	//DataProvider 3
	
	//DataProvider 4
}
