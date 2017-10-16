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
    static public ArrayList <Algae> plants = new ArrayList<Algae> (5);
    static public ArrayList <PlantEater> herbivores = new ArrayList<PlantEater> (5);
    static public ArrayList <MeatEater> carnivores = new ArrayList<MeatEater> (5);
    static public ArrayList <AnythingEater> omnivores = new ArrayList<AnythingEater> (5);
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
        //do this 5 times
        for(int i = 0; i < 5; i++){
            //add a new organism to the world for each type of organism
            plants.add(new Algae());
            addObject(new Algae(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
            herbivores.add(new PlantEater());
            addObject(new PlantEater(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
            omnivores.add(new AnythingEater());
            addObject(new AnythingEater(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
            carnivores.add(new MeatEater());
            addObject(new MeatEater(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
        }

       // setPaintOrder(Algae.class);
        //dolphin = new TestOrganism();
        //addObject(dolphin, 300,200);
        //plants.add(new Algae());
       // herbivores.add(new PlantEater());
        //carnivores.add(new MeatEater());
        //addObject(plants.get(plants.size()-1), Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400));
       /* for(int i = 0; i < 10; i++){
           addObject(new Pumpkin(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        } */
    }

    public void act(){
        if(Greenfoot.isKeyDown("space")){
            removeObject(dolphin);
        }
    }
}
