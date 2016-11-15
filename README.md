import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class VendingMachine extends JFrame{

   private JPanel menuPanel, confirmPanel, cashPanel, displayPanel;
   private JButton cokeButton, pepsiButton, drPepperButton, doritosButton, twixButton;
   private JButton pennyButton, nickelButton, dimeButton, quarterButton, dollarButton, fiveDollarsButton;
   private JButton vendButton, clearButton, restockButton;
//    private JButton resetButton;
   private JPanel pricePanel, priceMenuPanel, cashSummaryPanel;
   private JLabel priceLabel, cokeLabel, pepsiLabel, drPepperLabel, doritosLabel, twixLabel;
   private JLabel subTotalLabel, cashLabel, depositLabel;
   
   private JTextArea summary;
   private JTextField subTotalTextField, depositTextField;
   
   private JScrollPane scrollPane;
   
   private double subTotal, deposit;
   
   public static final double PENNY = 0.01, NICKEL = 0.05, DIME = 0.10, QUARTER = 0.25, DOLLAR = 1.00, FIVEDOLLARS = 5.00;
   public static final int MAX_COKE = 2, MAX_PEPSI = 2, MAX_DR_PEPPER = 3, MAX_DORITOS = 4, MAX_TWIX = 3;
   
   public static final int FRAME_WIDTH = 600, FRAME_HEIGHT = 400;
   
   private String[] items = {"Coca-Cola", "Pepsi", "Dr Pepper", "Doritos", "Twix"};
   private int[] itemCount = {MAX_COKE, MAX_PEPSI, MAX_DR_PEPPER, MAX_DORITOS, MAX_TWIX};
   private int[] maxCount = {MAX_COKE, MAX_PEPSI, MAX_DR_PEPPER, MAX_DORITOS, MAX_TWIX};
   private double[] price = {1.00, 1.00, 1.00, 0.50, 1.00};
   
   public VendingMachine()
   {
      menuPanel = new JPanel();
      confirmPanel = new JPanel();
      cashPanel = new JPanel();
      displayPanel = new JPanel();
      pricePanel = new JPanel();
      priceMenuPanel = new JPanel();
      cashSummaryPanel = new JPanel();
      
      subTotal = 0.00;
      deposit = 0.00;
   
      cokeButton = new JButton("Coca-Cola");
      pepsiButton = new JButton("Pepsi");
      drPepperButton = new JButton("Dr Pepper");
      doritosButton = new JButton("Doritos");
      twixButton = new JButton("Twix");
      pennyButton = new JButton("Add Penny");
      nickelButton = new JButton("Add Nickel");
      dimeButton = new JButton("Add Dime");
      quarterButton = new JButton("Add Quarter");
      dollarButton = new JButton("Add Dollar");
      fiveDollarsButton = new JButton("Add Five Dollars");
      
      vendButton = new JButton("Vend");
      clearButton = new JButton("Clear");
      restockButton = new JButton("Restock");
//       resetButton = new JButton("Reset");
      
      subTotalLabel = new JLabel("Total: ");
      summary = new JTextArea(14, 35);
      subTotalTextField = new JTextField("$0.00", 8);
      cashLabel = new JLabel("Insert Cash");
      depositLabel = new JLabel("Amount deposited: ");
      depositTextField = new JTextField("$0.00", 8);
      subTotalTextField.setEditable(false);
      summary.setEditable(false);
      depositTextField.setEditable(false);
      
      cokeLabel = new JLabel("$1.00");
      pepsiLabel = new JLabel("$1.00");
      drPepperLabel = new JLabel("$1.00");
      doritosLabel = new JLabel("$0.50");
      twixLabel = new JLabel("$1.00");
      
      scrollPane = new JScrollPane(summary);
      
      
      ActionListener itemListener = new itemHandler();
      cokeButton.addActionListener(itemListener);
      pepsiButton.addActionListener(itemListener);
      drPepperButton.addActionListener(itemListener);
      doritosButton.addActionListener(itemListener);
      twixButton.addActionListener(itemListener);
      
      ActionListener cashListener = new moneyHandler();
      pennyButton.addActionListener(cashListener);
      nickelButton.addActionListener(cashListener);      
      dimeButton.addActionListener(cashListener);
      quarterButton.addActionListener(cashListener);
      dollarButton.addActionListener(cashListener);
      fiveDollarsButton.addActionListener(cashListener);
      
//       ActionListener resetListener = new resetHandler();
//       resetButton.addActionListener(resetListener);

      ActionListener clearListener = new clearHandler();
      clearButton.addActionListener(clearListener);
      
      ActionListener restockListener = new restockHandler();
      restockButton.addActionListener(restockListener);
      
      ActionListener vendListener = new vendHandler();
      vendButton.addActionListener(vendListener);

      setTitle("Vending Machine");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
      setVisible(true);
      
      menuPanel.setLayout(new GridLayout(1,5));
      menuPanel.setBackground(new Color(80,80,80));
      menuPanel.add(cokeButton);
      menuPanel.add(pepsiButton);
      menuPanel.add(drPepperButton);
      menuPanel.add(doritosButton);
      menuPanel.add(twixButton);
      
      pricePanel.setLayout(new GridLayout(1,5));
      pricePanel.setBackground(new Color(50,50,50));
      pricePanel.add(cokeLabel).setForeground(Color.yellow);
      pricePanel.add(pepsiLabel).setForeground(Color.yellow);
      pricePanel.add(drPepperLabel).setForeground(Color.yellow);
      pricePanel.add(doritosLabel).setForeground(Color.yellow);
      pricePanel.add(twixLabel).setForeground(Color.yellow);
      pricePanel.setBorder(BorderFactory.createLineBorder(Color.white));
      cokeLabel.setHorizontalAlignment(cokeLabel.CENTER);
      pepsiLabel.setHorizontalAlignment(pepsiLabel.CENTER);
      drPepperLabel.setHorizontalAlignment(drPepperLabel.CENTER);
      doritosLabel.setHorizontalAlignment(doritosLabel.CENTER);
      twixLabel.setHorizontalAlignment(twixLabel.CENTER);
      
      cashSummaryPanel.setBackground(new Color(80,100,100));
      cashSummaryPanel.add(subTotalLabel).setForeground(Color.white);
      cashSummaryPanel.add(subTotalTextField).setForeground(Color.yellow);
      subTotalTextField.setBackground(Color.black);
      cashSummaryPanel.add(depositLabel).setForeground(Color.white);
      cashSummaryPanel.add(depositTextField).setForeground(Color.yellow);;
      depositTextField.setBackground(Color.black);
      
      displayPanel.setBackground(Color.gray);
      displayPanel.add(summary);
      displayPanel.add(cashSummaryPanel);
      
      confirmPanel.setBackground(new Color(220,220,220));
      confirmPanel.add(vendButton);
      confirmPanel.add(clearButton);
//       confirmPanel.add(resetButton);
      confirmPanel.add(restockButton);
      
      cashPanel.setLayout(new GridLayout(7, 1));
      cashLabel.setFont(new Font("Helvetica",Font.PLAIN, 20));
      cashLabel.setForeground(Color.white);
      cashPanel.setBackground(new Color(120,50,50));
      cashPanel.add(cashLabel);
      cashPanel.add(pennyButton);
      cashPanel.add(nickelButton);      
      cashPanel.add(dimeButton);      
      cashPanel.add(quarterButton);
      cashPanel.add(dollarButton);
      cashPanel.add(fiveDollarsButton);
      
      priceMenuPanel.setLayout(new GridLayout(2,0));
      priceMenuPanel.add(menuPanel, BorderLayout.NORTH);
      priceMenuPanel.add(pricePanel, BorderLayout.SOUTH);
      
      add(priceMenuPanel, BorderLayout.NORTH);;
      add(confirmPanel, BorderLayout.SOUTH);
      add(cashPanel, BorderLayout.EAST);
      add(displayPanel, BorderLayout.CENTER);
   }
   
   public VendingMachine(VendingMachine vendor)
   {
      vendor = new VendingMachine();
   }
   
   
   class itemHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JButton o = (JButton) e.getSource();
         String buttonName = o.getText();
         getItem(buttonName);
      }
   }
   
   public void getItem(String name)
   {
      String s = "";
      if (subTotal > 0){summary.append("an Item is already in queue\n");}      
      else {
         for (int i = 0; i < items.length; i++)
         {
            if (items[i].equals(name))
            {
                  s = s.format("%-10s\t\t$%-5.2f\n", items[i], price[i]);
                  summary.setText(s);
                  subTotal += price[i];
                  String str = "";
                  str = str.format("%.2f", subTotal);
                  subTotalTextField.setText("$" + str);
                  if (itemCount[i] == 0){subTotal -= price[i];}
            }
         }
      }
   }
         
   class moneyHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JButton button = (JButton) e.getSource();
         String buttonName = button.getText().toUpperCase();
         deposit += updateDeposit(buttonName);
         String s = "";
         s = s.format("%.2f", deposit);
         depositTextField.setText("$" + s);
      }
   }
   
   private double updateDeposit(String name)
   {
      if (name.substring(4).equals("PENNY")){return VendingMachine.PENNY;}
      else if (name.substring(4).equals("NICKEL")){return VendingMachine.NICKEL;}
      else if (name.substring(4).equals("DIME")){return VendingMachine.DIME;}
      else if (name.substring(4).equals("QUARTER")){return VendingMachine.QUARTER;}
      else if (name.substring(4).equals("DOLLAR")){return VendingMachine.DOLLAR;}
      else if (name.substring(4).equals("FIVE DOLLARS")){return VendingMachine.FIVEDOLLARS;}
      else {return 0;}
   }
   
//    class resetHandler implements ActionListener
//    {
//       public void actionPerformed(ActionEvent e)
//       {
//       return new VendingMachine();
//       }
//    }
      
   class clearHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         clearMenu();   
      }
   }
   
   public void clearMenu(){
      subTotalTextField.setText("$0.00");
      depositTextField.setText("$0.00");
      summary.setText("");
      deposit = 0;
      subTotal = 0;
   }
   
   class restockHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         for(int i = 0; i < items.length; i++){
            if(itemCount[i] < maxCount[i]){
               itemCount[i] = maxCount[i];
               summary.append("Re-Stocked " + items[i] + "\n");
            }
            else{
               summary.append(items[i] + " is already stocked.\n");
            }
         }
      }
   }
   
   class vendHandler implements ActionListener{
      public void actionPerformed(ActionEvent e){
         
         double change = deposit - subTotal;
         String s = "";
         String str = summary.getText();
         int pos = 0;
         pos = str.indexOf("\t");        
         if (pos == -1) {return;}
         str = str.substring(0, pos);
         str = str.trim();

         for(int i = 0; i < items.length; i++){
            if(itemCount[i] == 0 && str.equals(items[i])){
                  s = s.format("Sorry! We are out of " + items[i] + ".\nCollect your change: $" + "%.2f\n\n", change);
                  summary.append(s); 
                  subTotalTextField.setText("$0.00");
                  depositTextField.setText("$0.00");
                  deposit = 0;
                  subTotal = 0;
               }
          }
          if (deposit == 0 && subTotal == 0){}
          else if(subTotal <= deposit && change <= 4){
               
               decreaseItem(str);
               s = s.format("Thank you for your purchase!\nDon't forget to collect your change\nChange: $%.2f\n\n", change);
               summary.append(s);
               subTotalTextField.setText("$0.00");
               depositTextField.setText("$0.00");
               deposit = 0;
               subTotal = 0;
          }
          else if(subTotal > deposit){
               
               summary.setText("");
               summary.append("Insufficient funds!\nDon't forget to collect your change\nChange: $" + deposit + "\n\n");
               subTotalTextField.setText("$0.00");
               depositTextField.setText("$0.00");
               deposit = 0;
               subTotal = 0;
          }
          else{
               decreaseItem(str);
               summary.append("Thank you for your purchase\nMachine does not have enough change" + "\n\n");
               subTotalTextField.setText("$0.00");
               depositTextField.setText("$0.00");
               deposit = 0;
               subTotal = 0;
          }
      }
   }

   public void decreaseItem(String name)
   {
      for (int i = 0; i < items.length; i++){
         if(name.matches(items[i])){
            itemCount[i]--;
            if (itemCount[i] < 0){itemCount[i] = 0;}
         }
      }
   }

   public static void main(String[] args)
   {
	  JFrame VendingMachine = new VendingMachine();
      VendingMachine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      VendingMachine.setVisible(true);
   }
}
