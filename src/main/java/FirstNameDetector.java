import dataloader.NameDetector;

import java.util.Set;

public class FirstNameDetector extends NameDetector {

    public FirstNameDetector(Set<String> maleSet, Set<String> femaleSet) {
        super(maleSet, femaleSet);
    }

    @Override
    public String detect(String name) {
        if (super.maleSet.contains(name))
            return "MALE";
        if (femaleSet.contains(name))
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }
}
