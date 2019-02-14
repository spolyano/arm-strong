import java.util.Date;

public class Armstronger {
    static int maxLn = 9;
    static int curLn = 1;
    static int minNum = 1;
    static int maxNum = 9;
    
    static int[][] pows = new int[10][maxLn];
    static int[] numSet = new int[10];
    static int[] tmpSet = new int[10];

    public static void main(String[] args) {
        
        Date startTime = new Date();
        
        for(int i=0;i<10;i++) {
            for(int j=0;j<maxLn;j++) pows[i][j]=i*(j+1);
            numSet[i]=0;
        }
        
        while(curLn<=maxLn) {
            if(curLn>1) incLn();
            int curIterDig=9;
            int curLeftDig=curLn;
            int curSum=0;
            
            while(true) {
                if(curIterDig==0) {
                    numSet[0]=curLeftDig;
                    checkSet(curSum);
                    numSet[0]=0;
                } else {
                    for(int i=0;i<curLeftDig;i++) {
                        if(curSum + pows[curIterDig][i] > maxNum) break;
                        numSet[curIterDig]++;
                    }
                    if(numSet[curIterDig]==0) {
                        curIterDig--;
                        continue;
                    }
                    if(curSum + pows[curIterDig][numSet[curIterDig]-1] < minNum) {
                        break;
                    }
                    curSum+=pows[curIterDig][numSet[curIterDig]-1];
                    curLeftDig-=numSet[curIterDig];
                    if(curLeftDig==0) {
                        checkSet(curSum);
                        numSet[curIterDig]--;
                        curLeftDig=1;
                        curSum-=pows[curIterDig][0];
                    }
                    curIterDig--;
                    continue;
                }
                
                while(curIterDig<10 && numSet[curIterDig]==0) curIterDig++;
                if(curIterDig==10) break;
                numSet[curIterDig]--;
                curSum-=pows[curIterDig][0];
                curLeftDig++;
                curIterDig--;
            }

            curLn++;
        }
        
        Date finishTime = new Date();
        System.out.println((finishTime.getTime()-startTime.getTime())+"ms");
    }
    
    static void checkSet(int setSum) {
        
        if(setSum<minNum) return;
        for(int i=0;i<10;i++) tmpSet[i]=0;
        int tmpSum=setSum;
        while(tmpSum!=0) {
            tmpSet[tmpSum%10]++;
            tmpSum=tmpSum/10;
        }
        for(int i=0;i<10;i++) if(tmpSet[i]!=numSet[i]) return;
        System.out.println(setSum);
    }

    static void incLn() {
        minNum*=10;
        maxNum=10*maxNum+9;
        for(int i=0;i<10;i++) {
            numSet[i]=0;
            for(int j=0;j<maxLn;j++) pows[i][j]*=i;
        }
    }
}
