import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlacementCell {

    public ArrayList<Student> students;
    public ArrayList<Company> companies;
    public Date OpeningStudentReg;
    public Date EndingStudentReg;
    public Date OpeningCompanyReg;
    public Date EndingCompanyReg;


    PlacementCell(){
        students=new ArrayList<>();
        companies=new ArrayList<>();
        OpeningCompanyReg=new Date();
        OpeningStudentReg=new Date();
        EndingCompanyReg=new Date();
        EndingStudentReg=new Date();
    }
    public void OpenStudReg(Date osr,Date esr){
        OpeningStudentReg=osr;
        EndingStudentReg=esr;
    }
    public void OpenCompReg(Date odate, Date edate) {
        OpeningCompanyReg=odate;
        EndingCompanyReg=edate;
    }

    public void updateCGPA(Student student,double cgpa) {
        student.CGPA=cgpa;
    }


    public void getNumberofstudents() {
        int StudentNum = this.students.size();
        System.out.println("There are " + StudentNum + " students");
    }

    public void getNumberofcompanies() {
        int CompanyNum = this.companies.size();
        int count=0;
        for(int i=0;i<CompanyNum;i++){
            if(companies.get(i).registered){
                count++;
            }
        }
        System.out.println("There are " + count + " Registered Companies");

    }

    public void getNumberofstudents_status() {
        int applied = 0, un_applied = 0, offered = 0, un_offered = 0, blocked = 0;
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).status.equals("applied")) {
                applied++;
            } else if (this.students.get(i).status.equals("un-applied")) {
                un_applied++;
            } else if (this.students.get(i).status.equals("offered")) {
                offered++;
            } else if (this.students.get(i).status.equals("un-offered") ){
                un_offered++;
            } else if (this.students.get(i).status.equals("blocked")) {
                blocked++;
            }
        }
        System.out.println("There are :\n" +
                applied + " applied\n" +
                un_applied + " un-applied\n" +
                offered + " offered\n" +
                un_offered + " un-offered\n" +
                blocked + " blocked\n");

    }

    public void OpenStudentRegistration() {
        while (true) {
            System.out.println("Fill in the details:-\n" +
                    "\t1) Set the Opening time for Student registrations.(in format dd-mm-yyyy HH:mm:ss)\n" +
                    "\t2) Set the Ending time for Student registrations.(in format dd-mm-yyyy HH:mm:ss)\n");
            Scanner sc = new Scanner(System.in);
            String Date1 = sc.nextLine();
            String Date2 = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                Date Odate = format.parse(Date1);
                Date Edate = format.parse(Date2);
                if(Odate.compareTo(EndingCompanyReg)>0){
                    if (Odate.compareTo(Edate) < 0) {
                        this.OpenStudReg(Odate, Edate);
                        break;
                    } else {
                        System.out.println("Can't End Registration before Opening it!\n");
                    }
                }
                else{
                    System.out.println("Can't Start student Registration before Company Registration Ends!\n");
                }
            } catch (ParseException e) {
                System.out.println("Wrong input format\n");
            }
        }


    }

    public void OpenCompanyRegistration() {
        while (true) {
            System.out.println("Fill in the details:-\n" +
                    "\t1) Set the Opening time for company registrations.(in format dd-mm-yyyy HH:mm:ss)\n" +
                    "\t2) Set the Closing time for company registrations.(in format dd-mm-yyyy HH:mm:ss)\n");
            Scanner sc = new Scanner(System.in);
            String Date1 = sc.nextLine();
            String Date2 = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            try {
                Date Odate = format.parse(Date1);
                Date Edate = format.parse(Date2);
                if (Odate.compareTo(Edate) < 0) {
                    this.OpenCompReg(Odate, Edate);
                    break;
                } else {
                    System.out.println("Can't End Registration before Opening it!\n");
                }
            } catch (ParseException e) {
                System.out.println("Wrong input format\n");
            }
        }
    }

    public void getStudentDetails() {
        for (int i = 0; i < this.students.size(); i++) {
            Student S = this.students.get(i);
            System.out.println("Name: " + S.Name +
                    "\nRollNo: " + S.RollNo +
                    "\nBranch: " + S.Branch +
                    "\nCGPA: " + S.CGPA);
            if(S.ifaccepted){
                System.out.println("Accepted offer from"+S.accepted.Name);
            }
            else{
                System.out.println("Has applied for: ");{
                    for(int j=0;j<this.companies.size();j++){
                        if(this.companies.get(j).registeredstuds.contains(S)){
                            System.out.println(this.companies.get(j).Name);
                        }
                    }
                }
                System.out.println("Has received offers from: ");{
                    for(int j=0;j<this.companies.size();j++){
                        if(this.companies.get(j).offeredstuds.contains(S)){
                            System.out.println(this.companies.get(j).Name);
                        }
                    }
                }
            }
        }

    }

    public void getCompaniesDetails() {
        for (int i = 0; i < this.companies.size(); i++) {
            Company C = this.companies.get(i);
            System.out.println("Company Name: " + C.Name +
                    "\nRollNo Offered: " + C.Role +
                    "\nPackage Offered: " + C.Package +
                    "\nCGPA Criteria: " + C.CGPAcriteria);
            System.out.println("The students offered a package are: ");
            for (int j = 0; j < C.offeredstuds.size(); j++) {
                Student S = this.students.get(i);
                System.out.println("Name: " + S.Name +
                        "\nRollNo: " + S.RollNo +
                        "\nBranch: " + S.Branch +
                        "\nCGPA: " + S.CGPA);
            }
            if (C.offeredstuds.size() == 0) {
                System.out.println("None");
            }
        }
    }

    public void getAveragePackage() {
        double sum=0;
        int count=0;
        for(int i=0;i<this.students.size();i++){
            if(this.students.get(i).ifaccepted){
                sum+=this.students.get(i).accepted.Package;
                count++;
            }
        }
        double avg=sum/count;
        System.out.println("The average package is: "+avg+" LPA.");

    }

    public void getCompanyProcessResult(Company company) {
        int num=company.registeredstuds.size();
        ArrayList<Student> s=company.registeredstuds;
        int selected=(int)(Math.random()*(num+1)),i=0;
        System.out.println("Following students are selected: ");
        while(i!=selected){
            int a=(int)Math.random()*(num);
            if(company.offeredstuds.contains(s.get(a))){

            }
            else{
                Student stud=s.get(a);
                company.offeredstuds.add(stud);
                stud.offers.add(company);
                i++;
                System.out.println((i)+")\tName: "+stud.Name+"\n\tRollno: "+stud.RollNo+"\n\tCGPA: "+stud.CGPA+"\n\tBranch: "+ stud.Branch);
                stud.status="offered";
            }
        }
        if(i==0){
            System.out.println("None");
        }
    }
}
