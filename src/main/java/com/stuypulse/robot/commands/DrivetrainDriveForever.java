package com.stuypulse.robot.commands;
import com.stuypulse.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DrivetrainDriveForever extends CommandBase{
    
    private final Drivetrain drivetrain;
    
    public DrivetrainDriveForever (Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
     
        addRequirements(drivetrain);
        
    }
    
    @Override
    public void execute() {
        drivetrain.tankDriveVolts(12, 12);

    }

    
     
} 