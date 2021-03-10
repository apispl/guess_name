package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

@Component
public class NameLineValidator implements FileLineValidator {
    @Override
    public boolean validate(String line) {
        return line.length() > 0;
    }
}
