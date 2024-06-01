public class App {
    public static void main(String[] args) throws Exception {
        Girl g1 = new Girl("Ha", new Sweater());
        g1.showOutfit();
        g1.setOutfit(new Tshirt());
        g1.showOutfit();
    }
}
