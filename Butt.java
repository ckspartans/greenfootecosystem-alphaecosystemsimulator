import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Butt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Butt extends Actor
{
    /**
     * Act - do whatever the Butt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        MouseInfo mouse = Greenfoot.getMouseInfo();// makes getting info about the mouse (x and y values, button pressed, and if null or not) faster without writing longer code
        getImage().scale(100,200);
        setImage(getImage());
        if(Greenfoot.mousePressed(this)){
        
        }
    }    
}
