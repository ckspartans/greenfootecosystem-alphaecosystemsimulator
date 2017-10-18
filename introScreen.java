import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class introScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class introScreen extends World
{

    MyWorld world;
    
    /**
     * Constructor for objects of class introScreen.
     * 
     */
    public introScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    
    public introScreen(MyWorld w)
    {    
        super(600, 400, 1); 
        world = w;
    }
    
    public void act ()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(world);
        }
    }
}