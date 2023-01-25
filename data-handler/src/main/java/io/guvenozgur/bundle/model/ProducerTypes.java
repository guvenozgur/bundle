package io.guvenozgur.bundle.model;

public enum ProducerTypes {
    FILE_WRITER("file_writer"), QUEUE("queue");

    private String type;

    ProducerTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
