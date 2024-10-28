import java.util.ArrayList;
import java.util.List;
public class PokemonFactory {
        static Pokemon buildObject(String pokemonName){
            List<Move> moves = new ArrayList<>();
            switch (pokemonName) {
                case "Bulbasaur" -> {
                    Type type = Type.GRASS;
                    int lvl = 5;
                    int hp = 8;

                    Stat atk = new Stat(Stat.StatType.ATTACK, 8);
                    Stat def = new Stat(Stat.StatType.DEFENSE, 8);
                    Stat speed = new Stat(Stat.StatType.SPEED, 8);

                    moves.add(new Move("Tackle", Type.NORMAL,35, Move.MoveCategory.PHYSICAL, null));
                    moves.add(new Move("Growl", Type.NORMAL, 0, Move.MoveCategory.STATUS, Move.StatusEffect.ATTACK_DOWN));
                    moves.add(new Move("Razor Leaf", Type.GRASS, 55, Move.MoveCategory.SPECIAL, null));
                    moves.add(new Move("Vine Whip", Type.GRASS, 35, Move.MoveCategory.SPECIAL, null));

                    return new Pokemon(pokemonName, moves, atk, def, speed, type, lvl, hp);
                }
                    
                case "Charmander" -> {
                    Type type = Type.FIRE;
                    int lvl = 5;
                    int hp = 8;

                    Stat atk = new Stat(Stat.StatType.ATTACK, 9);
                    Stat def = new Stat(Stat.StatType.DEFENSE, 8);
                    Stat speed = new Stat(Stat.StatType.SPEED, 11);

                    moves.add(new Move("Scratch", Type.NORMAL, 40, Move.MoveCategory.PHYSICAL, null));
                    moves.add(new Move("Growl", Type.NORMAL, 0, Move.MoveCategory.STATUS, Move.StatusEffect.ATTACK_DOWN));
                    moves.add(new Move("Ember", Type.FIRE, 40, Move.MoveCategory.SPECIAL, null));
                    moves.add(new Move("Metal Claw", Type.STEEL, 50, Move.MoveCategory.PHYSICAL, null));

                    return new Pokemon(pokemonName, moves, atk, def, speed, type, lvl, hp);
                }

                case "Squirtle" -> {
                    Type type = Type.WATER;
                    int lvl = 5;
                    int hp = 8;

                    Stat atk = new Stat(Stat.StatType.ATTACK, 8);
                    Stat def = new Stat(Stat.StatType.DEFENSE, 11);
                    Stat speed = new Stat(Stat.StatType.SPEED, 8);

                    moves.add(new Move("Tackle", Type.NORMAL, 35, Move.MoveCategory.PHYSICAL, null));
                    moves.add(new Move("Tail Whip", Type.NORMAL, 0, Move.MoveCategory.STATUS, Move.StatusEffect.DEFENSE_DOWN));
                    moves.add(new Move("Bubble", Type.WATER, 20, Move.MoveCategory.SPECIAL, null));
                    moves.add(new Move("Water Gun", Type.WATER, 40, Move.MoveCategory.SPECIAL, null));

                    return new Pokemon(pokemonName, moves, atk, def, speed, type, lvl, hp);
                }

            }
            return null;
        }
}

