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
    //TestOrganism dolphin;
    //int foodEaten = 0;
    boolean doOnce = false;
    int plantValue = 40;//number of plants in total
    int herbiValue = 40;//number of herbivores in total
    int carniValue = 40;//number of carnivores in total
    int omniValue = 40;//number of omnivores in total
    int scavengerValue = 40;//number of scavengers in total
    int plantIncrease = 5;// the incrementation of the algae butts
    int herbiIncrease = 5;// the incrementation of the herbivores butts
    int carniIncrease = 5;// the incrementation of the carnivores butts
    int omniIncrease = 5;// the incrementation of the omnivores butts
    int scavengerIncrease = 5;// the incrementation of the scavengers butts
    //IntroScreen intro;
    //The following arraylists are made for each type of organism
    public ArrayList <Algae> plants;
    //static public ArrayList <PlantEater> herbivores = new ArrayList<PlantEater> (100);
    //static public ArrayList <MeatEater> carnivores = new ArrayList<MeatEater> (100);
    //static public ArrayList <AnythingEater> omnivores = new ArrayList<AnythingEater> (100);
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public MyWorld()
    {    
        // Create a new world with 800x700 cells with a cell size of 1x1 pixels.
        super(800, 700, 1);
        //intro = new IntroScreen(this);
        //Greenfoot.setWorld(intro);
        //spawn each organism
        //if(plants.size() == 0){
        plants = new ArrayList<Algae> ();
        /* 
        if(doOnce == false){   
        for(int i = 0; i < 50; i++){
        //add a new organism to the world for each type of organism
        plants.add(new Algae());
        addObject(plants.get(i), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(600));
        }
        doOnce = true;
        }*/
        /*
        int tempCount = plants.size();

        for(int i = 25; i < tempCount; i+=1){//runs as many times as # of plants that are needed to be deleted

        //remove them from all lists
        //plants.remove(plants.size()-1);//take the first one in the array and kill it
        removeObject(plants.get(0));
        plants.remove(0);
        System.out.println(plants.size() + "    ts working yo");

        }//*/
        int offsetX = getWidth() - 100;
        int offsetY = getHeight();
        
        addObject(new ButtPlus(this) , offsetX , offsetY);
        addObject(new ButtMinus(this) , offsetX + 30 , offsetY);
        /*addObject(new ButtPlus1(this) , offsetX + 30 , offsetY - 75);
        addObject(new ButtMinus1(this) , offsetX + 30 , offsetY - 75);
        addObject(new ButtPlus2(this) , offsetX + 30 , offsetY - 150);
        addObject(new ButtMinus2(this) , offsetX + 30 , offsetY - 150);
        addObject(new ButtPlus3(this) , offsetX + 30 , offsetY - 225);
        addObject(new ButtMinus3(this) , offsetX + 30 , offsetY - 225);
        addObject(new ButtPlus4(this) , offsetX + 30 , offsetY - 300);
        addObject(new ButtMinus4(this) , offsetX + 30 , offsetY - 300);*/
 
        //  addObject(new HerbivoreSlider() , 250 , 175);
        // addObject(new OmnivoreSlider() , 250 , 275);
        //  addObject(new CarnivoreSlider() , 250 , 375);
        /*for (int i = 0; i < 10; i++){
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
        }*/

    }

    public void act(){

        /*GreenfootImage line = new GreenfootImage(50,50);
        line.setColor(Color.BLACK);
        line.drawLine(0,0,500,500);*/
        
        //have only one check function by adding ints in the check to replace the value and .size if enough time ex. check(int value,int arraySize, actor Actor) *Can you use the actor like that?*  
        
        checkPlants();
        /*checkHerbi();
        checkCarni();
        checkOmni();
        checkScavanger();*/
        //this sets the order of apperance from left(clostest to forground) to right(farthest from foreground). Other classes will remain unspecified  
        setPaintOrder(ButtMinus.class, ButtPlus.class);
    }

    public void checkPlants(){
        if (plantValue < 0){plantValue = 0;}
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
    /*
    public void checkHerbi(){
        if (herbiValue < 0){herbiValue = 0;}
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
                Algae temp = new Algae(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                herbivores.add(temp);
                System.out.println("ADD 1:New Herbis size = " + herbivores.size());
            }
        } 
    }

    public void checkCarni(){
        if (carniValue < 0){carniValue = 0;}
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
                Algae temp = new Algae(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                carnivores.add(temp);
                System.out.println("ADD 1:New Carnivores size = " + carnivores.size());
            }
        } 
    }

    public void checkOmni(){
        if (omniValue < 0){omniValue = 0;}
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
                Algae temp = new Algae(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                omnivores.add(temp);
                System.out.println("ADD 1:New Omnivores size = " + omnivores.size());
            }
        } 
    }

    public void checkScavanger(){
        if (scavengerValue < 0){scavengerValue = 0;}
        int tempScavangerNum = scavengers.size();

        if (tempScavangerNum > scavengerValue){
            for (int i = scavengerValue; i < tempScavangerNum; i+=1){
                removeObject(scavengers.get(0));
                scavengers.remove(0);
                System.out.println("REMOVE 1: New Scavangers size = " + scavengers.size());
            }
        }
        else if (tempScavangerNum < scavengerValue){
            for (int i = tempScavangerNum; i < scavengerValue; i+=1){
                Algae temp = new Algae(); 
                addObject(temp,Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()) );
                scavengers.add(temp);
                System.out.println("ADD 1:New Scavangers size = " + scavengers.size());
            }
        } 
    }*/

    
}