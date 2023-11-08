package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Restaurant extends JFrame implements ActionListener{
  JPanel mainPanel,burgerPanel,sidePanel,drinkPanel,saucePanel,titlePanel,buttonPanel;
  
  JRadioButton cheeseBurger,chickenBurger,baconBurger,plainBurger,fries,salad,macCheese,onionRings,
  coke,lemonade,water,milkShake,garlicSauce,ketchup,bbq,tacoSauce;
  
  ButtonGroup burgerGroup,sideGroup,drinkGroup,sauceGroup;
  
  JLabel title;
  
  JButton confirmOrder,clearBtn,closeBtn,clearGUI;
  
  JTextArea displayOrders;
  
  //Static variable to give waiter & chef class access to GUI TextArea
  static Restaurant r;
	
	//Create list
	ArrayList<Order>orderList = new ArrayList<Order>();
	
	
public Restaurant(){
		super("Burger Restaurant");
		this.setLayout(new BorderLayout());
	//Display Orders TextArea
		displayOrders = new JTextArea();
		this.add(displayOrders,BorderLayout.CENTER);
		displayOrders.setEditable(false);
	 	this.add(new JScrollPane(displayOrders), "Center");
		displayOrders.setBackground(Color.white);
		
    //Main Panel 
	mainPanel = new JPanel(new GridLayout(2,2));   
	this.add(mainPanel,BorderLayout.WEST);
	mainPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
	mainPanel.setBackground(Color.yellow);
		
	//Burger Panel
	burgerPanel = new JPanel(new GridLayout(0,1));
	burgerPanel.setBorder(new  TitledBorder( "Choose Burger"));
	mainPanel.add(burgerPanel);
    burgerPanel.setBackground(Color.yellow);

	//Side Panel
	sidePanel = new JPanel(new GridLayout(0,1));
	sidePanel.setBorder(new  TitledBorder( "Choose Side"));
	mainPanel.add(sidePanel);
	sidePanel.setBackground(Color.yellow);
	
	//Drink Panel
	drinkPanel = new JPanel(new GridLayout(0,1));
	drinkPanel.setBorder(new  TitledBorder( "Choose Drink"));
	mainPanel.add(drinkPanel);
	drinkPanel.setBackground(Color.yellow);
	
	//Sauce Panel
	saucePanel = new JPanel(new GridLayout(0,1));
	saucePanel.setBorder(new  TitledBorder( "Choose Sauce"));
	mainPanel.add(saucePanel);
	saucePanel.setBackground(Color.yellow);
	
	//Title Panel
	titlePanel = new JPanel();
	title = new JLabel("Burger Restaurant");
	titlePanel.add(title);
	this.add(titlePanel,BorderLayout.NORTH);
	titlePanel.setBackground(Color.orange);
	
	//Button Panel
	buttonPanel = new JPanel();
	this.add(buttonPanel,BorderLayout.SOUTH);
	buttonPanel.setBackground(Color.orange);
		
	//Groups
	sideGroup = new ButtonGroup();
	burgerGroup = new ButtonGroup();
	drinkGroup = new ButtonGroup();
	sauceGroup = new ButtonGroup();
	
	//Confirm Button
	confirmOrder = new JButton("Confirm Order");
	confirmOrder.setBackground(Color.yellow);
	confirmOrder.addActionListener(this);
	buttonPanel.add(confirmOrder);
	
	//Clear Button
	clearBtn = new JButton("Clear Order");
	clearBtn.setBackground(Color.yellow);
	clearBtn.addActionListener(this);
	buttonPanel.add(clearBtn);
	
	//Clear GUI Button
	clearGUI = new JButton("Clear Text Area");
	clearGUI.setBackground(Color.yellow);
	clearGUI.addActionListener(this);
	buttonPanel.add(clearGUI);
	
	//Close Button
	closeBtn = new JButton("Close GUI");
	closeBtn.setBackground(Color.yellow);
	closeBtn.addActionListener(this);
	buttonPanel.add(closeBtn);
	
	//Burger selection check box
	cheeseBurger = new JRadioButton("Cheese Burger");
    cheeseBurger.addActionListener(this);
    burgerGroup.add(cheeseBurger);
	cheeseBurger.setBackground(Color.yellow);
     
	chickenBurger = new JRadioButton("Chicken Burger");
	chickenBurger.addActionListener(this);
    burgerGroup.add(chickenBurger);
    chickenBurger.setBackground(Color.yellow);
     
	baconBurger = new JRadioButton("Bacon Burger");
	baconBurger.addActionListener(this);
    burgerGroup.add(baconBurger);
    baconBurger.setBackground(Color.yellow);
    
	plainBurger = new JRadioButton("Plain Burger");
	plainBurger.addActionListener(this);
    burgerGroup.add(plainBurger);
    plainBurger.setBackground(Color.yellow);
    
    burgerPanel.add(cheeseBurger);
    burgerPanel.add(chickenBurger);
    burgerPanel.add(plainBurger);
    burgerPanel.add(baconBurger);
    
  //Sides selection check box
  	fries = new JRadioButton("Fries");
    fries.addActionListener(this);
    sideGroup.add(fries);
    fries.setBackground(Color.yellow);
       
	salad = new JRadioButton("Salad");
	salad.addActionListener(this);
    sideGroup.add(salad);
    salad.setBackground(Color.yellow);
       
	macCheese = new JRadioButton("Mac & Cheese");
	macCheese.addActionListener(this);
    sideGroup.add(macCheese);
    macCheese.setBackground(Color.yellow);
      
	onionRings = new JRadioButton("Onion Rings");
	onionRings.addActionListener(this);
    sideGroup.add(onionRings);
    onionRings.setBackground(Color.yellow);
      
     sidePanel.add(fries);
     sidePanel.add(salad);
     sidePanel.add(macCheese);
     sidePanel.add(onionRings);
    
     //Drinks selection check box
   	coke = new JRadioButton("Coke");
   	coke.addActionListener(this);
    drinkGroup.add(coke);
    coke.setBackground(Color.yellow);
        
    lemonade = new JRadioButton("Lemonade");
    lemonade.addActionListener(this);
    drinkGroup.add(lemonade);
    lemonade.setBackground(Color.yellow);
        
    water = new JRadioButton("Water");
    water.addActionListener(this);
    drinkGroup.add(water);
    water.setBackground(Color.yellow);
       
    milkShake = new JRadioButton("MilkShake");
    milkShake.addActionListener(this);
    drinkGroup.add(milkShake);
    milkShake.setBackground(Color.yellow);
       
      drinkPanel.add(coke);
      drinkPanel.add(lemonade);
      drinkPanel.add(water);
      drinkPanel.add(milkShake);
      
      //Sauce selection check box
     	garlicSauce = new JRadioButton("Garlic Sauce");
     	garlicSauce.addActionListener(this);
        sauceGroup.add(garlicSauce);
        garlicSauce.setBackground(Color.yellow);
          
     	ketchup = new JRadioButton("Ketchup");
     	ketchup.addActionListener(this);
        sauceGroup.add(ketchup);
        ketchup.setBackground(Color.yellow);
          
     	bbq = new JRadioButton("BBQ Sauce");
     	bbq.addActionListener(this);
        sauceGroup.add(bbq);
        bbq.setBackground(Color.yellow);
         
        tacoSauce = new JRadioButton("Taco Sauce");
        tacoSauce.addActionListener(this);
        sauceGroup.add(tacoSauce);
        tacoSauce.setBackground(Color.yellow);
         
        saucePanel.add(garlicSauce);
        saucePanel.add(ketchup);
        saucePanel.add(bbq);
        saucePanel.add(tacoSauce);
     
    
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.pack();
    this.setSize(1000, 500);
	}
	
public void actionPerformed(ActionEvent e) {
	 String burger = "Invalid";
	 String drink = "Invalid";
	 String side = "Invalid";
	 String sauce = "Invalid";
	 Order sendBurger,sendSide,sendDrink,sendSauce;
	
	if(e.getActionCommand().equals("Confirm Order")) {
		//Burger selection
		 if(baconBurger.isSelected()) {
			 burger = baconBurger.getText();
		
			 
		 }else if(chickenBurger.isSelected()){
			 burger = chickenBurger.getText();
			 
		 }else if(plainBurger.isSelected()){
			 burger = plainBurger.getText();
		 }
		 else if(cheeseBurger.isSelected()){
			 burger = cheeseBurger.getText();
			
		 }
		 //Drink selection
		 if(coke.isSelected()) {
			 drink = coke.getText();
		
			 
		 }else if(water.isSelected()){
			 drink = water.getText();
			 
		 }else if(lemonade.isSelected()){
			 drink = lemonade.getText();
			 
		 }else if(milkShake.isSelected()){
			 drink = milkShake.getText();
			 
		 }
		 //Side Selection
		 if(fries.isSelected()) {
			 side = fries.getText();
				 
		 }else if(salad.isSelected()){
			 side = salad.getText();
			 
		 }else if(onionRings.isSelected()){
			 side = onionRings.getText();
			 
		 }else if(macCheese.isSelected()){
			 side = macCheese.getText();
			 
		 }
		 //Sauce Selection
		 if(ketchup.isSelected()) {
			 sauce = ketchup.getText();
				 
		 }else if(bbq.isSelected()){
			 sauce = bbq.getText();
			 
		 }	 
	      else if(tacoSauce.isSelected()){
		 sauce = tacoSauce.getText();
		 
	      } 	 
	      else if(garlicSauce.isSelected()){
		  sauce = garlicSauce.getText(); 
	      }
		 
		 if(!burger.equals("Invalid")) {
			 sendBurger = new Order(burger);
			 orderList.add(sendBurger);
		 }
		 if(!sauce.equals("Invalid")) {
				sendSauce = new Order(sauce);
				orderList.add(sendSauce);
		 }
		 if(!drink.equals("Invalid")) {
				sendDrink = new Order(drink);
				orderList.add(sendDrink);
		 }
		 if(!side.equals("Invalid")) {
				sendSide = new Order(side);
				orderList.add(sendSide);
		 }
		 
		 if(burger.equals("Invalid") &&sauce.equals("Invalid")&&drink.equals("Invalid")&&side.equals("Invalid")) {
			 displayOrders.append("Customer has not selected anything to order\n");
		 }else {
			 synchronized(this) {
				 //Start Waiter Thread and pass orders in list to them
				Waiter w =  new Waiter(orderList,r);
				w.start();		
			
			 }
		 }

	}

	if(e.getActionCommand().equals("Clear Order")) {
		//Clear all radio button selections
		burgerGroup.clearSelection();
		sideGroup.clearSelection();
		drinkGroup.clearSelection();
		sauceGroup.clearSelection();
	}
	
	if(e.getActionCommand().equals("Close GUI")) {
		//Close GUI
		Restaurant.this.dispose();	
		
	}
	
	if(e.getActionCommand().equals("Clear Text Area")) {
		//Clear GUI Text Area
		displayOrders.setText("");	
		
	}
}
	
    public static void main(String[] args) {
        //Create my single-frame GUI application.
        r = new Restaurant();       
    }

}
