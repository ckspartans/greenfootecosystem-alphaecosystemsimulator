import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtPlus4 extends Actor    
{
    /**
     * Act - do whatever the slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    MyWorld world; 

    public ButtPlus4(MyWorld w){
        world = w;
    }



    public void act() 
    {
        // Add your action code here.

        //slider min and max are based off the centre of the shape

        //ys = getY();
        //x1 = getX();
        if (Greenfoot.mouseClicked(this)){
            world.scavengerValue += world.scavengerIncrease;
        }
        //getWorld().showText("" + number, getX(), getY());//shows value, but needs to erase after one print

        //getWorld().getBackground().drawLine(sliderXmin, y, sliderXmax, y);//NEEDS TO BE WORKED OOOOOOOOOOOOOONNNNN!!!!!
        //System.out.println(x);
        /*    MouseInfo mouse = Greenfoot.getMouseInfo();// makes getting info about the mouse (x and y values, button pressed, and if null or not) faster without writing longer code
        boolean mouseHeld = false;//boolean to check if mouse is held
        if (mouse != null){
        if (Greenfoot.mouseDragged(this)){// checks to see if mouse is held down on this object 
        mouseHeld = false;
        } else if (Greenfoot.mousePressed(this)) {// checks to see if mouse is pressed on this object 
        mouseHeld = true;
        }

        if (mouseHeld == true){
        for(int i = 0; i < 5; i++){
        plants.add(new Algae());
        getWorld().addObject(plants.get(plants.size()-1), 5 + Greenfoot.getRandomNumber(getWorld().getWidth()-10), 5 + Greenfoot.getRandomNumber(getWorld().getHeight()-10));//creates new object
        }
        }
        /*
        failed code for increasing and decreasing with a slider

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

        //pSize = getX() - sliderXmin;
        //}
        // } 
        //System.out.println(pSize + "Current 'Algae'");
        //HERE IS THE PROBLEM!!! THIS NEEDS TO BE FIXED!!!
        /*
        int tempCount = plants.size();
        if(tempCount != pSize){
        //*
        //Greenfoot.delay(3);
        //for(int i = pSize; i < plants.size(); i+=0){//runs as many times as # of plants that are needed to be deleted
        for(int i = pSize; i < tempCount; i+=1){//runs as many times as # of plants that are needed to be deleted
        //if(tempCount > pSize){
        //remove them from all lists
        //plants.remove(plants.size()-1);//take the first one in the array and kill it
        getWorld().removeObject(plants.get(0));
        plants.remove(0);
        System.out.println(plants.size() + "    ts working yo");

        }
        System.out.println(plants.size() + "    This is the plants array size");
        //*i/
        //*
        for(int i = pSize; i > tempCount; i+=1){//runs as many times as # of plants that are needed to be deleted
        //if(tempCount < pSize){
        //add some new algae
        plants.add(new Algae());
        getWorld().addObject(plants.get(plants.size()-1), 5 + Greenfoot.getRandomNumber(getWorld().getWidth()-10), 5 + Greenfoot.getRandomNumber(getWorld().getHeight()-10));//creates new object

        }
        //*i/
        }
        //*/
        //THE PROBLEM ENDS HERE
        //System.out.println(plants.size() + "    THIS IS THE plants.size() THINGY");
    }    
}
