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
    static public ArrayList <AbstOrganism> plants = new ArrayList<AbstOrganism> ();
    static public ArrayList <AbstOrganism> herbivores = new ArrayList<AbstOrganism> ();
    static public ArrayList <AbstOrganism> carnivores = new ArrayList<AbstOrganism> ();
    static public ArrayList <AbstOrganism> omnivores = new ArrayList <AbstOrganism> ();
    static public ArrayList <AbstOrganism> carrion = new ArrayList <AbstOrganism> ();
    static public ArrayList <AbstOrganism> scavengers = new ArrayList <AbstOrganism> ();
    int algaeNum = IntroScreen.aSlider.sliderNumber;
    int herbivoresNum = IntroScreen.hSlider.sliderNumber;
    int omnivoresNum = IntroScreen.oSlider.sliderNumber;
    int carnivoresNum = IntroScreen.cSlider.sliderNumber;
    int scavengerNum = IntroScreen.sSlider.sliderNumber;
    int plantValue = 40;//number of plants in total
    int herbiValue = 40;//number of herbivores in total
    int carniValue = 40;//number of carnivores in total
    int omniValue = 40;//number of omnivores in total
    int scavengerValue = 40;//number of scavengers in total
    int plantIncrease = 2;// the incrementation of the algae butts
    int herbiIncrease = 1;// the incrementation of the herbivores butts
    int carniIncrease = 2;// the incrementation of the carnivores butts
    int omniIncrease = 2;// the incrementation of the omnivores butts
    int scavengerIncrease = 1;// the incrementation of the scavengers butts
    int offsetX = 100;
    int offsetY = getHeight()/2;
    String plantName = "Plants";
    String herbivoresName = "Herbivores";
    String omnivoresName = "Omnivores";
    String carnivoresName = "Carnivores";
    String carrionName = "Carrion";
    String scavengersName = "Scavengers";
    pStats pHealthBar = new pStats(plants, plantName);
    pStats hHealthBar = new pStats(herbivores, herbivoresName);
    pStats oHealthBar = new pStats(omnivores, omnivoresName);
    pStats carnivoresHealthBar = new pStats(carnivores, carnivoresName);
    pStats carHealthBar = new pStats(carrion, carrionName);
    pStats sHealthBar = new pStats(scavengers, scavengersName);
    boolean startSpawn = true;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 1000, 1);
        intro = new IntroScreen(this);
        Greenfoot.setWorld(intro);
        addObject(pHealthBar, 50,100);
        addObject(hHealthBar, 200,100);
        addObject(oHealthBar, 350,100);
        addObject(carnivoresHealthBar, 500,100);
        addObject(carHealthBar, 650,100);
        addObject(sHealthBar, 800,100);
        addObject(new ButtPlus(this) , offsetX , offsetY);
        addObject(new ButtMinus(this) , offsetX + 30 , offsetY);
        addObject(new ButtPlus1(this) , offsetX , offsetY - 75);
        addObject(new ButtMinus1(this) , offsetX + 30 , offsetY - 75);
        addObject(new ButtPlus2(this) , offsetX , offsetY - 150);
        addObject(new ButtMinus2(this) , offsetX + 30 , offsetY - 150);
        addObject(new ButtPlus3(this) , offsetX , offsetY - 225);
        addObject(new ButtMinus3(this) , offsetX + 30 , offsetY - 225);
        addObject(new ButtPlus4(this) , offsetX , offsetY - 300);
        addObject(new ButtMinus4(this) , offsetX + 30 , offsetY - 300);
    }

    public void act(){
        setPaintOrder(AbstOrganism.class);
        algaeNum = IntroScreen.aSlider.sliderNumber;
        herbivoresNum = IntroScreen.hSlider.sliderNumber;
        omnivoresNum = IntroScreen.oSlider.sliderNumber;
        carnivoresNum = IntroScreen.cSlider.sliderNumber;
        scavengerNum = IntroScreen.sSlider.sliderNumber;
        pHealthBar.update();
        hHealthBar.update();
        oHealthBar.update();
        carnivoresHealthBar.update();
        carHealthBar.update();
        sHealthBar.update();
        checkPlants();
        checkHerbi();
        checkCarni();
        checkOmni();
        checkScavenger();
        showText("Increase/Decrease by " + plantIncrease , offsetX + 15, offsetY - 20);//shows value, but needs to erase after one print
        showText("Algae" , offsetX + 15, offsetY - 40);
        showText("Increase/Decrease by " + herbiIncrease , offsetX + 15, offsetY - 95);//shows value, but needs to erase after one print
        showText("Herbivores" , offsetX + 15, offsetY - 115);
        showText("Increase/Decrease by " + carniIncrease , offsetX + 15, offsetY - 170);//shows value, but needs to erase after one print
        showText("Carnivores" , offsetX + 15, offsetY - 190);
        showText("Increase/Decrease by " + omniIncrease , offsetX + 15, offsetY - 245);//shows value, but needs to erase after one print
        showText("Omnivores" , offsetX + 15, offsetY - 265);
        showText("Increase/Decrease by " + scavengerIncrease , offsetX + 15, offsetY - 320);//shows value, but needs to erase after one print
        showText("Scavengers" , offsetX + 15, offsetY - 340);
        setPaintOrder(ButtMinus.class, ButtPlus.class, ButtMinus1.class, ButtPlus1.class, ButtMinus2.class, ButtPlus2.class, ButtMinus3.class, ButtPlus3.class, ButtMinus4.class, ButtPlus4.class, Algae.class);
        
        //spawn each organism
        if(startSpawn == true){        
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
            startSpawn = false;
        }

        if(Greenfoot.isKeyDown("q")) {
            startSpawn = true;
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
            Greenfoot.setWorld(intro);
        }
    }
    
    public void checkPlants(){
        if (plantValue <= 0){plantValue = 1;}
        int tempPlantNum = plants.size();
       
        if (tempPlantNum > plantValue){
            for (int i = plantValue; i < tempPlantNum; i+=1){
                removeObject(plants.get(0));
                plants.remove(0);
                System.out.println("REMOVE 1: New Plants size = " + plants.size());
            }
        }
        else if (tempPlantNum < plantValue){
            for (int i = tempPlantNum; i < plantValue; i+=1){
                Algae temp = new Algae(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                plants.add(temp);
                System.out.println("ADD 1:New Plants size = " + plants.size());
            }
        }        
    
    }

    public void checkHerbi(){
        if (herbiValue <= 0){herbiValue = 1;}
        int tempHerbiNum = herbivores.size();

        if (tempHerbiNum > herbiValue){
            for (int i = herbiValue; i < tempHerbiNum; i+=1){
                removeObject(herbivores.get(0));
                herbivores.remove(0);
                System.out.println("REMOVE 1: New Herbivores size = " + herbivores.size());
            }
        }
        else if (tempHerbiNum < herbiValue){
            for (int i = tempHerbiNum; i < herbiValue; i+=1){
                PlantEater temp = new PlantEater(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                herbivores.add(temp);
                System.out.println("ADD 1:New Herbivoress size = " + herbivores.size());
            }
        } 
   }

    public void checkCarni(){
        if (carniValue <= 0){carniValue = 1;}
        int tempCarniNum = carnivores.size();

        if (tempCarniNum > carniValue){
            for (int i = carniValue; i < tempCarniNum; i+=1){
                removeObject(carnivores.get(0));
                carnivores.remove(0);
                System.out.println("REMOVE 1: New Carnivores size = " + carnivores.size());
            }
        }
        else if (tempCarniNum < carniValue){
            for (int i = tempCarniNum; i < carniValue; i+=1){
                MeatEater temp = new MeatEater(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                carnivores.add(temp);
                System.out.println("ADD 1:New Carnivores size = " + carnivores.size());
            }
        } 
   }

    public void checkOmni(){
        if (omniValue <= 0){omniValue = 1;}
        int tempOmniNum = omnivores.size();

        if (tempOmniNum > omniValue){
            for (int i = omniValue; i < tempOmniNum; i+=1){
                removeObject(omnivores.get(0));
                omnivores.remove(0);
                System.out.println("REMOVE 1: New Omnivores size = " + omnivores.size());
            }
        }
        else if (tempOmniNum < omniValue){
            for (int i = tempOmniNum; i < omniValue; i+=1){
                AnythingEater temp = new AnythingEater(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                omnivores.add(temp);
                System.out.println("ADD 1:New Omnivores size = " + omnivores.size());
            }
        } 
   }

    public void checkScavenger(){
        if (scavengerValue <= 0){scavengerValue = 1;}
        int tempScavengerNum = scavengers.size();

        if (tempScavengerNum > scavengerValue){
            for (int i = scavengerValue; i < tempScavengerNum; i+=1){
                removeObject(scavengers.get(0));
                scavengers.remove(0);
                System.out.println("REMOVE 1: New Scavengers size = " + scavengers.size());
            }
        }
        else if (tempScavengerNum < scavengerValue){
            for (int i = tempScavengerNum; i < scavengerValue; i+=1){
                Scavenger temp = new Scavenger(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                scavengers.add(temp);
                System.out.println("ADD 1:New Scavengers size = " + scavengers.size());
            }
        } }
   }
