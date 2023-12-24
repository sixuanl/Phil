package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DrivetrainDriveDistance extends CommandBase{
    private Drivetrain drivetrain;
    
    
    public void DrivetrainDriveForever (Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    
      
        addRequirements(drivetrain);

    }
    
    @Override
    public void execute() {
        drivetrain.tankDriveVolts(12, 12);
    }



    public boolean distanceOverFive() {
        if(drivetrain.getLeftDistance() > 5 & drivetrain.getRightDistance() > 5){
            return true;
        } 
        else {
            return false;
        }
    }

  
@Override
    public boolean isFinished() {
        // stop the comman when it returns true
        if(distanceOverFive()){
            drivetrain.tankDriveVolts(0, 0);
            return true;
        } else {
            return false;
        }

    }

    public void end(boolean interrupted) {
        drivetrain.tankDriveVolts(0, 0);
    }

}


