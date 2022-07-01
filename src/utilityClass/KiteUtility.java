package utilityClass;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import net.bytebuddy.utility.RandomString;

public class KiteUtility {
	
	public static String readDataFormExcel(int row, int cell) throws EncryptedDocumentException, IOException
	{
	File MyFile=new File("D:\\Velocity\\Java Class\\5th March B Batch\\5thMarchTest.xlsx");
	Sheet mySheet = WorkbookFactory.create(MyFile).getSheet("Sheet3");
	String myValue = mySheet.getRow(row).getCell(cell).getStringCellValue();
	return myValue;
	}
	
	public static void takeScreenshot(WebDriver driver) throws IOException
	{
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String random=RandomString.make(3);
	File dest= new File("D:\\SREENSHOTS Selenium"+random+".png");
    org.openqa.selenium.io.FileHandler.copy(src, dest);
    
	}
}


