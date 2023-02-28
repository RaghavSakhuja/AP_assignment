import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Collections.sort;


class Pair{
    Object fisrt;
    Integer second;

    Pair(Products p,Integer q){
        fisrt=p;
        second=q;
    }

    Pair(Deal d,Integer q){
        fisrt=d;
        second=q;
    }
}
public class Customer {
    private String Name;
    private String password;
    private int status;
    private double wallet;
    private ArrayList<Double> coupons;
    private ArrayList<String> notification;
    private ArrayList<Pair> productcart;
    private ArrayList<Pair> dealcart;

    /*************************
     * *Utility*
     * **********************/
    Customer(String name, String pwd) {
        this.Name = name;
        this.password = pwd;
        coupons=new ArrayList<>();
        notification = new ArrayList<>();
        productcart = new ArrayList<Pair>();
        dealcart = new ArrayList<Pair>();
        status = 0;
        wallet = 1000;
    }

    public String getname() {
        return this.Name;
    }

    public String getpwd() {
        return this.password;
    }

    public void showcoupons() {
        System.out.println("You have the coupons with following values: ");
        for (int i = 0; i < coupons.size(); i++) {
            System.out.println((i + 1) + "). " + coupons.get(i) + "%");
        }
    }

    public double getwalletvalue() {
        return wallet;
    }

    public void shownotifications() {
        for (int i = 0; i < notification.size(); i++) {
            System.out.println(notification.get(i));
        }
    }
    public int getstatus() {
        return status;
    }

    public void viewcart() {
        for (int i = 0; i < productcart.size(); i++) {
            Products p = (Products) productcart.get(i).fisrt;
            int q = productcart.get(i).second;
            System.out.println(p.getname() + " : " + q);
        }
        System.out.println();
        for (int i = 0; i < dealcart.size(); i++) {
            Deal p = (Deal) dealcart.get(i).fisrt;
            int q = dealcart.get(i).second;
            System.out.println(p.getp1().getname() + " + " + p.getp2().getname() + " : " + q);
        }
    }

    /*************************************
     deleting
     ************************************/
    public void deletepro(Products p) {
        for (int i = 0; i < productcart.size(); i++) {
            if (productcart.get(i).fisrt == p) {
                productcart.remove(i);
            }
        }
        this.notification.add(p.getname() + " has gone out of stock. ");
    }

    public void deletedeal(Deal d) {
        for (int i = 0; i < dealcart.size(); i++) {
            if (dealcart.get(i).fisrt == d) {
                dealcart.remove(i);
            }
        }
        this.notification.add("deal " + d.getid() + " is no longer valid. ");
    }

    /**********************
     * adding
     * ******************************/
    public void addproducts(Products p, int q) {
        if (p.getQuantity() < q) {
            System.out.println("Not enough products in stock");
        } else {
            Pair pp = new Pair(p, q);
            productcart.add(pp);
        }
    }

    public void adddeals(Deal d, int t) {
        Products p1 = d.getp1();
        Products p2 = d.getp2();
        if (p1.getQuantity() < t || p2.getQuantity() < t) {
            System.out.println("Not enough products in stock");
        } else {
            Pair pp = new Pair(d, t);
            dealcart.add(pp);
        }


    }

    public void addmoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount you wish to add:");
        int c = sc.nextInt();
        sc.nextLine();
        if (c >= 0) {
            wallet += c;
        } else {
            System.out.println("You cannot withdraw money. ");
        }
    }

    public void emptycart() {
        productcart.clear();
        dealcart.clear();
    }

    public void checkoutcart() {
        double value = 0;
        double minv = 100;
        boolean flag = true;
        ArrayList<Pair> used = new ArrayList<Pair>();
        for (int i = 0; i < productcart.size(); i++) {
            Products p = (Products) productcart.get(i).fisrt;
            int quantity = productcart.get(i).second;
            if (p.getQuantity() >= quantity) {
                p.reducequantity(quantity);
                double cost = p.getprice();
                if(coupons.size()==0){
                    coupons.add(0.0);
                }
                double discount = max(p.getdiscount(this), coupons.get(0));
                minv = min(minv, discount);
                value += (cost-(cost * discount / 100)) * quantity;
                if (minv == coupons.get(0)) {
                    coupons.remove(0);
                }
                used.add(productcart.get(i));

            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < dealcart.size(); i++) {
                Deal d = (Deal) dealcart.get(i).fisrt;

                Products p1 = d.getp1();
                Products p2 = d.getp2();
                int quantity = dealcart.get(i).second;
                if (p1.getQuantity() >= quantity && p2.getQuantity() >= quantity) {
                    double cost = d.getprice();
                    p1.reducequantity(quantity);
                    p2.reducequantity(quantity);
                    value += (cost) * quantity;

                    Pair n1 = new Pair(p1, quantity);
                    Pair n2 = new Pair(p2, quantity);
                    used.add(n1);
                    used.add(n2);
                } else {
                    flag = false;
                    break;
                }

            }
        }
        if (flag) {
            if (value > wallet) {
                System.out.println("Not enough funds in your wallet");
                for (int i = 0; i < used.size(); i++) {
                    Products p = (Products) used.get(i).fisrt;
                    int quant = used.get(i).second;
                    p.addquantity(quant);
                }

            } else {
                System.out.println("Details of you cart are");
                viewcart();
                double delivery = 0;
                int days = 0;

                if (value >= 5000) {
                    if (status == 0) {

                    } else if (status == 1) {
                        int n = (int) (Math.random() * (2) + 1);
                        for (int i = 0; i < n; i++) {
                            double c = Math.random() * (10) + 5;
                            coupons.add(c);
                            System.out.println("you have received a" + c + "% off coupon ");
                        }
                    } else if (status == 2) {
                        int n = (int) (Math.random() * (2) + 3);
                        for (int i = 0; i < n; i++) {
                            double c = (int)(Math.random() * (10) + 5);
                            coupons.add(c);
                            System.out.println("you have received a " + c + "% off coupon ");
                        }
                    }
                }
                if (status == 0) {
                    delivery = 100 + (0.05 * value);
                    days = (int) (Math.random() * (4) + 7);
                } else if (status == 1) {
                    delivery = 100 + (0.02 * value);
                    days = (int) (Math.random() * (4) + 3);

                } else if (status == 2) {
                    int n = (int) (Math.random() * (11));
                    if (n == 7) {
                        System.out.println("You have received the free surprise from Flipzone");
                    }
                    delivery = 100;
                    days = 2;
                }
                wallet-=value;
                wallet-=delivery;
                emptycart();
                sort(coupons);
                System.out.println("Your order has been placed for Rs."+value+".\nYour delivery charger are Rs. " + delivery + ". Your order will be delivered in " + days + " days.");

            }

        } else {
            System.out.println("the products in your cart have gone out of stock. ");
            for (int i = 0; i < used.size(); i++) {
                Products p = (Products) used.get(i).fisrt;
                int quant = used.get(i).second;
                p.addquantity(quant);
            }

        }
    }

    public void updatestatus() {
        Scanner sc = new Scanner(System.in);

        if (status == 0) {
            System.out.println("update to elite or prime?");
            String a = sc.nextLine();
            if (a.equals("elite")) {
                if (wallet >= 300) {
                    status = 2;
                    wallet -= 300;
                } else {
                    System.out.println("Not enough fund in the wallet");
                }
            } else if (a.equals("prime")) {
                if (wallet >= 200) {
                    wallet -= 200;
                    status += 1;
                } else {
                    System.out.println("Not enough fund in the wallet");
                }
            }

        } else if (status == 1) {
            if (wallet >= 100) {
                status = 2;
                wallet -= 100;
            } else {
                System.out.println("Not enough fund in the wallet");
            }

        }
    }


}