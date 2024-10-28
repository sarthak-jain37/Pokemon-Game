import java.util.Random;
import java.util.Scanner;
public class Battle {
    Scanner kb = new Scanner(System.in);
    private final Pokemon rival;
    private final Pokemon player;
    Battle(Pokemon player, Pokemon rival){
        this.player = player;
        this.rival = rival;
    }
    void startBattle(){

        while(player.getHP() > 0 && rival.getHP() > 0){
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

            while (playerChoice < 0 || playerChoice >= player.getMoves().size()) {
                System.out.println("Invalid choice, please select again.");
                playerChoice = kb.nextByte();
                playerChoice--; //to get the correct index, i.e player choice - 1
                System.out.println();
            }
            

            Random random = new Random();
            // byte rivalChoice = (byte)random.nextInt(rival.getMoves().size()); // Generates a random integer to choose the random move.
            byte rivalChoice = 1;

            if(player.getSpeed().getValue() > rival.getSpeed().getValue()){
                attack(player, rival, playerChoice);
                if(rival.getHP() <= 0) break;
                attack(rival, player, rivalChoice);
            }
            else{
                attack(rival, player, rivalChoice);
                if(player.getHP() <= 0) break;
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
        Move move = attacker.getMoves().get(index);
        move.setAdvantage(defender.getType());
        final int power = move.getBasePower();
        System.out.println(attacker.getName() + " used " + move.getName());

        int damage = 0;
        if(power != 0){
            double levelFactor = (2 * attacker.getLvl()) / 5.0;
            double attackFactor = levelFactor * power * attacker.getAtk().getValue();
            double defenseFactor = attackFactor / defender.getDef().getValue();
            double baseDamage = (defenseFactor / 50) + 2;
            damage = (int)(baseDamage * move.getAdvantage());
            System.out.printf("The attack factor: %f The defense factor: %f The advantage %f\n", attackFactor, defenseFactor, move.getAdvantage());
        }

        if(move.getAdvantage() != 1){
            System.out.printf("It is %s effective\n", (move.getAdvantage() == 2.0) ? "super" : "not very");
        }
        System.out.printf("It dealt %d damage\n", (int)damage);
        defender.reduceHP((int)damage);
        System.out.println(defender.getName()+ "'s HP: " + Math.max(0, defender.getHP()));
        
        if(move.getStatusEffect() != null){
            switch(move.getStatusEffect()){
                case ATTACK_UP -> {
                    //if(move.target == self)
                    if(attacker.getAtk().modifyStage(1)){
                    System.out.println(attacker.getName() + "'s attack rose!");
                    System.out.println(attacker.getAtk().getType() + " " + attacker.getAtk().getValue());
                    }
                    else{
                        System.out.println(attacker.getName() + "'s attack won't go any higher");
                    }
                }
                case ATTACK_DOWN -> {
                    if(defender.getAtk().modifyStage(-1)){
                    System.out.println(defender.getName() + "'s attack fell!");
                    System.out.println(defender.getAtk().getType() + " " + defender.getAtk().getValue());
                    }
                    else{
                        System.out.println(defender.getName() + "'s attack won't go any lower");
                    }
                }
                case DEFENSE_UP -> {
                    if(attacker.getDef().modifyStage(1)){
                    System.out.println(attacker.getName() + "'s defense rose!");
                    System.out.println(attacker.getDef().getType() + " " + attacker.getDef().getValue());
                    }
                    else{
                        System.out.println(attacker.getName() + "'s defense won't go any higher");
                    }
                }
                case DEFENSE_DOWN -> {
                    if(defender.getDef().modifyStage(-1)){
                    System.out.println(defender.getName() + "'s defense fell!");
                    System.out.println(defender.getDef().getType() + " " + defender.getDef().getValue());
                    }
                    else{
                        System.out.println(defender.getName() + "'s defense won't go any lower");
                    }
                }
                case SPEED_UP -> {
                    if(attacker.getSpeed().modifyStage(1)){
                    System.out.println(attacker.getName() + "'s speed rose!");
                    System.out.println(attacker.getSpeed().getType() + " " + attacker.getSpeed().getValue());
                    }
                    else{
                        System.out.println(attacker.getName() + "'s speed won't go any higher");
                    }
                }
                case SPEED_DOWN -> {
                    if(defender.getSpeed().modifyStage(-1)){
                    System.out.println(defender.getName() + "'s speed fell!");
                    System.out.println(defender.getSpeed().getType() + " " + defender.getSpeed().getValue()); 
                    }  
                    else{
                        System.out.println(defender.getName() + "'s speed won't go any lower");
                    }
                }
            }
    }
    System.out.println();
    }
    }
