
public class Move {

    public enum Category {
        PHYSICAL, SPECIAL, STATUS;
    }

    public enum StatusEffect {
        ATTACK_DOWN, ATTACK_UP, DEFENSE_DOWN, DEFENSE_UP, SPEED_UP, SPEED_DOWN, SPATK_DOWN, SPATK_UP, SPDEF_DOWN, SPDEF_UP, ACCURACY_UP, ACCURACY_DOWN, EVASION_UP, EVASION_DOWN;
    }

    public enum Target {
        SELF, FOE;
    }

    private final String name;
    private final int basePower;
    private final Category category;
    private final StatusEffect statusEffect;
    private final Type type;
    private final Target target;
    private final int accuracy;
    private int pp;
    private double advantage;

    public Move(String name, Type type, int basePower, Category category, StatusEffect statusEffect, Target target, int pp, int accuracy) {
        this.name = name;
        this.type = type;
        this.basePower = basePower;
        this.category = category;
        this.statusEffect = statusEffect;
        this.target = target;
        this.accuracy = accuracy;
        this.pp = pp;
        this.advantage = 1;
    }

    public String getName() {
        return name;
    }

    public int getBasePower() {
        return basePower;
    }

    public Category getCategory() {
        return category;
    }

    public StatusEffect getStatusEffect() {
        return statusEffect;
    }

    public Type getType() {
        return type;
    }

    public Target getTarget() {
        return target;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPP() {
        return pp;
    }

    public double getAdvantage() {
        return advantage;
    }

    public void reducePP() {
        pp--;
    }

    public void setAdvantage(Type opponentType) {
        switch (this.type) {
            case FIRE ->
                this.advantage = opponentType == Type.GRASS ? 2.0
                        : opponentType == Type.ICE ? 2.0
                                : opponentType == Type.STEEL ? 2.0
                                        : opponentType == Type.BUG ? 2.0
                                                : opponentType == Type.WATER ? 0.5
                                                        : opponentType == Type.FIRE ? 0.5
                                                                : opponentType == Type.ROCK ? 0.5
                                                                        : opponentType == Type.DRAGON ? 0.5 : 1.0;

            case WATER ->
                this.advantage = opponentType == Type.FIRE ? 2.0
                        : opponentType == Type.ROCK ? 2.0
                                : opponentType == Type.GROUND ? 2.0
                                        : opponentType == Type.GRASS ? 0.5
                                                : opponentType == Type.DRAGON ? 0.5
                                                        : opponentType == Type.WATER ? 0.5 : 1.0;
            case GRASS ->
                this.advantage = opponentType == Type.WATER ? 2.0
                        : opponentType == Type.GROUND ? 2.0
                                : opponentType == Type.ROCK ? 2.0
                                        : opponentType == Type.GRASS ? 0.5
                                                : opponentType == Type.FIRE ? 0.5
                                                        : opponentType == Type.POISON ? 0.5
                                                                : opponentType == Type.BUG ? 0.5
                                                                        : opponentType == Type.DRAGON ? 0.5
                                                                                : opponentType == Type.STEEL ? 0.5
                                                                                        : opponentType == Type.FLYING ? 0.5 : 1.0;

            case ELECTRIC ->
                this.advantage = opponentType == Type.WATER ? 2.0
                        : opponentType == Type.FLYING ? 2.0
                                : opponentType == Type.ELECTRIC ? 0.5
                                        : opponentType == Type.GRASS ? 0.5
                                                : opponentType == Type.DRAGON ? 0.5
                                                        : opponentType == Type.GROUND ? 0.0 : 1.0;

            case ICE ->
                this.advantage = opponentType == Type.GRASS ? 2.0
                        : opponentType == Type.GROUND ? 2.0
                                : opponentType == Type.FLYING ? 2.0
                                        : opponentType == Type.DRAGON ? 2.0
                                                : opponentType == Type.WATER ? 0.5
                                                        : opponentType == Type.ICE ? 0.5
                                                                : opponentType == Type.STEEL ? 0.5
                                                                        : opponentType == Type.FIRE ? 0.5 : 1.0;

            case FIGHTING ->
                this.advantage = opponentType == Type.NORMAL ? 2.0
                        : opponentType == Type.ICE ? 2.0
                                : opponentType == Type.ROCK ? 2.0
                                        : opponentType == Type.DARK ? 2.0
                                                : opponentType == Type.STEEL ? 2.0
                                                        : opponentType == Type.PSYCHIC ? 0.5
                                                                : opponentType == Type.POISON ? 0.5
                                                                        : opponentType == Type.PSYCHIC ? 0.5
                                                                                : opponentType == Type.BUG ? 0.5
                                                                                        : opponentType == Type.FAIRY ? 0.5
                                                                                                : opponentType == Type.GHOST ? 0 : 1.0;

            case POISON ->
                this.advantage = opponentType == Type.GRASS ? 2.0
                        : opponentType == Type.FAIRY ? 2.0
                                : opponentType == Type.POISON ? 0.5
                                        : opponentType == Type.ROCK ? 0.5
                                                : opponentType == Type.GHOST ? 0.5
                                                        : opponentType == Type.STEEL ? 0
                                                                : opponentType == Type.GROUND ? 0.5 : 1.0;

            case GROUND ->
                this.advantage = opponentType == Type.ELECTRIC ? 2.0
                        : opponentType == Type.FIRE ? 2.0
                                : opponentType == Type.POISON ? 2.0
                                        : opponentType == Type.ROCK ? 2.0
                                                : opponentType == Type.STEEL ? 2.0
                                                        : opponentType == Type.BUG ? 0.5
                                                                : opponentType == Type.GRASS ? 0.5
                                                                        : opponentType == Type.FLYING ? 0 : 1.0;

            case FLYING ->
                this.advantage = opponentType == Type.FIGHTING ? 2.0
                        : opponentType == Type.GRASS ? 2.0
                                : opponentType == Type.POISON ? 2.0
                                        : opponentType == Type.BUG ? 2.0
                                                : opponentType == Type.ROCK ? 0.5
                                                        : opponentType == Type.STEEL ? 2.0
                                                                : opponentType == Type.ELECTRIC ? 0.5 : 1.0;

            case PSYCHIC ->
                this.advantage = opponentType == Type.FIGHTING ? 2.0
                        : opponentType == Type.POISON ? 2.0
                                : opponentType == Type.PSYCHIC ? 0.5
                                        : opponentType == Type.STEEL ? 0.5
                                                : opponentType == Type.DARK ? 0 : 1.0;

            case BUG ->
                this.advantage = opponentType == Type.POISON ? 0.5
                        : opponentType == Type.FLYING ? 0.5
                                : opponentType == Type.FIRE ? 0.5
                                        : opponentType == Type.FIGHTING ? 0.5
                                                : opponentType == Type.GHOST ? 2.0
                                                        : opponentType == Type.GRASS ? 2.0
                                                                : opponentType == Type.STEEL ? 2.0
                                                                        : opponentType == Type.PSYCHIC ? 2.0
                                                                                : opponentType == Type.FAIRY ? 2.0
                                                                                        : opponentType == Type.DARK ? 2.0 : 1.0;

            case ROCK ->
                this.advantage = opponentType == Type.FLYING ? 2.0
                        : opponentType == Type.ICE ? 2.0
                                : opponentType == Type.BUG ? 2.0
                                        : opponentType == Type.FIRE ? 2.0
                                                : opponentType == Type.GROUND ? 0.5
                                                        : opponentType == Type.BUG ? 0.5
                                                                : opponentType == Type.STEEL ? 0.5
                                                                        : opponentType == Type.FIGHTING ? 0.5 : 1.0;

            case GHOST ->
                this.advantage = opponentType == Type.PSYCHIC ? 2.0
                        : opponentType == Type.GHOST ? 2.0
                                : opponentType == Type.DARK ? 0.5
                                        : opponentType == Type.NORMAL ? 0 : 1.0;

            case DRAGON ->
                this.advantage = opponentType == Type.DRAGON ? 2.0
                        : opponentType == Type.STEEL ? 0.5
                                : opponentType == Type.FAIRY ? 0 : 1.0;

            case DARK ->
                this.advantage = opponentType == Type.PSYCHIC ? 2.0
                        : opponentType == Type.GHOST ? 2.0
                                : opponentType == Type.FIGHTING ? 0.5
                                        : opponentType == Type.DARK ? 0.5
                                                : opponentType == Type.FAIRY ? 0.5 : 1.0;

            case STEEL ->
                this.advantage = opponentType == Type.ICE ? 2.0
                        : opponentType == Type.ROCK ? 2.0
                                : opponentType == Type.FAIRY ? 2.0
                                        : opponentType == Type.FIRE ? 0.5
                                                : opponentType == Type.WATER ? 0.5
                                                        : opponentType == Type.ELECTRIC ? 0.5
                                                                : opponentType == Type.STEEL ? 0.5 : 1.0;

            case FAIRY ->
                this.advantage = opponentType == Type.DRAGON ? 2.0
                        : opponentType == Type.ICE ? 2.0
                                : opponentType == Type.DARK ? 2.0
                                        : opponentType == Type.STEEL ? 0.5
                                                : opponentType == Type.FIRE ? 0.5
                                                        : opponentType == Type.POISON ? 0.5 : 1.0;

            case NORMAL ->
                this.advantage = opponentType == Type.ROCK ? 0.5
                        : opponentType == Type.STEEL ? 0.5
                                : opponentType == Type.GHOST ? 0 : 1.0;
        }
    }
}
