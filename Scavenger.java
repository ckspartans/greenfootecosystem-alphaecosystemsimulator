import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scavenger extends AbstOrganism 
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //This object target is used to get information about the target organism
    public Scavenger(){
        trophicLevel = 2;
        age = 0;
        // world.herbivores.add(this);
        repro_energy = 75;
        siz = 30;//(int) (0.2*energy) + 50;
        health = 100;
        energy = 50;
        speed = 5;
        att = 50;
        def = 50;
        mutation_rate = 25;
        MyWorld world;
        targets = null;
        thinker = new AI(this);
        sight = 300;
        energyFactor = 0;
        energyIndicator  = new EnergyBar(this,(int)energy); 
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
        //age();
        //interact();
        reproduce();
        //System.out.println("H : " + energy);

        // System.out.println("" + energyFactor);
        //die();
        if(energy <= 1){
            die();
        }
    }
    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
        //increase the energy amount
        //later I will probably take into acount the amount of overlap with other ogranisms
        //System.out.println("Feed Not implemented");
        if(this.isTouching(DeadOrganism.class)){
            //System.out.println("heloolloolol");
            //remove them from all lists
            //world.plants.remove(getIntersectingObjects(Algae.class));

            energy += energyFactor * 0.1;
            // System.out.println("I made it");
            thinker.kill();
            removeTouching(AbstOrganism.class);

            //remove them from the world
        }
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        //System.out.println("Grow Not implemented");
        energy -= (siz *0.0001);
        siz = (int)energy + 1; //(int) (0.2*energy+10);
        getImage().scale(siz,siz);
    }
    //this program checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one
    public void reproduce(){
        //check tp see of their is enough energy (size??) to split
        //if yes then call the constructor for new ones and kill the last one
        if (energy >= repro_energy){
            world.scavengers.add(new Scavenger());
            world.addObject(world.scavengers.get(world.scavengers.size()-1), getX() + Greenfoot.getRandomNumber(200), getY() + Greenfoot.getRandomNumber(200));

            world.omnivores.get(world.omnivores.size()-1).repro_energy = mutate(repro_energy);
            world.omnivores.get(world.omnivores.size()-1).siz = mutate(siz);
            world.omnivores.get(world.omnivores.size()-1).speed = mutate(speed);
            world.omnivores.get(world.omnivores.size()-1).mutation_rate = mutate(mutation_rate);
            world.omnivores.get(world.omnivores.size()-1).sight = mutate(sight);
            
            world.scavengers.add(new Scavenger());
            world.addObject(world.scavengers.get(world.scavengers.size()-1), getX() - Greenfoot.getRandomNumber(200), getY() - Greenfoot.getRandomNumber(200));
            
            
            die();
        }
        //System.out.println("Reproduce Not implemented");
    }
    //this function adds age to the algae ( I dont really use it right now, but feel free to use it)
    public void age(){
        //increase age
        //and check to see if past lifespan
        //if then kill them
        energy -= 0.25;
        // System.out.println("Not implemented");
    }
    //this function is where the algae dies 
    public void die(){
        //I will make this based off of size soon
        //energy = (energy) - (0.02 * siz);

        //???Add a corspe object???

        //remove them from all lists
        world.scavengers.remove(this);
        world.removeObject(energyIndicator);
        //remove them from the world
        world.removeObject(this);

        //System.out.println("Not implemented");

    }
    //you dont really need this, but if you come up with a cool idea please let me know first
    public void move(){
        //not needed
        //System.out.println("Not implemented");
        //setLocation(getX() + Greenfoot.getRandomNumber(5), getY() + Greenfoot.getRandomNumber(5));
        move((int)speed);
        if(targets != null && !targets.isEmpty()){
            // System.out.println("YEA");
            thinker.track();
        }

        // System.out.println("NO");
        //setLocation((int)(getX() + Math.cos(angle) * speed), (int)(getY() + Math.sin(angle) * speed));

        else if (targets == null || targets.isEmpty()) {
            // System.out.println("NO");
            //setLocation((int)(getX() + Math.cos(angle) * speed), (int)(getY() + Math.sin(angle) * speed));
            if(Greenfoot.getRandomNumber(100) < 10){
                turn(Greenfoot.getRandomNumber(180));
            }
        }

        if(isAtEdge()){
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

