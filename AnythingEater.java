import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnythingEater extends AbstOrganism 
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //basic default constructor, this is where I initialize all the variables that must be in this class (because it inherits from the abstract class)

    //target is used to store the animals target
    AbstOrganism target = null;

    //REFER TO ABSTORGANISM FOR DOCUMENTATION OF EACH VARIABLE

    public AnythingEater(){
        //this is an omnivore, hence trophicLevel = 2
        trophicLevel = 3;
        age = 0;
        //add the omnivore to the world when created
        world.omnivores.add(this);
        repro_energy = 100;
        siz = 25;//(int) (0.2*energy+5);
        health = 100;
        energy = 50;
        speed = 3;
        att = 30;
        def = 25;
        mutation_rate = 25;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        reproducer = new Reproduce(this);
        sight = 50;
        energyFactor = 0;
        energyIndicator = new EnergyBar(this,(int)energy);

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
        reproducer.reproduce();
        if(energy <= 1){
            die();
        }

    }

    public void feed(){
        //if an omnivore is touching a prey
        if(this.isTouching(Algae.class) || this.isTouching(PlantEater.class) || this.isTouching(AnythingEater.class) || this.isTouching(Scavenger.class)){ 

            //add the appropriate energy to the omnivore
            energy += energyFactor * 0.1;
            thinker.kill();
            //remove the prey from the world
            removeTouching(AbstOrganism.class);
        }
    }
    
    //Here I use the energy that the omnivore has to grow the omnivore to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        energy -= (siz *0.0001);
        siz = (int)energy + 1;//(int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this function checks if the omnivore has enough energy to reproduce, and then creates two new omnivores and kills off the old one while the new ones inherit the previous omnivore's traits and have a chance to mutate their traits
    public void reproduce(){
        //check to see of there is enough energy to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
        
            // spawn new omnivore
            world.omnivores.add(new AnythingEater());
            world.addObject(world.omnivores.get(world.omnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));
            // new omnivore inherits original omnivore's traits and has a chance to mutate them
            world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
            world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
            world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
            world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
            
            // spawn new omnivore
            world.omnivores.add(new AnythingEater());
            world.addObject(world.omnivores.get(world.omnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));
            // new omnivore inherits original omnivore's traits and has a chance to mutate them
            world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
            world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
            world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
            world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
            
            // kill original omnivore
            die();
        }
    }

    public void age(){
        // System.out.println("Not implemented");
    }
    //this function is where the organism dies 
    public void die(){
        //the energy will decrease based off of size of animal

        /// Add a corspe object
        world.carrion.add(new DeadOrganism());
        world.addObject(world.carrion.get(world.carrion.size()-1),getX(),getY());

        //remove them from all lists
        world.omnivores.remove(this);
        world.removeObject(energyIndicator);
        //remove them from the world
        world.removeObject(this);

       
    }

    public void move(){

        //move constantly
        move((int)speed);
        //if there are targets nearby
        if(targets != null && !targets.isEmpty()){
            //track them
            thinker.track();
        }
        //if there are no targets nearby
        else if (targets == null || targets.isEmpty()) {
            //keep moving randomly and try to find them
            if(Greenfoot.getRandomNumber(100) < 10){
                turn(Greenfoot.getRandomNumber(180));
            }
        }
        //if your at the edge
        if(isAtEdge()){
            //turn in other direction
            turn(180);
        }
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
