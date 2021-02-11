/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentviewer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 *
 * @author laltobel
 */
public class Appointment implements Comparable<Appointment>{
     public    String     description;
   protected Calendar   date;
   private   String     location;
   private   int        duration;
   Person p = new Person();
   
   public Appointment(int year, int month, int day, int hour, int minute, String descr)
   {
	   this.date = new GregorianCalendar();
	   date.set(Calendar.YEAR, year);
	   date.set(Calendar.MONTH, month);
	   date.set(Calendar.DAY_OF_MONTH, day);
	   date.set(Calendar.HOUR_OF_DAY, hour);
	   date.set(Calendar.MINUTE, minute);
	   description = descr;
   }
   
   public String print()
   {
	   String appt;
	   
	   int ayear      = date.get(Calendar.YEAR);
	   int amonth     = date.get(Calendar.MONTH); 
	   int adayOfMonth= date.get(Calendar.DAY_OF_MONTH);
	   int hourOfDay  = date.get(Calendar.HOUR_OF_DAY); 
	   int minute     = date.get(Calendar.MINUTE);
	   
	   if (minute > 10)
		     appt = "" + hourOfDay + ":" + minute + " " + description;
		   else
			 appt = "" + hourOfDay + ":0" + minute + " " + description; 
	   return appt;
   }
   
   public Calendar getDate()
   {
	   return date;
   }
   
   public boolean occursOn(int year, int month, int day, int hour, int minute)
   {
	   if (date.get(Calendar.YEAR)== year && 
		   date.get(Calendar.MONTH) == month &&
	       date.get(Calendar.DAY_OF_MONTH) == day &&
	       date.get(Calendar.HOUR_OF_DAY) == hour && 
	       date.get(Calendar.MINUTE) == minute)
         return true;
	   return false;
   }
   
   public boolean occursOn(int year, int month, int day)
   {
	   if (date.get(Calendar.YEAR)== year && 
		   date.get(Calendar.MONTH) == month &&
	       date.get(Calendar.DAY_OF_MONTH) == day)
         return true;
	   return false;
   }
   
   public boolean recurring()
   {
	   return false;
   }
   
   
   
   public int compareTo(Appointment other)
   {
	   
	   return date.compareTo(other.date);
   }
}
