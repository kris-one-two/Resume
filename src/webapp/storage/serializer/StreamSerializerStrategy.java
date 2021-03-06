package webapp.storage.serializer;

import webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializerStrategy {
    void doWrite(Resume resume, OutputStream outputStream) throws IOException;
    Resume doRead(InputStream inputStream) throws IOException;
}
