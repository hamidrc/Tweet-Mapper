package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

public class OrFilter implements Filter {
    private final Filter C1;
    private final Filter C2;

    public OrFilter (Filter Child1, Filter Child2) {
        this.C1 = Child1;
        this.C2 = Child2;
    }

    @Override
    public boolean matches(Status s) {
        return C1.matches(s) || C2.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> Term = new ArrayList<>();
        Term.addAll(C1.terms());
        Term.addAll(C2.terms());
        return Term;
    }

    @Override
    public String toString() {
        return "(" + C1.toString() +
                " or " + C2.toString() + ")";
    }
}