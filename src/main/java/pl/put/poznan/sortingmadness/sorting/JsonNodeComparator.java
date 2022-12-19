package pl.put.poznan.sortingmadness.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Comparator;

/**
 * The comparator to compare two JsonNode objects. Uses given path to extract
 * value from JsonNode.
 * Comparator returns:
 * <ul>
 *     <li>Negative integer - when the first object is less than the second object</li>
 *     <li>Positive integer - when the first object is greater than the second object</li>
 *     <li>Zero - when the two objects are equal</li>
 * </ul>
 */
public class JsonNodeComparator implements Comparator<JsonNode> {
    private final String path;

    public JsonNodeComparator(String path) {
        this.path = path;
    }

    @Override
    public int compare(JsonNode firstNode, JsonNode secondNode) {
        int firstValue = firstNode.at(path).asInt();
        int secondValue = secondNode.at(path).asInt();
        return firstValue - secondValue;
    }
}
