import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyBar extends Actor
{
    GreenfootImage temp;
    //This color object shows how much energy the organism has
    Color color; 
    //We pass in an owner to figure out its energy and display it
    AbstOrganism owner;
    //max health will be the amount the organism has when it spawns into the world
    int maxHealth;
    int size;
    //the health will be how much energy it has right
    int health;
    MyWorld world;
    //the constructor needs the owner organism, and the health before they start losing or gaining any
    public EnergyBar(AbstOrganism o, int maxHeath){
        this.maxHealth = (int)o.health;
        owner = o;
        //size of the bar will be half of the size owners size
        size = owner.getImage().getHeight()/2;

    }

    public void update() 
    {
        //if the mouse is on top of the owner
        if (Greenfoot.mouseMoved(owner)){
        //then update the health bar and show it to the user
            temp = getImage();

            temp.clear();


            size = owner.getImage().getHeight()/2;
            health = (int)owner.energy;
            if(owner != null){
                setLocation(owner.getX(), owner.getY()-size);
            }
            else{
                world.removeObject(this);
            }
          
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
            //sometimes color could be null, to Avoid a null pointer exception i make sure its not null
            if(color != null){
                temp.setColor(color); 
                temp.fill();
                temp.scale(1+50*health/maxHealth, 8);
            }
            else{
                temp.setTransparency(0);
                temp.clear();
            }
            //setImage(temp);
            //update the position
            //setLocation(owner.x+temp.getWidth()/2, owner.y-size);

            // Add your action code here.
        }
        else{
        //if the mouse is not on top of the organism don't show the health bar
            if(temp != null){
                temp.clear();
            }
            if(owner.energy <= 0 || owner == null){
                temp.clear();
                world.removeObject(this);
            }
        }
    }
}

