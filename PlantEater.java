import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */    
public class PlantEater extends AbstOrganism 
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
     
    //basic default constructor, this is where I initialize all the variables that must be in this class (because it inherits from the abstract class)
    

    //This object target is used to get information about the target organism
    AbstOrganism target = null;
    //REFER TO ABSTORGANISM FOR DOCUMENTATION OF EACH VARIABLE

    public PlantEater(){
       //this is a herbivore, hence trophicLevel = 1
        trophicLevel = 1;
        age = 0;
        //add the herbivore to the world when created
        world.herbivores.add(this);
        repro_energy = 75;
        siz = 25;
        health = 100;
        energy = 40;
        speed = 4;
        att = 0;
        def = 50;
        mutation_rate = 25;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 100;
        energyFactor = 0;
        energyIndicator =  new EnergyBar(this,(int)energy);
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
        targets = getObjectsInRange(sight, AbstOrganism.class);
        if(energyIndicator != null){
            energyIndicator.update();
        }
        move();
        feed();
        grow();   
        reproduce();

        if(energy <= 1){
            die();
        }

    }

    public void feed(){
         //if a herbivore is touching an algae
        if(this.isTouching(Algae.class)){
            //add the appropriate energy to the herbivore
            energy += energyFactor * 0.1;
            thinker.kill();
            //remove prey from the world
            removeTouching(AbstOrganism.class);
        }
    }
    //Here I use the energy that the herbivore has to grow the herbivore to a certain size
    public void grow(){
       //increase the size of the image based on the current energy
        energy -= (siz *0.0001);
        siz = (int)energy + 1; //(int) (0.2*energy+10);
        getImage().scale(siz,siz);
    }
    //this function checks if the herbivore has enough energy to reproduce, and then creates two new herbivore and kills off the old one while the new ones inherit the previous herbivore's traits and have a chance to mutate their traits
    public void reproduce(){
        //check to see of there is enough energy to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            
            // spawn new herbivore
            world.herbivores.add(new PlantEater());
            world.addObject(world.herbivores.get(world.herbivores.size()-1), getX() + Greenfoot.getRandomNumber(100), getY() + Greenfoot.getRandomNumber(100));
            
            // new herbivore inherits original herbivore's traits and has a chance to mutate them
            world.herbivores.get(world.herbivores.size()-1).repro_energy = mutate(repro_energy);
            world.herbivores.get(world.herbivores.size()-1).siz = mutate(siz);
            world.herbivores.get(world.herbivores.size()-1).speed = mutate(speed);
            world.herbivores.get(world.herbivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.herbivores.get(world.herbivores.size()-1).sight = mutate(sight);
            
            // spawn new herbivore
            world.herbivores.add(new PlantEater());
            world.addObject(world.herbivores.get(world.herbivores.size()-1), getX() - Greenfoot.getRandomNumber(100), getY() - Greenfoot.getRandomNumber(100));
            
            // new herbivore inherits original herbivore's traits and has a chance to mutate them
            world.herbivores.get(world.herbivores.size()-1).repro_energy = mutate(repro_energy);
            world.herbivores.get(world.herbivores.size()-1).siz = mutate(siz);
            world.herbivores.get(world.herbivores.size()-1).speed = mutate(speed);
            world.herbivores.get(world.herbivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.herbivores.get(world.herbivores.size()-1).sight = mutate(sight);
            
            // kill original herbivore
            die();
        }
    }

    public void age(){

    }
    //this function is where the organism dies 
    public void die(){
        //if they ran out of energy
        // Add a corspe object
        world.carrion.add(new DeadOrganism());
        world.addObject(world.carrion.get(world.carrion.size()-1),getX(),getY());

        //remove them from all lists
        world.herbivores.remove(this);
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
            if(Greenfoot.getRandomNumber(100) < 20){
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
