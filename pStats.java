import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.Color.*;
import java.io.*;
import java.util.*;
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//this class handles the stats of each species
public class pStats extends Actor
{
   GreenfootImage temp;
   //this color object will be used to visually represent the health of the species as a whole.
   Color color = new Color (0,255,0); 
    //the max health would be the amount of organisms in the world as a whole
    int maxHealth;
    int size;
    //the health would be the size of the organism array I send in
    int health;
    MyWorld world;
    //This label will be used for when I use show text so that I know which bar represents which species
    public String label = "Organism";
    //This arrayList will be used store the certain species we send into this class to check its health
    public ArrayList <AbstOrganism> organismStats = new ArrayList <AbstOrganism> ();
   //this constructor needs the type of species we send in (so the arrayList from the MyWorld class) & the name of the organism
    public pStats(ArrayList<AbstOrganism> numerator, String organismName){
        //the label will be what i pass in (which would be the name of the species)
        label = organismName;
        //the organismStats will be the arrayList I send in from the MyWorld class 
        organismStats = numerator;
        GreenfootImage healthfill = new GreenfootImage(200,5);
        size = 100;
    }
    public void act(){
    //Get MyWorld
        if( world == null){
            world = (MyWorld) getWorld();
            //show the text of which species' health bar we are showing
             world.showText(label , getX(), getY()-50);
            
        } 
            //update the health bar   
        update();
    }
    public void update() 
    {
        if (temp == null){
            temp = getImage();
            temp.clear();
        }
        //health will be the size of the organisms size
        health = organismStats.size();
        //maxHealth is all the organisms in the world
        maxHealth = world.plants.size() + world.carnivores.size() + world.herbivores.size() + world.omnivores.size() + world.scavengers.size(); 
        //calculate the health and change the health bar accordingly to its health
        if (health == maxHealth|| health <= 0){return;}
        int red = 0, green = 0;
        try{
            red = (int)(120+134*(1. - ((double)health/(double)maxHealth)));
            green = (int)( 254 *((double)health/(double)maxHealth));
            color = new Color(red,green, 0);
        }
        catch(Exception e){
            System.out.println(""+red+" , "+green);
        }
        temp.setColor(color); 
        temp.fill();
        temp.scale(1+100*health/maxHealth, 8);
    }    
}
