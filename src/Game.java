import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a Pokemon: ");
        System.out.println("1. Bulbasaur \t 2. Charmander \t 3. Squirtle.");
        byte choice = sc.nextByte();
        Pokemon player;  
        Pokemon rival; 
        switch(choice){
            case 1 -> {
                player = PokemonFactory.buildObject("Bulbasaur");
                rival = PokemonFactory.buildObject("Charmander");
                break;
            }

            case 2 -> {
                player = PokemonFactory.buildObject("Charmander");
                rival = PokemonFactory.buildObject("Squirtle");
                break;
            }

            case 3 -> {
                player = PokemonFactory.buildObject("Squirtle");
                rival = PokemonFactory.buildObject("Bulbasaur");
                break;
            }

            default -> {
                System.out.println("Invalid Option.");
                player = PokemonFactory.buildObject("Charmander");
                rival = PokemonFactory.buildObject("Squirtle");
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
