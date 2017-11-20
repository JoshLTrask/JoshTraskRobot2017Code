package org.usfirst.frc.team4152.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


//Iterative Robot allows for Init and Periodic for both Autonomous and TeleOP
public class Robot extends IterativeRobot {
	
	
	RobotDrive myRobot = new RobotDrive(0, 1);//left is pwm 0, right is pwm 1
	Joystick stick = new Joystick(0);
	//Note to self: get input from other joystick for fun
	
	Timer timer = new Timer();// keep track of timme for autonomous
	
	int timesCalled = 0;//Used for troubleshooting
	
	Gyro gyro = new AnalogGyro(0);//analog 0 
	
	//Roborio Accelerometert 
	Accelerometer accel = accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
	
	Encoder Encoder = new Encoder(0, 1, false);///left encoder is DIO 0,1

	final double wheelCircumference = 6 * Math.PI;//set circumference of wheel w/ math
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Encoder.setDistancePerPulse(wheelCircumference/2036);
		Encoder.reset();
		gyro.calibrate();
		gyro.reset();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {//resets sensors used in autonomous
		timer.reset();
		timer.start();
		Encoder.reset();
		gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		// Code included for accelerometer included
		
		
		// Drive for 3.0 seconds
		
		if (timer.get() < 3.0) {
			myRobot.drive(-0.4, 0.0); // drive forwards at two-fifth speed
			timesCalled++;
			
			//Update Smartboard variables
			SmartDashboard.putNumber("timesCalled", timesCalled);
			SmartDashboard.putNumber("Time", timer.get());
			SmartDashboard.putNumber("Angle", gyro.getAngle());
			SmartDashboard.putNumber("Accel X", accel.getX());
			SmartDashboard.putNumber("Accel Y", accel.getY());
			SmartDashboard.putNumber("Accel Z", accel.getZ());
			SmartDashboard.putNumber("Encoder", Encoder.getDistance());
			SmartDashboard.putNumber("Gyro Rate",gyro.getRate());
		
			}
		
		else {
			myRobot.drive(0.0, 0.0); // stop robot
		
			
		}
		
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
		Encoder.reset();
		gyro.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		myRobot.arcadeDrive(stick.getRawAxis(1),stick.getRawAxis(0)*-1);
		
		
		// Do smartboard stuff
		SmartDashboard.putNumber("Angle", gyro.getAngle());
		SmartDashboard.putNumber("Accel X", accel.getX()*9.8);
		SmartDashboard.putNumber("Accel Y", accel.getY()*9.8);
		SmartDashboard.putNumber("Accel Z", accel.getZ()*9.8);
		SmartDashboard.putNumber("Encoder", Encoder.getDistance());
		SmartDashboard.putNumber("Gyro Rate",gyro.getRate());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
//Running around at the speed of sound. got no place to go, gotta follow my rainbow. 
//Don't mess around gotta keep moving on, what lies ahead only one way to find out!
//
