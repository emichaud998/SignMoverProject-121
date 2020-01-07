import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MovingSignPanel extends JPanel implements ActionListener, ItemListener{
   JButton quit= new JButton("Quit");
   JButton start= new JButton("Start");
   JButton stop= new JButton("Stop");
   JTextField phrase= new JTextField(30);
   Timer clock = new Timer(20,this);//Timer for banner
   Timer tick = new Timer(1000,this);//Timer for lights
   CheckboxGroup Time = new CheckboxGroup();//Checkbox for day and night options
   Checkbox day = new Checkbox("Day", true, Time);
   Checkbox night = new Checkbox("Night", false, Time);
   
   int x= 300;
   int y= 380;
   int textWidth1 = 0;// Width of current textfield phrase 
   int textWidth2 = 0;// Width of last textfield phrase 
   boolean daytime= true;
   boolean lights= false;
   String singlePhrase= " ";//holds last textfield phrase
   String banner=" ";// holds banner of textfield phrases pasted together
   
   //new colors created using Color class
   Color lightBlue= new Color(225, 255, 255);
   Color lightYellow= new Color(255,255,200);
   Color lightGreen= new Color(150, 255, 150);
   Color brown= new Color(100,42,42);
   Color lightBrown= new Color(160,110,110);
    
   public MovingSignPanel(JMenuBar bar){
      setPreferredSize(new Dimension(1000,700));
      this.add(phrase);//Adds text field to panel
      this.add(start);//Adds start button to panel
      start.addActionListener(this);//panel listener for start button
      this.add(stop);//Add stop button to panel
      stop.addActionListener(this);//panel listener for stop button
      this.add(quit);//Add quit button to panel 
      quit.addActionListener(this);//panel listener for quit button
      this.add(day);//Add day checkbox to panel
      day.addItemListener(this);//panel listener for day checkbox
      this.add(night);//Add night checkbox to panel
      night.addItemListener(this);//panel listener for night checkbox
      
   }
     
   public void paintComponent(Graphics g){
      super.paintComponent(g);//Panel cleared 
      
      //Create main building
      g.setColor(Color.lightGray);
      g.fillRect(325, 250, 350, 400);
      
      //Create main building door
      g.setColor(lightBrown);
      g.fillRect(455, 610, 70, 50);
      
      //Create banner on building
      g.setColor(Color.white);
      g.fillRect(325, 350, 350, 40);
      
      //Create shape outlines
      g.drawRect(325,250,349,400);//Outline for main building
      g.drawRect(325,350,350,40);//Outline for banner
      
      moveText(g);
      
      //If day box is checked
      if (daytime){
         setBackground(lightBlue);//light blue sky
         
         //Covers spilled text on both sides of building (light blue)
         g.setColor(lightBlue);
         g.fillRect(0, 350, 325, 40);//Left
         g.fillRect(675, 350, 325, 40);//Right
        
         //Create smaller building
         g.setColor(Color.darkGray);
         g.fillRect(25,400,270,250);
         
         //Create smaller building door
         g.setColor(lightBrown);
         g.fillRect(118, 610, 70, 50);
         
         //Creates blue windows for main and small buildings
         g.setColor(lightBlue);
         makeWindowsMain(g);
         makeWindowsSmall(g);
         
         //Creates sun
         g.setColor(Color.yellow);
         g.fillOval(800, 50, 150, 150);
         
         //Creates cloud
         g.setColor(Color.white);
         g.fillOval(95, 100, 255, 105);
         g.fillOval(120, 85, 120, 115);
         g.fillOval(200, 85, 120, 115); 
      }
     
      //If night box is checked
      else if (!daytime){
         setBackground(Color.blue.darker().darker().darker());//dark blue sky
         
         //Covers spilled text on both sides of building (dark blue)
         g.setColor(Color.blue.darker().darker().darker());
         g.fillRect(0, 350, 325, 40);//Left
         g.fillRect(675, 350, 325, 40);//Right
         
         //Creates smaller building
         g.setColor(Color.darkGray);
         g.fillRect(25, 400, 270, 250);
         
         //Creates smaller building door
         g.setColor(lightBrown);
         g.fillRect(118, 610, 70, 50);
         
         //Creates blinking lights for smaller building
         if (lights){
            g.setColor(Color.yellow);
         }
         else{
            g.setColor(Color.black);
         }
         makeWindowsSmall(g);
         
         //Creates yellow windows for main building
         g.setColor(Color.yellow);
         makeWindowsMain(g);
         
         //Creates moon and stars
         g.setColor(lightYellow);
         g.fillOval(800, 50, 150,150);//Moon
         makeStars(g);
      }
      
      //Creates tree trunk
      g.setColor(brown);
      g.fillRect(780, 500, 40, 150);
      
      //Creates tree leaves
      g.setColor(lightGreen);
      g.fillOval(700, 380, 200, 130);
      g.fillOval(730, 340, 140, 150);
      
      //Creates bench
      g.setColor(lightBrown);
      g.fillRect(850, 600, 100, 35);//Seat
      g.fillRect(850, 635, 20, 15);//Leg1
      g.fillRect(930, 635, 20, 15);//Leg2 
      
      //Creates ground
      g.setColor(Color.gray);
      g.fillRect(0, 650, 1000, 50);  
  }

   //Makes the scrolling banner text
   public void moveText(Graphics g){
      Font bigFont= new Font("Dialog", Font.BOLD, 30);//Creates bigger font (Found in class Font in Java API)
      g.setFont(bigFont);
      textWidth1 = (g.getFontMetrics().stringWidth(phrase.getText()+" "));//Stores width of current textfield phrase plus space after(Found in class Graphics and class FontMetrics)
      textWidth2 = (g.getFontMetrics().stringWidth(singlePhrase+" "));//Stores width of last textfield phrase plus space after
      g.setColor(Color.black);
      g.drawString(banner, x, y);//Moves words across banner as x changes
   }
  
   //Creates windows of main building
   public void makeWindowsMain(Graphics g){ 
      
      //First windows of each row 
      g.fillRect(335, 260, 30, 30);//row 1
      g.fillRect(335, 310, 30, 30);//row 2
      g.fillRect(335, 400, 30, 30);//row 3
      g.fillRect(335, 450, 30, 30);//row 4
      g.fillRect(335, 500, 30, 30);//row 5
      g.fillRect(335, 550, 30, 30);//row 6
      
      //Creates all windows in each row before banner
      for (int rowsHigh=1; rowsHigh<3; rowsHigh++){
         int yval= 210+(50*rowsHigh);//For each next row increase y value by 50
         for (int columns=1; columns<7; columns++){
            int xval=(335+(50*columns));//For each next column increase x value by 50
            g.fillRect(xval, yval, 30, 30);//Creates windows as x and y increase
         }
      }
      
      //Creates all windoes in each row after banner
      for (int rowsLow=1; rowsLow<5; rowsLow++){
         int yval= 350+(50*rowsLow);//For each next row increase y value by 50
         for (int columns=1; columns<7; columns++){
            int xval=(335+(50*columns));//For each next column increase x value by 50
            g.fillRect(xval, yval, 30, 30);//Creates windows as x and y increase
         }
      }
   }
   
  //Creates windows of small building
  public void makeWindowsSmall(Graphics g){
      
      //First windows of each row
      g.fillRect(35, 410, 30, 30);//row 1
      g.fillRect(35, 460, 30, 30);//row 2
      g.fillRect(35, 510, 30, 30);//row 3
      g.fillRect(35, 560, 30, 30);//row 4
      
      //Creates all windows in each row
      for (int rows=1;rows<5;rows++){
         int yPos=360+(50*rows);//For each next row increase y value by 50
         for (int column=1; column<5; column++){
            int xPos=35+(55*column);//For each next column increase x value by 55
            g.fillRect(xPos, yPos, 30, 30);//Creates windows as x and y increase
         }
      }        
  }
  
  //Creates stars in sky
  public void makeStars(Graphics g){
      g.fillOval(20, 100, 10, 10);
      g.fillOval(360, 50, 10, 10);
      g.fillOval(700, 400, 10, 10);
      g.fillOval(250, 200, 10, 10);
      g.fillOval(120, 320, 10, 10);
      g.fillOval(500, 160, 10, 10);
      g.fillOval(730, 70, 10, 10);
      g.fillOval(890, 300, 10, 10);
      g.fillOval(940, 550, 10, 10);
      g.fillOval(750, 220, 10, 10); 
      g.fillOval(150, 60, 10, 10);
  }
   
   //Method specifies button and timer actions
   public void actionPerformed(ActionEvent e){
      
      //Quits program when quit button pressed
      if (e.getSource()==quit){
         System.exit(0);
      }
      
      //Stops clock when stop button pressed and freezes banner
      if (e.getSource()== stop){
         clock.stop();
      }
      
      //Starts banner scrolling with entered text field when start button pressed
      if (e.getSource()== start){
         clock.start();//starts the clock and banner scroll
      if (!singlePhrase.equals(phrase.getText())){//if new phrase entered, start x over from beginning
         x=(325-(textWidth1*3));//x coordinate 3 words back from start of banner
         singlePhrase= phrase.getText();//Stores last phrase for textWidth2 so width does not change while typing new phrase
         
         //Creates banner of 22 strings pasted together
         banner="";
         for (int j=0; j<23; j++){
            banner= banner+(phrase.getText()+" ");
         }
      }
   }  
      
      //Advances x coordinate for banner 1 byte right and resets to beginning once two strings enter banner 
      if (e.getSource()== clock){
         x=x+1;
         if (x==(325-textWidth2))
            x=(325-(textWidth2*3));
      repaint();
      }
      
      //Switches lights value from true to false and false to true
      if (e.getSource()==tick){
         lights=(!lights);
         repaint();
      }
   }
   
   //Method specifies check box actions
   public void itemStateChanged(ItemEvent e){
      
      //Changes daytime value to true when day is checked
      if(e.getItem().equals("Day")){
         daytime = true;
         repaint();
      }
      
      //Changes daytime value to false when night is checked
      //Starts timer for blinking lights
      if(e.getItem().equals("Night")){
         tick.start();
         daytime = false;
         repaint();
     }
   } 
}