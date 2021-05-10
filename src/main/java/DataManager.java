import java.io.*;
import java.util.*;

public class DataManager {
    private String name;
    private String pathway = "C:\\Users\\Shsmith0206\\OneDrive - Mesa Public Schools\\Desktop\\HData";
    Scanner scan = new Scanner(System.in);

    public void askName(){
        System.out.println("Please type your first and last name, and press [Enter]");
        name = scan.nextLine();
    }

    public void newFile() throws IOException{
        File file = new File(pathway + "\\" + name + ".txt");
        if(!file.createNewFile()){
            System.out.println("File Already Exists.");
        }
        writeFile();
    }

    public void writeFile() throws IOException{
        Prompt prompt = new Prompt();
        FileWriter writer = new FileWriter(name + ".txt");
        writer.write("Name: " + name);
        writer.write("Time Allotted: 60s");
        writer.write("Response Time: " + prompt.getResponseTime());
        writer.write("The Question Was: " + prompt.getQuestion());
        writer.write("The animals in questions were: " + prompt.getAnimal1() + " and " + prompt.getAnimal2());
        writer.write("Post-Survey Response: " + prompt.getResponse());
        writer.close();

    }

}
