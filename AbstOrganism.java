import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class AbstOrgansim here.
 * 
 * @author Rowbottom 
 * @version Sept. 27 2017
 */
public abstract class AbstOrganism extends Actor{

    static public ArrayList <Actor> lifeforms = new ArrayList<Actor>();//list of all the organsims in the game
    public ArrayList <Object> prey;//list of all that the types of organism can feed on
    public ArrayList <Object> predators;//list of all the types of organsims that the organism can be eaten by    
    public int trophicLevel;
    public int age;
    public int lifespan;
    public double repro_energy;
    public int siz;
    static public GreenfootImage [] imgs; //animation capabilities
    public double health;
    public double energy;
    public double speed;
    public int att;
    public int def;
    public double mutation_rate;
    MyWorld world;
       
    public abstract void feed();
    
    public abstract void grow();
    
    public abstract void reproduce();
    
    public abstract void age();
    
    public abstract void die();
    
    public abstract void move();
    
    public abstract void mutate();  
    
    public void say(String phrase){
        System.out.println(phrase);
    }
    
}
