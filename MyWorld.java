import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    TestOrganism dolphin;
    int foodEaten = 0;
    IntroScreen intro;
    //The following arraylists are made for each type of organism
    static public ArrayList <Algae> plants = new ArrayList<Algae> (100);
    static public ArrayList <PlantEater> herbivores = new ArrayList<PlantEater> (100);
    static public ArrayList <MeatEater> carnivores = new ArrayList<MeatEater> (100);
    static public ArrayList <AnythingEater> omnivores = new ArrayList<AnythingEater> (100);
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        intro = new IntroScreen(this);
        Greenfoot.setWorld(intro);
        //spawn each organism
        for(int i = 0; i < 75; i++){
            //add a new organism to the world for each type of organism
            plants.add(new Algae());
            addObject(new Algae(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
        }
        for (int i = 0; i < 10; i++){
            herbivores.add(new PlantEater());
            addObject(new PlantEater(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
        }
        for (int i = 0; i < 5; i++){
            omnivores.add(new AnythingEater());
            addObject(new AnythingEater(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
        }
        for(int i = 0; i < 3; i++){
            carnivores.add(new MeatEater());
            addObject(new MeatEater(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
        }

    }

    public void act(){

    }
}