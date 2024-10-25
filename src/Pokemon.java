public class Pokemon {
    private final String name;
    private final String type;
    private final String moves[];
    private final int basePower[];
    private double advantage;
    private final int lvl;
    private int hp;
    private final int atk;
    private final int def;
    private final int speed;

    public Pokemon(String name, String moves[], String type, int lvl, int basePower[], int hp, int atk, int def, int speed){
        this.name = name;
        this.moves = moves;
        this.type = type;
        this.lvl = lvl;
        this.basePower = basePower;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
    }

    

    public void display(){
        System.out.println("\nName: "+name);
        System.out.print("Moves:");
        for(String m : moves) {
            System.out.print("  " +m);
        }
        System.out.println();
        System.out.println("Type: "+type);
        System.out.println("HP: "+hp);
        System.out.println("Attack: "+atk);
        System.out.println("Defense: "+def);
        System.out.println("Speed: "+speed);
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public String[] getMoves() {
        return moves;
    }

    public int[] getBasePower() {
        return basePower;
    }

    public String getType() {
        return type;
    }

    public int getHP() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLvl() {
        return lvl;
    }

    public double getAdvantage() {
        return advantage;
    }

    public void setAdvantage(Pokemon opponent) {
        this.advantage = calculateAdvantage(this.type, opponent.getType());
    }

    private double calculateAdvantage(String playerType, String opponentType) {
        if (playerType.equals(opponentType)) {
            return 1.0;
        }
        return switch (playerType) {
            case "Fire" -> opponentType.equals("Water") ? 0.5 : 2.0;
            case "Water" -> opponentType.equals("Grass") ? 0.5 : 2.0;
            case "Grass" -> opponentType.equals("Fire") ? 0.5 : 2.0;
            default -> 1.0;
        }; 
    }

    public void reduceHP(int damage){
        hp -= damage;
    }

    public void fainted(){
        System.out.println(name + " fainted.");
    }
}
