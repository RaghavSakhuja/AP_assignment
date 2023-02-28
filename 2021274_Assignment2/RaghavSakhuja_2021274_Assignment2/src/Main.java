import java.util.Scanner;

public class Main {

    static Admin store;

    public static void main(String[] args) {
        store = new Admin();
        System.out.println("WELCOME TO FLIPZON");
        menu();
    }
    public static void menu(){
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("1) Enter as Admin\n" +
                    "2) Explore the Product Catalog\n" +
                    "3) Show Available Deals\n" +
                    "4) Enter as Customer\n" +
                    "5) Exit the Application\n");
            String val=sc.nextLine();
            if(val.equals("1")){
                String Name;
                String RollNo;
                System.out.println("Enter the name of the admin: ");
                Name=sc.nextLine();
                System.out.println("Enter the Password: ");
                RollNo=sc.nextLine();
                if(Name.equals("Raghav Sakhuja") && RollNo.equals("2021274")){
                    adminmenu();
                }

            }
            else if(val.equals("2")){
                browseproducts();
            }
            else if(val.equals("3")){
                browsedeals();

            }
            else if(val.equals("4")){
                customermenu();
            }
            else if(val.equals("5")){
                break;
            }
            else{
                System.out.println("Wrong input");
            }
        }
    }
    private static void customermenu() {
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("1) Sign up\n" +
                    "2) Log in\n" +
                    "3) Back");
            String val = sc.nextLine();
            if (val.equals("1")) {
                System.out.println("Enter your name: ");
                String name=sc.nextLine();
                System.out.println("Enter your password: ");
                String pwd=sc.nextLine();
                store.addcustomers(name,pwd);
            } else if (val.equals("2")) {
                System.out.println("Enter your name: ");
                String name=sc.nextLine();
                System.out.println("Enter your password: ");
                String pwd=sc.nextLine();
                Customer c=store.findcustomer(name,pwd);
                if(c!=null) {
                    customerspecificmenu(c);
                }
                else{
                    System.out.println("No such user exists");
                }
            } else if (val.equals("3")) {
                break;
            } else {
                System.out.println("Wrong input");
            }
        }
    }
    private static void customerspecificmenu(Customer c) {
        System.out.println("Welcone "+c.getname());
        c.shownotifications();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1) browse products\n" +
                    "2) browse deals\n" +
                    "3) add a product to cart\n" +
                    "4) add products in deal to cart\n" +
                    "5) view coupons\n" +
                    "6) check account balance\n" +
                    "7) view cart\n" +
                    "8) empty cart\n" +
                    "9) checkout cart\n" +
                    "10) upgrade customer status\n" +
                    "11) Add amount to wallet\n" +
                    "12) back");

            String val = sc.nextLine();
            if (val.equals("1")) {
                browseproducts();
            } else if (val.equals("2")) {
                browsedeals();
            } else if (val.equals("3")) {
                Products p=store.findproduct();
                if(p!=null) {
                    System.out.println("Enter the quantity: ");
                    int t = sc.nextInt();
                    sc.nextLine();
                    c.addproducts(p, t);
                }
                else{
                    System.out.println("No such product exists");
                }
            } else if (val.equals("4")) {
                Deal d=store.finddeals();
                if(d!=null) {
                    System.out.println("Enter the quantity: ");
                    int t = sc.nextInt();
                    sc.nextLine();
                    c.adddeals(d, t);
                }
                else{
                    System.out.println("So such deal exists");
                }


            } else if (val.equals("5")) {
                c.showcoupons();
            } else if (val.equals("6")) {
                double w=c.getwalletvalue();
                System.out.println("Your wallet has Rs."+w);
            } else if (val.equals("7")) {
                c.viewcart();
            } else if (val.equals("8")) {
                c.emptycart();
            } else if (val.equals("9")) {
                c.checkoutcart();
            } else if (val.equals("10")) {
                c.updatestatus();
            } else if (val.equals("11")) {
                c.addmoney();
            } else if (val.equals("12")) {
                break;
            } else {

                System.out.println("Wrong Input");
            }
        }
    }
    public static void adminmenu(){
        System.out.println("Welcome Raghav!! ");
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("Please choose any one of the following actions:\n\n" +
                    "1) Add category\n" +
                    "2) Delete category\n" +
                    "3) Add Product\n" +
                    "4) Delete Product\n" +
                    "5) Set Discount on Product\n" +
                    "6) Add giveaway deal\n" +
                    "7) Add product stock\n" +
                    "8) Back");
            String val=sc.nextLine();
            if(val.equals("1")){
                System.out.println("Add name of the category");
                String name=sc.nextLine();
                System.out.println("Add id of the category");
                int id=sc.nextInt();
                sc.nextLine();
                store.addcategory(name,id);
            }
            else if(val.equals("2")){
                System.out.println("Input the name of the category");
                String name=sc.nextLine();
                store.deletecategory(name);
            }
            else if(val.equals("3")){
                Category c=store.findcategory();
                if(c==null){
                    System.out.println("No such category exists");
                }
                else{
                    store.addproduct(c);
                }
            }
            else if(val.equals("4")){
                Category c=store.findcategory();
                if(c==null){
                    System.out.println("No such category exists");
                }
                else{
                    store.deleteproduct(c);
                }
            }
            else if(val.equals("5")){
                store.setdicsount();
            }
            else if(val.equals("6")){
                store.adddeals();
            }
            else if(val.equals("7")){
                store.addproductstock();
            }
            else if(val.equals("8")){
                break;
            }
            else{
                System.out.println("Wrong input");
            }
        }
    }
    private static void browseproducts(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (store.getcatsize() > 0) {
                System.out.println("Categories are: ");
                for (int i = 0; i < 100; i++) {
                    Category c = store.getcate(i);
                    if(c!=null) System.out.println(c.getid() + "). " + c.getname());
                }
                System.out.println("Chose the category you wish to browse: ");
                int i = sc.nextInt();
                sc.nextLine();
                if (i < 100 && i >= 0 && store.getcate(i)!=null) {
                    Category c = store.getcate(i);
                    System.out.println("Products are: ");
                    for (int j = 0; j < 100; j++) {
                        Products p = c.getpro(j);
                        if(p!=null)System.out.println(p.getid() + "). " + p.getname());
                    }
                    System.out.println("Chose the product you wish to see: ");
                    int pro = sc.nextInt();
                    sc.nextLine();
                    if (pro < 100 && pro >= 0 && c.getpro(pro)!=null) {
                        Products p = c.getpro(pro);
                        System.out.println(p);
                    } else {
                        System.out.println("Wrong input");
                        break;
                    }
                } else {
                    System.out.println("Wrong input");
                    break;
                }
            }
            else {
                System.out.println("no products are available yet. ");
                break;
            }
        }

    }
    private static void browsedeals() {
        if(store.getdealsize()>0) {
            for (int i = 0; i < 100; i++) {
                if(store.getdeal(i)!=null) {
                    System.out.println(store.getdeal(i));
                    System.out.println();
                }
            }
        }
        else{
            System.out.println("no deals are available yet. ");
        }
    }

}