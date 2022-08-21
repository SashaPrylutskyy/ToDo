import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> dataInformation = new ArrayList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    public static void main(String[] args) throws IOException {
        welcomeWin();
    }
    public static void welcomeWin() throws IOException {
        flushTerminal();
        System.out.println("* * * * * * ToDo application * * * * * *");
        System.out.println("*           Select an option           *");
        System.out.println("* - - - - - - - - - - - - - - - - - -  *");
        System.out.println("*              1 - List                *");
        System.out.println("*              2 - Add                 *");
        System.out.println("*              3 - Edit                *");
        System.out.println("* _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  *");
        selectMethod();
    }
    public static void selectMethod() throws IOException {
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();

        switch(option){
            case 1:
                listWin();
                break;
            case 2:
                addWin();
                break;
            case 3:
                //editWin();
                break;
            default:
                //System.out.println("Something went wrong...\nReEnter, please: ");
                System.out.println("Restart app");
        }
    }
    public static void flushTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void innitArray() throws IOException{
        Scanner reader = new Scanner(new File("data.txt"));
        while (reader.hasNextLine()){
            dataInformation.add(reader.nextLine());
        }
    }
    public static void addWin() throws IOException{
        flushTerminal();

        System.out.println("* * * * * * *  ADD Window  * * * * * * *");
        System.out.println("* - - - - - " + YELLOW + " Enter = Cancel " + ANSI_RESET + " - - - - - *");
        System.out.println("* * * * * * * Enter a Task * * * * * * *");

        Scanner scan = new Scanner(System.in);
        String Task = scan.nextLine();

        if (Task != ""){
            innitArray();
            dataInformation.add(Task);

            Path output = Paths.get("data.txt");
            Files.write(output, dataInformation);
            dataInformation.clear();

            welcomeWin();
        }
        else welcomeWin();
    }

    public static void listWin() throws IOException {
        flushTerminal();
        innitArray();
        //ToDo
        System.out.println(CYAN +   "________        ___         " + ANSI_RESET);
        System.out.println(CYAN +   "   ||    ____  |    ╲  ____ " + ANSI_RESET);
        System.out.println(YELLOW + "   ||   |    | |     ||    |" + ANSI_RESET);
        System.out.println(YELLOW + "   ||   |____| |____╱ |____|" + ANSI_RESET);
        int amount = 0;
        for (String i : dataInformation){
            amount++;
            System.out.println("| " + amount + " | " + i);
        }
    }

}