import java.util.ArrayList;
import java.util.Collections;

public class Category {
    private String Name;

    private int id;
    private ArrayList<Products> products;

    Category(String name,int id){
        this.Name=name;
        this.id=id;
        products=new ArrayList<Products>(Collections.nCopies(100, (Products) null));
    }
    public int getprosize(){
        int count=0;
        for(int i=0;i<100;i++){
            if(products.get(i)!=null){
                count++;
            }
        }
        return count;
    }
    public Products getpro(int i){
        return products.get(i);
    }
    public String getname(){
        return Name;
    }
    public int getid() {
        return id;
    }
    public void addproducts(Products p) {
        products.set(p.getid(),p);
    }
    public void deleteproduct(int j) {
        products.remove(j);
    }

}
