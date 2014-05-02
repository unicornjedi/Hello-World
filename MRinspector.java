/*
 * MRinspector.java
 * 
 * Copyright 2014 Elijah Diamond <unicornjedi@beautiful.silksky.com>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * Author <Elijah Diamond>
 * Date created <3/3/2014>
 * Date modified <3/3/2014>
 * 
 * DESCRIPTION:
 * MRInspector is a tool to measure a vehicle's pollutants. 
 * Then the vehicle is evaluated under emissions inspection criteria. 
 * 
 */

import java.io.*;
import java.util.*;

public class MRinspector {
	
	public static void main (String args[]) 
	
		throws java.io.IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		boolean Pass = true;
		int version = 1;
		int ODO;
		double CO, HC, NOX, NMHC;
		String input;
		String SER;
		
		//initialization of variables		
		SER = "null";
		ODO = 0;
		CO = 0;
		HC = 0;
		NOX = 0;
		NMHC = 0;
		
		//Output program information
		System.out.println("MRinspector v"+version);
		System.out.println("Copyright 2014 Unicornjedi, Ent.");
		System.out.println("This is free software with ABSOLUTELY NO WARRANTY.\n");
		
		System.out.println("This motor vehicle is evualated by some of the criteria under section:");
		System.out.println("9VAC5-91-420. Inspection procedure; rejection, pass, fail, waiver.");
		
		System.out.print("\nSerial Number: ");
		input=br.readLine();
		SER=input;
		
		System.out.print("Odometer Reading: ");
		input=br.readLine();
		ODO=Integer.parseInt(input);
					
		System.out.print("Carbon Monoxide: ");
		input=br.readLine();
		CO=Double.parseDouble(input);
			
			if(CO<6.0){	
				System.out.println("PASSED\n");
				
				System.out.print("Hydro Carbons: ");
				input=br.readLine();
				HC=Double.parseDouble(input);
							
				if(HC<4.0){
					System.out.println("PASSED\n");
					
					System.out.print("Nitrogen Oxides: ");
					input=br.readLine();
					NOX=Double.parseDouble(input);
					
					if(NOX<0.45){
						System.out.println("PASSED\n");
						
						System.out.print("Non Methan Hyrdro Carbons: ");
						input=br.readLine();
						NMHC=Double.parseDouble(input);
						
						if(NMHC<0.3){
							System.out.println("PASS\n");
												
							
						}else {
							System.out.println("FAILURE"); 
							Pass=false;}						
					}else {
						System.out.println("FAILURE");
						 Pass=false;}
				}else {
					System.out.println("FAILURE"); 
					Pass=false;}
			}else {
				System.out.println("FAILURE"); 
				Pass=false;}	
				
		br.close();
		System.out.println("\nUpdating Database...   [OKAY]\n");
		
		//Output		
		if(Pass) {
			 System.out.println("SN: "+SER+"ODO: "+ODO+"\tPASSED inspection!");	 
			 System.out.println("Congratulations, You PASSED inspection!");
			} 
			else {
				System.out.println("SN: "+SER+" ODO: "+ODO+"\t!!!FAILED inspection. Fined $1000!!!");
				}
				
	}
} //end of program

