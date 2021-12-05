package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Utils {

	private static final String RELATIVE_TEXT_PATH = "./resources/";

	private static final String RELATIVE_PROPERTIES_PATH = "./resources/";

	/*
	 * Function which will write in text file, with the text, and the file. The
	 * file contained uniquely the name, and not the extension (.txt), this is
	 * automatically added in the function.
	 */
	static public void writeInTextFile(String text, String file) {

		try (FileWriter myWriter = new FileWriter(RELATIVE_TEXT_PATH + file + ".txt", true);) {
			myWriter.append(text);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	/*
	 * Write in text file with array
	 */
	static public void writeInTextFile(int[][] tab, String file) {
		try (FileWriter myWriter = new FileWriter(RELATIVE_TEXT_PATH + file + ".txt", true);) {
			for (int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[i].length; j++) {
					if (i == 0 && j == 0) {
						myWriter.append("[\n[");
					}
					myWriter.append(tab[i][j] + "");

					if (j != tab[i].length - 1) {
						myWriter.append(", ");
					}

					if (i == tab.length - 1 && j == tab[i].length - 1) {
						myWriter.append("]\n]");
					}

					if (j == tab[i].length - 1 && i != tab.length - 1) {
						myWriter.append("],\n[");
					}

				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	static public int[] getItemInPropertiesFile(String key) {
		try (InputStream input = new FileInputStream(RELATIVE_PROPERTIES_PATH + "gameResult.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            
            System.out.println(prop.getProperty(key));
            return convertResultGamePropertiesFormatIntoArray(prop.getProperty(key));
        } catch (IOException io) {
            io.printStackTrace();
        }
		
		return null;
	}
	
	
	static public int[] convertResultGamePropertiesFormatIntoArray(String currentPropertie) {
		if (currentPropertie == null) {
			return null;		
		}
		
		String[] stringArray = currentPropertie.split("#");
		int[] intArray = new int[stringArray.length];
		
		
		for (int i = 0; i < stringArray.length; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]);
		}
		
		return intArray;
		
		
		
	}
	
	
	static public void displayTable(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				System.out.print(" [");				
			} 
			
			System.out.print(arr[i] + ", ");
			
			if (i == arr.length - 1) {
				System.out.println("]");
			}
		}
	}
	
	/*
	 * stat corresponding to the stat of the player in this format in the properties file (NumberofVictory#NumberOfdefeat)
	 * */
	
	static public void setItemInPropertiesFile(String key, int[] stats) {
		try (OutputStream output = new FileOutputStream(RELATIVE_PROPERTIES_PATH + "gameResult.properties")) {

            Properties prop = new Properties();
            String currentStat = "";
            // set the properties value
            for (int i = 0; i < stats.length; i++) {
            	currentStat += String.valueOf(stats[i]);					
            	if (i != stats.length - 1) {
            		currentStat += "#";
				}
			}
            
            prop.setProperty(key, currentStat);

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

	}
	
	
	
	

}
