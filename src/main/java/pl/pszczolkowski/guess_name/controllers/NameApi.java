package pl.pszczolkowski.guess_name.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.pszczolkowski.guess_name.buissines_logic.DetectorService;

import java.io.IOException;

@RestController
@RequestMapping("/detector")
public class NameApi {

    private final DetectorService detectorService;

    @Autowired
    public NameApi(DetectorService detectorService) {
        this.detectorService = detectorService;
    }

    @GetMapping
    public ResponseEntity<StreamingResponseBody> getTokens() {
        StreamingResponseBody responseBody = outputStream -> outputStream.write(detectorService.getAllTokens());

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(responseBody);
    }

    @PostMapping
    public ResponseEntity<String> postName(@RequestBody String name) throws IOException {
        String result = detectorService.detectName(name);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(result);
    }
}
