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
        repro_energy = 100;
        //This means that the algae will be 100 by 100 in size
        siz = 100;
        //the health will start out at 100
        health = 100;
        //the algae will have 50 energy
        energy = 50;
        speed = 0;
        att = 50;
        def = 50;
        mutation_rate = 5;
        MyWorld world;
        targets = null;
        sight = 0;
        energyFactor = 0;
     
    }
  
    public void act() 
    {
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
            world.plants.add(this);
        } 
        //this is where I call all the functions
        reproduce();
        age();
        grow();
        feed();
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
    //this program checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one
    public void reproduce(){
        if (energy >= repro_energy){
                int range = 20;//spawn range around parent
                int x = getX() - range + Greenfoot.getRandomNumber(range*2);
                int y = getY() - range + Greenfoot.getRandomNumber(range*2);
                
                world.plants.add(new Algae());
                world.addObject(world.plants.get(world.plants.size()-1), x, y);
                world.plants.get(world.plants.size()-1).repro_energy = mutate(repro_energy);
                world.plants.get(world.plants.size()-1).siz = mutate(siz);
                world.plants.get(world.plants.size()-1).health = mutate((int) health);
                world.plants.get(world.plants.size()-1).energy = mutate((int) energy);
                world.plants.get(world.plants.size()-1).speed = mutate(speed);
                world.plants.get(world.plants.size()-1).speed = mutate(def);
                world.plants.get(world.plants.size()-1).att = mutate(att);
                world.plants.get(world.plants.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.plants.get(world.plants.size()-1).sight = mutate(sight);
                
                world.plants.add(new Algae());
                world.addObject(world.plants.get(world.plants.size()-1), x, y);
                world.plants.get(world.plants.size()-1).repro_energy = mutate(repro_energy);
                world.plants.get(world.plants.size()-1).siz = mutate(siz);
                world.plants.get(world.plants.size()-1).health = (double) mutate((int) health);
                world.plants.get(world.plants.size()-1).energy = (double) mutate((int) energy);
                world.plants.get(world.plants.size()-1).speed = mutate(speed);
                world.plants.get(world.plants.size()-1).speed = mutate(def);
                world.plants.get(world.plants.size()-1).att = mutate(att);
                world.plants.get(world.plants.size()-1).mutation_rate = (double) mutate((int) mutation_rate);
                world.plants.get(world.plants.size()-1).sight = mutate(sight);
                
                die();
        }
    }
    //this function adds age to the algae ( I dont really use it right now, but feel free to use it)
    public void age(){
        //increase age
        //and check to see if past lifespan
        //if then kill them
        age++;
        //System.out.println("Not implemented");
    }
    //this function is where the algae dies 
    public void die(){
        //???Add a corspe object???
        
        //remove them from all lists
        world.plants.remove(this);
        
        //remove them from the world
        world.removeObject(this);
        
        //System.out.println("Not implemented");
    }
    //you dont really need this, but if you come up with a cool idea please let me know first
    public void move(){
        //not needed
        //System.out.println("Not implemented");
    }
    //try to complete this
    public int mutate(int trait) {
       int mutate = Greenfoot.getRandomNumber(100);
        
        if (mutate <= mutation_rate) {
            trait = trait+(trait*((int) mutation_rate/100));
        }
        
       return trait;
    }
    public void interact(){
        //not implemented yet
    }
}
