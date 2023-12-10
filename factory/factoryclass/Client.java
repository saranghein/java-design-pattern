package factory.factoryclass;

public class Client {
    public static void main(String[]args){
        SimplePizzaFactory simplePizzaFactory=new SimplePizzaFactory();
        PizzaStore pizzaStore=new PizzaStore(simplePizzaFactory);

        Pizza pizza=pizzaStore.orderPizza("cheese");
        System.out.println("We ordered a "+pizza.getName());
    }
}
