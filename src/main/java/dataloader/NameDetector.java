package dataloader;

import java.util.Set;

public abstract class NameDetector {

    public final Set<String> maleSet;
    public final Set<String> femaleSet;

    public NameDetector(Set<String> maleSet, Set<String> femaleSet) {
        this.maleSet = maleSet;
        this.femaleSet = femaleSet;
    }

    public abstract String detect(String name);
}
