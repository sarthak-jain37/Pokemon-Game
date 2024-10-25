public class PokemonFactory {
        static Pokemon buildObject(String pokemonName){
            switch (pokemonName) {
                case "Bulbasaur" -> {
                    String moves[] = {"Tackle", "Growl", "Razor Leaf", "Vine Whip"};
                    int basePower[] = {35, 0, 55, 35};
                    return new Pokemon("Bulbasaur", moves, "Grass", 5,  basePower, 8, 8, 8, 8);
                }
                    
                case "Charmander" -> {
                    String moves[] = {"Scratch", "Growl", "Ember", "Metal Claw"};
                    int basePower[] = {40, 0, 40, 50};
                    return new Pokemon("Charmander", moves, "Fire", 5, basePower, 8, 9, 8, 11);
                }

                case "Squirtle" -> {
                    String moves[] = {"Tackle", "Tail Whip", "Bubble", "Water Gun"};
                    int basePower[] = {35, 0, 20, 40};
                    return new Pokemon("Squirtle", moves, "Water", 5, basePower, 8, 8, 11, 8);
                }

            }
            return null;
        }
}

