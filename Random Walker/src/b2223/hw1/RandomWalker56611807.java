/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b2223.hw1;

import java.util.Random;

/**
 *
 * @author admin
 */
public class RandomWalker56611807 {

    public static void main(String[] args) {
        int n = 500000;
        int steps= 1;int maxStep=1;
        double prob=1;double maxProb=1;
        int walkCount;
        Sky sky = new Sky();
        while(prob>0.3){
            //System.out.println(steps);
            steps++;
            //System.out.println("prob="+prob);
            walkCount=0;
            for (int i=0;i<n;i++){  
                sky.reset();
                sky.walk(steps);
                if(sky.dis<=4)walkCount++;

            }
            prob=(double)walkCount/n;
            if (prob>=0.5){ 
                maxProb=prob;
                maxStep=steps;
            }
        }
        System.out.println("The longest random walk = "+maxStep);
        System.out.println("The chance of walking home = "+maxProb);
        System.out.println("No of iterations = "+n);
    }

    private static class Sky {
        
        private int x = 0;
        private int y = 0;
        private int dis=0;
        void walk(int steps) {
            Random random = new Random();
            for (int i=0;i<steps;i++){
            int direction = random.nextInt(4);
            if (direction == 0)x++;
        else if (direction == 1) x--;
        else if (direction == 2) y++;
        else y--;
            }
            dis=Math.abs(x)+Math.abs(y);
        }
        void reset(){
            x=0;y=0;dis=0;
        }
    }}
