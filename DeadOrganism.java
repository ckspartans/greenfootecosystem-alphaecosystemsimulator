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
        //since this is the lowest on the food chain, I give it a trophicLevel of value 0
        trophicLevel = -1;
        //always set the age to 0 when an algae is created
        age = 0;
        //add the algae to the world when created
        world.carrion.add(this);
        //This means that the algae needs 25 energy to reproduce
        repro_energy = 100;
        //This means that the algae will be 100 by 100 in size
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
    //this function basically adds energy to the plant, I add 0.1 energy per frame
    public void feed(){
    }
    //Here I use the energy that the algae has to grow the algae to a certain size
    public void grow(){
        //increase the size of the image bases on the current energy
        //System.out.println("Grow Not implemented");
        energy -= (siz *0.0001);
        siz = (int) energy + 1;
        getImage().scale(siz,siz);
    }
    //this program checks if the algae has enough energy to reproduce, and then creates two new algaes and kills off the old one
    public void reproduce(){

    }
    //this function adds age to the algae ( I dont really use it right now, but feel free to use it)
    public void age(){

    }
    //this function is where the algae dies 
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
    //try to complete this
    public void mutate(){
        //need a way to change the specific object
        //rubber band them to have tradeoffs (age to reproduce to etc_
        //System.out.println("Not implemented");
    }

    public void interact(){
        //not implemented yet
    }
}
