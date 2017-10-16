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
        mutation_rate = 0;
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
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            //if yes than 
                //call the constructor for a new herbivore
                    //call die for the parent after calling the constructor
                    
                    //first I need to add a carnivore to the abstorganism arraylist (so that the new herbivore actually exists)
                    world.herbivores.add(new PlantEater());
                    //now I add the algae to the world, and let it spawn 20 pixels around the parent carnivore
                    world.addObject(world.herbivores.get(world.herbivores.size()-1), getX() + Greenfoot.getRandomNumber(50), getY() + Greenfoot.getRandomNumber(50));
                 
                    //die();
                    energy = 50;
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
    public void mutate(){
        //need a way to change the specific object
        //rubber band them to have tradeoffs (age to reproduce to etc_
        //System.out.println("Not implemented");
   }
    public void interact(){
        //not implemented yet
        //getObjectsInRange(1,null));
        
        
   } 
}
    

