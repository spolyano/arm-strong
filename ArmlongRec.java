import java.util.Date;

public class ArmlongRec {
    static int maxLn = 18;
    static int curLn = 1;
    static long minNum = 1;
    static long maxNum = 9;
    
    static long[][] pows = new long[10][maxLn];
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
            combineSet(9, curLn, 0);
            curLn++;
        }
        
        Date finishTime = new Date();
        System.out.println((finishTime.getTime()-startTime.getTime())+"ms");
    }
    
    static void combineSet(int curDigit, int leftDigits, long curSum) {
        if(leftDigits==0) {
            checkSet(curSum);
            return;
        }
        if(curDigit==0) {
            numSet[0]=leftDigits;
            checkSet(curSum);
            numSet[0]=0;
        } else {
            if(curSum + pows[curDigit][leftDigits-1] < minNum) return;
            int maxCap;
            for(maxCap=0;maxCap<leftDigits;maxCap++) {
                if(curSum + pows[curDigit][maxCap] > maxNum) {
                    maxCap= (maxCap==0) ? 0 : maxCap-1;
                    break;
                }
            }
            for(int i=maxCap;i>=0;i--) {
                numSet[curDigit]=i;
                combineSet(curDigit-1,leftDigits-i,curSum+ ((i==0) ? 0:pows[curDigit][i-1]));
            }
            
        }
    }
    
    static void checkSet(long setSum) {
        
        if(setSum<minNum) return;
        for(int i=0;i<10;i++) tmpSet[i]=0;
        long tmpSum=setSum;
        while(tmpSum!=0) {
            tmpSet[(int) (tmpSum%10)]++;
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
