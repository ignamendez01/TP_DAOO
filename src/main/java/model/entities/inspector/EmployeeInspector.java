package model.entities.inspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeInspector {

    private final List<Inspector> inspectors;

    public EmployeeInspector() {
        inspectors = List.of(
                new NameInspector(),
                new PhoneInspector(),
                new ContractInspector()
        );
    }

    public List<Inspector> getInspectors() {
        return inspectors;
    }
}
