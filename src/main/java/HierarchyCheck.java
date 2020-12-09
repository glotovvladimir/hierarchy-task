import utils.CsvManager;

import java.util.List;

public class HierarchyCheck {

    public static void main(String[] args) {
        CsvManager manager = new CsvManager();
        List<List<String>> hierarchy = manager.readCsvRecords("src/main/resources/data.csv");

        manager.writeSingleLineToCsv(manager.getLowestHierarchyElements(hierarchy), "src/main/output/lowestHierarchyElements.csv");
        manager.writeMultipleLinesToCsv(manager.getNamesWithSameSymbols(hierarchy), "src/main/output/sameSymbolsElements.csv");
    }
}
