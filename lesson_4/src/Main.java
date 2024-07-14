import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {260, 270, 250, 210, 265, 230};
    public static int[] heroesDamage = {20, 15, 10, 0, 25, 13};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Thor", "lucky"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }



    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }


        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseDefence();
        bossHits();
        heroesHit();
        medicHelp();
        thorAbility();
        luckyAbility();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber
                + " -------------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No Defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth
                + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No Defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }
    public static void medicHelp() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int help = random.nextInt(120);
                if (heroesHealth[3] ==0) {
                    help = 0;
                }
                if (heroesAttackType[i] == heroesAttackType[3]) {
                    continue;
                }
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                    heroesHealth[i] += help;
                    break;
                }
                    System.out.println("Helping: " + help);
                }
            }
        }
    }
    public static void thorAbility() {
        Random random = new Random();
        boolean ability = random.nextBoolean();
        for (int i = 0; i <heroesHealth.length; i++) {
            if (heroesHealth[4] > 0 && ability == true) {
                bossDamage = 0;
                break;
            }
            if (ability == false) {
                bossDamage = 50;
            }
            System.out.println("Thor ability used");
        }
    }
    public static void luckyAbility() {
        Random random = new Random();
        boolean luck = random.nextBoolean();
        for (int i = 0; i <heroesHealth.length ; i++) {
            if (heroesHealth[5] > 0 && luck == true) {
                heroesHealth[5] += 50;
                break;
            }
            if (luck == false) {
                bossDamage = 50;
            }
        }
    }
}