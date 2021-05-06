import java.time.Clock;
import java.util.*;

public class Prompt {
    private String[] animalstmp = {"Monkey", "Beaver", "Bison", "Camel", "squirrel", "Hedge Hog", "Donkey", "Giraffe", "Hamster", "Dear", "Dog", "Polar Bear", "Kangaroo", "Cat", "Cow", "Rabbit", "Llama", "Lion", "Mouse", "Rhinoceros", "Hippopotamus", "Elephant", "Horse", "Sheep", "Tiger", "Pig", "Bat", "Fox", "Wolf", "Zebra"};
    private String[] questions = {"When travelling at their fastest, which of the following two animals is faster?", "At their tallest, which of the following two animals is taller?", "At their heaviest, which of the following two animals weighs more?"};
    private ArrayList<String> animals = new ArrayList<String>();
    private String postSurvey;
    private long responseTime;
    private String animal1;
    private String animal2;

    int i = -1;

    public void addStuff(){
        for(String str : animalstmp)
        animals.add(str);
    }

    public void shuffle(){
        String tmp;
        int j;
        for(int k = 0; k < 5; k++) { //this loop is unnecessary
            for (int i = 0; i < animals.size(); i++) {
                j = (int) (Math.random() * 30);
                tmp = animals.get(i);
                animals.set(i, animals.get(j));
                animals.set(j, tmp);
            }
        }
    }

    public void timer(long t){
        long time = System.currentTimeMillis(); //implement timer
        while(postSurvey.equals(null)){
            if(System.currentTimeMillis()-time > 60000){ //60,000 = 60 seconds = max response time
                break;
            }
        }
        responseTime = System.currentTimeMillis()-time;
    }

    public String getQuestion(){
        return questions[((int)(Math.random()*3))];
    }

    public String getAnimal(){
        i++;
        if(i > 29){
            i = 0;
        }
        if(i%2 == 0){
            animal1 = animals.get(i);
        } else{
            animal2 = animals.get(i);
        }
        return animals.get(i);
    }

    public void postResponse(){
        Scanner scan = new Scanner(System.in);
        postSurvey = scan.nextLine();
    }

    public String getResponse(){
        return postSurvey;
    }

    public String getAnimal1(){
        return animal1;
    }

    public String getAnimal2(){
        return animal2;
    }

    public long getResponseTime(){ return responseTime; }

}
