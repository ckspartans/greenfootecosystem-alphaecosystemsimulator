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
    Color color; 
    AbstOrganism owner;
    int maxHealth;
    int size;
    int health;
    MyWorld world;
    public EnergyBar(AbstOrganism o, int maxHeath){
        // color = Color(0, 255, 0);
        this.maxHealth = (int)o.health;
        owner = o;
        size = owner.getImage().getHeight()/2;

    }

    public void update() 
    {
        if (Greenfoot.mouseMoved(owner)){
            temp = getImage();

            temp.clear();

            //temp.clear();
            size = owner.getImage().getHeight()/2;
            health = (int)owner.energy;
            if(owner != null){
                setLocation(owner.getX(), owner.getY()-size);
            }
            else{
                world.removeObject(this);
            }
            //update the image
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

