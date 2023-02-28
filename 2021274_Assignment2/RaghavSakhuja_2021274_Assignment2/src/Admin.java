import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Admin {
    private ArrayList<Category> categories;

    private ArrayList<Customer> customers;
    private ArrayList<Deal> deals;

    Admin() {
        categories = new ArrayList<Category>(Collections.nCopies(100, (Category) null));
        customers = new ArrayList<Customer>();
        deals = new ArrayList<Deal>(Collections.nCopies(100, (Deal) null));
    }

    /*************************
     * utility
     * ********************/

    public int getcatsize() {
        int count=0;
        for(int i=0;i<100;i++){
            if(categories.get(i)!=null){
                count++;
            }
        }
        return count;
    }
    public Category getcate(int i) {
        return this.categories.get(i);
    }
    public int getdealsize() {
        int count=0;
        for(int i=0;i<100;i++){
            if(deals.get(i)!=null){
                count++;
            }
        }
        return count;

    }

    public Deal getdeal(int i) {
        return this.deals.get(i);
    }


    /****************************************************
    adding things
    ******************************************************/

    public void addcategory(String name, int id) {
        int flag = 0;
        if (categories.get(id) != null) {
            flag++;
        }
        if (flag == 0) {
            Category c = new Category(name, id);
            categories.set(id,c);
            System.out.println("Category added");
            System.out.println("Now to add a product: ");
            System.out.println();
            addproduct(c);
        } else {
            System.out.println("Category  with this id already exists");
        }
    }

    public void addproduct(Category c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Input the product name: ");
        String name = sc.nextLine();
        System.out.println("Input the product id: ");
        String id = sc.nextLine();
        System.out.println("Input the quantity of the product: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        String[] brokenid = id.split("\\.", 2);
        try {
            int cid = Integer.parseInt(brokenid[0]), pid = Integer.parseInt(brokenid[1]);
            if (c.getid() == cid) {
                if (c.getpro(pid) == null) {
                    Products p = new Products(pid, name, price, quantity);

                    System.out.println("Input the number of attributes");
                    int n = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Now input the attributes. ");
                    for (int j = 0; j < n; j++) {
                        String t = sc.nextLine();
                        p.addattribute(t);
                    }
                    c.addproducts(p);
                    System.out.println("Product added\n");
                } else {
                    System.out.println("product with this id already exists");
                }
            }
            else {
                System.out.println("product id has wrong category id");
            }

        }catch (NumberFormatException exception){
            System.out.println("Wrong input format");
        }
    }

    public void adddeals() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the 2 products you wish to input in the deal");
        Products p1 = findproduct();
        Products p2 = findproduct();
        if (p1 != null && p2 != null) {
            System.out.println("Enter the combined price for elite,prime and normal(Should be less than their combined price):");
            double elite, prime, normal;
            elite = sc.nextDouble();
            prime = sc.nextDouble();
            normal = sc.nextDouble();
            System.out.println("Input the deal id:");
            int did = sc.nextInt();
            sc.nextLine();
            if (deals.get(did) == null) {
                if ((elite > (p1.getelitevalue() + p2.getelitevalue()))
                        || (prime > (p1.getprimevalue() + p2.getprimevalue()))
                        || (normal > (p1.getnormalvalue() + p2.getnormalvalue()))) {
                    System.out.println("value should be less than price");
                } else {
                    Deal d = new Deal(p1, p2, elite, prime, normal,did);
                    deals.set(d.getid(),d);
                    System.out.println("Deal created\n");
                }
            } else {
                System.out.println("deal with this id already exists");
            }
        }
    }

    public void addcustomers(String name, String pwd) {
        Customer c = new Customer(name, pwd);
        customers.add(c);
        System.out.println("Customer added\n");
    }
    public void addproductstock() {
        Scanner sc = new Scanner(System.in);

        Products p=findproduct();
        if(p!=null){
            System.out.println("Enter the quantity you wish to add: ");
            int quant=sc.nextInt();
            sc.nextLine();
            p.addquantity(quant);
            System.out.println("Stock added");
        }
    }
    public void setdicsount() {
        Scanner sc = new Scanner(System.in);
        Products p = findproduct();
        if (p != null) {
            System.out.println("Enter discount for Elite, Prime and Normal customers respectively (in % terms)");
            double elite, prime, normal;
            elite = sc.nextDouble();
            prime = sc.nextDouble();
            normal = sc.nextDouble();
            p.adddiscount(elite, prime, normal);
            System.out.println("Discount added");
        }

    }


    /************************************
     * Finding things
     * **************************************/
    public Category findcategory() {
        System.out.println("Input the id of category: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        return categories.get(id);
    }

    public Products findproduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the product id: ");
        String id = sc.nextLine();
        String[] brokenid = id.split("\\.", 2);
        int cid = Integer.parseInt(brokenid[0]), pid = Integer.parseInt(brokenid[1]);
        if (categories.get(cid) == null) {
            return null;
        } else {
            return categories.get(cid).getpro(pid);
        }
    }

    public Deal finddeals() {
        System.out.println("Input the id of deal: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        return deals.get(id);
    }

    public Customer findcustomer(String name, String pwd) {
        for (int i = 0; i < customers.size(); i++) {
            if (name.equals(customers.get(i).getname())) {
                if (pwd.equals(customers.get(i).getpwd())) {
                    return customers.get(i);
                } else {
                    System.out.println("Wrong password");
                    return null;
                }
            }
        }
        System.out.println("No user with this name. ");
        return null;
    }


    /********************************
     * deleting things*
     * ******************************/
    public void deletecategory(String name) {
        int flag = 0;
        Category c = null;
        for (int i = 0; i < categories.size(); i++) {
            if (name.equals(categories.get(i).getname())) {
                c = categories.get(i);
                for (int k = 0; k < 100; k++) {
                    if (c.getpro(k) != null) ;
                    {
                        deleteproduct(c, 0);
                    }
                }
                categories.set(i, null);
                flag++;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Category " + name + " is deleted");
        } else {
            System.out.println("No such category exists");
        }
    }

    public void deleteproduct(Category c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the product id: ");
        String id = sc.nextLine();
        String[] brokenid = id.split(".", 2);
        int cid = Integer.parseInt(brokenid[0]), pid = Integer.parseInt(brokenid[1]);
        if (categories.get(cid) == null) {
            System.out.println("No product with this id exists");
        } else {
            if (categories.get(cid).getpro(pid) == null) {
                System.out.println("No product with this id exists");
            } else {
                Products deletedpro = c.getpro(pid);
                c.deleteproduct(pid);
                deletefromcart(deletedpro);
                for (int i = 0; i < deals.size(); i++) {
                    if (deals.get(i) != null) {
                        Products p1 = deals.get(i).getp1();
                        Products p2 = deals.get(i).getp2();
                        if (p1 == deletedpro || p2 == deletedpro) {
                            deletedeals(i);
                        }

                    }
                }
                if (c.getprosize() == 0) {
                    System.out.println("The category is now empty. Do you wish to delete it?(y/n) ");
                    String l = sc.nextLine();
                    if (l.equals("y")) {
                        addproduct(c);
                    } else {
                        deletecategory(c.getname());
                    }
                }
            }
        }
    }

    public void deleteproduct(Category c, int j) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the product id: ");
        String id = sc.nextLine();
        String[] brokenid = id.split(".", 2);
        int cid = Integer.parseInt(brokenid[0]), pid = Integer.parseInt(brokenid[1]);
        if (categories.get(cid) == null) {
            System.out.println("No product with this id exists");
        } else {
            if (categories.get(cid).getpro(pid) == null) {
                System.out.println("No product with this id exists");
            } else {
                Products deletedpro = c.getpro(pid);
                c.deleteproduct(pid);
                deletefromcart(deletedpro);
                for (int i = 0; i < deals.size(); i++) {
                    if (deals.get(i) != null) {
                        Products p1 = deals.get(i).getp1();
                        Products p2 = deals.get(i).getp2();
                        if (p1 == deletedpro || p2 == deletedpro) {
                            deletedeals(i);
                        }
                    }
                }
            }
        }
    }

    private void deletefromcart(Products p) {
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            c.deletepro(p);
        }
    }

    private void deletefromcart(Deal d) {
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            c.deletedeal(d);
        }
    }

    private void deletedeals(int id) {
        Deal d = deals.get(id);
        deletefromcart(d);
        if (deals.contains(d)) {
            deals.set(id, null);
        }

    }

}