public class Recursion {

    public static void main(String[] args) {
        int[] binary = {1, 2, 3, 4, 5, 6, 7};
        int n = binary.length - 1;
        System.out.println(binaryRecursive(binary, 0, n));
        
        int N = 165;
        System.out.println(countZeros(N, 0));
        
        String s = "kayakdf";
        int length = s.length();
        System.out.println(palindrome(s, 0, length - 1));
    }
    
    public static int binaryRecursive(int[] binary, int start, int end) {
        if (end == start) {
            return binary[start];
        } else {
            int mid = (end + start) / 2;
            int max1 = binaryRecursive(binary, start, mid);
            int max2 = binaryRecursive(binary, mid + 1, end);
            return Math.max(max1, max2);
        }       
    }
    
    public static int countZeros(int N, int num) {
        if (N == 0) {
            return num + 1; // +1 to account for the "0" representation of zero.
        }
        if (N == 1) {
            return num;
        } else {
            if (N % 2 == 0) {
                return countZeros(N / 2, num + 1);
            } else {
                return countZeros(N / 2, num);
            }
        }
    }
    
    public static boolean palindrome(String s, int start, int end) {
        if (start >= end) {
            return true;
        } else {
            if (s.charAt(start) == s.charAt(end)) {
                return palindrome(s, start + 1, end - 1);
            } else {
                return false; 
            }
        }
    }
}
