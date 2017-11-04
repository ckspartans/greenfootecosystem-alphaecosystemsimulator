import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class slider here.
 * 
 * @author (your name) 
 * @version (a version sliderNumber or a date)
 */
public class OmnivoreSlider extends Actor    
{
    /**
     * Act - do whatever the slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int sliderNumber;
    public int text(int sliderNumber, int min, int max){
        getWorld().showText("" + sliderNumber, min+((max-min)/2), getY() - 20);//shows value, but needs to erase after one print
        getWorld().showText("Omnivore" , min+((max-min)/2) , getY() - 40);
        return sliderNumber;
    }

    public void act() 
    {
        // Add your action code here.

        //slider min and max are based off the centre of the shape
        int sliderXmin = 475;
        int sliderXmax = 575;
        int x = getX();
        int y = getY();
        sliderNumber = getX() - sliderXmin;

        text(sliderNumber, sliderXmin, sliderXmax);
        //getWorld().showText("" + sliderNumber, getX(), getY());//shows value, but needs to erase after one print

        //getWorld().getBackground().drawLine(sliderXmin, y, sliderXmax, y);//NEEDS TO BE WORKED OOOOOOOOOOOOOONNNNN!!!!!
       
        MouseInfo mouse = Greenfoot.getMouseInfo();// makes getting info about the mouse (x and y values, button pressed, and if null or not) faster without writing longer code
        boolean mouseHeld = false;//boolean to check if mouse is held
        if (mouse != null){
            if (Greenfoot.mouseDragged(this)){// checks to see if mouse is held down on this object 
                mouseHeld = true;
            } else if (Greenfoot.mousePressed(this)) {
                mouseHeld = false;
            }
            if(mouseHeld == true && mouse.getButton() == 1){// if mouse right button held dowm 
                if(sliderXmin < mouse.getX() && sliderXmax > mouse.getX()){setLocation(mouse.getX(), y);}//sets slider location to mouse X if within paramiters       
                else if(mouse.getX() > sliderXmax){setLocation(sliderXmax, y);}//this and next line sets the slider X value to the max/min X value for slider if mouse outside of paramiters   
                else if(mouse.getX() > sliderXmin){setLocation(sliderXmin, y);}

            }
            else{

                /*
                for(int i = 0; i < sliderNumber; i++){
                //add a new organism to the world for each type of organism
                plants.add(new Algae());
                addObject(new Algae(), Greenfoot.getRandomsliderNumber(world.getWidth()), Greenfoot.getRandomsliderNumber(world.getHeight()));
                }*/

            }
        } 

    }    
}
