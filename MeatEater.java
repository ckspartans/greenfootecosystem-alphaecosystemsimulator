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
        
        //System.out.println("C : " + energy);
        reproduce();
        //die();
       // System.out.println("Size: " + siz);
       // System.out.println("Energy: " + energy);
    }
    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
        //increase the energy amount
        //later I will probably take into acount the amount of overlap with other ogranisms
        //System.out.println("Feed Not implemented");
       if(this.isTouching(AbstOrganism.class) && !this.isTouching(Algae.class)){
           //System.out.println("heloolloolol" + energyFactor);
                //remove them from all lists
                //System.out.println("helooadnkadladjkll");
                //world.plants.remove(getIntersectingObjects(PlantEater.class));
                
                energy += energyFactor * 0.1;
                
                
                
              
                //remove them from the world
               removeTouching(null);
        }
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        //increase the size of the s image bases on the current energy
        //System.out.println("Grow Not implemented");
        siz = (int)energy;// (int) (0.2*energy+5);
        getImage().scale(siz,siz);
    }
    //this program checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one
    public void reproduce(){
        //check tp see of their is enough energy (size??) to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            //if yes than 
                //call the constructor for two new algaes and kill the original
                    //call die for the parent after calling the constructor
                    
                    //first I need to add an algae to the abstorganism arraylist (so that the new algae actually exists)
                    world.carnivores.add(new MeatEater());
                    //now I add the algae to the world, and let him spawn 20 pixels around the old dead algae
                    world.addObject(world.carnivores.get(world.carnivores.size()-1), getX() + Greenfoot.getRandomNumber(20), getY() + Greenfoot.getRandomNumber(20));
                 
                     
                    //first I need to add an algae to the abstorganism arraylist (so that the new algae actually exists)
                  //  lifeForms.add(new MeatEater());
                    //now I add the algae to the world, and let him spawn 20 pixels around the old dead algae
                  //  world.addObject(lifeForms.get(lifeForms.size()-1), getX() - Greenfoot.getRandomNumber(20), getY() - Greenfoot.getRandomNumber(-20));
                    
                    //kill the old algae now
                    //die();
                    energy = 50;
        }
        //System.out.println("Reproduce Not implemented");
    }
    //this function adds age to the algae ( I dont really use it right now, but feel free to use it)
    public void age(){
        //increase age
        //and check to see if past lifespan
        //if then kill them
        energy -= 0.01;
       // System.out.println("Not implemented");
    }
    //this function is where the algae dies 
    public void die(){
        energy = energy - (0.01 * siz);
        if (energy < 1){
                //???Add a corspe object???
                
                //remove them from all lists
                world.carnivores.remove(this);
                
                //remove them from the world
                world.removeObject(this);
                
                //System.out.println("Not implemented");
        }
    }
    //you dont really need this, but if you come up with a cool idea please let me know first
    public void move(){
        //not needed
        //System.out.println("Not implemented");
        //setLocation(getX() + Greenfoot.getRandomNumber(5), getY() + Greenfoot.getRandomNumber(5));
             
        move((int)speed);
        if(targets != null && !targets.isEmpty() && !targets.contains(Algae.class)){
           // System.out.println("YEA");
           thinker.track();
        }
        
           // System.out.println("NO");
            //setLocation((int)(getX() + Math.cos(angle) * speed), (int)(getY() + Math.sin(angle) * speed));
        else if (targets == null || targets.isEmpty()) {
           // System.out.println("NO");
            //setLocation((int)(getX() + Math.cos(angle) * speed), (int)(getY() + Math.sin(angle) * speed));
          if(Greenfoot.getRandomNumber(100) < 20){
                turn(Greenfoot.getRandomNumber(180));
         }
        }
        if(isAtEdge()){
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
    

