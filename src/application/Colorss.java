package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Colorss{

    private static String backgroundColor;
	private static String MenuColor;
	private static String thirdColor;


   public Colorss(){
	   

	   try {
           File file = new File("C:\\Users\\mahdi\\OneDrive\\Desktop\\MyCodes\\intellij main\\colors.txt");
           Scanner scanner = new Scanner(file);

           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
                   String[] colors = line.split(":");
                   Colorss.backgroundColor = colors[0];
                   Colorss.MenuColor = colors[1];
                   Colorss.thirdColor = colors[2];
               }
           

           scanner.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

   }

    static String getMenuColor() {
        return MenuColor;
    }

    static String getBackgroundColor() {
        return backgroundColor;
    }


    static String getThirdColor() {
        return thirdColor;
    }
}