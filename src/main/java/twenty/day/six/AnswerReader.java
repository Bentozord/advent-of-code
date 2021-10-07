package twenty.day.six;

import org.apache.commons.collections4.CollectionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AnswerReader {

    List<Set<String>> getAnswers(String filename) {
        List<Set<String>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            Set<String> groupAnswers = new HashSet<>();
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals("")) {
                    result.add(groupAnswers);
                    groupAnswers = new HashSet<>();
                }

                for (char c : currentLine.toCharArray()) {
                    groupAnswers.add(String.valueOf(c));
                }
            }
            result.add(groupAnswers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //TODO refactor
    List<Set<String>> getAnswersFiltered(String filename) {
        List<Set<String>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            Set<String> groupAnswers = new HashSet<>();
            List<String> temporaryList = new ArrayList<>();
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals("") && temporaryList.size() == 1) {
                    Set<String> temporarySet = new HashSet<>();
                    for (char c : temporaryList.get(0).toCharArray()) {
                        temporarySet.add(String.valueOf(c));
                    }
                    result.add(temporarySet);
                    temporaryList = new ArrayList<>();
                } else if (currentLine.equals("")) {
                    extract(result, groupAnswers, temporaryList);
                    groupAnswers = new HashSet<>();
                    temporaryList = new ArrayList<>();
                } else {
                    temporaryList.add(currentLine);
                }
            }

            if (temporaryList.size() == 1) {
                result.add(new HashSet<>(temporaryList));
            } else {
                extract(result, groupAnswers, temporaryList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void extract(List<Set<String>> result, Set<String> groupAnswers, List<String> temporaryList) {
        String current = temporaryList.get(0); //just need first element
        for (char c : current.toCharArray()) {
            int counter = 0;
            for (int j = 1; j < temporaryList.size(); j++) {
                if (temporaryList.get(j).contains(String.valueOf(c))) {
                    counter += 1;
                }
            }
            if (counter == temporaryList.size() - 1) {
                groupAnswers.add(String.valueOf(c));
            }
        }
        if (CollectionUtils.isNotEmpty(groupAnswers)) {
            result.add(groupAnswers);
        }
    }
}
