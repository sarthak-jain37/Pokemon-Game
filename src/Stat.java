
public class Stat {

    public enum StatType {
        ATTACK, DEFENSE, SPEED;
    }

    private final StatType type;
    private int value;
    private int stage;

    public Stat(StatType type, int base, int level) {
        this.type = type;
        this.value = ((2 * base * level) / 100) + 5;
        this.stage = 0;  // Default stage
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
        return stage;
    }

    public boolean modifyStage(int change) {
        int oldStage = stage;
        stage = Math.max(-6, Math.min(6, stage + change));  // Clamp between -6 and +6
        return oldStage != stage;
    }

    private double getStageMultiplier() {
        if (stage >= 0) {
            return 1 + (stage * 0.5);
        } else {
            return 1 / (1 + (-stage * 0.5));
        }
    }
}
