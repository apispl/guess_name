package pl.pszczolkowski.guess_name.dataloader;

import org.springframework.stereotype.Component;

import java.io.SequenceInputStream;

@Component
public interface FileFacade {

    boolean hasMaleName(String name);
    boolean hasFemaleName(String name);
    SequenceInputStream fetchData();
}
