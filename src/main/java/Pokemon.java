
import java.util.List;
import java.util.Random;

public class Pokemon {

    private static final Random RAND = new Random();

    private final String name;
    private final List<Type> types;
    private final List<Move> moves;
    private final Stat atk;
    private final Stat def;
    private final Stat spatk;
    private final Stat spdef;
    private final Stat speed;
    private final Stat accuracy;
    private final Stat evasion;
    private StatusEffect currentEffect;
    private final int lvl;
    private int hp;
    private final int maxHP;

    public Pokemon(String name, List<Move> moves, Stat atk, Stat def, Stat spatk, Stat spdef, Stat speed, Stat accuracy, Stat evasion, List<Type> types, int lvl, int hp) {
        this.name = name;
        this.moves = moves;
        this.types = types;
        this.atk = atk;
        this.def = def;
        this.spatk = spatk;
        this.spdef = spdef;
        this.speed = speed;
        this.accuracy = accuracy;
        this.evasion = evasion;
        this.currentEffect = null;
        this.lvl = lvl;
        this.hp = hp;
        this.maxHP = hp;
    }

    public void display() {
        System.out.println("\nName: " + name);
        System.out.print("Moves:");
        for (Move m : moves) {
            System.out.print("  " + m.getName());
        }
        System.out.println();
        System.out.println("Type: " + types);
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

    public List<Type> getTypes() {
        return types;
    }

    public int getHP() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
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

    public Stat getAccuracy() {
        return accuracy;
    }

    public Stat getEvasion() {
        return evasion;
    }

    public void setCurrentEffect(StatusEffect effect) {
        this.currentEffect = effect;
    }

    public StatusEffect getCurrentEffect() {
        return currentEffect;
    }

    public void reduceHP(int damage) {
        hp -= damage;
    }

    public void fainted() {
        System.out.println(name + " fainted.");
    }

    public void performMove(Pokemon defender, Move move) {

        if (canMove(this, currentEffect)) {
            Move.StatModifier modifier = move.getStatModifier();
            StatusEffect effect = move.getStatusEffect();
            move.setAdvantage(defender.getTypes());
            final int power = move.getBasePower();
            System.out.println(this.name + " used " + move.getName());

            if (!doesMoveHit(defender, move)) {
                System.out.println("But it missed!");
                System.out.println(defender.getName() + "'s HP: " + Math.max(0, defender.getHP()));
                System.out.println();
            } else {
                int damage = calculateDamage(defender, move, power);
                defender.reduceHP(damage);
                System.out.printf("Inflicted %d damage\n", damage);
                System.out.println(defender.getName() + "'s HP: " + Math.max(0, defender.getHP()));
                System.out.println();
                modifyStat(defender, move, modifier);

                if (effect != null) {
                    if (defender.getCurrentEffect() == null) {
                        tryStatusEffect(defender, effect);
                    } else {
                        if (defender.getCurrentEffect() == StatusEffect.FRZ && move.getType() == Type.FIRE) {
                            defender.setCurrentEffect(null);
                            System.out.println(defender.getName() + " thawed out!");
                        }
                    }
                }
            }
        }
        if (currentEffect != null) {
            handleStatusDamage(this, currentEffect);
        }
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
        System.out.printf("Base Damage: %.2f\n", baseDamage);

        if (move.getAdvantage() == 0) {
            System.out.printf("It doesn't affect %s...\n", defender.getName());
            return 0;
        } else if (move.getAdvantage() != 1) {
            System.out.printf("It is %s effective\n", (move.getAdvantage() >= 2.0) ? "super" : "not very");
        }

        float r = RAND.nextFloat(0.85f, 1.0f);
        System.out.printf("Advantage: %.2f Random %.2f\n", move.getAdvantage(), r);
        return Math.max(1, (int) (baseDamage * move.getAdvantage() * r));
    }

    private boolean isCritical() {
        return (RAND.nextInt(16) == 0);
    }

    private boolean doesMoveHit(Pokemon defender, Move move) {
        // System.out.println("Accuracy: " + this.accuracy.getStage());
        // System.out.println("Evasion: " + defender.getEvasion().getStage());

        double multiplier = Stat.getStageMultiplier(this.accuracy.getStage() - defender.getEvasion().getStage());
        // System.out.println("Multiplier: " + multiplier);
        int final_accuracy = (int) (move.getAccuracy() * multiplier);
        // System.out.println("Final Accuracy: " + final_accuracy);
        int r = RAND.nextInt(100) + 1;
        // System.out.println("Random: " + r);
        return (r <= final_accuracy);
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

    private void modifyStat(Pokemon defender, Move move, Move.StatModifier modifier) {
        if (modifier != null) {
            if (move.getTarget() == Move.Target.SELF) {
                defender = this;
            }

            int change = (modifier == Move.StatModifier.ATTACK_UP || modifier == Move.StatModifier.DEFENSE_UP || modifier == Move.StatModifier.SPATK_UP || modifier == Move.StatModifier.SPDEF_UP || modifier == Move.StatModifier.SPEED_UP || modifier == Move.StatModifier.ACCURACY_UP || modifier == Move.StatModifier.EVASION_UP) ? 1 : -1;
            Stat statToModify;

            switch (modifier) {
                case ATTACK_UP, ATTACK_DOWN ->
                    statToModify = defender.getAtk();
                case DEFENSE_UP, DEFENSE_DOWN ->
                    statToModify = defender.getDef();
                case SPEED_UP, SPEED_DOWN ->
                    statToModify = defender.getSpeed();
                case SPATK_UP, SPATK_DOWN ->
                    statToModify = defender.getSpAtk();
                case SPDEF_UP, SPDEF_DOWN ->
                    statToModify = defender.getSpDef();
                case ACCURACY_UP, ACCURACY_DOWN ->
                    statToModify = defender.getAccuracy();
                case EVASION_UP, EVASION_DOWN ->
                    statToModify = defender.getEvasion();
                default ->
                    throw new IllegalArgumentException("Unexpected value: " + modifier);
            }

            if (!statToModify.canModifyStage(change)) {
                System.out.printf("%s's %s won't go any %s!\n", defender.getName(), statToModify.getType(), (change > 0) ? "higher" : "lower");
                System.out.println();
            } else {
                reportStatChange(defender, statToModify.getType(), (change > 0) ? "rose" : "fell", statToModify.getValue());
            }
        }
    }

    private void tryStatusEffect(Pokemon defender, StatusEffect effect) {
        applyStatusEffect(defender, effect);
    }

    private void applyStatusEffect(Pokemon defender, StatusEffect effect) {
        List<Type> types = defender.getTypes();
        switch (effect) {
            case BRN -> {
                for (Type type : types) {
                    if (type == Type.FIRE) {
                        return;
                    }
                }
                System.out.println(defender.getName() + " was burnt!");
            }

            case FRZ -> {
                for (Type type : types) {
                    if (type == Type.ICE) {
                        return;
                    }
                }
                System.out.println(defender.getName() + " was frozen solid!");
            }

            case PSN -> {
                for (Type type : types) {
                    if (type == Type.STEEL || type == Type.POISON) {
                        return;
                    }
                }
                System.out.println(defender.getName() + " was poisoned!");
            }

            case SLP -> {
                System.out.println(defender.getName() + " fell asleep!");
            }

            case PAR -> {
                System.out.println(defender.getName() + " was paralysed! It may be unable to move!");
                defender.getSpeed().setValue((int) (defender.getSpeed().getValue() * 0.25));
            }
        }
        System.out.println();
        defender.setCurrentEffect(effect);
    }

    private void handleStatusDamage(Pokemon defender, StatusEffect effect) {
        switch (effect) {
            case BRN -> {
                defender.reduceHP(defender.getMaxHP() / 8);
                System.out.println(defender.getName() + " is hurt by its burn!\n");
            }
            case PSN -> {
                defender.reduceHP(defender.getMaxHP() / 8);
                System.out.println(defender.getName() + " is hurt by poison!\n");
            }

            default -> {

            }
        }
    }

    private boolean canMove(Pokemon defender, StatusEffect effect) {
        if (effect == null) {
            return true;
        }

        switch (effect) {
            case FRZ -> {
                int r = RAND.nextInt(100);
                if (r < 10) {
                    System.out.println(defender.getName() + " thawed out!\n");
                    defender.setCurrentEffect(null);
                    return true;
                } else {
                    System.out.println(defender.getName() + " is frozen solid!\n");
                    return false;
                }
            }

            case SLP -> {
                int r = RAND.nextInt(4);
                if (r == 0) {
                    defender.setCurrentEffect(null);
                    System.out.println("It woke up!\n");
                } else {
                    System.out.println(defender.getName() + " is fast asleep!\n");
                    return false;
                }
            }

            case PAR -> {
                System.out.print(defender.getName() + " is pararlysed!");
                int r = RAND.nextInt(100);
                if (r < 25) {
                    System.out.println(" It can't move!\n");
                    return false;
                } else {
                    System.out.println();
                    return true;
                }
            }

            default -> {
                return true;
            }
        }
        return true;
    }

    private void reportStatChange(Pokemon defender, Stat.StatType stat, String statAction, int newStat) {
        System.out.printf("%s's %s %s!\n", defender.getName(), stat, statAction);
        if (!(stat == Stat.StatType.ACCURACY) && !(stat == Stat.StatType.EVASION)) {
            System.out.println(stat + " " + newStat);
        }
        System.out.println();
    }
}
