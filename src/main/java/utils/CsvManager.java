package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvManager {

    public List<List<String>> getCsvRecords(String path) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public ArrayList<String> getLowestHierarchyElements(List<List<String>> hierarchy) {
        ArrayList<String> children = new ArrayList<>();
        ArrayList<String> parents = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for (List<String> list : hierarchy) {
            children.add(list.get(0).replace(". ", "."));
            if (list.size() == 1) parents.add(null);
            else parents.add(list.get(1).replace(". ", "."));
        }
        for (String child : children) {
            if (!parents.contains(child)) result.add(child);
        }
        return result;
    }

    private String getStringWithOrderedSymbols(String string) {
        string = string.toLowerCase().replaceAll(" ", "");
        char[] charArray = string.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public List<List<String>> getNamesWithSameSymbols(List<List<String>> hierarchy) {
        ArrayList<String> children = new ArrayList<>();
        ArrayList<String> parents = new ArrayList<>();
        HashMap<String, ArrayList<String>> mapToHash = new HashMap<>();
        HashMap<String, ArrayList<String>> mapHashed = new HashMap<>();
        List<List<String>> arrFinal = new ArrayList<>();

        for (List<String> list : hierarchy) {
            children.add(list.get(0).replace(". ", "."));
            if (list.size() == 1) parents.add("-1");
            else parents.add(list.get(1).replace(". ", "."));
        }

        for (int i = 0; i < parents.size(); i++) {
            ArrayList<String> arrToAdd = new ArrayList<>();
            String parent = parents.get(i);
            String child = children.get(i);
            if (mapToHash.get(parent) == null) {
                arrToAdd.add(child);
                mapToHash.put(parent, arrToAdd);
            } else mapToHash.get(parent).add(child);
        }

        for (ArrayList<String> arr : mapToHash.values()) {
            for (String str : arr) {
                ArrayList<String> arrToAdd = new ArrayList<>();
                String sortedString = getStringWithOrderedSymbols(str);
                if (mapHashed.get(sortedString) == null) {
                    arrToAdd.add(str);
                    mapHashed.put(sortedString, arrToAdd);
                } else mapHashed.get(sortedString).add(str);
            }
        }

        for (ArrayList<String> str : mapHashed.values()) {
            if (str.size() > 1) arrFinal.add(str);
        }
        return arrFinal;
    }

}
