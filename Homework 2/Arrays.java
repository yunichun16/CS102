import java.util.StringTokenizer;

public class Arrays {

    public static void main(String[] args) {
        String st1 = new String("Google");
        String st2 = new String("elgooG");
        System.out.println(compareString(st1, st2));
        String line = new String("Other entries include a historic district in Charlottesville Virginia cut-flower greenhouse complex");
        printShortest(line);
    }

    public static int compareString(String st1, String st2) {
        st1 = st1.toLowerCase().replaceAll(" ", "");
        st2 = st2.toLowerCase().replaceAll(" ", "");
        StringBuilder sb = new StringBuilder(st1);
        String reversedSt1 = sb.reverse().toString();
        
        return reversedSt1.equals(st2) ? 1 : 0;
    }

    public static void printShortest(String line) {
        StringTokenizer tok = new StringTokenizer(line, " ");
        StringBuilder shortList = new StringBuilder();
        while (tok.hasMoreTokens()) {
            String first = tok.hasMoreTokens() ? tok.nextToken() : "";
            String second = tok.hasMoreTokens() ? tok.nextToken() : "";
            String third = tok.hasMoreTokens() ? tok.nextToken() : "";

            // Assuming 'first' as the shortest by default to handle the tie condition
            String shortest = first;
            if (!second.isEmpty() && (second.length() < shortest.length() || shortest.isEmpty())) {
                shortest = second;
            }
            if (!third.isEmpty() && (third.length() < shortest.length() || shortest.isEmpty())) {
                shortest = third;
            }
            if (!shortest.isEmpty()) {
                shortList.append(shortest).append(" ");
            }
        }
        System.out.println(shortList.toString().trim());
    }
}
