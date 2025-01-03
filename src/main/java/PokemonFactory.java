
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PokemonFactory {

    static Pokemon createPokemon(String pokemonName) {
        List<Move> moves = new ArrayList<>();
        List<Type> types = new ArrayList<>();

        try {
            JSONObject jsonObject = parseJsonFile("src/main/resources/pokedex.json");
            JSONObject pokemonData = (JSONObject) jsonObject.get(pokemonName);

            if (pokemonData != null) {
                int lvl = ((Long) pokemonData.get("lvl")).intValue();
                int baseHP = ((Long) pokemonData.get("baseHP")).intValue();
                int hp = calculateHP(baseHP, lvl);

                Stat atk = createStat(pokemonData, "atk", lvl);
                Stat def = createStat(pokemonData, "def", lvl);
                Stat spatk = createStat(pokemonData, "spatk", lvl);
                Stat spdef = createStat(pokemonData, "spdef", lvl);
                Stat speed = createStat(pokemonData, "speed", lvl);

                Stat accuracy = new Stat(Stat.StatType.ACCURACY);
                Stat evasion = new Stat(Stat.StatType.EVASION);

                JSONArray typeArray = (JSONArray) pokemonData.get("type");
                for (Object typeObj : typeArray) {
                    types.add(Type.valueOf(((String) typeObj).toUpperCase()));
                }

                moves.addAll(parseMoves(pokemonData));

                return new Pokemon(pokemonName, moves, atk, def, spatk, spdef, speed, accuracy, evasion, types, lvl, hp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static int calculateHP(int baseHP, int lvl) {
        return ((2 * baseHP * lvl) / 100) + lvl + 10;
    }

    private static Stat createStat(JSONObject pokemonData, String statName, int lvl) {
        JSONObject stats = (JSONObject) pokemonData.get("stats");
        return new Stat(Stat.StatType.valueOf(statName.toUpperCase()), ((Long) stats.get(statName)).intValue(), lvl);
    }

    private static List<Move> parseMoves(JSONObject pokemonData) {
        List<Move> moves = new ArrayList<>();
        JSONArray movesArray = (JSONArray) pokemonData.get("moves");

        for (Object moveObj : movesArray) {
            JSONObject moveData = (JSONObject) moveObj;

            Move move = new Move(
                    (String) moveData.get("name"),
                    Type.valueOf((String) moveData.get("type")),
                    ((Long) moveData.get("power")).intValue(),
                    Move.Category.valueOf((String) moveData.get("category")),
                    ((((String) moveData.get("statModifier")) != null)) ? Move.StatModifier.valueOf((String) moveData.get("statModifier")) : null,
                    ((((String) moveData.get("statusEffect")) != null)) ? StatusEffect.valueOf((String) moveData.get("statusEffect")) : null,
                    Move.Target.valueOf((String) moveData.get("target")),
                    ((Long) moveData.get("pp")).intValue(), ((Long) moveData.get("accuracy")).intValue()
            );
            moves.add(move);
        }
        return moves;
    }

    private static JSONObject parseJsonFile(String path) throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        return (JSONObject) obj;
    }
}
