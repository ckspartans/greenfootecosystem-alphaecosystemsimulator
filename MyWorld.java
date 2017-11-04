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
    int foodEaten = 0;
    IntroScreen intro;
    //The following arraylists are made for each type of organism
    static public ArrayList <AbstOrganism> plants = new ArrayList<AbstOrganism> ();
    static public ArrayList <AbstOrganism> herbivores = new ArrayList<AbstOrganism> ();
    static public ArrayList <AbstOrganism> carnivores = new ArrayList<AbstOrganism> ();
    static public ArrayList <AbstOrganism> omnivores = new ArrayList <AbstOrganism> ();
    static public ArrayList <AbstOrganism> carrion = new ArrayList <AbstOrganism> ();
    static public ArrayList <AbstOrganism> scavengers = new ArrayList <AbstOrganism> ();
    //The following values get the number of each species I have to spawn in based off of the sliders from the intro screen
    int algaeNum = IntroScreen.aSlider.sliderNumber;
    int herbivoresNum = IntroScreen.hSlider.sliderNumber;
    int omnivoresNum = IntroScreen.oSlider.sliderNumber;
    int carnivoresNum = IntroScreen.cSlider.sliderNumber;
    int scavengerNum = IntroScreen.sSlider.sliderNumber;
    //The following strings Are used for the label of each species health bar (so we know which health bar is for each type of species)
    String plantName = "Plants";
    String herbivoresName = "Herbivores";
    String omnivoresName = "Omnivores";
    String carnivoresName = "Carnivores";
    String carrionName = "Carrion";
    String scavengersName = "Scavengers";
    //The following objects are the healthBars for each species
    pStats pHealthBar = new pStats(plants, plantName);
    pStats hHealthBar = new pStats(herbivores, herbivoresName);
    pStats oHealthBar = new pStats(omnivores, omnivoresName);
    pStats carnivoresHealthBar = new pStats(carnivores, carnivoresName);
    pStats carHealthBar = new pStats(carrion, carrionName);
    pStats sHealthBar = new pStats(scavengers, scavengersName);
    //This boolean is used so that I know when the initial spawn in of each species should stop
    boolean startSpawn = true;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 1000x1000 cells with a cell size of 1x1 pixels.
        super(1000, 1000, 1);
        intro = new IntroScreen(this);
        Greenfoot.setWorld(intro);
        //add each healthBar to world
        addObject(pHealthBar, 50,100);
        addObject(hHealthBar, 200,100);
        addObject(oHealthBar, 350,100);
        addObject(carnivoresHealthBar, 500,100);
        addObject(carHealthBar, 650,100);
        addObject(sHealthBar, 800,100);
    }

    public void act(){
        //Show text to let the user know they can click q to quit the simulation
        showText("Press Q to restart Simulation" , 500, 20);
        //Set the paint order so that health bars come first and then the organisms go next
        setPaintOrder(pStats.class, AbstOrganism.class);
        //The following values get the number of each species I have to spawn in based off of the sliders from the intro screen
        algaeNum = IntroScreen.aSlider.sliderNumber;
        herbivoresNum = IntroScreen.hSlider.sliderNumber;
        omnivoresNum = IntroScreen.oSlider.sliderNumber;
        carnivoresNum = IntroScreen.cSlider.sliderNumber;
        scavengerNum = IntroScreen.sSlider.sliderNumber;
        //update each healthbar
        pHealthBar.update();
        hHealthBar.update();
        oHealthBar.update();
        carnivoresHealthBar.update();
        carHealthBar.update();
        sHealthBar.update();
        //spawn each organism if the simulation just started
        if(startSpawn == true){
            //for each species, spawn in the number of organisms which the user set the sliderNumber too
            for(int i = 0; i < algaeNum; i++){
                //add a new organism to the world for each type of organism
                plants.add(new Algae());
                addObject(plants.get(plants.size()-1), Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(800));
            }
            for (int i = 0; i < herbivoresNum; i++){
                herbivores.add(new PlantEater());
                addObject(herbivores.get(herbivores.size()-1), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(1000));
            }
            for (int i = 0; i < omnivoresNum; i++){
                omnivores.add(new AnythingEater());
                addObject(omnivores.get(omnivores.size()-1), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(1000));
            }
            for (int i = 0; i < omnivoresNum; i++){
                carnivores.add(new MeatEater());
                addObject(carnivores.get(carnivores.size()-1), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(1000));
            }
            for (int i = 0; i < scavengerNum; i++){
                scavengers.add(new Scavenger());
                addObject(scavengers.get(scavengers.size()-1), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(1000));
            }
            //set the initial spawn to false so that we don't spawn in anymore organisms
            startSpawn = false;
        }
        //if the player clicks q to leave the simulation
        if(Greenfoot.isKeyDown("q")) {
            //set startSpawn to true for the next simulation
            startSpawn = true;
            //delete every organism on screen
            for(int i = 0; i < plants.size()-1; i++){
                //add a new organism to the world for each type of organism
                removeObject(plants.get(i));
            }
            for(int i = 0; i < herbivores.size()-1; i++){
                //add a new organism to the world for each type of organism
                removeObject(herbivores.get(i));
            }
            for(int i = 0; i < omnivores.size()-1; i++){
                //add a new organism to the world for each type of organism
                removeObject(omnivores.get(i));
            }
            for(int i = 0; i < carnivores.size()-1; i++){
                //add a new organism to the world for each type of organism
                removeObject(carnivores.get(i));
            }
            for(int i = 0; i < scavengers.size()-1; i++){
                //add a new organism to the world for each type of organism
                removeObject(scavengers.get(i));
            }
            for(int i = 0; i < carrion.size()-1; i++){
                //add a new organism to the world for each type of organism
                removeObject(carrion.get(i));
            }
            //set the world back to intro
            Greenfoot.setWorld(intro);
        }
    }
}
