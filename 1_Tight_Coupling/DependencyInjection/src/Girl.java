public class Girl {
    private String name;
    private IOutfit outfit;

    //contructer injection
    public Girl(String name, IOutfit outfit){ 
        this.name = name;
        this.outfit = outfit;
    }

    //setter injection
    public void setOutfit(IOutfit outfit) {
        this.outfit = outfit;
    }


    public void showOutfit(){
        System.out.println("Co gai " + this.name);
        outfit.wear();
    }
}
