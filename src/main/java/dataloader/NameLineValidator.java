package dataloader;

class NameLineValidator implements FileLineValidator {
    @Override
    public boolean validate(String line) {
        return line.length() > 0;
    }
}
