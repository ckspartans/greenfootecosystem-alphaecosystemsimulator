import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class AbstOrgansim here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//This class is the AbstOrganism class with the abstract properties updated
public abstract class AbstOrganism extends Actor
{
    //I use trophic levels to determine which organisms can and cannot eat
    public int trophicLevel;
    //Age is used to determine how long an organism has been in the world for
    public int age;
    //Repro_energy is used to know how much energy an organism must need before being able to reproduce
    public int repro_energy;
    //Siz is used to manage the size of the organism
    public int siz;
    //Health will be used in the future iterations when I implement fighting and such, currently organisms just die when they touch each other 
    public double health;
    //Energy is used to keep track of the organisms energy
    public double energy;
    //Speed is used to determine how fast the organisms may move
    public int speed;
    //Attack and defence will be used in the next iteration when I implement fighting and such
    public int att;
    public int def;
    //I do not use mutation, but I believe it is the mutations guy responsibility
    public double mutation_rate;
    MyWorld world;
    //This list keeps track of the organisms (targets) the 'owner' organism can find
    public List <AbstOrganism> targets;
    
    //This is an object of the AI class, which is neccessary so that the organims have 
    AI thinker;
     //This variable is used to determine how far each organism can see
     int sight;
     
     double energyFactor;
     
    Reproduce reproducer;
     
    double energyToReproduce;
    double mutationFactor;
    int reproduceFactor;
    
    
    public void act() 
    {
        // Add your action code here.
        if(world == null){
            world = (MyWorld) getWorld();
        } 
    }
          
    public abstract void feed();

    public abstract void grow();
        
    public abstract void interact();
    
    public abstract void reproduce();

    public abstract void age();

    public abstract void die();

    public abstract void move();

    public abstract int mutate(int trait); 

    public void say(String s){
        System.out.println(s);
    }

}
