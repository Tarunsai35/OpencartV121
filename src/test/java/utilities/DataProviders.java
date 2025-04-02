
package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")//LoginData
	public String[][] getData() throws IOException {

		String path = "C:\\Users\\Dinesh\\eclipse-workspace\\opencartV121\\testData\\LoginData.xlsx";

		ExcelUtility xlutil = new ExcelUtility(path);

		int totalrow = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalrow][totalcols];
		for (int i = 1; i <= totalrow; i++) {
			for (int j = 0; j < totalcols; j++) {
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
}
