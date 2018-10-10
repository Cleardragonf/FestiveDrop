package me.cleardragonf.festivedrop;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager {



    private static ConfigurationManager instance = new ConfigurationManager();



    public static ConfigurationManager getInstance(){
        return instance ;
    }



    private File configDir;



    //The configuration folder for this plugi
    public void ConfigurationManager2(File configDir) {
        this.configDir = configDir;
    }


    // The config Manager for the mail storage file


    private ConfigurationLoader<CommentedConfigurationNode>configLoader1;
    private ConfigurationLoader<CommentedConfigurationNode>TimeTrackLoader;

    // the in-memory version of the mail storage file
    private CommentedConfigurationNode config1;
    private CommentedConfigurationNode TimeTracker;



    public void enable()
    {
        //setting the name of the file
        File Week1 = new File(this.configDir, "Week1.conf");
        File TimeTrackerTime = new File(this.configDir, "TimeTracking.conf");

        this.configLoader1 = HoconConfigurationLoader.builder().setFile(Week1).build();
        this.TimeTrackLoader = HoconConfigurationLoader.builder().setFile(TimeTrackerTime).build();

        try{
            //create a new folder if it does not exist
            if(!this.configDir.isDirectory()){
                this.configDir.mkdirs();
            }
            //create a new one if the file does not exist

            if(!TimeTrackerTime.isFile()){
                try{
                    TimeTrackerTime.createNewFile();
                    loadTime();
                    TimeTracker.getNode("========Time Tracking========").setComment("The Point of this config is to keep track of the Time and Date");
                    TimeTracker.getNode("========Time Tracking========", "Day: ").setComment("Day number in Game. Between 1-30").setValue("1");
                    TimeTracker.getNode("========Time Tracking========", "Time: ").setComment("Set the Time in Game. Between 0 - 24000").setValue("0");
                    saveTime();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(!Week1.isFile()){
                try{
                    Week1.createNewFile();
                    load1();
                    config1.getNode("Server Drop Event").setComment("This Portion of the Config is for Setting up the Server Drop");
                    config1.getNode("Server Drop Event", "Time Between: ").setComment("Please enter a Time in Seconds").setValue(60);
                    config1.getNode("Server Drop Event", "How Many of Each: ").setComment("Please Select a number that All of the Drops will leave.  Smaller number means Shorter Drops").setValue(10);
                    config1.getNode("Server Drop Event", "Time Between Item Waves: ").setComment("Please enter a Time in Seconds, that'll represent the number between each item dropping").setValue(2);
                    save1();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            //load the stored mails
            this.config1 = this.configLoader1.load();
            this.TimeTracker = this.TimeTrackLoader.load();
        }catch (IOException e){
            return;
        }
    }
    private void loadTime() {
        try{
            TimeTracker = TimeTrackLoader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void load1(){
        try{
            config1 = configLoader1.load();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void saveTime() {
        try{
            TimeTrackLoader.save(TimeTracker);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void save1(){
        try{
            configLoader1.save(config1);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadconfig1(){
        try{
            config1 =configLoader1.load();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public CommentedConfigurationNode getConfig1() {
        return config1;
    }
    public CommentedConfigurationNode getTimeTrack(){
        return TimeTracker;
    }
}