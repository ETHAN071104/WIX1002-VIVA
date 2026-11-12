public class SAQ4 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Centre information: matching indexes refer to the same centre
        String[] centres = {"Kampung Baru Hall", "Sri Muda School", "Masjid Al-Hidayah", "Taman Melati Hall"};
        int[] maxFamilies = {60, 80, 40, 50};
        double[] foodPerFamily = {3.0, 2.5, 4.0, 3.5};
        int[] distance = {12, 25, 8, 30};

        // All centres start as unserved
        boolean[] served = {false, false, false, false};

        double foodLeft = 400.0;
        double fuelLeft = 30.0;

        int totalFamilies = 0;
        double totalFood = 0.0;
        double totalFuel = 0.0;

        while (foodLeft > 0) {

            // Check whether any unserved centre can still be reached
            boolean canReach = false;

            for (int i = 0; i < centres.length; i++) {
                double fuelNeeded = distance[i] * 2 * 0.25;

                if (!served[i] && fuelNeeded <= fuelLeft) {
                    canReach = true;
                    break;
                }
            }

            if (!canReach) {
                System.out.println("No remaining centre can be reached with the fuel left.");
                break;
            }

            // Display the complete menu
            System.out.println("\n===== Flood Relief Van Dispatcher =====");
            System.out.printf("Food Left: %.2f kg | Fuel Left: %.2f L%n", foodLeft, fuelLeft);

            for (int i = 0; i < centres.length; i++) {
                System.out.printf("%d. %s (%d families, %.1f kg/family, %d km)", i + 1, centres[i], maxFamilies[i], foodPerFamily[i], distance[i]);

                if (served[i]) {
                    System.out.print(" [SERVED]");
                }

                System.out.println();
            }

            System.out.println("0. End dispatch");
            System.out.print("Select a centre (0-4): ");

            // Validate the centre selection
            if (!input.hasNextInt()) {
                System.out.println("Error: Please enter an integer from 0 to 4.");
                input.nextLine(); // Discard invalid input
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 0) {
                System.out.println("Dispatch ended by user.");
                break;
            }

            if (choice < 0 || choice > 4) {
                System.out.println("Error: Invalid centre selection.");
                continue;
            }

            // Menu choices 1-4 correspond to array indexes 0-3
            int index = choice - 1;

            if (served[index]) {
                System.out.println("Error: This centre has already been served.");
                continue;
            }

            double fuelNeeded = distance[index] * 2 * 0.25;

            if (fuelNeeded > fuelLeft) {
                System.out.printf("Error: Not enough fuel to reach %s (needs %.2f L).%n", centres[index], fuelNeeded);
                continue;
            }

            // Keep asking until the number of families is valid
            int families;

            while (true) {
                System.out.printf("Enter number of families to serve (1-%d): ", maxFamilies[index]);

                if (!input.hasNextInt()) {
                    System.out.println("Error: Enter a whole number.");
                    input.nextLine();
                    continue;
                }

                families = input.nextInt();
                input.nextLine();

                if (families < 1 || families > maxFamilies[index]) {
                    System.out.println("Error: Number of families is out of range.");
                    continue;
                }

                break;
            }

            // Calculate how many whole family packs can be delivered
            double foodRequired = families * foodPerFamily[index];
            int familiesServed;
            boolean foodRanOut = false;

            if (foodRequired <= foodLeft) {
                familiesServed = families;
            } else {
                familiesServed = (int) (foodLeft / foodPerFamily[index]);
                foodRanOut = true;
            }

            double foodDelivered = familiesServed * foodPerFamily[index];

            // Update resources, totals and centre status
            foodLeft -= foodDelivered;
            fuelLeft -= fuelNeeded;

            totalFamilies += familiesServed;
            totalFood += foodDelivered;
            totalFuel += fuelNeeded;

            served[index] = true;

            // Display delivery summary
            System.out.println("\n--- Delivery Summary ---");
            System.out.println("Centre: " + centres[index]);
            System.out.printf("Food Required: %.2f kg%n", foodRequired);
            System.out.printf("Food Delivered: %.2f kg%n", foodDelivered);
            System.out.printf("Fuel Used: %.2f L (round trip %d km)%n", fuelNeeded, distance[index] * 2);

            if (foodRanOut) {
                System.out.printf("Food Ran Out! Only %d of %d families served.%n", familiesServed, families);
            } else {
                System.out.printf("Result: %d families served.%n", familiesServed);
            }

            System.out.println("------------------------");

            // End after a partial delivery, even if some food remains
            if (foodRanOut) {
                break;
            }
        }

        // Final report is displayed whichever way the session ends
        System.out.println("\n===== Dispatch Report =====");
        System.out.println("Families Served: " + totalFamilies);
        System.out.printf("Food Delivered: %.2f kg%n", totalFood);
        System.out.printf("Fuel Used: %.2f L%n", totalFuel);
        System.out.printf("Food Left: %.2f kg | Fuel Left: %.2f L%n", foodLeft, fuelLeft);

        input.close();
    }
}
