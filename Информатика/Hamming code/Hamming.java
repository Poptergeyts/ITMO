public class Hamming {
    public static void main(String[] args){
        int s1 = 0, s2 = 0, s3 = 0;
        for (byte indexI = 0; indexI < 7; indexI++){
            switch (indexI) {
                case 0 -> s1 = Integer.parseInt(args[indexI]);
                case 1 -> s2 = Integer.parseInt(args[indexI]);
                case 2 -> {
                    s1 ^= Integer.parseInt(args[indexI]);
                    s2 ^= Integer.parseInt(args[indexI]);
                }
                case 3 -> s3 = Integer.parseInt(args[indexI]);
                case 4 -> {
                    s1 ^= Integer.parseInt(args[indexI]);
                    s3 ^= Integer.parseInt(args[indexI]);
                }
                case 5 -> {
                    s2 ^= Integer.parseInt(args[indexI]);
                    s3 ^= Integer.parseInt(args[indexI]);
                }
                case 6 -> {
                    s1 ^= Integer.parseInt(args[indexI]);
                    s2 ^= Integer.parseInt(args[indexI]);
                    s3 ^= Integer.parseInt(args[indexI]);
                }
            }
        }

        int result = s3 * 4 + s2 * 2 + s1;
        if (result != 0) {
            System.out.println("Error in bit number " + result);
            result -= 1;
            if (Integer.parseInt(args[result]) == 1) {
                args[result] = "0";
            } else {
                args[result] = "1";
            }
        } else {
            System.out.println("Success");
        }

        System.out.println("Your message: " + args[2] + args[4] + args[5] + args[6]);
    }
}
