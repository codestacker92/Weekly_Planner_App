package app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MarkdownWriter {
    public static String buildMarkdown(String title, List<String> priorities, String notes) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(escape(title)).append("\n\n");
        sb.append("_Generated: ")
          .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
          .append("_\n\n");
        sb.append("## Top Priorities\n\n");
        int i = 1;
        for (String p : priorities) {
            sb.append(i++).append(". ").append(escape(p)).append("\n");
        }
        if (notes != null && !notes.isBlank()) {
            sb.append("\n## Notes\n\n").append(escape(notes)).append("\n");
        }
        sb.append("\n---\n");
        sb.append("> Tip: Check items off by changing `1.` to `- [x]` in GitHub Markdown.\n");
        return sb.toString();
    }

    public static void writeToFile(String dir, String filename, String content) throws IOException {
        Path d = Paths.get(dir);
        if (!Files.exists(d)) Files.createDirectories(d);
        Path f = d.resolve(filename);
        Files.writeString(f, content, StandardCharsets.UTF_8);
    }

    private static String escape(String s) {
        // Minimal escaping for Markdown special chars.
        return s.replace("<", "&lt;").replace(">", "&gt;");
    }
}
