public class Deal extends Products{
    private Products p1;
    private Products p2;

    private double elite;
    private double prime;
    private double normal;
    Deal(Products p1,Products p2, double elite,double prime, double normal,int id){
        this.p1=p1;
        this.p2=p2;
        this.elite=elite;
        this.prime=prime;
        this.normal=normal;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Deal id: "+this.id+
                "\nProduct 1: "+
                p1.getname()+"\nProduct 2: "+
                p2.getname()+"\nPrice for elite: "
                +this.elite+"\nPrice for prime: "
                +this.prime+"\nPrice for normal: "
                +this.normal;
    }

    public Products getp1() {
        return p1;
    }

    public Products getp2() {
        return p2;
    }
}

