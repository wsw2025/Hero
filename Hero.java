import java.util.*;
public class Hero {
    private String name;
    private int hitPoints;

    public Hero(String name){
        this.name = name;
        this.hitPoints = 100;
    }

    public String getName(){
        return this.name;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }

    public String toString(){
        return "Hero{" + "name='" + this.name + "'" + ", hitPoints=" + this.hitPoints + "}";
    }

    public void loseHealth(int dmg){
        if(dmg>this.hitPoints){
            this.hitPoints=0; //lowest hp can only be 0
        }else{
            this.hitPoints-=dmg;
        }
    }
    public void attack(Hero opponent){
        double atk = Math.random();
        if(atk<0.5){
            opponent.loseHealth(10); //opponent receives damage
        }else{
            loseHealth(10); //own hero receives damage
        }
    }

    public void senzuBean(){
        this.hitPoints=100;
    }

    private void fightUntilTheDeathHelper(Hero opponent){
        while(this.hitPoints>0 && opponent.getHitPoints()>0){ //if both own hero and opponent does not have 0 hp
            attack(opponent);
        }
    }

    public String fightUntilTheDeath(Hero opponent){
        senzuBean();
        opponent.senzuBean(); //initialize both heros health to 100
        fightUntilTheDeathHelper(opponent);
        return this.name+": "+this.hitPoints+"\t"+opponent.getName()+": "+opponent.getHitPoints(); //prints both heros hp
    }

    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        int[] wins = new int[2];
        while(n>0){
            senzuBean();
            opponent.senzuBean();
            fightUntilTheDeathHelper(opponent);
            if(this.hitPoints>0){
                wins[0]++;
            }else{
                wins[1]++;
            }
            n--;
        }
        return wins;
    }

    public String nFightsToTheDeath(Hero opponent, int n){
        int[] arr = nFightsToTheDeathHelper(opponent, n); //wins recorded for both hero
//        System.out.println(Arrays.toString(arr));
        String msg = "";
        if(arr[0]>arr[1]){ //test which hero won more to determine end msg
            msg = this.name + " wins!";
        }else if(arr[0]==arr[1]){
            msg = "OMG! It was actually a draw!";
        }else{
            msg = opponent.getName()+" wins!";
        }
        return this.name + ": " + arr[0] + " wins\n" + opponent.getName()+": " + arr[1] + " wins\n" + msg;
//        return ": " + arr[0] + "\n" + arr[1] + "\n" + "wins!";
    }

    public void dramaticFightToTheDeath(Hero opponent){
        while(this.hitPoints>0 && opponent.getHitPoints()>0){ //test whether both heros are still alive
            attack(opponent);
            System.out.println(this.name+": "+this.hitPoints+"\t"+opponent.getName()+": "+opponent.getHitPoints());
        }
        if(this.hitPoints>0){ //test which hero is still alive
            System.out.print(this.name + " wins!");
        }else{
            System.out.print(opponent.getName()+" wins!");
        }
    }
}
