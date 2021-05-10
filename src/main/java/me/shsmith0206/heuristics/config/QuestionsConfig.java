package me.shsmith0206.heuristics.config;

import com.dfsek.tectonic.annotations.Value;
import com.dfsek.tectonic.config.ConfigTemplate;

import java.util.List;

public class QuestionsConfig implements ConfigTemplate {
    @Value("questions")
    private List<String> questions;

    public List<String> getQuestions() {
        return questions;
    }
}
