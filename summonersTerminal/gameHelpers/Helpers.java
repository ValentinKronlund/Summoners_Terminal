package summonersTerminal.gameHelpers;

import java.util.Scanner;

public class Helpers {
    /* ----- HELPERS! 👷🏽 ----- */
    public String askLine(Scanner input, String prompt) {
        Screen.get()
            .addStatusWindow()
            .addStatusMessage(prompt)
            .draw();
        while (true) {
            String correctInput = input.nextLine().trim();

            if (correctInput != null && !correctInput.isEmpty()) {
                return correctInput;
            } else {
                Screen.get()
                .addStatusWindow()
                .addStatusMessage("⚠️ Please enter a correct input value, don't leave it blank! ⚠️")
                .draw();
            }
        }
    }

    public int askInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim();
            try {
                return Integer.parseInt(inputAsString);
            } catch (NumberFormatException e) {
                Screen.get()
                    .addStatusWindow()
                    .addStatusMessage("⚠️ Please enter a whole number! ⚠️")
                    .draw();
            }

        }
    }

    public double askDobule(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputAsString = input.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(inputAsString);
            } catch (NumberFormatException e) {
                Screen.get()
                    .addStatusWindow()
                    .addStatusMessage("Please enter a number!")
                    .draw();
            }

        }
    }

    public char askChar(Scanner input, String prompt) {
        try {
            System.out.println(prompt);
            return input.nextLine().trim().charAt(0);

        } catch (Exception e) {
            System.out.println(prompt);
            return '.';
        }
    }

}
