package me.shsmith0206.heuristics.data;

public class HeuristicsEntry {
    private final String answer;

    private final Response response;

    private final Question question;

    public HeuristicsEntry(String answer, Response response, Question question) {
        this.answer = answer;
        this.response = response;
        this.question = question;
    }
}
