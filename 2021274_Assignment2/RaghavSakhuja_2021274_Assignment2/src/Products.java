import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.max;

public class Products {
    protected double price;
    protected int id;
    protected String Name;

    private int quantity;
    private ArrayList<String> attributes;

    private ArrayList<Double> discount;

    Products(){
        attributes=new ArrayList<String>();
    }
    Products(int id,String name,double price,int quantity) {
        this.id=id;
        this.Name=name;
        this.price=price;
        this.quantity=quantity;
        attributes = new ArrayList<String>();
        discount =new ArrayList<Double>();
        discount.add(0.0);
        discount.add(5.0);
        discount.add(10.0);
    }

    public String getname() {
        return Name;
    }

    public int getid() {
        return id;
    }
    public void addattribute(String at){
        attributes.add(at);
    }
    @Override
    public String toString() {
        return "Prduct id: "+this.id+
                "\nName: "+ this.Name+
                "\nPrice "+this.price+
                "\nQuantity: "+this.quantity+"\n"+Arrtostring();

    }
    private String Arrtostring(){
        String a="";
        for(int i=0;i<attributes.size();i++){
            a+=attributes.get(i)+"\n";
        }
        return a;
    }
    public void adddiscount(double elite,double prime,double normal) {

        discount.set(0,max(0,normal));
        discount.set(1,max(5,prime));
        discount.set(2,max(10,elite));
    }
    public double getelitevalue(){

        return price-(price*discount.get(2)/100);

    }
    public double getprimevalue(){

        return price-(price*discount.get(1)/100);


    }
    public double getnormalvalue(){

        return price-(price*discount.get(0)/100);

    }
    public int getQuantity(){

        return quantity;

    }
    public double getprice() {
        return price;
    }
    public double getdiscount(Customer c) {
        return discount.get(c.getstatus());
    }
    public void reducequantity(int quantity) {
        this.quantity-=quantity;
    }
    public void addquantity(int quant) {
        this.quantity+=quant;
    }
}
