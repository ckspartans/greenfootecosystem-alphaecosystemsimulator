import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MeatEater extends AbstOrganism
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //basic default constructor, this is where I initialize all the variables that must be in this class (because it inherits from the abstract class)
    
    //target is used to store the animals target
    AbstOrganism target = null;
    //REFER TO ABSTORGANISM FOR DOCUMENTATION OF EACH VARIABLE
    EnergyBar energyIndicator;
    
    public MeatEater(){
        //this is an omnivore, hence trophicLevel = 4 (the highest)
        trophicLevel = 4;
        age = 0;
        //add the carnivore to the world when created
        world.carnivores.add(this);
        repro_energy = 80;
        siz = 50;
        health = 100;
        energy = 50;
        speed = 2;
        att = 50;
        def = 10;
        mutation_rate = 25;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 75;
        energyFactor = 0;
        energyIndicator  = new EnergyBar(this,(int)energy);

    }

    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
            //world.carnivores.add(this);
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
        reproduce();
        if(energy <= 1){
            die();
        }
    }

    public void feed(){
        //if an carnivore is touching a prey (and it isnt algae)
        if(this.isTouching(AnythingEater.class) || this.isTouching(PlantEater.class) || this.isTouching(Scavenger.class) || this.isTouching(MeatEater.class)){
            //add the appropriate energy to the carnivore
            energy += energyFactor * 0.1;
            thinker.kill();
            //remove the prey from the world
            removeTouching(AbstOrganism.class);
        }
    }
    //Here I use the energy that the carnivore has to grow the carnivore to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        energy -= (siz *0.0001);
        siz = (int)energy + 1;
        getImage().scale(siz,siz);
    }
    //this function checks if the carnivore has enough energy to reproduce, and then creates two new carnivores and kills off the old one while the new ones inherit the previous carnivore's traits and have a chance to mutate their traits
    public void reproduce(){
        //check to see of there is enough energy to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){

            // spawn new carnivore
            world.carnivores.add(new MeatEater());
            world.addObject(world.carnivores.get(world.carnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            // new carnivore inherits original carnivore's traits and has a chance to mutate them
            world.carnivores.get(world.carnivores.size()-1).repro_energy = mutate(repro_energy);
            world.carnivores.get(world.carnivores.size()-1).siz = mutate(siz);
            world.carnivores.get(world.carnivores.size()-1).speed = mutate(speed);
            world.carnivores.get(world.carnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.carnivores.get(world.carnivores.size()-1).sight = mutate(sight);
            
            // spawn new carnivore
            world.carnivores.add(new MeatEater());
            world.addObject(world.carnivores.get(world.carnivores.size()-1), getX() - Greenfoot.getRandomNumber(200), getY() - Greenfoot.getRandomNumber(200));

            // new carnivore inherits original carnivore's traits and has a chance to mutate them
            world.carnivores.get(world.carnivores.size()-1).repro_energy = mutate(repro_energy);
            world.carnivores.get(world.carnivores.size()-1).siz = mutate(siz);
            world.carnivores.get(world.carnivores.size()-1).speed = mutate(speed);
            world.carnivores.get(world.carnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.carnivores.get(world.carnivores.size()-1).sight = mutate(sight);
            
            // kill original carnivore
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
        world.carnivores.remove(this);
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

    public void track() {
    }    
}
