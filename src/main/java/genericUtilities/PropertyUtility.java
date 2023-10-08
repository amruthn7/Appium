package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



/**
 * This class contains reusable methods to handle property file
 * @author Amruth N
 *
 */
public class PropertyUtility {
	private Properties prop;
	
	/**
	 * This constructor is used to create object for class
	 */
	public PropertyUtility() {
	}
	
	/**
	 * This constructor is used to initialize the property file
	 */
	public PropertyUtility(String filePath) {
		initializePropertyFile(filePath);
	}
	
	/**
	 * This method is used to initialize the property file
	 * @param filePath
	 */
	@Deprecated
	private void initializePropertyFile(String filePath) {
		FileInputStream fisproperty = null;
		try {
			fisproperty = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fisproperty);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fisproperty.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * This method is used to fetch data from property file based on key
	 * @param Key
	 * @return
	 */
	public String getPropertyData(PropertyKey key) {
		String keyString=key.name().toLowerCase();
		String value= prop.getProperty(keyString, "please give proper key '"+keyString+"'").trim();
		return value;
	}
	

}
