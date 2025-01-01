
public class Stat {

    public enum StatType {
        ATK, DEF, SPATK, SPDEF, SPEED, ACCURACY, EVASION;
    }

    private final StatType type;
    private int value;
    private int stage;

    public Stat(StatType type, int base, int level) {
        this.type = type;
        this.value = ((2 * base * level) / 100) + 5;
        this.stage = 0;  // Default stage
    }

    public Stat(StatType type) {
        this.type = type;
        this.stage = 0;
    }

    public StatType getType() {
        return type;
    }

    public int getValue() {
        return (int) (this.value * getStageMultiplier());
    }

    public int getBaseValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStage() {
        return this.stage;
    }

    public boolean canModifyStage(int change) {
        int oldStage = this.stage;
        this.stage = (Math.max(-6, Math.min(6, this.stage + change)));  // Clamp between -6 and +6
        return oldStage != this.stage;
    }

    private double getStageMultiplier() {
        if (this.stage >= 0) {
            return (2 + this.stage) / 2.0;
        } else {
            return 2 / (2.0 - this.stage);
        }
    }

    public static double getStageMultiplier(int stage) {
        if (stage > 6) {
            stage = 6;
        }

        if (stage < -6) {
            stage = -6;
        }

        if (stage >= 0) {
            return (3 + stage) / 3.0;
        } else {
            return 3 / (3.0 - stage);
        }
    }
}
