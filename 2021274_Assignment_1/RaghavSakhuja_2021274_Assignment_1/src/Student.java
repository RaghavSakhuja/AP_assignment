import java.util.*;

public class Student {
    public String Name;
    public String RollNo;
    public double CGPA;
    public String Branch;
    public String status; /* applied, un-applied, offered, un-offered, blocked */
    public Date registerationDate;
    public ArrayList<Company> offers;
    public Company accepted;
    public boolean ifaccepted;

    Student(String name,String rollNo, double cgpa, String branch){
        this.Name=name;
        this.Branch=branch;
        this.CGPA=cgpa;
        this.RollNo=rollNo;
        this.status="not-applied";
        this.registerationDate=new Date();
        this.offers=new ArrayList<>();
        this.ifaccepted=false;
    }
    public void registerForCompany(PlacementCell Pcell,String company){

        int Pakage=0;
        for(int i=0;i<offers.size();i++){
            if(offers.get(i).Package>Pakage){
                Pakage=offers.get(i).Package;
            }
        }
        int flag=0;
        if(status.equals("blocked")){
            System.out.println("You are blocked, you can't register");
            return;
        }
        else if(status.equals("un-applied")){
            System.out.println("You haven't applied for registration. ");
            return;
        }
        else if(status.equals("offered")) {
            for (int i = 0; i < Pcell.companies.size(); i++) {
                Company comp = Pcell.companies.get(i);
                if (comp.Name.equals(company)) {
                    if (comp.registered) {
                        if (CGPA >= comp.CGPAcriteria && (Pakage * 3) <= comp.Package) {
                            comp.registeredstuds.add(this);
                            flag++;
                            System.out.println("You have successfully registered for " + comp.Name);
                        } else {
                            System.out.println("Not qualified");
                            return;
                        }
                    } else {
                        System.out.println("Company not registered");
                    }
                }
            }
        }
        else{
            for(int i=0;i<Pcell.companies.size();i++) {
                Company comp = Pcell.companies.get(i);
                if (comp.Name.equals(company)) {
                    if (comp.registered) {
                        if (CGPA >= comp.CGPAcriteria) {
                            comp.registeredstuds.add(this);
                            flag++;
                            System.out.println("You have successfully registered for " + comp.Name);
                        } else {
                            System.out.println("Not qualified");
                            return;
                        }
                    } else {
                        System.out.println("Company not registered");

                    }
                }
            }
        }
        if(flag==0){
            System.out.println("No such Company available");
        }


    }
    public void getCurrentStatus(){
        System.out.println(this.status);
        if(status.equals("offered")){
            for(int i=0;i<offers.size();i++){
                System.out.println("You have received offer from "+offers.get(i).Name);
            }
        }
    }
    public void getAllCompanies(PlacementCell Pcell){

        int Pakage=0;
        for(int i=0;i<offers.size();i++){
            if(offers.get(i).Package>Pakage){
                Pakage=offers.get(i).Package;
            }
        }

        int flag=0;
        if(status.equals("blocked")){
            flag=0;
        }
        else if(status.equals("un-applied")){
            flag=0;
        }
        else if(status.equals("offered")){
            for(int i=0;i<Pcell.companies.size();i++) {
                Company comp = Pcell.companies.get(i);
                if (CGPA >= comp.CGPAcriteria && (Pakage*3)<=comp.Package) {
                    System.out.println("Company Name: "+comp.Name);
                    System.out.println("Company Role offered: "+comp.Role);
                    System.out.println("Company Package: "+comp.Package);
                    System.out.println("Company Criteria: "+comp.CGPAcriteria);
                    flag++;
                } else {
                    System.out.println("Not qualified");
                    return;
                }
            }
        }
        else{
            for(int i=0;i<Pcell.companies.size();i++) {
                Company comp = Pcell.companies.get(i);
                if (CGPA >= comp.CGPAcriteria) {
                    System.out.println("Company Name: "+comp.Name);
                    System.out.println("Company Role offered: "+comp.Role);
                    System.out.println("Company Package: "+comp.Package);
                    System.out.println("Company Criteria: "+comp.CGPAcriteria);
                    flag++;
                } else {
                    System.out.println("Not qualified");
                    return;
                }
            }
        }
        if(flag==0){
            System.out.println("No Company available");
        }

    }


    public void registerForDrive(Date date) {
        this.registerationDate=date;
        this.status="applied";

        System.out.println("You have successfully registered!!\nYour details are: ");
        System.out.println("Name: "+Name);
        System.out.println("Branch: "+Branch);
        System.out.println("CGPA: "+CGPA);
        System.out.println("RollNo: "+RollNo);

    }

    public void Accept() {
        if(this.offers.size()!=0) {
            Company max = offers.get(0);
            int j = 0;
            for (int i = 1; i < offers.size(); i++) {
                if (offers.get(i).Package > max.Package) {
                    max = offers.get(i);
                    j = i;
                }
            }
            this.accepted = max;
            this.ifaccepted=true;
            offers.remove(j);
            System.out.println("The offer from " + max.Name + " is accepted.");
        }
        else {
            System.out.println("No offer received yet. ");
        }
    }

    public void Reject() {
        if (this.offers.size() != 0) {

            Company max = offers.get(0);
            int j = 0;
            for (int i = 1; i < offers.size(); i++) {
                if (offers.get(i).Package > max.Package) {
                    max = offers.get(i);
                    j = i;
                }
            }
            offers.remove(j);
            System.out.println("The offer from " + max.Name + "accepted.");
            if (offers.size() == 0) {
                this.status = "blocked";
            }
        } else {
            System.out.println("No offer received yet. ");
        }

    }
}

