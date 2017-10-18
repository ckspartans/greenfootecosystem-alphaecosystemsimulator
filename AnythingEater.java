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
        trophicLevel = 2;
        age = 0;
        world.omnivores.add(this);
        repro_energy = 75;
        siz = 25;//(int) (0.2*energy+5);
        health = 100;
        energy = 50;
        speed = 5;
        att = 50;
        def = 50;
        mutation_rate = 0;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 20;
        energyFactor = 0;
        
        reproducer = new Reproduce(this);

    }
  
    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if( world == null){
            world = (MyWorld) getWorld();
        } 
        //this is where I call all the functions
         targets = getObjectsInRange(sight, AbstOrganism.class);
        move();    
        feed();
        grow();
        reproduce();
    }
    public void feed(){
        //if an omnivore is touching a prey
       if(this.isTouching(Algae.class) || this.isTouching(PlantEater.class) || this.isTouching(AnythingEater.class)){ 
          
               //add the appropriate energy to the omnivore
               energy += energyFactor * 0.1;

                //remove the prey from the world
               removeTouching(null);
        }
    }

    public void grow(){
        siz = (int)energy;//(int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this program checks if the omnivore has enough energy to reproduce, and then creates a new omnivore
    public void reproduce(){
        if (energy >= repro_energy){
                world.omnivores.add(new AnythingEater());
                world.addObject(world.omnivores.get(world.omnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));
 
                    
                world.omnivores.add(new AnythingEater());
                world.addObject(world.omnivores.get(world.omnivores.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

                
                die();
        }
    }
 
    public void age(){
       // System.out.println("Not implemented");
       age++;
    }
    //this function is where the organism dies 
    public void die(){
        //the energy will decrease based off of size of animal

                //???Add a corspe object???
                
                //remove them from all lists
                world.omnivores.remove(this);
                
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
}
