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
public class pStats extends Actor
{
   GreenfootImage temp;
   Color color = new Color (0,255,0); 
    //Enemy owner;
    int maxHealth;
    int size;
    int health;
    MyWorld world;
    public String label = "Organism";
    public ArrayList <AbstOrganism> organismStats = new ArrayList <AbstOrganism> ();
    /*public HealthBar(Enemy o, int maxHeath){
        healthFill = new GreenfootImage(20,5);  
        color = Color(0, 255, 0);
        this.maxHealth = o.health;
        owner = o;
        size = owner.getImage().getHeight()/2;

    }*/
    public pStats(){
        GreenfootImage healthfill = new GreenfootImage(200,5);
        size = 100;
    }
    public pStats(ArrayList<AbstOrganism> numerator){
        organismStats = numerator;
        GreenfootImage healthfill = new GreenfootImage(200,5);
        size = 100;
    }
    public pStats(ArrayList<AbstOrganism> numerator, String organismName){
        label = organismName;
        organismStats = numerator;
        GreenfootImage healthfill = new GreenfootImage(200,5);
        size = 100;
    }
    public void act(){
        if( world == null){
            world = (MyWorld) getWorld();
             world.showText(label , getX(), getY()-50);
            //world.carnivores.add(this);
        } 
               
        update();
    }
    public void update() 
    {
        if (temp == null){
            temp = getImage();
            temp.clear();
        }
        //temp.clear();
        //int health = owner.health;
        //setLocation(owner.x, owner.y-size);
        //update the image
        health = organismStats.size();
        maxHealth = world.plants.size() + world.carnivores.size() + world.herbivores.size() + world.omnivores.size() + world.scavengers.size(); 
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
        //setImage(temp);
        //update the position
        //setLocation(owner.x+temp.getWidth()/2, owner.y-size);

        // Add your action code here.
    }    
}
