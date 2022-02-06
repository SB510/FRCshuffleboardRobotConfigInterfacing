package frc.robot;

//import java.lang.reflect.Field;
import java.lang.reflect.*;
import java.util.HashMap;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class RobotConfig {

    //-----------------STORE CONTSTANTS HERE-----------------
    public static final double TEST_VALUE = new Double(3);//Integer(3);
    public static final double Test_2 = new Double(4);

    //-------------------------------------------------------





    private static HashMap<String, NetworkTableEntry> shuffleBoardElements = new HashMap<String, NetworkTableEntry>();
    private static RobotConfig INSTANCE;
    private static boolean first = true;
    public static RobotConfig getInstance(){
        //the Config itself throws exeptions so INSTANCE has to be defined here so that we can catch the Exceptions
        try {
            INSTANCE = new RobotConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (INSTANCE);
    }

    private RobotConfig() throws Exception{
        //find all feilds in this class
        Field[] fields = this.getClass().getDeclaredFields();
        if(first == true){
            for(Field f : fields){
                if((!f.getName().equals("INSTANCE")) & !(f.getType() == HashMap.class) & !(f.getName().equals("first"))){
                    f.setAccessible(true);

                    Field modifiersField = Field.class.getDeclaredField("modifiers");
                    modifiersField.setAccessible(true);
                    modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    shuffleBoardElements.put(f.getName(),
                    Shuffleboard.getTab("Configuration")
                        .add(f.getName(), 1)
                        .withWidget("Number Slider")
                        .withPosition(1, 1)
                        .withSize(2, 1)
                        .getEntry()
                    );
                    System.out.println(f.getName());
                    double newValue = shuffleBoardElements.get(f.getName()).getDouble(1.0);
                    f.set(null, newValue);
                }
                
            }
            first = false;
        }
        else {
            //loop through each feild and make it accesible, then set it to some new value
            for(Field f : fields){
                if((!f.getName().equals("INSTANCE")) & !(f.getType() == HashMap.class) & !(f.getName().equals("first"))){
                    f.setAccessible(true);

                    Field modifiersField = Field.class.getDeclaredField("modifiers");
                    modifiersField.setAccessible(true);
                    modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                    
                    double newValue = shuffleBoardElements.get(f.getName()).getDouble(1.0);//Shuffleboard.getTab("Configuration").getComponents().get(index);
                    f.set(null, newValue);
                }
            }
        }
    }
}
