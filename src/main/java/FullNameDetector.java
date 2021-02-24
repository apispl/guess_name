import java.util.Set;

public class FullNameDetector extends NameDetector {

    public FullNameDetector(Set<String> maleSet, Set<String> femaleSet) {
        super(maleSet, femaleSet);
    }

    @Override
    public String detect(String nameSurname) {
        String[] separatedNames = nameSurname.split(" ");

        int maleCounter = 0;
        int femaleCounter = 0;

        for (String separatedName : separatedNames) {
            if (isMale(separatedName))
                maleCounter++;
            if (isFemale(separatedName))
                femaleCounter++;
        }

        if (maleCounter > femaleCounter)
            return "MALE";
        if (maleCounter < femaleCounter)
            return "FEMALE";
        else
            return "INCONCLUSIVE";
    }

    private boolean isMale(String name){
        return super.maleSet.contains(name);
    }

    private boolean isFemale(String name){
        return super.femaleSet.contains(name);
    }
}
