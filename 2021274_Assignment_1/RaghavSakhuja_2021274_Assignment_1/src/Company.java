import java.util.*;
public class Company {
    public String Name;
    public String Role;
    public int Package;
    public int CGPAcriteria;
    public Date RegisterationDate;
    public boolean registered;

    public ArrayList<Student> registeredstuds;
    public ArrayList<Student> offeredstuds;


    Company(String Name,String Role,int Package,int cgpa){
        this.Name=Name;
        this.Role=Role;
        this.Package=Package;
        this.CGPAcriteria=cgpa;
        this.RegisterationDate=new Date();
        this.registered=false;
        this.registeredstuds=new ArrayList<>();
        this.offeredstuds=new ArrayList<>();
    }
    public void updateRole(String role){
        this.Role=role;
    }


    public void updatePackage(int pack) {
        this.Package=pack;
    }

    public void updateCGPA(int cgpa) {
        this.CGPAcriteria=cgpa;
    }

    public void Register(Date date) {
        this.RegisterationDate=date;
        this.registered=true;
    }
}
