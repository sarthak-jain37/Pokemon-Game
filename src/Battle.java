import java.util.Random;
import java.util.Scanner;
public class Battle {
    Scanner kb = new Scanner(System.in);
    private final Pokemon rival;
    private final Pokemon player;
    private static final int NUM_MOVES = 4;
    Battle(Pokemon player, Pokemon rival){
        this.player = player;
        this.rival = rival;
    }
    void startBattle(){
        player.setAdvantage(rival);
        rival.setAdvantage(player);

        while(player.getHP() > 0 && rival.getHP() > 0){
            System.out.print("Choose a Move: ");
            int i = 1;
            for (String m : player.getMoves()) {
                System.out.printf("    %d. %s", i, m);
                i++;
            }
            System.out.println();
            byte playerChoice = kb.nextByte();
            playerChoice--; //to get the correct index, i.e player choice - 1
            System.out.println();

            while (playerChoice < 0 || playerChoice >= player.getMoves().length) {
                System.out.println("Invalid choice, please select again.");
                playerChoice = kb.nextByte();
                playerChoice--; //to get the correct index, i.e player choice - 1
                System.out.println();
            }
            

            Random random = new Random();
            byte rivalChoice = (byte)random.nextInt(NUM_MOVES); // Generates a random integer in range [0,3]


            if(player.getSpeed() > rival.getSpeed()){
                attack(player, rival, playerChoice);
                attack(rival, player, rivalChoice);
            }
            else{
                attack(rival, player, rivalChoice);
                attack(player, rival, playerChoice);
            }
        }

        if(player.getHP() <= 0){
            player.fainted();
            System.out.println("Your Rival defeated you in battle.");
        }
        else if(rival.getHP() <= 0){
            rival.fainted();
            System.out.println("You defeated your Rival in battle!");
        }
        kb.close();
    }

    private void attack(Pokemon attacker, Pokemon defender, int index){
        String move = attacker.getMoves()[index];
        final int power = attacker.getBasePower()[index];
        System.out.println(attacker.getName() + " used " + move);

        double damage;
        if(power != 0){
            double levelFactor = (2 * attacker.getLvl()) / 5.0;
            double attackFactor = levelFactor * power * attacker.getAtk();
            double defenseFactor = attackFactor / defender.getDef();
            double baseDamage = (defenseFactor / 50) + 2;
            damage = baseDamage * attacker.getAdvantage();
        }
        else{
            damage = 0;
        }


        if(attacker.getAdvantage() != 1){
            System.out.printf("It is %s effective\n", (attacker.getAdvantage() == 2.0) ? "super" : "not very");
        }
        System.out.printf("It dealt %d damage\n\n", (int)damage);
        defender.reduceHP((int)damage);
    }
}
