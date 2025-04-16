package sonar;

import org.sonar.api.rule.RuleStatus;
import org.sonar.api.server.rule.RulesDefinition;

/**
 * RulesDefinition은 “메타데이터만 등록”함 (UI에서 보이게만 해줌)
 */
public class MyCustomRulesDefinition implements RulesDefinition {
    public static final String REPOSITORY_KEY = "cbjsonar";

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY_KEY, "java")
                .setName("My Custom Sonar Rules");

        repository.createRule("MethodMustHaveComment")
                .setName("Method Must Have Comment")
                .setHtmlDescription("모든 함수는 주석을 달아야 해요!")
                .setSeverity("MINOR") // 경고 레벨
                .setStatus(RuleStatus.READY)
                .setTags("convention", "comments")
                .setTemplate(false);

        repository.done();
    }
}
