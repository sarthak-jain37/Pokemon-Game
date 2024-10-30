
import java.util.ArrayList;
import java.util.List;

public class PokemonFactory {

    static Pokemon buildObject(String pokemonName) {
        List<Move> moves = new ArrayList<>();
        switch (pokemonName) {
            case "Bulbasaur" -> {
                Type type = Type.GRASS;
                int lvl = 5;
                int baseHP = 45;
                int hp = ((2 * baseHP * lvl) / 100) + lvl + 10;

                Stat atk = new Stat(Stat.StatType.ATTACK, 49, lvl);
                Stat def = new Stat(Stat.StatType.DEFENSE, 49, lvl);
                Stat speed = new Stat(Stat.StatType.SPEED, 45, lvl);

                moves.add(new Move("Tackle", Type.NORMAL, 35, Move.Category.PHYSICAL, null, Move.Target.FOE));
                moves.add(new Move("Growl", Type.NORMAL, 0, Move.Category.STATUS, Move.StatusEffect.ATTACK_DOWN, Move.Target.FOE));
                moves.add(new Move("Razor Leaf", Type.GRASS, 55, Move.Category.SPECIAL, null, Move.Target.FOE));
                moves.add(new Move("Vine Whip", Type.GRASS, 35, Move.Category.SPECIAL, null, Move.Target.FOE));

                return new Pokemon(pokemonName, moves, atk, def, speed, type, lvl, hp);
            }

            case "Charmander" -> {
                Type type = Type.FIRE;
                int lvl = 5;
                int baseHP = 39;
                int hp = ((2 * baseHP * lvl) / 100) + lvl + 10;

                Stat atk = new Stat(Stat.StatType.ATTACK, 52, lvl);
                Stat def = new Stat(Stat.StatType.DEFENSE, 43, lvl);
                Stat speed = new Stat(Stat.StatType.SPEED, 65, lvl);

                moves.add(new Move("Scratch", Type.NORMAL, 40, Move.Category.PHYSICAL, null, Move.Target.FOE));
                moves.add(new Move("Growl", Type.NORMAL, 0, Move.Category.STATUS, Move.StatusEffect.ATTACK_DOWN, Move.Target.FOE));
                moves.add(new Move("Ember", Type.FIRE, 40, Move.Category.SPECIAL, null, Move.Target.FOE));
                moves.add(new Move("Metal Claw", Type.STEEL, 50, Move.Category.PHYSICAL, null, Move.Target.FOE));

                return new Pokemon(pokemonName, moves, atk, def, speed, type, lvl, hp);
            }

            case "Squirtle" -> {
                Type type = Type.WATER;
                int lvl = 5;
                int baseHP = 44;
                int hp = ((2 * baseHP * lvl) / 100) + lvl + 10;

                Stat atk = new Stat(Stat.StatType.ATTACK, 48, lvl);
                Stat def = new Stat(Stat.StatType.DEFENSE, 65, lvl);
                Stat speed = new Stat(Stat.StatType.SPEED, 43, lvl);

                moves.add(new Move("Tackle", Type.NORMAL, 35, Move.Category.PHYSICAL, null, Move.Target.FOE));
                moves.add(new Move("Tail Whip", Type.NORMAL, 0, Move.Category.STATUS, Move.StatusEffect.DEFENSE_DOWN, Move.Target.FOE));
                moves.add(new Move("Bubble", Type.WATER, 20, Move.Category.SPECIAL, null, Move.Target.FOE));
                moves.add(new Move("Water Gun", Type.WATER, 40, Move.Category.SPECIAL, null, Move.Target.FOE));

                return new Pokemon(pokemonName, moves, atk, def, speed, type, lvl, hp);
            }

        }
        return null;
    }
}
