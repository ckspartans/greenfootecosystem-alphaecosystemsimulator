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
        mutation_rate = 0;
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
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            //if yes than 
                //call the constructor for a new carnivore
                    //call die for the parent after calling the constructor
                    
                    //first I need to add a carnivore to the abstorganism arraylist (so that the new carnivore actually exists)
                    world.carnivores.add(new MeatEater());
                    //now I add the algae to the world, and let it spawn 20 pixels around the parent carnivore
                    world.addObject(world.carnivores.get(world.carnivores.size()-1), getX() + Greenfoot.getRandomNumber(20), getY() + Greenfoot.getRandomNumber(20));
                 
                     
                    //die();
                    energy = 50;
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
    public void mutate(){
        //need a way to change the specific object
        //rubber band them to have tradeoffs (age to reproduce to etc_
        //System.out.println("Not implemented");
    }
    public void interact(){
        //not implemented yet
        //getObjectsInRange(1,null));
        
        
   }
   public void track() {

   }    
}
    

