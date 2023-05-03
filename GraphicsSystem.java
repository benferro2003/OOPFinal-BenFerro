package c3654717;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import javax.imageio.*;
import javax.swing.JFrame;
import uk.ac.leedsbeckett.oop.LBUGraphics;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
public class GraphicsSystem extends LBUGraphics
{
    public static void main(String[] args)
    {
            new GraphicsSystem(); 
            
            
    }
    
    @Override
    public void about() {
    	super.about();
    	clear();
		setPenColour(Color.yellow);
		setStroke(30);
	    penDown();
	    setxPos(130);
	    setyPos(100);
	    forward(195);
	    setStroke(20);
	    penDown();
	    drawCircle(55, 180, 140);
	    drawCircle(55, 180, 250);
	    reset();
	    

	    setPenColour(Color.yellow);
		setStroke(20);
		penDown();
	    setxPos(332);
	    setyPos(100);
	    forward(200);
	    setxPos(332);
	    setyPos(100);
	    turnLeft(90);
	    forward(100);
	    setxPos(332);
	    setyPos(200);
	    forward(100);
	    setxPos(332);
	    setyPos(300);
	    forward(100);
	    
	    reset();
	    
	    
	    setPenColour(Color.yellow);
		setStroke(20);
		penDown();
	    setxPos(532);
	    setyPos(100);
	    forward(200);
	    setxPos(532);
	    setyPos(100);
	    turnLeft(30);
	    forward(230);
	    turnRight(30);
	    forward(-200);
	    reset();
	    cycleColours();
	}
    
    public void pencolour(int red, int green, int blue)
    {
		
		setPenColour(new Color(red,green,blue));
		penDown();
	}
    
    public void square(int length) 
    {
		penDown();
		forward(length);
		turnLeft(90);
		forward(length);
		turnLeft(90);
		forward(length);
		turnLeft(90);
		forward(length);
		reset();	
	}
    
    public void penwidth(int width) 
    {
    	setStroke(width);	
    }
    
    public void triangle(int size) 
    {	
    	penDown();
		turnLeft(120);
		forward(size);
		turnLeft(120);
		forward(size);
		turnLeft(120);
		forward(size);
 	
    }
    
    public void triangles(int side1, int side2, int side3) { // only works with sides 90 156 180 //
    	penDown();
    	forward(side1);
    	turnLeft(90);
    	forward(side2);
    	turnRight(210);
    	forward(side3);
    	turnLeft(60);
    	reset();
    	
    	
    }

    public GraphicsSystem()
    {
            JFrame MainFrame = new JFrame();            
            MainFrame.setLayout(new FlowLayout());     
            MainFrame.add(this);                       
            MainFrame.setSize(850,450);               
            MainFrame.setVisible(true);                
                                                
                                                                                   
    }
    
    
    ArrayList<String> commandList = new ArrayList<String>();
    boolean penIsDown = true;

    public void addCommand(String command, int amount) {
        String cmdString = command + " " + amount;
        commandList.add(cmdString);
    }

    public void writeCommand() {
        try {
            File file = new File("commands.txt");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            try (BufferedWriter writecommand = new BufferedWriter(new FileWriter(file))) {
                for (String command : commandList) {
                    writecommand.write(command);
                    writecommand.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  


    
    public void processCommand(String command)           
    {
    
    	
    	command = command.toLowerCase();
    	String[] VALID_COMMANDS = {"about", "penup", "pendown","turnleft", "turnright", "forward", "backward", "red", "green", "black", "white", "reset", "clear", "save", "load", "loadcommands", "savecommands", "square", "pencolour", "penwidth", "triangle"};
    	
    	
    		
    	int amount = 0;
    	int red = 0;
    	int green = 0;
    	int blue = 0;
    	int side1 = 0;
    	int side2 = 0;
    	int side3 = 0;
    	
 

    	boolean commandIsValid = false;
    	
    	if (command.startsWith("pencolour ")) {
            String[] rgb = command.split(" "); 
            command = rgb[0];
            if (rgb.length != 4 || rgb.length < 2) {
                displayMessage("missing rgb parameters" + command);
                return;
            }
            
            try { 
                red = Integer.parseInt(rgb[1]);
                green = Integer.parseInt(rgb[2]);
                blue = Integer.parseInt(rgb[3]);
            } catch (NumberFormatException e) {
                displayMessage("Invalid rgb - " + command + " - numeric digit needed");
                return;
            }
            pencolour(red, green, blue);
            commandList.add(command);
            return;
       
    	}else if (command.startsWith("triangle")) {
            String[] sides = command.split(" "); 
            command = sides[0];
            
            if(sides.length == 2){
            	amount = Integer.parseInt(sides[1]);
            	triangle(amount);
            	addCommand(command,amount);
            	
            }
            
            
            else if (sides.length != 4 || sides.length < 2) {
                displayMessage("missing triangle sides" + command);
                return;
            }
            
            
            
            try { 
                side1 = Integer.parseInt(sides[1]);
                side2 = Integer.parseInt(sides[2]);
                side3 = Integer.parseInt(sides[3]);
            } catch (NumberFormatException e) {
                displayMessage("Invalid side parameter - " + command + " - numeric digit needed");
                return;
            }
            triangles(side1, side2, side3);
            commandList.add(command);
            return;
            
        
		    
	    	
    	
    	}else if (command.contains(" ")) {
    	    String[] parts = command.split(" "); 
    	    command = parts[0]; 
    	    if (parts.length < 2) {
    	    	displayMessage("Amount missing for command " + command);
    	        return;
    	    }
    	    
    	    try { 
    	        amount = Integer.parseInt(parts[1]);
    	    } catch (NumberFormatException e) {
    	    	displayMessage("Invalid parameter - " + parts[1] + " - numeric digit needed");
    	        return;
    	    
    	    
    	    
    	    } if (amount < 0 || amount > 200) {
    	    	throw new ArithmeticException("Amount must be greater than 0 and less than 200");
    	    }
    	
    	
    	
    	    
    	       
    	    
    	    
    	    
    	} else if (Arrays.asList("forward", "backward", "turnright", "turnleft", "square").contains(command)) {
    	    displayMessage("Amount missing for command " + command);
    	    return;
    	

    		
    	
    	}else if (Arrays.asList("forward", "backward", "turnright", "turnleft", "square").contains(command)) {
    		displayMessage("Amount missing for command " + command);
    	    return;
    	}
    	for (String validCommand : VALID_COMMANDS) {
    	    if (validCommand.equals(command)) {
    	        commandIsValid = true;
    	        break;
    	    }
    	}
    	if (!commandIsValid) {
    		displayMessage("Invalid Command - " + command);
    	    return;
    	}
    	
    	if (command.equals("about")) {
    		about();
    		commandList.add(command);
    		
    
       	
        } else if(command.equals("penup")) {
            penUp();
            penIsDown = false;
            commandList.add(command);
            
        } else if(command.equals("pendown")) {
            penDown();
            penIsDown = true;
            commandList.add(command);
            
        }

       
        if (penIsDown) {
            penDown();
        } else {
            penUp();
        }

        if(command.equals("turnleft")) {
            turnLeft(amount);
            addCommand(command,amount);
        } else if(command.equals("turnright")) {
            turnRight(amount);
            addCommand(command,amount);
        } else if(command.equals("forward")) {
            forward(amount);
            addCommand(command,amount);
        } else if(command.equals("backward")) {
            forward(-amount);
            addCommand(command,amount);
        } else if(command.equals("red")) {
            setPenColour(Color.red);
            commandList.add(command);
        } else if(command.equals("green")) {
            setPenColour(Color.green);
            commandList.add(command);
        } else if(command.equals("black")) {
            setPenColour(Color.black);
            commandList.add(command);
        } else if(command.equals("white")) {
            setPenColour(Color.white);
            commandList.add(command);
        } else if(command.equals("clear")) {
            clear();
            commandList.add(command);
        } else if(command.equals("reset")) {
            reset();
            setPenColour(getPenColour());
            int width = (int) getStroke();
            setStroke(width);
            penDown();
            commandList.add(command);
        
            
            
        } else if(command.equals("save")) {
        	
        	BufferedImage img = getBufferedImage();
        	
        	File file = new File("userimage.png");
        	
        	try {
        	    ImageIO.write(img, "png", file);
        	
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
             
        } else if(command.equals("load") ) {
        	 File file = new File("userimage.png");
        	 BufferedImage img = getBufferedImage();
		
        	 try {
				img = ImageIO.read(file);
			
        	 } catch (IOException e) {
				displayMessage("No image saved, save first - ");
				e.printStackTrace();
			
        	 }
             setBufferedImage(img);
        	
        
        } else if (command.equals("savecommands")) {
        	writeCommand();
        
        } else if (command.equals("loadcommands")) {
            try (BufferedReader readcommands = new BufferedReader(new FileReader("commands.txt"))) {
                String line;
                while ((line = readcommands.readLine()) != null) {
                    String Line = line.trim();
                    if (!Line.isEmpty()) { 
                        processCommand(Line);
                    }
                }
            } catch (IOException e) {
            	displayMessage("No commands saved, save first - ");
                e.printStackTrace();
            }
           
            return; 
        
            
        }else if(command.equals("square")) 
        {square(amount);
        addCommand(command,amount);
        
        }else if(command.equals("penwidth"))
        {penwidth(amount);
        addCommand(command,amount);
        
       
        }
      
        
	}
    
}
        
    