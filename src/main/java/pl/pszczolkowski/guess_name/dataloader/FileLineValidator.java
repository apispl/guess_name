package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

@Component
public interface FileLineValidator {
    boolean validate(String line);
}
