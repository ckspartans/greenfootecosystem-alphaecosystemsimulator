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
    AbstOrganism target = null;
    //REFER TO ABSTORGANISM FOR DOCUMENTATION OF EACH VARIABLE 
    public MeatEater(){
        trophicLevel = 3;
        age = 0;
        world.carnivores.add(this);
        repro_energy = 80;
        siz = 50;
        health = 100;
        energy = 50;
        speed = 5;
        att = 50;
        def = 50;
        mutation_rate = 5;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 30;
        energyFactor = 0;
        
        reproducer = new Reproduce(this);

    }

    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
            //world.carnivores.add(this);
        } 
        //this is where I call all the functions
        targets = getObjectsInRange(sight, AbstOrganism.class);
        move();
        feed();

        grow();
        reproduce();

    }

    public void feed(){
        //if an carnivore is touching a prey (and it isnt algae)
        if(this.isTouching(AbstOrganism.class) && !this.isTouching(Algae.class)){
            //add the appropriate energy to the omnivore
            energy += energyFactor * 0.1;
            //remove the prey from the world
            removeTouching(null);
        }
    }

    public void grow(){
        siz = (int)energy;
        getImage().scale(siz,siz);
    }
    //this program checks if the carnivore has enough energy to reproduce, and then creates a new carnivore
    public void reproduce(){
        if (energy >= repro_energy){

            world.carnivores.add(new MeatEater());
            world.addObject(world.carnivores.get(world.carnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            world.carnivores.add(new MeatEater());
            world.addObject(world.carnivores.get(world.carnivores.size()-1), getX() - Greenfoot.getRandomNumber(200), getY() - Greenfoot.getRandomNumber(200));

            die();
        }
    }

    public void age(){

    }
    public void die(){

        //if they ran out of energy

        //???Add a corspe object???

        //remove them from all lists
        world.carnivores.remove(this);

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
    public int mutate(int trait) {
       int mutate = Greenfoot.getRandomNumber(100);
        // randomly mutates all traits based on initial mutation rate
        if (mutate <= mutation_rate) {
            // traits mutated are mutated by a constant (which can also mutate to change)
            trait = trait+(trait*((int) mutation_rate/100));
        }
        
       return trait;
    }

    public void interact(){

    }
    public void track() {
    }    
}
