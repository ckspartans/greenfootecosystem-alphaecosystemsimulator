import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
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
        lifeForms = new ArrayList <AbstOrganism> () ;//list of all the organsims in the game
        prey = new ArrayList <Object> ();//list of all that the types of organism can feed on
        predators = new ArrayList <Object> ();//list of all the types of organsims that the organism can be eaten by 
        trophicLevel = 0;
        age = 0;
        lifeForms.add(this);
        repro_energy = 25;
        siz = (int) (0.2*energy+5);
        GreenfootImage [] imgs;
        health = 100;
        energy = 0;
        speed = 20;
        att = 50;
        def = 50;
        mutation_rate = 0;
        MyWorld world;
    }
  
    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
            //lifeForms.add(this);
        } 
        //this is where I call all the functions 
        reproduce();
        age();
        feed();
        grow();
    }
    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
        //increase the energy amount
        //later I will probably take into acount the amount of overlap with other ogranisms
        System.out.println("Feed Not implemented");
        energy += 0.1;
        //I show the energy on the screen so I know what energy the algae is at
        world.showText("Energy" + energy , 100,100);
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        System.out.println("Grow Not implemented");
        siz = (int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this program checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one
    public void reproduce(){
        //check tp see of their is enough energy (size??) to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            //if yes than 
                //call the constructor for two new algaes and kill the original
                    //call die for the parent after calling the constructor
                    
                    //first I need to add an algae to the abstorganism arraylist (so that the new algae actually exists)
                    lifeForms.add(new Algae());
                    //now I add the algae to the world, and let him spawn 20 pixels around the old dead algae
                    world.addObject(lifeForms.get(lifeForms.size()-1), getX() + Greenfoot.getRandomNumber(20), getY() + Greenfoot.getRandomNumber(20));
                    
                    //do it again for one more algae
                    lifeForms.add(new Algae());
                    world.addObject(lifeForms.get(lifeForms.size()-1), getX() - Greenfoot.getRandomNumber(20), getY() - Greenfoot.getRandomNumber(20));
                    
                    //kill the old algae now
                    die();
        }
        System.out.println("Reproduce Not implemented");
    }
    //this function adds age to the algae ( I dont really use it right now, but feel free to use it)
    public void age(){
        //increase age
        //and check to see if past lifespan
        //if then kill them
        age++;
        System.out.println("Not implemented");
    }
    //this function is where the algae dies 
    public void die(){
        //???Add a corspe object???
        
        //remove them from all lists
        lifeForms.remove(this);
        
        //remove them from the world
        world.removeObject(this);
        
        System.out.println("Not implemented");
    }
    //you dont really need this, but if you come up with a cool idea please let me know first
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
}
