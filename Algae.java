import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**NOTES
 * 
 * need to have boundaries but they wont f*cking work
 * 
 * 
 */

/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Algae extends AbstOrganism
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //basic default constructor, this is where I initialize all the variables that must be in this class (because it inherits from the abstract class)
    public Algae(){
        prey = new ArrayList <Object> ();//list of all that the types of organism can feed on
        predators = new ArrayList <Object> ();//list of all the types of organsims that the organism can be eaten by 
        trophicLevel = 0;
        age = 0;
        lifeforms.add(this);
        repro_energy = 40;
        siz = 5;
        GreenfootImage [] imgs;
        health = 100;
        energy = 0;
        speed = 0;
        att = 0;
        def = 50;
        mutation_rate = 0;
        MyWorld world;
        deathCheck = true;
    }

    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
        } 
        //this is where I call all the functions 
        reproduce();
        feed();
        grow();
        age();

        //restricts x and y values to the map size
        if (getWorld()!=null){
            //System.out.println("Ohhh");
            System.out.println(" " + getX() + " " + getY());
            if (getX() <= 0){die();}
            else if (getX() >= world.getWidth()){die();}
            else if (getY() <= 0){die();}
            else if (getY() >= world.getHeight()){die();}
            //System.out.println("Yaaaaaa");
        }

        System.out.println("Lifeforms" + lifeforms.size());
    }
    //universal timer
    public double uniTimer(double update, double updateSpeed){
        for(int i = 0; i <= 60; i++){
            if (i != 60){}else{
                update+=updateSpeed;
            }
        }
        return update;
    }

    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
        //increase the energy amount
        //later I will probably take into acount the amount of overlap with other ogranisms
        System.out.println("Feed Not implemented");
        energy += (double)Greenfoot.getRandomNumber(3)/10;//makes algae grow at different rates
        //I show the energy on the screen so I know what energy the algae is at
        world.showText("Energy" + energy , 100,100);
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        System.out.println("Grow Not implemented");
        int sizX = getImage().getWidth();
        int sizY = getImage().getHeight();
        sizX = (int)(0.2*energy+5);
        sizY = (int)(0.2*energy+5);
        getImage().scale(sizX,sizY);
        setImage(getImage());

    }
    //this program checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one
    public void reproduce(){
        //check tp see of their is enough energy (size??) to split
        //if yes then call the constructor for new ones and kill the last one
        if((energy >= repro_energy)){
            //if yes than 
            //call the constructor for two new algaes and kill the original
            //call die for the parent after calling the constructor

            for (int o = 0; o < 2; o++){
                //first I need to add an algae to the abstorganism arraylist (so that the new algae actually exists)
                lifeforms.add(new Algae());
                //now I add the algae to the world, and let him spawn around the old dead algae
                int x = getX() + Greenfoot.getRandomNumber(20)-Greenfoot.getRandomNumber(40);
                int y = getY() + Greenfoot.getRandomNumber(20)-Greenfoot.getRandomNumber(40);

                getWorld().addObject(lifeforms.get(lifeforms.size()-1), x, y);//creates new object

                
            }
            //kill the old algae now
            energy = 0;
            die();

        }
        System.out.println("Reproduce implemented");
    }
    //this function adds age to the algae, but accelerates it too fast. needs work
    public void age(){
        //increase age
        //and check to see if past lifespan
        //if then kill them
        //double rate = Greenfoot.getRandomNumber(35);//random rate of growth, allowing for varrying ages
        age = uniTimer(age, 0.175);//incriments the the age NOTE:0.02 will incriment at 1 per second
        //average lifespan is around 68
        //creates 10% odds of death when they turn 65
        if(deathCheck == true){
            if(age > 65){
                int death = Greenfoot.getRandomNumber(100);
                if(death > 10){
                    deathCheck = false;
                } else if(death <= 10){//10% chance of death
                    die();
                    //System.out.println(" This worked");
                    deathCheck = false;
                }

            }
        }
        //death unavoidable after 75
        if(age > 75){
            die();
        }
        world.showText("Age" + age , 100,300);
        System.out.println("Age implemented");
    }
    //this function is where the algae dies 
    public void die(){
        //???Add a corspe object??? maybe

        //remove them from the world if not removed already
        if (getWorld()!=null){
            getWorld().removeObject(this);
        }

        //remove them from all lists
        lifeforms.remove(this);

        System.out.println("Die not implemented");
    }
    //you dont really need this, but can evole into alage latching onto carnivores
    public void move(){
        //not needed
        System.out.println("Not implemented");
    }
    //try to complete this
    public void mutate(){
        //need a way to change the specific object
        //rubber band them to have tradeoffs (age to reproduce to etc_
        System.out.println("Not implemented");
    }

    public void interact() {
        //not implmented ...... not needed
    }
}
