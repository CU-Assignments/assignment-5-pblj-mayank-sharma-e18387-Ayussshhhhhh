import java.util.ArrayList;
import java.util.Scanner;

public class SumWithAutoboxingUnboxing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();

        String[] parts = input.split(" ");

        for (String part : parts) {
            int num = Integer.parseInt(part);
            numbers.add(num); 
        }

        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }

        System.out.println("Sum of numbers: " + sum);

        scanner.close();
    }
}
