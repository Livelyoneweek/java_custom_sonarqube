package sonar;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;

@Rule(key = "MethodMustHaveComment")
public class MyCustomRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree methodTree) {
        if (methodTree.firstToken().trivias().isEmpty()) {
            context.reportIssue(this, methodTree.simpleName(), "이 메서드는 주석이 없습니다.");
        }

        // 반드시 부모 클래스의 메서드 호출
        super.visitMethod(methodTree);
    }
}
