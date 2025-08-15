package app;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Planner {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("\n=== Weekly Planner CLI ===\n");
        System.out.print("Enter a title for this week (e.g., Week of Aug 18, 2025): ");
        String title = readNonEmptyLine();

        int min = 3, max = 7;
        System.out.printf("How many top priorities? (%d–%d): ", min, max);
        int count = readIntInRange(min, max);

        List<String> priorities = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.printf("Priority %d: ", i);
            priorities.add(readNonEmptyLine());
        }

        System.out.print("Any optional notes? (press Enter to skip): ");
        String notes = scanner.nextLine().trim();

        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        String filename = "weekly-planner-" + date + ".md";
        String outputDir = "plans";

        String md = MarkdownWriter.buildMarkdown(title, priorities, notes);

        try {
            MarkdownWriter.writeToFile(outputDir, filename, md);
            System.out.println("\n✅ Plan saved to '" + outputDir + "/" + filename + "'.");
            System.out.println("Open it in your editor or preview it on GitHub.");
        } catch (IOException e) {
            System.err.println("\n❌ Could not save the plan: " + e.getMessage());
        }

        System.out.println("\nDone. Have a productive week!\n");
    }

    private String readNonEmptyLine() {
        while (true) {
            String s = scanner.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.print("Please enter something: ");
        }
    }

    private int readIntInRange(int min, int max) {
        while (true) {
            String s = scanner.nextLine();
            try {
                int n = Integer.parseInt(s.trim());
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Enter a number between %d and %d: ", min, max);
        }
    }
}
