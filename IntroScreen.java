import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScreen extends World
{
    MyWorld world;
    /**
     * Constructor for objects of class IntroScreen.
     * 
     */
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 700, 1); 
    }

    public IntroScreen(MyWorld w)
    {    
        super(800, 700, 1); 
        world = w;
    }

    public void act(){
        if (Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(world);
        }

    }
}   
