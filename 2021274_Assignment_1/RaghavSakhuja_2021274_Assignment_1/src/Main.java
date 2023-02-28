import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class Main {

    public static PlacementCell PCell = new PlacementCell();

    public static void main(String[] args) {

        entryMenu();

    }
    public static void entryMenu(){
        while (true) {
            System.out.println("Welcome to FutureBuilder:\n"
                    + "\t 1)Enter the Application\n"
                    + "\t 2)Exit the Application\n");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            String buffer = sc.nextLine();
            if (val == 1) {
                enterAsMenu();
            } else if (val == 2) {
                System.out.println("Thanks for using Future Builder");
                break;
            } else {
                System.out.println("Wrong input\n");
            }
        }
    }
    public static void enterAsMenu(){
        while (true) {
            System.out.println("Choose The mode you want to Enter in:-\n" +
                    "\t1) Enter as Student Mode\n" +
                    "\t2) Enter as Company Mode\n" +
                    "\t3) Enter as Placement Cell Mode\n" +
                    "\t4) Return To Main Application\n");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            String buffer = sc.nextLine();
            if (val == 1) {
                Studentmenu();
            } else if (val == 2) {
                Companymemu();
            } else if (val == 3) {
                Placementcellmenu();
            } else if (val == 4) {
                break;
            } else {
                System.out.println("Wrong input\n");
            }
        }
    }
    public static void Placementcellmenu(){
        while (true) {
            System.out.println("Welcome to IIITD Placement Cell\n" +
                    "\t1) Open Student Registrations\n" +
                    "\t2) Open Company Registrations\n" +
                    "\t3) Get Number of Student Registrations\n" +
                    "\t4) Get Number of Company Registrations\n" +
                    "\t5) Get Number of Offered/Unoffered/Blocked Students\n" +
                    "\t6) Get Student Details\n" +
                    "\t7) Get Company Details\n" +
                    "\t8) Get Average Package\n" +
                    "\t9) Get Company Process Results\n" +
                    "\t10) Back");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            sc.nextLine();
            if (val == 1) {
                PCell.OpenStudentRegistration();
            } else if (val == 2) {
                PCell.OpenCompanyRegistration();
            } else if (val == 3) {
                PCell.getNumberofstudents();
            } else if (val == 4) {
                PCell.getNumberofcompanies();
            } else if (val == 5) {
                PCell.getNumberofstudents_status();
            } else if (val == 6) {
                PCell.getStudentDetails();
            } else if (val == 7) {
                PCell.getCompaniesDetails();
            } else if (val == 8) {
                PCell.getAveragePackage();
            } else if (val == 9) {
                System.out.println("Input the name of the Company: ");
                String Name=sc.nextLine();
                for(int i=0;i<PCell.companies.size();i++){
                    if(PCell.companies.get(i).Name.equals(Name)){
                        PCell.getCompanyProcessResult(PCell.companies.get(i));
                    }
                }
            } else if (val == 10) {
                break;
            } else {
                System.out.println("Wrong input\n");
            }
        }

    }
    public static void Companymemu(){
        while (true) {
            System.out.println("Choose the Company Query to perform\n" +
                    "\t1) Add Company and Details\n" +
                    "\t2) Choose Company\n" +
                    "\t3) Get Available Companies\n" +
                    "\t4) Back");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            String buffer = sc.nextLine();
            if (val == 1) {
                System.out.println("Input the name of the Company: ");
                String Name=sc.nextLine();
                System.out.println("Input the Role the Company is offering: ");
                String Role=sc.nextLine();
                System.out.println("Input CTC in LPA: ");
                int ctc=sc.nextInt();
                System.out.println("Input the CGPA requirement for the Company: ");
                int CGPA=sc.nextInt();
                Company c=new Company(Name,Role,ctc,CGPA);
                PCell.companies.add(c);
            } else if (val == 2) {
                for(int i=0;i<PCell.companies.size();i++){
                    System.out.println((i+1)+") "+PCell.companies.get(i).Name);
                }
                System.out.println("Enter the index of the Company you wish to choose: ");
                int idx=sc.nextInt();
                CompanySpecific(idx);
            } else if (val == 3) {
                System.out.println("The Available companies are: ");
                for (int i = 0; i < PCell.companies.size(); i++) {
                    Company S = PCell.companies.get(i);
                    System.out.println("Company Name: " + S.Name +
                            "\nRollNo Offered: " + S.Role +
                            "\nPackage Offered: " + S.Package +
                            "\nCGPA Criteria: " + S.CGPAcriteria);
                }
            } else if (val == 4) {
                break;
            } else {
                System.out.println("Wrong input\n");
                enterAsMenu();
            }
        }
    }

    private static void CompanySpecific(int i) {
        while (true) {
            Company c=PCell.companies.get(i-1);
            System.out.println("Welcome "+ c.Name+"!!\n" +
                    "\t1) Update Role\n" +
                    "\t2) Update Package\n" +
                    "\t3) Update CGPA criteria\n" +
                    "\t4) Register To Institute Drive\n" +
                    "\t5) Back");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            String buffer = sc.nextLine();
            if (val == 1) {
                System.out.println("Enter the Role you wish to offer: ");
                String role=sc.nextLine();
                c.updateRole(role);
            } else if (val == 2) {
                System.out.println("Enter the amount in LPA: ");
                int pack=sc.nextInt();
                buffer = sc.nextLine();
                c.updatePackage(pack);
            } else if (val == 3) {
                System.out.println("Enter the new CGPA criteria: ");
                int cgpa=sc.nextInt();
                buffer = sc.nextLine();
                c.updateCGPA(cgpa);
            } else if (val == 4) {
                System.out.println("Enter the Registration date-time(in format dd-MM-yyyy HH:mm:ss) :");
                String Date1=sc.nextLine();
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                try{
                    Date Odate=format.parse(Date1);
                    if(Odate.compareTo(PCell.OpeningCompanyReg)<0){
                        System.out.println("Registration hasn't started yet.");
                    }
                    else if(Odate.compareTo(PCell.EndingCompanyReg)>0){
                        System.out.println("Registration has ended");
                    }
                    else{
                        PCell.companies.get(i-1).Register(Odate);
                    }
                }
                catch (ParseException e) {
                    System.out.println("Wrong input format\n");
                }
            } else if (val == 5) {
                break;
            } else {
                System.out.println("Wrong input\n");
            }
        }
    }

    public static void Studentmenu() {
        while (true) {
            System.out.println("Choose the Student Query to perform\n" +
                    "\t1) Enter as a Student(Give Student Name, and Roll No.)\n" +
                    "\t2) Add students\n" +
                    "\t3) Back\n");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            String buffer = sc.nextLine();
            if (val == 1) {
                System.out.println("Enter the name of Student: ");
                String name=sc.nextLine();
                System.out.println("Enter the Roll No: ");
                String roll=sc.nextLine();
                for(int i=0;i<PCell.students.size();i++){
                    if(name.equals(PCell.students.get(i).Name) && roll.equals(PCell.students.get(i).RollNo)){
                        StudentSpecific(PCell.students.get(i));
                    }
                }

            } else if (val == 2) {
                System.out.println("Enter the Number of students you wish to enter: ");
                int a=sc.nextInt();
                sc.nextLine();
                for(int i=0;i<a;i++){
                    System.out.println("Enter the name of Student: ");
                    String name=sc.nextLine();
                    System.out.println("Enter the Roll No: ");
                    String roll=sc.nextLine();
                    System.out.println("Enter the Branch of Student: ");
                    String branch=sc.nextLine();
                    System.out.println("Enter the CGPA of the Student: ");
                    double CGPA=sc.nextDouble();
                    sc.nextLine();
                    Student s=new Student(name,roll,CGPA,branch);
                    PCell.students.add(s);

                }
            } else if (val == 3) {
                break;
            } else {
                System.out.println("Wrong input\n");
            }
        }
    }

    private static void StudentSpecific(Student student) {
        while (true){
            System.out.println("Welcome "+student.Name+"!!\n" +
                    "\t1) Register For Placement Drive\n" +
                    "\t2) Register For Company\n" +
                    "\t3) Get All available companies\n" +
                    "\t4) Get Current Status\n" +
                    "\t5) Update CGPA\n" +
                    "\t6) Accept offer\n" +
                    "\t7) Reject offer\n" +
                    "\t8) Back");
            Scanner sc = new Scanner(System.in);
            int val = sc.nextInt();
            String buffer = sc.nextLine();
            if (val == 1) {
                System.out.println("Enter the Registration date-time(in format dd-MM-yyyy HH:mm:ss) :");
                String Date1=sc.nextLine();
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                try{
                    Date date=format.parse(Date1);
                    if(date.compareTo(PCell.OpeningStudentReg)<0){
                        System.out.println("Registration hasn't started yet. Try later.");
                    }
                    else if(date.compareTo(PCell.EndingStudentReg)>0){
                        System.out.println("Registration has ended. You are Late");
                    }
                    else{
                        student.registerForDrive(date);
                    }
                }
                catch (ParseException e) {
                    System.out.println("Wrong input format\n");
                }

            } else if (val == 2) {
                System.out.println("Enter the name of the company you wish to register for: ");
                String company=sc.nextLine();
                student.registerForCompany(PCell,company);
            } else if (val == 3) {
                student.getAllCompanies(PCell);
            } else if (val == 4) {
                student.getCurrentStatus();
            } else if (val == 5) {
                System.out.println("Enter the new CGPA: ");
                int cg=sc.nextInt();
                PCell.updateCGPA(student,cg);
            } else if (val == 6) {

                student.Accept();

            } else if (val == 7) {
                student.Reject();

            } else if (val == 8) {
                break;
            } else {
                System.out.println("Wrong input\n");
            }

        }

    }
}

//18-09-2022 17:00:00 18-09-2022 17:02:00 18-09-2022 17:04:00 19-09-2022 17:06:00