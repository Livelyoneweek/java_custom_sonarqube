package sonar.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

@Rule(key = "EmptyCatchBlockRule")
public class Rule1EmptyCatchBlockRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitCatch(CatchTree tree) {
        BlockTree block = tree.block();
        boolean isEmpty = block == null || block.body().isEmpty();

        if (isEmpty) {
            context.reportIssue(this, tree.parameter(), "빈 catch 블록이 있습니다. 최소한 로그 출력이나 주석을 작성하세요.");
        }

        super.visitCatch(tree);
    }

//    private JavaFileScannerContext context;
//
//    @Override
//    public void scanFile(JavaFileScannerContext context) {
//        this.context = context;
//        scan(context.getTree());
//    }
//
//    @Override
//    public void visitMethod(MethodTree methodTree) {
//        // 첫 토큰 기준으로 trivia에서 // 주석 존재 여부 확인
//        boolean hasSingleLineComment = methodTree.firstToken().trivias().stream()
//                .map(SyntaxTrivia::comment)
//                .anyMatch(comment -> comment.trim().startsWith("//"));
//
//        // 파일 전체 라인에서 메서드 정의 바로 위 줄 가져옴
//        int methodLine = methodTree.firstToken().range().start().line();
//        String previousLine = context.getFileLines().get(methodLine - 2); // -2: line index는 0-based
//
//        boolean hasBlockComment = previousLine.trim().startsWith("/**") || previousLine.trim().startsWith("/*");
//
//        // 🔍 디버깅 로그 출력
//        System.out.println(">>> method: " + methodTree.simpleName() +
//                ", line: " + methodLine +
//                ", hasSingleLineComment: " + hasSingleLineComment +
//                ", hasBlockComment: " + hasBlockComment +
//                ", previousLine: \"" + previousLine.trim() + "\"");
//
//        if (!hasSingleLineComment && !hasBlockComment) {
//            context.reportIssue(this, methodTree.simpleName(), "이 메서드는 // 또는 /* 주석이 없습니다.");
//        }
//
//        super.visitMethod(methodTree);
//    }
}