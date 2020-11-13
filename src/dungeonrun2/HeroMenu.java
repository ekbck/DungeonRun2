package dungeonrun2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class HeroMenu {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Monsters> monsters = new ArrayList<>();
    static ArrayList<Creatures> attackOrder = new ArrayList<>();
    static ArrayList<Integer> initiative = new ArrayList<>();
    static Creatures player;

    public static void chooseHero() {

        System.out.println("                      =============== ");
        System.out.println("                     |  SELECT HERO  |");
        System.out.println("                     |   1. Knight   |");
        System.out.println("                     |   2. Wizard   |");
        System.out.println("                     |   3. Thief    |");
        System.out.println("                      =============== ");

        int userInput = sc.nextInt();
        sc.nextLine();

        if (userInput == 1) {
            Knight.stats();
            boolean confirm = confirmHero();
            if (confirm == true) {
                String name = name();
                player = new Knight(15, 9, 6, 4, name, 1, 0);
                randomMonster();
            } else {
                chooseHero();
            }

        } else if (userInput == 2) {
            Wizard.stats();
            boolean confirm = confirmHero();
            if (confirm == true) {
                String name = name();
                player = new Wizard(5, 9, 6, 4, name, 1, 0);
                randomMonster();
            } else {
                chooseHero();
            }
        } else if (userInput == 3) {
            Thief.stats();
            boolean confirm = confirmHero();
            if (confirm == true) {
                String name = name();
                player = new Thief(5, 9, 6, 4, name, 1, 0);
                randomMonster();
            } else {
                chooseHero();
            }
        } else {
            System.out.println("Error! Choose 1, 2 or 3!");
            chooseHero();

        }

    }

    public static void loadCharacter() {

        System.out.println(" ================================= ");
        System.out.println("|           LOAD CHARACTER        |");
        System.out.println("|   1. [insert saved character]   |");
        System.out.println("|   2. [insert saved character]   |");
        System.out.println("|   3. [insert saved character]   |");
        System.out.println(" ================================= ");
    }

    public static boolean confirmHero() {

        System.out.println("\nContinue? \n1. Yes \n2. No");

        int userInput = sc.nextInt();
        sc.nextLine();

        while (true) {
            switch (userInput) {
                case 1:
                    return true;
                case 2:
                    return false;

                default:
                    System.out.println("Error! Choose 1 or 2");
                    break;
            }
        }

    }

    public static String name() {
        System.out.println("\nEnter name");
        String input = sc.nextLine();
        return input;
    }

    public static void randomMonster() {

        monsters.clear();

        Random random = new Random();

        if (Math.random() * 100 < 100) {     //jättespindel 20, 
            System.out.println("-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----");
            System.out.println("Watch out, there's a Giantspider ahead!");
            Monsters spider = new GiantSpider(7, 2, 1, 3, "Giant Spider", 2, 0);
            monsters.add(spider);
        }

        if (Math.random() * 100 < 100) {  //skelett 15  
            System.out.println("-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----");
            System.out.println("Watch out, there's a Skeleton ahead!");
            Monsters skeleton = new Skeleton(4, 3, 2, 3, "Skeleton", 2, 0);
            monsters.add(skeleton);
        }
        if (Math.random() * 100 < 100) {   //orc 10
            System.out.println("-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----");
            System.out.println("Watch out, there's an Orc ahead!");
            Monsters orc = new Orc(6, 4, 3, 4, "Orc", 2, 0);
            monsters.add(orc);
        }
        if (Math.random() * 100 < 100) {     //troll 5
            System.out.println("-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----");
            System.out.println("Watch out, there's a Troll ahead!");
            Monsters troll = new Troll(2, 7, 4, 2, "Troll", 2, 0);
            monsters.add(troll);
        }

        if (monsters.size() > 0) {
            battle();
        } else {
            System.out.println("No monsters");
        }

    }

    public static void battle() {
    
        int flee = 0;
        
        EnumsRFun.sortAttackers();

        for (Creatures attacker : EnumsRFun.attackers) {

            for (Monsters monster : monsters) {

                if (attacker.getType() == 1) {
                    while (flee != 1 && player.getHealth() >= 1 && monster.getHealth() >= 1) {
                    System.out.println(" ===============");
                    System.out.println("|YOUR HP: " + player.getHealth() + "     |\n|MONSTER'S HP: " + monster.getHealth() + "|");
                    System.out.println(" ===============");

                    if (player.getHealth() >= 1) {
                        int choice = fightOrFlight();
                        if (choice == 1) {
                            int hAttack = heroAttack(player.getAttack(), monster.getAgility(), monster.getName());
                            if (hAttack == 1) {
                                monster.setHealth(monster.getHealth() - 1);
                            }
                        } else if (choice == 2) {
                            flee = flee(player.getAgility());
                            if (flee == 1) {
                                chooseHero();
                            }
                        }
                    } else {
                        
                    }
                }
            

                }
            }
        }
    }

//        int playerInitSum = rollDice(player.getInitiative());
//
//      for (Monsters monster : monsters) {
//            int flee = 0;
//            int monsterInit = monster.getInitiative();
//            int monsterInitSum = rollDice(monsterInit);
//
//            if (monsterInitSum > playerInitSum) {
//                int mAttack = monsterAttack(monster.getAttack(), player.getAgility(), monster.getName());
//                if (mAttack == 1) {
//                    player.setHealth(player.getHealth() - 1);
//                }
//            } else if (monsterInitSum < playerInitSum) {
//                if (player.getHealth() >= 1) {
//
//                    System.out.println(" ===============");
//                    System.out.println("|YOUR HP: " + player.getHealth() + "     |\n|MONSTER'S HP: " + monster.getHealth() + "|");
//                    System.out.println(" ===============");
//
//                    int choice = fightOrFlight();
//                    if (choice == 1) {
//                        int hAttack = heroAttack(player.getAttack(), monster.getAgility(), monster.getName());
//                        if (hAttack == 1) {
//                            monster.setHealth(monster.getHealth() - 1);
//
//                        }
//                    }
//                    int mAttack = monsterAttack(monster.getAttack(), player.getAgility(), monster.getName());
//                    if (mAttack == 1) {
//                        player.setHealth(player.getHealth() - 1);
//                    }
//                }
//
//            }
//
//            //Hero
//            int heroHP = player.getHealth();
//            int heroInit = player.getInitiative();
//            int heroAttack = player.getAttack();
//            int heroAgility = player.getAgility();
//            int choice;
//            int hAttack;
//            int flee = 0;
//
//            //Monster
//            int monsterHP = monster.getHealth();
//            int monsterInit = monster.getInitiative();
//            int monsterAttack = monster.getAttack();
//            int monsterAgility = monster.getAgility();
//            int mAttack;
//
//            System.out.println("\nThere is a " + monster.getName() + " in the room!");
//
//            int whoToStart = checkInitiative();
//
//            if (whoToStart == 1) {
//                while (flee != 1 && heroHP >= 1 && monsterHP >= 1) {
//                    System.out.println(" ===============");
//                    System.out.println("|YOUR HP: " + heroHP + "     |\n|MONSTER'S HP: " + monsterHP + "|");
//                    System.out.println(" ===============");
//
//                    if (heroHP >= 1) {
//                        choice = fightOrFlight();
//                        if (choice == 1) {
//                            hAttack = heroAttack(heroAttack, monsterAgility, monster.getName());
//                            if (hAttack == 1) {
//                                monsterHP--;
//                            }
//                            mAttack = monsterAttack(monsterAttack, heroAgility, monster.getName());
//                            if (mAttack == 1) {
//                                heroHP--;
//                            }
//                        } else if (choice == 2) {
//                            flee = flee(heroAgility);
//                            if (flee != 1) {
//                                mAttack = monsterAttack(monsterAttack, heroAgility, monster.getName());
//                                if (mAttack == 1) {
//                                    heroHP--;
//                                }
//                            } else if (flee == 1) {
//                                
//                                chooseHero();
//                            }
//                        }
//                    }
//                }
//            } else {
//                while (flee != 1 && heroHP >= 1 && monsterHP >= 1) {
//                    mAttack = monsterAttack(monsterAttack, heroAgility, monster.getName());
//                    if (mAttack == 1) {
//                        heroHP--;
//                    }
//                    System.out.println(" ===============");
//                    System.out.println("|YOUR HP: " + heroHP + "     |\n|MONSTER'S HP: " + monsterHP + "|");
//                    System.out.println(" ===============");
//
//                    if (heroHP >= 1) {
//                        choice = fightOrFlight();
//                        if (choice == 1 && heroHP >= 1) {
//                            hAttack = heroAttack(heroAttack, monsterAgility, monster.getName());
//                            if (hAttack == 1) {
//                                monsterHP--;
//                            }
//                        } else if (choice == 2) {
//                            flee = flee(heroAgility);
//                            if (flee == 1) {
//                                
//                                chooseHero();
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (heroHP <= 0) {
//                System.out.println("\nYou died!");
//            } else if (monsterHP <= 0) {
//                System.out.println("\nYou defeated the " + monster.getName() + "!");
//            }
//        }
//    }
    public static int checkInitiative() {

        int monsterInitSum = 0;
        int heroInitSum = 0;

        for (Monsters monste : monsters) {
            monsterInitSum = rollDice(monste.getInitiative());
            System.out.println("The " + monste.getName() + " initiative is: " + monsterInitSum);

        }

        if (heroInitSum > monsterInitSum) { // Jämför bara gentemot senaste monster initsum
            System.out.println("You start");
            return 1;
        } else {
            System.out.println("The monster starts attacking you");
            return 0;
        }

    }

    public static int fightOrFlight() {
        System.out.println("\nChoose: \n1. Fight \n2. Flee");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            return 1;
        } else if (choice == 2) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int flee(int agility) {
        System.out.println("\nYou try to escape...");

        int chance = agility * 10;

        int result = (int) (Math.random() * 100) + 1; // Drar random tal mellan 1 - 100

        if (result <= chance) {
            System.out.println("and you manage to get away!");
            return 1;
        } else {
            System.out.println("but to no avail, you have to stay and fight one more round!");
            return 0;
        }
    }

    public static int heroAttack(int heroAttack, int monsterAgility, String monster) {

        int attackSum = attack(heroAttack);
        System.out.println("\nYou attack with a level " + attackSum + " attack");

        int agilitySum = dodge(monsterAgility);
        System.out.println("and the " + monster + " try to get away with a level " + agilitySum + " dodge!");

        if (attackSum > agilitySum) {
            System.out.println("So your attack hit the " + monster + " which loses 1 HP!");
            return 1;
        } else {
            System.out.println("So you miss the " + monster + "...");
            return 0;
        }
    }

    public static int monsterAttack(int monsterAttack, int heroAgility, String monster) {

        int attackSum = attack(monsterAttack);
        System.out.println("\nThe " + monster + " attacks you with a level " + attackSum + " attack");

        int agilitySum = dodge(heroAgility);
        System.out.println("and you try to get away with a level " + agilitySum + " dodge!");

        if (attackSum > agilitySum) {
            System.out.println("So the " + monster + " attack hit you an you lose 1 HP...");
            return 1;
        } else {
            System.out.println("So the " + monster + " swings and misses you!");
            return 0;
        }
    }

    public static int attack(int attack) {
        int attackSum = rollDice(attack);
        return attackSum;
    }

    public static int dodge(int inititative) {
        int initiativeSum = rollDice(inititative);
        return initiativeSum;
    }

    public static int rollDice(int numberOfDice) {
        int sum = 0;

        for (int i = 0; i < numberOfDice; i++) {

            int resultOfDie = (int) (Math.random() * 6) + 1; // Slår en tärning med maxvärde 6
            sum += resultOfDie; // Lägger ihop alla tärningsresultat
        }
        return sum;
    }

    public static void playMusic(String filepath) {

        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
