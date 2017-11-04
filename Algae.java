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
        //since this is the lowest on the food chain, I give it a trophicLevel of value 0
        trophicLevel = 0;
        //always set the age to 0 when an algae is created
        age = 0;
        //add the algae to the world when created
        world.plants.add(this);
        //This means that the algae needs 25 energy to reproduce
        repro_energy = 150;
        //This means that the algae will be 100 by 100 in size
        siz = 100;
        //the health will start out at 100
        health = 100;
        //the algae will have 100 energy
        energy = 75;
        speed = 0;
        att = 50;
        def = 50;
        mutation_rate = 25;
        MyWorld world;
        targets = null;
        sight = 0;
        energyIndicator =  new EnergyBar(this,(int)energy);
        energyFactor = 0; 
    }

    public void act() 
    {

        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
            world.plants.add(this);
            world.addObject(energyIndicator,-1000,0);
        } 
        //this is where I call all the functions
                if(energyIndicator != null){
        energyIndicator.update();
       }
        age();
        grow();
        feed();
        reproduce();
       
    }
    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
        //increase the energy amount
        energy += 0.1;
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        siz = (int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this function checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one while the new ones inherit the previous algae's traits and have a chance to mutate their traits
    public void reproduce(){
        //check to see of there is enough energy to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){

            // spawn new algae
            world.plants.add(new Algae());
            world.addObject(world.plants.get(world.plants.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            // new algae inherits original algae's traits and has a chance to mutate them
            world.plants.get(world.plants.size()-1).repro_energy = mutate(repro_energy);
            world.plants.get(world.plants.size()-1).siz = mutate(siz);
            world.plants.get(world.plants.size()-1).speed = mutate(speed);
            world.plants.get(world.plants.size()-1).mutation_rate = mutate(mutation_rate);
            world.plants.get(world.plants.size()-1).sight = mutate(sight);
            
            // spawn new algae
            world.plants.add(new Algae());
            world.addObject(world.plants.get(world.plants.size()-1), getX() - Greenfoot.getRandomNumber(200), getY() - Greenfoot.getRandomNumber(200));

            // new algae inherits original algae's traits and has a chance to mutate them
            world.plants.get(world.plants.size()-1).repro_energy = mutate(repro_energy);
            world.plants.get(world.plants.size()-1).siz = mutate(siz);
            world.plants.get(world.plants.size()-1).speed = mutate(speed);
            world.plants.get(world.plants.size()-1).mutation_rate = mutate(mutation_rate);
            world.plants.get(world.plants.size()-1).sight = mutate(sight);
            
            // kill original algae
            die();
        }
    }
    //this function adds age to the algae ( I dont really use it right now, but feel free to use it)
    public void age(){
    }
    //this function is where the algae dies 
    public void die(){
        // Add a corspe object
        //remove them from all lists
        world.plants.remove(this);
                //System.out.println("Not implemented");
        world.removeObject(energyIndicator);
        //remove them from the world
        world.removeObject(this);
    }
    
    public void move(){
        //not needed
        //System.out.println("Not implemented");
    }
    
    // when traits of an organism are inputed, there is a chance that it mutates and returns the newly mutated trait
    public int mutate(int trait) {
       // generates random number between 0 and 100
       int mutate = Greenfoot.getRandomNumber(100);
        
        // if the random number is less than or equal to the mutation rate
        if (mutate <= mutation_rate) {
            // the inputed trait is mutated by adding the initial trait by the mutation factor
            trait = trait+(trait*((int) mutation_rate/100));
        }
        
       // returns initial/mutated trait depending on if mutated
       return trait;
    }
}
