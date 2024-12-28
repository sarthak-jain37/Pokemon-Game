
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a Pokemon: ");
        System.out.println("1. Bulbasaur \t 2. Charmander \t 3. Squirtle.");
        byte choice = sc.nextByte();
        Pokemon player;
        Pokemon rival;
        switch (choice) {
            case 1 -> {
                player = PokemonFactory.createPokemon("Bulbasaur");
                rival = PokemonFactory.createPokemon("Charmander");
                break;
            }

            case 2 -> {
                player = PokemonFactory.createPokemon("Charmander");
                rival = PokemonFactory.createPokemon("Squirtle");
                break;
            }

            case 3 -> {
                player = PokemonFactory.createPokemon("Squirtle");
                rival = PokemonFactory.createPokemon("Bulbasaur");
                break;
            }

            default -> {
                System.out.println("Invalid Option.");
                player = PokemonFactory.createPokemon("Charmander");
                rival = PokemonFactory.createPokemon("Squirtle");
                break;
            }
        }
        System.out.println("\nYou picked " + player.getName());
        System.out.println("Your Rival picked " + rival.getName());

        player.display();
        rival.display();

        System.out.println("Your Rival challenged you to a battle!\n");
        Battle b = new Battle(player, rival);
        b.startBattle();
        sc.close();
    }
}
