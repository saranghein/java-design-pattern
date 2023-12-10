package factory.abstractfactory;

public class Client {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        //PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza + "\n");
//        pizza = chicagoStore.orderPizza("clam");
//        System.out.println("Joel ordered a " + pizza + "\n");

    }
}