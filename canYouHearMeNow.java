/*
 * canYouHearMeNow.java
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
 * Date created <4/10/2014>
 * Date modified <4/17/2014>
 * 
 * -------------------------------------------------------
 * 
 * CSC201 TAKE HOME PROGRAM
 * 
 * canYouHearMeNow prints out an invoice for a serivce company providing
 * a variety of services.
 * 
 *  METHOD LIST
 * 
 *  1  versionInfo   	   	[A1]
 *  2  say			      		[A2]
 *  3  sayLine 			    	[A3]
 *  4  findMinutePrice		[A4]
 *  5  findTextPrice		  [A5]
 *  6  findPhotoPrice		  [A6]
 *  7  applyInternetPrice	[A7]
 *  8  applyCablePrice 		[A8]
 *  9  verifyNum			    [A9]
 *  10 invoice			    	[A10]
 * 
 */

import java.io.*;
import java.util.*; 
import java.text.*;

public class canYouHearMeNow {
	
	private static DecimalFormat num = new DecimalFormat("$,###.##");
	private static InputStreamReader isr = new InputStreamReader(System.in);
	private static BufferedReader br = new BufferedReader(isr);
			
	private static final int monthRate=86;
	private static double months;
	
	private static String customer, ID;
		
	//prices-------------------------
	private static double minutesPrice;
	private static double textPrice;
	private static double photoPrice;
	private static double internetPrice;
	private static double cablePrice;
	//-------------------------------
		
	private static double temp;
			
	public static void main (String args[]) 
	throws java.io.IOException		
	{
						
		versionInfo("canYouHearMeNow",1,"Unicornjedi, Ent");
		
		for(int i=1;i<=5;i++) {
		
			say("\nCustomer (FULL NAME): ");
			customer = br.readLine();
		
			say("ID: \t\t");
			ID = br.readLine();
				
			say("Months: \t");
			months=verifyNum(br);
			if (months<=1) {
				say("No bill for now.\n");
			}
			
			else {
				months--;
		
				say("Minutes: \t");
				verifyNum(br);
				findMinutePrice(temp);
						
				say("Texts: \t\t");
				verifyNum(br);
				findTextPrice(temp);
				
				say("Photos: \t");
				verifyNum(br);
				findPhotoPrice(temp);
		
				say("Internet(T/F): \t");
				applyInternetPrice(br.readLine());
		
				say("Cable(T/F): \t");
				applyCablePrice(br.readLine());
		
				invoice();
			}		
		} //end loop
		
		br.close();
		
	} //end of main
	
	// [A1]	
	private static void versionInfo(String name, int version, String company){
		sayLine(name+" v"+version);
		sayLine("Copyright 2014 Unicornjedi, Ent.");
		sayLine("This is free software with ABSOLUTELY NO WARRANTY.\n");
	} //end of versionInfo
	
	// [A2]
	private static void say(String input){
		System.out.print(input);
	} //end of say
	
	// [A3]
	private static void sayLine(String input){
		System.out.println(input);
	} //end of sayLine
	
	// [A4]
	private static double findMinutePrice(double minutes){
		
		double minExtra=0.45;
							
		if(minutes>1400){
		minutesPrice = (minutes-1400)*minExtra;
		return minutesPrice;	
		}
		else
		minutesPrice = 0;
		return minutesPrice;		
	} //end of findMinutePrice
	
	// [A5]
	private static double findTextPrice(double texts){
				
		textPrice = 0.20;
		textPrice = texts*textPrice;		
		return textPrice;		
	} //end of findTextPrice
	
	// [A6]
	private static double findPhotoPrice(double photos){
		
		photoPrice = 0.50;
		photoPrice = photos*photoPrice;	
		return photoPrice;  
    } //end of findPhotoPrice
	
	// [A7]	
	private static double applyInternetPrice(String input){
		
		boolean internet = Boolean.parseBoolean(input);
		if(internet){
		internetPrice=25;
		return internetPrice*=months;
		}
		else
		return internetPrice=0;
				
	}//end of applyInternetPrice
	
	// [A8]
	private static double applyCablePrice(String input){
		
		boolean cable = Boolean.parseBoolean(input);
		if(cable){
		cablePrice=19;
		return cablePrice*=months;
		}
		else
		return cablePrice=0;
		
	} //end of applyCablePrice
	
	// [A9]
	private static double verifyNum(BufferedReader br)
	throws java.io.IOException
	{
		boolean again;
		String input;
	do{
		input=br.readLine();
				
      if (input.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {  
            again=false;           
        } 
			else {  
				System.out.print("Invalid input. Try again.\t");  
				again=true;
			} 			
	 }while(again);
	 
	 again=false; //resets 'again' boolean
	 temp=Math.abs(Double.parseDouble(input));	
	 return temp;
	 
	}//end of verifyNum
	
	// [A10]
	private static void invoice(){
		
		Date date = new Date();
				
		double tax=0.055;
		double surcharge=.01;
		
		double grandtotal;
		double subtotal;
		
		double internetDiscount;
	    double cableDiscount;
				
		subtotal=minutesPrice+textPrice+photoPrice+internetPrice+cablePrice;
		surcharge=subtotal*surcharge;
		tax=subtotal*tax;
				
		if(internetPrice>0){
				internetDiscount=0.15;
				internetDiscount = subtotal*internetDiscount;
			}
			else{
			internetDiscount=0;
			}
			
		if(cablePrice>0){
				cableDiscount=.05;
				cableDiscount = subtotal*cableDiscount;
			}
			else{
			cableDiscount=0;
			}	
		
		grandtotal = subtotal+tax+surcharge-internetDiscount-cableDiscount;
				
		sayLine("\nCellphone Corp INVOICE \n");
		sayLine("today is: " + date);
		sayLine("---------------------");
		sayLine("MINUTES: \t" + num.format(minutesPrice));
		sayLine("TEXT: \t\t" + num.format(textPrice));  
		sayLine("PHOTO: \t\t" + num.format(photoPrice));
		sayLine("INTERNET: \t" + num.format(internetPrice));  
		sayLine("CABLE: \t\t" + num.format(cablePrice));
		sayLine("\nSUBTOTAL: \t\t" + num.format(subtotal));    
		sayLine("---------------------");
		sayLine("EXTRA FEES \n");
		sayLine("TAX: \t\t" + num.format(tax)); 
		sayLine("SURCHARGE: \t" + num.format(surcharge)); 
		sayLine("---------------------");
		sayLine("DISCOUNTS \n"); 
		sayLine("CABLE DISCOUNT: \t" + num.format(cableDiscount)); 
		sayLine("INTERNET DISCOUNT: \t" + num.format(internetDiscount)); 
		sayLine("---------------------");
		sayLine("GRANDTOTAL: \t\t" +  num.format(grandtotal)); 
		sayLine("---------------------");
		sayLine("Payment for customer " + customer + " " + ID +"\n");          
	} //end of invoice
}// end of progam



