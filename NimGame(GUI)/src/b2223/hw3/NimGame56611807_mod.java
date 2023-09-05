/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b2223.hw3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

/**
 *
 * @author admin
 */
public class NimGame56611807_mod extends JFrame {
    public static final Color LIGHT_BLUE = new Color(51,153,255);
    public static int gamemode=0; //1 is hvh,2 is pvh
    public int[] pilesArray = new int[5];
    private boolean isFirstPlayerTurn;
    public int clickCount=0;
    public int clickedRow=-1;
    Nims[][] piles=new Nims[5][7];
    JLabel[] Binary=new JLabel[5];
    JTextArea Msg=new JTextArea("Welcome to Nim Game!Choose Human vs Human or Human vs Player\n");
    JLabel displayNimSum = new JLabel("Nim Sum  "+"   ");
    //public class NimFrame extends JFrame {

        NimGame56611807_mod() {
            setLayout(new BorderLayout(5, 10));         //the GUI
            Msg.setAlignmentY(CENTER_ALIGNMENT);
             
            //JButton HVH = new JButton("Human vs Human");
            //HVH.addActionListener(e->{gamemode=1;});
            
            GameModeButton HVH=new GameModeButton("Human vs Human");
            HVH.gm=1;
            HVH.setPreferredSize(new Dimension(150,30));
            GameModeButton PVH = new GameModeButton("PC vs Human");
            PVH.gm=2;
            PVH.setPreferredSize(new Dimension(150,30));
            JPanel Match_Choice = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
            JPanel Bottom = new JPanel(new BorderLayout(20,5));
            
            JPanel Text=new JPanel();
            Text.setPreferredSize(new Dimension(500,100));
            
            JPanel NimIcons = new JPanel(new GridLayout(5,7,10,20));
            JPanel NimBin = new JPanel(new GridLayout(5,1,10,20));
            //NimIcons.setBorder(new MatteBorder(0,0,2,0,Color.ORANGE));
            Bottom.setBorder(new MatteBorder(2,0,0,0,Color.ORANGE));
            
            for(int i=0;i<5;i++){
                Binary[i]=new JLabel(toBinaryString(piles[i].length,3));
                NimBin.add(Binary[i],BorderLayout.EAST);
                Binary[i].setVisible(false);
            }
            //JLabel[] r2=new JLabel[7];JLabel[] r3=new JLabel[7];JLabel[] r4=new JLabel[7];JLabel[] r5=new JLabel[7];
            for(int r=0;r<5;r++){
            for(int i=0;i<piles[r].length;i++){
                piles[r][i]=new Nims();
                //piles[r][i].setBorder(new LineBorder(Color.BLUE, 1, true));
                piles[r][i].setVisible(false);
                piles[r][i].setEnabled(false);
                piles[r][i].setBackground(LIGHT_BLUE);
                piles[r][i].row=r;
                NimIcons.add(piles[r][i]);
            }}
            Next next=new Next("Next");
            
            //NimIcons.setBorder(new LineBordser(Color.ORANGE, 1, true));
            //HVH.setPreferredSize(new Dimension(100, 50));
            HVH.setBackground(Color.ORANGE);
            
            //Msg.setEditable(false);
            Msg.setPreferredSize(new Dimension(475,100));
            Msg.setBackground(Color.WHITE);
            Msg.setBorder(new MatteBorder(0,0,10,0,Match_Choice.getBackground()));
            //.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
            
            PVH.setBackground(Color.ORANGE);
            
            Text.add(Msg);
            Bottom.add(next,BorderLayout.WEST);
            Bottom.add(Text,BorderLayout.SOUTH);
            Bottom.add(displayNimSum,BorderLayout.EAST);
            
            
            Match_Choice.add(HVH);
            //PVH.setPreferredSize(new Dimension(100, 50));
            Match_Choice.add(PVH);
            add(Match_Choice,BorderLayout.NORTH);
            add(NimIcons,BorderLayout.CENTER);
            add(Bottom,BorderLayout.SOUTH);
            add(NimBin,BorderLayout.EAST);
            
 
        }

    public void repile(){           //repaint the piles and the binary number on gui
             for (int a = 0; a < pilesArray.length; a++){
                    for(int b=0;b<pilesArray[a];b++){
                        piles[a][b].setVisible(true);
                        piles[a][b].setEnabled(true);
                        piles[a][b].reset();
                    }
                }
                for (int a = 0; a < pilesArray.length; a++){
                    for(int b=pilesArray[a];b<7;b++){
                        piles[a][b].setVisible(false);
                        piles[a][b].setEnabled(false);
                        piles[a][b].reset();
                    }
                }
                for(int i=0;i<5;i++){
                Binary[i].setText(toBinaryString(pilesArray[i],3));
                //NimBin.add(Binary[i],BorderLayout.EAST);
                Binary[i].setVisible(true);}
                
         }
    //main function
            public static void main(String[] args) {
            //NimGame56611807_mod a = new NimGame56611807_mod();
            NimGame56611807_mod frame = new NimGame56611807_mod();
            frame.setTitle("NimGame56611807");
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setVisible(true);
           
        }

     class GameModeButton extends JButton {

        private String message = "Human vs Human";
        private int x = 30;
        private int y = 20;
        public int gm=0;
        
        public GameModeButton(String s) {                   //generate the random piles (if PCvsHuman, move for the Pc
            super(s); //***********
            this.addActionListener((ActionEvent e)->{ 
                  gamemode=gm;
                  clickCount=0;
                  clickedRow=-1;
                
                    Random random = new Random();
                    isFirstPlayerTurn = true;
                for (int a = 0; a < pilesArray.length; a++) {
                    pilesArray[a] = random.nextInt(7) + 1;                  // generate random number between 1 and 7
                    }
                repile();
                
                displayNimSum.setText("Nim Sum  "+toBinaryString(nimsum(pilesArray),3));
                Msg.setText("Game start! Player 1 turn(Click on the buttons to remove stones, click next to end turn)");
                
                if(gamemode==2){
                        Map.Entry<Integer, Integer> move = PCNext(pilesArray.clone());
                        isFirstPlayerTurn=false;
                        pilesArray[move.getKey()] -= move.getValue();
                        Msg.setText("PC removed "+ move.getValue() + " stones from pile " + (move.getKey() + 1)+". \nHuman Turn");
                        Msg.append("\n(Click on the buttons to remove stones, click next to end turn)");
                        //Msg.append("\n"+pilesArray[0]+pilesArray[1]+pilesArray[2]+pilesArray[3]+pilesArray[4]);
                        //System.out.println(player1.getPlayerName() + " removed " + move.getValue() + " stones from pile " + (move.getKey() + 1));
                        repile();
                        displayNimSum.setText("Nim Sum  "+toBinaryString(nimsum(pilesArray),3));
                        //Msg.setText("PC removed "+ move.getValue() + " stones from pile " + (move.getKey() + 1)+". \nHuman Turn");
                        //Msg.append("\n"+pilesArray[0]+pilesArray[1]+pilesArray[2]+pilesArray[3]+pilesArray[4]);

                        
                }
                    repaint();
                  //start();
                });
            };
        

    }
     public Map.Entry<Integer, Integer> PCNext(int[] piles_copy){                    //edited nextmove method from last assignment
                isFirstPlayerTurn=!isFirstPlayerTurn;
                //pilesArray[clickedRow]-=clickCount;
                displayNimSum.setText("Nim Sum  "+toBinaryString(nimsum(piles_copy),3));
                int pile=1, stones=1;
                int sum;
                clickedRow=-1;
                clickCount=0;

            sum=nimsumWithMove(1,-1,piles_copy);
            if (sum==0){
                pile=playRandom(piles_copy);
                return Map.entry(pile, 1);
            }
            int count=0;
            for(int i=0;i<piles_copy.length;i++){
                if(piles_copy[i]!=0){
                    count=count+piles_copy[i];
                }
            }
            if(count==1){
                pile=playRandom(piles_copy);
                return Map.entry(pile, 1);
            }
            //System.out.println("sum="+sum);
            for(int i=0;i<piles_copy.length;i++){
                if(piles_copy[i]!=0){
                for(int j=0;j<piles_copy[i];j++){
                    sum=nimsumWithMove(i, j, piles_copy);
                if(sum==0){
                    pile=i;
                    stones=j+1;
                    piles_copy[pile]-=stones;
                    if(check_any_piles_with_size_two(piles_copy)){
                    return Map.entry(pile, stones);}
                    else{
                        if(piles_copy[pile]==1){
                            stones++;
                            return Map.entry(pile, stones);
                            
                        }
                        if(piles_copy[pile]==0){
                            stones--;
                        return Map.entry(pile, stones);}
                    }
                }}}
            }
            
             return Map.entry(pile, stones);
         }
        
        


   
     
     class Nims extends JButton {   //the piles buttons (in blue color)
        public int row;
        //private String message = "Human vs Human";
        private int x = 30;
        private int y = 20;
        private boolean paint = true;
        private boolean clicked=false;

        public Nims() {
            
            this.addActionListener( (ActionEvent e)->{

                /** Handle mouse dragged event */
                //public void mouseMoved(MouseEvent e) {
                if(clicked==false){
                    if(clickedRow==-1 || clickedRow==row){
                    this.setBackground(Color.GRAY);
                    paint=false;
                    clickedRow=row;
                    clickCount++;
                    clicked=true;
                    repaint();
                    //setEnabled(paint);
                    }}
                else if(clicked==true){
                        this.setBackground(LIGHT_BLUE);
                        repaint();
                        clicked=false;
                        clickCount--;
                        if(clickCount==0)clickedRow=-1;
                    }
                });
            
        }

        public void reset(){
         this.setBackground(LIGHT_BLUE);
         paint = true;
         clicked=false;
        clickedRow=-1;
        }
        /** Paint the component */
        @Override
        protected void paintComponent(Graphics g) {
//            if(paint==true){
//            super.paintComponent(g);}
            super.paintComponent(g);
            //g.drawString(message, x, y);
        }
    }
     class Next extends JButton {                       //the next move button
        private String message = "Next";
        private int x = 4;
        private int y = 10;
       

        public Next(String s) { 
            super(s);
            this.addActionListener( (ActionEvent e)->{
                 

                
                if(gamemode==1){                //Human vs Human
                
                HumanNext();
                
                
                }
                else if(gamemode==2){               //PC vs Human
                    if (clickCount == 0) {
                        Msg.setText("You need to choose stones to remove!");
                        if (IntStream.of(pilesArray).sum() <= 0) {
                            Msg.setText("Click on GameMode Button to restart game");
                        }
                        return;
                    }
                    isFirstPlayerTurn = !isFirstPlayerTurn;
                    pilesArray[clickedRow] -= clickCount;
                    displayNimSum.setText("Nim Sum  " + toBinaryString(nimsum(pilesArray), 3));
                    clickedRow = -1;
                    clickCount = 0;
                
                       
                    if (IntStream.of(pilesArray).sum() <= 0) {
                        repile();
                        Msg.setText(isFirstPlayerTurn ? "PC won!" : "Human won!");
                        Msg.append("\nClick on GameMode Button to restart game");
                        return;
                    }
                    Map.Entry<Integer, Integer> move = PCNext(pilesArray.clone());
                    pilesArray[move.getKey()] -= move.getValue();
                    //System.out.println(player1.getPlayerName() + " removed " + move.getValue() + " stones from pile " + (move.getKey() + 1));
                    repile();
                    displayNimSum.setText("Nim Sum  " + toBinaryString(nimsum(pilesArray), 3));
                    if (IntStream.of(pilesArray).sum() <= 0) {
                        Msg.setText(isFirstPlayerTurn ? "PC won!" : "Human won!");
                        Msg.append("\nClick on GameMode Button to restart game");
                    } else {
                        Msg.setText("PC removed " + move.getValue() + " stones from pile " + (move.getKey() + 1) + ". \nHuman Turn");
                    }
                    
                }
                });
            
           
            
            
        }
         public void HumanNext(){           //human move
                
                if(clickCount==0){
                     Msg.setText("You need to choose stones to remove!");
                     if(IntStream.of(pilesArray).sum()<=0)Msg.setText("Click on GameMode Button to restart game");
                     return;
                 }
                isFirstPlayerTurn=!isFirstPlayerTurn;
                pilesArray[clickedRow]-=clickCount;
                displayNimSum.setText("Nim Sum  "+toBinaryString(nimsum(pilesArray),3));
                clickedRow=-1;
                clickCount=0;
                repile();
                if(gamemode==1){
                if(IntStream.of(pilesArray).sum()<=0){
                        if(gamemode==1){
                     Msg.setText(isFirstPlayerTurn ?("Player 1 won!"+"Click on GameMode Button to restart game"):"Player 2 won!"+"Click on GameMode Button to restart game"); }   // declare winnner
                    else{Msg.setText(isFirstPlayerTurn ?"PC won!":"Human won!");}}
                    else{
                    if(gamemode==1)Msg.setText(isFirstPlayerTurn ?"Player 1 turn":"Player 2 turn"); 
                    else if(gamemode==2)Msg.setText(isFirstPlayerTurn ?"PC turn":"Human turn"); 
                }
                }
                
         
         }
         
         
         


    }
     ////////stop////
     //Below is the nimsum functions from last assignment
         public static int nimsum(int[] piles){
        int sum=piles[0];
        for(int k=1;k<piles.length;k++){
            sum=sum^piles[k];
        }          
            return sum;
    }
    public int nimsumWithMove(int pileNo,int stone,int[] piles){
        int sum=-1;
                    
                            if(pileNo!=0){
                            sum=piles[0];}
                            else{
                                sum=piles[0]-stone-1;
                            }
                    for(int k=1;k<piles.length;k++){
                        /*if(sum==-1){
                            if(k!=pileNo){
                            sum=piles[k];}
                            else{
                                sum=piles[k]-stone-1;
                            }
                        }*/
                        if(k!=pileNo){
                        sum=sum^piles[k];
                        }
                        else{
                            sum=sum^(piles[pileNo]-stone-1);
                        }
                    }

                
            return sum;
    }
    public boolean check_any_piles_with_size_two(int[] piles){
        boolean any_piles_with_size_two=false;
        for(int i=0;i<piles.length;i++){
            if(piles[i]>=2){
                any_piles_with_size_two=true;
            }
        }
        return any_piles_with_size_two;
    }
    public int playRandom(int[] piles){
        for(int i=0;i<piles.length;i++){
            if(piles[i]>0){
                return i;
            }
        }
        return 0;
    }
    public static String toBinaryString(int n, int length) {
        String binary = Integer.toBinaryString(n);
        int padding = length - binary.length();
        if (padding > 0) {
            return "0".repeat(padding) + binary;
        } else {
            return binary;
        }
    }
 }
