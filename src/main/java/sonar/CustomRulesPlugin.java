package sonar;

import org.sonar.api.Plugin;

import java.util.Collections;

public class CustomRulesPlugin implements Plugin {
    @Override
    public void define(Context context) {
        // 커스텀 룰을 등록합니다.
        context.addExtensions(Collections.singleton(MyCustomRule.class));
    }
}
