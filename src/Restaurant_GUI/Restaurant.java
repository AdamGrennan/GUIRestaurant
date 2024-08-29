package Restaurant_GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class Restaurant extends JFrame implements ActionListener {
    //GUI Components
    private JPanel foodPanel, mainPanel, burgerPanel, sidePanel, drinkPanel, saucePanel, titlePanel, boxPanel;
    private ButtonGroup burgerGroup, sideGroup, drinkGroup, sauceGroup;
    private JLabel title;
    private JButton confirmOrder, clearBtn, closeBtn, clearGUI;
    private JTextArea displayOrders;
    private  Waiter waiter;

    //Custom Colors
    private Color sapphire = Color.decode("#003559"); 
    private Color oxford = Color.decode("#004080"); 
    private Color blue = Color.decode("#1E96FC"); 


    // Queue to hold orders
    private Queue<Order> orderQueue = new LinkedList<>();
    private Object lock = new Object();

    //JFrame setup
    public Restaurant() {
        super("Burger Restaurant");
        setupGUI();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setSize(1000, 500);
    }

    private void setupGUI() {
        //Layout main components of GUI
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        //BoxPanel
        boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBackground(sapphire);
        add(boxPanel,BorderLayout.WEST);      
        setupButtons();

        //FoodPanel
        foodPanel = new JPanel(new GridLayout(2, 2));
        foodPanel.setBackground(blue);
        mainPanel.add(foodPanel, BorderLayout.WEST);
    
        //Orders TextArea
        displayOrders = new JTextArea();
        displayOrders.setEditable(false);
        displayOrders.setBackground(Color.white);
        mainPanel.add(new JScrollPane(displayOrders), BorderLayout.CENTER);
 
        //Setup for food panels with icons
        burgerPanel = createPanelWithIcon("images/hamburger.png");
        sidePanel = createPanelWithIcon("images/french-fries.png");
        drinkPanel = createPanelWithIcon("images/soda.png");
        saucePanel = createPanelWithIcon("images/fish.png");

        //Add to foodPanel
        foodPanel.add(burgerPanel);
        foodPanel.add(sidePanel);
        foodPanel.add(drinkPanel);
        foodPanel.add(saucePanel);
         
        //TitlePanel setup
        titlePanel = new JPanel();
        title = new JLabel("Burger Restaurant");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        titlePanel.setBackground(blue);


        // RadioButton Groups
        burgerGroup = new ButtonGroup();
        sideGroup = new ButtonGroup();
        drinkGroup = new ButtonGroup();
        sauceGroup = new ButtonGroup();

        addRadioButtons();
    }

    private JPanel createPanelWithIcon(String iconPath) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
    
        
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        
        if (icon != null) {
          
            Image resizedImage = resizeImage(icon.getImage(), 50, 50);
            JLabel iconLabel = new JLabel(new ImageIcon(resizedImage));
            panel.add(iconLabel);
        }
   
        panel.setBackground(blue);
        return panel;
    }
    
    private Image resizeImage(Image originalImage, int width, int height) {
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImage;
    }
    
    private void setupButtons() {  
        confirmOrder = createButton("Confirm Order");
        boxPanel.add(Box.createRigidArea(new Dimension(0, 30))); 
        
        clearBtn = createButton("Clear Order");
        boxPanel.add(Box.createRigidArea(new Dimension(0, 30))); 
        
        clearGUI = createButton("Clear Text Area");
        boxPanel.add(Box.createRigidArea(new Dimension(0, 30))); 
        
        closeBtn = createButton("Close GUI");
    }
    

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(sapphire);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16)); 
        button.setPreferredSize(new Dimension(150, 50));   
        button.setMargin(new Insets(10, 20, 10, 20));

        button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(oxford);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(sapphire); 
        }
    });
        button.addActionListener(this);
        boxPanel.add(button);
        return button;
    }

    private void addRadioButtons() {
        addBurgerOptions();
        addSideOptions();
        addDrinkOptions();
        addSauceOptions();
    }

    private void addBurgerOptions() {
        String[] burgers = {"Cheese Burger", "Chicken Burger", "Bacon Burger", "Plain Burger"};
        addRadioButtonsToPanel(burgerPanel, burgerGroup, burgers);
    }

    private void addSideOptions() {
        String[] sides = {"Fries", "Salad", "Mac & Cheese", "Onion Rings"};
        addRadioButtonsToPanel(sidePanel, sideGroup, sides);
    }

    private void addDrinkOptions() {
        String[] drinks = {"Coke", "Lemonade", "Water", "MilkShake"};
        addRadioButtonsToPanel(drinkPanel, drinkGroup, drinks);
    }

    private void addSauceOptions() {
        String[] sauces = {"Garlic Sauce", "Ketchup", "BBQ Sauce", "Taco Sauce"};
        addRadioButtonsToPanel(saucePanel, sauceGroup, sauces);
    }

    private void addRadioButtonsToPanel(JPanel panel, ButtonGroup group, String[] options) {
        for (String option : options) {
            JRadioButton button = new JRadioButton(option);
            button.setBackground(blue);
            button.addActionListener(this);
            group.add(button);
            panel.add(button);
        }
    }

    //Box panel button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if ("Confirm Order".equals(actionCommand)) {
            processOrder();
            clearSelections();
        } else if ("Clear Order".equals(actionCommand)) {
            clearSelections();
        } else if ("Close GUI".equals(actionCommand)) {
            dispose();
        } else if ("Clear Text Area".equals(actionCommand)) {
            displayOrders.setText("");
        }
    }

    private void processOrder() {
    String burger = getSelectedText(burgerGroup);
    String drink = getSelectedText(drinkGroup);
    String side = getSelectedText(sideGroup);
    String sauce = getSelectedText(sauceGroup);

    synchronized (lock) {
        // Add items to the queue if they are selected
        if (burger != null) orderQueue.add(new Order(burger));
        if (sauce != null) orderQueue.add(new Order(sauce));
        if (drink != null) orderQueue.add(new Order(drink));
        if (side != null) orderQueue.add(new Order(side));

        if (orderQueue.isEmpty()) {
            appendToDisplayOrders("\nCustomer has not selected anything to order\n");
        } else {
            appendToDisplayOrders("\nNew order placed");
           

            // Check if a Waiter thread is already running
            if  (waiter == null || !waiter.isAlive()) {
                waiter = new Waiter(orderQueue, this, lock);
                waiter.start();
            }
        }
    }
}

//Get button text
private String getSelectedText(ButtonGroup group) {
    for (AbstractButton button : Collections.list(group.getElements())) {
        if (button.isSelected()) {
            return button.getText();
        }
    }
    return null;
}

//Clear selections of food
    private void clearSelections() {
        burgerGroup.clearSelection();
        sideGroup.clearSelection();
        drinkGroup.clearSelection();
        sauceGroup.clearSelection();
    }

    //Display orders on textArea
    public void appendToDisplayOrders(String text) {
        SwingUtilities.invokeLater(() -> displayOrders.append(text));
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
