package sonar;

import org.sonar.api.server.rule.RulesDefinition;

public class MyCustomRulesDefinition implements RulesDefinition {
    public static final String REPOSITORY_KEY = "cbjsonar";

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY_KEY, "java")
                .setName("My Custom Sonar Rules");

        NewRule rule = repository.createRule("MethodMustHaveComment");
        rule.setName("Method Must Have Comment");
        rule.setHtmlDescription("모든 함수는 주석을 달아야 해요");
        // 필요하면 severity, tags 등 추가 설정

        repository.done();
    }
}
