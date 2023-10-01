package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Contact mainContacts = new Contact();

    public static void main(String[] args) {
        new Main().input();
    }

    public void input(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] com = input.split(" ");
        switch (com[0]){
            case "add":
                mainContacts.add(com[1], com[2], com[3]);
                break;
            case "remove":
                if(com.length == 2)
                    mainContacts.remove(Integer.parseInt(com[1]));
                else
                    mainContacts.remove(com[1], com[2], com[3]);
                break;
            case "edit":
                if(com.length == 5)
                    mainContacts.edit(Integer.parseInt(com[1]), com[2], com[3], com[4]);
                else
                    mainContacts.edit(com[1], com[2], com[3], com[4], com[5], com[6]);
                break;
            case "print":
                mainContacts.print();
                break;
            case "logs":
                mainContacts.logs();
                break;
            case "quit":
                mainContacts.quit();
                break;
            default:
                System.out.println("error");
                break;
        }
        if(!mainContacts.isQuite())
            input();
    }
}

class Contact{
    private boolean isOut = true;
    private boolean isQuit = false;
    Logs out = new Logs();
    private String name;
    private String surName;
    private String num;


    ArrayList<Contact> contacts = new ArrayList<>();



    public void add(String name, String surName,String num){
        boolean error = false;
        for (Contact contact : contacts) {
            if (contact.name.equals(name) && contact.surName.equals(surName) && contact.num.equals(num)) {
                System.out.println(out.logAddError(isOut));
                error = true;
            }
        }
        if(!error){
            Contact tmp = new Contact();
            tmp.name = name;
            tmp.surName = surName;
            tmp.num = num;
            contacts.add(tmp);
            System.out.println(out.logAddSuccess(isOut));
        }
        System.out.println();
    }

    public void remove(String name, String surName, String num){
        boolean isDo = false;
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).name.equals(name) && contacts.get(i).surName.equals(surName) && contacts.get(i).num.equals(num)) {
                contacts.remove(i);
                isDo = true;
            }
        }
        if(isDo)
            System.out.println(out.logRemoveSuccess(isOut));
        else
            System.out.println(out.logRemoveFoundError(isOut));
        System.out.println();
    }

    public void remove(int index){
        if(index > contacts.size())
            System.out.println(out.logRemoveIndexError(isOut));
        else {
            contacts.remove(index - 1);
            System.out.println(out.logRemoveSuccess(isOut));
        }
        System.out.println();
    }

    public void edit(String name, String surName, String num, String newName, String newSurName, String newNum){
        for (int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).name.equals(name) && contacts.get(i).surName.equals(surName) && contacts.get(i).num.equals(num)){
                Contact tmp = new Contact();
                tmp.name = newName;
                tmp.surName = newSurName;
                tmp.num = newNum;
                contacts.set(i, tmp);
                System.out.println(out.logEditSuccess(isOut));
            } else
                System.out.println(out.logEditFoundError(isOut));
            System.out.println();
        }
    }

    public void edit(int index, String newName, String newSurName, String newNum){
        if(index >= contacts.size()) {
            Contact tmp = new Contact();
            tmp.name = newName;
            tmp.surName = newSurName;
            tmp.num = newNum;
            contacts.set(index - 1, tmp);
            System.out.println(out.logEditSuccess(isOut));
        } else
            System.out.println(out.logEditIndexError(isOut));
        System.out.println();
    }

    public void print(){
        if(!contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.print(i + 1 + ": ");
                System.out.print(contacts.get(i).name + "; ");
                System.out.print(contacts.get(i).surName + "; ");
                System.out.print(contacts.get(i).num);
                System.out.println();
            }
        } else
            System.out.println(out.logPrintError(isOut));
        System.out.println();
    }

    public void logs(){
        if(isOut) {
            System.out.println(out.logLogsOffSuccess(true));
            isOut = false;
        } else {
            System.out.println(out.logLogsOnSuccess(true));
            isOut = true;
        }
        System.out.println();
    }

    public void quit(){
        System.out.println(out.logQuitSuccess(isOut));
        isQuit = true;
    }

    public boolean isQuite() {
        return isQuit;
    }
}

class Logs{
    final private String nullX = "";
    final private String addError =           "E: Ти вже маєш цей контакт !";
    final private String removeFoundError =   "E: Такого контакту не iснує !";
    final private String removeIndexError =   "E: Такого iндексу контакту не iснує !";
    final private String editFoundError =     "E: Такого контакту не iснує !";
    final private String editIndexError =     "E: Такого iндексу контакту не iснує !";
    final private String printError =         "E: Список контактів порожній !";

    final private String addSuccess =         "S: Контакт доданий ✓";
    final private String removeSuccess =      "S: Контакт видалено ✓";
    final private String editSuccess =        "S: контакт відредаговано ✓";
    final private String quitSuccess =        "S: зупинення коду...";
    final private String logsOffSuccess =     "S: відображення історії вимкнено ✓";
    final private String logsOnSuccess =      "S: відображення історії включено ✓";

    public String logAddError(boolean out) {
        if(out)
            return addError;
        else
            return nullX;
    }public String logRemoveFoundError(boolean out) {
        if(out)
            return removeFoundError;
        else
            return nullX;
    }public String logRemoveIndexError(boolean out) {
        if(out)
            return removeIndexError;
        else
            return nullX;
    }public String logEditFoundError(boolean out) {
        if(out)
            return editFoundError;
        else
            return nullX;
    }public String logEditIndexError(boolean out) {
        if(out)
            return editIndexError;
        else
            return nullX;
    }public String logPrintError(boolean out) {
        if(out)
            return printError;
        else
            return nullX;
    }public String logAddSuccess(boolean out) {
        if(out)
            return addSuccess;
        else
            return nullX;
    }public String logRemoveSuccess(boolean out) {
        if(out)
            return removeSuccess;
        else
            return nullX;
    }public String logEditSuccess(boolean out) {
        if(out)
            return editSuccess;
        else
            return nullX;
    }public String logQuitSuccess(boolean out) {
        if(out)
            return quitSuccess;
        else
            return nullX;
    }public String logLogsOffSuccess(boolean out) {
        if(out)
            return logsOffSuccess;
        else
            return nullX;
    }public String logLogsOnSuccess(boolean out) {
        if (out)
            return logsOnSuccess;
        else
            return nullX;
    }
}