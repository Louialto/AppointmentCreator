/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentviewer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 *
 * @author laltobel
 */
public class AppointmentFrame extends JFrame {
     private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 800;
   
   private static final int AREA_ROWS = 30;
   private static final int AREA_COLUMNS = 30;
  
   private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
   
   Calendar calendar;
   SimpleDateFormat sdf;
   
   private JTextField 	hourText;
   private JLabel 		hourLabel;
   private int 			hour;
   private JTextField 	minuteText;
   private JLabel 		minuteLabel;
   private int 			minute;
   private JTextField 	dayText;
   private JLabel 		dayLabel;
   private int 			day;
   private JTextField 	monthText;
   private JLabel 		monthLabel;
   private int 			month;
   private JTextField 	yearText;
   private JLabel 		yearLabel;
   private int 			year;
   private JLabel 		dateLabel;
   
   private JButton      nextDay;
   private JButton      prevDay;
   private JButton      dateButton;
   private JButton      createAppointment;
   private JButton      cancelAppointment;
  
   private JTextArea    description;
   private JTextArea 	appointmentArea;
   private JPanel 		appointmentPanel;
   private JScrollPane 	scrollPane;
   private ActionListener listener;

   /**
      Constructs the frame.
   */
   public AppointmentFrame()
   {  
	   
	   sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
	   calendar = new GregorianCalendar();
	   
	   dateLabel = new JLabel(sdf.format(calendar.getTime()));
	   this.setLayout(new BorderLayout());
       add(dateLabel, BorderLayout.NORTH);
       
	   appointmentArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
	   appointmentArea.setText("");
	   appointmentArea.setEditable(false);
	   scrollPane = new JScrollPane(appointmentArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   add(scrollPane, BorderLayout.CENTER); 

      createControlPanel();
      
      scrollPane.repaint();
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   
   
   /**
      
   */
   private void createControlPanel()
   {
      JPanel datePanel        = createDatePanel();
      JPanel actionPanel      = actionAppointmentPanel();
      JPanel descriptionPanel = createDescriptionPanel();

      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new GridLayout(3, 1));
      controlPanel.add(datePanel);
      controlPanel.add(actionPanel);
      controlPanel.add(descriptionPanel);

      add(controlPanel, BorderLayout.SOUTH);
   }
   
   class PrintNextDayAppointmentsListener implements ActionListener
   {  
      public void actionPerformed(ActionEvent event)
      {  
    	  calendar.add(Calendar.DAY_OF_MONTH, 1);
    	  dateLabel.setText(sdf.format(calendar.getTime()));
    	  dateLabel.repaint();
          printAppointments();
      }
   }
  
   class PrintPrevDayAppointmentsListener implements ActionListener
   {  
      public void actionPerformed(ActionEvent event)
      {  
    	  calendar.add(Calendar.DAY_OF_MONTH, -1);
    	  dateLabel.setText(sdf.format(calendar.getTime()));
    	  dateLabel.repaint();
    	  printAppointments();
      }
   }
   
   class PrintDateAppointmentsListener implements ActionListener
   {  
      public void actionPerformed(ActionEvent event)
      {  
          int day   = Integer.parseInt(dayText.getText());
          int month = Integer.parseInt(monthText.getText())-1;
          int year  = Integer.parseInt(yearText.getText());
          calendar.set(Calendar.YEAR, year);
  		  calendar.set(Calendar.MONTH, month);
  		  calendar.set(Calendar.DAY_OF_MONTH, day);
  		  dateLabel.setText(sdf.format(calendar.getTime()));
  	      dateLabel.repaint();
    	  printAppointments();
      }
   }

   /**
      
   */
   private JPanel createDatePanel()
   {
	   JPanel nextPanel = new JPanel();
	   nextPanel.setLayout(new GridLayout(1, 2));
	   prevDay = new JButton("<");
	   nextDay = new JButton(">");
	     
	   ActionListener listener = new PrintNextDayAppointmentsListener();
	   nextDay.addActionListener(listener);
	   listener = new PrintPrevDayAppointmentsListener();
	   prevDay.addActionListener(listener); 
	   nextPanel.add(prevDay);
	   nextPanel.add(nextDay);
	   
	   JPanel dateEnterPanel = new JPanel();
	   dayText = new JTextField(2);
	   dayLabel = new JLabel("Day");
	   dateEnterPanel.add(dayLabel);
	   dateEnterPanel.add(dayText);
	   monthText = new JTextField(2);
	   monthLabel = new JLabel("Month");
	   dateEnterPanel.add(monthLabel);
	   dateEnterPanel.add(monthText);
	   yearText = new JTextField(4);
	   yearLabel = new JLabel("Year");
	   dateEnterPanel.add(yearLabel);
	   dateEnterPanel.add(yearText);
	   
	   JPanel showPanel = new JPanel();
	   dateButton = new JButton("Show");	   
       listener = new PrintDateAppointmentsListener();
       dateButton.addActionListener(listener);
       showPanel.add(dateButton);
              
       JPanel datePanel = new JPanel();
       datePanel.setBorder(new TitledBorder(new EtchedBorder(), "Date"));
       datePanel.setLayout(new GridLayout(3, 1));
       datePanel.add(nextPanel);
       datePanel.add(dateEnterPanel);
       datePanel.add(showPanel);
      
       return datePanel;
   }

   
   class CreateAppointmentListener implements ActionListener
   {  
      public void actionPerformed(ActionEvent event)
      {  
         createAppointment();
      }
   }
  
   class CancelAppointmentListener implements ActionListener
   {  
      public void actionPerformed(ActionEvent event)
      {  
         cancelAppointment();
      }
   }
   
   /**
     
   */
   private JPanel actionAppointmentPanel()
   {
	  JPanel timePanel = new JPanel();
      hourText = new JTextField(4);
      hourLabel = new JLabel("Hour");
      minuteText = new JTextField(4);
      minuteLabel = new JLabel("Minute");
      timePanel.add(hourLabel);
      timePanel.add(hourText);
      timePanel.add(minuteLabel);
      timePanel.add(minuteText);
      
      JPanel actionPanel = new JPanel();
      createAppointment = new JButton("CREATE");
      listener = new CreateAppointmentListener();
      createAppointment.addActionListener(listener);
      listener = new CancelAppointmentListener();
      cancelAppointment = new JButton("CANCEL");
      cancelAppointment.addActionListener(listener);
      actionPanel.add(createAppointment);
      actionPanel.add(cancelAppointment);
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(2, 1));
      panel.setBorder(new TitledBorder(new EtchedBorder(), "Action"));
      panel.add(timePanel);
      panel.add(actionPanel);
      
      return panel;
   }

   /**
      
   */
   private JPanel createDescriptionPanel()
   {
	  description = new JTextArea(4, 20);
	  description.setEditable(true);
	  JPanel panel = new JPanel();
      panel.add(description);
      panel.setBorder(new TitledBorder(new EtchedBorder(), "Description"));

      return panel;
   }

   /**
      
   */
   private void createAppointment()
   {  
      int hour, minute;
      
	  String hourString = hourText.getText();
      if (hourString.equals("")) return;
      
	  String minuteString = minuteText.getText();
      if (minuteString.equals(""))
    	  minuteString = "0";
	  
      hour   = Integer.parseInt(hourString);  
      minute = Integer.parseInt(minuteString);
      
      int year = calendar.get(Calendar.YEAR);
	  int month = calendar.get(Calendar.MONTH); 
	  int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
      
	  calendar.set(Calendar.HOUR_OF_DAY, hour);
	  calendar.set(Calendar.MINUTE, minute);	
	  String descr = description.getText();
	  
	  if (!findAppointment(year, month, dayOfMonth, hour, minute))
	  {
         Appointment appt = new Appointment(year, month, dayOfMonth, hour, minute, descr);
         appointments.add(appt);
         Collections.sort(appointments);
         
         printAppointments();
         
         description.setText("");
         hourText.setText("");
         minuteText.setText("");
	  }
	  else
	    description.setText("CONFLICT!!"); 
	  
      scrollPane.repaint();
   }
   
   private void cancelAppointment()
   {  
      int hour, minute;
      int cancelledAppt = -1;
      
	  String hourString = hourText.getText();
      if (hourString.equals("")) return;
      
      String minuteString = minuteText.getText();
      if (minuteString.equals(""))
    	  minuteString = "0";
	  
      hour   = Integer.parseInt(hourString);  
      minute = Integer.parseInt(minuteString);
      
      int year = calendar.get(Calendar.YEAR);
	  int month = calendar.get(Calendar.MONTH); 
	  int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
      
	  for (int i = 0; i < appointments.size(); i++)
	  {
		   Appointment appt = appointments.get(i);
		   		   
		   if (appt.occursOn(year, month, dayOfMonth, hour, minute))
		   {
		      cancelledAppt = i;
		      break;
		   }
	  }
	  appointments.remove(cancelledAppt);
      
	  Collections.sort(appointments);
      
      printAppointments();
      
      description.setText("");
      hourText.setText("");
      minuteText.setText("");
               
      scrollPane.repaint();
   }
   
   private void printAppointments()
   {
	   // Get date of current appointment day
	   int year = calendar.get(Calendar.YEAR);
	   int month = calendar.get(Calendar.MONTH); 
	   int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	   
	   appointmentArea.setText("");
	   
	   for (int i = 0; i < appointments.size(); i++)
	   {
		   Appointment appt = appointments.get(i);
		  
		   if (appt.occursOn(year, month, dayOfMonth))
		      appointmentArea.append(appt.print() + "\n\n");
	   }
   }
   
   private boolean findAppointment(int year, int month, int dayOfMonth, int hour, int minute)
   {
	   for (int i = 0; i < appointments.size(); i++)
	   {
		   Appointment appt = appointments.get(i);
		  
		   if (appt.occursOn(year, month, dayOfMonth, hour, minute))
		     return true;
	   }
	   return false;
   }
 }


