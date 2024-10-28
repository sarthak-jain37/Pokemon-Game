import java.util.List;
public class Pokemon {
    private final String name;
    private final Type type;
    private final List<Move> moves;
    private final Stat atk;
    private final Stat def;
    private final Stat speed;
    private final int lvl;
    private int hp;

    public Pokemon(String name, List<Move> moves, Stat atk, Stat def, Stat speed, Type type, int lvl, int hp){
        this.name = name;
        this.moves = moves;
        this.type = type;
        this.atk = atk;
        this.def = def;
        this.speed = speed;
        this.lvl = lvl;
        this.hp = hp;
    }

    

    public void display(){
        System.out.println("\nName: "+name);
        System.out.print("Moves:");
        for(Move m : moves) {
            System.out.print("  " +m.getName());
        }
        System.out.println();
        System.out.println("Type: "+type);
        System.out.println("HP: "+hp);
        System.out.println(atk.getType() + " : " + atk.getValue());
        System.out.println(def.getType() + " : " + def.getValue());
        System.out.println(speed.getType() + " : " + speed.getValue());
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Type getType() {
        return type;
    }

    public int getHP(){
        return hp;
    }

    public int getLvl() {
        return lvl;
    }

    public Stat getAtk(){
        return atk;
    }

    public Stat getDef() {
        return def;
    }

    public Stat getSpeed() {
        return speed;
    }

    public void reduceHP(int damage){
        hp -= damage;
    }

    public void fainted(){
        System.out.println(name + " fainted.");
    }
}
