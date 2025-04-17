package sonar.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;

@Rule(key = "MethodNameShouldNotStartWithTest")
public class Rule2 extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree methodTree) {
        String methodName = methodTree.simpleName().name();
        if (methodName.startsWith("test")) {
            context.reportIssue(this, methodTree.simpleName(), "메서드 이름이 'test'로 시작하면 안 됩니다.");
        }
        super.visitMethod(methodTree);
    }
}
