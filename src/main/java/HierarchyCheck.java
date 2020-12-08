import utils.CsvManager;

import java.util.List;

public class HierarchyCheck {

    public static void main(String[] args) {

        CsvManager manager = new CsvManager();

        List<List<String>> hierarchy = manager.getCsvRecords("src/main/resources/data.csv");
        System.out.println(manager.getLowestHierarchyElements(hierarchy));

        manager.getNamesWithSameSymbols(hierarchy);
    }
}
