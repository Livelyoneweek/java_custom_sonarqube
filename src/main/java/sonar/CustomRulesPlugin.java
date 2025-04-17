package sonar;

import org.sonar.api.Plugin;
import sonar.rule.Rule1EmptyCatchBlockRule;

/**
 * 커스텀 룰 최종 등록
 */
public class CustomRulesPlugin implements Plugin {
    @Override
    public void define(Context context) {
        // 커스텀 룰을 등록합니다.
        context.addExtensions(MyCustomRulesDefinition.class, Rule1EmptyCatchBlockRule.class, MyCustomCheckRegistrar.class);
    }
}
