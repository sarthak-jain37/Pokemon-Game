
import java.util.Random;
import java.util.Scanner;

public class Battle {

    Scanner kb = new Scanner(System.in);
    private final Pokemon rival;
    private final Pokemon player;

    Battle(Pokemon player, Pokemon rival) {
        this.player = player;
        this.rival = rival;
    }

    void startBattle() {

        while (player.getHP() > 0 && rival.getHP() > 0) {
            System.out.print("Choose a Move: ");
            int i = 1;
            for (Move m : player.getMoves()) {
                System.out.printf("    %d. %s", i, m.getName());
                i++;
            }
            System.out.println();

            byte playerChoice = kb.nextByte();
            playerChoice--; //to get the correct index, i.e player choice - 1
            System.out.println();
            Random random = new Random();
            byte rivalChoice = (byte) random.nextInt(rival.getMoves().size()); // Generates a random integer to choose the random move.
            // byte rivalChoice = 1;

            while (playerChoice < 0 || playerChoice >= player.getMoves().size()) {
                System.out.println("Invalid choice, please select again.");
                playerChoice = kb.nextByte();
                playerChoice--; //to get the correct index, i.e player choice - 1
                System.out.println();
            }

            Move playerMove = player.getMoves().get(playerChoice);
            Move rivalMove = rival.getMoves().get(rivalChoice);
            while (playerMove.getPP() <= 0) {
                System.out.println("There's no PP left for this move!.");
                playerChoice = kb.nextByte();
                playerChoice--; //to get the correct index, i.e player choice - 1
                System.out.println();
                playerMove = player.getMoves().get(playerChoice);
            }

            while (rivalMove.getPP() <= 0) {
                rivalChoice = (byte) random.nextInt(rival.getMoves().size());
                rivalMove = rival.getMoves().get(rivalChoice);
            }

            if (player.getSpeed().getValue() > rival.getSpeed().getValue()) {
                player.performMove(rival, playerMove);
                if (rival.getHP() > 0) {
                    rival.performMove(player, rivalMove);
                }
            } else {
                rival.performMove(player, rivalMove);
                if (player.getHP() > 0) {
                    player.performMove(rival, playerMove);
                }
            }
        }

        if (player.getHP() <= 0) {
            player.fainted();
            System.out.println("Your Rival defeated you in battle.");
        } else if (rival.getHP() <= 0) {
            rival.fainted();
            System.out.println("You defeated your Rival in battle!");
        }
        kb.close();
    }
}
