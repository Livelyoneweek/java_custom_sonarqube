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

        repository.createRule("RestControllerMustHaveTag")
                .setName("Rest Controller Must Have Tag")
                .setHtmlDescription("컨트롤러 클래스에 Tag 어노테이션을 입력하시기 바랍니다.")
                .setSeverity("MINOR")
                .setStatus(RuleStatus.READY)
                .setTags("convention")
                .setTemplate(false);

        repository.createRule("ApiMethodMustHaveOperation")
                .setName("Api Method Must Have Operation")
                .setHtmlDescription("api 메소드에는 operation 어노테이션을 입력하시기 바랍니다.")
                .setSeverity("MINOR")
                .setStatus(RuleStatus.READY)
                .setTags("convention")
                .setTemplate(false);

        repository.done();
    }
}
