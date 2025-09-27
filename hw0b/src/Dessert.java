public class Dessert {
    private int flavor,name;
    private static int numDesserts=0;

    public Dessert(int flavor,int name ){
        this.flavor=flavor;
        this.name=name;
        numDesserts++;
    }
    public void printDessert(){
        System.out.println(flavor+" "+name+" "+numDesserts);
    }

    public static void main(String[] args) {

        System.out.println("I love dessert!");
    }
}
