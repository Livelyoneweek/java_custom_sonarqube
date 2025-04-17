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

        repository.createRule("EmptyCatchBlockRule")
                .setName("Empty Catch Block Rule")
                .setHtmlDescription("catch 블록에는, 최소한 로그 출력이나 주석을 작성하세요.")
                .setSeverity("MINOR") // 경고 레벨
                .setStatus(RuleStatus.READY)
                .setTags("convention", "comments")
                .setTemplate(false);

        repository.createRule("MethodNameShouldNotStartWithTest")
                .setName("Method name should not start with 'test'")
                .setHtmlDescription("메서드 이름이 'test'로 시작하지 않아야 합니다.")
                .setSeverity("MINOR")
                .setStatus(RuleStatus.READY)
                .setTags("naming")
                .setTemplate(false);


        repository.done();
    }
}
