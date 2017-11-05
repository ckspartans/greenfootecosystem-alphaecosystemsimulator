import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Plants here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeadOrganism extends AbstOrganism
{
    /**
     * Act - do whatever the Plants wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    //basic default constructor, this is where I initialize all the variables that must be in this class (because it inherits from the abstract class)

    public DeadOrganism(){
        //since this dead, I give it a trophicLevel of value -1
        trophicLevel = -1;
        age = 0;
        //add the carrion to the world when created
        world.carrion.add(this);
        repro_energy = 100;
        siz = 50;//(int) (0.2*energy+5);
        health = 100;
        energy = 70;
        speed = 20;
        att = 50;
        def = 50;
        mutation_rate = 0;
        MyWorld world;
        targets = null;
        sight = 0;
        energyFactor = 0;

        energyIndicator  = new EnergyBar(this,(int)energy);
    }

    public void act() 
    {
        // Add your action code here.
        //This checks if the world is being needed to be called or not
        if(world == null){
            world = (MyWorld) getWorld();
            world.addObject(energyIndicator,-1000,0);
        } 
        //this is where I call all the functions 
        if(energyIndicator != null){
            energyIndicator.update();
        }
        grow();
        feed();
        die();
    }
    public void feed(){
    }
    //Here I use the energy that the carrion has lost to reduce the carrion to a certain size
    public void grow(){
        //decrease the size of the image bases on the current energy
        //System.out.println("Grow Not implemented");
        energy -= (siz *0.0001);
        siz = (int) energy + 1;
        getImage().scale(siz,siz);
    }
    
    public void reproduce(){

    }
    
    public void age(){

    }
    // when the carrion completely decomposes
    public void die(){
        if (energy <= 1){
            //remove them from all lists

            world.carrion.remove(this);
            world.removeObject(energyIndicator);
            //remove them from the world
            world.removeObject(this);

            //System.out.println("Not implemented");
        }
    }
    //you dont really need this, but if you come up with a cool idea please let me know first
    public void move(){
        //not needed
        //System.out.println("Not implemented");
    }
   

    public void interact(){
        //not implemented yet
    }
}
