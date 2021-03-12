package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

@Component
class NameLineValidator implements FileLineValidator {
    @Override
    public boolean validate(String line) {
        return line.length() > 0;
    }
}
