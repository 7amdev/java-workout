import ajp.*;

public class RepeatingDigits {
    public static void main(String[] args) {
        int countDigits[] = new int[10];

        SimpleIo.prompt("Enter a number: ");
        String inputNumber = SimpleIo.readLine().trim();

        try {
            int number = Integer.parseInt(inputNumber);
            while (number > 0) {
                countDigits[number % 10] += 1;

                number /= 10;
            }

            for (int j = 0; j < 10; j++) {
                System.out.println(j + ": " + countDigits[j]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + e.getMessage());
            System.exit(-1);
        }
    }
}