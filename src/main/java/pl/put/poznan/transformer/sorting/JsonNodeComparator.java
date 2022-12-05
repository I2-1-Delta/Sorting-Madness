package pl.put.poznan.transformer.sorting;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Comparator;

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
