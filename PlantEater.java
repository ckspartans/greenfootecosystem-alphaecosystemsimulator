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

    //This object target is used to get information about the target organism
    AbstOrganism target = null;
    //REFER TO ABSTORGANISM FOR DOCUMENTATION OF EACH VARIABLE
    public PlantEater(){
        trophicLevel = 1;
        age = 0;
        world.herbivores.add(this);
        repro_energy = 200;
        siz = 25;//(int) (0.2*energy) + 50;
        
        health = 100;
        energy = 50;
        speed = 5;
        att = 50;
        def = 50;
        mutation_rate = 5;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 100;
        energyFactor = 0;
        
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
        //age();
        //interact();
        reproduce();

        //die();
    }
    public void feed(){
       if(this.isTouching(Algae.class)){
					//add the appropriate energy to the herbivore
                    energy += energyFactor * 0.1; 
                //remove prey from the world
                 removeTouching(null);
        }
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        siz = (int)energy; //(int) (0.2*energy+10);
        getImage().scale(siz,siz);
    }
    //this program checks if the herbivore has enough energy to reproduce, and then creates a new omnivore
    public void reproduce(){
        if (energy >= repro_energy){
                int range = 20;//spawn range around parent
                int x = getX() - range + Greenfoot.getRandomNumber(range*2);
                int y = getY() - range + Greenfoot.getRandomNumber(range*2);
                
                world.herbivores.add(new PlantEater());
                world.addObject(world.herbivores.get(world.herbivores.size()-1), x, y);
                world.herbivores.get(world.herbivores.size()-1).repro_energy = mutate(repro_energy);
                world.herbivores.get(world.herbivores.size()-1).siz = mutate(siz);
                world.herbivores.get(world.herbivores.size()-1).health = mutate((int) health);
                world.herbivores.get(world.herbivores.size()-1).energy = mutate((int) energy);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(speed);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(def);
                world.herbivores.get(world.herbivores.size()-1).att = mutate(att);
                world.herbivores.get(world.herbivores.size()-1).mutation_rate = mutate((int) mutation_rate);
                world.herbivores.get(world.herbivores.size()-1).sight = mutate(sight);
                
                world.herbivores.add(new PlantEater());
                world.addObject(world.herbivores.get(world.herbivores.size()-1), x, y);
                world.herbivores.get(world.herbivores.size()-1).repro_energy = mutate(repro_energy);
                world.herbivores.get(world.herbivores.size()-1).siz = mutate(siz);
                world.herbivores.get(world.herbivores.size()-1).health = (double) mutate((int) health);
                world.herbivores.get(world.herbivores.size()-1).energy = (double) mutate((int) energy);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(speed);
                world.herbivores.get(world.herbivores.size()-1).speed = mutate(def);
                world.herbivores.get(world.herbivores.size()-1).att = mutate(att);
                world.herbivores.get(world.herbivores.size()-1).mutation_rate = (double) mutate((int) mutation_rate);
                world.herbivores.get(world.herbivores.size()-1).sight = mutate(sight);
                
                die();
        }
    }
    public void age(){

    }
    //this function is where the algae dies 
    public void die(){
        //the energy will decrease based off of size of animal
        energy = (energy) - (0.01 * siz);
		//if they ran out of energy
        if (energy < 1){
                //???Add a corspe object???
                
                //remove them from all lists
                world.herbivores.remove(this);
                
                //remove them from the world
                world.removeObject(this);
               
        }
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
}
    

