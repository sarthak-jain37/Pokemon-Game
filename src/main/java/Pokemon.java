
import java.util.List;
import java.util.Random;

public class Pokemon {

    private static final Random RAND = new Random();

    private final String name;
    private final Type type;
    private final List<Move> moves;
    private final Stat atk;
    private final Stat def;
    private final Stat spatk;
    private final Stat spdef;
    private final Stat speed;
    private final int lvl;
    private int hp;

    public Pokemon(String name, List<Move> moves, Stat atk, Stat def, Stat spatk, Stat spdef, Stat speed, Type type, int lvl, int hp) {
        this.name = name;
        this.moves = moves;
        this.type = type;
        this.atk = atk;
        this.def = def;
        this.spatk = spatk;
        this.spdef = spdef;
        this.speed = speed;
        this.lvl = lvl;
        this.hp = hp;
    }

    public void display() {
        System.out.println("\nName: " + name);
        System.out.print("Moves:");
        for (Move m : moves) {
            System.out.print("  " + m.getName());
        }
        System.out.println();
        System.out.println("Type: " + type);
        System.out.println("HP: " + hp);
        System.out.println(atk.getType() + " : " + atk.getValue());
        System.out.println(def.getType() + " : " + def.getValue());
        System.out.println(spatk.getType() + " : " + spatk.getValue());
        System.out.println(spdef.getType() + " : " + spdef.getValue());
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

    public int getHP() {
        return hp;
    }

    public int getLvl() {
        return lvl;
    }

    public Stat getAtk() {
        return atk;
    }

    public Stat getDef() {
        return def;
    }

    public Stat getSpAtk() {
        return spatk;
    }

    public Stat getSpDef() {
        return spdef;
    }

    public Stat getSpeed() {
        return speed;
    }

    public void reduceHP(int damage) {
        hp -= damage;
    }

    public void fainted() {
        System.out.println(name + " fainted.");
    }

    public void performMove(Pokemon defender, int index) {
        Move move = this.moves.get(index);

        Move.StatusEffect effect = move.getStatusEffect();
        move.setAdvantage(defender.getType());
        final int power = move.getBasePower();
        System.out.println(this.name + " used " + move.getName());

        int damage = calculateDamage(defender, move, power);
        defender.reduceHP(damage);
        System.out.printf("Inflicted %d damage\n", damage);
        System.out.println(defender.getName() + "'s HP: " + Math.max(0, defender.getHP()));
        System.out.println();
        this.applyStatusEffect(defender, move, effect);
        move.reducePP();
    }

    private int calculateDamage(Pokemon defender, Move move, int power) {
        if (power == 0) {
            return 0;
        }

        int attack = getAttackValue(move);
        int defense = getDefenseValue(move, defender);
        double levelFactor = (2 * this.lvl) / 5.0;
        int critical = isCritical() ? 2 : 1;

        if (critical == 2) {
            attack = Math.max(attack, getBaseAttackValue(move));
            defense = Math.min(defense, getBaseDefenseValue(move, defender));
            System.out.println("A critical hit!");
        }

        double baseDamage = (((levelFactor * power * attack) / defense / 50) + 2) * critical;
        System.out.printf("Base %.2f damage\n", baseDamage);

        if (move.getAdvantage() == 0) {
            System.out.printf("It doesn't affect %s...\n", defender.getName());
            return 0;
        } else if (move.getAdvantage() != 1) {
            System.out.printf("It is %s effective\n", (move.getAdvantage() == 2.0) ? "super" : "not very");
        }

        float r = RAND.nextFloat(0.85f, 1.0f);
        System.out.printf("Advantage: %.2f Random %.2f\n", move.getAdvantage(), r);
        return Math.max(1, (int) (baseDamage * move.getAdvantage() * r));
    }

    private int getAttackValue(Move move) {
        return (move.getCategory() == Move.Category.PHYSICAL) ? this.atk.getValue() : this.spatk.getValue();
    }

    private int getDefenseValue(Move move, Pokemon defender) {
        return (move.getCategory() == Move.Category.PHYSICAL) ? defender.getDef().getValue() : defender.getSpDef().getValue();
    }

    private int getBaseAttackValue(Move move) {
        return (move.getCategory() == Move.Category.PHYSICAL) ? this.atk.getBaseValue() : this.spatk.getBaseValue();
    }

    private int getBaseDefenseValue(Move move, Pokemon defender) {
        return (move.getCategory() == Move.Category.PHYSICAL) ? defender.getDef().getBaseValue() : defender.getSpDef().getBaseValue();
    }

    private void applyStatusEffect(Pokemon defender, Move move, Move.StatusEffect effect) {
        if (effect != null) {
            if (move.getTarget() == Move.Target.SELF) {
                defender = this;
            }
            modifyStat(defender, effect);
        }
    }

    private void modifyStat(Pokemon defender, Move.StatusEffect effect) {
        int change = (effect == Move.StatusEffect.ATTACK_UP || effect == Move.StatusEffect.DEFENSE_UP || effect == Move.StatusEffect.SPEED_UP) ? 1 : -1;
        Stat statToModify;

        switch (effect) {
            case ATTACK_UP, ATTACK_DOWN ->
                statToModify = defender.getAtk();
            case DEFENSE_UP, DEFENSE_DOWN ->
                statToModify = defender.getDef();
            case SPEED_UP, SPEED_DOWN ->
                statToModify = defender.getSpeed();
            default ->
                throw new IllegalArgumentException("Unexpected value: " + effect);
        }

        if (!statToModify.modifyStage(change)) {
            System.out.printf("%s's %s won't go any %s!\n", defender.getName(), statToModify.getType(), (change > 0) ? "higher" : "lower");
        } else {
            reportStatChange(defender, statToModify.getType(), (change > 0) ? "rose" : "fell", statToModify.getValue());
        }
    }

    private void reportStatChange(Pokemon defender, Stat.StatType stat, String statAction, int newStat) {
        System.out.printf("%s's %s %s!\n", defender.getName(), stat, statAction);
        System.out.println(stat + " " + newStat);
        System.out.println();
    }

    private boolean isCritical() {
        return (RAND.nextInt(16) == 0);
    }
}
