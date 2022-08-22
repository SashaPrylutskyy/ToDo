import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int ID = 0;
    public static ArrayList<String> dataInformation = new ArrayList<>();
    public static Boolean isWarning = false;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;36m";    // CYAN

    public static void main(String[] args) throws IOException {
        mainWin();
    }
    public static void displayList(){
        int amount = 0;
        for (String i : dataInformation){
            amount++;
            System.out.println("| " + amount + " | " + i);
        }
    }
    public static void logo(){
        System.out.println(CYAN +   "   ________        ___            " + ANSI_RESET);
        System.out.println(CYAN +   "      ||    ____  |    ╲  ____    " + ANSI_RESET);
        System.out.println(YELLOW + "      ||   |    | |     ||    |   " + ANSI_RESET);
        System.out.println(YELLOW + "      ||   |____| |____╱ |____|   " + ANSI_RESET);
    }
    public static void pushArray(String filePath) throws IOException{
        Path output = Paths.get(filePath);
        Files.write(output, dataInformation);
        dataInformation.clear();
    }
    public static void selectMethod() throws IOException {
        Scanner scan = new Scanner(System.in);
        int option = 0;
        try {
            option = scan.nextInt();
        } catch (Exception e) {
            isWarning = true;
        }

        switch (option) {
            case 1:
                addWin();
                break;
            case 2:
                editWin();
                break;
            case 3:
                deleteWin();
                break;
            default:
                isWarning = true;
                mainWin();
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

        logo();
        System.out.println("* * * * * *  ADD Window  * * * * * *");
        System.out.println("* - - - - " + YELLOW + " Enter = Cancel " + ANSI_RESET + " - - - - *");
        System.out.println("* * * * * * Enter a Task * * * * * *");

        Scanner scan = new Scanner(System.in);
        String Task = scan.nextLine();

        if (Task != ""){
            innitArray();
            dataInformation.add(Task);
            pushArray("data.txt");

            mainWin();
        }
        else mainWin();
    }
    public static void mainWin() throws IOException {
        flushTerminal();
        innitArray();
        //ToDo ⭐
        if (isWarning) System.out.println( RED + "Enter valid value" + ANSI_RESET); isWarning = false; //warning message
        logo();
        System.out.println(         GREEN + "  1 - NEW " + ANSI_RESET + YELLOW + "  2 - EDIT"+ ANSI_RESET + RED + "  3 - DELETE" + ANSI_RESET);

        displayList();
        dataInformation.clear();
        selectMethod();
    }

    public static void deleteWin() throws IOException{
        flushTerminal();
        if (isWarning) System.out.println( RED + "Enter valid value" + ANSI_RESET); isWarning = false; //warning message
        logo();
        System.out.println(YELLOW + "   Enter ID of message to" + ANSI_RESET + RED +" DELETE" + ANSI_RESET);

        innitArray();
        displayList();

        //Take id
        Scanner scan = new Scanner(System.in);
        try {
            ID = scan.nextInt();
        }catch (Exception e){
            isWarning = true;
        }
        dataInformation.clear();

        verifyDelete();
    }
    public static void verifyDelete() throws IOException{
        flushTerminal();
        logo();
        System.out.println(YELLOW + "   Message will be deleted permanently" + ANSI_RESET);
        System.out.println("      Enter 'Y' to DELETE");
        System.out.println("      Other key to cancel");

        innitArray();
        System.out.println(YELLOW + "\n   Message: "+ ANSI_RESET + RED + dataInformation.get(ID - 1) + ANSI_RESET);

        Scanner scan = new Scanner(System.in);

        String option = "";
        try{
            option = scan.nextLine();
        }catch(Exception e){
            isWarning = true;
        }
        if (option.equals("y") || option.equals("Y") || option.equals("YES") || option.equals("Yes") || option.equals("yes")){
            dataInformation.remove(ID - 1);
            ID = 0;
            pushArray("data.txt");
        }

        dataInformation.clear();
        mainWin();
    }
    public static void editWin() throws IOException{
        flushTerminal();
        logo();
        System.out.println(CYAN + "      Enter message ID to edit" + ANSI_RESET);
        innitArray();
        displayList();

        Scanner scan = new Scanner(System.in);
        try {
            ID = scan.nextInt();
            flushTerminal();
            logo();
            System.out.println(YELLOW + "Message: " + dataInformation.get(ID - 1) + ANSI_RESET);
            System.out.println("Enter edited message: ");

            try{
                Scanner scanTwo = new Scanner(System.in);
                String newMessage = scanTwo.nextLine();
                dataInformation.remove(ID - 1);
                dataInformation.add(ID - 1, newMessage);
                pushArray("data.txt");
            }catch(Exception e){
                isWarning = true;
                mainWin();
            }
        }catch(Exception e){
            isWarning = true;
            mainWin();
            dataInformation.clear();
        }
        dataInformation.clear();
        mainWin();
    }

}