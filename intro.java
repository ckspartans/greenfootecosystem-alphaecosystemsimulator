import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class intro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class intro extends World
{
    
    
    public intro()  {    
       super(600, 400, 1); 
       int state = 0;
       
       while(state == 0) {
           showText("ecosystem game" , 180 , 180);
           showText("Start" , 180 , 180);
           if(Greenfoot.mousePressed(this)){
           
           }
           showText("Settings" , 180 , 200);
           // if settings clicked state == 1;
       }
       
       while(state == 1) {
           showText("gameplay",180 ,180);
           showText("graphics" , 180 , 200);
           showText("organisms" , 180 , 220);
       }
       
       while(state == 2) {
           //game running state
           //if game is paused state == 3;
       }
       
       while(state == 3) {
           //game paused menu 
           //if end button is pressed state == 4;
       }
       
       while(state == 4) {
           showText("Play again", 180, 180);
           showText("quite", 180 , 180);
           //if play again is pressed State == 0;
           //if quite is pressed end program
       }
    }
}   