package com.stuypulse.robot.subsystems;

import com.ctre.phoenix.platform.DeviceType;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

    // 6 motors
    private final CANSparkMax[] left;
    private final CANSparkMax[] right;
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;
    private final DoubleSolenoid gearShift;
    private final AHRS navx;
        
    public void highGear(double gearShift){
        gearShift.set;
    }    

    public Drivetrain(){
        gearShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, forwardChannel);
        CANSparkMax motor1 = new CANSparkMax(10, MotorType.kBrushless);
        CANSparkMax motor2 = new CANSparkMax(11, MotorType.kBrushless);
        CANSparkMax motor3 = new CANSparkMax(12, MotorType.kBrushless);
        CANSparkMax motor4 = new CANSparkMax(13, MotorType.kBrushless);
        CANSparkMax motor5 = new CANSparkMax(14, MotorType.kBrushless);
        CANSparkMax motor6 = new CANSparkMax(15, MotorType.kBrushless);
        left = new CANSparkMax[] {motor1, motor2, motor3};
        right = new CANSparkMax[] {motor4, motor5, motor6};
        leftEncoder = new Encoder(0,1);
        rightEncoder = new Encoder(2,3);
        navx = new AHRS(I2C.Port.kMXP);
    }

    public double getLeftVoltage() {
        return left[0].getAppliedOutput();
    }
    
    public double getRightVoltage() {
        return right[0].getAppliedOutput();
    
     public void left(double voltage) {
        for (CANSparkMax motor : left) {
            motor.setVoltage(voltage);
        }
    }

    public void right(double voltage) {
       for (CANSparkMax motor : right) {
        motor.setVoltage(voltage);
       }
    }
        
    public void a() {
        gearShift.set(Value.kReverse);
        leftEncoder.getDistance();

    }

    public void tankDriveVolts(double leftVoltage, double rightVoltage) {
        for (CANSparkMax motor:left) {
            motor.setVoltage(leftVoltage);
        for (CANSparkMax motor:right) {
            motor.setVoltage(rightVoltage);
        }
    }
}

@Override
public void periodic(){
    
    SmartDashboard.putNumber("Drivetraian/ Motor 1 Voltage,", motor1.getAppliedOutPut());
    SmartDashboard.putNumber("Drivetraian/ Motor 2 Voltage,", motor2.getAppliedOutPut());
    SmartDashboard.putNumber("Drivetraian/ Motor 3 Voltage,", motor3.getAppliedOutPut());
    SmartDashboard.putNumber("Drivetraian/ Motor 4 Voltage,", motor4.getAppliedOutPut());
    SmartDashboard.putNumber("Drivetraian/ Motor 5 Voltage,", motor5.getAppliedOutPut());
    SmartDashboard.putNumber("Drivetraian/ Motor 6 Voltage,", motor6.getAppliedOutPut());
}
    
}
}