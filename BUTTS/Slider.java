import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slider extends Actor    
{
    /**
     * Act - do whatever the slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public int text(int number, int min, int max){
    getWorld().showText("" + number, min+((max-min)/2), getY() - 20);//shows value, but needs to erase after one print
    getWorld().showText("Omnivore" , min+((max-min)/2) , getY() - 40);
    return number;
    }
	
    
    public void act() 
    {
        // Add your action code here.

        //slider min and max are based off the centre of the shape
        int sliderXmin = 200;
        int sliderXmax = 400;
        int x = getX();
        int y = getY();
        int number = getX() - sliderXmin;

        text(number, sliderXmin, sliderXmax);
        //getWorld().showText("" + number, getX(), getY());//shows value, but needs to erase after one print
        

        //getWorld().getBackground().drawLine(sliderXmin, y, sliderXmax, y);//NEEDS TO BE WORKED OOOOOOOOOOOOOONNNNN!!!!!

        //System.out.println(x);
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
                for(int i = 0; i < number; i++){
                //add a new organism to the world for each type of organism
                plants.add(new Algae());
                addObject(new Algae(), Greenfoot.getRandomNumber(world.getWidth()), Greenfoot.getRandomNumber(world.getHeight()));
                }*/
                //System.out.println(number);

            }
        } 

    }    
}