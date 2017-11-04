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
        reproduce();
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

    public void grow(){
        energy -= (siz *0.0001);
        siz = (int)energy + 1;//(int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this program checks if the omnivore has enough energy to reproduce, and then creates a new omnivore
    public void reproduce(){
        if (energy >= repro_energy){
            world.omnivores.add(new AnythingEater());
            world.addObject(world.omnivores.get(world.omnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
            world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
            world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
            world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
            
            world.omnivores.add(new AnythingEater());
            world.addObject(world.omnivores.get(world.omnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));
            
            world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
            world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
            world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
            world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
            
            die();
        }
    }

    public void age(){
        // System.out.println("Not implemented");
    }
    //this function is where the organism dies 
    public void die(){
        //the energy will decrease based off of size of animal

        //???Add a corspe object???
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
    //try to complete this
    public int mutate(int trait) {
       int mutate = Greenfoot.getRandomNumber(100);
        
        if (mutate <= mutation_rate) {
            trait = trait+(trait*((int) mutation_rate/100));
        }
        
       return trait;
    }
}