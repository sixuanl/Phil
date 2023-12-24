package com.stuypulse.robot.subsystems; 

import org.ejml.equation.IntegerSequence.For;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax; 

import com.revrobotics.CANSparkMaxLowLevel.MotorType; 

import com.stuypulse.robot.constants.Ports.Gamepad;
import com.stuypulse.stuylib.streams.IStream;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase; 

public class Drivetrain extends SubsystemBase { 
private final DoubleSolenoid gearShift;
    // 6 Motors
private final CANSparkMax[] left = new CANSparkMax[3];
private final CANSparkMax[] right = new CANSparkMax[3];
private final Encoder leftEncoder = new Encoder(0, 1);
private final Encoder rightEncoder = new Encoder(2, 3);
private final AHRS navx;
private final DifferentialDrive drivetrain;
private final double distance;
private final DifferentialDriveOdometry odometry;
private final Field2d field;
private final IStream speed;
private final IStream angle;
private final Gamepad driver;


public Drivetrain(){
    // gearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 0);

    this.speed =  driver.getLeftX;
    this.angle = driver.getRight
//         this.angle.getRightX() - drive.getLeftX())
//         .filtered(
//             x -> SLMath.deadband(x, 1),
//             x -> SLMath.spow(x, 2),
//             )
// 
    
    field = new Field2d();
    left[0] = new CANSparkMax(10, MotorType.kBrushless);
    left[1] = new CANSparkMax(11, MotorType.kBrushless);
    left[2] =  new CANSparkMax(12, MotorType.kBrushless);

    right[0] = new CANSparkMax(13, MotorType.kBrushless);
    right[1] = new CANSparkMax(14, MotorType.kBrushless);
    right[2] =  new CANSparkMax(15, MotorType.kBrushless);

drivetrain = new DifferentialDrive(
    new MotorControllerGroup(left),
    new MotorControllerGroup(right)
);
}

public Pose2d getPose() {
    updateOdometry();
    return odometry.getPoseMeters();
}

public Field2d getField() {
    return field;
}
@Override
public void stop() {
    drivetrain.stopMotor();
}

public void tankDrive(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive (leftSpeed, rightSpeed);
}

public void arcadeDrive (double speed, double rotation) {
    drivetrain.arcadeDrive (speed, rotation);
}

public void curvatureDrive (double speed, double rotation, boolean isQuickTurn) {
    drivetrain.curvatureDrive(speed, rotation, isQuickTurn);
    }
// private final CANSparkMax

// p is percentage


// public void a() {
//     gearShift.set(Value.kReverse);
// }

public double getLeftDistance() {
    return leftEncoder.getDistance();
}

public double getRightDistance() {
    return rightEncoder.getDistance();
}

// public void highGear() {
// gearShift.set(Value.kReverse);
//     }

// public void lowGear() {
// gearShift.set(Value.kForward);
//     }

public void tankDriveVolts(double leftVoltage, double rightVoltage) {
    for (CANSparkMax motor : left) {
        motor.setVoltage(leftVoltage);
    }
    for (CANSparkMax motor : right) {
        motor.setVoltage(rightVoltage);
    }
    drivetrain.feed();
}

private void updateOdometry(){
    odometry.update(navx.getRotation2d(), getLeftDistance(), getRightDistance());{
        
    }
}

@Override
public void periodic() {
updateOdometry();
field.setRobotPose(odometry.getPoseMeters());
SmartDashboard.putData("Field", getField());
    SmartDashboard.putNumber("Drivetrain/Motor 1 Voltage", left[1].getAppliedOutput());
    SmartDashboard.putNumber("Drivetrain/Motor 1 Voltage", left[2].getAppliedOutput());
    SmartDashboard.putNumber("Drivetrain/Motor 2 Voltage", left[0].getAppliedOutput());
    SmartDashboard.putNumber("Drivetrain/Motor 3 Voltage", right[0].getAppliedOutput());
    SmartDashboard.putNumber("Drivetrain/Motor 4 Voltage", right[1].getAppliedOutput());
    SmartDashboard.putNumber("Drivetrain/Motor 5 Voltage", right[2].getAppliedOutput());

}

}
