import java.io.IOException;

public class Main {

    /*
    Randomly ask 1 of 3 predefined questions                    - Done
    present 2 options from an array of 30 possible options      - Done
    have constant a time limit                                  - Done
    record answers                                              - Done
    short post-survey response                                  - Done
    */
    public static void main(String args[]) throws IOException {
        Prompt prompt = new Prompt();
        prompt.addStuff();
        prompt.shuffle();
        System.out.println();
        //prompt.getString().forEach(item -> System.out.print(item + ", ")); //Array of animals is shuffled by this point

        System.out.println("\n" + prompt.getQuestion()); //Asks the question
        String animal1 = prompt.getAnimal();
        String animal2 = prompt.getAnimal(); //Obtains the two animals
        System.out.println(animal1 + " or " + animal2 + "?");
        prompt.timer(60); //Records the answer and sets a time limit to t seconds
        prompt.postResponse(); //Records the post-survey response

        DataManager data = new DataManager();
        data.askName(); //collects the responder's name
        data.newFile(); //creates a new file in the specified directory
        data.writeFile(); //appends data to file



    }
}
