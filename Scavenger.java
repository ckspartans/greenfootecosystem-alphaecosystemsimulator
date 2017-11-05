import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scavenger extends AbstOrganism 
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
     
    //basic default constructor, this is where I initialize all the variables that must be in this class (because it inherits from the abstract class)
    
    //REFER TO ABSTORGANISM FOR DOCUMENTATION OF EACH VARIABLE
    public Scavenger(){
        //this is a scavenger, hence trophicLevel = 2
        trophicLevel = 2;
        age = 0;
        repro_energy = 75;
        siz = 30;//(int) (0.2*energy) + 50;
        health = 100;
        energy = 50;
        speed = 5;
        att = 50;
        def = 50;
        mutation_rate = 25;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        reproducer = new Reproduce(this);
        sight = 300;
        energyFactor = 0;
        energyIndicator  = new EnergyBar(this,(int)energy); 
    }

    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
            world.addObject(energyIndicator,-1000,0);
        } 
        //this is where I call all the functions
        if(energyIndicator != null){
            energyIndicator.update();
        }
        targets = getObjectsInRange(sight, AbstOrganism.class);
        move();
        feed();

        grow();
        //age();
        //interact();
        reproducer.reproduce();
        //System.out.println("H : " + energy);

        // System.out.println("" + energyFactor);
        //die();
        if(energy <= 1){
            die();
        }
    }
    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
        
        //if a scavenger is touching a carrion
        if(this.isTouching(DeadOrganism.class)){
            
            //increase the energy amount
            energy += energyFactor * 0.1;
            //remove them from all lists
            thinker.kill();
            //remove them from the world
            removeTouching(AbstOrganism.class);
        }
    }
    //Here I use the energy that the herbivore has to grow the herbivore to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        //System.out.println("Grow Not implemented");
        energy -= (siz *0.0001);
        siz = (int)energy + 1; //(int) (0.2*energy+10);
        getImage().scale(siz,siz);
    }
    //this function checks if the scavenger has enough energy to reproduce, and then creates two new scavengers and kills off the old one while the new ones inherit the previous scavenger's traits and have a chance to mutate their traits
    public void reproduce(){
        //check to see of there is enough energy to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            // spawn new scavenger
            world.scavengers.add(new Scavenger());
            world.addObject(world.scavengers.get(world.scavengers.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            // new scavenger inherits original scavenger's traits and has a chance to mutate them
            world.scavenger.get(world.scavengers.size()-1).repro_energy = mutate(repro_energy);
            world.scavenger.get(world.scavengers.size()-1).siz = mutate(siz);
            world.scavenger.get(world.scavengers.size()-1).speed = mutate(speed);
            world.scavenger.get(world.scavengers.size()-1).mutation_rate = mutate(mutation_rate);
            world.scavenger.get(world.scavengers.size()-1).sight = mutate(sight);
            
            // spawn new scavenger
            world.scavengers.add(new Scavenger());
            world.addObject(world.scavengers.get(world.scavengers.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            // new scavenger inherits original scavenger's traits and has a chance to mutate them
            world.scavenger.get(world.scavengers.size()-1).repro_energy = mutate(repro_energy);
            world.scavenger.get(world.scavengers.size()-1).siz = mutate(siz);
            world.scavenger.get(world.scavengers.size()-1).speed = mutate(speed);
            world.scavenger.get(world.scavengers.size()-1).mutation_rate = mutate(mutation_rate);
            world.scavenger.get(world.scavengers.size()-1).sight = mutate(sight);
            
            // kill original scavenger
            die();
        }
        //System.out.println("Reproduce Not implemented");
    }
    //this function adds age to the scavenger
    public void age(){
        //increase age
        //and check to see if past lifespan
        //if then kill them
        energy -= 0.25;
        // System.out.println("Not implemented");
    }
    //this function is where the scavenger dies 
    public void die(){
        //energy = (energy) - (0.02 * siz);

        // Add a corspe object

        //remove them from all lists
        world.scavengers.remove(this);
        world.removeObject(energyIndicator);
        //remove them from the world
        world.removeObject(this);

        //System.out.println("Not implemented");

    }
    //you dont really need this, but if you come up with a cool idea please let me know first
    public void move(){
        //not needed
        //System.out.println("Not implemented");
        //setLocation(getX() + Greenfoot.getRandomNumber(5), getY() + Greenfoot.getRandomNumber(5));
        move((int)speed);
        if(targets != null && !targets.isEmpty()){
            // System.out.println("YEA");
            thinker.track();
        }

        // System.out.println("NO");
        //setLocation((int)(getX() + Math.cos(angle) * speed), (int)(getY() + Math.sin(angle) * speed));

        else if (targets == null || targets.isEmpty()) {
            // System.out.println("NO");
            //setLocation((int)(getX() + Math.cos(angle) * speed), (int)(getY() + Math.sin(angle) * speed));
            if(Greenfoot.getRandomNumber(100) < 10){
                turn(Greenfoot.getRandomNumber(180));
            }
        }

        if(isAtEdge()){
            turn(180);
        }
    }
    /// when traits of an organism are inputed, there is a chance that it mutates and returns the newly mutated trait
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

    public void interact(){
        //not implemented yet
        //getObjectsInRange(1,null));

    } 
}

