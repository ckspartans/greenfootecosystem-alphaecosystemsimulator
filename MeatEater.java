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
        repro_energy = 200;
        siz = 50;//(int) (0.2*energy+5);
        health = 100;
        energy = 50;
        speed = 5;
        att = 50;
        def = 50;
        mutation_rate = 5;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 150;
        energyFactor = 0;
  
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
        
        //age();
        //interact();
                grow();
       
        reproduce();
        //die();
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
        //increase the size of the s image bases on the current energy
        //System.out.println("Grow Not implemented");
        siz = (int)energy;// (int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this program checks if the carnivore has enough energy to reproduce, and then creates a new carnivore
    public void reproduce(){
        if (energy >= repro_energy){
                int range = 20;//spawn range around parent
                int x = getX() - range + Greenfoot.getRandomNumber(range*2);
                int y = getY() - range + Greenfoot.getRandomNumber(range*2);
                
                world.carnivores.add(new MeatEater());
                world.addObject(world.carnivores.get(world.carnivores.size()-1), x, y);
                world.carnivores.get(world.carnivores.size()-1).repro_energy = mutate(repro_energy);
                world.carnivores.get(world.carnivores.size()-1).siz = mutate(siz);
                world.carnivores.get(world.carnivores.size()-1).health = mutate((int) health);
                world.carnivores.get(world.carnivores.size()-1).energy = mutate((int) energy);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(speed);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(def);
                world.carnivores.get(world.carnivores.size()-1).att = mutate(att);
                world.carnivores.get(world.carnivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.carnivores.get(world.carnivores.size()-1).sight = mutate(sight);
                
                world.carnivores.add(new MeatEater());
                world.addObject(world.carnivores.get(world.carnivores.size()-1), x, y);
                world.carnivores.get(world.carnivores.size()-1).repro_energy = mutate(repro_energy);
                world.carnivores.get(world.carnivores.size()-1).siz = mutate(siz);
                world.carnivores.get(world.carnivores.size()-1).health = (double) mutate((int) health);
                world.carnivores.get(world.carnivores.size()-1).energy = (double) mutate((int) energy);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(speed);
                world.carnivores.get(world.carnivores.size()-1).speed = mutate(def);
                world.carnivores.get(world.carnivores.size()-1).att = mutate(att);
                world.carnivores.get(world.carnivores.size()-1).mutation_rate = (double) mutate((int) mutation_rate);
                world.carnivores.get(world.carnivores.size()-1).sight = mutate(sight);
                
                die();
        }
    }
   
    public void age(){

    }
    //this function is where the organism dies 
    public void die(){
    //the energy will decrease based off of size of animal
        energy = energy - (0.01 * siz);
        //if they ran out of energy
        if (energy < 1){
                //???Add a corspe object???
                
                //remove them from all lists
                world.carnivores.remove(this);
                
                //remove them from the world
                world.removeObject(this);
         
        }
    }
    //you dont really need this, but if you come up with a cool idea please let me know first
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
        //getObjectsInRange(1,null));
        
        
   }
   public void track() {

   }    
}
    

