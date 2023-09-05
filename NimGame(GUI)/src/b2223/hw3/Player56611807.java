package b2223.hw3;

import java.util.Map;
public class Player56611807 extends NimPlayer{
    public static void main(String[] args) {
        Player56611807 tester=new Player56611807("tester");
        int[] test1 = {0,0,2,1,2};
        System.out.println(tester.nimsumWithMove(0, -1, test1));
        int[] expected = new int[]{0b000, 0b000, 0b000};
        Map.Entry<Integer, Integer> move = tester.nextMove(test1.clone());
        System.out.println(move.getKey()+" "+move.getValue());

    }
    public Player56611807(String name) {
        super(name);
    }
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
    @Override
    public Map.Entry<Integer, Integer> nextMove(int[] piles) {

        //Scanner scanner = new Scanner(System.in);
        int pile=1, stones=1;
        int sum;
        do {
            System.out.print(name + "'s turn: choose a pile (1-" + piles.length + ") and number of stones to remove: ");
            //pile = scanner.nextInt() - 1; // adjust index to start from 0
            //stones = scanner.nextInt();
            //sum=nimsim(nimsum(piles[0],piles[1]),piles[2]);
            sum=nimsumWithMove(1,-1,piles);
            if (sum==0){
                pile=playRandom(piles);
                return Map.entry(pile, 1);
            }
            int count=0;
            for(int i=0;i<piles.length;i++){
                if(piles[i]!=0){
                    count=count+piles[i];
                }
            }
            if(count==1){
                pile=playRandom(piles);
                return Map.entry(pile, 1);
            }
            //System.out.println("sum="+sum);
            for(int i=0;i<piles.length;i++){
                if(piles[i]!=0){
                for(int j=0;j<piles[i];j++){
                    sum=nimsumWithMove(i, j, piles);
                if(sum==0){
                    pile=i;
                    stones=j+1;
                    piles[pile]-=stones;
                    if(check_any_piles_with_size_two(piles)){
                    return Map.entry(pile, stones);}
                    else{
                        if(piles[pile]==1){
                            stones++;
                            return Map.entry(pile, stones);
                            
                        }
                        if(piles[pile]==0){
                            stones--;
                        return Map.entry(pile, stones);}
                    }
                }}}
            }
            
//            if (pile < 0 || pile >= piles.length || stones <= 0 || stones > piles[pile]) {
//                System.out.println("Invalid move. Please try again.");
//            }
        } while (pile < 0 || pile >= piles.length || stones <= 0 || stones > piles[pile]);
        
        return Map.entry(pile, stones);
    }
}
/*   if(piles[0]!=0){
                for(int j=0;j<piles[0];j++){
                    sum=nimsum(piles[0]-j-1,piles[1]);
                    sum=nimsum(sum,piles[2]);
                if(sum==0){
                    pile=0;
                    stones=j+1;
                    piles[pile]-=stones;
                    if(check_any_piles_with_size_two(piles)){
                    return Map.entry(pile, stones);}
                    else{
                        if(piles[pile]==1){
                            return Map.entry(pile, ++stones);
                        }
                        if(piles[pile]==0){
                            return Map.entry(pile, --stones);}
                    }
                }}}
                if(piles[1]!=0){
                for(int j=0;j<piles[1];j++){
                sum=nimsum(piles[0],piles[1]-j-1);
                sum=nimsum(sum,piles[2]);
                if(sum==0){
                    pile=1;
                    stones=j+1;
                    piles[pile]-=stones;
                    if(check_any_piles_with_size_two(piles)){
                    return Map.entry(pile, stones);}
                    else{
                        if(piles[pile]==1){
                            return Map.entry(pile, ++stones);
                        }
                        if(piles[pile]==0){
                            return Map.entry(pile, --stones);}
                    }
                }}}
                if(piles[2]!=0){
                for(int j=0;j<piles[2];j++){
                sum=nimsum(piles[0],piles[1]);
                sum=nimsum(sum,piles[2]-j-1);
                if(sum==0){
                    pile=2;
                    stones=j+1;
                    piles[pile]-=stones;
                    if(check_any_piles_with_size_two(piles)){
                    return Map.entry(pile, stones);}
                    else{
                        if(piles[pile]==1){
                            return Map.entry(pile, ++stones);
                        }
                        if(piles[pile]==0){
                            return Map.entry(pile, --stones);}
                    }
                }
                }}
            */