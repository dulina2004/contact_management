import java.util.*;
import java.time.LocalDate;

class list {
    private Node start;

    public int search(String nameOrPhone) {
        int index = 0;
        Node temp = start;
        while (temp != null) {
            if (temp.Contact.getName().equals(nameOrPhone)
                    || temp.Contact.getMobile().equals(nameOrPhone)) {
                return index;
            }
            index++;
            temp = temp.next;
        }
        return -1;
    }

    public void remove(int index) {
        if (index == 0) {
            start = start.next;
        } else {
            int count = 0;
            Node temp = start;
            while (count < index - 1) {
                count++;
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
    }

    public void add(contact data) {
        Node n1 = new Node(data);
        if (start == null) {
            start = n1;
        } else {
            Node lastNode = start;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = n1;
        }
    }

    private boolean isEmpty() {
        return start == null;
    }

    private int size() {
        int count = 0;
        Node temp = start;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private contact get(int index) {
        Node temp = start;
        int count = 0;
        while (count < index) {
            count++;
            temp = temp.next;
        }
        return temp.Contact;
    }

    // ------------------UPDATE NAME------------------------
    public void updateName(int index, String name) {
        get(index).setName(name);
    }

    // ------------------UPDATE MOBILE------------------------
    public void updateMobile(int index, String mobile) {
        get(index).setMobile(mobile);
    }

    // ------------------UPDATE COMPANY------------------------
    public void updateCompany(int index, String company) {
        get(index).setCompany(company);
        ;
    }

    // ------------------UPDATE SALARY------------------------
    public void updateSalary(int index, int salary) {
        get(index).setSalary(index);
    }

    public void about(int index) {
        System.out.println("\tContact ID            :" + get(index).getId());
        System.out.println("\tName                  :" + get(index).getName());
        System.out.println("\tPhone Number          :" + get(index).getMobile());
        System.out.println("\tCompany Name          :" + get(index).getCompany());
        System.out.println("\tSalary                :" + get(index).getSalary());
        System.out.println("\tB'Day(YYYY-MM-DD)     :" + get(index).getBirthday() + "\n");
    }

    public void listAll() {
        System.out.println(
                "+-----------------------------------------------------------------------------------------------+");
        System.out.println("| Contact ID\t| Name\t\t|Phone Number\t| Company\t| Salary\t| Birthday\t|");
        System.out.println(
                "+-----------------------------------------------------------------------------------------------+");
        for (int i = 0; i < size(); i++) {
            System.out.printf("| %-14s| %-14s| %-14s| %-14s| %-14d| %-14s|\n", get(i).getId(), get(i).getName(),
                    get(i).getMobile(), get(i).getCompany(), get(i).getSalary(), get(i).getBirthday());
        }
        System.out.println(
                "+-----------------------------------------------------------------------------------------------+");
    }

    //////////////////////////////
    // ------sorting and swap
    ///////////////////////////////
    private void swap(Node n) {
        contact temp = n.Contact;
        n.Contact = n.next.Contact;
        n.next.Contact = temp;
    }

    public void sortSalary() {
        for (int i = size(); i > 1; i--) {
            Node temp = start;
            boolean sorted = true;
            for (int j = 0; j < i - 1; j++) {
                if (temp.Contact.getSalary() > temp.next.Contact.getSalary()) {
                    sorted = false;
                    swap(temp);
                }
                temp = temp.next;
            }
            if (sorted) {
                break;
            }
        }
        listAll();
    }

    public void sortBirthday() {
        for (int i = size(); i > 1; i--) {
            Node temp = start;
            boolean sorted = true;
            for (int j = 0; j < i - 1; j++) {

                String temp1 = temp.Contact.getBirthday().substring(0, 4)
                        + temp.Contact.getBirthday().substring(5, 7)
                        + temp.Contact.getBirthday().substring(8, 10);
                int x1 = Integer.parseInt(temp1);
                String temp2 = temp.next.Contact.getBirthday().substring(0, 4)
                        + temp.next.Contact.getBirthday().substring(5, 7)
                        + temp.next.Contact.getBirthday().substring(8, 10);
                int x2 = Integer.parseInt(temp2);
                if (x1 > x2) {
                    sorted = false;
                    swap(temp);
                }
                temp = temp.next;
            }
            if (sorted) {
                break;
            }
        }
        listAll();
    }

    public void sortName() {
        for (int i = size(); i > 1; i--) {
            Node temp = start;
            boolean sorted = true;
            for (int j = 0; j < i - 1; j++) {
                String name1 = temp.Contact.getName().toLowerCase();
                String name2 = temp.next.Contact.getName().toLowerCase();
                if (name1.compareTo(name2) > 0) {
                    sorted = false;
                    swap(temp);
                }
                temp = temp.next;
            }
            if (sorted) {
                break;
            }
        }
        listAll();
    }
    //////////////////////////////
    // ----innerclass
    /////////////////////////////

    class Node {
        private contact Contact;
        private Node next;

        public Node(contact data) {
            this.Contact = data;
        }
    }

    public String generateID() {
        Node temp = start;
        int lastNo = 0;
        while (temp != null) {
            String lastId = temp.Contact.getId();
            int x = Integer.parseInt(lastId.substring(1));
            if (lastNo <= x) {
                lastNo = x;
            }
            temp = temp.next;
        }
        return String.format("C%04d", lastNo + 1);
    }
}

class contact {
    private String id;
    private String name;
    private String mobile;
    private String company;
    private int salary;
    private String birthday;

    public contact(String id, String name, String mobile, String company, int salalry, String birthday) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.company = company;
        this.salary = salalry;
        this.birthday = birthday;
    }

    /////////////////// getters
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getMobile() {
        return this.id;
    }

    public String getCompany() {
        return this.name;
    }

    public int getSalary() {
        return this.salary;
    }

    public String getBirthday() {
        return this.birthday;
    }

    ////////////////////// setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

class ifriend {

    ////////// global
    public static list contactList = new list();

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////////// title create
    public static void title(String word) {
        System.out.print("+----------");
        for (int i = 0; i < word.length(); i++) {
            System.out.print("-");
        }
        System.out.print("----------+\n");
        System.out.print("|          ");
        System.out.print(word);
        System.out.print("          |\n");
        System.out.print("+----------");
        for (int i = 0; i < word.length(); i++) {
            System.out.print("-");
        }
        System.out.print("----------+\n");
    }
    ///////////////////////////////////////////////////////////////////////
    // ADD CONTACT TO LIST
    /////////////////////////////////////////////////////////////////////

    public static String generateID() {
        return contactList.generateID();
    }

    public static String generateMobile() {
        Scanner input = new Scanner(System.in);
        System.out.print("Phone Number    : ");
        String n = input.next();
        n = n.strip();
        if (n.length() == 10 && n.charAt(0) == '0') {
            return n;
        } else {
            System.out.println("\n\tinvalid phone number...\n");
            System.out.print("Do you want to add phone number again (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                // Move the cursor up five lines
                System.out.print("\033[5A");
                // Clear the lines
                System.out.print("\033[0J");
                return generateMobile();
            }
            body();
        }
        return "";
    }

    public static int generateSalary() {
        Scanner input = new Scanner(System.in);
        System.out.print("Salary          : ");
        int salary = input.nextInt();
        if (salary > 0) {
            return salary;
        } else {
            System.out.println("\n\tinvalid salary...\n");
            System.out.print("Do you want to enter again (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                // Move the cursor up five lines
                System.out.print("\033[5A");
                // Clear the lines
                System.out.print("\033[0J");
                return generateSalary();
            }
            body();
        }
        return 0;
    }

    public static String generateBirthday() {
        Scanner input = new Scanner(System.in);

        try {

            System.out.print("B'Day(YYYY-m-DD): ");
            String str = input.next();
            str = str.strip();
            String[] parts = str.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            boolean validate;

            LocalDate localD = LocalDate.now();
            // create a LocalDate object

            String localdate = localD.toString();
            localdate = localdate.strip();
            String[] local = localdate.split("-");
            int localyear = Integer.parseInt(local[0]);
            int localmonth = Integer.parseInt(local[1]);
            int localday = Integer.parseInt(local[2]);
            if (year < localyear) {
                validate = true;
            } else if (year == localyear) {
                if (month < localmonth) {
                    validate = true;
                } else if (month == localmonth) {
                    if (day < localday) {
                        validate = true;
                    } else {
                        validate = false;
                    }
                } else {
                    validate = false;
                }
            } else {
                validate = false;
            }

            if (str.length() == 10 && validate && month < 13 && day < 32) {
                return str;
            } else {
                System.out.println("\n\tinvalid Birthday...\n");
                System.out.print("Do you want to input birthday again (Y/N):");
                char choice = input.next().charAt(0);
                if (choice == 'Y' || choice == 'y') {
                    // Move the cursor up five lines
                    System.out.print("\033[5A");
                    // Clear the lines
                    System.out.print("\033[0J");
                    return generateBirthday();
                }
                body();
            }
            return "";

        } catch (Exception e) {
            System.out.println("\n\tinvalid Birthday...\n");
            System.out.print("Do you want to input birthday again (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                // Move the cursor up five lines
                System.out.print("\033[5A");
                // Clear the lines
                System.out.print("\033[0J");
                return generateBirthday();
            }
            body();
            return "";
        }

    }

    public static void addContacts() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("Add Contact to the list");
        System.out.println("\n\n");

        String tempid = generateID();
        System.out.println(tempid + "\n======\n");

        System.out.print("Name            : ");
        String tempname = input.next();

        String tempMobile = generateMobile();

        System.out.print("Company Name    : ");
        String tempCompany = input.next();

        int tempSalary = generateSalary();

        String tempBirthday = generateBirthday();

        contact temp = new contact(tempid, tempname, tempMobile, tempCompany, tempSalary, tempBirthday);
        contactList.add(temp);
        System.out.println("\n\tContact has been added successfully...\n");
        System.out.print("Do you want to add another contact (Y/N):");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            addContacts();
        }
        body();

    }

    /////////////////////////////////////////////////////////////////////
    // UPDATE CONTACT
    ////////////////////////////////////////////////////////////////////
    public static void updateCompany(int index) {
        // Move the cursor up five lines
        System.out.print("\033[8A");
        // Clear the lines
        System.out.print("\033[0J");
        Scanner input = new Scanner(System.in);
        System.out.println("Update Company");
        System.out.println("===============\n");
        System.out.print("Input new company - ");
        String n = input.next();
        contactList.updateCompany(index, n);
        System.out.println("\n\tcontact has been update succesfully...\n");
        System.out.print("Do you want to update another contact (Y/N):");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            updateContacts();
        }
        body();
    }

    public static void updateName(int index) {
        // Move the cursor up five lines
        System.out.print("\033[8A");
        // Clear the lines
        System.out.print("\033[0J");
        Scanner input = new Scanner(System.in);
        System.out.println("Update Name");
        System.out.println("============\n");
        System.out.print("Input new name - ");
        String n = input.next();
        contactList.updateName(index, n);
        System.out.println("\n\tcontact has been update succesfully...\n");
        System.out.print("Do you want to update another contact (Y/N):");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            updateContacts();
        }
        body();
    }

    public static void updateMobile(int index) {
        // Move the cursor up five lines
        System.out.print("\033[8A");
        // Clear the lines
        System.out.print("\033[0J");
        Scanner input = new Scanner(System.in);
        System.out.println("Update Phone Number");
        System.out.println("===================\n");
        System.out.print("Input new phone Number - ");
        String n = input.next();
        n = n.strip();
        if (n.length() == 10 && n.charAt(0) == '0') {
            contactList.updateMobile(index, n);
            System.out.println("\n\tcontact has been update succesfully...\n");
            System.out.print("Do you want to update another contact (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                updateContacts();
            }
            body();
        } else {
            System.out.println("\n\tinvalid phone number...\n");
            System.out.print("Do you want to add phone number again (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                updateMobile(index);
            }
            body();
        }
    }

    public static void updateSalary(int index) {
        // Move the cursor up five lines
        System.out.print("\033[8A");
        // Clear the lines
        System.out.print("\033[0J");
        Scanner input = new Scanner(System.in);
        System.out.println("Update Salary");
        System.out.println("==============\n");
        System.out.print("Input new salary - ");
        int n = input.nextInt();
        if (n > 0) {
            contactList.updateSalary(index, n);
            System.out.println("\n\tcontact has been update succesfully...\n");
            System.out.print("Do you want to update another contact (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                updateContacts();
            }
            body();
        } else {
            System.out.println("\n\tinvalid Salary...\n");
            System.out.print("Do you want to add phone number again (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                updateSalary(index);
            }
            body();
        }
    }

    public static void updateContacts() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("UPDATE Contact");
        System.out.println("\n\n");

        System.out.print("Search Contact by Name or Phone Number -");
        String str = input.next();
        str = str.strip();
        int index = contactList.search(str);
        if (index == -1) {
            updateContacts();
        } else {
            // System.out.println(index);
            System.out.println();
            contactList.about(index);
            System.out.println("What do you want to update...\n");
            System.out.println("\t[01] Name");
            System.out.println("\t[02] Phone Number");
            System.out.println("\t[03] Company Name");
            System.out.println("\t[04] Salary\n");
            System.out.print("Enter an option to continue -> ");
            int x = input.nextInt();
            switch (x) {
                case 1:
                    updateName(index);
                    break;
                case 2:
                    updateMobile(index);
                    break;
                case 3:
                    updateCompany(index);
                    break;
                case 4:
                    updateSalary(index);
                    break;

                default:
                    body();
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    // DELETE CONTACT
    ////////////////////////////////////////////////////////////////////
    public static void deleteContacts() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("DELETE Contact");
        System.out.println("\n\n");

        System.out.print("Search Contact by Name or Phone Number -");
        String str = input.next();
        str = str.strip();
        int index = contactList.search(str);
        if (index == -1) {
            updateContacts();
        } else {
            System.out.println();
            contactList.about(index);
            {
                System.out.print("Do you want to delete this contact (Y/N): ");
                char choice = input.next().charAt(0);
                if (choice == 'Y' || choice == 'y') {
                    contactList.remove(index);
                    System.out.println("\n\tContact has beed deleted succesfully...");
                }
            }
            {
                System.out.print("\n\nDo you want to delete another contact (Y/N): ");
                char choice = input.next().charAt(0);
                if (choice == 'Y' || choice == 'y') {
                    deleteContacts();
                }
                body();
            }
        }

    }

    /////////////////////////////////////////////////////////////////////
    // SEARCH CONTACT
    ////////////////////////////////////////////////////////////////////
    public static void searchContacts() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("SEARCH Contact");
        System.out.println("\n\n");
        System.out.print("Search Contact by Name or Phone Number -");
        String str = input.next();
        str = str.strip();
        int index = contactList.search(str);
        if (index == -1) {
            System.out.println("\nNo contact found for " + str + "...\n");
            System.out.print("Do you want to try a new search (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                searchContacts();
            }
            body();
        } else {
            System.out.println();
            System.out.println();
            contactList.about(index);
            System.out.print("Do you want to try a new search (Y/N):");
            char choice = input.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                searchContacts();
            }
            body();
        }

    }

    /////////////////////////////////////////////////////////////////////
    // SORT CONTACT
    ////////////////////////////////////////////////////////////////////
    public static void listContact() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("SORT Contact");
        System.out.println("\n\t[01] Sorting by Name\n");
        System.out.println("\t[02] Sorting by Salary\n");
        System.out.println("\t[03] Sorting by Birthday\n");
        System.out.print("Enter an option to continue ->");
        int option = input.nextInt();
        switch (option) {
            case 1:
                ;
                listByName();
                break;
            case 2:
                ;
                listBySalary();
                break;
            case 3:
                ;
                listByBirthday();
                break;
            default:
                body();
        }
        System.out.print("\nDo you want to go to home page (Y/N):");
        char choice = input.next().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            body();
        }
    }

    public static contact[] newcontactArray = new contact[0];

    public static void listByName() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("List Contact by Name");
        System.out.println();
        contactList.sortName();
    }

    public static void listBySalary() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("List Ccntact by Salary");
        System.out.println();
        contactList.sortSalary();

    }

    public static void listByBirthday() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        title("List Contact by Birthday");
        System.out.println();
        contactList.sortBirthday();

    }

    public static void body() {
        clearConsole();
        Scanner input = new Scanner(System.in);
        System.out.println("\t /$$ /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$   /$$ /$$$$$$$ ");
        System.out.println("\t|__/| $$_____/| $$__  $$|_  $$_/| $$_____/| $$$ | $$| $$__  $$");
        System.out.println("\t /$$| $$      | $$  \\ $$  | $$  | $$      | $$$$| $$| $$  \\ $$");
        System.out.println("\t| $$| $$$$$   | $$$$$$$/  | $$  | $$$$$   | $$ $$ $$| $$  | $$");
        System.out.println("\t| $$| $$__/   | $$__  $$  | $$  | $$__/   | $$  $$$$| $$  | $$");
        System.out.println("\t| $$| $$      | $$  \\ $$  | $$  | $$      | $$\\  $$$| $$  | $$");
        System.out.println("\t| $$| $$      | $$  | $$ /$$$$$$| $$$$$$$$| $$ \\  $$| $$$$$$$/");
        System.out.println("\t|__/|__/      |__/  |__/|______/|________/|__/  \\__/|_______/\n\n");

        System.out.println("   _____            _             _          ____                        _    ");
        System.out.println("  / ____|          | |           | |        / __ \\                      (_)   ");
        System.out.println(" | |     ___  _ __ | |_ __ _  ___| |_ ___  | |  | |_ __ __ _  __ _ _ __  _ _______ _ __ ");
        System.out.println(
                " | |    / _ \\| '_ \\| __/ _` |/ __| __/ __| | |  | | '__/ _` |/ _` | '_ \\| |_  / _ \\ '__|");
        System.out.println(" | |___| (_) | | | | || (_| | (__| |_\\__ \\ | |__| | | | (_| | (_| | | | | |/ /  __/ | ");
        System.out.println(
                "  \\_____\\___/|_| |_|\\__\\__,_|\\___|\\__|___/  \\____/|_|  \\__, |\\__,_|_| |_|_/___\\___|_| ");
        System.out.println("                                                        __/ |                 ");
        System.out.println("                                                       |___/  \n");
        System.out.println(
                "===========================================================================================\n");

        System.out.println("\t[01] ADD Contacts\n");
        System.out.println("\t[02] UPDATE Contacts\n");
        System.out.println("\t[03] DELETE Contacts\n");
        System.out.println("\t[04] SEARCH Contacts\n");
        System.out.println("\t[05] LIST Contacts\n");
        System.out.println("\t[06] Exit\n");
        System.out.print("Enter an option to continue ->");
        int option = input.nextInt();
        switch (option) {
            case 1:
                addContacts();
                break;
            case 2:
                updateContacts();
                break;
            case 3:
                deleteContacts();
                break;
            case 4:
                searchContacts();
                break;
            case 5:
                listContact();
                break;
            case 6:
                return;
            default:
                body();
        }
    }

    public static void main(String[] args) {
        body();
    }
}
